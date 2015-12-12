package com.flzc.rob.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.flzc.base.service.impl.BaseServiceImpl;
import com.flzc.rob.api.service.AdService;

@Service("adService")
public class AdServiceImpl extends BaseServiceImpl implements AdService{

	@Override
	public List<Map<String, Object>> queryUserAPPAdByCity(Integer cityId) {

		String sql = "select a.url as imageUrl from ad_put_position app,ad_city_position acp,ad_schedule s,ad a WHERE app.id = acp.ad_position_id and acp.id = s.ad_city_position_id and s.ad_id = a.id and s.ad_city_position_id = acp.id and app.`code` = 'userAppHomePgeTopMiddle' and acp.city_id = "+cityId+" and s.begin_time < NOW() and s.end_time > NOW() order by s.show_order_index asc";
		return this.findListMapBySql(sql);
	}
}
