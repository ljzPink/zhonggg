package com.zhonggg.spider.controller;

import com.zhonggg.commonUtils.Result;
import com.zhonggg.commonUtils.ResultUtil;
import com.zhonggg.spider.service.SpiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: lijinzhong
 * @create: 2020-03-05 14:15
 */
@RestController
@RequestMapping("/spider")
public class SpiderController {

    @Autowired
    private SpiderService spiderService;

    @PostMapping(value = "/poem")
    public Result queryFocusPerson() {
        spiderService.spiderPoem();
        return ResultUtil.success();
    }
    @PostMapping(value = "/chnDic")
    public Result chnDic() {
        spiderService.chnDic();
        return ResultUtil.success();
    }

    @PostMapping(value = "/news")
    public Result news() {
        spiderService.news();
        return ResultUtil.success();
    }
    @PostMapping(value = "/music")
    public Result music() {
        spiderService.music();
        return ResultUtil.success();
    }
}
