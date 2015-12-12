package com.flzc.rob.service.impl;

import com.flzc.base.dao.CommonDao;
import com.flzc.rob.api.entity.HouseBuildingMemo;
import com.flzc.rob.api.service.HouseBuildingMemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 楼盘动态信息服务接口实现类
 * Created by iverson on 2015/10/10.
 */
@Service("houseBuildingMemoService")
public class HouseBuildingMemoServiceImpl implements HouseBuildingMemoService {
    private static final Logger LOGGER = LoggerFactory.getLogger(HouseBuildingMemoServiceImpl.class);

    @Resource(name = "commonDao")
    private CommonDao commonDao;
    /**
     * 保存动态信息
     *
     * @param memo
     * @return
     */
    @Override
    public Number save(HouseBuildingMemo memo) throws Exception {
        try {
            memo.setCreateTime(new Date());
            return this.commonDao.save(memo);
        } catch (Exception e) {
            LOGGER.error("保存楼盘配套信息",e);
            throw e;
        }
    }

    /**
     * 查询楼盘动态信息
     *
     * @param buildingId
     * @return
     */
    @Override
    public HouseBuildingMemo queryByBuildingId(Integer buildingId) throws Exception {
        HouseBuildingMemo memo = new HouseBuildingMemo();
        memo.setBuildingId(buildingId);
        try {
          return  this.commonDao.findUniqueObj(memo);
        } catch (Exception e) {
            LOGGER.error("查询楼盘配套信息",e);
            throw e;
        }
    }

    /**
     * 根据动态信息id更新
     *
     * @param id
     */
    @Override
    public void updateById(Integer id ,String memo) throws Exception {
        try {
            HouseBuildingMemo old = this.commonDao.findById(HouseBuildingMemo.class, id);
            old.setMemo(memo);
            old.setUpdateTime(new Date());
            this.commonDao.update(old);
        } catch (Exception e) {
           LOGGER.error("更新楼盘配套信息",e);
            throw  e;
        }
    }

    /**
     * 根据楼盘id更新动态信息
     *
     * @param buildingId
     */
    @Override
    public void updateByBuildingId(Integer buildingId , String memo) throws Exception {
        try {
            HouseBuildingMemo param = new HouseBuildingMemo();
            param.setBuildingId(buildingId);
            HouseBuildingMemo old = this.commonDao.findUniqueObj(param);
            old.setMemo(memo);
            old.setUpdateTime(new Date());
            this.commonDao.update(old);
        } catch (Exception e) {
            LOGGER.error("更新楼盘配套信息",e);
            throw  e;
        }
    }
}
