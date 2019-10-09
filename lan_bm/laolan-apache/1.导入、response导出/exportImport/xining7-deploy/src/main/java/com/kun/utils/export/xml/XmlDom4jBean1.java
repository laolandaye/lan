package com.kun.utils.export.xml;

import java.util.List;
import java.util.Map;

public class XmlDom4jBean1 {

    private String fileName;
    private List<Map<String, Object>> heads;//头数据
    private List<Map<String, Object>> centents;//内容数据

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<Map<String, Object>> getHeads() {
        return heads;
    }

    public void setHeads(List<Map<String, Object>> heads) {
        this.heads = heads;
    }

    public List<Map<String, Object>> getCentents() {
        return centents;
    }

    public void setCentents(List<Map<String, Object>> centents) {
        this.centents = centents;
    }
}
