package com.flzc.service.service.impl;

import com.flzc.base.util.Memcached;
import com.flzc.base.util.UserUtil;
import com.flzc.scene.filing.api.entity.*;
import com.flzc.scene.filing.api.service.*;
import com.flzc.service.bean.SceneFilingsBean;
import com.flzc.service.exception.ErrorBean;
import com.flzc.service.exception.SceneException;
import com.flzc.service.service.SceneFilingBizService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *  报备业务实现类
 * Created by song on 2015/11/20.
 */
@Service
public class SceneFilingBizServiceImpl implements SceneFilingBizService{

    private static  final Logger logger = LoggerFactory.getLogger(SceneFilingBizServiceImpl.class);

    @Autowired
    private SceneFilingService sceneFilingService ;

    @Autowired
    private SceneVisitService sceneVisitService ;

    @Autowired
    private SceneMissReasonService sceneMissReasonService ;

    @Autowired
    private SceneInvalidReasonService sceneInvalidReasonService ;

    @Autowired
    private SceneRemarkService sceneRemarkService ;

    @Autowired
    private SceneDealService sceneDealService ;

    //检查用户是否在报备限制期（30内看过房的用户不允许报备）
    private boolean checkFiling(Integer userId) {
        return true ;
    }



    /**
     * 经纪人向案场经纪人提交报备
     *
     * @param sceneFiling
     * @return
     */
    @Override
    public String addSceneFiling(SceneFiling sceneFiling) throws SceneException {
        ErrorBean errorBean = new ErrorBean() ;
        HouseBuildingInfo houseBuildingInfo = sceneDealService.findBuildingById(sceneFiling.getHouseId()) ;
        User user = sceneDealService.findUserById(sceneFiling.getUserId()) ;
        if (checkFiling(sceneFiling.getUserId())) {
            sceneFiling =  sceneFilingService.saveSceneFiling(sceneFiling);
        } else {
            errorBean.setErrno("300015");
            errorBean.setMsg("报备失败！");
            throw new SceneException(errorBean) ;
        }
        return  "{\"status\":0,\"result\":"+sceneFiling.getId()+"}";
    }



    /**
     * 案场经纪人有效无效操作
     *
     * @param token
     * @param fillingId
     * @param valid
     * @return
     */
    @Override
    public String filingValid(String token, Integer fillingId, Integer valid) throws SceneException {
        ErrorBean errorBean = new ErrorBean() ;
        try {
            SceneFiling sceneFiling = sceneFilingService.findById(fillingId) ;
            int sceneId = (int) Memcached.get(token);
            if (sceneFiling == null) {
                errorBean.setErrno("300011");
                errorBean.setMsg("该报备信息为空！");
                throw new SceneException(errorBean) ;
            }
            if (sceneId != sceneFiling.getSceneId()) {
                errorBean.setErrno("300012");
                errorBean.setMsg("案场与用户申请楼盘不对应！");
                throw new SceneException(errorBean) ;
            }
            if (sceneFiling.getStatus() != 57002 || sceneFiling.getValid() != 0) {
                errorBean.setErrno("300013");
                errorBean.setMsg("无法进行该操作！");
                throw new SceneException(errorBean) ;
            }
            if (valid == 0) {
                sceneFiling.setSceneId(sceneId);
                sceneFiling.setStatus(57003);
            } else {
                sceneFiling.setValid(valid);
            }
            sceneFilingService.updateSceneFiling(sceneFiling);
        } catch (Exception e) {
            logger.error("后台服务异常",e);
            errorBean.setErrno("300100");
            errorBean.setMsg("后台服务异常！");
            throw new SceneException(errorBean) ;
        }
        return  " {\"status\":0,\"success\":true}";
    }

