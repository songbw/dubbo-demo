package com.flzc.tags.service;

import com.flzc.base.service.BaseService;
import com.flzc.tags.bean.Area;
import com.flzc.tags.bean.City;
import com.flzc.tags.bean.Province;
import java.util.List;

public abstract interface AddressService extends BaseService {
	public abstract List<Province> getProvincesAll();

	public abstract List<City> getCitysAll();

	public abstract List<Area> getAreasAll();

	public abstract List<City> getCitysByProvinceId(Integer paramInteger);

	public abstract List<Area> getAreasByCityId(Integer paramInteger);

	public abstract Province getProvinceById(Integer paramInteger);

	public abstract City getCityById(Integer paramInteger);

	public abstract Area getAreaById(Integer paramInteger);

	public abstract Province getProvinceFromMemcached(Integer paramInteger);

	public abstract List<Province> getProvinceFromMemcached();

	public abstract City getCityFromMemcached(Integer paramInteger);

	public abstract List<City> getCitysFromMemcached(Integer paramInteger);

	public abstract Area getAreaFromMemcached(Integer paramInteger);

	public abstract List<Area> getAreasFromMemcached(Integer paramInteger);

	public Integer  getCityIdByName(String cityName) throws Exception;
}
