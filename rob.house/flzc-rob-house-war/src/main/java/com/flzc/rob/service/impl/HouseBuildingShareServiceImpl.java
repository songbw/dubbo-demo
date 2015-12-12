
package com.flzc.rob.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.flzc.base.dao.CommonDao;
import com.flzc.base.service.BaseService;
import com.flzc.base.util.CommonUtils;
import com.flzc.base.util.DateUtil;
import com.flzc.rob.api.entity.HouseBuildingShare;
import com.flzc.rob.api.service.HouseBuildingShareService;
import com.flzc.rob.common.Constants;

@Service("houseBuildingShareService")
public class HouseBuildingShareServiceImpl implements HouseBuildingShareService {

	private final static Logger logger = LoggerFactory.getLogger(HouseBuildingShareServiceImpl.class);
	
	@Autowired
	private CommonDao commonDao;
	
	@Resource(name="baseServiceImpl")
	private BaseService baseService;
	
	@Override
	public Integer saveHouseBuildingShare(HouseBuildingShare houseBuildingShare) throws Exception {
		try {
			houseBuildingShare.setCreateTime(new Date());
			Number share = commonDao.save(houseBuildingShare);
			return share.intValue();
		} catch (Exception e) {
			logger.error("分享楼盘报错",e);
			throw e;
		}
	}

	@Override
	public List<HouseBuildingShare> queryUserHouseBuildingShareList(
			Integer userId) throws Exception {

		try {
			String hql =" from HouseBuildingShare where userId=:userId  and status =0 ";
			List<HouseBuildingShare> shareList = baseService.findByNamedParam(hql, "userId", userId);
			
			return shareList;
		} catch (Exception e) {
			logger.error("查询我分享楼盘列表报错,用户Id："+userId,e);
			throw e;
		}
	}

	@Override
	public void deleteUserHouseBuildingShare(HouseBuildingShare houseBuildingShare) 
			throws Exception {
		
		try {
			commonDao.delete(houseBuildingShare);
		} catch (Exception e) {
			logger.error("删除我分享的楼盘报错",e);
			throw e;
		}
	}
	
	@Override
	public HouseBuildingShare queryUserHouseBuildingShareById(Integer shareId)
			throws Exception {
		
		try {
			
			return commonDao.findById(HouseBuildingShare.class, shareId);
		} catch (Exception e) {
			logger.error("查询我分享的楼盘报错,分享Id："+shareId,e);
			throw e;
		}
	}

	@Override
	public HouseBuildingShare queryUserHouseBuildingShare(
			HouseBuildingShare houseBuildingShare) throws Exception {
		
		try {
			
			return commonDao.findUniqueObj(houseBuildingShare);
		} catch (Exception e) {
			logger.error("查询我分享的楼盘报错",e);
			throw e;
		}
	}

	@Override
	public Integer queryUserShareTimes(Integer userId) throws Exception {
		
		try {
			
			String currentDate = DateUtil.format(new Date(), "yyyy-MM-dd");
			String hql = "select count(id) from HouseBuildingShare where userId="+userId +" and createTime like '"+currentDate+"%'";
			
			List<Long> list = baseService.findByHql(hql);
			if(list==null || list.isEmpty())
				return 0;
			
			String times = String.valueOf(list.get(0));
			return (times==null || times=="")?0:Integer.parseInt(times);
			
		} catch (Exception e) {
			logger.error("查询用户当天收藏次数报错",e);
			throw e;
		}
	}

	@Override
	public List<Map<String, Object>> queryShareBuildingList(Map<String, Object> param,
			int curPage, int pageSize) {
        if(curPage==0) curPage++;
        if(pageSize==0) pageSize = 10;
        List<String> keys = new ArrayList<>();
        List<Object> values = new ArrayList<>();
        logger.info("参数：" + JSONObject.toJSONString(param));
        try {
            param =  CommonUtils.clearMapBlankVal(param);
            Double longitude = Double.valueOf( param.get("longitude").toString());
            Double latitude = Double.valueOf( param.get("latitude").toString());
            keys.add(Constants.LATITUDE_0);
            keys.add(Constants.LATITUDE_1);
            keys.add(Constants.LONGITUDE);
            keys.add("userId");
            
            values.add(latitude);
            values.add(latitude);
            values.add(longitude);
            values.add(Integer.valueOf(String.valueOf(param.get("userId"))));
            
            String sql = "SELECT DISTINCT " + Constants.DISTANCE_FORMULA + "," +
                    "  b.id," +
                    "  r.act_id as actId," +
                    "  b.`name` ," +
                    "  b.refer_price as referPrice ," +
                    "  b.total_num as totalNum," +
                    "  b.sale_num as saleNum," +
                    "  b.latitude," +
                    "  b.longitude ," +
                    "  b.address," +
                    "  b.decoration," +
                    "  r.type ," +
                    "  b.area_id as areaId , " +
                    "  hi.img_url as imgUrl" +
                     "  FROM " +
                    "  house_building_info b " +
                    " INNER JOIN house_building_img hi on b.id = hi.building_id " +
                    " LEFT JOIN  activity_recap r  on  b.id = r.building_id " +
                    " INNER JOIN house_building_share hbs on r.act_id=hbs.activity_id and r.type=hbs.type" +
                    " WHERE " +
                    " hi.main_pic=0 and hi.type=0 and b.building_state=40004 and r.act_status=0 and hbs.user_id=:userId" +
                    " order by  r.show_start_date desc";

           return  this.commonDao.findMapBySqlAndNamedParamPage(sql, keys, values, (curPage - 1) * pageSize, pageSize);
        } catch (Exception e) {
            logger.error("查询我的分享列表异常",e);
            throw  e;
        }

    }
	
	
}
