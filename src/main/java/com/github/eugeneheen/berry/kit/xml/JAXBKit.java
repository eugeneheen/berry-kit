package com.github.eugeneheen.berry.kit.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * JAXB工具类
 *
 * @author Eugene
 * 2018-10-10 13:33
 */
public class JAXBKit {
    /**
     * 对象转换为XML格式，默认编码UTF-8
     * @param source 待转换的对象
     * @return XML格式字符串
     */
    public String toXml(Object source) {
        return this.toXml(source, StandardCharsets.UTF_8);
    }

    /**
     * 对象转换为XML格式字符串，需明确指定编码
     * @param source 待转换的对象
     * @param charset 字符编码
     * @return XML格式字符串
     */
    public String toXml(Object source, Charset charset) {
        String xmlStr = null;

        try (StringWriter writer = new StringWriter()) {
            JAXBContext context = JAXBContext.newInstance(source.getClass());
            Marshaller marshaller = context.createMarshaller();
            // 决定是否在转换成xml时同时进行格式化(即按标签自动换行，否则即是一行的xml)
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            // XML的编码方式
            marshaller.setProperty(Marshaller.JAXB_ENCODING, charset.toString());
            marshaller.marshal(source, writer);
            xmlStr = writer.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return xmlStr;
    }

    /**
     * XML格式字符串转换为对象
     * @param xml 待转换的XML字符串
     * @param clazz 最终转换的对象类型
     * @param <T> 泛型T
     * @return 指定的clazz对象类型
     */
    public <T> T toBean(String xml, Class<T> clazz) {
        T type = null;

        try (StringReader stringReader = new StringReader(xml)) {
            JAXBContext context = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            type = (T) unmarshaller.unmarshal(stringReader);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return type;
    }
}
