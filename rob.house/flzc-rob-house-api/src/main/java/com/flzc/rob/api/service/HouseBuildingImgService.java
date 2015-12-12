package com.flzc.rob.api.service;

import com.flzc.rob.api.entity.HouseBuildingImg;

import java.util.List;

/**
 * 楼盘图片服务接口
 * Created by iverson on 2015/10/10.
 */
public interface HouseBuildingImgService {

    /**
     * 保存图片
     * @param img
     * @return
     */
    public Number save(HouseBuildingImg img) throws Exception;

    /**
     * 删除一条图片信息，逻辑删除
     * @param id
     */
    public void deleteById(Integer id) throws Exception;

    /**
     * 更新图片。根据Id更新
     * @param img
     */
    public void update(HouseBuildingImg img) throws Exception;

    /**
     * 根据id查询
     * @param id
     * @return
     */
    public HouseBuildingImg queryById(Integer id) throws Exception;

    /**
     * 按参数查询楼盘图片
     * @param params
     * @return
     */
    public List<HouseBuildingImg> queryListByParams(HouseBuildingImg params) throws Exception;

    /**
     * 根据楼盘id查询封面图片信息
     * @Title: queryHouseBuildingImgByCoverAndBuildingId 
     * @Description: TODO
     * @param buildingId
     * @return
     * @return: HouseBuildingImg
     */
    public HouseBuildingImg queryHouseBuildingImgByCoverAndBuildingId(Integer buildingId);
    
    /**
     * 查询图片类别
     * @Title: queryHouseBuildingImgByType 
     * @Description: TODO
     * @return
     * @return: List<HouseBuildingImg>
     */
    public List<HouseBuildingImg> queryHouseBuildingImgByTypeAndBuildingId(
    		Integer buildingId);
    
    /**
     * 根据type查询图片list
     * @Title: queryHouseBuildingImgListByType 
     * @Description: TODO
     * @param type
     * @return
     * @return: List<HouseBuildingImg>
     */
    public List<HouseBuildingImg> queryHouseBuildingImgListByTypeAndBuildingId(
    		Integer type,Integer buildingId);
}
