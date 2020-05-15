package com.zhonggg.config;

import com.zhonggg.spider.service.SpiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * 定时任务配置
 *
 * @author wangxiaolong
 * @date 2019/8/22
 */
@EnableScheduling // 开启定时任务
@Configuration
public class TimingTaskConfig {

    @Autowired
    private SpiderService spiderService;


    /**
     * 每天凌晨8点执行一次
     *
     * @author xuqi
     * @date 2019-08-21 15:33:08
     */
    @Scheduled(cron = "0 0 8 * * *")
    private void clearFile() {
        //Log
        spiderService.news();
    }


}
