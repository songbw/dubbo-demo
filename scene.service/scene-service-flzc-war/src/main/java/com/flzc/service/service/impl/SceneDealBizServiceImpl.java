package com.flzc.service.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.flzc.base.util.DateUtil;
import com.flzc.base.util.Memcached;
import com.flzc.file.upload.api.entity.FileUploadBean;
import com.flzc.file.upload.api.service.FileUploadService;
import com.flzc.rob.api.entity.HouseBuildingTags;
import com.flzc.rob.api.service.HouseBuildingTagsService;
import com.flzc.scene.filing.api.entity.*;
import com.flzc.scene.filing.api.service.SceneDealImgService;
import com.flzc.scene.filing.api.service.SceneDealService;
import com.flzc.scene.filing.api.service.SceneFilingService;
import com.flzc.service.bean.SceneFilingView;
import com.flzc.service.exception.ErrorBean;
import com.flzc.service.exception.SceneException;
import com.flzc.service.service.SceneDealBizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 *  合同业务实现类
 * Created by song on 2015/11/21.
 */
@Service
public class SceneDealBizServiceImpl implements SceneDealBizService {

    @Autowired
    private SceneDealService sceneDealService ;

    @Autowired
    private SceneDealImgService sceneDealImgService ;

    @Autowired
    private SceneFilingService sceneFilingService ;

    @Autowired
    private FileUploadService fileUploadService ;

    @Autowired
    private HouseBuildingTagsService houseBuildingTagsService ;

    /**
     * 添加成交信息
     *
     * @param sceneDeal
     * @return
     * @throws SceneException
     */
    @Override
    public String addDeal(String token ,SceneDeal sceneDeal) throws SceneException {
        ErrorBean errorBean = new ErrorBean() ;
        int sceneId = (int) Memcached.get(token);
        sceneDeal.setSceneId(sceneId);
        SceneFiling sceneFiling = sceneFilingService.findById(sceneDeal.getFilingId()) ;
        if (sceneFiling == null) {
            errorBean.setErrno("300031");
            errorBean.setMsg("报备信息不存在");
            throw new SceneException(errorBean) ;
        }
        sceneDeal.setAgencyId(sceneFiling.getAgencyId());
        sceneDeal.setAgencyName(sceneFiling.getAgencyName());
        sceneDeal.setAgencyTelephone(sceneFiling.getAgencyTelephone());
        sceneDeal.setBuilderReward(sceneFiling.getBuilderReward());
        sceneDeal.setCouponSum(sceneFiling.getCouponSum());
        sceneDeal.setFlzcReward(sceneFiling.getFlzcReward());
        sceneDeal.setHouseId(sceneFiling.getHouseId());
        sceneDeal.setName(sceneFiling.getName());
        sceneDeal.setOriginalSum(sceneFiling.getOriginalSum());
        sceneDeal.setRebate(sceneFiling.getRebate());
        sceneDeal.setUserId(sceneFiling.getUserId());
        sceneDeal.setUsername(sceneFiling.getUsername());
        sceneDeal.setUserSex(sceneFiling.getUserSex());
        sceneDeal.setUserTelephone(sceneFiling.getUserTelephone());
        sceneFiling.setStatus(57005);
        try {
            sceneFilingService.updateSceneFiling(sceneFiling);
            sceneDeal = sceneDealService.saveSceneDeal(sceneDeal) ;
        } catch (Exception e) {
            errorBean.setErrno("300100");
            errorBean.setMsg("后台服务异常！");
            throw new SceneException(errorBean) ;
        }
        return "{\"status\":0,\"result\":"+ sceneDeal.getId()+"}";
    }

    /**
     * 添加图片接口
     *
     * @param tokenId
     * @return
     * @throws SceneException
     */
    @Override
    public String addDealImg(String tokenId, InputStream file, String originalFilename, String storePath, Integer dealId) throws SceneException {
        ErrorBean errorBean = new ErrorBean() ;
        if (file == null) {
            errorBean.setErrno("300021");
            errorBean.setMsg("上传文件为空！");
            throw new SceneException(errorBean) ;
        }
        String newName =  new Date().getTime() +originalFilename ;
        String newPath = storePath + dealId + newName ;

         SceneDealImg sceneDealImg = new SceneDealImg() ;
        FileUploadBean fileUploadBean = new FileUploadBean() ;
        fileUploadBean.setFile(file);
        fileUploadBean.setRelativePath("dealimg/");
        fileUploadBean.setFileName(newPath);
        try {
//            FileUtils.copyInputStreamToFile(file, new File(newPath,String.valueOf(dealId)));
            String path = fileUploadService.uploadIn(newPath,"dealimg/",file) ;
            sceneDealImg.setDealId(dealId);
            sceneDealImg.setImgPath(path);
            sceneDealImg = sceneDealImgService.saveSceneDealImg(sceneDealImg) ;
        } catch (Exception e) {
            e.printStackTrace();
            errorBean.setErrno("300022");
            errorBean.setMsg("图片生成失败！");
            throw new SceneException(errorBean) ;
        }
        return "{\"status\":0,\"result\":"+ sceneDealImg.getId()+"}";
    }

    /**
     * 获取指定成交信息
     *
     * @param token
     * @param id
     * @return
     * @throws SceneException
     */
    @Override
    public SceneDeal findDealById(String token, Integer id) throws SceneException {
        SceneDeal sceneDeal = sceneDealService.findByDealId(id) ;
        ErrorBean errorBean = new ErrorBean() ;
        if (sceneDeal == null) {
            errorBean.setErrno("300027");
            errorBean.setMsg("用户信息不存在！");
            throw new SceneException(errorBean) ;
        }
        return sceneDeal;
    }

