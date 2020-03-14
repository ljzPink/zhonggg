package com.zhonggg.spider.model;

/**
 * @description:
 * @author: lijinzhong
 * @create: 2020-03-05 14:40
 */
public class Poem {
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

    @Override
    public String toString() {
        return "Poem{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", dynasty='" + dynasty + '\'' +
                ", author='" + author + '\'' +
                ", content='" + content + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