    /**
     * 修改看房时间
     *
     * @param token
     * @param fillingId
     * @param reserce
     * @return
     * @throws SceneException
     */
    @Override
    public String updateReserve(String token, Integer fillingId, Long reserce) throws SceneException {
        ErrorBean errorBean = new ErrorBean() ;
        try {
            SceneFiling sceneFiling = sceneFilingService.findById(fillingId) ;
            int sceneId = (int) Memcached.get(token);
            if (sceneFiling == null) {
                errorBean.setErrno("300011");
                errorBean.setMsg("该报备信息为空！");
                throw new SceneException(errorBean) ;
            }
            if (sceneId != sceneFiling.getSceneId()) {
                errorBean.setErrno("300012");
                errorBean.setMsg("案场与用户申请楼盘不对应！");
                throw new SceneException(errorBean) ;
            }
            if (sceneFiling.getStatus() != 57003 || sceneFiling.getValid() != 0) {
                errorBean.setErrno("300013");
                errorBean.setMsg("无法进行该操作！");
                throw new SceneException(errorBean) ;
            }
           sceneFiling.setReserveAt(new Date(reserce));
            sceneFilingService.updateSceneFiling(sceneFiling);
        } catch (Exception e) {
            logger.error("后台服务异常",e);
            errorBean.setErrno("300100");
            errorBean.setMsg("后台服务异常！");
            throw new SceneException(errorBean) ;
        }
        return  " {\"status\":0,\"success\":true}";
    }

    /**
     * 查看指定报备信息详情
     *
     * @param token
     * @param fillingId
     * @return
     */
    @Override
    public SceneFiling queryFilingInfo(String token, Integer fillingId) throws SceneException {
        ErrorBean errorBean = new ErrorBean() ;
        SceneFiling sceneFiling = sceneFilingService.findById(fillingId) ;
        int sceneId = (int) Memcached.get(token);
        if (sceneId != sceneFiling.getSceneId()) {
            errorBean.setErrno("300014");
            errorBean.setMsg("无权读取信息！");
            throw new SceneException(errorBean) ;
        }
        if (sceneFiling == null) {
            errorBean.setErrno("300011");
            errorBean.setMsg("该报备信息为空！");
            throw new SceneException(errorBean) ;
        }
        return sceneFiling;
    }

    /**
     * 查询待报备列表
     *
     * @param token
     * @param page
     * @return
     */
    @Override
    public SceneFilingsBean allFiling(String token, Integer page, Integer pageSize) throws SceneException {
        int id  = (int) Memcached.get(token);
        SceneFilingsBean sceneFilingsBean = new SceneFilingsBean() ;
        List<SceneFiling> list = sceneFilingService.findbyStatus(id, 57002, page,pageSize) ;
        int count = sceneFilingService.allByStatus(id,57002) ;
        sceneFilingsBean.setSceneFilingList(list);
        int pageCount = (int) Math.ceil(count/pageSize);
        sceneFilingsBean.setTotal(pageCount);
        return sceneFilingsBean;
    }

    /**
     * 添加无效报备原因
     *
     * @param token
     * @param sceneInvalidReason
     * @return
     */
    @Override
    public String addInvalidReason(String token, SceneInvalidReason sceneInvalidReason) throws SceneException {
        ErrorBean errorBean = new ErrorBean() ;
        String result = "" ;
        int id  = (int) Memcached.get(token);
        sceneInvalidReason.setSceneId(id);
        try {
            sceneInvalidReason = sceneInvalidReasonService.saveSceneInvalidReason(sceneInvalidReason) ;
        } catch (Exception e) {
            logger.error("保存数据库失败",e);
            errorBean.setErrno("100100");
            errorBean.setMsg("无效原因后台异常"); ;
            throw new SceneException(errorBean) ;
        }
        return "{\"status\":0,\"result\":"+sceneInvalidReason.getId()+"}";
    }

