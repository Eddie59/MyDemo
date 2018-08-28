package com.em.mybatisgj.model;

/**
 * PageModel class
 *
 * @author Administrator
 * @date
 */
public class PageModel {
    private int pageNum;
    private int pageSize;

    public int getPageNum() {
        return pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
