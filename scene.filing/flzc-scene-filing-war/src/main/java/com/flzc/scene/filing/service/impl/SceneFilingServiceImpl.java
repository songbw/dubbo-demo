package com.flzc.scene.filing.service.impl;

import com.flzc.base.service.impl.BaseServiceImpl;
import com.flzc.base.util.DateUtil;
import com.flzc.scene.filing.api.entity.SceneFiling;
import com.flzc.scene.filing.api.service.SceneFilingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *   报备服务实现类
 * Created by song on 2015/11/18.
 */
@Service("sceneFilingService")
public class SceneFilingServiceImpl extends BaseServiceImpl implements SceneFilingService {

    private static  final Logger LOGGER = LoggerFactory.getLogger(SceneFilingServiceImpl.class);

    /**
     * 添加报备信息
     *
     * @param filing
     * @return
     */
    @Override
    public SceneFiling saveSceneFiling(SceneFiling filing) {
        try {
            filing.setCreateAt(new Date());
            Serializable id = this.save(filing);
            filing.setId(Integer.valueOf(id.toString()));
            return filing;
        } catch (Exception e) {
            LOGGER.error("保存报备信息异常",e);
            throw e;
        }
    }

    /**
     * 更新报备信息
     *
     * @param filing
     */
    @Override
    public void updateSceneFiling(SceneFiling filing) {
        try {
            filing.setUpdateAt(new Date());
            this.update(filing);
        } catch (Exception e) {
            LOGGER.error("更新报备信息异常",e) ;
            throw e ;
        }
    }

    /**
     * 根据ID查询指定报备信息
     *
     * @param id
     * @return
     */
    @Override
    public SceneFiling findById(Integer id) {
        SceneFiling result = this.findById(SceneFiling.class,id);
        if(result == null) return null ;
        return result;
    }

    /**
     * 根据案场ID和报备状态查询报备信息列表
     * @param sceneId
     * @param status
     * @return
     */
    @Override
    public List<SceneFiling> findbyStatus(Integer sceneId, Integer status, Integer page, Integer pageSize) {
        String hql = "from SceneFiling where sceneId = "+ sceneId +" and status = "+ status +" and  valid = 0 ORDER BY createAt DESC " ;
        int minSize = (page - 1) * pageSize;
        List<SceneFiling> list = this.findByHql(hql, minSize, pageSize);
        return list;
    }

    /**
     * 根据案场ID和报备状态获取总条数
     *
     * @param sceneId
     * @param status
     * @return
     */
    @Override
    public int allByStatus(Integer sceneId, Integer status) {
        String sql = "SELECT count(id) FROM scene_filing WHERE scene_id = " + sceneId + " AND status = " + status + " AND valid = 0 " ;
        List<?> result = this.findBySql(sql);
        if(result == null || result.isEmpty())  return 0;
        BigInteger bigInteger = (BigInteger) result.get(0);
        return bigInteger.intValue();
    }

    /**
     * 根据用户ID查询经纪人列表
     *
     * @param sceneId
     * @param userId
     * @return
     */
    @Override
    public List<SceneFiling> findAgencyListByUserId(Integer sceneId, Integer userId) {
        String hql = "from SceneFiling where sceneId = "+ sceneId +" and status = 57003  and  valid = 0 and userId =  " + userId ;
        List<SceneFiling> list = this.findByHql(hql);
        return list;
    }

    @Override
    public List<SceneFiling> findAgencyBy4UserId(Integer sceneId, Integer userId) {
        String hql = "from SceneFiling where sceneId = "+ sceneId +" and status = 57004  and  valid = 0 and userId =  " + userId ;
        List<SceneFiling> list = this.findByHql(hql);
        return list;
    }

    /**
     *   根据客户状态、来源、活动信息等查询客户管理列表
     *
     * @param sceneId
     * @param status
     * @param activityId
     * @param intentId
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public  List<SceneFiling> queryClient(Integer sceneId,Integer status,Integer activityId, Integer intentId, Integer page, Integer pageSize) {
        String hql = "from SceneFiling where sceneId = "+ sceneId  + "and valid = 0 ";
        if (status != null && status !=0) {
            hql = hql + " and status = " + status ;
        } else {
            hql = hql + " and status  != 57002 " ;
        }
        if (activityId != null && activityId !=0) {
            hql = hql +  " and activityId = " + activityId ;
        }
        if (intentId != null) {
            if (intentId == -1) {
                hql = hql + " and intentId = " + intentId ;
            }
            if (intentId == 1) {
                hql = hql + " and intentId  >" + intentId ;
            }
        }
        hql = hql + " ORDER BY  updateAt,createAt DESC " ;
        int minSize = (page - 1) * pageSize;
        List<SceneFiling> list = this.findByHql(hql, minSize, pageSize);
        return list ;
    }

    /**
     * 根据姓名查询该楼盘用户列表
     *
     * @param sceneId
     * @param name
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public List<SceneFiling> findByName(Integer sceneId, String name, Integer page, Integer pageSize) {
        String hql = "from SceneFiling where sceneId = "+ sceneId  + " and name = '" + name +  "' ORDER BY  createAt DESC ";
        int minSize = (page - 1) * pageSize;
        List<SceneFiling> list = this.findByHql(hql, minSize, pageSize);
        return list ;
    }

    /**
     * 根据用户状态查询报备信息
     *
     * @param sceneId
     * @param userId
     * @param status
     * @return
     */
    @Override
    public List<SceneFiling> findByUserstatus(Integer sceneId, Integer userId, Integer status) {
        String hql = "from SceneFiling where sceneId = "+ sceneId  + " and userId = " + userId +  " and status =  " + status ;
        List<SceneFiling> list = this.findByHql(hql);
        return list ;
    }

    /**
     * 获取待看信息列表
     *
     * @param sceneId
     * @param status  57003
     * @return
     */
    @Override
    public List<SceneFiling> findbyReserver(Integer sceneId, Integer status, Integer page, Integer pageSize) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, 1); //minus number would decrement the days
        Date dat  = cal.getTime() ;
        String date = DateUtil.format(dat, "yyyy-MM-dd");
        System.out.println(date);
        String hql = "from SceneFiling where sceneId = "+ sceneId +" and status = "+ status +"  and  valid = 0 and reserveAt  < '"+date+"' ORDER BY  reserveAt DESC"  ;
        int minSize = (page - 1) * pageSize;
        List<SceneFiling> list = this.findByHql(hql, minSize, pageSize);
        return list ;
    }

    /**
     * 获取待看信息条数
     *
     * @param sceneId
     * @param status
     * @return
     */
    @Override
    public int allByReserve(Integer sceneId, Integer status) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, 1); //minus number would decrement the days
        Date dat  = cal.getTime() ;
        String date = DateUtil.format(dat, "yyyy-MM-dd");
        String sql = "SELECT count(id) FROM scene_filing WHERE scene_id = " + sceneId + " AND status = " + status + " AND valid = 0 and reserve_at  < '"+date+"'";
        List<?> result = this.findBySql(sql);
        BigInteger bigInteger = (BigInteger) result.get(0);
        return bigInteger.intValue();
    }
}
