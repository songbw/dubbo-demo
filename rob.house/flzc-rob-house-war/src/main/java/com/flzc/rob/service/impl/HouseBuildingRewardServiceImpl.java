package com.flzc.rob.service.impl;

import com.flzc.base.dao.BaseDao;
import com.flzc.base.dao.CommonDao;
import com.flzc.rob.api.entity.HouseBuildingReward;
import com.flzc.rob.api.service.HouseBuildingRewardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by iverson on 2015/11/16.
 */
@Service("houseBuildingRewardService")
public class HouseBuildingRewardServiceImpl implements HouseBuildingRewardService {
    private static final Logger LOGGER = LoggerFactory.getLogger(HouseBuildingRewardServiceImpl.class);

    @Autowired
    private CommonDao commonDao;

    @Autowired
    private BaseDao baseDao;

    /**
     * 根据开发商查询打赏金额
     *
     * @param buildingId
     * @return
     */
    @Override
    public Integer queryRewordByBuildingId(Integer buildingId ,Integer type ) {
        Date now =  new Date();
        String hql = " from HouseBuildingReward r where r.buildingId=:buildingId  and r.type=:type and r.startDate>=:start and r.endDate<=:end order by r.createTime desc ";
        List<HouseBuildingReward> result = this.baseDao.findByNamedParam(hql, new String[]{"buildingId","type", "start", "end"}, new Object[]{buildingId,type, now, now});
        if(result !=null && result.size() > 0 ){
            return result.get(0).getAmount();
        }
        return null;
    }
}
