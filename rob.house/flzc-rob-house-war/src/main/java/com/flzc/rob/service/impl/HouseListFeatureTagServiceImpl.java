package com.flzc.rob.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.flzc.base.service.impl.BaseServiceImpl;
import com.flzc.rob.api.entity.HouseBuildingTags;
import com.flzc.rob.api.entity.HouseListFeatureTag;
import com.flzc.rob.api.service.HouseListFeatureTagService;

/**
 * 列表页线上房展接口实现
 * @ClassName: HouseListFeatureTagServiceImpl 
 * @Description: TODO
 * @author: chenqi
 * @date: 2015年12月2日 下午4:47:27
 */
@Service("houseListFeatureTagService")
public class HouseListFeatureTagServiceImpl extends BaseServiceImpl 
	implements HouseListFeatureTagService{

	@Override
	public List<HouseBuildingTags> queryHouseListFeatureTagList(Integer page, Integer pageSize) {

		String hql = "select hts from HouseListFeatureTag ht , HouseBuildingTags hts " +
		"where ht.tagCode = hts.code and hts.status = 0 and ht.status = 0 and ht.type=0 order by ht.createTime desc";
		
		return  this.findByHql(hql, (page - 1) * pageSize, pageSize);
	}

	
}
