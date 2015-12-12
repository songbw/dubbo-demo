package com.flzc.file.upload.api.entity;

import javax.persistence.Entity;
import java.io.InputStream;
import java.io.Serializable;

/**
 *  文件上传bean
 * Created by song on 2015/11/22.
 */
public class FileUploadBean implements Serializable{
    private InputStream file ;
    private String fileName ;
    private String relativePath ;

    public InputStream getFile() {
        return file;
    }

    public void setFile(InputStream file) {
        this.file = file;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getRelativePath() {
        return relativePath;
    }

    public void setRelativePath(String relativePath) {
        this.relativePath = relativePath;
    }
}
