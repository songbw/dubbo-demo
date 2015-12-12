package com.flzc.file.upload.service.impl;

import com.flzc.file.upload.api.entity.ThumScaleBean;
import com.flzc.file.upload.api.entity.ThumSizeBean;
import com.flzc.file.upload.api.service.FileUploadService;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

/**
 *  上传文件实现类
 * Created by song on 2015/11/22.
 */
@Service("fileUploadService")
public class FileUploadServiceImpl implements FileUploadService {

    public final static String UPLOAD_IMAGE_PATH = "/data/webroot/img.18fanglian.com/";
    /**
     * 上传文件
     *
     * @param
     * @return
     */
    @Override
    public String upload(MultipartFile file, String fileName, String relativePath) {
        String newName =  new Date().getTime() +fileName ;
        String newPath = UPLOAD_IMAGE_PATH + relativePath + newName ;
        String  dir = UPLOAD_IMAGE_PATH + relativePath ;
        File file1 = new File(dir) ;
        if  (!file1 .exists()  && !file1 .isDirectory()) {
            file1.mkdir();
        }
         try {
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(newPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(newPath);
        return relativePath + newName;
    }

    @Override
    public String uploadIn(String fileName, String relativePath,InputStream file) {
        String newName =  new Date().getTime() +fileName ;
        String newPath = UPLOAD_IMAGE_PATH + relativePath + newName ;
        String  dir = UPLOAD_IMAGE_PATH + relativePath ;
        File file1 = new File(dir) ;
        if  (!file1 .exists()  && !file1 .isDirectory()) {
            file1.mkdir();
        }
        try {
            FileUtils.copyInputStreamToFile(file, new File(newPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(newPath);
        return relativePath + newName;
    }

    /**
     * 指定大小进行缩放
     * 若图片横比width小，高比hight小，不变
     * 若图片横比width小，高比hight大，高缩小到300，图片比例不变
     * 若图片横比width大，高比hight小，横缩小到width，图片比例不变
     * 若图片横比width大，高比hight大，图片按比例缩小，横为width或高为hight
     *
     * @param thumSizeBean
     * @return
     */
    @Override
    public String sizeThum(ThumSizeBean thumSizeBean) {
        try {
            Thumbnails.of(thumSizeBean.getFilePath())
                    .size(thumSizeBean.getWidth(), thumSizeBean.getHeight())
                    .toFile(thumSizeBean.getThumPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return thumSizeBean.getThumPath();
    }

    /**
     * 根据比较进行压缩
     *
     * @param thumScaleBean
     * @return
     */
    @Override
    public String scaleThum(ThumScaleBean thumScaleBean) {
        try {
            Thumbnails.of(thumScaleBean.getFilePath())
                    .scale(thumScaleBean.getScale())
                    .toFile(thumScaleBean.getThumPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return thumScaleBean.getThumPath();
    }

    /**
     * 删除图片
     *
     * @param path
     */
    @Override
    public void deleteImg(String path) {
        path = UPLOAD_IMAGE_PATH +path ;
        File file = new File(path) ;
        if  (!file .exists()  && !file .isDirectory()) {
            file.mkdir();
        } else {
            file.delete() ;
        }
    }
}
