package com.jiangconglin.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpClient {

    private static HttpClientConnectionManager manager;

    static {
        manager = new PoolingHttpClientConnectionManager();
        ((PoolingHttpClientConnectionManager) manager).setMaxTotal(50);
        ((PoolingHttpClientConnectionManager) manager).setDefaultMaxPerRoute(25);
    }

    public static void get(String url)
    {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet(url);
        try {
            HttpResponse response = client.execute(get);
            HttpEntity entity = response.getEntity();
            System.out.println(response.getStatusLine());
            System.out.println(EntityUtils.toString(entity));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
