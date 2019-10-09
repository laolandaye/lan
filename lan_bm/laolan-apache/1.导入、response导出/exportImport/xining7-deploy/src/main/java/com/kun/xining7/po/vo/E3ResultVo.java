package com.kun.xining7.po.vo;

/**
 * Created by lan_jiaxing on 2018/7/13 0013.
 */
public class E3ResultVo {

    private Integer errno = 1;
    private String [] data;

    public Integer getErrno() {
        return errno;
    }

    public void setErrno(Integer errno) {
        this.errno = errno;
    }

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }
}
