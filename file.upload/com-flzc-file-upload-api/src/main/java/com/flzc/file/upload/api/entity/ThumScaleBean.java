package com.flzc.file.upload.api.entity;

import javax.persistence.Entity;
import java.io.Serializable;

/**
 * 比例压缩bean
 * Created by song on 2015/11/22.
 */
public class ThumScaleBean implements Serializable{
    private Float scale ;
    private String filePath ;
    private String thumPath ;

    public Float getScale() {
        return scale;
    }

    public void setScale(Float scale) {
        this.scale = scale;
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
