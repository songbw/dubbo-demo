package com.flzc.scene.filing.service.impl;

import com.flzc.base.service.impl.BaseServiceImpl;
import com.flzc.scene.filing.api.entity.*;
import com.flzc.scene.filing.api.service.SceneDealService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 成交服务实现类
 * Created by song on 2015/11/18.
 */
@Service("sceneDealService")
public class SceneDealServiceImpl extends BaseServiceImpl implements SceneDealService {
    private static  final Logger LOGGER = LoggerFactory.getLogger(SceneDealServiceImpl.class);
    /**
     * 保存案场成交信息
     *
     * @param sceneDeal
     * @return
     */
    @Override
    public SceneDeal saveSceneDeal(SceneDeal sceneDeal) {
        try {
            sceneDeal.setCreateAt(new Date());
            Serializable id = this.save(sceneDeal);
            sceneDeal.setId(Integer.valueOf(id.toString()));
            return sceneDeal;
        } catch (Exception e) {
            LOGGER.error("保存案场成交信息异常",e);
            throw e ;
        }
    }

    /**
     * 根据ID查询指定信息
     *
     * @param id
     * @return
     */
    @Override
    public SceneDeal findByDealId(Integer id) {
        SceneDeal result =  this.findById(SceneDeal.class,id);
        if(result == null) return null ;
        return result;
    }

    /**
     * 根据CODE获取指定房链券信息
     *
     * @param code
     * @return
     */
    @Override
    public FlzcTicket findByTicketCode(String code) {
        String hql = "from FlzcTicket where ticketCode = '" + code +"'";
        List<FlzcTicket> flzcTickets = this.findByHql(hql) ;
        if (flzcTickets == null || flzcTickets.size() == 0) {
            return  null ;
        }
        FlzcTicket flzcTicket = flzcTickets.get(0) ;
        return flzcTicket;
    }

    /**
     * 获取用户信息
     *
     * @param id
     * @return
     */
    @Override
    public User findUserById(Integer id) {
        User user = this.findById(User.class,id) ;
        return user;
    }

    /**
     * 根据活动ID和活动类型，查询活动汇总表
     *
     * @param id
     * @param type
     * @return
     */
    @Override
    public ActivityRecap findActivityByIdAndType(Integer id, Integer type) {
        String hql = "from ActivityRecap where actId = " + id + " and type = " + type ;
        List<ActivityRecap> activityRecaps = this.findByHql(hql) ;
        if (activityRecaps == null || activityRecaps.size() == 0) {
            return  null ;
        }
        ActivityRecap activityRecap = activityRecaps.get(0) ;
        return activityRecap;
    }

    /**
     * 根据ID查询楼盘信息
     *
     * @param id
     * @return
     */
    @Override
    public HouseBuildingInfo findBuildingById(Integer id) {
        HouseBuildingInfo result =  this.findById(HouseBuildingInfo.class,id);
        if(result == null) return null ;
        return result;
    }
}
