package com.jiangconglin.httpclient;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * HttpClient Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Jul 1, 2019</pre>
 */
public class HttpClientTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: get(String url)
     */
    @Test
    public void testGet() throws Exception {
        HttpClient.get("http://www.baidu.com");
    }


} 
