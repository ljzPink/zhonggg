package com.zhonggg.commonUtils.dto;

/**
 * @description:
 * @author: lijinzhong
 * @create: 2020-03-19 11:18
 */
public class PageDTO {
    private Integer pageNo = 1;
    private Integer pageSize = 1000000;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
