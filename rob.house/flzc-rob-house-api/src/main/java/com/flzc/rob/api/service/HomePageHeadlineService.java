package com.flzc.rob.api.service;

import com.flzc.rob.api.entity.HomePageHeadline;

import java.util.List;

/**
 * 新房首页头条接口
 * Created by iverson on 2015/10/20.
 */
public interface HomePageHeadlineService {
    /**
     * 保存数据
     * @param headline
     * @return
     */
    public Number save(HomePageHeadline headline) throws Exception;

    /**
     * 查询前几条数据
     * @param limit
     * @return
     */
    public List<HomePageHeadline> queryByLimit(int limit);
    
    /**
     * 根据活动汇总id分页查询记录
     * @Title: queryHomePageHeadlineByRecapIdAndPage 
     * @Description: TODO
     * @param recapId
     * @param page
     * @param pageSize
     * @return
     * @return: List<HomePageHeadline>
     */
    public List<HomePageHeadline> queryHomePageHeadlineByRecapIdAndPage(
    		Integer recapId,Integer page,Integer pageSize);

}
