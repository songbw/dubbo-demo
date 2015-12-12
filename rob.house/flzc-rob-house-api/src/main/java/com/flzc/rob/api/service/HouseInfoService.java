package com.flzc.rob.api.service;

import com.flzc.rob.api.entity.HouseInfo;

import java.util.List;
import java.util.Map;

/**户型服务接口
 * Created by iverson on 2015/10/10.
 */
public interface HouseInfoService {

    /**
     * 保存
     * @param info
     * @return
     * @throws Exception
     */
    public Number save(HouseInfo info) throws Exception;

    /**
     * 根据楼盘id查询
     * @param buildingId
     * @return
     */
    public List<HouseInfo> queryByBuildingId(Integer buildingId) throws Exception;

    /**
     * 根据id查询
     * @param id
     * @return
     */
    public HouseInfo queryById(Integer id) throws Exception;

    /**
     * 查询所有户型
     * @return
     */
    public List<Map<String, Object>> queryAll( ) throws Exception;
}
