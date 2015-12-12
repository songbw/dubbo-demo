package com.flzc.file.upload.service.impl;

import com.flzc.file.upload.api.entity.FileUploadBean;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by song on 2015/11/22.
 */
public class FileUploadServiceImplTest {

    @Test
    public void testUpload() throws Exception {
        InputStream in = null ;
        String fileName = "D:\\a380_25%.jpg" ;
        File file = new File(fileName);
        in = new FileInputStream(file);
        FileUploadBean fileUploadBean = new FileUploadBean() ;
        fileUploadBean.setFile(in);
        fileUploadBean.setFileName("123.jpg");
        fileUploadBean.setRelativePath("user//");
        FileUploadServiceImpl   fileUploadService = new FileUploadServiceImpl() ;
//        String a = fileUploadService.upload(in,"123.jpg","user/") ;
//        System.out.print(a);
    }
}