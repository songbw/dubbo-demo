package com.flzc.service.service;

import com.flzc.scene.filing.api.entity.SceneDeal;
import com.flzc.scene.filing.api.entity.SceneDealImg;
import com.flzc.service.bean.SceneDealBean;
import com.flzc.service.bean.SceneFilingView;
import com.flzc.service.exception.SceneException;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.InputStream;

/**
 * 添加成交信息
 *
 * Created by song on 2015/11/21.
 */
public interface SceneDealBizService {
    /**
     *  添加成交信息
     * @param sceneDeal
     * @return
     * @throws SceneException
     */
    public String addDeal(String token ,SceneDeal sceneDeal) throws SceneException ;

    /**
     *  添加图片接口
     * @param tokenId
     * @return
     * @throws SceneException
     */
    public String addDealImg(String tokenId, InputStream file, String originalFilename, String storePath, Integer dealId) throws SceneException ;

    /**
     *  获取指定成交信息
     * @param token
     * @param id
     * @return
     * @throws SceneException
     */
    public SceneDeal findDealById(String token,Integer id) throws SceneException ;

    /**
     *  核销
     * @param tokenId
     * @param code
     * @return
     * @throws SceneException
     */
    public SceneFilingView verification(String tokenId, String code) throws  SceneException ;
}
