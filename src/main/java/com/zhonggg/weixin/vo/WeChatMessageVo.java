package com.zhonggg.weixin.vo;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @description:
 * @author: lijinzhong
 * @create: 2020-04-18 09:26
 */
@XmlRootElement(name="xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class WeChatMessageVo {
    // 发送方的账号
    protected String FromUserName;
    // 接收方的账号(OpenID)
    protected String ToUserName;
    // 消息创建时间
    protected Long CreateTime;
    /**
     * 消息类型
     * text 文本消息
     * image 图片消息
     * voice 语音消息
     * video 视频消息
     * music 音乐消息
     * news 图文消息
     */
    protected String MsgType;
    // 语音
    @XmlElementWrapper(name="Voice")
    private String[] MediaId ;
    // 文本内容
    private String Content;

    private int ArticleCount;
    //图文消息
    @XmlElementWrapper(name="Articles")
    private List<NewItems> item;

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public Long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Long createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public String[] getMediaId() {
        return MediaId;
    }

    public void setMediaId(String[] mediaId) {
        MediaId = mediaId;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public int getArticleCount() {
        return ArticleCount;
    }

    public void setArticleCount(int articleCount) {
        ArticleCount = articleCount;
    }

    public List<NewItems> getItem() {
        return item;
    }

    public void setItem(List<NewItems> item) {
        this.item = item;
    }
}
