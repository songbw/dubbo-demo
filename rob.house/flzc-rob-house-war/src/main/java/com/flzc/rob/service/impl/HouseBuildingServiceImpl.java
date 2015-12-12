package com.flzc.rob.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.flzc.rob.api.entity.BuilderApprove;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.flzc.base.dao.BaseDao;
import com.flzc.base.dao.CommonDao;
import com.flzc.base.util.CommonUtils;
import com.flzc.rob.api.entity.HouseBuildingInfo;
import com.flzc.rob.api.service.HouseBuildingService;
import com.flzc.rob.common.Constants;
import com.flzc.rob.util.ReflectionUtil;
import com.flzc.rob.util.StringUtils;
import com.flzc.tags.bean.City;

/**
 * 楼盘服务接口实现类
 * Created by iverson on 2015/10/10.
 */
@Service("houseBuildingService")
public class HouseBuildingServiceImpl implements HouseBuildingService {
    private static final Logger LOGGER = LoggerFactory.getLogger(HouseBuildingServiceImpl.class);

    //新房首页查询标签条件
    @Value("${TAG_KEYS}")
    private  String   TAG_KEYS ;
    @Value("${EXCLUDE_KEYS}")
    private  String   EXCLUDE_KEYS ;


     @Resource(name = "commonDao")
     private CommonDao commonDao;

     @Autowired
     private BaseDao baseDao;

    /**
     * 保存楼盘信息
     *
     * @param info
     * @return
     */
    @Override
    public Number save(HouseBuildingInfo info) throws Exception{
        try {
            return this.commonDao.save(info);
        } catch (Exception e) {
            LOGGER.error("保存楼盘信息异常",e);
            throw e;
        }
    }

    /**
     * 修改楼盘信息。根据id修改.
     *
     * @param info 包含要修改的字段以及id
     * @return
     */
    @Override
    public void updateById(HouseBuildingInfo info) throws Exception{
        try {
            HouseBuildingInfo old = this.commonDao.findById(HouseBuildingInfo.class, info.getId());
            ReflectionUtil.copyProperties(old, info);
            this.commonDao.update(old);
        } catch (Exception e) {
            LOGGER.error("更新楼盘信息异常",e);
            throw e;
        }
    }

    /**
     * 根据id查询楼盘信息
     *
     * @param id
     * @return
     */
    @Override
    public HouseBuildingInfo queryById(Integer id) throws Exception {
        if( id == null ) return null;
        try {
            return this.commonDao.findById(HouseBuildingInfo.class, id);
        } catch (Exception e) {
            LOGGER.error("根据id查询楼盘异常",e);
            throw  e;
        }
    }

