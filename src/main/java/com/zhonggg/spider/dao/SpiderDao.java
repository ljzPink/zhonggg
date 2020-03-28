package com.zhonggg.spider.dao;

import com.zhonggg.spider.model.NewsInfo;
import com.zhonggg.spider.model.Poem;
import org.apache.ibatis.annotations.Param;

import java.util.List;
public interface SpiderDao {
    void addPoemBatch(@Param("poems") List<Poem> poems);

    void addNewsInfoBatch(@Param("news") List<NewsInfo> news);
}
