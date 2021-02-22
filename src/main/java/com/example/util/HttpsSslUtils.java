package com.example.util;

import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.asn1.pkcs.RSAPrivateKey;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import javax.net.ssl.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.util.Map;

/**
 * @Auther: yangtingting
 * @Date: 2021/2/7
 */
public class HttpsSslUtils {
    /**
     1.pom.xml依赖第三方包做openssl私钥算法解析，默认jdk库无法解析超长128byte以上的密钥
     <dependency>
     <groupId>org.bouncycastle</groupId>
     <artifactId>bcprov-jdk15on</artifactId>
     <version>1.60</version>
     </dependency>
     2.在双向验证中，客户端需要用到密钥库，保存自己的私钥和证书，并且证书需要提前发给服务器，由服务器放到它的信任库中。
     3.可通过命令方式调试对比：curl -v -i --cacert ca.crt --cert client.crt --key openssl.key https://ssl.com/token -X POST -d '{"user_id":"1"}'
     */
    private final static Logger logger = LoggerFactory.getLogger(HttpsSslUtils.class);

    static final int CONNECT_TIMEOUT_MILLES = 3000;
    static final Charset ENCODING;
    public static final String HTTP_GET = "GET";
    public static final String HTTP_POST = "POST";
    static final String [] METHODS;

    static {
        ENCODING = Charset.forName("UTF-8");
        METHODS = new String[]{HTTP_GET, HTTP_POST};
    }
    static {
        //for localhost testing only
        javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(
                new javax.net.ssl.HostnameVerifier(){

                    public boolean verify(String hostname,
                                          javax.net.ssl.SSLSession sslSession) {
                        if (hostname.equals("localhost")) {
                            return true;
                        }
                        return false;
                    }
                });
    }

    public static String doSslGet(String url, Map<String, String> headers, Map<String,String> params, String content, SSLKeyStore ssl) throws IOException {
        return doOutput(url, HTTP_GET, headers, params, content, true, ssl);
    }

    public static String doSslPost(String url, Map<String, String> headers, Map<String,String> params, String content, SSLKeyStore ssl) throws IOException {
        return doOutput(url, HTTP_POST, headers, params, content, true, ssl);
    }

    public static String doOutput(final String url,final String method,final Map<String, String> headers,final Map<String,String> params,final String content,boolean isSsl, SSLKeyStore ssl) throws IOException {
        HttpURLConnection conn = null;
        try{
            conn = createConnection(setParams(url, params), isSsl, ssl);
            setMethod(conn, method);
            setHeaders(conn, headers);
            conn.connect();
            output(conn, content);
            return input(conn);
        }catch(IOException ioe){
            ioe.printStackTrace();
            throw ioe;
        } finally{
            connClose(conn);
        }
    }

    private static void connClose(HttpURLConnection conn){
        if (conn != null){
            conn.disconnect();
        }
    }

    private static String input(HttpURLConnection conn) throws IOException{
        int len ;
        char[] cbuf = new char[1024 * 8];
        StringBuilder buf = new StringBuilder();
        int status = conn.getResponseCode();
        InputStream in = null;
        BufferedReader reader = null;
        try{
            in = conn.getErrorStream();
            if (in == null && status < 400) { //400或以上表示：访问的页面域名不存在或者请求错误、服务端异常
                in = conn.getInputStream();
            }
            if (in != null){
                reader = new BufferedReader(new InputStreamReader(in, ENCODING));
                while ((len = reader.read(cbuf)) > 0){
                    buf.append(cbuf, 0 , len);
                }
            }
        }finally{
            if (reader != null){
                reader.close();
            }
            if (in != null){
                in.close();
            }
        }
        return buf.toString();
    }

    private static void output(HttpURLConnection conn, String content) throws IOException {
        if (StringUtils.isEmpty(content))
            return ;
        OutputStream out = conn.getOutputStream();
        try{
            out.write(content.getBytes(ENCODING));
        } finally{
            if (out != null){
                out.flush();
                out.close();
            }
        }
    }

