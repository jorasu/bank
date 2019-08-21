package com.kgc.utils;

/**
 * Created by jora on 2019/5/27.
 */

public class Page {
    private Integer pageNo;
    private Integer pageSize;
    private Integer totalCount;
    private Integer totalPageCount;
    private Integer startRow;

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

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(Integer pageSize,Integer totalCount) {
        this.totalPageCount = totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1;
    }

    public Integer getStartRow() {
        return startRow;
    }

    public void setStartRow(Integer pageNo,Integer pageSize) {
        this.startRow = (pageNo-1)*pageSize;
    }

    public Page(Integer pageNo, Integer pageSize, Integer totalCount) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.setTotalPageCount(pageSize,totalCount);
        this.setStartRow(pageNo,pageSize);
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", totalCount=" + totalCount +
                ", totalPageCount=" + totalPageCount +
                ", startRow=" + startRow +
                '}';
    }
}
