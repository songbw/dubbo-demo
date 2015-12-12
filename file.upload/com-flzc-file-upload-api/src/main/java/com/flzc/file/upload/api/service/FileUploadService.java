package com.flzc.file.upload.api.service;

import com.flzc.file.upload.api.entity.FileUploadBean;
import com.flzc.file.upload.api.entity.ThumScaleBean;
import com.flzc.file.upload.api.entity.ThumSizeBean;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 *
 *  文件上传服务
 * Created by song on 2015/11/22.
 */
public interface FileUploadService {

    /**
     * 上传文件
     * @param file
     * @param fileName
     * @param relativePath
     * @return
     */
    public String upload(MultipartFile file, String fileName, String relativePath)  ;

    public String uploadIn(String fileName, String relativePath,InputStream file)  ;

    /**
     * 指定大小进行缩放
     *  若图片横比width小，高比hight小，不变
     * 若图片横比width小，高比hight大，高缩小到300，图片比例不变
     * 若图片横比width大，高比hight小，横缩小到width，图片比例不变
     * 若图片横比width大，高比hight大，图片按比例缩小，横为width或高为hight
     * @param thumSizeBean
     * @return
     */
    public String sizeThum(ThumSizeBean thumSizeBean) ;

    /**
     *  根据比较进行压缩
     * @param thumScaleBean
     * @return
     */
    public String scaleThum(ThumScaleBean thumScaleBean) ;

    /**
     * 删除图片
     * @param path
     */
    public void deleteImg(String path) ;
}
