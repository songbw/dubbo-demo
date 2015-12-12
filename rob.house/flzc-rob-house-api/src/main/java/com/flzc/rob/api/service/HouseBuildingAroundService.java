package com.flzc.rob.api.service;

import com.flzc.rob.api.entity.HouseBuildingAround;

import java.util.List;
import java.util.Map;

/**
 * 楼盘周边配套接口
 * Created by iverson on 2015/10/10.
 */
public interface HouseBuildingAroundService {

    /**
     * 保存配套信息
     *
     * @param around
     * @return
     */
    public Number save(HouseBuildingAround around) throws Exception;

    /**
     * 删除配套信息
     *
     * @param id
     */
    public void deleteById(Integer id) throws Exception;

    /**
     * 根据楼盘id查询
     *
     * @param buildingId
     * @return
     */
    public List<Map<String, Object>> queryByBuildingId(Integer buildingId) throws Exception;
}
