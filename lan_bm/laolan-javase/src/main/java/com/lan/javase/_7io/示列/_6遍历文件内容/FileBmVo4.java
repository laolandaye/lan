package com.lan.javase._7io.示列._6遍历文件内容;

import java.util.List;

public class FileBmVo4 {

    private String prename; // 文件名字, 前部分

    private String path; // 文件名字, 前部分
    private String folder; // 文件夹
    private String fileName; // 文件夹 + 文件名字
    private String fileNameMainJs; // 文件夹 + 文件名字

    private String fileNameFtl;
    private String pathFtl;

    public String getPathFtl() {
        return pathFtl;
    }

    public void setPathFtl(String pathFtl) {
        this.pathFtl = pathFtl;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getPrename() {
        return prename;
    }

    public void setPrename(String prename) {
        this.prename = prename;
    }

    public String getFileNameFtl() {
        return fileNameFtl;
    }

    public void setFileNameFtl(String fileNameFtl) {
        this.fileNameFtl = fileNameFtl;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFileNameMainJs() {
        return fileNameMainJs;
    }

    public void setFileNameMainJs(String fileNameMainJs) {
        this.fileNameMainJs = fileNameMainJs;
    }
}
