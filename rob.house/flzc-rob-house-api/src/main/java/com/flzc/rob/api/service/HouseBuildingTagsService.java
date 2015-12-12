package com.flzc.rob.api.service;

import com.flzc.rob.api.entity.HouseBuildingTags;

import java.util.List;

/**
 * Created by iverson on 2015/10/10.
 */
public interface HouseBuildingTagsService {
    /**
     *  保存标签
     * @param tag
     * @return
     */
    public Number save(HouseBuildingTags tag) throws Exception;

    /**
     * 根据id更新标签
     * @param id
     */
  //  public void updateById(Integer id);

    /**
     * 根据id标签删除标签
     * @param id
     */
    public void deleteById(Integer id) throws Exception;

    /**
     * 根据多个楼盘id查询标签
     * @param ids
     * @return
     */
    public List<HouseBuildingTags> queryByIds(List<Integer> ids) throws Exception;



    /**
     * 根据楼盘id,类型查询标签
     * @param ids
     * @param type
     * @return
     */
    public List<HouseBuildingTags> queryByIds(List<Integer> ids ,Integer type);

    /**
     *
     * @param pcode 父编码
     * @param type 类型
     * @return
     */
    public List<HouseBuildingTags> queryByParentCode(String pcode,Integer type);

    /**
     * 根据楼盘id,编码查询标签
     * @param buildingId
     * @param code
     * @return
     */
    public List<HouseBuildingTags> queryTags(Integer buildingId,String code) throws Exception;
    
    /**
     * 根据标签查询标签
     * @param id
     * @return
     * @throws Exception
     */
    public HouseBuildingTags queryById(Integer id) throws Exception;
    
    /**
     * 根据编码查询tags
     * @Title: queryHouseBuildingTagsByCode 
     * @Description: TODO
     * @param code
     * @return
     * @return: HouseBuildingTags
     */
    public HouseBuildingTags queryHouseBuildingTagsByCode(String code);
}
