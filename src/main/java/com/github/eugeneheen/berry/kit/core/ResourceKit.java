package com.github.eugeneheen.berry.kit.core;

import java.io.InputStream;
import java.net.URL;

/**
 * 资源工具箱
 *
 * @author Eugene
 */
public class ResourceKit {

    private ResourceKit() {
    }

    /**
     * 从类路径下加载资源文件
     *
     * @param classpath 基于类路径的文件路径
     * @return InputStream对象
     */
    public static InputStream getResourceAsStream(String classpath) {
        InputStream in = null;
        if (!classpath.startsWith("/")) {
            classpath = "/" + classpath;
        }

        in = Thread.currentThread().getContextClassLoader().getResourceAsStream(classpath);
        if (in == null) {
            in = ResourceKit.class.getClassLoader().getResourceAsStream(classpath);
        }

        if (in == null) {
            in = ResourceKit.class.getResourceAsStream(classpath);
        }

        return in;
    }

    /**
     * 从类路径下加载资源文件
     *
     * @param classpath 基于类路径的文件路径
     * @return URL对象
     */
    public static URL getResource(String classpath) {
        URL url = null;
        if (!classpath.startsWith("/")) {
            classpath = "/" + classpath;
        }

        url = Thread.currentThread().getContextClassLoader().getResource(classpath);
        if (url == null) {
            url = ResourceKit.class.getClassLoader().getResource(classpath);
        }

        if (url == null) {
            url = ResourceKit.class.getResource(classpath);
        }

        return url;
    }
}
