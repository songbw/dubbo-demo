package com.flzc.rob.api.service;

import com.flzc.rob.api.entity.HouseImg;

import java.util.List;

/**
 * 户型图片服务接口
 * Created by iverson on 2015/10/10.
 */
public interface HouseImgService {
    /**
     * 保存图片
     * @param img
     * @return
     */
    public Number save(HouseImg img) throws Exception;

    /**
     * 删除一条图片信息，逻辑删除
     * @param id
     */
    public void deleteById(Integer id) throws Exception;

    /**
     * 更新图片。根据Id更新
     * @param img
     */
    public void update(HouseImg img) throws Exception;

    /**
     * 根据id查询
     * @param id
     * @return
     */
    public HouseImg queryById(Integer id) throws Exception;

    /**
     * 按参数查询户型图片
     * @param params
     * @return
     */
    public List<HouseImg> queryListByParams(HouseImg params) throws Exception;
}
