package com.ekko.secondproject;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class HttpClientPoolTest {
    public static void main(String[] args) throws IOException {
        //创建连接池管理器
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();

        //设置最大连接数
        cm.setMaxTotal(100);

        //设置每个主机的最大连接数
        cm.setDefaultMaxPerRoute(10);

        //使用管理器发起请求
        doGet(cm);
        doGet(cm);
    }

    private static void doGet(PoolingHttpClientConnectionManager cm) throws IOException {
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();
        HttpGet httpGet = new HttpGet("https://www.deanza.edu/clubs/club-list.html");

        //发请求，获取response
        CloseableHttpResponse response = null;
        response = httpClient.execute(httpGet);

        //解析响应
        if(response.getStatusLine().getStatusCode() == 200){
            String content = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            System.out.println(content.length());
        }

        if(response != null) {
            response.close();
        }
    }
}
