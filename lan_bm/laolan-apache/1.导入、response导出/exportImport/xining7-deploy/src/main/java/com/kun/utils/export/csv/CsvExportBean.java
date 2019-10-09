package com.kun.utils.export.csv;

import java.util.LinkedHashMap;
import java.util.List;

public class CsvExportBean {
    
    private String fileName;//文件名
    private LinkedHashMap<String, String> heads;//头标题
    private List<LinkedHashMap<String, String>> contents;//行数据

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public LinkedHashMap<String, String> getHeads() {
        return heads;
    }

    public void setHeads(LinkedHashMap<String, String> heads) {
        this.heads = heads;
    }

    public List<LinkedHashMap<String, String>> getContents() {
        return contents;
    }

    public void setContents(List<LinkedHashMap<String, String>> contents) {
        this.contents = contents;
    }
}
