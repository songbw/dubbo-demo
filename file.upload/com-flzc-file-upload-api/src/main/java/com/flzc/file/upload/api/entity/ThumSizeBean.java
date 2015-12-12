package com.flzc.file.upload.api.entity;

import javax.persistence.Entity;
import java.io.Serializable;

/**
 *   大小压缩bean
 * Created by song on 2015/11/22.
 */
public class ThumSizeBean implements Serializable{
    private Integer width ;
    private Integer height ;
    private String filePath ;
    private String thumPath ;

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getThumPath() {
        return thumPath;
    }

    public void setThumPath(String thumPath) {
        this.thumPath = thumPath;
    }
}
