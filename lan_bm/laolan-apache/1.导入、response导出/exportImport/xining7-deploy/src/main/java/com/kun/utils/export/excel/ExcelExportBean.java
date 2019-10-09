package com.kun.utils.export.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.List;

/**
 * Excel 导出bean
 */
public class ExcelExportBean {
    List<?> list; //获取数据
    String [][] content;//转换成的2维数组
    String[] title;//excel标题
    String fileName = ""; //excel文件名
    String sheetName = ""; //sheet名

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }

    public String[] getTitle() {
        return title;
    }

    public void setTitle(String[] title) {
        this.title = title;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }


    public ExcelExportBean() {
    }

    public String[][] getContent() {
        return content;
    }

    public void setContent(String[][] content) {
        this.content = content;
    }
}
