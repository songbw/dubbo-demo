package com.flzc.scene.filing.service.impl;

import com.flzc.base.service.impl.BaseServiceImpl;
import com.flzc.scene.filing.api.entity.SceneDealImg;
import com.flzc.scene.filing.api.service.SceneDealImgService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Date;

/**
 *  成交合同图片服务实现类
 * Created by song on 2015/11/18.
 */
@Service("sceneDealImgService")
public class SceneDealImgServiceImpl extends BaseServiceImpl implements SceneDealImgService{
    private static  final Logger LOGGER = LoggerFactory.getLogger(SceneDealServiceImpl.class);
    /**
     * 保存合同图片信息
     *
     * @param sceneDealImg
     * @return
     */
    @Override
    public SceneDealImg saveSceneDealImg(SceneDealImg sceneDealImg) {
        try {
            sceneDealImg.setCreateAt(new Date());
            Serializable id = this.save(sceneDealImg) ;
            sceneDealImg.setId(Integer.valueOf(id.toString()));
            return sceneDealImg;
        } catch (Exception e) {
            LOGGER.error("保存成交合同图片信息异常",e);
            throw e ;
        }
    }
}
