package com.zhonggg.spider.controller;

import com.zhonggg.commonUtils.Result;
import com.zhonggg.commonUtils.ResultUtil;
import com.zhonggg.spider.service.SpiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: lijinzhong
 * @create: 2020-03-05 14:15
 */
@RestController("/spider")
public class SpiderController {

    @Autowired
    private SpiderService spiderService;

    @PostMapping(value = "/poem")
    public Result queryFocusPerson() {
        spiderService.spiderPoem();
        return ResultUtil.success();
    }
}
