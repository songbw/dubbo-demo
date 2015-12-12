package com.flzc.rob.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flzc.base.dao.BaseDao;
import com.flzc.base.service.impl.BaseServiceImpl;
import com.flzc.rob.api.entity.AutionActivityInfo;
import com.flzc.rob.api.service.AutionActivityInfoService;

/**
 * 竞拍活动详情业务实现
 * @ClassName: AutionActivityInfoServiceImpl 
 * @Description: TODO
 * @author: chenqi
 * @date: 2015年10月10日 下午4:57:17
 */
@Service("autionActivityInfoService")
public class AutionActivityInfoServiceImpl extends BaseServiceImpl implements AutionActivityInfoService{

	private final static Logger logger = LoggerFactory.getLogger(AutionActivityInfoServiceImpl.class);
	
	@Autowired
	private BaseDao baseDao;
	
	@Override
	public Integer saveAutionActivityInfo(AutionActivityInfo info) {

		try{
			
			return Integer.valueOf(this.save(info).toString());
			
		}catch(Exception e){
			
			logger.error("保存竞拍活动失败",e);
			throw e;
		}
	}

	@Override
	public AutionActivityInfo queryAutionActivityInfo(AutionActivityInfo info) {

		
		return null;
	}

	@Override
	public AutionActivityInfo queryAutionActivityInfoByBuildingId(Integer buildingId) {

		String hql = "from AutionActivityInfo where status = 0 and buildingId=:buildingId";
		List<AutionActivityInfo> list = this.findByNamedParam(
				hql, "buildingId", buildingId);
		
		if(list == null || list.isEmpty())
			return null;
		
		return list.get(0);
	}

	@Override
	public AutionActivityInfo queryAutionActivityInfoById(Integer id) {

		return this.findById(AutionActivityInfo.class, id);
	}

	@Override
	public String queryAuctionTicketUseRule(Integer ticketId, Integer activityId) {

		String rule="";
		try {
			
			String hql = "SELECT aai.* from FlzcTicket ft, ActivityRecap ar,AutionActivityInfo aai where " +
						 "	ft.activityRecapId=ar.id " +
						 "	ar.act_id=aai.id " +
						 " and ft.id="+ticketId+" and aai.id="+activityId;
			
			List<AutionActivityInfo> list = baseDao.findByHql(hql, 1);
			if(list==null || list.isEmpty())
				return rule;
			
			return list.get(0).getRule();
		} catch (Exception e) {
			logger.error("查询房链券使用规则出错,"+e);
			throw e;
		}
	}
	
}
