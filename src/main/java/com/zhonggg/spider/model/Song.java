package com.zhonggg.spider.model;

/**
 * @description:
 * @author: lijinzhong
 * @create: 2020-04-21 17:13
 */
public class Song {
    private  Long id ;
    private Long songId;
    private String name;
    private String url;
    private String pic;
    private String author;
    private String lrcData;
    private String lrc_data;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLrcData() {
        return lrcData;
    }

    public void setLrcData(String lrcData) {
        this.lrcData = lrcData;
    }

    public String getLrc_data() {
        return lrc_data;
    }

    public void setLrc_data(String lrc_data) {
        this.lrc_data = lrc_data;
        this.lrcData = lrc_data;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }
}