    /**
     * 核销
     *
     * @param tokenId
     * @param code
     * @return
     * @throws SceneException
     */
    @Override
    public SceneFilingView verification(String tokenId, String code) throws SceneException {
        ErrorBean errorBean = new ErrorBean() ;
        SceneFilingView sceneFilingView = new SceneFilingView() ;
        int sceneId = (int) Memcached.get(tokenId);
        String[] hexiao = code.split("_") ;
        FlzcTicket flzcTicket = new FlzcTicket() ;
        if (hexiao.length == 0) {
            flzcTicket = sceneDealService.findByTicketCode(code) ;
        } else {
            flzcTicket = sceneDealService.findByTicketCode(hexiao[1]) ;
        }
        long currentTime = new Date().getTime() ;
        if (flzcTicket == null) {
            errorBean.setErrno("300028");
            errorBean.setMsg("该核销码不存在，请确认是否输入有误！");
            throw new SceneException(errorBean) ;
        }
        ActivityRecap activityRecap = sceneDealService.findActivityByIdAndType(flzcTicket.getActivityRecapId(),flzcTicket.getActType())  ;
        if (activityRecap == null) {
            errorBean.setErrno("300032");
            errorBean.setMsg("该活动已不存在！");
            throw new SceneException(errorBean) ;
        }
        if (currentTime < flzcTicket.getStartDate().getTime()) {
            String fromdate = DateUtil.format(flzcTicket.getStartDate(), "yyyy-MM-dd hh:mm:ss");
            errorBean.setErrno("300029");
            errorBean.setMsg("活动未开始，活动开始时间为："+fromdate+"！");
            throw new SceneException(errorBean) ;
        }
        if (currentTime > flzcTicket.getEndDate().getTime()) {
            String fromdate = DateUtil.format(flzcTicket.getEndDate(), "yyyy-MM-dd hh:mm:ss");
            errorBean.setErrno("300030");
            errorBean.setMsg("活动已结束，活动结束时间为："+fromdate+"！");
            throw new SceneException(errorBean) ;
        }
        List<SceneFiling> sceneFilingList = sceneFilingService.findByUserstatus(sceneId, flzcTicket.getUserId(),57004) ;
        HouseBuildingInfo houseBuildingInfo = sceneDealService.findBuildingById(activityRecap.getBuildingId()) ;
        if (houseBuildingInfo == null) {
            errorBean.setErrno("300034");
            errorBean.setMsg("无法核实楼盘信息！");
            throw new SceneException(errorBean) ;
        }
        SceneFiling sceneFiling = new SceneFiling() ;
        User user = sceneDealService.findUserById(flzcTicket.getUserId()) ;
        if (user == null) {
            errorBean.setErrno("300033");
            errorBean.setMsg("无法核实用户信息！");
            throw new SceneException(errorBean) ;
        }
        List<HouseBuildingTags> houseBuildingTagses = houseBuildingTagsService.queryByParentCode("100",0) ;
        sceneFilingView.setHouseBuildingTagses(houseBuildingTagses);
        // 写报备表（用户、楼盘、活动、优惠券信息）
        if (sceneFilingList == null || sceneFilingList.size() == 0 || sceneFilingList.get(0) == null) {
            sceneFiling.setSceneId(sceneId);
            sceneFiling.setHouseId(houseBuildingInfo.getId());
            sceneFiling.setHouseName(houseBuildingInfo.getName());
            sceneFiling.setStatus(57005);
            sceneFiling.setActivityEndAt(activityRecap.getActActiveEndDate());
            sceneFiling.setActivityId(flzcTicket.getActivityRecapId());
            sceneFiling.setActivityName(activityRecap.getActName());
            sceneFiling.setActivityStartAt(flzcTicket.getStartDate());
            sceneFiling.setCouponId(flzcTicket.getId());
            sceneFiling.setCouponSum((float)flzcTicket.getAmount());
            sceneFiling.setCouponInfo(flzcTicket.getActType().toString());
            sceneFiling.setCouponName(flzcTicket.getTicketCode());
            sceneFiling.setUserId(user.getId()) ;
            sceneFiling.setUsername(user.getRealName());
            sceneFiling.setUserTelephone(user.getPhoneNumber());

            sceneFiling.setValid(0);
            sceneFiling = sceneFilingService.saveSceneFiling(sceneFiling) ;
            sceneFilingView.setSceneFiling(sceneFiling);
            return sceneFilingView ;
        }
        sceneFiling = sceneFilingList.get(0) ;
        sceneFiling.setStatus(57005);
        sceneFiling.setHouseId(houseBuildingInfo.getId());
        sceneFiling.setHouseName(houseBuildingInfo.getName());
        sceneFiling.setActivityEndAt(activityRecap.getActActiveEndDate());
        sceneFiling.setActivityId(flzcTicket.getActivityRecapId());
        sceneFiling.setActivityName(activityRecap.getActName());
        sceneFiling.setActivityStartAt(flzcTicket.getStartDate());
        sceneFiling.setCouponId(flzcTicket.getId());
        sceneFiling.setCouponSum((float)flzcTicket.getAmount());
        sceneFiling.setCouponInfo(flzcTicket.getActType().toString());
        sceneFiling.setCouponName(flzcTicket.getTicketCode());
        sceneFiling.setUserId(user.getId()) ;
        sceneFiling.setUsername(user.getRealName());
        sceneFilingService.updateSceneFiling(sceneFiling);
        sceneFilingView.setSceneFiling(sceneFiling);
        return sceneFilingView;
    }
}
