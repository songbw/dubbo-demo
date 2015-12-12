package com.flzc.rob.service.impl;

import com.flzc.base.dao.BaseDao;
import com.flzc.base.dao.CommonDao;
import com.flzc.rob.api.entity.HouseBuildingTagsLink;
import com.flzc.rob.api.service.HouseBuildingTagsLinkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 楼盘与标签关联关系接口服务实现类
 * Created by iverson on 2015/10/10.
 */
@Service("houseBuildingTagsLinkService")
public class HouseBuildingTagsLinkServiceImpl implements HouseBuildingTagsLinkService {

    private static final Logger LOGGER = LoggerFactory.getLogger(HouseBuildingTagsLinkServiceImpl.class);
    private static final Integer VALID = 0;
    private static final Integer INVALID = 1;

    @Resource(name = "commonDao")
    private CommonDao commonDao;

    @Resource(name = "baseDaoImpl")
    private BaseDao baseDao;

    @Override
    public Number save(HouseBuildingTagsLink link) throws Exception {
        try {
            link.setStatus(VALID);
            link.setCreateTime(new Date());
           return  this.commonDao.save(link);
        } catch (Exception e) {
            LOGGER.error("保存楼盘标签关系异常",e);
            throw e;
        }
    }

    @Override
    public void deleteById(Integer id) throws Exception {
        try {
            HouseBuildingTagsLink old = this.commonDao.findById(HouseBuildingTagsLink.class, id);
            old.setUpdateTime(new Date());
            old.setStatus(INVALID);
            this.commonDao.update(old);
        } catch (Exception e) {
            LOGGER.error("删除楼盘标签关系异常",e);
            throw e;
        }
    }

    @Override
    public List<HouseBuildingTagsLink> queryByBuildingId(Integer buildingId) throws Exception {
        try {
            HouseBuildingTagsLink link = new HouseBuildingTagsLink();
            link.setStatus(VALID);
            link.setBuildingId(buildingId);
            List<HouseBuildingTagsLink> links = this.commonDao.findObjs(link);
            return links;
        } catch (Exception e) {
           LOGGER.error("查询楼盘标签关系异常",e);
            throw  e;
        }

    }
}
