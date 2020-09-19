package com.zhonggg.weixin.controller;

import com.zhonggg.common.vo.PageVO;
import com.zhonggg.commonUtils.Result;
import com.zhonggg.commonUtils.ResultUtil;
import com.zhonggg.weixin.dto.NewDTO;
import com.zhonggg.weixin.dto.PoemDTO;
import com.zhonggg.weixin.dto.SongDTO;
import com.zhonggg.weixin.service.XiaoChengXuService;
import com.zhonggg.weixin.validate.XiaoChengXuValidate;
import com.zhonggg.weixin.vo.NewsVO;
import com.zhonggg.weixin.vo.PoemVO;
import com.zhonggg.weixin.vo.SongVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: lijinzhong
 * @create: 2020-03-06 14:31
 */
@RestController()
@RequestMapping("/xiaochengxu")
public class XiaoChengXuController {
    @Autowired
    private  XiaoChengXuService xiaoChengXuService;
    @PostMapping("/getPoemList")
    public Result getPoemList(@RequestBody PoemDTO poemDTO){
        PageVO<PoemVO> poem = xiaoChengXuService.getPoemList(poemDTO);
        return ResultUtil.success(poem);
    }
    @PostMapping("/getPoemById")
    public Result getPoemById(@RequestBody PoemDTO poemDTO){
        PoemVO  poem = xiaoChengXuService.getPoemById(poemDTO.getId());
        return ResultUtil.success(poem);
    }

    @PostMapping("/getNews")
    public Result getNews(@RequestBody NewDTO newDTO){
        XiaoChengXuValidate.getNewsValidate(newDTO);
        PageVO<NewsVO> news = xiaoChengXuService.getNews(newDTO);
        return ResultUtil.success(news);
    }
    @PostMapping("/getSongs")
    public Result getSongs(@RequestBody SongDTO songDTO){
        PageVO<SongVO> songs = xiaoChengXuService.getSongs(songDTO);
        return ResultUtil.success(songs);
    }
}