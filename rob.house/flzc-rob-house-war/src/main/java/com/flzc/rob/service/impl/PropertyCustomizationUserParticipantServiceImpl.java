package com.flzc.rob.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flzc.base.dao.BaseDao;
import com.flzc.base.dao.CommonDao;
import com.flzc.rob.api.entity.PropertyCustomizationUserParticipant;
import com.flzc.rob.api.service.PropertyCustomizationUserParticipantService;

/**
 * Created by iverson on 2015/10/10.
 */
@Service("propertyCustomizationUserParticipantService")
public class PropertyCustomizationUserParticipantServiceImpl implements PropertyCustomizationUserParticipantService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PropertyCustomizationUserParticipantServiceImpl.class);

    @Resource(name = "commonDao")
    private CommonDao commonDao;
    
    @Autowired
	private BaseDao baseDao;

	@Override
	public Integer savePropertyCustomizationUserParticipant(
			PropertyCustomizationUserParticipant participant) throws Exception {
		
		try {
			
			Number id = commonDao.save(participant);
			
			return id.intValue();
		} catch (Exception e) {
			
			LOGGER.error("保存用户定制参与人员信息错误,"+e);
			throw e;
		}
	}

	@Override
	public PropertyCustomizationUserParticipant queryPropertyCustomizationUserParticipantById(
			Integer id) throws Exception {
		
		try {
			
			return commonDao.findById(PropertyCustomizationUserParticipant.class, id);
		} catch (Exception e) {
			LOGGER.error("查询用户定制参与人员信息错误,"+e);
			throw e ;
		}
	}

	@Override
	public List<PropertyCustomizationUserParticipant> queryPropertyCustomizationUserParticipantList(
			PropertyCustomizationUserParticipant participant) throws Exception {
		
		try {
			
			return commonDao.findObjs(participant);
		} catch (Exception e) {
			LOGGER.error("查询用户定制参与人员信息错误,"+e);
			throw e;
		}
	}

	@Override
	public Integer queryCountByActId(Integer actId) throws Exception {
		String hql = "select count(id) from PropertyCustomizationUserParticipant where activityId=:actId group by userId ";
		List<Long> list = baseDao.findByNamedParam(hql, "actId", actId);
		return (list == null || list.isEmpty())?0:Integer.valueOf(list.get(0).toString());
	}

	@Override
	public Integer queryPayUserByActId(Integer actId) throws Exception {
		String hql = "select count(id) from PropertyCustomizationUserParticipant where activityId=:actId and participantWay=0 group by userId ";
		List<Long> list = baseDao.findByNamedParam(hql, "actId", actId);
		return (list == null || list.isEmpty())?0:Integer.valueOf(list.get(0).toString());
	}

}
