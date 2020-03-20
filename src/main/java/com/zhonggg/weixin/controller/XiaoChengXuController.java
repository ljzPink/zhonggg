package com.zhonggg.weixin.controller;

import com.zhonggg.commonUtils.Result;
import com.zhonggg.commonUtils.ResultUtil;
import com.zhonggg.commonUtils.vo.PageVO;
import com.zhonggg.weixin.dto.PoemDTO;
import com.zhonggg.weixin.service.XiaoChengXuService;
import com.zhonggg.weixin.vo.PoemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Result getPoemById(Integer id){
        PoemVO  poem = xiaoChengXuService.getPoemById(id);
        return ResultUtil.success(poem);
    }
}