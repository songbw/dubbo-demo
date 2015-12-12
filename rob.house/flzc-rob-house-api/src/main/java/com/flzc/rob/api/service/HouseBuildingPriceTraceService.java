package com.flzc.rob.api.service;

import com.flzc.rob.api.entity.HouseBuildingPriceTrace;

import java.util.List;

/**
 * 保存楼盘历史价格服务接口
 * Created by iverson on 2015/10/10.
 */
public interface HouseBuildingPriceTraceService {
    /**
     * 保存
     * @param priceTrace
     * @return
     */
    public Number save(HouseBuildingPriceTrace priceTrace) throws Exception;

    /**
     * 根据楼盘id查询
     * @param buildingId
     * @return
     */
    public List<HouseBuildingPriceTrace>  queryByBuildingId(Integer buildingId) throws Exception;
}
