package com.flzc.rob.api.service;

import com.flzc.rob.api.entity.BlockArea;

import java.util.List;

/**
 * 小区服务接口
 * Created by iverson on 2015/10/10.
 */
public interface BlockAreaService {

    /**
     * 保存
     * @param blockArea
     * @return
     */
    public Number save(BlockArea blockArea) throws Exception;

    /**
     * 根据条件查询小区
     * @param blockArea
     * @return
     */
    public List<BlockArea> queryByParams(BlockArea blockArea) throws Exception;

    /**
     * 根据小区名字查询
     * @param name
     * @return
     */
    public BlockArea queryByNamme(String name) throws Exception;
}
