package com.github.eugeneheen.berry.kit.net;

import com.github.eugeneheen.berry.kit.exception.HttpResponseException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * <p>
 * HttpResponse响应处理工具箱。提供了响应对象相关的常用工具集方法，
 * 获取响应报文中的响应状态、响应正文等方法，以此提供快捷的Http响应内容解析方法。
 * </p>
 * Created by Eugene on 2016/10/25.
 */
public class HttpResponseKit {

    /**
     * 获取HttpResponse响应内容对象，该对象包含全部响应内容。
     *
     * @param httpResponse HttpResponse响应对象
     * @return Http响应内容对象
     */
    public HttpEntity getHttpEntity(HttpResponse httpResponse) {
        return httpResponse.getEntity();
    }

    /**
     * 获取HttpResponse对象的响应正文的流对象。
     *
     * @param httpResponse HttpResponse响应对象。
     * @return InputStream响应正文对象。
     */
    public InputStream getInputStreamContent(HttpResponse httpResponse) {
        InputStream in = null;
        try {
            in = this.getHttpEntity(httpResponse).getContent();
        } catch (IOException e) {
            throw new HttpResponseException("获取输入流响应正文异常！", e);
        }
        return in;
    }

    /**
     * 获取HttpResponse对象的字节数组响应正文。
     *
     * @param httpResponse HttpResponse响应对象。
     * @return 字节数组响应正文。
     */
    public byte[] getBytesContent(HttpResponse httpResponse) {
        byte[] bytes = null;
        try {
            bytes = EntityUtils.toByteArray(this.getHttpEntity(httpResponse));
        } catch (IOException e) {
            throw new HttpResponseException("获取字节数组响应正文异常！", e);
        }
        return bytes;
    }

    /**
     * 获取HttpResponse对象的字符串响应正文。
     *
     * @param httpResponse HttpResponse响应对象。
     * @param charset 指定字符编码
     * @return 字符串响应正文。
     */
    public String getStringContent(HttpResponse httpResponse, Charset charset) {
        String str = null;
        try {
            str = EntityUtils.toString(this.getHttpEntity(httpResponse), charset);
        } catch (IOException e) {
            throw new HttpResponseException("获取字符串响应正文异常！", e);
        }
        return str;
    }

    /**
     * 关闭HttpEntity对象操作相关的流。如果通过HttpResponseKit的getInputStreamContent方法获取流，然后手动控制流关闭，无需使用本方法。
     *
     * @param httpEntity HttpEntity对象。
     */
    public void consumeEntity(HttpEntity httpEntity) {
        try {
            EntityUtils.consume(httpEntity);
        } catch (IOException e) {
            throw new HttpResponseException("HttpEntity关闭流操作异常！", e);
        }
    }

}
