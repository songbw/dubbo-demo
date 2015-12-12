package com.flzc.rob.service.impl;

import java.util.List;

import com.flzc.base.dao.BaseDao;
import com.flzc.base.dao.CommonDao;
import com.flzc.rob.api.entity.AnswerQuestionActivity;
import com.flzc.rob.api.entity.PropertyCustomizationActivity;
import com.flzc.rob.api.service.PropertyCustomizationActivityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 私人定制活动接口实现
 * Created by iverson on 2015/10/22.
 */
@Service("propertyCustomizationActivityService")
public class PropertyCustomizationActivityServiceImpl implements PropertyCustomizationActivityService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PropertyCustomizationActivityServiceImpl.class);
    @Autowired
    private CommonDao commonDao;
    
    @Autowired
	private BaseDao baseDao;

    /**
     * 根据id查询活动信息
     *
     * @param id
     * @return
     */
    @Override
    public PropertyCustomizationActivity queryById(Integer id) throws Exception {
        try {
           return this.commonDao.findById(PropertyCustomizationActivity.class ,id);
        } catch (Exception e) {
            LOGGER.error("查询私人定制活动异常",e);
            throw e;
        }
    }

	@Override
	public String queryPropertyTicketUseRule(Integer ticketId,
			Integer activityId) {

		String rule="";
		try {
			
			String hql = "SELECT pca.* from FlzcTicket ft, ActivityRecap ar,PropertyCustomizationActivity pca where " +
						 "	ft.activityRecapId=ar.id " +
						 "	ar.act_id=pca.id " +
						 " and ft.id="+ticketId+" and pca.id="+activityId;
			
			List<PropertyCustomizationActivity> list = baseDao.findByHql(hql, 1);
			if(list==null || list.isEmpty())
				return rule;
			
			return list.get(0).getRules();
		} catch (Exception e) {
			LOGGER.error("查询房链券使用规则出错,"+e);
			throw e;
		}
	}
    
}
