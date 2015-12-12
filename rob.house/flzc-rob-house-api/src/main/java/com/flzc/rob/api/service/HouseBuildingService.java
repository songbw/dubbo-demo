package com.flzc.rob.api.service;

import java.util.List;
import java.util.Map;

import com.flzc.rob.api.entity.BuilderApprove;
import com.flzc.rob.api.entity.HouseBuildingInfo;
import com.flzc.tags.bean.City;

/**
 * 楼盘服务接口
 * Created by iverson on 2015/10/10.
 */
public interface HouseBuildingService {

    /**
     * 保存楼盘信息
     * @param info
     * @return
     */
    public Number save(HouseBuildingInfo info) throws Exception;

    /**
     * 修改楼盘信息。根据id修改
     * @param info
     * @return
     */
    public void updateById(HouseBuildingInfo info) throws Exception;

    /**
     * 根据id查询楼盘信息
     * @param id
     * @return
     */
    public HouseBuildingInfo queryById(Integer id) throws Exception;

    /**
     * 根据多个id查询楼盘信息
     * @param ids
     * @return
     */
    public List<HouseBuildingInfo>  queryByIds(List<Integer> ids) throws Exception;

    /**
     * 根据条件查询出楼盘信息
     * @param params
     * @return
     */
    public HouseBuildingInfo queryUniqueByParams(HouseBuildingInfo params) throws Exception;

    /**
     * 根据条件查询出多个楼盘信息.只适合单表查询
     * @param params
     * @return
     */
    public List<HouseBuildingInfo> queryListByParams(HouseBuildingInfo params) throws Exception;

    /**
     * 新房首页查询,复杂查询。
     * @param param key 有areaId;referPrice;around;feature;buildingType;house. 其中referPrice为low-up格式，表示s的是一个区间
     *             areaId:区域，referPrice：价格，around:表示周边配套， buildingType：楼盘类型，house：户型
     * @return
     */
    public List<Map<String,Object>> queryList(Map<String,Object> param , int curPage , int pageSize);

    /**
     * 根据城市id与活动类型查询
     * @param cityId ,actType, latitude,longitude
     * @param curPage
     * @param pageSize
     * @return
     */
    public List<Map<String,Object>> queryListByActAndCity(Map<String , Object> params , int curPage , int pageSize) throws Exception;

    /**
     * 查询热门定制楼盘列表
     * @param params
     * @param curPage
     * @param pageSize
     * @return
     * @throws Exception
     */
    public List<Map<String,Object>> queryListByCustomization(Map<String , Object> params , int curPage , int pageSize) throws Exception;

    /**
     * 查询有楼盘的城市
     * @return
     */
    public List<City> queryCityWithBuildings() throws Exception;

    /**
     * 经纪人，楼盘查询,复杂查询。
     * @param param
     *   查询条件有：cityId  ,activityId活动，
     * @return
     */
    public List<Map<String,Object>> queryListForAgency(Map<String,Object> param , int curPage , int pageSize);
    
    /**
     * 查询用户参与活动楼盘
     * @param param key 
     * @return
     */
    public List<Map<String,Object>> queryActivityBuildingListByUserId(Map<String,Object> param , int curPage , int pageSize);
    
    /**
	 * 根据活动汇总id获取楼盘信息
	 * @Title: getBuildingNameById
	 * @param activityRecapId
	 * @return
	 */
	public HouseBuildingInfo getBuildingNameByRecapId(Integer activityRecapId);
	
	/**
     * 新房首页查询,复杂查询。
     * @param param key 经纬度
     * @return
     */
    public Map<String,Object> queryBuildingInfoByBuildingId(Map<String,Object> param , Integer buildingId);

    BuilderApprove queryBuilderInfo(Integer builderId) throws Exception;

    List<Map<String, Object>> queryListByDikouAndCity(Map<String, Object> params, int curPage, int pageSize) throws Exception;
    
    /**
     * 根据城市id查询楼盘数量
     * @Title: queryListCountByCityId 
     * @Description: TODO
     * @param cityId
     * @return
     * @return: Integer
     */
    public Integer queryListCountByCityId(Integer cityId);
    
    /**
     * 根据城市查询活动楼盘信息
     * @Title: queryListByCity 
     * @Description: TODO
     * @param params
     * @param curPage
     * @param pageSize
     * @return
     * @throws Exception
     * @return: List<Map<String,Object>>
     */
    public List<Map<String, Object>> queryListByCity( Map<String,Object> params , int curPage, int pageSize) throws Exception;
    
    
    /**
   	 * 
   	 * @author wangxing
   	 * @date 2015年11月25日 下午8:50:12
   	 * @parameter agencyId
   	 * @description  获取楼盘佣金数
   	 * @return
   	 */

       public Map<String, Object> queryBuildingCommission(Integer buildingId) throws Exception;
    

	/**
	 * @author wangxing
	 * @date 2015年12月1日 下午8:25:03
	 * @parameter 
	 * @description 根据门店Code和城市Id获取门店下的楼盘信息
	 * @return
	 */
	public  List<Map<String,Object>> queryBuildingByOfficeCode(String agencyOfficeCode, Integer cityId,Integer curPage) throws Exception;
	
}
