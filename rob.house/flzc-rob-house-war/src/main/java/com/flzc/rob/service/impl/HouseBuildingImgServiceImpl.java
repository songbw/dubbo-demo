package com.flzc.rob.service.impl;

import com.flzc.base.dao.BaseDao;
import com.flzc.base.dao.CommonDao;
import com.flzc.base.service.BaseService;
import com.flzc.rob.api.entity.HouseBuildingImg;
import com.flzc.rob.api.service.HouseBuildingImgService;
import com.flzc.rob.util.ReflectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 楼盘图片接口实现类
 * Created by iverson on 2015/10/10.
 */
@Service("houseBuildingImgService")
public class HouseBuildingImgServiceImpl implements HouseBuildingImgService {
    private static final Logger LOGGER = LoggerFactory.getLogger(HouseBuildingImgServiceImpl.class);

    @Resource(name = "commonDao")
    private CommonDao commonDao;
    

    @Autowired
    private BaseDao baseDao;



    /**
     * 保存图片
     *
     * @param img
     * @return
     */
    @Override
    public Number save(HouseBuildingImg img) throws Exception {
        try {
            img.setUpdateTime(new Date());
            img.setStatus(0);
            return this.commonDao.save(img);
        } catch (Exception e) {
            LOGGER.error("保存楼盘信息图片异常", e);
            throw e;
        }
    }

    /**
     * 删除一条图片信息，逻辑删除
     *
     * @param id
     */
    @Override
    public void deleteById(Integer id) throws Exception {
        try {
            HouseBuildingImg old = this.queryById(id);
            old.setStatus(1);//图片无效
            old.setUpdateTime(new Date());
            this.commonDao.update(old);
        } catch (Exception e) {
            LOGGER.error("删除楼盘图片异常", e);
            throw e;
        }
    }

    /**
     * 更新图片。根据Id更新
     *
     * @param img
     */
    @Override
    public void update(HouseBuildingImg img) throws Exception {
        try {
            HouseBuildingImg old = this.queryById(img.getId());
            ReflectionUtil.copyProperties(old, img);
            old.setUpdateTime(new Date());
            this.commonDao.update(old);
        } catch (Exception e) {
            LOGGER.error("更新楼盘图片异常", e);
            throw e;
        }
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @Override
    public HouseBuildingImg queryById(Integer id) throws Exception {
        try {
            return this.commonDao.findById(HouseBuildingImg.class, id);
        } catch (Exception e) {
            LOGGER.error("查询楼盘图片异常", e);
            throw e;
        }
    }

    /**
     * 按参数查询楼盘图片
     *
     * @param params
     * @return
     */
    @Override
    public List<HouseBuildingImg> queryListByParams(HouseBuildingImg params) throws Exception {
        try {
            return this.commonDao.findObjs(params, null);
        } catch (Exception e) {
            LOGGER.error("查询楼盘图片异常", e);
            throw e;
        }
    }

	@Override
	public HouseBuildingImg queryHouseBuildingImgByCoverAndBuildingId(Integer buildingId) {

		String hql = "from HouseBuildingImg where status = 0 and mainPic = 0 and type = 0 and buildingId=:buildingId";
		List<HouseBuildingImg> list = baseDao.findByNamedParam(hql, "buildingId", buildingId);
		
		if(list == null || list.isEmpty())
			return null;
		
		return list.get(0);
	}

	@Override
	public List<HouseBuildingImg> queryHouseBuildingImgByTypeAndBuildingId(Integer buildingId) {

		String hql = "from HouseBuildingImg where type > 0 and status = 0 and buildingId = "+buildingId+" group by type";
		return this.baseDao.findByHql(hql);
	}

	@Override
	public List<HouseBuildingImg> queryHouseBuildingImgListByTypeAndBuildingId(
			Integer type,Integer buildingId) {

		String hql = "from HouseBuildingImg where status = 0 and buildingId=:buildingId and type=:type order by imgOrder asc";
		return this.baseDao.findByNamedParam(hql, 
				new String[]{"buildingId","type"}, new Object[]{buildingId,type});
	}
}
