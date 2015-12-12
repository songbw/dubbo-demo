package com.flzc.rob.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.flzc.base.service.BaseService;
import com.flzc.rob.api.entity.HouseBuildingTagsRecLink;
import com.flzc.rob.api.service.HouseBuildingTagsRecLinkService;

@Service("houseBuildingTagsRecLinkService")
public class HouseBuildingTagsRecLinkServiceImpl implements
		HouseBuildingTagsRecLinkService {

	private static final Logger logger = LoggerFactory.getLogger(HouseBuildingTagsRecLinkServiceImpl.class);

	@Resource(name="baseServiceImpl")
    private BaseService baseService;
    
	@Override
	public List<HouseBuildingTagsRecLink> queryByBuildingId(Integer buildingId)
			throws Exception {
		
		try {
			
			String hql = " from HouseBuildingTagsRecLink where buildingId ="+buildingId +" and status =0";
			return baseService.findByHql(hql);
		} catch (Exception e) {
			logger.error("根据楼盘id查询特色标签异常",e);
			throw e;
		}
	}

}
