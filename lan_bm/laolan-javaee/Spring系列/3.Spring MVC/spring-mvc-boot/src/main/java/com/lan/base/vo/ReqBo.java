package com.lan.base.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class ReqBo {

    @NotBlank(message = "tabId 不为空, 必填")
    private String tabId;

    @NotBlank(message = "colId 不为空, 必填")
    private String colId;

    @NotBlank(message = "colName 不为空, 必填")
    private String colName;

    private String colCnName;

    @NotBlank(message = "colCon 不为空, 必填")
    private String colCon;  // 拼接的查询条件

    private String colDefaultVal;

    public ReqBo(String tabId, String colName, String colCon, String colDefaultVal) {
        this.tabId = tabId;
        this.colName = colName;
        this.colCon = colCon;
        this.colDefaultVal = colDefaultVal;
    }

}
