package com.flzc.rob.service.impl;

import com.flzc.base.dao.BaseDao;
import com.flzc.base.dao.CommonDao;
import com.flzc.rob.api.entity.HouseBuildingTagsLog;
import com.flzc.rob.api.service.HouseBuildingTagsLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by iverson on 2015/10/10.
 */
@Service("houseBuildingTagsLogService")
public class HouseBuildingTagsLogServiceImpl implements HouseBuildingTagsLogService {

    private static final Logger LOGGER = LoggerFactory.getLogger(HouseBuildingTagsLogServiceImpl.class);

    @Resource(name = "commonDao")
    private CommonDao commonDao;

    @Resource(name = "baseDaoImpl")
    private BaseDao baseDao;


    /**
     * 保存日志
     *
     * @param log
     * @return
     */
    @Override
    public Number save(HouseBuildingTagsLog log) throws Exception {
        try {
            log.setCreateTime(new Date());
            return  this.commonDao.save(log);
        } catch (Exception e) {
           LOGGER.error("保存楼盘标签日志异常",e);
            throw  e;
        }
    }

    /**
     * 根据条件查询
     *
     * @param param
     * @return
     */
    @Override
    public List<HouseBuildingTagsLog> queryByParams(HouseBuildingTagsLog param) throws Exception {
        try {
           return  this.commonDao.findObjs(param);
        } catch (Exception e) {
            LOGGER.error("查询楼盘标签日志异常",e);
            throw e;
        }
    }
}
