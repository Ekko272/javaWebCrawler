package com.ekko.secondproject;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class HttpGetTest {
    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //设置请求地址为：https://www.deanza.edu/search/?query=clubs&collection=de-anza-meta
        //创建URIBuilder
        //设置参数



        //设置目标网站url
//        HttpGet httpGet = new HttpGet("https://www.deanza.edu/clubs/club-list.html");
//
//        //发请求，获取response
//        CloseableHttpResponse response = null;
//        response = httpClient.execute(httpGet);
//
//        //解析响应
//        if(response.getStatusLine().getStatusCode() == 200){
//            String content = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
//            System.out.println(content);
//        }
//
//        response.close();
    }

}
