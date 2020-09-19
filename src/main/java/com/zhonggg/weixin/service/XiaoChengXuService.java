package com.zhonggg.weixin.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhonggg.common.vo.PageVO;
import com.zhonggg.weixin.dao.XiaoChengXuDao;
import com.zhonggg.weixin.dto.NewDTO;
import com.zhonggg.weixin.dto.PoemDTO;
import com.zhonggg.weixin.dto.SongDTO;
import com.zhonggg.weixin.vo.NewsVO;
import com.zhonggg.weixin.vo.PoemVO;
import com.zhonggg.weixin.vo.SongVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: lijinzhong
 * @create: 2020-03-19 11:25
 */
@Service
public class XiaoChengXuService {
    @Autowired
    private XiaoChengXuDao xiaoChengXuDao;
    public PageVO<PoemVO> getPoemList(PoemDTO poemDTO) {
        PageInfo<PoemVO> pageInfo = PageHelper.startPage(poemDTO.getPageNo(), poemDTO.getPageSize()).doSelectPageInfo(() -> xiaoChengXuDao.getPoemList(poemDTO));
        return new PageVO<PoemVO>(pageInfo);

    }

    public PoemVO getPoemById(Integer id) {
        return xiaoChengXuDao.getPoemById(id);
    }

    public PageVO<NewsVO> getNews(NewDTO newDTO) {
        PageVO<NewsVO> pageVO = new PageVO(newDTO);
        pageVO.setData(xiaoChengXuDao.getNews(newDTO));

        return pageVO;

    }

    public PageVO<SongVO> getSongs(SongDTO songDTO) {
        List<SongVO> songs = xiaoChengXuDao.getSongs(songDTO);
        PageInfo<SongVO> pageInfo = PageHelper.startPage(songDTO.getPageNo(), songDTO.getPageSize()).doSelectPageInfo(() -> xiaoChengXuDao.getSongs(songDTO));
        return new PageVO<SongVO>(pageInfo);
    }
}
