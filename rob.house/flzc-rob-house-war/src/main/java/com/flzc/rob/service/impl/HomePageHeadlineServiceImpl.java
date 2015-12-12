package com.flzc.rob.service.impl;

import com.flzc.base.dao.BaseDao;
import com.flzc.base.dao.CommonDao;
import com.flzc.base.service.impl.BaseServiceImpl;
import com.flzc.rob.api.entity.HomePageHeadline;
import com.flzc.rob.api.service.HomePageHeadlineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 新房首页头条接口实现类
 * Created by iverson on 2015/10/20.
 */
@Service("homePageHeadlineService")
public class HomePageHeadlineServiceImpl extends BaseServiceImpl implements HomePageHeadlineService {
    private static  final Logger LOGGER = LoggerFactory.getLogger(HomePageHeadlineServiceImpl.class);

    @Autowired
    private CommonDao commonDao;

    @Autowired
    private BaseDao baseDao;

    /**
     * 保存数据
     *
     * @param headline
     * @return
     */
    @Override
    public Number save(HomePageHeadline headline) throws Exception {
        try {
            return  this.commonDao.save(headline);
        } catch (Exception e) {
            LOGGER.error("保存头条异常",e);
            throw e;
        }
    }

    /**
     * 查询前几条数据
     *
     * @param limit
     * @return
     */
    @Override
    public List<HomePageHeadline> queryByLimit(int limit) {
        try {
            return  this.baseDao.findByHql("from HomePageHeadline h where h.status = 0  order by h.id desc",limit);
        } catch (Exception e) {
           LOGGER.error("查询新房首页列表异常",e);
            throw e;
        }
    }

	@Override
	public List<HomePageHeadline> queryHomePageHeadlineByRecapIdAndPage(
			Integer recapId, Integer page,
			Integer pageSize) {

		String hql = "from HomePageHeadline where recapId= " + recapId;
		int minSize = (page - 1) * pageSize;
		List<HomePageHeadline> list = this.findByHql(hql, minSize, pageSize);
		return list;
	}
}
