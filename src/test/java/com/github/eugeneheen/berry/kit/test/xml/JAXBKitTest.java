package com.github.eugeneheen.berry.kit.test.xml;

import com.github.eugeneheen.berry.kit.test.pojo.xml.PassivityTextMessage;
import com.github.eugeneheen.berry.kit.xml.JAXBKit;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 描述类的功能
 *
 * @author Eugene
 * 2018-10-10 13:36
 */
public class JAXBKitTest {
    private static String xmlStr;

    @BeforeClass
    public static void beforeClass() {

        StringBuffer xmlStrBuffer = new StringBuffer();
        xmlStrBuffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>").append("\n")
                .append("<xml>").append("\n")
                .append("    <ToUserName>xxUfw64cytu</ToUserName>").append("\n")
                .append("    <FromUserName>Eugene</FromUserName>").append("\n")
                .append("    <CreateTime>1539133827535</CreateTime>").append("\n")
                .append("    <MsgType>text</MsgType>").append("\n")
                .append("    <Content>xxxxxxx,您好！</Content>").append("\n")
                .append("</xml>");
        JAXBKitTest.xmlStr = xmlStrBuffer.toString();
    }

    @Test
    public void testObjectToXml() {
        PassivityTextMessage passivityTextMessage = new PassivityTextMessage();
        passivityTextMessage.setToUserName("xxUfw64cytu");
        passivityTextMessage.setFromUserName("Eugene");
        passivityTextMessage.setCreateTime(1539133827535L);
        passivityTextMessage.setMsgType("text");
        passivityTextMessage.setContent("xxxxxxx,您好！");

        JAXBKit jaxbKit = new JAXBKit();
        String xmlStr = jaxbKit.toXml(passivityTextMessage);
        Assert.assertNotNull(xmlStr);
    }

    @Test
    public void testXmlToObject() {
        JAXBKit jaxbKit = new JAXBKit();
        PassivityTextMessage passivityTextMessage = jaxbKit.toBean(JAXBKitTest.xmlStr, PassivityTextMessage.class);
        Long createTime = 1539133827535L;
        Assert.assertEquals("xxUfw64cytu", passivityTextMessage.getToUserName());
        Assert.assertEquals("Eugene", passivityTextMessage.getFromUserName());
        Assert.assertEquals(createTime, passivityTextMessage.getCreateTime());
        Assert.assertEquals("text", passivityTextMessage.getMsgType());
        Assert.assertEquals("xxxxxxx,您好！", passivityTextMessage.getContent());
    }
}