    /**
     * 根据用户ID获取经济人列表
     *
     * @param token
     * @param userId
     * @return
     * @throws SceneException
     */
    @Override
    public SceneFilingsBean getAgencyList(String token, Integer userId) throws SceneException {
        int id  = (int) Memcached.get(token);
        SceneFilingsBean sceneFilingsBean = new SceneFilingsBean() ;
        List<SceneFiling> sceneFilings = new ArrayList<SceneFiling>() ;
        SceneFiling sceneFiling = new SceneFiling() ;
        sceneFilings = sceneFilingService.findAgencyBy4UserId(id,userId) ;
        if (sceneFilings == null || sceneFilings.size() == 0) {
            sceneFilings = sceneFilingService.findAgencyListByUserId(id,userId) ;
            if(sceneFilings == null || sceneFilings.size() == 0) {
                // 自然人，查询用户表，写报备表
                User user = sceneDealService.findUserById(id) ;
                sceneFiling.setUsername(user.getRealName());
                sceneFiling.setUserId(user.getId());
                sceneFiling.setUserTelephone(user.getPhoneNumber());
                sceneFiling.setHeadImg(user.getHeadImg());
                sceneFiling.setStatus(57003);
                sceneFiling.setSceneId(id);
                sceneFiling = sceneFilingService.saveSceneFiling(sceneFiling) ;
                sceneFilings.add(sceneFiling) ;
            }
        }
        sceneFilingsBean.setSceneFilingList(sceneFilings);
        return sceneFilingsBean;
    }

    /**
     *  待看房列表中看房业务实现
     * @param token
     * @param filingId
     * @return
     * @throws SceneException
     */
    @Override
    public String userVisted(String token, Integer filingId) throws SceneException {
        ErrorBean errorBean = new ErrorBean() ;
        int sceneId  = (int) Memcached.get(token);
        SceneVisit sceneVisit = new SceneVisit();

        SceneFiling sceneFiling = sceneFilingService.findById(filingId) ;
        if (sceneId != sceneFiling.getSceneId()) {
            errorBean.setErrno("300014");
            errorBean.setMsg("无权读取信息！");
            throw new SceneException(errorBean) ;
        }
        if (sceneFiling == null) {
            errorBean.setErrno("300011");
            errorBean.setMsg("该报备信息为空！");
            throw new SceneException(errorBean) ;
        }
        try {
            sceneFiling.setStatus(57004);
            sceneFilingService.updateSceneFiling(sceneFiling);
            sceneVisit.setSceneId(sceneFiling.getSceneId());
            sceneVisit.setFilingId(sceneFiling.getId());
            sceneVisit.setAgencyId(sceneFiling.getAgencyId());
            sceneVisit.setHouseId(sceneFiling.getHouseId());
            sceneVisit.setUserId(sceneFiling.getUserId());
            sceneVisit = sceneVisitService.saveSceneVisit(sceneVisit) ;
        } catch (Exception e) {
            logger.error("添加看房信息异常",e);
            errorBean.setErrno("100100");
            errorBean.setMsg("看房后台异常");
            throw new SceneException(errorBean) ;
        }
        return  "{\"status\":0,\"result\":"+sceneVisit.getId()+"}";
    }

    /**
     * 爽约服务
     *
     * @param token
     * @param filingId
     * @return
     * @throws SceneException
     */
    @Override
    public String userMissed(String token, Integer filingId,String missReason) throws SceneException {
        ErrorBean errorBean = new ErrorBean() ;
        SceneMissReason sceneMissReason = new SceneMissReason() ;
        String result = "" ;
        int sceneId  = (int) Memcached.get(token);
        SceneFiling sceneFiling = sceneFilingService.findById(filingId) ;
        if (sceneId != sceneFiling.getSceneId()) {
            errorBean.setErrno("300014");
            errorBean.setMsg("无权读取信息！");
            throw new SceneException(errorBean) ;
        }
        if (sceneFiling == null) {
            errorBean.setErrno("300011");
            errorBean.setMsg("该报备信息为空！");
            throw new SceneException(errorBean) ;
        }
        try {
            sceneMissReason.setSceneId(sceneFiling.getSceneId());
            sceneMissReason.setFilingId(sceneFiling.getId());
            sceneMissReason.setHouseId(sceneFiling.getHouseId());
            sceneMissReason.setAgencyId(sceneFiling.getAgencyId());
            sceneMissReason.setMissReason(missReason);
            sceneMissReason.setUserId(sceneFiling.getUserId());
            sceneMissReason = sceneMissReasonService.saveSceneMissReason(sceneMissReason) ;
        } catch (Exception e) {
            logger.error("保存数据库失败",e);
            errorBean.setErrno("100100");
            errorBean.setMsg("无效原因后台异常"); ;
            throw new SceneException(errorBean) ;
        }
        return "{\"status\":0,\"result\":"+sceneMissReason.getId()+"}";
    }

