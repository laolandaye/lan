package com.kun.utils.export.json;

public class JsonExportBean {

    private String fileName; //文件名
    private String jsonString; //未格式化的json数据

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getJsonString() {
        return jsonString;
    }

    public void setJsonString(String jsonString) {
        this.jsonString = jsonString;
    }
}
