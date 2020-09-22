package com.kun.sc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// @ApiModel("RPA -> 数采客户端, 数采客户端 -> 数采服务端  响应结构")

/**
 * 1.基本响应结果
 * 2.入库
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScResp {

    // @ApiModelProperty("返回结果： 2-正确， 非2是异常")
    private String retCode;

    // @ApiModelProperty("返回结果字符串)
    private String retStr;

    /********************************************************/

    private String tableName;

    private String searchWord;

    private String recordType;

    private Integer recordCount;

    private String recordContent;

}
