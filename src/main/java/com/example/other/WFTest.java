package com.example.other;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wanfangdata.open.ext.apache.http.entity.ContentType;
import com.wanfangdata.open.sdk.http.*;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Auther: yangtingting
 * @Date: 2020/12/8
 */
public class WFTest {
    public static void main(String[] args) throws Exception {
        String appKey = "783171233690";
        String appSecret = "5alc6y5y9zpviy7xl8dvwgorsdn3z0ax";
        String appCode = "f5gbjtug30lgox2k7fggkayq0iirdzvf";

        WFClient client = new WFHttpClient(appKey, appSecret, appCode);

        WFHttpRequest request = new WFHttpRequest();
        request.setUrl("http://api.wanfangdata.com.cn/openwanfang/fulltextStatus");
//        request.addParam("docId",);
//        request.addParam("timestamp",System.currentTimeMillis());
//        request.setUrl("http://api.wanfangdata.com.cn/openwanfang/getQuery");

        request.setHttpMethod(HttpMethod.GET);
//        request.setHttpMethod(HttpMethod.POST);
//        RawItem rawItem = new RawItem();
//        rawItem.setContentType(ContentType.APPLICATION_JSON);
        String body ="{\n" +
                "  \"collections\":[\"OpenPeriodical\"],\n" +
//                "  \"cursor_mark\":'*',\n" +
//                "  \"query\":\"关键字：工程经济\",\n" +
//                "  'query':'关键词:量子卫星 PublishDate:2012\\01\\01',\n" +
                "  \"query\":\"关键词:量子卫星 PublishYear:2020\",\n" +
//                "  \"filters\":[{\"field\":\"SourceDB\",\"value\":\"NSTL\"}],\n" +
                "  \"returned_fields\":[\"Title\",\"Id\",'Creator','FirstCreator','ScholarId','ForeignCreator'," +
                "'OrganizationNorm','OrganizationNew','OriginalOrganization','OriginalClassCode','MachinedClassCode','ClassCodeForSearch'," +
                "'Keywords','ForeignKeywords','MachinedKeywords','Abstract','PeriodicalId','PeriodicalTitle','SourceDB','IsOA','Fund'," +
                "'PublishDate','MetadataOnlineDate','FulltextOnlineDate','ServiceMode','HasFulltext','Issue','Volum','Page','PageNo'," +
                "'Column','CorePeriodical','FulltextPath','DOI','AuthorOrg','ThirdPartyUrl','Language','ISSN','CN','SequenceInIssue','PrePublishVersion'," +
                "'PrePublishGroupId','PublishStatus','Type',\"PublishYear\"],\n" +
//                "'returned_fields':['HasFulltext','FulltextPath'],"+
                "  \"sort\":{\n" +
                "    \"sorts\":[" +
//                "{\"by\":\"score\",\"order\":\"DESC\"},\n" +
                "    {\"by\":\"Id\",\"order\":\"DESC\"}]\n" +
                "  }\n" +
                "}";
//        rawItem.setRaw(body);
//        request.setRawItem(rawItem);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        WFHttpResponse wfHttpResponse = client.syncInvoke(request);

        JSONObject jsonObject = JSONObject.parseObject(wfHttpResponse.getResponseContent());
        JSONArray documents = jsonObject.getJSONArray("documents");
        System.out.println("ResponseContent=====" + wfHttpResponse.getResponseContent());

        List<WKMessage> wkMessageList = new ArrayList<>();
        for (int i = 0; i < documents.size(); i++) {
            JSONObject document = documents.getJSONObject(i);
            WKMessage wkMessage = new WKMessage();
            wkMessage.setSource("WF");
            wkMessage.setResource_type(document.getString("resourceType"));
            JSONObject fields = document.getJSONObject("fields");
            wkMessage.setTitle(getListValueString(fields.getJSONObject("Title")));
            wkMessage.setId(fields.getJSONObject("Id").getString("stringValue"));
            wkMessage.setCreator(getListValueString(fields.getJSONObject("Creator")));
            if (fields.containsKey("FirstCreator"))
                wkMessage.setFirst_creator(fields.getJSONObject("FirstCreator").getString("stringValue"));
            else
                wkMessage.setFirst_creator("");
            if (fields.containsKey("ForeignCreator"))
                wkMessage.setForeign_creator(getListValueString(fields.getJSONObject("ForeignCreator")));
            else
                wkMessage.setForeign_creator("");
            if (fields.containsKey("OrganizationNorm"))
                wkMessage.setOrganization_norm(getListValueString(fields.getJSONObject("OrganizationNorm")));
            else
                wkMessage.setOrganization_norm("");
            if (fields.containsKey("OrganizationNew"))
                wkMessage.setOrganization_new(getListValueString(fields.getJSONObject("OrganizationNew")));
            else
                wkMessage.setOrganization_new("");
            if (fields.containsKey("OriginalOrganization"))
                wkMessage.setOriginal_organization(getListValueString(fields.getJSONObject("OriginalOrganization")));
            else
                wkMessage.setOriginal_organization("");
            if (fields.containsKey("Keywords"))
                wkMessage.setKeywords(getListValueString(fields.getJSONObject("Keywords")));
            else
                wkMessage.setKeywords("");
            if (fields.containsKey("ForeignKeywords"))
                wkMessage.setForeign_keywords(getListValueString(fields.getJSONObject("ForeignKeywords")));
            else
                wkMessage.setForeign_keywords("");
            if (fields.containsKey("MachinedKeywords"))
                wkMessage.setMachined_keywords(getListValueString(fields.getJSONObject("MachinedKeywords")));
            else
                wkMessage.setMachined_keywords("");
            if (fields.containsKey("Abstract"))
                wkMessage.setContent(getListValueString(fields.getJSONObject("Abstract")));
            else
                wkMessage.setContent("");
            wkMessage.setPublish_time(fields.getJSONObject("PublishDate").getString("stringValue"));
            wkMessage.setCrawl_time(dateFormat.format(new Date()));
            wkMessageList.add(wkMessage);
        }
//        System.out.println("RequestId=====" + wfHttpResponse.getRequestId());
        System.out.println("ResponseContent=====" + wfHttpResponse.getResponseContent());
        System.out.println("wkMessageList=====" + JSONArray.parseArray(JSON.toJSONString(wkMessageList)));
    }
    private static List<String> getListValue(JSONObject listValue){
        List<String> valueList = new ArrayList<>();
        JSONArray values = listValue.getJSONObject("listValue").getJSONArray("values");
        for (int i = 0; i < values.size(); i++) {
            JSONObject value = values.getJSONObject(i);
            valueList.add(value.getString("stringValue"));
        }
        return valueList;
    }
    private static String getListValueString(JSONObject listValue){
        StringBuffer sb = new StringBuffer();
        JSONArray values = listValue.getJSONObject("listValue").getJSONArray("values");
        for (int i = 0; i < values.size(); i++) {
            JSONObject value = values.getJSONObject(i);
            sb.append(value.getString("stringValue"));
            if (i<values.size()-1){
                sb.append(",");
            }
        }
        return sb.toString();
    }
}
