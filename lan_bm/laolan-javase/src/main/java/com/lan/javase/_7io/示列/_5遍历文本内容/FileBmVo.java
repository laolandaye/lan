package com.lan.javase._7io.示列._5遍历文本内容;

import java.util.List;

public class FileBmVo {

    private String prename; // 文件名字, 前部分

    private String name; // 文件名字
    private String folder; // 文件夹
    private String fileName; // 文件夹 + 文件名字
    private String copyFileName; // (临时复制)文件夹 + 文件名字

    List<FileBmPo> fileBmPosCss;
    List<FileBmPo> fileBmPosJs;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getCopyFileName() {
        return copyFileName;
    }

    public void setCopyFileName(String copyFileName) {
        this.copyFileName = copyFileName;
    }

    public List<FileBmPo> getFileBmPosCss() {
        return fileBmPosCss;
    }

    public void setFileBmPosCss(List<FileBmPo> fileBmPosCss) {
        this.fileBmPosCss = fileBmPosCss;
    }

    public List<FileBmPo> getFileBmPosJs() {
        return fileBmPosJs;
    }

    public void setFileBmPosJs(List<FileBmPo> fileBmPosJs) {
        this.fileBmPosJs = fileBmPosJs;
    }

    static class FileBmPo {
        private String type;    // .css 或者 .js
        private String name;
        private String folder;
        private String fileName;
        private String fileLinkScript;
        private Boolean flag=false;  // 是否使用（true） 代表已使用

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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

        public String getFileLinkScript() {
            return fileLinkScript;
        }

        public void setFileLinkScript(String fileLinkScript) {
            this.fileLinkScript = fileLinkScript;
        }

        public Boolean getFlag() {
            return flag;
        }

        public void setFlag(Boolean flag) {
            this.flag = flag;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
