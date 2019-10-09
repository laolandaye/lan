package com.kun.utils;

import java.util.List;

/*
 * 分页器
 * */
public class PageBean<T> {
    private List<T> dataModel;
    private int totalPage;
    private int currentPage;
    private int pageSize;
    private int total;

    public PageBean(List<T> dataModel, int totalPage, int currentPage, int pageSize, int total) {
        super();
        this.dataModel = dataModel;
        this.totalPage = totalPage;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.total = total;
    }

    public List<T> getDataModel() {
        return dataModel;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getTotal() {
        return total;
    }

    public void setDataModel(List<T> dataModel) {
        this.dataModel = dataModel;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
