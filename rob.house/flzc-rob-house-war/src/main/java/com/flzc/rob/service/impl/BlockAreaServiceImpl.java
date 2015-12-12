package com.flzc.rob.service.impl;

import com.flzc.base.dao.CommonDao;
import com.flzc.rob.api.entity.BlockArea;
import com.flzc.rob.api.service.BlockAreaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by iverson on 2015/10/10.
 */
@Service("blockAreaService")
public class BlockAreaServiceImpl implements BlockAreaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BlockAreaServiceImpl.class);

    @Resource(name = "commonDao")
    private CommonDao commonDao;

    /**
     * 保存
     *
     * @param blockArea
     * @return
     */
    @Override
    public Number save(BlockArea blockArea) throws Exception {
        try {
            blockArea.setCreateTime(new Date());
           return  this.commonDao.save(blockArea);
        } catch (Exception e) {
            LOGGER.error("保存小区异常",e);
            throw e;
        }
    }

    /**
     * 根据条件查询小区
     *
     * @param blockArea
     * @return
     */
    @Override
    public List<BlockArea> queryByParams(BlockArea blockArea) throws Exception {
        try {
            return  this.commonDao.findObjs(blockArea);
        } catch (Exception e) {
            LOGGER.error("查询小区异常",e);
            throw e;
        }
    }

    /**
     * 根据小区名字查询
     *
     * @param name
     * @return
     */
    @Override
    public BlockArea queryByNamme(String name) throws Exception {
        BlockArea param = new BlockArea();
        param.setName(name);
        try {
            return  this.commonDao.findUniqueObj(param);
        } catch (Exception e) {
            LOGGER.error("查询小区异常",e);
            throw e;
        }
    }
}
