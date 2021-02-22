package com.example.other;

import com.example.util.HttpUtil;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

/**
 * @Auther: yangtingting
 * @Date: 2021/2/7
 */
public class HttpsTest {
    public static void main(String[] args) throws NoSuchAlgorithmException, KeyManagementException, IOException {
        String url = "https://localhost:8443/api-xm/api/getPlayUrl?uuid=ec1f001cb7024db3b2ac61b638be46fc&appkey=a72c304a25834a9f9c9181be93951aad&tm=00000011599117657012&sign=1835952328e99ac4f4b2c445d5098191&sn=077fa0ef0ad85f70&ver=2&channel=1";
//        String url = "http://localhost:8081/api-xm/api/getPlayUrl?uuid=ec1f001cb7024db3b2ac61b638be46fc&appkey=a72c304a25834a9f9c9181be93951aad&tm=00000011599117657012&sign=1835952328e99ac4f4b2c445d5098191&sn=077fa0ef0ad85f70&ver=2&channel=1";
        String result = HttpUtil.ignoreVerifySSLSendGet(url);
//        String result = HttpUtil.doGet(url);
        System.out.println(result);
    }
}
