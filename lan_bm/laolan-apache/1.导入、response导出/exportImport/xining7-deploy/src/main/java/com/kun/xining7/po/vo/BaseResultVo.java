package com.kun.xining7.po.vo;

import com.kun.utils.PageBean;
import org.springframework.data.domain.Page;

import java.io.Serializable;

public class BaseResultVo implements Serializable {

    private String code = "0";//0成功
    private String message = "成功";//返回的信息，目前是在前端写
    private Object data;
    private PageBean<?> pageBean;//pageBean分页
    private Page<?> page;//jpa分页

    public BaseResultVo() {
    }

    public void setCodeMessage(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public BaseResultVo(Object data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public PageBean<?> getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean<?> pageBean) {
        this.pageBean = pageBean;
    }

    public Page<?> getPage() {
        return page;
    }

    public void setPage(Page<?> page) {
        this.page = page;
    }
}


