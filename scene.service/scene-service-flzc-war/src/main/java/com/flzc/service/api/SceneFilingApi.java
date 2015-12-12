package com.flzc.service.api;

import com.flzc.base.util.Memcached;
import com.flzc.base.util.UserUtil;
import com.flzc.scene.filing.api.entity.SceneFiling;
import com.flzc.scene.filing.api.entity.SceneInvalidReason;
import com.flzc.service.bean.SceneFilingView;
import com.flzc.service.bean.SceneFilingsBean;
import com.flzc.service.bean.SceneInfoView;
import com.flzc.service.exception.ErrorBean;
import com.flzc.service.exception.SceneException;
import com.flzc.service.exception.SceneResponseException;
import com.flzc.service.service.SceneFilingBizService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 *   案场报备接口
 * Created by song on 2015/11/20.
 */
@Component
@Scope
@Path("/filing")
public class SceneFilingApi {

    private static  final Logger LOGGER = LoggerFactory.getLogger(SceneFilingApi.class);

    @Autowired
    private SceneFilingBizService sceneFilingBizService ;

    /**
     *  看房接口
     * @param tokenId
     * @param filingId
     * @return
     */
    @Path("/visit")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public String userVisited(@FormParam("tokenId") String tokenId, @FormParam("filingId") Integer filingId) {
        LOGGER.error(tokenId + "    " + filingId);
        ErrorBean errorBean = new ErrorBean() ;
        if (filingId ==null || filingId == 0) {
            errorBean.setErrno("300017");
            errorBean.setMsg("报备ID不能为空！");
            throw new SceneResponseException(errorBean) ;
        }
        String result = "" ;
        if (Memcached.keyExists(tokenId)) {
            try {
                result = sceneFilingBizService.userVisted(tokenId,filingId);
            } catch (SceneException e) {
                throw new SceneResponseException(e.getErrorBean()) ;
            }
        } else {
            errorBean.setErrno("300101");
            errorBean.setMsg("tokenId验证失败！");
            throw new SceneResponseException(errorBean) ;
        }
        return result ;
    }
    /**
     *  获取经纪人列表
     * @param tokenId
     * @param userId
     * @return
     */
    @GET
    @Path("/agency/list")
    @Produces(MediaType.APPLICATION_JSON)
    public SceneFilingsBean getAgencyList(@QueryParam("tokenId") String tokenId, @QueryParam("userId") String userId){
        String[] userToken = userId.split("_") ;
        ErrorBean errorBean = new ErrorBean() ;
        SceneFilingsBean sceneFilings = null ;
        if (userToken[0] ==null || "".equals(userToken[0])) {
            errorBean.setErrno("300016");
            errorBean.setMsg("userId不能为空！");
            throw new SceneResponseException(errorBean) ;
        }
        if (Memcached.keyExists(tokenId)) {
            try {
                int id = UserUtil.getUserIdByTokenId(userToken[0]) ;
                sceneFilings = sceneFilingBizService.getAgencyList(tokenId,id) ;
            } catch (SceneException e) {
                throw new SceneResponseException(e.getErrorBean()) ;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            errorBean.setErrno("300101");
            errorBean.setMsg("tokenId验证失败！");
            throw new SceneResponseException(errorBean) ;
        }
        return sceneFilings ;
    }
    /**
     *  添加报备信息
     * @param sceneFiling
     * @return
     */
    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String addFiling(SceneFiling sceneFiling) {
        ErrorBean errorBean = new ErrorBean() ;
        if (sceneFiling.getUserId() == null || sceneFiling.getUserId() == 0) {
            errorBean.setErrno("300035");
            errorBean.setMsg("userId不能为空");
            throw new SceneResponseException(errorBean) ;
        }
        if (sceneFiling.getAgencyId() == null || sceneFiling.getAgencyId() == 0) {
            errorBean.setErrno("300036");
            errorBean.setMsg("agencyId不能为空");
            throw new SceneResponseException(errorBean) ;
        }
        if (sceneFiling.getIntentId() == null || sceneFiling.getIntentId() == 0) {
            errorBean.setErrno("300037");
            errorBean.setMsg("intentId不能为空");
            throw new SceneResponseException(errorBean) ;
        }
        if (sceneFiling.getHouseId() == null || sceneFiling.getHouseId() == 0) {
            errorBean.setErrno("300038");
            errorBean.setMsg("houseId不能为空");
            throw new SceneResponseException(errorBean) ;
        }
        if (sceneFiling.getAgencyCompanyId() == null || sceneFiling.getAgencyCompanyId() == 0) {
            errorBean.setErrno("300039");
            errorBean.setMsg("agencyCompanyId不能为空");
            throw new SceneResponseException(errorBean) ;
        }
        String result = "" ;
        try {
            result = sceneFilingBizService.addSceneFiling(sceneFiling) ;
        } catch (SceneException e) {
            throw new SceneResponseException(e.getErrorBean()) ;
        }
        return result ;
    }

    /**
     *    获取未报备列表
     * @param tokenId
     * @param page
     * @return
     */
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public SceneFilingsBean getAllFiling(@QueryParam("tokenId") String tokenId, @QueryParam("page") Integer page,@QueryParam("pageSize") Integer pageSize) {
        ErrorBean errorBean = new ErrorBean() ;
        SceneFilingsBean sceneFilings = new SceneFilingsBean() ;
        if (page ==null || page == 0) {
            page = 1 ;
        }
        if (pageSize ==null || pageSize == 0) {
            pageSize = 10 ;
        }
        if (Memcached.keyExists(tokenId)) {
            try {
                sceneFilings = sceneFilingBizService.allFiling(tokenId,page,pageSize) ;
            } catch (SceneException e) {
                throw new SceneResponseException(e.getErrorBean()) ;
            }
        } else {
            errorBean.setErrno("300101");
            errorBean.setMsg("tokenId验证失败！");
            throw new SceneResponseException(errorBean) ;
        }
        return sceneFilings ;
    }

    /**
     *  获取指定报备信息
     * @param tokenId
     * @param filingId
     * @return
     */
    @Path("/info")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public SceneFilingView getFiling(@QueryParam("tokenId") String tokenId, @QueryParam("filingId") Integer filingId) {
        ErrorBean errorBean = new ErrorBean() ;
        SceneFilingView sceneFilingView = new SceneFilingView() ;
        SceneFiling sceneFiling = new SceneFiling() ;
        if (filingId ==null || filingId == 0) {
            errorBean.setErrno("300013");
            errorBean.setErrno("报备ID不能为空！");
            throw new SceneResponseException(errorBean) ;
        }
        if (Memcached.keyExists(tokenId)) {
            try {
                sceneFiling = sceneFilingBizService.queryFilingInfo(tokenId,filingId) ;

            } catch (SceneException e) {
                throw new SceneResponseException(e.getErrorBean()) ;
            }
        } else {
            errorBean.setErrno("300101");
            errorBean.setMsg("tokenId验证失败！");
            throw new SceneResponseException(errorBean) ;
        }
        if (sceneFiling ==null) {
            errorBean.setErrno("300028");
            errorBean.setMsg("该条报备信息不存在！");
            throw new SceneResponseException(errorBean) ;
        }
        sceneFilingView.setSceneFiling(sceneFiling);
        return sceneFilingView ;
    }
    /**
     *  有效无效接口
     * @param tokenId
     * @param filingId
     * @param valid
     * @return
     */
    @Path("/valid")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public String validOrInvalid(@FormParam("tokenId") String tokenId, @FormParam("filingId") Integer filingId, @FormParam("valid") Integer valid) {
        String result = "" ;
        ErrorBean errorBean = new ErrorBean() ;
        if (filingId ==null || filingId == 0) {
            errorBean.setErrno("300013");
            errorBean.setErrno("报备ID不能为空！");
            throw new SceneResponseException(errorBean) ;
        }
        if (valid ==null) {
            errorBean.setErrno("300014");
            errorBean.setErrno("有效性字段不能为空！");
            throw new SceneResponseException(errorBean) ;
        }
        if (Memcached.keyExists(tokenId)) {
            try {
                result = sceneFilingBizService.filingValid(tokenId,filingId,valid) ;
            } catch (SceneException e) {
                throw new SceneResponseException(e.getErrorBean()) ;
            }
        } else {
            errorBean.setErrno("300101");
            errorBean.setMsg("tokenId验证失败！");
            throw new SceneResponseException(errorBean) ;
        }
        return result;
    }

    @Path("/update/reserve")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public String updateReserve(@FormParam("tokenId") String tokenId, @FormParam("filingId") Integer filingId, @FormParam("reserve") Long reserve) {
        String result = "" ;
        ErrorBean errorBean = new ErrorBean() ;
        if (filingId ==null || filingId == 0) {
            errorBean.setErrno("300013");
            errorBean.setErrno("报备ID不能为空！");
            throw new SceneResponseException(errorBean) ;
        }
        if (reserve ==null) {
            errorBean.setErrno("300019");
            errorBean.setErrno("时间字段不能为空！");
            throw new SceneResponseException(errorBean) ;
        }
        if (Memcached.keyExists(tokenId)) {
            try {
                result = sceneFilingBizService.updateReserve(tokenId,filingId,reserve) ;
            } catch (SceneException e) {
                throw new SceneResponseException(e.getErrorBean()) ;
            }
        } else {
            errorBean.setErrno("300101");
            errorBean.setMsg("tokenId验证失败！");
            throw new SceneResponseException(errorBean) ;
        }
        return result;
    }

    /**
     *   填写无效原因接口
     * @param tokenId
     * @param reasonInfo
     * @return
     * @throws Exception
     */
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/invalid/reason")
    public String invalidReason(@FormParam("tokenId") String tokenId, @FormParam("reasonInfo") String reasonInfo, @FormParam("filingId") Integer filingId)  throws Exception {
        LOGGER.error(tokenId + "   " +  reasonInfo + "     " + filingId);
        String result = "" ;
        SceneInvalidReason sceneInvalidReason = new SceneInvalidReason() ;
        ErrorBean errorBean = new ErrorBean() ;
        sceneInvalidReason.setReasonInfo(reasonInfo);
        if (reasonInfo == null ||  "".equals(reasonInfo)) {
            errorBean.setErrno("300010");
            errorBean.setErrno("无效原因不能为空！");
            throw new SceneResponseException(errorBean) ;
        }
        if (Memcached.keyExists(tokenId)) {
            try {
                result = sceneFilingBizService.addInvalidReason(tokenId,sceneInvalidReason) ;
            } catch (SceneException e) {
                throw new SceneResponseException(e.getErrorBean()) ;
            }
        } else {
            errorBean.setErrno("300101");
            errorBean.setMsg("tokenId验证失败！");
            throw new SceneResponseException(errorBean) ;
        }
        return result;
    }

    /**
     *   添加爽约原因
     * @param tokenId
     * @param reasonInfo
     * @param filingId
     * @return
     * @throws Exception
     */
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/miss/reason")
    public String missReason(@FormParam("tokenId") String tokenId, @FormParam("reasonInfo") String reasonInfo, @FormParam("filingId") Integer filingId)  throws Exception {
        LOGGER.error(tokenId + "   " + reasonInfo + "    " + filingId);
        String result = "" ;
        ErrorBean errorBean = new ErrorBean() ;
        if (reasonInfo == null ||  "".equals(reasonInfo)) {
            errorBean.setErrno("300010");
            errorBean.setErrno("无效原因不能为空！");
            throw new SceneResponseException(errorBean) ;
        }
        if (filingId == null ||  filingId == 0) {
            errorBean.setErrno("300018");
            errorBean.setMsg("报备ID不能为空！");
            throw new SceneResponseException(errorBean) ;
        }
        if (Memcached.keyExists(tokenId)) {
            try {
                result = sceneFilingBizService.userMissed(tokenId,filingId,reasonInfo) ;
            } catch (SceneException e) {
                throw new SceneResponseException(e.getErrorBean()) ;
            }
        } else {
            errorBean.setErrno("300101");
            errorBean.setMsg("tokenId验证失败！");
            throw new SceneResponseException(errorBean) ;
        }
        return result;
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/remark")
    public String remark(@FormParam("tokenId") String tokenId, @FormParam("remark") String remark, @FormParam("filingId") Integer filingId)  throws Exception {
        String result = "" ;
        ErrorBean errorBean = new ErrorBean() ;
        if (remark == null ||  "".equals(remark)) {
            errorBean.setErrno("300010");
            errorBean.setErrno("备注内容不能为空！");
            throw new SceneResponseException(errorBean) ;
        }
        if (filingId == null ||  filingId == 0) {
            errorBean.setErrno("300018");
            errorBean.setMsg("报备ID不能为空！");
            throw new SceneResponseException(errorBean) ;
        }
        if (Memcached.keyExists(tokenId)) {
            try {
                result = sceneFilingBizService.remark(tokenId,filingId,remark);
            } catch (SceneException e) {
                throw new SceneResponseException(e.getErrorBean()) ;
            }
        } else {
            errorBean.setErrno("300101");
            errorBean.setMsg("tokenId验证失败！");
            throw new SceneResponseException(errorBean) ;
        }
        return result;
    }

    /**
     *    获取已报备列表
     * @param tokenId
     * @param page
     * @return
     */
    @Path("/waitvisit")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public SceneFilingsBean getAllReserve(@QueryParam("tokenId") String tokenId, @QueryParam("page") Integer page,@QueryParam("pageSize") Integer pageSize) {
        ErrorBean errorBean = new ErrorBean() ;
        SceneFilingsBean sceneFilings = null ;
        if (page ==null || page == 0) {
            page = 1 ;
        }
        if (pageSize ==null || pageSize == 0) {
            pageSize = 10 ;
        }
        if (Memcached.keyExists(tokenId)) {
            try {
                sceneFilings = sceneFilingBizService.allAwaitVisit(tokenId,page,pageSize) ;
            } catch (SceneException e) {
                throw new SceneResponseException(e.getErrorBean()) ;
            }
        } else {
            errorBean.setErrno("300101");
            errorBean.setMsg("tokenId验证失败！");
            throw new SceneResponseException(errorBean) ;
        }
        return sceneFilings ;
    }

    /**
     *  客户管理列表
     * @param tokenId
     * @param status
     * @param activityId
     * @param intentId
     * @param page
     * @param pageSize
     * @return
     */
    @Path("/client")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public SceneFilingsBean clientManage(@QueryParam("tokenId") String tokenId,
                                         @QueryParam("status") Integer status, @QueryParam("activityId") Integer activityId,
                                         @QueryParam("intentId") Integer intentId,@QueryParam("page") Integer page,@QueryParam("pageSize") Integer pageSize) {
        ErrorBean errorBean = new ErrorBean() ;
        SceneFilingsBean sceneFilings = null ;
        if (page ==null || page == 0) {
            page = 1 ;
        }
        if (pageSize ==null || pageSize == 0) {
            pageSize = 10 ;
        }
        if (Memcached.keyExists(tokenId)) {
            try {
                sceneFilings = sceneFilingBizService.clientManage(tokenId,status,activityId,intentId,page,pageSize) ;
            } catch (SceneException e) {
                throw new SceneResponseException(e.getErrorBean()) ;
            }
        } else {
            errorBean.setErrno("300101");
            errorBean.setMsg("tokenId验证失败！");
            throw new SceneResponseException(errorBean) ;
        }
        return sceneFilings ;
    }

    @Path("/client/name")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public SceneFilingsBean clientFindByName(@QueryParam("tokenId") String tokenId,
                                         @QueryParam("name") String name, @QueryParam("page") Integer page,@QueryParam("pageSize") Integer pageSize) {
        ErrorBean errorBean = new ErrorBean() ;
        SceneFilingsBean sceneFilings = null ;
        if (page ==null || page == 0) {
            page = 1 ;
        }
        if (pageSize ==null || pageSize == 0) {
            pageSize = 10 ;
        }
        if (Memcached.keyExists(tokenId)) {
            try {
                sceneFilings = sceneFilingBizService.clientFindByName(tokenId,name,page,pageSize) ;
            } catch (SceneException e) {
                throw new SceneResponseException(e.getErrorBean()) ;
            }
        } else {
            errorBean.setErrno("300101");
            errorBean.setMsg("tokenId验证失败！");
            throw new SceneResponseException(errorBean) ;
        }
        return sceneFilings ;
    }
}
