package com.flzc.base.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by iverson on 2015/11/22.
 */
public class HttpUtils {
    /**
     * @param url 以表单形式
     * @param formData 提交的数
     * @throws IOException
     */
    public static String post(String url, Map<String, Object> formData) throws Exception {
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        if (formData != null) {
            Iterator<Map.Entry<String, Object>> iterator = formData.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Object> next = iterator.next();
                String key = next.getKey();
                Object value = next.getValue();
                formparams.add(new BasicNameValuePair(key, value.toString()));
            }
        }

        HttpEntity reqEntity = new UrlEncodedFormEntity(formparams, "utf-8");

        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(50000)
                .setConnectTimeout(50000)
                .setConnectionRequestTimeout(50000)
                .build();
        post.setEntity(reqEntity);
        post.setConfig(requestConfig);
        HttpResponse response = client.execute(post);
        if (response.getStatusLine().getStatusCode() == 200) {
            HttpEntity resEntity = response.getEntity();
            return  EntityUtils.toString(resEntity, "utf-8");
        } else {
            throw new Exception("请求失败");
        }
    }

    /**
     *以流的形式发送数据
     * @param url
     * @param content
     * @return
     */
    public static String post(String url ,String content) throws Exception {
        HttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        StringEntity entity = new StringEntity(content);
        entity.setContentEncoding( "UTF-8" );
        entity.setContentType( "application/json" );//设置为 json数据
        post.setEntity(entity);
        HttpResponse response = client.execute(post);
        HttpEntity resEntity = response.getEntity();
        String res = EntityUtils.toString(resEntity);
        return  res;
    }
}
