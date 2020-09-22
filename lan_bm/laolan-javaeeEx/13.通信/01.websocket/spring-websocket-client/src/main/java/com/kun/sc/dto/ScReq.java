package com.kun.sc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// @ApiModel("数采服务端 -> 数采客户端, 数采客户端 -> RPA 请求的数据结构")

/**
 * 1.基本请求信息
 * 2.sessionId
 * 3.rpa请求条件
 * 4.rpa基础信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScReq {

    // @ApiModelProperty("返回结果： 2-正确， 非2是异常")
    private String retCode;

    // @ApiModelProperty("返回结果字符串)
    private String retStr;

    /*****************************************************************/

    private String sessionId;

    /*****************************************************************/

    // @ApiModelProperty("Hash(from+userid+keyid+key+starttime+endtime+发送时间timestamp)-消息唯一值")
    private String serialno;

    /*@ApiModelProperty("对端类型" +
            "  1-部云搜" +
            "2-市智搜" +
            "3-市刑专" +
            "4-部目标" +
            "5-市情报平台") */
    private Integer from;

    //@ApiModelProperty("非0值，指向终端用户ID指向")
    private Integer userid;

    /*@ApiModelProperty("查询检索字段类型ID" +
            "1-身份证" +
            "2-手机号" +
            "3-微信账号" +
            "4-支付宝账号")*/
    private Integer keyid;

    //@ApiModelProperty("检索字段的值")
    private String key;

    //@ApiModelProperty("获取数据的大类ID，根据业务情况定义,TBD")
    private Integer categoryid;

    //@ApiModelProperty("获取数据的小磊ID,根据业务情况定义,TBD")
    private Integer subcategoryid;

    //@ApiModelProperty("查询时间段的开始时间")
    private String starttime;

    //@ApiModelProperty("查询时间段的结束时间")
    private String endtime;


    /********************** 为rpa基础信息 *************************/
    private String rpaUri;

    private String rpaType;

    private String versionNo;
    /***********************************************/
}
