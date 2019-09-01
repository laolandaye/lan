package com.lan._2model.page;

/**
 * 前端xxx：拼音 后台xxx：英文单词
 * 为xxx表单的接受对象
 * js:
 * 条件：
 *    xxxForm {
 *        ...
 *        dataType: 'year',
 *        dataValue: '2018',
 *        dataFormat: 'yyyy',
 *        currentPage  : 1,
 *        pageSize(可不写，默认10)
 *    }
 *    xxxPage = 后台xxxPageVo
 */
public class XxxPageVo extends PageBo {

    private String dataType;    //year、month、week或者date hour
    private String dataValue;   //日期格式，一般都是“-”格式
    private String dataFormat;  // 前端的，后台不一定需要
}
