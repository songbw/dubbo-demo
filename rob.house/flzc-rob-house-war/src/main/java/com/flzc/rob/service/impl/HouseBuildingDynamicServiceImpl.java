package com.flzc.rob.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flzc.base.dao.BaseDao;
import com.flzc.rob.api.entity.HouseBuildingDynamic;
import com.flzc.rob.api.service.HouseBuildingDynamicService;

@Service("houseBuildingDynamicService")
public class HouseBuildingDynamicServiceImpl implements HouseBuildingDynamicService {
	private static final Logger LOGGER = LoggerFactory.getLogger(HouseBuildingMemoServiceImpl.class);
	private static final Integer VALID = 0;
    private static final Integer INVALID = 1;
    
    @Autowired
    private BaseDao baseDao;
    
	@Override
	public List<HouseBuildingDynamic> queryByBuildingId(Integer buildingId, Integer page, Integer pageSize) throws Exception {
		try {
			String hql = "from HouseBuildingDynamic where buildingId=:buildingId and status=:valid order by create_time desc";
			return baseDao.findByNamedParam(hql, new String[]{"buildingId", "valid"}, 
					new Object[]{buildingId, VALID}, (page-1)*pageSize, pageSize);
        } catch (Exception e) {
            LOGGER.error("查询楼盘配套信息",e);
            throw e;
        }
	}

	@Override
	public Integer queryCountByBuildingId(Integer buildingId) throws Exception {
		try {
			String hql = String.format("select count(id) from HouseBuildingDynamic where buildingId=%s and status=%s order by create_time desc", buildingId, VALID);
			return baseDao.findPageTotalCount(hql);
		} catch (Exception e) {
			LOGGER.error("查询楼盘配套总数信息",e);
            throw e;
		}
	}

}
