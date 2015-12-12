
package com.flzc.rob.service.impl;

import java.util.ArrayList;
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
import com.flzc.rob.api.entity.HouseBuildingView;
import com.flzc.rob.api.service.HouseBuildingViewService;
import com.flzc.rob.common.Constants;

@Service("houseBuildingViewService")
public class HouseBuildingViewServiceImpl implements HouseBuildingViewService {

	private final static Logger logger = LoggerFactory.getLogger(HouseBuildingViewServiceImpl.class);
	
	@Autowired
	private CommonDao commonDao;
	
	@Resource(name="baseServiceImpl")
	private BaseService baseService;
	
	@Override
	public Integer saveHouseBuildingView(HouseBuildingView houseBuildingView)
			throws Exception {
		
		try {
			Number share = commonDao.save(houseBuildingView);
			
			return share.intValue();
		} catch (Exception e) {
			logger.error("保存浏览楼盘报错",e);
			throw e;
		}
	}

	@Override
	public List<HouseBuildingView> queryUserHouseBuildingViewList(
			Integer userId) throws Exception {

		try {
			String hql =" from HouseBuildingView where userId=:userId  and status =0 ";
			List<HouseBuildingView> viewList = baseService.findByNamedParam(hql, "userId", userId);
			
			return viewList;
		} catch (Exception e) {
			logger.error("查询我浏览楼盘列表报错,用户Id："+userId,e);
			throw e;
		}
	}
	
	@Override
	public HouseBuildingView queryUserHouseBuildingViewById(Integer viewId)
			throws Exception {
		
		try {
			
			return commonDao.findById(HouseBuildingView.class, viewId);
		} catch (Exception e) {
			logger.error("查询我浏览的楼盘报错,浏览Id："+viewId,e);
			throw e;
		}
	}
	
	@Override
	public void deleteUserHouseBuildingView(HouseBuildingView houseBuildingView)
			throws Exception {
		
		try {
			
			commonDao.delete(houseBuildingView);
		} catch (Exception e) {
			logger.error("删除我浏览的楼盘报错",e);
			throw e;
		}
	}

	@Override
	public HouseBuildingView queryUserHouseBuildingView(HouseBuildingView houseBuildingView) 
			throws Exception {
		
		try {
			
			return commonDao.findUniqueObj(houseBuildingView);
		} catch (Exception e) {
			logger.error("查询我浏览的楼盘报错",e);
			throw e;
		}
	}

	@Override
	public List<Map<String, Object>> queryViewBuildingList(
			Map<String, Object> param, int curPage, int pageSize) {
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
                    " INNER JOIN house_building_view hbv on r.act_id=hbv.activity_id and r.type=hbv.type" +
                    " WHERE " +
                    " hi.main_pic=0 and hi.type=0 and b.building_state=40004 and r.act_status=0 and hbv.user_id=:userId" +
                    " order by  r.show_start_date desc";

           return  this.commonDao.findMapBySqlAndNamedParamPage(sql, keys, values, (curPage - 1) * pageSize, pageSize);
        } catch (Exception e) {
            logger.error("查询我的浏览列表异常",e);
            throw  e;
        }
    }

}
