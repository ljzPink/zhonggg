package com.zhonggg.common.vo;

import com.github.pagehelper.PageInfo;
import com.zhonggg.common.dto.PageDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: lijinzhong
 * @create: 2020-03-19 11:18
 */
public class PageVO <T>{
    private Integer pageNo;
    private Integer pageSize;
    private Integer pages;
    private List data = new ArrayList<T>();
    public PageVO() {
    }
    public PageVO(PageInfo<T> pageInfo) {
        setPageInfo(pageInfo);
    }
    public void setPageInfo(PageInfo<T> pageInfo) {
        this.pageNo = pageInfo.getPageNum();
        this.pageSize = pageInfo.getPageSize();
        this.pages = pageInfo.getPages();
        this.data = pageInfo.getList();
    }
    public PageVO(PageDTO pageDTO) {
        this.pageNo = pageDTO.getPageNo();
        this.pageSize = pageDTO.getPageSize();
    }
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

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }
}

