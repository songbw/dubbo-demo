package com.flzc.rob.service.impl;

import com.flzc.base.dao.BaseDao;
import com.flzc.base.dao.CommonDao;
import com.flzc.rob.api.entity.HouseInfo;
import com.flzc.rob.api.service.HouseInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by iverson on 2015/10/10.
 */
@Service("houseInfoService")
public class HouseInfoServiceImpl implements HouseInfoService {
    private static final Logger LOGGER = LoggerFactory.getLogger(HouseInfoServiceImpl.class);

    @Resource(name = "commonDao")
    private CommonDao commonDao;

    @Resource(name = "baseDaoImpl")
    private BaseDao baseDao;



    @Override
    public Number save(HouseInfo info) throws Exception {
        try {
            info.setCreateTime(new Date());
            return  this.commonDao.save(info);
        } catch (Exception e) {
            LOGGER.error("保存户型异常",e);
            throw  e;
        }
    }

    @Override
    public List<HouseInfo> queryByBuildingId(Integer buildingId) throws Exception {
        HouseInfo info = new HouseInfo();
        info.setBuildingId(buildingId);
        try {
           return   this.commonDao.findObjs(info);
        } catch (Exception e) {
            LOGGER.error("查询户型异常",e);
            throw  e;
        }
    }

    @Override
    public HouseInfo queryById(Integer id) throws Exception {
        try {
            return   this.commonDao.findById(HouseInfo.class,id);
        } catch (Exception e) {
            LOGGER.error("查询户型异常",e);
            throw  e;
        }
    }

    @Override
    public List<Map<String, Object>> queryAll() throws Exception {
    	try {
            String sql = "SELECT sd.name as house, sd.code as houseCode FROM  house_building_tags sd WHERE sd.parent_code = 100 ";
            return   this.commonDao.findBySql(sql);
        } catch (Exception e) {
            LOGGER.error("查询户型列表异常",e);
            throw e;
        }
    }
}
