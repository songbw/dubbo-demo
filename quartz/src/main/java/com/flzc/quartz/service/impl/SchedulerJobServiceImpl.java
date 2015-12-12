package com.flzc.quartz.service.impl;

import com.flzc.quartz.dao.CommonDao;
import com.flzc.quartz.entity.SchedulerConfigInfo;
import com.flzc.quartz.service.SchedulerJobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by iverson on 2015/9/19.
 */
@Service("schedulerJobService")
public class SchedulerJobServiceImpl implements SchedulerJobService {

    @Autowired
    private CommonDao commonDao;

    private  static  final Logger LOGGER = LoggerFactory.getLogger(SchedulerJobServiceImpl.class);

    @Override
    public Number insert(SchedulerConfigInfo info) throws Exception {
        try {
            return  this.commonDao.save(info);
        } catch (Exception e) {
            LOGGER.error("",e);
            throw  e;
        }
    }

    @Override
    public void update(SchedulerConfigInfo info) throws Exception {
        try {
            info.setUpdateTime(new Date());
            this.commonDao.update(info);
        } catch (Exception e) {
            LOGGER.error("", e);
            throw  e;
        }
    }

    @Override
    public List<SchedulerConfigInfo> queryAll() throws Exception {
        try {
            return this.commonDao.findAll(SchedulerConfigInfo.class);
        } catch (Exception e) {
            LOGGER.error("", e);
            throw  e;
        }
    }
}
