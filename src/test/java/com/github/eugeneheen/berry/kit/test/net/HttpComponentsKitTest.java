package com.github.eugeneheen.berry.kit.test.net;

import com.github.eugeneheen.berry.kit.net.HttpComponentsClientKit;
import com.github.eugeneheen.berry.kit.net.HttpResponseKit;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * 描述类的功能
 *
 * @author Eugene
 * 2019-03-19 9:27
 */
public class HttpComponentsKitTest {

    @Test
    public void testFileUpload() throws IOException {
        HttpComponentsClientKit httpComponentsClientKit = new HttpComponentsClientKit();
        InputStream file = new FileInputStream(new File("D:\\Work\\logo.png"));
        System.out.println(file.available());
        HttpPost httpPost = httpComponentsClientKit.createHttpPost("http://localhost:1006/user/upload", "file", "test", file);
        CloseableHttpResponse response = httpComponentsClientKit.execute(httpPost);
        HttpResponseKit responseKit = new HttpResponseKit();
        String json = responseKit.getStringContent(response, StandardCharsets.UTF_8);
        System.out.println(json);
    }
}
