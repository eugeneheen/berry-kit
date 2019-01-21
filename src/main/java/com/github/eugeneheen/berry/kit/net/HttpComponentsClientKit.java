package com.github.eugeneheen.berry.kit.net;

import com.github.eugeneheen.berry.kit.exception.HttpComponentsException;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 使用Apache Commons项目的HttpComponents实现的Http客户端工具箱，用于Http访问便捷操作。<br>
 * Http工具箱使用流程：<br>
 * 1 创建请求类型对象，例如：HttpGet或HttpPost请求对象。<br>
 * 2 创建HttpClient对象。<br>
 * 3 使用doGet方法执行HttpGet请求，使用doPost方法执行HttpPost请求，获取HttpResponse对象。
 * Get请求用户需要自定义创建带参数的URI对象。Post请求如果存在参数，需要通过HttpEntity设置参数。<br>
 * 4 通过HttpResponse对象获取响应内容及其他响应项。<br>
 * </p>
 * Created by Eugene on 2016/10/24.
 */
public class HttpComponentsClientKit extends HttpResponseKit {

    /**
     * Http协议。
     */
    public final static String HTTP_SCHEME = "http";

    /**
     * Https协议。
     */
    public final static String HTTPS_SCHEME = "https";

    /**
     * 创建一个默认的Http客户端对象。
     *
     * @return Http客户端对象（CloseableHttpClient）。
     */
    public CloseableHttpClient creatHttpClient() {
        return HttpClients.createDefault();
    }

    /**
     * 直接使用指定的url创建URI对象。
     *
     * @param url url地址。
     * @return URI对象。
     */
    public URI createURI(String url) {
        URI uri;
        try {
            uri = new URIBuilder(url).build();
        } catch (URISyntaxException e) {
            throw new HttpComponentsException("通过URL地址创建URI对象失败！", e);
        }
        return uri;
    }

    /**
     * 用户自定义创建URI对象。
     *
     * @param scheme     访问协议，可通过HttpComponentsClientKit的HTTP_SCHEME、HTTPS_SCHEME等常量获取。
     * @param host       主机IP或域名。
     * @param port       访问端口。不指定端口时传入-1。
     * @param path       访问的URL。
     * @param parameters 访问参数，无访问参数时传入null。
     * @return URI对象。
     */
    public URI createURI(String scheme, String host, int port, String path, Map<String, String> parameters) {
        //端口缺省设置的值
        final int DEFAULT = -1;
        URI uri;
        URIBuilder uriBuilder = new URIBuilder().setScheme(scheme).setHost(host);
        //存在访问端口时，设置指定端口
        if (port != DEFAULT) {
            uriBuilder.setPort(port);
        }
        uriBuilder.setPath(path);
        //存在请求参数时，在URL后面添加请求参数
        if (parameters != null && parameters.size() > 0) {
            for (Map.Entry<String, String> entry : parameters.entrySet()) {
                uriBuilder.setParameter(entry.getKey(), entry.getKey());
            }
        }
        try {
            uri = uriBuilder.build();
        } catch (URISyntaxException e) {
            throw new HttpComponentsException("创建带参数的URI对象失败！", e);
        }
        return uri;
    }

    /**
     * 创建HttpEntity对象。
     *
     * @param nameValuePairs 请求传递的参数。
     * @param charset        字符编码集。
     * @return HttpEntity对象。
     */
    public HttpEntity createHttpEntity(Map<String, String> nameValuePairs, Charset charset) {
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        for (Map.Entry<String, String> entry : nameValuePairs.entrySet()) {
            nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }

        HttpEntity httpEntity = new UrlEncodedFormEntity(nvps, charset);

        return httpEntity;
    }

    /**
     * 创建HttpGet请求对象。
     *
     * @param url 请求的URL。
     * @return HttpGet请求对象。
     */
    public HttpGet createHttpGet(String url) {
        return new HttpGet(url);
    }

    /**
     * 创建HttpGet请求对象。
     *
     * @param uri URI请求对象，带有请求协议、地址、端口、URL、参数。通过HttpComponentsClientKit类的createURI方法创建。
     * @return HttpGet请求对象。
     */
    public HttpGet createHttpGet(URI uri) {
        return new HttpGet(uri);
    }

    /**
     * 创建HttpPost请求对象。
     *
     * @param url 请求的URL。
     * @return HttpPost请求对象。
     */
    public HttpPost createHttpPost(String url) {
        return new HttpPost(url);
    }

    /**
     * 创建HttpPost请求对象。
     *
     * @param url 请求的URL。
     * @param data 请求数据
     * @return HttpPost请求对象。
     */
    public HttpPost createHttpPost(String url, String data) {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(new StringEntity(data, StandardCharsets.UTF_8));
        return httpPost;
    }

