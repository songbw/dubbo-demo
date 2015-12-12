package com.flzc.rob.api.service;

import com.flzc.rob.api.entity.HouseBuildingTagsLog;

import java.util.List;

/**
 * 楼盘标签操作日志
 * Created by iverson on 2015/10/10.
 */
public interface HouseBuildingTagsLogService {

    /**
     * 保存日志
     * @param log
     * @return
     */
    public Number save(HouseBuildingTagsLog log) throws Exception;

    /**
     * 根据条件查询
     * @param param
     * @return
     */
    public List<HouseBuildingTagsLog> queryByParams(HouseBuildingTagsLog param) throws Exception;
}
