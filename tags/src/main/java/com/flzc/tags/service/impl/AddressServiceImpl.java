package com.flzc.tags.service.impl;

import com.flzc.base.dao.CommonDao;
import com.flzc.base.service.impl.BaseServiceImpl;
import com.flzc.base.util.Memcached;
import com.flzc.base.util.MemcachedKeyConstant;
import com.flzc.tags.bean.Area;
import com.flzc.tags.bean.City;
import com.flzc.tags.bean.Province;
import com.flzc.tags.service.AddressService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl extends BaseServiceImpl implements AddressService {

	@Autowired
	private CommonDao commonDao;

	public List<Province> getProvincesAll() {
		return findAll("Province");
	}

	public List<City> getCitysByProvinceId(Integer provinceId) {
		return findByNamedParam("From City t where t.provinceId = :provinceId", "provinceId", provinceId);
	}

	public List<Area> getAreasByCityId(Integer cityId) {
		return findByNamedParam("From Area t where t.cityId = :cityId", "cityId", cityId);
	}

	public List<City> getCitysAll() {
		return findAll("City");
	}

	public List<Area> getAreasAll() {
		return findAll("Area");
	}

	public Province getProvinceById(Integer id) {
		return (Province) findById(Province.class, id);
	}

	public City getCityById(Integer id) {
		return (City) findById(City.class, id);
	}

	public Area getAreaById(Integer id) {
		return (Area) findById(Area.class, id);
	}

	public Province getProvinceFromMemcached(Integer provinceId) {
		Province p = (Province) Memcached.get("prov.list.all" + provinceId);
		if (p != null) {
			return p;
		}
		p = getProvinceById(provinceId);
		Memcached.set("prov.list.all" + provinceId, p);
		return p;
	}

	public List<Province> getProvinceFromMemcached() {
		List ps = (List) Memcached.get("prov.list.all");
		if ((ps != null) && (ps.size() > 0)) {
			return ps;
		}
		ps = getProvincesAll();
		Memcached.set("prov.list.all", ps);
		return ps;
	}

	public City getCityFromMemcached(Integer cityId) {
		City city = (City) Memcached.get("city.list.all" + cityId);
		if (city != null) {
			return city;
		}
		city = getCityById(cityId);
		Memcached.set("city.list.all" + cityId, city);
		return city;
	}

	public List<City> getCitysFromMemcached(Integer provinceId) {
		List citys = (List) Memcached.get("city." + provinceId);
		if ((citys != null) && (citys.size() > 0)) {
			return citys;
		}
		citys = getCitysByProvinceId(provinceId);
		Memcached.set("city." + provinceId, citys);
		return citys;
	}

	public Area getAreaFromMemcached(Integer areaId) {
		Area area = (Area) Memcached.get("area.list.all" + areaId);
		if (area != null) {
			return area;
		}
		area = getAreaById(areaId);
		Memcached.set("area.list.all" + areaId, area);
		return area;
	}

	public List<Area> getAreasFromMemcached(Integer cityId) {
		List areas = (List) Memcached.get("area." + cityId);
		if ((areas != null) && (areas.size() > 0)) {
			return areas;
		}
		areas = getAreasByCityId(cityId);
		Memcached.set("area." + cityId, areas);
		return areas;
	}

	public Integer getCityIdByName(String cityName) throws Exception {
		String key = MemcachedKeyConstant.CITY +"id." + cityName ;
		Integer cityId = (Integer) Memcached.get(key);
		if(cityId==null){
			City city = new City();
			city.setCityName(cityName);
			city =  this.commonDao.findUniqueObj(city);
			if(city == null) return 0;
			cityId = city.getId();
			Memcached.set(key,cityId);
		}
		return cityId;
	}
}
