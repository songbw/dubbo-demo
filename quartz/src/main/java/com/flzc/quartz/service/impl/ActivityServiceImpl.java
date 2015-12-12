package com.flzc.quartz.service.impl;

import com.flzc.quartz.dao.CommonDao;
import com.flzc.quartz.service.ActivityService;
import com.flzc.quartz.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service("activityService")
public class ActivityServiceImpl implements ActivityService {

    private  static  final Logger LOGGER = LoggerFactory.getLogger(ActivityServiceImpl.class);

    @Autowired
    private CommonDao commonDao;
    /**
     * 所有竞拍活动置为无效
     */
    @Override
    public void updateAuctionOver() {
         String today = DateUtil.getCurrenDate();
         String sql = "update aution_activity_info set status=2  where date_format(act_end_date,%Y-%m-%d)=':today' and status = 0";
         this.commonDao.updateBySql(sql.replace(":today",today));
    }
    /**
     * 把所有答题到结束日期的活动置为已结束
     */
    @Override
    public void updateQuestionOver(){
        String today = DateUtil.getCurrenDate();
        String sql = "update answer_question_activity set status=2  where date_format(act_end_date,%Y-%m-%d)=':today' and status = 0";
        this.commonDao.updateBySql(sql.replace(":today",today));
    }

    /**
     * 把所有定制活动到结束日期的活动置为已结束
     */
    @Override
    public void updateCustomizationOver(){
        String today = DateUtil.getCurrenDate();
        String sql = "update property_customization_activity set status=2  where date_format(act_end_date,%Y-%m-%d)=':today' and status = 0";
        this.commonDao.updateBySql(sql.replace(":today",today));
    }

    /**
     * 把所有到结束日期的活动置为已结束
     */
    @Override
    public void updateActivityRecapOver(){
        String today = DateUtil.getCurrenDate();
        String sql = "update activity_recap set act_status=2  where date_format(act_end_date,%Y-%m-%d)=':today' and act_status = 0";
        this.commonDao.updateBySql(sql.replace(":today",today));
    }

}