    /**
     * 创建HttpPost请求对象。
     *
     * @param uri URI请求对象，带有请求协议、地址、端口、URL、参数。通过HttpComponentsClientKit类的createURI方法创建。
     * @return HttpPost请求对象。
     */
    public HttpPost createHttpPost(URI uri) {
        return new HttpPost(uri);
    }

    /**
     * 创建HttpDelete请求对象。
     *
     * @param url 请求的URL。
     * @return HttpDelete请求对象。
     */
    public HttpDelete createHttpDelete(String url) {
        return new HttpDelete(url);
    }

    /**
     * 创建HttpDelete请求对象。
     *
     * @param uri URI请求对象，带有请求协议、地址、端口、URL、参数。通过HttpComponentsClientKit类的createURI方法创建。
     * @return HttpDelete请求对象。
     */
    public HttpDelete createHttpDelete(URI uri) {
        return new HttpDelete(uri);
    }

    /**
     * 创建HttpPut请求对象。
     *
     * @param url 请求的URL。
     * @return HttpPut请求对象。
     */
    public HttpPut createHttpPut(String url) {
        return new HttpPut(url);
    }

    /**
     * 创建HttpPut请求对象。
     *
     * @param url 请求的URL。
     * @param data 请求数据
     * @return HttpPut请求对象。
     */
    public HttpPut createHttpPut(String url, String data) {
        HttpPut httpPut = new HttpPut(url);
        httpPut.setEntity(new StringEntity(data, StandardCharsets.UTF_8));
        return httpPut;
    }

    /**
     * 创建HttpPut请求对象。
     *
     * @param uri URI请求对象，带有请求协议、地址、端口、URL、参数。通过HttpComponentsClientKit类的createURI方法创建。
     * @return HttpPut请求对象。
     */
    public HttpPut createHttpPut(URI uri) {
        return new HttpPut(uri);
    }

    /**
     * 设置Http请求头，Get、Post请求通用设置。
     *
     * @param httpRequestBase Http请求类型基础对象，兼容HttpGet和HttpPost请求对象。
     * @param headers         待设置的Header信息。
     */
    public void setHeader(HttpRequestBase httpRequestBase, Map<String, String> headers) {
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            httpRequestBase.setHeader(entry.getKey(), entry.getValue());
        }
    }

    /**
     * 执行Http请求
     *
     * @param httpRequestBase Http请求类型基础对象，兼容HttpGet和HttpPost请求对象。
     * @return CloseableHttpResponse响应对象
     */
    public CloseableHttpResponse execute(HttpRequestBase httpRequestBase) {
        CloseableHttpClient client = this.creatHttpClient();
        CloseableHttpResponse response;
        try {
            response = client.execute(httpRequestBase);
        } catch (IOException e) {
            throw new HttpComponentsException("HttpGet请求异常！", e);
        }
        return response;
    }

    /**
     * 简洁HttpGet请求处理
     *
     * @param url 请求的URL地址
     * @return HttpGet请求响应报文
     */
    public String doGet(String url) {
        String responseContent;
        HttpGet httpGet = this.createHttpGet(url);
        CloseableHttpResponse closeableHttpResponse = this.execute(httpGet);
        HttpEntity httpEntity = closeableHttpResponse.getEntity();
        try {
            responseContent = EntityUtils.toString(httpEntity, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new HttpComponentsException("执行Http的Get请求，响应结果转换字符串异常！", e);
        }
        return responseContent;
    }

    public String doPost(String url) {
        String responseContent;
        HttpPost httpPost = this.createHttpPost(url);
        CloseableHttpResponse closeableHttpResponse = this.execute(httpPost);
        HttpEntity httpEntity = closeableHttpResponse.getEntity();
        try {
            responseContent = EntityUtils.toString(httpEntity, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new HttpComponentsException("执行Http的Post请求，响应结果转换字符串异常！", e);
        }
        return responseContent;
    }

    /**
     * 关闭Http客户端。
     *
     * @param client 待关闭的Http客户端对象。
     */
    public void close(CloseableHttpClient client) {
        if (client != null) {
            try {
                client.close();
            } catch (IOException e) {
                throw new HttpComponentsException("Http客户端，关闭异常！", e);
            }
        }
    }

    /**
     * 关闭HttpResponse。
     *
     * @param response 待关闭的HttpResponse对象。
     */
    public void close(CloseableHttpResponse response) {
        if (response != null) {
            try {
                response.close();
            } catch (IOException e) {
                throw new HttpComponentsException("HttpResponse对象，关闭异常！", e);
            }
        }
    }
}