    private static HttpURLConnection createConnection(String url, boolean isSsl, SSLKeyStore ssl) throws IOException {
        HttpURLConnection conn = null;
        if (isSsl) {
            try {
                conn = (HttpsURLConnection)(new URL(url)).openConnection();
                if (ssl != null){
                    //ssl协议，证书验证
                    SSLSocketFactory sslSocketFactory = getSSLSocketFactory(ssl.getCaAlias(),ssl.getCaPath(),ssl.getCrtAlias(),ssl.getCrtPath(),ssl.getKeyPath(), ssl.getPassword());
                    ((HttpsURLConnection) conn).setSSLSocketFactory(sslSocketFactory);
                }else {
                    //ssl协议，跳过证书验证
                    httpssl(conn);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            conn = (HttpURLConnection)(new URL(url)).openConnection();
        }
        setConfig(conn);
        return conn;
    }

    private static void setMethod(HttpURLConnection conn, String method) throws IOException{
        Assert.isTrue(StringUtils.containsAny(method,METHODS),"只支持GET、POST、PUT、DELETE操作");
        conn.setRequestMethod(method);
    }

    private static void setConfig(HttpURLConnection conn){
        conn.setConnectTimeout(CONNECT_TIMEOUT_MILLES);
        conn.setUseCaches(false);
        conn.setInstanceFollowRedirects(true);
        conn.setRequestProperty("Connection", "close");
        //conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");//这个根据需求，自已加，也可以放到headersc参数内
        conn.setDoOutput(true);//是否启用输出流，method=get,请求参数是拼接在地址档，默认为false
        conn.setDoInput(true);//是否启用输入流
    }

    private static void setHeaders(HttpURLConnection conn, Map<String,String> headers){
        if (headers == null || headers.size() <= 0) return ;
        headers.forEach((k,v) -> conn.setRequestProperty(k,v));//设置自定义header参数
    }

    private static String setParams(String url, Map<String, String> params){
        if (params == null || params.size() <= 0)
            return url;
        StringBuilder sb = new StringBuilder(url);
        sb.append("?");
        params.forEach((k,v)->sb.append(k).append("=").append(v).append("&"));
        return sb.substring(0, sb.length() - 1);
    }

    /**
     * 创建ssl连接(此版本跳过证书较验)
     * SSL协议位于TCP/IP协议与各种应用层协议之间，为数据通讯提供安全支持，提供如下支持：
     * 1）认证用户和服务器，确保数据发送到正确的客户机和服务器；
     * 2）加密数据以防止数据中途被窃取；
     * 3）维护数据的完整性，确保数据在传输过程中不被改变。
     * @throws Exception
     */
    public static void httpssl(HttpURLConnection conn) throws Exception {
        TrustManager[] trustAllCerts = new TrustManager[1];
        TrustManager tm = new SslManager();
        trustAllCerts[0] = tm;
        javax.net.ssl.SSLContext sc = javax.net.ssl.SSLContext.getInstance("SSL");
//        javax.net.ssl.SSLContext sc = javax.net.ssl.SSLContext.getInstance("TLS");
        sc.init(null, trustAllCerts, new SecureRandom());
        ((HttpsURLConnection)conn).setSSLSocketFactory(sc.getSocketFactory());
        ((HttpsURLConnection)conn).setHostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String str, SSLSession session) {
                return true;
            }
        });
    }

    /**
     *  SSL双向认证，需要服务端和客户端均加载ca证书和客户端证书，并且支持私钥加密解密（双向解析签名）
     * @param caAlias  服务端证书别名
     * @param caPath    服务端证书绝对路径
     * @param crtAlias  客户端证书别名
     * @param crtPath   客户端证书绝对路径
     * @param keyPath   密钥证书绝对路径
     * @param password  本地证书信任库密码，可以为空
     * @return
     * @throws Exception
     */
    public static SSLSocketFactory getSSLSocketFactory(String caAlias, String caPath, String crtAlias, String crtPath, String keyPath, String password) throws Exception{
        //CA证书是用来认证服务端的，CA就是一个公认的认证证书
        CertificateFactory cacf = CertificateFactory.getInstance("X.509");
        InputStream caStream = new FileInputStream(new File(caPath));
        Certificate ca = cacf.generateCertificate(caStream);
        KeyStore caks = KeyStore.getInstance(KeyStore.getDefaultType());
        caks.load(null, password.toCharArray());
        caks.setCertificateEntry(caAlias, ca);
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        tmf.init(caks);
        //关闭文件流
        caStream.close();

        //crt客户端证书，发送给服务端做双向验证
        CertificateFactory crtcf = CertificateFactory.getInstance("X.509");
        InputStream crtStream = new FileInputStream(new File(crtPath));
        Certificate crt = crtcf.generateCertificate(crtStream);
        KeyStore crtks = KeyStore.getInstance(KeyStore.getDefaultType());
        crtks.load(null, password.toCharArray());
        crtks.setCertificateEntry(crtAlias, crt);
        //客户端私钥，处理双向SSL验证中服务端用客户端证书加密的数据的解密（解析签名）工具
        //加载openssl私钥文件并返回解析对象
        PrivateKey privateKey = getPrivateKey(keyPath);
        crtks.setKeyEntry(crtAlias + ".private.key",  privateKey, password.toCharArray(), new Certificate[]{crt});

        //初始化秘钥管理器
        KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        kmf.init(crtks, password.toCharArray());
        //关闭文件流0
        crtStream.close();

        //注意，此处用TLSv1.2，需要服务端与客户端采用相同协议
        SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
        // 第一个参数是授权的密钥管理器，用来授权验证。TrustManager[]第二个是被授权的证书管理器，用来验证服务器端的证书。第三个参数是一个随机数值，可以填写null
        sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), new SecureRandom());
        return sslContext.getSocketFactory();
    }

    /**
     * 解析openssl私钥文件
     * @param keyPath
     * @return
     * @throws Exception
     */
    public static PrivateKey getPrivateKey(String keyPath) throws Exception{
        PrivateKey privKey = null;
        PemReader pemReader = null;
        File file = new File(keyPath);
        try {
            if (!file.exists()){
                throw new FileNotFoundException("未找到私钥文件：" + keyPath);
            }
            pemReader = new PemReader(new FileReader(file));
            PemObject pemObject = pemReader.readPemObject();
            byte[] pemContent = pemObject.getContent();
            //支持从PKCS#1或PKCS#8 格式的私钥文件中提取私钥
            if (pemObject.getType().endsWith("RSA PRIVATE KEY")) {
                //取得私钥  for PKCS#1 , openssl genrsa 默认生成的私钥就是PKCS1的编码
                RSAPrivateKey asn1PrivKey = RSAPrivateKey.getInstance(pemContent);
                RSAPrivateKeySpec rsaPrivKeySpec = new RSAPrivateKeySpec(asn1PrivKey.getModulus(), asn1PrivKey.getPrivateExponent());
                KeyFactory keyFactory= KeyFactory.getInstance("rsa");
                privKey= keyFactory.generatePrivate(rsaPrivKeySpec);
            } else if (pemObject.getType().endsWith("PRIVATE KEY")) {
                //通过openssl pkcs8 -topk8转换为pkcs8，例如（-nocrypt不做额外加密操作）：
                //openssl pkcs8 -topk8 -in pri.key -out pri8.key -nocrypt
                //取得私钥 for PKCS#8
                PKCS8EncodedKeySpec privKeySpec = new PKCS8EncodedKeySpec(pemContent);
                KeyFactory kf = KeyFactory.getInstance("rsa");
                privKey = kf.generatePrivate(privKeySpec);
            }
        } catch (FileNotFoundException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        } catch (NoSuchAlgorithmException e) {
            throw e;
        } catch (InvalidKeySpecException e) {
            throw e;
        }  finally {
            try {
                if (pemReader != null) {
                    pemReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return privKey;
    }
    //TrustManager是JSSE 信任管理器的基接口，管理和接受提供的证书,通过JSSE可以很容易地编程实现对HTTPS站点的访问
    //X509TrustManager此接口的实例管理使用哪一个 X509 证书来验证远端的安全套接字
    public static class SslManager implements TrustManager, X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
        }
        @Override
        public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
        }
        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    /**
     *  此实体用于封装ssl请求，证书验证相关的keystroe信息
     */
    public static class SSLKeyStore{
        //ca证书别名
        private String caAlias;
        //ca存放绝对路径
        private String caPath;
        //客户端证书别名
        private String crtAlias;
        //客户端证书存放绝对路径
        private String crtPath;
        //客户端密钥存放绝对路径
        private String keyPath;
        //keystroey证书信任库访问密码，可以默认为null
        private String password;

        public String getCaAlias() {
            return caAlias;
        }

        public void setCaAlias(String caAlias) {
            this.caAlias = caAlias;
        }

        public String getCaPath() {
            return caPath;
        }

        public void setCaPath(String caPath) {
            this.caPath = caPath;
        }

        public String getCrtAlias() {
            return crtAlias;
        }

        public void setCrtAlias(String crtAlias) {
            this.crtAlias = crtAlias;
        }

        public String getCrtPath() {
            return crtPath;
        }

        public void setCrtPath(String crtPath) {
            this.crtPath = crtPath;
        }

        public String getKeyPath() {
            return keyPath;
        }

        public void setKeyPath(String keyPath) {
            this.keyPath = keyPath;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    public static void main(String[] args) throws IOException {
        //https 测试
        String httpsUrl = "https://localhost:8443/api-xm/api/getPlayUrl?uuid=ec1f001cb7024db3b2ac61b638be46fc&appkey=a72c304a25834a9f9c9181be93951aad&tm=00000011599117657012&sign=1835952328e99ac4f4b2c445d5098191&sn=077fa0ef0ad85f70&ver=2&channel=1";

//        String content = "{\"user_id\":\"1\"}";
        SSLKeyStore ssl = new SSLKeyStore();
        ssl.setCaAlias("server");
        ssl.setCaPath("/Volumes/W/server.crt");
        ssl.setCrtAlias("client");
        ssl.setCrtPath("/Volumes/W/client.crt");
        ssl.setKeyPath("/Volumes/W/client.key");
        ssl.setPassword("123456");
        String html = doSslGet(httpsUrl,null, null, null, ssl);
        System.out.println(html);
    }
}
