package com.flzc.rob.api.service;

import com.flzc.rob.api.entity.PropertyCustomizationRecord;

import java.util.List;
import java.util.Map;

/**
 * 私人定制活动服务接口
 * Created by iverson on 2015/10/17.
 */
public interface PropertyCustomizationRecordService {
    /**
     * 保存活动
     * @param activity
     * @return
     */
    public Number save(PropertyCustomizationRecord activity) throws Exception;

    /**
     *根据建筑id查询参与活动的信息
     * @param buildingId
     * @return
     */
    public List<Map<String ,Object>> queryByBuildingId(Integer buildingId ,int curPage,int pageSize ) throws Exception;

    /**
     * 根据用户id查询活动
     * @param userId
     * @return
     */
    public List<PropertyCustomizationRecord> queryByUserId(Integer userId) throws Exception;

    /**
     * 根据id查询
     * @param id
     * @return
     */
    public PropertyCustomizationRecord queryById(Integer id) throws Exception;

    /**
     * 根据楼盘与户型统计
     * @param recordId
     * @return
     */
    public  int countNumByHouse(  Integer recordId);

    /**
     * 统计定制户型的支持数
     * @param recordId
     * @return
     */
    public int countFollowNum(Integer recordId);
    
    /**
     * 根据活动id查询用户定制列表
     * @Title: queryPCRListByActId
     * @param actId
     * @return
     * List<PropertyCustomizationRecord>
     */
    public List<PropertyCustomizationRecord> queryPCRListByActId(Integer actId,Integer curPage,Integer pageSize);

}