    /**
     * 备注
     *
     * @param token
     * @param filingId
     * @param remark
     * @return
     * @throws SceneException
     */
    @Override
    public String remark(String token, Integer filingId, String remark) throws SceneException {
        ErrorBean errorBean = new ErrorBean() ;
        SceneRemark sceneRemark = new SceneRemark() ;
        String result = "" ;
        int sceneId  = (int) Memcached.get(token);
        SceneFiling sceneFiling = sceneFilingService.findById(filingId) ;
        if (sceneId != sceneFiling.getSceneId()) {
            errorBean.setErrno("300014");
            errorBean.setMsg("无权读取信息！");
            throw new SceneException(errorBean) ;
        }
        if (sceneFiling == null) {
            errorBean.setErrno("300011");
            errorBean.setMsg("该报备信息为空！");
            throw new SceneException(errorBean) ;
        }
        try {
            sceneRemark.setSceneId(sceneFiling.getSceneId());
            sceneRemark.setFilingId(sceneFiling.getId());
            sceneRemark.setHouseId(sceneFiling.getHouseId());
            sceneRemark.setAgencyId(sceneFiling.getAgencyId());
            sceneRemark.setRemarkInfo(remark);
            sceneRemark.setUserId(sceneFiling.getUserId());
            sceneRemark = sceneRemarkService.saveSceneRemark(sceneRemark);
        } catch (Exception e) {
            logger.error("保存数据库失败",e);
            errorBean.setErrno("100100");
            errorBean.setMsg("无效原因后台异常"); ;
            throw new SceneException(errorBean) ;
        }
        return "{\"status\":0,\"result\":"+sceneRemark.getId()+"}";
    }

    /**
     * 查询已报备列表
     *
     * @param token
     * @param cp
     * @param cp
     * @return
     */
    @Override
    public SceneFilingsBean allAwaitVisit(String token,  Integer cp,Integer ls) throws SceneException {
        int id  = (int) Memcached.get(token);
        SceneFilingsBean sceneFilingsBean = new SceneFilingsBean() ;
        List<SceneFiling> list = sceneFilingService.findbyReserver(id,57003,cp,ls) ;
        int count = sceneFilingService.allByReserve(id,57003) ;
        sceneFilingsBean.setSceneFilingList(list);
        int page = (int) Math.ceil(count/10);
        sceneFilingsBean.setTotal(page);
        return sceneFilingsBean;
    }

    /**
     * 客户管理列表
     *
     * @param token
     * @param status
     * @param activityId
     * @param intentId
     * @param cp
     * @param ls
     * @return
     * @throws SceneException
     */
    @Override
    public SceneFilingsBean clientManage(String token, Integer status, Integer activityId, Integer intentId, Integer cp, Integer ls) throws SceneException {
        int id  = (int) Memcached.get(token);
        SceneFilingsBean sceneFilingsBean = new SceneFilingsBean() ;
        List<SceneFiling> list = sceneFilingService.queryClient(id,status,activityId,intentId,cp,ls) ;
        sceneFilingsBean.setSceneFilingList(list);
        return sceneFilingsBean;
    }

    /**
     * 根据用户姓名和案场ID查询用户信息
     *
     * @param token
     * @param name
     * @param cp
     * @param ls
     * @return
     */
    @Override
    public SceneFilingsBean clientFindByName(String token, String name, Integer cp, Integer ls) throws SceneException {
        int id  = (int) Memcached.get(token);
        SceneFilingsBean sceneFilingsBean = new SceneFilingsBean() ;
        List<SceneFiling> list = sceneFilingService.findByName(id,name,cp,ls) ;
        sceneFilingsBean.setSceneFilingList(list);
        return sceneFilingsBean;
    }
}
