package com.zhonggg.weixin.dto;

import com.zhonggg.commonUtils.dto.PageDTO;

/**
 * @description:
 * @author: lijinzhong
 * @create: 2020-04-22 09:38
 */
public class SongDTO extends PageDTO {
    private  Long id ;
    private Long songId;
    private String name;
    private String author;
    private String copyright;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSongId() {
        return songId;
    }

    public void setSongId(Long songId) {
        this.songId = songId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }
}
