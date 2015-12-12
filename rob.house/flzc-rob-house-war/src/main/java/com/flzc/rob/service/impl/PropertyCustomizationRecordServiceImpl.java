package com.flzc.rob.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flzc.base.dao.CommonDao;
import com.flzc.base.service.BaseService;
import com.flzc.rob.api.entity.PropertyCustomizationRecord;
import com.flzc.rob.api.service.PropertyCustomizationRecordService;
import com.flzc.rob.common.Constants;

/**
 * 私人定制活动服务接口类
 * Created by iverson on 2015/10/17.
 */
@Service("propertyCustomizationRecordService")
public class PropertyCustomizationRecordServiceImpl implements PropertyCustomizationRecordService {
    private static  final Logger LOGGER = LoggerFactory.getLogger(PropertyCustomizationRecordServiceImpl.class );

    @Autowired
    private CommonDao commonDao;

    @Resource(name="baseServiceImpl")
	private BaseService baseService;

    /**
     * 保存活动
     * @param record
     * @return
     */
    public Number save(PropertyCustomizationRecord record) throws Exception {
        Number id = null;
        try {
            record.setCreateTime(new java.util.Date());
            id = this.commonDao.save(record);
            return id;
        } catch (Exception e) {
            LOGGER.error("保存私人定制异常",e);
            throw e;
        }
    }

    /**
     * 根据建筑id查询参与活动的信息
     *
     * @param buildingId
     * @return
     */
    @Override
    public List<Map<String ,Object>> queryByBuildingId(Integer buildingId ,int curPage,int pageSize ) throws Exception {
        if(curPage==0) curPage++;
        if(pageSize==0) pageSize = 10;
        try {
            String sql = "SELECT " +
                    " r.id ," +
                    " r.house_type as houseType, " +
                    " r.size , " +
                    " r.decoration , " +
                    " r.floor_biz as floorBiz , " +
                    " r.pro_biz as proSize, " +
                    " r.green_rate as greenRate, " +
                    " r.face_to as faceTo  " +
                    "FROM " +
                    " property_customization_record r , property_customization_activity a  " +
                    " where r.derive_from = 0 and r.activity_id = a.id  and a.building_id="  + buildingId  +
                    " order by r.create_time desc ";
            return  this.commonDao.findBySql(sql , (curPage - 1) * pageSize, pageSize);
        } catch (Exception e) {
            LOGGER.error("查询定制楼盘列表异常",e);
            throw  e;
        }
    }

    /**
     * 根据用户id查询活动
     *
     * @param userId
     * @return
     */
    @Override
    public List<PropertyCustomizationRecord> queryByUserId(Integer userId) throws Exception {
        PropertyCustomizationRecord param = new PropertyCustomizationRecord();
        param.setUserId(userId);
        try {
            List<PropertyCustomizationRecord> rows = this.commonDao.findObjs(param);
            return rows;
        } catch (Exception e) {
            LOGGER.error("根据用户id查询私人定制异常",e);
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
    public PropertyCustomizationRecord queryById(Integer id) throws Exception {
        try {
            return  this.commonDao.findById(PropertyCustomizationRecord.class,id);
        } catch (Exception e) {
            LOGGER.error("根据id查询",e);
            throw e;
        }
    }

    /**
     * 根据楼盘与户型统计，表示这个定制有多少人跟订了
     *
     * @param recordId
     * @return
     */
    @Override
    public int countNumByHouse( Integer recordId) {
        String sql = "SELECT " +
                " count(r.id) as count " +
                " FROM " +
                " property_customization_record r " +
             //   "INNER JOIN property_customization_activity a ON  r.activity_id = a.id " +
                "WHERE " +
                " r.derive_from = " + recordId ;
              //  "AND a.building_id = " + buildingId;
        List<Map<String, Object>> result = this.commonDao.findBySql(sql);
        if(result == null || result.isEmpty()) return 0 + 1;//表示当id下没有有衍生的数据，只有这条数据本身
        return  Integer.valueOf( result.get(0).get("count").toString()) + 1 ;

    }

    /**
     * 统计定制户型的支持数
     *
     * @param recordId
     * @return
     */
    @Override
    public int countFollowNum(Integer recordId) {
        String sql = "select count(id) as count from property_customization_follow f where  f.status = 0 and f.record_id = " + recordId;
        List<Map<String, Object>> result = this.commonDao.findBySql(sql);
        if(result == null || result.isEmpty()) return 0;
        return  Integer.valueOf( result.get(0).get("count").toString())  ;
    }

	@Override
	public List<PropertyCustomizationRecord> queryPCRListByActId(Integer actId,Integer curPage,Integer pageSize) {
		
		if(curPage==null || curPage<=0) curPage=1;
		pageSize =Constants.DEFAULT_PAGESIZE;
		
		String hql =" from PropertyCustomizationRecord where activityId=:activityId";
		
		return baseService.findByNamedParam(hql, new String[]{"activityId"}, new Object[]{actId},(curPage-1)*pageSize,pageSize);
	}
    
}
