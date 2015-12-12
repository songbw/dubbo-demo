package com.flzc.rob.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.flzc.base.dao.CommonDao;
import com.flzc.base.service.impl.BaseServiceImpl;
import com.flzc.base.util.DateUtil;
import com.flzc.base.util.Memcached;
import com.flzc.base.util.MemcachedKeyConstant;
import com.flzc.rob.api.entity.ActivityRecap;
import com.flzc.rob.api.service.ActivityRecapService;
import com.flzc.rob.common.Constants;

/**
 * 活动汇总接口实现
 * @ClassName: ActivityRecapServiceImpl 
 * @Description: TODO
 * @author: chenqi
 * @date: 2015年10月10日 下午10:19:53
 */
@Service("activityRecapService")
public class ActivityRecapServiceImpl extends BaseServiceImpl implements ActivityRecapService{

	private static final Logger logger = LoggerFactory.getLogger(ActivityRecapServiceImpl.class);
	
	@Resource
	private CommonDao commonDao;
	
	@Override
	public Integer saveActivityRecap(ActivityRecap activityRecap) {

		try {

			Integer id = Integer.valueOf(this.save(activityRecap).toString());
			activityRecap.setId(id);

			Memcached.set(MemcachedKeyConstant.BUILDING_ACTIVITY_RECAP + id,
					activityRecap, com.flzc.base.util.Constants.CACHE_TIME,
					com.flzc.base.util.Constants.USER_INFO_USERID_TIME);
			return id;
		} catch (Exception e) {
			logger.error("保存活动汇总失败", e);
			throw e;
		}
	}

	@Override
	public List<ActivityRecap> queryActivityRecapByBuildingId(Integer buildingId) {

		String date = DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss");
		String hql = "from ActivityRecap where actStatus = 0 and buildingId=:buildingId and showStartDate <'"+ date +"' and " 
				+ "actActiveEndDate > '" + date + "' order by type";
		List<ActivityRecap> list = this.findByNamedParam(hql, "buildingId", buildingId);
		return list;
	}
	
	@Override
	public List<ActivityRecap> queryActivityRecapByBId(Integer buildingId) {

		String date = DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss");
		String hql = "from ActivityRecap where actStatus = 0 and buildingId=:buildingId and showStartDate <'"+ date +"' and " 
				+ "actActiveEndDate > '" + date + "' order by type";
		List<ActivityRecap> list = this.findByNamedParam(hql, "buildingId", buildingId);
		return list;
	}


	
	@Override
	public List<ActivityRecap> queryActivityListByCityAndPage(Integer cityId,Integer page, Integer pageSize) {

		String date = DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss");
		String hql = "select ar from ActivityRecap ar,HouseBuildingInfo hbi where ar.buildingId = hbi.id and hbi.buildingState = 40001 and ar.actStatus = 0 and hbi.cityId = "+cityId+" and ar.showStartDate <'"+ date +"' and " 
				+ "ar.actEndDate > '" + date + "'";
		
		int minSize = (page - 1) * pageSize;
		List<ActivityRecap> list = this.findByHql(hql, minSize, pageSize);
		return list;
	}

	
	@Override
	public Integer queryAboutOnLineActivityCount(Integer cityId) {
		
		String sql = "select count(DISTINCT a.building_id) from activity_recap a,house_building_info hbi where a.building_id = hbi.id and hbi.city_id = "+cityId+" and a.act_status = 0 "
				+ "and a.show_start_date < now() and act_active_end_date > now()";
		
		List<Object> list = this.findBySql(sql);

		int count = 0;
		if(list != null && !list.isEmpty())
			count = Integer.valueOf(list.get(0).toString());
		return count;
		
	}

	@Override
	public ActivityRecap queryActivityRecapById(Integer activityRecapId) throws Exception {

		ActivityRecap recap =null;
		String key = MemcachedKeyConstant.BUILDING_ACTIVITY_RECAP+activityRecapId;
		try {
			
			recap=(ActivityRecap) Memcached.get(key);
			if(recap!=null)
				return recap;
			
			recap =commonDao.findById(ActivityRecap.class, activityRecapId);
			Memcached.set(key,recap, com.flzc.base.util.Constants.CACHE_TIME,
					com.flzc.base.util.Constants.USER_INFO_USERID_TIME);
		} catch (Exception e) {
			logger.error("根据活动汇总id查询活动汇总出错"+e);
			throw e;
		}
		return recap;
	}

	

	@Override
	public ActivityRecap queryActivityRecapByBuildingAndType(Integer buildingId, Integer type) {
		String hql = "from ActivityRecap where actStatus = 0 and buildingId=:buildingId and type=:type";
		List<ActivityRecap> list = this.findByNamedParam(hql, new String[]{"buildingId", "type"}, new Object[]{buildingId, type});
		ActivityRecap recap = null;
		if(list != null && list.size() > 0){
			recap = list.get(0);
		}
		return recap;
	}

	@Override
	public ActivityRecap queryActivityRecapById(Integer activityId,
			Integer activityType) throws Exception {

		try {
			
			ActivityRecap recap = new ActivityRecap();
			recap.setActId(activityId);
			recap.setType(activityType);
			
			return commonDao.findUniqueObj(recap);
		} catch (Exception e) {
			logger.error("根据活动id和活动类型的id查询活动汇总信息异常",e);
			throw e;
		}
	}
	
}
