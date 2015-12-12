package com.flzc.rob.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.flzc.base.service.impl.BaseServiceImpl;
import com.flzc.rob.api.entity.PropertyCustomizationBaseInfo;
import com.flzc.rob.api.service.PropertyCustomizationBaseInfoService;

@Service("propertyCustomizationBaseInfoService")
public class PropertyCustomizationBaseInfoServiceImpl extends BaseServiceImpl implements PropertyCustomizationBaseInfoService{

	private final static Logger logger = LoggerFactory.getLogger(PropertyCustomizationBaseInfoServiceImpl.class);
	
	@Override
	public PropertyCustomizationBaseInfo savePropertyCustomizationBaseInfo(PropertyCustomizationBaseInfo info) {

		try{
			
			Integer id = Integer.valueOf(this.save(info).toString());
			info.setId(id);
			return info;
			
		}catch(Exception e){
			e.printStackTrace();
			logger.error("保存用户定制基本信息失败，用户id：" + info.getUserId() + ",活动id：" + info.getActivityId(), e);
			throw e;
		}
	}

	@Override
	public PropertyCustomizationBaseInfo queryPropertyCustomizationBaseInfoById(Integer id) {

		return this.findById(PropertyCustomizationBaseInfo.class, id);
	}

	@Override
	public List<PropertyCustomizationBaseInfo> queryPcBiListByActivityIdAndPid(Integer activityId, Integer page,Integer pageSize) {

		String hql = "from PropertyCustomizationBaseInfo where activityId="+activityId+" and pid = 0 and status = 0";
		
		int minSize = (page - 1) * pageSize;
		
		List<PropertyCustomizationBaseInfo> list = this.findByHql(hql, minSize, pageSize);
		
		return list;
	}

	@Override
	public Integer queryPcBiCountByPid(Integer pid) {

		String hql = "select count(id) from PropertyCustomizationBaseInfo where status = 0 and pid=:pid";
		List<Long> list = this.findByNamedParam(hql, "pid", pid);
		
		return (list == null || list.isEmpty())?1:Integer.valueOf(list.get(0).toString());
	}

	@Override
	public Integer queryPcBiCountByActivityId(Integer activityId) {

		String hql = "select count(id) from PropertyCustomizationBaseInfo where status = 0 and activityId=:activityId group by userId";
		List<Long> list = this.findByNamedParam(hql, "activityId", activityId);
		
		return (list==null || list.isEmpty())?0:list.size();
	}

	@Override
	public List<PropertyCustomizationBaseInfo> queryPcbiListByActivityIdAndPage(
			Integer activityId, Integer page,Integer pageSize) {

		String hql = "from PropertyCustomizationBaseInfo where status = 0 and activityId = " + activityId + " group by userId";
		int minSize = (page - 1) * pageSize;
		
		List<PropertyCustomizationBaseInfo> list = this.findByHql(hql, minSize, pageSize);
		
		return list;
	}

	@Override
	public List<PropertyCustomizationBaseInfo> queryPcBiListByActivityIdAndPid(
			Integer activityId) {
		String hql = "from PropertyCustomizationBaseInfo where activityId="+activityId+" and pid = 0 and status = 0";
		
		List<PropertyCustomizationBaseInfo> list = this.findByHql(hql);
		return list;
	}

	@Override
	public Integer queryRewardUserCountByActId(Integer activityId) {

		String hql = "select count(DISTINCT userId) from PropertyCustomizationBaseInfo where status = 0 and approveStatus = 43002 and activityId=:activityId group by userId";
		List list = this.findByNamedParam(hql, "activityId", activityId);
		if(list == null || list.isEmpty()){
			return 0;
		}
		return Integer.valueOf(list.get(0).toString());
	}
	
	
	@Override
	public List<PropertyCustomizationBaseInfo> queryRewardUserListByActId(Integer activityId, Integer page,
			Integer pageSize) {


		String hql = "from PropertyCustomizationBaseInfo where  status = 0 and approveStatus = 43002 and activityId="+activityId+" group by userId order by createTime desc";
		int minSize = (page - 1) * pageSize;
		List list = this.findByHql(hql, minSize, pageSize);
		return list;
	}

}
