package com.flzc.rob.service.impl;

import com.flzc.base.dao.BaseDao;
import com.flzc.base.dao.CommonDao;
import com.flzc.base.util.Memcached;
import com.flzc.base.util.MemcachedKeyConstant;
import com.flzc.rob.api.entity.HouseBuildingTags;
import com.flzc.rob.api.entity.HouseBuildingTagsLink;
import com.flzc.rob.api.service.HouseBuildingTagsService;
import com.flzc.rob.util.ReflectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by iverson on 2015/10/10.
 */
@Service("houseBuildingTagsService")
public class HouseBuildingTagsServiceImpl implements HouseBuildingTagsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(HouseBuildingTagsServiceImpl.class);



    @Resource(name = "commonDao")
    private CommonDao commonDao;

    @Resource(name = "baseDaoImpl")
    private BaseDao baseDao;

    /**
     * 保存标签
     *
     * @param tag
     * @return
     */
    @Override
    public Number save(HouseBuildingTags tag) throws Exception {
        try {
            tag.setStatus(0);
            tag.setCreateTime(new Date());
           return  this.commonDao.save(tag);
        } catch (Exception e) {
            LOGGER.error("保存楼盘标签异常",e);
            throw  e;
        }
    }

    /**
     * 根据id更新标签
     *
     * @param id
     */
//    @Override
//    public void updateById(Integer id) {
//        HouseBuildingTags old = this.commonDao.findById(HouseBuildingTags.class, id);
//
//    }

    /**
     * 根据id标签删除标签,逻辑删除
     *
     * @param id
     */
    @Override
    public void deleteById(Integer id) throws Exception {
        try {
            HouseBuildingTags old = this.commonDao.findById(HouseBuildingTags.class, id);
            old.setStatus(1);
            old.setUpdateTime(new Date());
            this.commonDao.update(old);
        } catch (Exception e) {
           LOGGER.error("删除楼盘标签日志异常",e);
            throw  e;
        }
    }

    /**
     * 根据id查询标签
     *
     * @param ids
     * @return
     */
    @Override
    public List<HouseBuildingTags> queryByIds(List<Integer> ids) throws Exception{

        if(ids == null || ids.isEmpty()) return Collections.emptyList();
        StringBuffer sb = new StringBuffer();
        for (Integer id : ids){
            sb.append(id).append(",");
        }
        String con = sb.toString().replaceAll(",+$", "");
        try{
            if(! con.isEmpty()){
                return this.baseDao.findByHql("from HouseBuildingTags t where t.status = 0  and  t.code in ( " + con + ")");
            }
            return Collections.EMPTY_LIST;
        }catch (Exception e){
            LOGGER.error("查询楼盘标签信息异常",e);
            throw e;
        }
    }



    /**
     * 根据多个id,类型查询标签
     *
     * @param ids
     * @param type
     * @return
     */
    @Override
    public List<HouseBuildingTags> queryByIds(List<Integer> ids, Integer type) {
        if(ids == null || ids.isEmpty()) return Collections.emptyList();
        StringBuffer sb = new StringBuffer();
        for (Integer id : ids){
            sb.append(id).append(",");
        }
        String con = sb.toString().replaceAll(",+$", ",");
        try {
            if(! con.isEmpty()){
                return this.baseDao.findByNamedParam("from HouseBuildingTags t where t.status = 0 and type=:type and  t.code in ( " + con + ")", "type" ,type);
            }
            return Collections.emptyList();
        }catch (Exception e){
            LOGGER.error("查询楼盘标签信息异常",e);
            throw e;
        }

    }

    /**
     * @param pcode 父编码
     * @param type  类型 .如果为空则查全部类型的标签
     * @return
     */
    @Override
    public List<HouseBuildingTags> queryByParentCode(String pcode, Integer type) {
        String hql = "from HouseBuildingTags t where t.status = 0  and t.parentCode=:parentCode ";
        String[] keys = {"parentCode"};
        Object[] vals = {pcode};
        if(type != null){
            hql =  hql + " and t.type=:type";
            keys = new String[]{"parentCode", "type"};
            vals = new Object[]{pcode, type};
        }

        try {
            return this.baseDao.findByNamedParam(hql,keys,vals);
        } catch (Exception e) {
            LOGGER.error("查询标签异常",e);
            throw e;
        }
    }

    /**
     * 根据楼盘id,编码查询标签
     *
     * @param buildingId
     * @param code 父code
     * @return
     */
    @Override
    public List<HouseBuildingTags> queryTags(Integer buildingId, String code) throws Exception {
          String sql = " SELECT " +
                  " t.`name` ,t.id , t.code ,t.parent_code as parentCode,t.type " +
                  " FROM " +
                  " `house_building_tags` t," +
                  " house_building_tags_link tl " +
                  " WHERE " +
                  " t.code = tl.tag_code " +
                  "AND tl.building_id = %1s and t.parent_code='%2s'  " +
                  "AND tl.`status` = 0 ";
           sql = String.format(sql,buildingId,code);
        try {
            List<Map<String, Object>> rows = this.commonDao.findBySql(sql);
            return ReflectionUtil.convertMaps2Entities(HouseBuildingTags.class,rows);
        } catch (Exception e) {
            LOGGER.error("查询楼盘标签异常",e);
            throw e;
        }
    }

	@Override
	public HouseBuildingTags queryById(Integer id) throws Exception {
		return commonDao.findById(HouseBuildingTags.class, id);
	}

	@Override
	public HouseBuildingTags queryHouseBuildingTagsByCode(String code) {
        String key = MemcachedKeyConstant.USER_HEAD_IMAGE_USERID + code;
        HouseBuildingTags tag = (HouseBuildingTags) Memcached.get(key);
        if(tag ==null){
            String hql = "from HouseBuildingTags where status = 0 and code=:code";
            List<HouseBuildingTags> list = this.baseDao.findByNamedParam(hql, "code", code);
            tag = (list == null || list.isEmpty())?null:list.get(0);
            Memcached.set(key,tag, Calendar.YEAR,1);
        }
        return tag;

	}
}
