package com.flzc.rob.api.service;

import com.flzc.rob.api.entity.HouseBuildingTagsLink;

import java.util.List;

/**
 * 楼盘与标签关联关系接口服务类
 * Created by iverson on 2015/10/10.
 */
public interface HouseBuildingTagsLinkService {
    /**
     * 保存
     * @param link
     * @return
     * @throws Exception
     */
    public Number save(HouseBuildingTagsLink link) throws Exception;

    /**
     * 根据id删除
     * @param id
     * @throws Exception
     */
    public void deleteById(Integer id) throws Exception;

    /**
     * 根据楼盘id查询
     * @param buildingId
     * @return
     * @throws Exception
     */
    public List<HouseBuildingTagsLink> queryByBuildingId(Integer buildingId) throws Exception;

    /**
     * 根据楼盘id查询前多少条
     * @param buildingId
     * @param limit
     * @return
     * @throws Exception
     */
  //  public List<HouseBuildingTagsLink> queryByBuildingId(Integer buildingId , int limit) throws Exception;


}
