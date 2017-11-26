package com.taotao.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
/**
 * Created by ASUS on 2017/11/2.
 */
public class HttpClientTest {
    @Test
    public void testHttpGet() throws Exception{
        CloseableHttpClient httpClient= HttpClients.createDefault();
        HttpGet get = new HttpGet("http://www.itheima.com");
        CloseableHttpResponse response = httpClient.execute(get);
        HttpEntity entity = response.getEntity();
        String s = EntityUtils.toString(entity);
        System.out.println(s);
        response.close();
        httpClient.close();
    }
}
