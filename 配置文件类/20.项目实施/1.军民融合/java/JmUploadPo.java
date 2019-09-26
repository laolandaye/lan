package com.kun.jmrh.entity.po;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name = "jm_upload")
public class JmUploadPo  implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id", strategy = "uuid")
    @Column(name = "id" ,length = 40 ,nullable = false)
    private String id ;					// 新闻id

    @Column(name = "file_path",length = 100)
    private String filePath  ;

    @Column(name = "file_name" ,length = 100)
    private String fileName ;

    @Column(name = "file_md5" ,length = 100)
    private String fileMd5 ;

    @Column(name = "file_content_type",length = 100)
    private String fileContentType ;				// 新闻标题

    @Column(name = "file_size",length = 100)
    private String fileSize ;				// 新闻标题

    @Column(name = "file_path_real",length = 100)
    private String filePathReal  ;

    @Column(name = "file_suffix",length = 100)
    private String fileSuffix  ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePathReal() {
        return filePathReal;
    }

    public void setFilePathReal(String filePathReal) {
        this.filePathReal = filePathReal;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileMd5() {
        return fileMd5;
    }

    public void setFileMd5(String fileMd5) {
        this.fileMd5 = fileMd5;
    }

    public String getFileContentType() {
        return fileContentType;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileSuffix() {
        return fileSuffix;
    }

    public void setFileSuffix(String fileSuffix) {
        this.fileSuffix = fileSuffix;
    }

    public JmUploadPo() {
    }

    public JmUploadPo(String filePath, String fileName, String fileMd5, String fileContentType, String fileSize, String filePathReal, String fileSuffix) {
        this.filePath = filePath;
        this.fileName = fileName;
        this.fileMd5 = fileMd5;
        this.fileContentType = fileContentType;
        this.fileSize = fileSize;
        this.filePathReal = filePathReal;
        this.fileSuffix = fileSuffix;
    }
}
