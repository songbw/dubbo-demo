package com.flzc.quartz.service;

import com.flzc.quartz.entity.SchedulerConfigInfo;

import java.util.List;

/**
 * Created by iverson on 2015/9/19.
 */
public interface SchedulerJobService {
    /**
     * 添加一条记录
     * @param info
     * @return
     */
    public Number insert(SchedulerConfigInfo info) throws Exception;


    public void update(SchedulerConfigInfo info) throws Exception;

    public List<SchedulerConfigInfo> queryAll() throws Exception;

}
