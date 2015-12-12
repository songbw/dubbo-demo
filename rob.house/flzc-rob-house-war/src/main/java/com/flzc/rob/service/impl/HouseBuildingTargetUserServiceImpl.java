package com.flzc.rob.service.impl;

import com.flzc.base.dao.CommonDao;
import com.flzc.rob.api.entity.HouseBuildingTargetUser;
import com.flzc.rob.api.service.HouseBuildingTargetUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 楼盘目标客户接口实现类
 */
@Service("houseBuildingTargetUserService")
public class HouseBuildingTargetUserServiceImpl implements HouseBuildingTargetUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(HouseBuildingTargetUserServiceImpl.class);

    @Autowired
    private CommonDao commonDao;
    /**
     * 查询楼盘目标客户
     *
     * @param buildingId
     * @return
     */
    @Override
    public HouseBuildingTargetUser queryByBuildingId(Integer buildingId) throws Exception {
        HouseBuildingTargetUser param = new HouseBuildingTargetUser();
        param.setBuildingId(buildingId);
        try {
            return  this.commonDao.findUniqueObj(param);
        } catch (Exception e) {
            LOGGER.error("查询楼盘目标客户异常：id="+buildingId,e);
            throw e;
        }
    }
}
