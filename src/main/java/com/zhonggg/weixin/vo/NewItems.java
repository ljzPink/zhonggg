package com.zhonggg.weixin.vo;

import lombok.Data;

/**
 * @description:
 * @author: lijinzhong
 * @create: 2020-04-18 09:31
 */
public class NewItems {
    private String title;
    private String url;
    private String descriptiontle;
    private String picUrl;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescriptiontle() {
        return descriptiontle;
    }

    public void setDescriptiontle(String descriptiontle) {
        this.descriptiontle = descriptiontle;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
}
