package com.github.eugeneheen.berry.kit.test.pojo.xml;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;

/**
 * 被动回复消息基类，是一个抽象类，自身不允许被实例化
 *
 * @author Eugene
 * 2018-10-09 17:16
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement
@XmlSeeAlso({PassivityTextMessage.class})
public abstract class PassivityMessage {

    /**
     * 接收方帐号（一个OpenID）
     */
    @XmlElement(name = "ToUserName")
    @Getter
    @Setter
    public String toUserName;

    /**
     * 开发者微信号
     */
    @XmlElement(name = "FromUserName")
    @Getter
    @Setter
    public String fromUserName;

    /**
     * 消息创建时间 （整型）
     */
    @XmlElement(name = "CreateTime")
    @Getter
    @Setter
    public Long createTime;

    /**
     * 消息类型，
     */
    @XmlElement(name = "MsgType")
    @Getter
    @Setter
    public String msgType;
}
