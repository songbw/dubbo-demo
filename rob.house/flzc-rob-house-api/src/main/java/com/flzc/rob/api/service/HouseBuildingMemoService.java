package com.flzc.rob.api.service;

import com.flzc.rob.api.entity.HouseBuildingMemo;

/**
 * 楼盘动态信息接口
 * Created by iverson on 2015/10/10.
 */
public interface HouseBuildingMemoService {
    /**
     * 保存动态信息
     * @param memo
     * @return
     */
    public Number save(HouseBuildingMemo memo) throws Exception;

    /**
     * 查询楼盘动态信息
     * @param buildingId
     * @return
     */
    public HouseBuildingMemo queryByBuildingId(Integer buildingId) throws Exception;

    /**
     * 根据动态信息id更新
     * @param id
     */
    public void updateById(Integer id , String memo) throws Exception;

    /**
     * 根据楼盘id更新动态信息
     * @param buildingId
     */
    public void updateByBuildingId(Integer buildingId , String memo) throws Exception;
}
