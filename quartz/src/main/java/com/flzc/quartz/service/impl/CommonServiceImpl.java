package com.flzc.quartz.service.impl;

import com.flzc.quartz.dao.CommonDao;
import com.flzc.quartz.service.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 通用数据库接口实现类
 * Created by iverson on 2015/9/19.
 */
@Service("commonService")
public class CommonServiceImpl implements CommonService {
    private static  final Logger LOGGER = LoggerFactory.getLogger(CommonServiceImpl.class);

    @Autowired
    private CommonDao commonDao;

    @Override
    public <T> Number save(T t) throws Exception {
        try {
            Number id = this.commonDao.save(t);
            return id;
        }catch (Exception e){
            LOGGER.error("插入数据异常",e);
            throw  e;
        }
    }

    @Override
    public <T> void update(T t) throws Exception {
        try {
            this.commonDao.update(t);
        } catch (Exception e) {
            LOGGER.error("update operation exception",e);
            throw e;
        }
    }

    /**
     * 根据实体类属性值，动态查询,支持map
     *
     * @param t
     * @return
     */
    @Override
    public <T> List<T> findObj(T t) throws Exception {
        try {
           return   this.commonDao.findObjs(t);
        } catch (Exception e) {
            LOGGER.error("query list operation",e);
            throw  e;
        }
    }

    /**
     * 查询唯一对象
     *
     * @param t
     * @return
     */
    @Override
    public <T> T findUniqueObj(T t) throws Exception {
        try {
            return   this.commonDao.findUniqueObj(t);
        } catch (Exception e) {
            LOGGER.error("query unique operation",e);
            throw  e;
        }
    }

    /**
     * 根据id查询
     *
     * @param clazz
     * @param id
     * @return
     */
    @Override
    public <T> T findById(Class<T> clazz, Number id) throws Exception {
        try {
            return   this.commonDao.findById(clazz,id);
        } catch (Exception e) {
            LOGGER.error("query by id operation exception",e);
            throw  e;
        }
    }
}
