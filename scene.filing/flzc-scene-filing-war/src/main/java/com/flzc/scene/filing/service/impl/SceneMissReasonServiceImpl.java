package com.flzc.scene.filing.service.impl;

import com.flzc.base.service.impl.BaseServiceImpl;
import com.flzc.scene.filing.api.entity.SceneMissReason;
import com.flzc.scene.filing.api.service.SceneMissReasonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Date;

/**
 *  爽约原因服务实现类
 * Created by song on 2015/11/18.
 */
@Service("sceneMissReasonService")
public class SceneMissReasonServiceImpl extends BaseServiceImpl implements SceneMissReasonService {
    private static  final Logger LOGGER = LoggerFactory.getLogger(SceneMissReasonServiceImpl.class);
    /**
     * 保存爽约原因服务
     *
     * @param sceneMissReason
     * @return
     */
    @Override
    public SceneMissReason saveSceneMissReason(SceneMissReason sceneMissReason) {
        try {
            sceneMissReason.setCreateAt(new Date());
            Serializable id = this.save(sceneMissReason) ;
            sceneMissReason.setId(Integer.valueOf(id.toString()));
            return sceneMissReason;
        } catch (Exception e) {
            LOGGER.error("保存爽约原因异常",e);
            throw e ;
        }
    }
}
