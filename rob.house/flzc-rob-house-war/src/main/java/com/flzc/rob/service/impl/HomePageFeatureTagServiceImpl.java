package com.flzc.rob.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.flzc.base.service.impl.BaseServiceImpl;
import com.flzc.rob.api.entity.HouseBuildingTags;
import com.flzc.rob.api.service.HomePageFeatureTagService;

/**
 * 
  * @ClassName: HomePageFeatureTagServiceImpl
  * @Description: 线上房展特色标签
  * @author qj
  * @date 2015年10月20日 下午5:22:31
  *
 */
@Service("homePageFeatureTagService")
public class HomePageFeatureTagServiceImpl extends BaseServiceImpl implements HomePageFeatureTagService {


    private static final Logger LOGGER = LoggerFactory.getLogger(HomePageFeatureTagServiceImpl.class);

	@Override
	public List<HouseBuildingTags> queryHouseBuildingTags() throws Exception {

		try {
			String hql = "select hts from HomePageFeatureTag ht , HouseBuildingTags hts " +
					"where ht.tagCode = hts.code and hts.status = 0 and ht.type=0 order by ht.createTime desc";
			return  this.findByHql(hql, 2);
		} catch (Exception e) {
			LOGGER.error("查询线上房展失败",e);
			throw e;
		}
	}
    
    
}