    /**
     * 根据多个id查询楼盘信息
     *
     * @param ids
     * @return
     */
    @Override
    public List<HouseBuildingInfo>  queryByIds(List<Integer> ids) throws Exception {
        String ii = StringUtils.joinArray(ids ,null);
        String hql = "from HouseBuildingInfo o where o.buildingState = 40001 and o.id in(%1s)";
        try {
            if(org.apache.commons.lang.StringUtils.isNotBlank(ii)){
                return   this.baseDao.findByHql(String.format(hql,ii));
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            LOGGER.error("根据多个楼盘id查询异常",e);
            throw e;
        }
    }

    /**
     * 根据条件查询出楼盘信息
     *
     * @param params
     * @return
     */
    @Override
    public HouseBuildingInfo queryUniqueByParams(HouseBuildingInfo params) throws Exception {
        try {
        	params.setBuildStage(40001);
            List<HouseBuildingInfo> objs = this.commonDao.findObjs(params);
            if(objs != null && objs.size() == 1){
                return objs.get(0);
            }else{
                LOGGER.error("查询结果多于一条");
                throw new Exception("查询结果多于一条");
            }

        } catch (Exception e) {
            LOGGER.error("根据条件查询楼盘信息异常",e);
            throw e;
        }
    }

    /**
     * 根据条件查询出多个楼盘信息
     *
     * @param params
     * @return
     */
    @Override
    public List<HouseBuildingInfo> queryListByParams(HouseBuildingInfo params) throws Exception {
        try {
        	params.setBuildStage(40001);
           return this.commonDao.findObjs(params, null);
        } catch (Exception e) {
            LOGGER.error("根据条件查询楼盘信息异常",e);
            throw e;
        }
    }

    /**
     * 根据城市id与活动类型查询
     *
     * @param cityName ,actType, latitude,longitude
     * @param actType 39001:答题，39002：定制，39003：竞拍,39004:只有抵扣活动
     * @param curPage
     * @param pageSize
     * @return
     */
    @Override
    public List<Map<String, Object>> queryListByActAndCity( Map<String,Object> params , int curPage, int pageSize) throws Exception {
        if(curPage==0) curPage ++;
        if(pageSize == 0) pageSize = 10;
        List<String> keys = new ArrayList<>();
        List<Object> values = new ArrayList<>();
        LOGGER.info("参数：" + JSONObject.toJSONString(params));

        try {
            params =  CommonUtils.clearMapBlankVal(params);
            Double longitude = Double.valueOf( params.get("longitude").toString());
            Double latitude = Double.valueOf( params.get("latitude").toString());
            keys.add(Constants.LATITUDE_0);
            keys.add(Constants.LATITUDE_1);
            keys.add(Constants.LONGITUDE);
            values.add(latitude);
            values.add(latitude);
            values.add(longitude);

            Object cityName = params.get("cityName");
            City city = new City();
            city.setCityName(cityName.toString());
            Integer cityId = this.commonDao.findUniqueObj(city).getId();


            Object actType = params.get("actType");
            keys.add("cityId");
            values.add(cityId);

            Integer at = Integer.valueOf(actType.toString());
            String sql = "SELECT " + Constants.DISTANCE_FORMULA + "," +
                    "  b.id," +
                    "  a.id as actId , " +
                    "  b.`name` ," +
                    "  b.refer_price as referPrice ," +
                    "  b.latitude," +
                    "  b.longitude ," +
                    "  b.address," +
                    "  a.act_start_date as startDate," +
                    "  a.act_end_date as endDate," +
                    "  a.act_active_end_date as activeEndDate , " +
                    "  a.act_active_start_date as activeStartDate , " +
                    "  hi.img_url as imgUrl,b.sold_num as soldNum,b.total_num as totalNum  " + dynamicColumnForActivity(at) +
                    "  FROM " +
                    "  house_building_info b " +
                    "  ,house_building_img hi,"  + dynamicSqlForActivity(at) +
                    " and b.id=hi.building_id and hi.main_pic=0 and hi.type=0 and b.building_state=40001 and ar.type = "+actType+" and a.status=0 and b.city_id=:cityId"  + " group by actId order by a.act_start_date desc";
            return  this.commonDao.findMapBySqlAndNamedParamPage(sql, keys, values, (curPage - 1) * pageSize, pageSize);
        } catch (Exception e) {
            LOGGER.error("根据活动查询楼盘异常",e);
            throw e;
        }
    }
    private String dynamicColumnForActivity(Integer actType){
        if(actType == null) return "";
        String col = "";
        switch (actType){
            case Constants.ACTIVITY_ANSWER:
               // col= String.format(" concat('免费领取',w.level_value，'购房券') as title , %1s  as type" , Constants.ACTIVITY_ANSWER ) ;
                break;
            case Constants.ACTIVITY_CUSTOMIZATION:
                col=  String.format(",'%1s' as title , %2s as type ","接受专属订制",Constants.ACTIVITY_CUSTOMIZATION) ;
                break;
            case Constants.ACTIVITY_AUCTION:
                col=  String.format(",'起拍价%1s元' as title , %2s as type  "," a.start_price",Constants.ACTIVITY_AUCTION)   ;
                break;
            case Constants.ACTIVITY_DIKOU:
              //  col =
                break;
            default:

                break;
        }
        return col;

    }
    private String dynamicSqlForActivity(Integer actType){
        if(actType == null) return "";
        String table = "";
        switch (actType){
            case Constants.ACTIVITY_ANSWER:
                table = " answer_question_activity a,activity_recap ar where ar.building_id = b.id and ar.act_id = a.id and a.building_id = b.id ";
                break;
            case Constants.ACTIVITY_CUSTOMIZATION:
                table = " property_customization_activity a,activity_recap ar where ar.building_id = b.id and ar.act_id = a.id and a.building_id = b.id ";
                break;
            case Constants.ACTIVITY_AUCTION:
                table = " aution_activity_info a,activity_recap ar where ar.building_id = b.id and ar.act_id = a.id and a.building_id = b.id ";
                break;
//            case  Constants.ACTIVITY_DIKOU:
//                table = " inner join activity_recap a on a.building_id=b.id and a.type=" + actType ;
            default:
                break;
        }
        return table;
    }

    /**
     * 新房首页查询,复杂查询。
     * @param param key 有areaId;referPrice;around;feature;buildingType;house. 其中referPrice为low-up格式，表示s的是一个区间
     *             areaId:区域，referPrice：价格，around:表示周边配套， buildingType：楼盘类型，house：户型
     *             加latitude:纬度 ，longitude: 经度
     *              外加4个接门标签搜索：这4个参数命名为 hotTag1,hotTag2,hotTag3,hotTag4,
     * @return
     */
    @Override
    public List<Map<String, Object>> queryList(Map<String, Object> param ,int curPage , int pageSize) {
        if(curPage==0) curPage++;
        if(pageSize==0) pageSize = 10;
        List<String> keys = new ArrayList<>();
        List<Object> values = new ArrayList<>();
        LOGGER.info("参数：" + JSONObject.toJSONString(param));
        try {
            param =  CommonUtils.clearMapBlankVal(param);
            Double longitude = Double.valueOf( param.get("longitude").toString());
            Double latitude = Double.valueOf( param.get("latitude").toString());
            keys.add(Constants.LATITUDE_0);
            keys.add(Constants.LATITUDE_1);
            keys.add(Constants.LONGITUDE);
            values.add(latitude);
            values.add(latitude);
            values.add(longitude);

            String sql = "SELECT " + Constants.DISTANCE_FORMULA + "," +
                    "  b.id," +
                    "  r.act_id as actId," +
                    "  b.`name` ," +
                    "  b.refer_price as referPrice ," +
                    "  b.total_num as totalNum," +
                    "  b.latitude," +
                    "  b.longitude ," +
                    "  b.address," +
                    "  b.decoration," +
                    "  r.type ," +
                    "  b.area_id as areaId , " +
                    "  hi.img_url as imgUrl,b.sold_num as soldNum  " +
                     "  FROM " +
                    "  house_building_info b " +
                    "  LEFT JOIN  activity_recap r  on  b.id = building_id " +
                    " INNER JOIN house_building_img hi on b.id = hi.building_id " + dynamicTableSql(param) +
                    " WHERE " +
                    " hi.main_pic=0 and hi.type=0 and b.building_state=40001 and r.act_status=0 " + dynamicWhereCondition(param,keys,values) + " order by  r.show_start_date desc";

           return  this.commonDao.findMapBySqlAndNamedParamPage(sql, keys, values, (curPage - 1) * pageSize, pageSize);
        } catch (Exception e) {
            LOGGER.error("查询新房列表异常",e);
            throw  e;
        }

    }
    
    /**
     * 查询用户参与活动楼盘
     * @param param key userId
     * @return
     */
    @Override
    public List<Map<String, Object>> queryActivityBuildingListByUserId(Map<String, Object> param ,int curPage , int pageSize) {
        if(curPage==0) curPage++;
        if(pageSize==0) pageSize = 10;
        List<String> keys = new ArrayList<>();
        List<Object> values = new ArrayList<>();
        LOGGER.info("参数：" + JSONObject.toJSONString(param));
        try {
            param =  CommonUtils.clearMapBlankVal(param);
            Double longitude = Double.valueOf( param.get("longitude").toString());
            Double latitude = Double.valueOf( param.get("latitude").toString());
            Integer userId = Integer.valueOf(String.valueOf(param.get("userId")));
            
            keys.add(Constants.LATITUDE_0);
            keys.add(Constants.LATITUDE_1);
            keys.add(Constants.LONGITUDE);
            keys.add("answer_user_id");
            keys.add("auction_user_id");
            keys.add("property_user_id");
            
            values.add(latitude);
            values.add(latitude);
            values.add(longitude);
            values.add(userId);
            values.add(userId);
            values.add(userId);
            String sql = "SELECT " + Constants.DISTANCE_FORMULA + "," +
            		"      b.id,"+
            		"      r.act_id as actId, "+
            		"      b.`name` , "+
            		"      b.refer_price as referPrice , "+
            		"      b.total_num as totalNum, "+
            		"      b.latitude, "+
            		"      b.longitude , "+
            		"      b.address, "+
            		"      b.decoration, "+
            		"      r.type , "+
            		"      b.area_id as areaId ,  "+
            		"      hi.img_url as imgUrl,b.sold_num as soldNum "+
            		"   FROM  "+
            		"      house_building_info b "+
            		"      left JOIN house_building_img hi on b.id = hi.building_id "+
            		"      LEFT JOIN  activity_recap r  on  b.id = r.building_id  "+
            		"      inner join "+
            		"    ("+
            		"      select qap.act_id as activity_id,"+Constants.ACTIVITY_ANSWER+" as type from question_activity_participant qap where qap.user_id=:answer_user_id"+
            		"           UNION"+
            		"      select aad.activity_id as activity_id,"+Constants.ACTIVITY_AUCTION+" as type from auction_activity_deposit aad where aad.user_id=:auction_user_id"+
            		"           UNION"+
            		"      SELECT pcup.activity_id as activity_id,"+Constants.ACTIVITY_CUSTOMIZATION+" as type from property_customization_user_participant pcup where pcup.user_id=:property_user_id"+
            		"    ) temp on r.act_id=temp.activity_id and r.type=temp.type"+
            		"        WHERE  "+
            		"          hi.main_pic=0 and hi.type=0 and b.building_state=40001 order by  r.show_start_date desc";

           return  this.commonDao.findMapBySqlAndNamedParamPage(sql, keys, values, (curPage - 1) * pageSize, pageSize);
        } catch (Exception e) {
            LOGGER.error("查询新房列表异常",e);
            throw  e;
        }

    }


    /**
     * 查询热门定制楼盘列表
     *
     * @param param
     * @param curPage
     * @param pageSize
     * @return
     * @throws Exception
     */
    @Override
    public List<Map<String, Object>> queryListByCustomization(Map<String, Object> param, int curPage, int pageSize) throws Exception {
        if(curPage==0) curPage++;
        if(pageSize==0) pageSize = 10;
        List<String> keys = new ArrayList<>();
        List<Object> values = new ArrayList<>();
        LOGGER.info("参数：" + JSONObject.toJSONString(param));
        try {
            String sql = "SELECT " +
                    " r.house_type as houseType, " +
                    " r.size , " +
                    " r.pro_biz as proSize, " +
                    " r.green_rate as greenRate, " +
                    " r.face_to as faceTo  " +
                    "FROM " +
                    " property_customization_record r order by create_time desc";
            return  this.commonDao.findMapBySqlAndNamedParamPage(sql, keys, values, (curPage - 1) * pageSize, pageSize);
        } catch (Exception e) {
            LOGGER.error("查询定制楼盘列表异常",e);
            throw  e;
        }
    }

    /**
     * 根据参数动态拼接 表联结
     * @param param
     * @return
     */
    private String dynamicTableSql(Map<String, Object> param){
        if(param == null) return "";
        StringBuffer innerTable = new StringBuffer();
        Iterator<String> iterator = param.keySet().iterator();
        while (iterator.hasNext()){
            String key = iterator.next();
            if(! EXCLUDE_KEYS.contains(key)){
              if(TAG_KEYS.contains(key)){
                  if(innerTable.indexOf("house_building_tags_link") == -1)
                      innerTable.append("  INNER JOIN house_building_tags_link tk on b.id = tk.building_id  ");
              }else if("around".equals(key)){
                  innerTable.append(" INNER JOIN house_building_around ba on b.id = ba.building_id" ) ;
              }else if("house".equals(key)){
                  innerTable.append("  INNER JOIN house_info h on b.id = h.building_id  ");
              }
            }
        }
        return innerTable.toString();
    }

    /**
     * 根据条件动态拼接where条件
     * @param param
     * @return
     */
    private String dynamicWhereCondition(Map<String, Object> param ,List<String> placeHolder,List<Object> values){
        if(param == null) return "";
        StringBuffer where = new StringBuffer();
        StringBuffer tags = new StringBuffer();
        Iterator iterator = param.entrySet().iterator();

        while (iterator.hasNext()){
            Map.Entry<String,Object>  entry = ( Map.Entry<String,Object> )iterator.next();
            Object e = entry.getValue();
            String key = entry.getKey();
            if( e == null || org.apache.commons.lang.StringUtils.isBlank(e.toString())) continue;

            String key_col = StringUtils.getColunmStyleStr(key);
            if("areaId".contains(key)) {
                placeHolder.add(key);
                values.add(e);
                where.append(" and b.").append(key_col+"=:"+key);
            }else if("referPrice".equals(key)){

                String[] priceRange = e.toString().split("-");
                if(priceRange.length==2){
                    placeHolder.add("down");
                    placeHolder.add("up");
                    values.add(Integer.valueOf(priceRange[0]));
                    values.add(Integer.valueOf(priceRange[1]));
                    where.append(" and b.refer_price between :down and :up");
                }else {
                    placeHolder.add(key);
                    values.add(Integer.valueOf(priceRange[0]));
                    where.append(" and b.refer_price  >=:").append(key);
                }

            }else if("around".equals(key)){
                placeHolder.add(key);
                values.add(e);
                where.append(" and ba.").append(key_col+"=:"+key);
            }else if("house".equals(key)){
                placeHolder.add(key);
                values.add(e);
                where.append(" and h.").append(key_col+"=:"+key);
            }else if(TAG_KEYS.contains(key)){//这几个查询条件属于楼盘标签
                tags.append(param.get(key)).append(",");
            }
        }
        String tagsStr = tags.toString().replaceAll(",$","").trim();
        if(! tagsStr.isEmpty()){
            where.append( String.format( " and tk.tag_code in (%1s) and tk.status=0 ",tagsStr));
        }
        return where.toString();
    }

    /**
     * 查询有楼盘的城市
     *
     * @return
     */
    @Override
    public List<City> queryCityWithBuildings() throws Exception {
          String sql = "select c.id , city_name as cityName,c.province_id as provinceId " +
                  " from city c , house_building_info b where c.id  = b.city_id and b.building_state = 40001 group by b.city_id";
        try {
            List<Map<String, Object>> rows = this.commonDao.findBySql(sql);
            List<City> cities = new ArrayList<>();
            if(rows != null){
                City city ;
                for (Map<String,Object> map : rows){
                    city = new City();
                    BeanUtils.populate(city,map);
                    cities.add(city);
                }
            }
            return cities;
        } catch (Exception e) {
            LOGGER.error("查询有楼盘可售城市异常",e);
            throw e;
        }
    }

    /**
     * 经纪人，楼盘查询,复杂查询。
     *
     * @param param    查询条件有：areaId ,distance，actType活动，orderFlag:查询标签符1=佣金，2=活动人数，3=距离最近，4=发布时间
     * @param curPage
     * @param pageSize @return
     */
    @Deprecated
    @Override
    public List<Map<String, Object>> queryListForAgency(Map<String, Object> param, int curPage, int pageSize) {
        if(curPage==0) curPage++;
        List<String> keys = new ArrayList<>();
        List<Object> values = new ArrayList<>();
        LOGGER.info("参数：" + JSONObject.toJSONString(param));
        try {
            param =  CommonUtils.clearMapBlankVal(param);
            Double longitude = Double.valueOf(param.get("longitude").toString());
            Double latitude = Double.valueOf( param.get("latitude").toString());
            keys.add(Constants.LATITUDE_0);
            keys.add(Constants.LATITUDE_1);
            keys.add(Constants.LONGITUDE);
            values.add(latitude);
            values.add(latitude);
            values.add(longitude);

            String sql = " SELECT * FROM (  SELECT " + Constants.DISTANCE_FORMULA + "," +
                    "   b.id, " +
                    "   b.name, " +
                    "   b.address, " +
                    "   hi.img_url as imgUrl, " +
                    "   b.commission , " +
                    "  FROM " +
                    "   house_building_info b " +
                    "  INNER JOIN house_building_img hi ON b.id = hi.building_id " +
                    "    LEFT JOIN house_building_ads ad ON b.id = ad.building_id " +
                       dynamicAgencyTableSql(param) +
                    "  WHERE " +
                    "   hi.main_pic = 0 " +
                    "  AND hi.type = 0 " +
                    "  AND b.building_state=40001" + this.dynamicWhereForAgency(param,keys,values)+ " ) tmp " + dynamicOrderForAgency(param);

            return  this.commonDao.findMapBySqlAndNamedParamPage(sql, keys, values, (curPage - 1) * pageSize, pageSize);
        } catch (Exception e) {
            LOGGER.error("查询经纪人楼盘列表异常",e);
            throw  e;
        }
     }

    /**
     * 经纪人app查询动态拼接表
     * @param param
     * @return
     */
    private String dynamicWhereForAgency(Map<String,Object> param,List<String> placeHolder,List<Object> values){
        if(param == null) return "";
        StringBuffer where = new StringBuffer();
        Iterator<Map.Entry<String, Object>> iterator = param.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, Object> entry = iterator.next();
            String key = entry.getKey();
            Object value = entry.getValue();
            if(value==null || org.apache.commons.lang.StringUtils.isBlank(value.toString())) continue;
            switch (key){
                case "actType":
                    where.append(" and r.type =:" + key);
                    placeHolder.add(key);
                    values.add(value);
                    break;
                case  "areaId" :
                    where.append(" and b.area_id =:" + key);
                    placeHolder.add(key);
                    values.add(value);
                    break;
                case  "distance" :
//                    where.append(" and distance <=:" + key);
//                    placeHolder.add(key);
//                    values.add(value);
                    break;
                default:
                    break;
            }
        }
        return where.toString();
    }
    /**
     * 根据参数动态拼接 表联结
     * @param param
     * @return
     */
    private String dynamicAgencyTableSql(Map<String, Object> param){
        if(param == null) return "";
        StringBuffer innerTable = new StringBuffer();
        Iterator<Map.Entry<String, Object>> iterator = param.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, Object> next = iterator.next();
            String key = next.getKey();
            Object value = next.getValue();
            if(value==null || org.apache.commons.lang.StringUtils.isBlank(value.toString())) continue;
            switch (key){
                case "actType":
                    innerTable.append(" INNER JOIN activity_recap r on b.id = r.building_id ");
                    break;
                default:

            }
        }
        return innerTable.toString();
    }

    /**
     * 经纪人动态排序
     * @param param
     * @return
     */
    private String dynamicOrderForAgency(Map<String, Object> param){
        if(param == null) return "";
        StringBuffer order = new StringBuffer();
        Object orderFlag   = param.get("orderFlag");
        Object distance   = param.get("distance");
        String dt = "";
        if(distance != null){
            dt = " where distance <=" + distance.toString();
        }
        if(orderFlag != null){
            Integer flag = Integer.valueOf(orderFlag.toString());
            if(1 == flag) { //佣金
                order.append(" commission desc ,");
            }
            if(2==flag){//参与人数
                order.append(" participant desc,");
            }
            if(3==flag){
                order.append(" distance ,");
            }
            //todo 最新发布
 //           if(release !=null){
//            order.append(" release desc");
//        }
        }

        String str =   order.toString().replaceAll(",\\s*$","").trim();
        if(! str.isEmpty())
            return  dt + " order by " + str;
        return dt + " order by commission desc ,participant desc";
    }

	@Override
	public HouseBuildingInfo getBuildingNameByRecapId(Integer activityRecapId) {

		try {
			
			String hql ="select hb from ActivityRecap ar,HouseBuildingInfo hb where hb.buildingState = 40001 and ar.buildingId = hb.id and ar.id=:id";

			List<HouseBuildingInfo> list = baseDao.findByNamedParam(hql, "id", activityRecapId);
			if(list==null || list.isEmpty())
				return null;
			return list.get(0);
			
		} catch (Exception e) {
			LOGGER.error("根据活动汇总id查询楼盘信息出错,"+e);
			throw e;
		}
	}

	@Override
	public Map<String, Object> queryBuildingInfoByBuildingId(Map<String, Object> param,
			Integer buildingId) {
		
        List<String> keys = new ArrayList<>();
        List<Object> values = new ArrayList<>();
        LOGGER.info("参数：" + JSONObject.toJSONString(param));
        try {
            param =  CommonUtils.clearMapBlankVal(param);
            Double longitude = Double.valueOf( param.get("longitude").toString());
            Double latitude = Double.valueOf( param.get("latitude").toString());
            keys.add(Constants.LATITUDE_0);
            keys.add(Constants.LATITUDE_1);
            keys.add(Constants.LONGITUDE);
            keys.add("buildingId");
            values.add(latitude);
            values.add(latitude);
            values.add(longitude);
            values.add(buildingId);

            String sql = "SELECT " + Constants.DISTANCE_FORMULA + "," +
                    "  b.id," +
                    "  r.act_id as actId," +
                    "  b.`name` ," +
                    "  b.refer_price as referPrice ," +
                    "  b.total_num as totalNum," +
                    "  b.latitude," +
                    "  b.longitude ," +
                    "  b.address," +
                    "  b.decoration," +
                    "  r.type ," +
                    "  b.area_id as areaId , " +
                    "  hi.img_url as imgUrl,b.sold_num as soldNum " +
                     "  FROM " +
                    "  house_building_info b " +
                    "  LEFT JOIN  activity_recap r  on  b.id = building_id " +
                    " INNER JOIN house_building_img hi on b.id = hi.building_id " + dynamicTableSql(param) +
                    " WHERE " +
                    " hi.main_pic=0 and hi.type=0 and b.building_state=40001 and r.act_status=0  and b.id=:buildingId order by  r.show_start_date desc";

           List<Map<String, Object>> buildingList = this.commonDao.findMapBySqlAndNamedParamPage(sql, keys, values, 0, 1);
           if(buildingList==null || buildingList.isEmpty())
        	   return null;
           return buildingList.get(0);
        } catch (Exception e) {
            LOGGER.error("查询楼盘关联详情异常",e);
            throw  e;
        }

    }
    @Override
    public BuilderApprove queryBuilderInfo(Integer builderId) throws Exception {
        BuilderApprove param = new BuilderApprove();
        param.setBuilderId(builderId);
        try {
            return this.commonDao.findUniqueObj(param);
        } catch (Exception e) {
            LOGGER.error("查询异常",e);
            throw e;
        }
    }

    /**
     * 首页查询红包抵扣
     * @param params
     * @param curPage
     * @param pageSize
     * @return
     * @throws Exception
     */
    @Override
    public List<Map<String, Object>> queryListByDikouAndCity( Map<String,Object> params , int curPage, int pageSize) throws Exception {
        if(curPage==0) curPage ++;
        if(pageSize == 0) pageSize = 10;
        List<String> keys = new ArrayList<>();
        List<Object> values = new ArrayList<>();
        LOGGER.info("参数：" + JSONObject.toJSONString(params));

        try {
            params =  CommonUtils.clearMapBlankVal(params);
            Double longitude = Double.valueOf( params.get("longitude").toString());
            Double latitude = Double.valueOf( params.get("latitude").toString());
            keys.add(Constants.LATITUDE_0);
            keys.add(Constants.LATITUDE_1);
            keys.add(Constants.LONGITUDE);
            values.add(latitude);
            values.add(latitude);
            values.add(longitude);

            Object cityName = params.get("cityName");
            City city = new City();
            city.setCityName(cityName.toString());
            Integer cityId = this.commonDao.findUniqueObj(city).getId();
            
            Date d = new Date();

            keys.add("cityId");
            values.add(cityId);

            String sql = "SELECT " + Constants.DISTANCE_FORMULA + "," +
                    "  b.id," +
                    "  a.id as actId , " +
                    "  b.`name` ," +
                    "  b.refer_price as referPrice ," +
                    "  b.latitude," +
                    "  b.longitude ," +
                    "  b.address," +
                    "  a.act_start_date as startDate," +
                    "  a.act_end_date as endDate," +
                    "  a.show_start_date as activeStartDate , " +
                    "  a.act_active_end_date as activeEndDate , " +
                    "  hi.img_url as imgUrl,b.sold_num as soldNum,b.total_num as totalNum    " +
                    "  FROM " +
                    "  house_building_info b " +
                    " INNER JOIN house_building_img hi on b.id = hi.building_id "  +
                    " INNER JOIN activity_recap a on b.id = a.building_id "  +
                    " WHERE  a.type=39004 and  " +
                    " hi.main_pic=0 and hi.type=0 and b.building_state=40001 and a.act_status=0 and b.city_id=:cityId"  + " group by actId,a.building_id order by a.type,a.act_start_date desc";
            return  this.commonDao.findMapBySqlAndNamedParamPage(sql, keys, values, (curPage - 1) * pageSize, pageSize);
        } catch (Exception e) {
            LOGGER.error("根据活动查询楼盘异常",e);
            throw e;
        }
    }

	@Override
	public Integer queryListCountByCityId(Integer cityId) {

		String hql = "select count(id) from HouseBuildingInfo where status = 0 and (buildingState = 40001 or buildingState = 40003) and cityId=:cityId";
		List<Long> list = this.baseDao.findByNamedParam(hql, "cityId", cityId);
		
		return (list==null||list.isEmpty())?0:Integer.valueOf(list.get(0).toString());
	}

	
	/**
     * 查询经融模块推荐楼盘
     * @param params
     * @param curPage
     * @param pageSize
     * @return
     * @throws Exception
     */
    @Override
    public List<Map<String, Object>> queryListByCity( Map<String,Object> params , int curPage, int pageSize) throws Exception {
        if(curPage==0) curPage ++;
        if(pageSize == 0) pageSize = 10;
        List<String> keys = new ArrayList<>();
        List<Object> values = new ArrayList<>();
        LOGGER.info("参数：" + JSONObject.toJSONString(params));

        try {
            params =  CommonUtils.clearMapBlankVal(params);
            Double longitude = Double.valueOf( params.get("longitude").toString());
            Double latitude = Double.valueOf( params.get("latitude").toString());
            keys.add(Constants.LATITUDE_0);
            keys.add(Constants.LATITUDE_1);
            keys.add(Constants.LONGITUDE);
            values.add(latitude);
            values.add(latitude);
            values.add(longitude);

            Object cityName = params.get("cityName");
            City city = new City();
            city.setCityName(cityName.toString());
            Integer cityId = this.commonDao.findUniqueObj(city).getId();
            
            Date d = new Date();

            keys.add("cityId");
            values.add(cityId);

            String sql = "SELECT " + Constants.DISTANCE_FORMULA + "," +
                    "  b.id," +
            		"  a.type as actType, " + 
                    "  a.act_id as actId , " +
                    "  b.`name` ," +
                    "  b.refer_price as referPrice ," +
                    "  b.latitude," +
                    "  b.longitude ," +
                    "  b.address," +
                    "  a.act_start_date as startDate," +
                    "  a.act_end_date as endDate," +
                    "  a.show_start_date as activeStartDate , " +
                    "  a.act_active_end_date as activeEndDate , " +
                    "  hi.img_url as imgUrl,b.sold_num as soldNum,b.total_num as totalNum    " +
                    "  FROM " +
                    "  house_building_info b " +
                    " INNER JOIN house_building_img hi on b.id = hi.building_id "  +
                    " INNER JOIN activity_recap a on b.id = a.building_id "  +
                    " WHERE  " +
                    " hi.main_pic=0 and hi.type=0 and b.building_state=40001 and a.act_start_date < now() and a.act_active_end_date > now() and a.act_status=0 and b.city_id=:cityId"  + " group by actId,a.building_id order by a.type,a.act_start_date desc";
            return  this.commonDao.findMapBySqlAndNamedParamPage(sql, keys, values, (curPage - 1) * pageSize, pageSize);
        } catch (Exception e) {
            LOGGER.error("根据活动查询楼盘异常",e);
            throw e;
        }
    }
    /**
	 * 
	 * @author wangxing
	 * @date 2015年11月25日 下午8:50:12
	 * @parameter agencyId
	 * @description  获取楼盘佣金数
	 * @return
	 */
	@Override
	public Map<String, Object> queryBuildingCommission(Integer buildingId) throws Exception {
	        try {
	            return  this.commonDao.findUniqueBySql("select c.commision as buildingCommission from house_building_info c where c.id=" + buildingId);
	        } catch (Exception e) {
	            LOGGER.error("查询楼盘佣金数异常",e);
	            throw e;
	        }
    }
    
    /**
	 * @author wangxing
	 * @date 2015年12月1日 下午8:25:03
	 * @parameter 
	 * @description 根据门店Code和城市Id获取门店下的所有楼盘
	 * @return
	 */

	@Override
	public List<Map<String,Object>> queryBuildingByOfficeCode(String agencyOfficeCode, Integer cityId,Integer curPage) throws Exception {
		 Integer pageSize = 10;
		 if(curPage==0) curPage ++;
		
	     String sql = 
				" SELECT b.id AS buildingId, b.name AS buildingName, b.area_id AS areaId, a.area_name AS areaName " +
				" FROM  agency_office_building_link bl,  house_building_info b, area a " + 
				"  WHERE " +
			    " b.id = bl.building_id AND bl.ao_id = " + agencyOfficeCode + 
			    " AND b.city_id = " + cityId + 
			    " AND b.area_id = a.id " ;
		
	     return  this.commonDao.findBySql(sql, (curPage - 1) * pageSize, pageSize);
	}
}
