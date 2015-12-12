package com.flzc.rob.service.impl;

import com.flzc.base.dao.CommonDao;
import com.flzc.rob.api.entity.HouseBuildingAround;
import com.flzc.rob.api.service.HouseBuildingAroundService;
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
@Service("houseBuildingAroundService")
public class HouseBuildingAroundServiceImpl implements HouseBuildingAroundService {
    private static final Logger LOGGER = LoggerFactory.getLogger(HouseBuildingAroundServiceImpl.class);

    @Resource(name = "commonDao")
    private CommonDao commonDao;

    /**
     * 保存配套信息
     *
     * @param around
     * @return
     */
    @Override
    public Number save(HouseBuildingAround around) throws Exception {
        try {
            around.setCreateTime(new Date());
           return  this.commonDao.save(around);
        } catch (Exception e) {
            LOGGER.error("保存楼盘周围配套信息异常",e);
            throw  e;
        }
    }

    /**
     * 删除配套信息
     *
     * @param id
     */
    @Override
    public void deleteById(Integer id) throws Exception {
        HouseBuildingAround old = null;
        try {
            old = this.commonDao.findById(HouseBuildingAround.class, id);
            this.commonDao.delete(old);
        } catch (Exception e) {
           LOGGER.error("删除楼盘周围配套信息异常",e);
            throw  e;
        }

    }

    /**
     * 根据楼盘id查询
     *
     * @param buildingId
     * @return
     */
    @Override
    public List<Map<String, Object>> queryByBuildingId(Integer buildingId) throws Exception {
        try {
            String sql = "SELECT " +
                    " t.code, " +
                    " t.name " +
                    "FROM " +
                    " house_building_tags t, " +
                    " house_building_around a " +
                    "WHERE " +
                    " t.code = a.tag_code " +
                    "AND a.building_id = " + buildingId;
            return  this.commonDao.findBySql(sql);
        } catch (Exception e) {
            LOGGER.error("查询楼盘周围配套信息异常",e);
            throw e;
        }
    }
}
