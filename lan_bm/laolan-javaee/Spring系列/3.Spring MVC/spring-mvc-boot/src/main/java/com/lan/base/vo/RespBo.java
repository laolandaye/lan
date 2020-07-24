package com.lan.base.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class RespBo {

    @NotBlank(message = "tabId 不为空, 必填")
    private String tabId;

    @NotBlank(message = "colId 不为空, 必填")
    private String colId;

    @NotBlank(message = "colName 不为空, 必填")
    private String colName;

    private String colAlias;

    @NotBlank(message = "colCnName 不为空, 必填")
    private String colCnName;

    private String remark;

    public RespBo(String colName, String colCnName, String remark) {
        this.colName = colName;
        this.colCnName = colCnName;
        this.remark = remark;
    }

}

