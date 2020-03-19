package com.zhonggg.weixin.dto;

import com.zhonggg.commonUtils.dto.PageDTO;

/**
 * @description:
 * @author: lijinzhong
 * @create: 2020-03-19 11:23
 */
public class PoemDTO extends PageDTO {
    private  int id;
    private String title;
    private String dynasty;
    private String author;
    private String content;
    private String category;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDynasty() {
        return dynasty;
    }

    public void setDynasty(String dynasty) {
        this.dynasty = dynasty;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
