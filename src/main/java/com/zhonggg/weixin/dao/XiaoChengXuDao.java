package com.zhonggg.weixin.dao;

import com.zhonggg.weixin.dto.NewDTO;
import com.zhonggg.weixin.dto.PoemDTO;
import com.zhonggg.weixin.dto.SongDTO;
import com.zhonggg.weixin.vo.NewsVO;
import com.zhonggg.weixin.vo.PoemVO;
import com.zhonggg.weixin.vo.SongVO;

import java.util.List;

public interface XiaoChengXuDao {
    List<PoemVO> getPoemList(PoemDTO poemDTO);

    PoemVO getPoemById(Integer id);

    List<NewsVO> getNews(NewDTO newDTO);

    List<SongVO> getSongs(SongDTO songDTO);
}
