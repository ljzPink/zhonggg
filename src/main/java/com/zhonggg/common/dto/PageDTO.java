package com.zhonggg.common.dto;

/**
 * @description:
 * @author: lijinzhong
 * @create: 2020-03-19 11:18
 */
public class PageDTO {
    private Integer pageNo = 1;
    private Integer pageSize = 1000000;
    private Integer startIndex = 0;

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

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }
}
