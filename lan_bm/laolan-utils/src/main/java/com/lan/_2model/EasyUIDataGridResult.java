package com.lan._2model;

import java.io.Serializable;
import java.util.List;

/**
 * 用于接受easyUI 分页表格数据，也可以用map代替
 */
public class EasyUIDataGridResult implements Serializable{
    private Long total;

    private List<?> rows;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }
}
