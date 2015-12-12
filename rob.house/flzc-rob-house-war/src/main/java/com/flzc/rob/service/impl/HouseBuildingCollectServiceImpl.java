
package com.flzc.rob.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.flzc.base.dao.CommonDao;
import com.flzc.base.service.BaseService;
import com.flzc.base.util.CommonUtils;
import com.flzc.base.util.Memcached;
import com.flzc.base.util.MemcachedKeyConstant;
import com.flzc.rob.api.entity.HouseBuildingCollect;
import com.flzc.rob.api.service.HouseBuildingCollectService;
import com.flzc.rob.common.Constants;
import com.flzc.rob.util.Constant;

@Service("houseBuildingCollectService")
public class HouseBuildingCollectServiceImpl implements	HouseBuildingCollectService {

	private final static Logger logger = LoggerFactory.getLogger(HouseBuildingCollectServiceImpl.class);
	
	@Autowired
	private CommonDao commonDao;
	
	@Resource(name="baseServiceImpl")
	private BaseService baseService;
	
	@Override
	public Integer saveHouseBuildingCollect(HouseBuildingCollect houseBuildingCollect) throws Exception {
		
		try {
			
			HouseBuildingCollect coll = queryHouseBuildingCollectByUserIdAndBuildingId(
					houseBuildingCollect.getUserId(), 
					houseBuildingCollect.getBuildingId());
			
			if(coll != null)
				throw new RuntimeException();
			
			
			Number collect = commonDao.save(houseBuildingCollect);
			
			return collect.intValue();
		} catch (Exception e) {
			logger.error("收藏楼盘报错",e);
			throw e;
		}
	}

	@Override
	public List<HouseBuildingCollect> queryUserHouseBuildingCollectList(
			Integer userId,Integer page,Integer pageSize) throws Exception {

		try {
			String hql =" from HouseBuildingCollect where userId="+userId+" and status =0 ";
			int minSize = (page - 1) * pageSize;
			List<HouseBuildingCollect> collectList = baseService.findByHql(hql, minSize, pageSize);
			
			return collectList;
		} catch (Exception e) {
			logger.error("查询我收藏楼盘列表报错,用户Id："+userId,e);
			throw e;
		}
	}
	
	@Override
	public int queryUserHouseBuildingCollectListSize(
			Integer userId) throws Exception {
		try {
			String hql ="select count(id) from HouseBuildingCollect where userId="+userId+" and status =0 ";			
			return baseService.findPageTotalCount(hql);
		} catch (Exception e) {
			logger.error("查询我收藏楼盘列表数量报错,用户Id："+userId,e);
			throw e;
		}
	}

	@Override
	public void deleteUserHouseBuildingCollect(HouseBuildingCollect houseBuildingCollect)
			throws Exception {
		
		try {
			commonDao.delete(houseBuildingCollect);
			String key = MemcachedKeyConstant.USER_COLLECT_BUILDING_BY_USERID_AND_BUILDINGID + 
					houseBuildingCollect.getUserId() + "." + houseBuildingCollect.getBuildingId();
			Memcached.remove(key);
		} catch (Exception e) {
			logger.error("删除我收藏的楼盘报错",e);
			throw e;
		}
	}

	@Override
	public HouseBuildingCollect queryUserHouseBuildingCollectById(Integer collectId)
			throws Exception {
		
		try {
			
			return commonDao.findById(HouseBuildingCollect.class, collectId);
		} catch (Exception e) {
			logger.error("查询我收藏的楼盘报错,收藏Id："+collectId,e);
			throw e;
		}
	}


	@Override
	public HouseBuildingCollect queryUserHouseBuildingCollect(
			HouseBuildingCollect houseBuildingCollect) throws Exception {
		
		try {
			
			return commonDao.findUniqueObj(houseBuildingCollect);
		} catch (Exception e) {
			logger.error("查询我收藏的楼盘报错",e);
			throw e;
		}
	}

	@Override
	public HouseBuildingCollect queryHouseBuildingCollectByUserIdAndBuildingId(Integer userId,
			Integer buildingId) {

		String key = MemcachedKeyConstant.USER_COLLECT_BUILDING_BY_USERID_AND_BUILDINGID + userId + "." + buildingId;
		HouseBuildingCollect collect = (HouseBuildingCollect)Memcached.get(key);
		if(collect != null)
			return collect;
		
		String hql = "from HouseBuildingCollect where status = 0 and "
				+ "userId=:userId and buildingId=:buildingId";
		
		List<HouseBuildingCollect> list = baseService.findByNamedParam(hql, new String[]{"userId","buildingId"},
				new Object[]{userId,buildingId});
		
		if(list == null || list.isEmpty())
			return null;
		
		Memcached.set(key, list.get(0), Calendar.MINUTE, Constant.time_5);
		
		return list.get(0);
	}

	
	@Override
	public List<Map<String, Object>> queryCollectBuildingList(
			Map<String, Object> param, int curPage, int pageSize) {
        if(curPage==0) curPage++;
        if(pageSize==0) pageSize = 10;
        List<String> keys = new ArrayList<>();
        List<Object> values = new ArrayList<>();
        logger.info("参数：" + JSONObject.toJSONString(param));
        try {
            param =  CommonUtils.clearMapBlankVal(param);
            Double longitude = Double.valueOf( param.get("longitude").toString());
            Double latitude = Double.valueOf( param.get("latitude").toString());
            keys.add(Constants.LATITUDE_0);
            keys.add(Constants.LATITUDE_1);
            keys.add(Constants.LONGITUDE);
            keys.add("userId");
            
            values.add(latitude);
            values.add(latitude);
            values.add(longitude);
            values.add(Integer.valueOf(String.valueOf(param.get("userId"))));
            
            String sql = "SELECT DISTINCT " + Constants.DISTANCE_FORMULA + "," +
                    "  b.id," +
                    "  r.act_id as actId," +
                    "  b.`name` ," +
                    "  b.refer_price as referPrice ," +
                    "  b.total_num as totalNum," +
                    "  b.sale_num as saleNum," +
                    "  b.latitude," +
                    "  b.longitude ," +
                    "  b.address," +
                    "  b.decoration," +
                    "  r.type ," +
                    "  b.area_id as areaId , " +
                    "  hi.img_url as imgUrl" +
                     "  FROM " +
                    "  house_building_info b " +
                    " INNER JOIN house_building_img hi on b.id = hi.building_id " +
                    " LEFT JOIN  activity_recap r  on  b.id = r.building_id " +
                    " INNER JOIN house_building_collect hbc on r.act_id=hbc.activity_id and r.type=hbc.type" +
                    " WHERE " +
                    " hi.main_pic=0 and hi.type=0 and b.building_state=40004 and r.act_status=0 and hbc.user_id=:userId" +
                    " order by  r.show_start_date desc";

           return  this.commonDao.findMapBySqlAndNamedParamPage(sql, keys, values, (curPage - 1) * pageSize, pageSize);
        } catch (Exception e) {
            logger.error("查询我的收藏列表异常",e);
            throw  e;
        }

    }

}
