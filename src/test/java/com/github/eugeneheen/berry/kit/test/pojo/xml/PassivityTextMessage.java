package com.github.eugeneheen.berry.kit.test.pojo.xml;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 被动文本消息
 *
 * @author Eugene
 * 2018-10-09 17:22
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "xml")
public class PassivityTextMessage extends PassivityMessage {

    /**
     * 回复的消息内容
     */
    @XmlElement(name = "Content")
    @Getter
    @Setter
    public String content;
}
