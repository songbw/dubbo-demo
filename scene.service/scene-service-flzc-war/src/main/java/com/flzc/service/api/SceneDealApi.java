package com.flzc.service.api;

import com.flzc.base.util.Memcached;
import com.flzc.scene.filing.api.entity.SceneDeal;
import com.flzc.scene.filing.api.entity.SceneDealImg;
import com.flzc.service.bean.SceneFilingView;
import com.flzc.service.common.Constant;
import com.flzc.service.exception.ErrorBean;
import com.flzc.service.exception.SceneException;
import com.flzc.service.exception.SceneResponseException;
import com.flzc.service.service.SceneDealBizService;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.InputStream;

/**
 *  成交接口
 * Created by song on 2015/11/21.
 */
@Component
@Scope
@Path("/deal")
public class SceneDealApi {

    @Autowired
    public SceneDealBizService sceneDealBizService ;

    /**
     *   保存合同信息接口
     * @param tokenId
     * @param filingId
     * @param houseTempletType
     * @param houseFace
     * @param floor
     * @param acreage
     * @param dealSum
     * @return
     */
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)

    public String addDeal(@FormParam("tokenId") String tokenId, @FormParam("filingId") Integer filingId, @FormParam("houseTempletType") String houseTempletType,
                          @FormParam("houseFace") String houseFace, @FormParam("floor") Integer floor,@FormParam("acreage") Float acreage,@FormParam("dealSum") Float dealSum, @FormParam("originalSum") Float originalSum) {
        ErrorBean errorBean = new ErrorBean() ;
        if (filingId == null || filingId == 0) {
            errorBean.setErrno("300021") ;
            errorBean.setMsg("报备ID不能为空");
            throw new SceneResponseException(errorBean) ;
        }
        if (houseTempletType == null || "".equals(houseTempletType)) {
            errorBean.setErrno("300022") ;
            errorBean.setMsg("户型不能为空");
            throw new SceneResponseException(errorBean) ;
        }
//        if (houseFace == null || "".equals(houseFace)) {
//            errorBean.setErrno("300023") ;
//            errorBean.setMsg("朝向不能为空");
//            throw new SceneResponseException(errorBean) ;
//        }
//        if (floor == null || floor == 0) {
//            errorBean.setErrno("300024") ;
//            errorBean.setMsg("楼层不能为空");
//            throw new SceneResponseException(errorBean) ;
//        }
        if (acreage == null || acreage == 0) {
            errorBean.setErrno("300025") ;
            errorBean.setMsg("面积不能为空");
            throw new SceneResponseException(errorBean) ;
        }
        if (dealSum == null || dealSum == 0) {
            errorBean.setErrno("300026") ;
            errorBean.setMsg("成交额不能为空");
            throw new SceneResponseException(errorBean) ;
        }
        SceneDeal sceneDeal = new SceneDeal() ;
        sceneDeal.setAcreage(acreage);
        sceneDeal.setFilingId(filingId);
        sceneDeal.setDealSum(dealSum);
        sceneDeal.setFloor(floor);
        sceneDeal.setHouseFace(houseFace);
        sceneDeal.setHouseTempletType(houseTempletType);
        String result = "" ;
        if (Memcached.keyExists(tokenId)) {
            try {
                result = sceneDealBizService.addDeal(tokenId, sceneDeal);
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
     *   上传合同图片
     * @param tokenId
     * @param fileInputStream
     * @param contentDispositionHeader
     * @return
     */
    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String addDealImg(@FormDataParam("tokenId") String tokenId,@FormDataParam("dealImg") InputStream fileInputStream,
                             @FormDataParam("dealImg") FormDataContentDisposition contentDispositionHeader,@FormDataParam("dealId") Integer dealId) {
        ErrorBean errorBean = new ErrorBean() ;
        SceneDealImg sceneDealImg = new SceneDealImg() ;
        String result = "" ;
        if (dealId == null || dealId == 0) {
            errorBean.setErrno("300020");
            errorBean.setMsg("成交ID不能为空！");
            throw new SceneResponseException(errorBean) ;
        }
        if (Memcached.keyExists(tokenId)) {
            try {
                result = sceneDealBizService.addDealImg(tokenId,fileInputStream,contentDispositionHeader.getFileName(), Constant.UPLOAD_HEAD_IMAGE_PATH,dealId) ;
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
     *   根据ID获取成交信息
     * @param tokenId
     * @param id
     * @return
     */
    @GET
    @Path("/finddeal")
    @Produces(MediaType.APPLICATION_JSON)
    public SceneDeal getDeal(@QueryParam("tokenId") String tokenId, @QueryParam("id") Integer id) {
        ErrorBean errorBean = new ErrorBean() ;
        SceneDeal sceneDeal = new SceneDeal() ;
        if (id == null || id == 0) {
            errorBean.setErrno("300028");
            errorBean.setMsg("成交ID不能为空");
            throw new SceneResponseException(errorBean) ;
        }
        if (Memcached.keyExists(tokenId)) {
            try {
                sceneDeal = sceneDealBizService.findDealById(tokenId,id);
            } catch (SceneException e) {
                throw new SceneResponseException(e.getErrorBean()) ;
            }
        } else {
            errorBean.setErrno("300101");
            errorBean.setMsg("tokenId验证失败！");
            throw new SceneResponseException(errorBean) ;
        }
        return sceneDeal ;
    }


    /**
     *   核销
     * @param tokenId
     * @param code
     * @return
     */
    @GET
    @Path("/verify")
    @Produces(MediaType.APPLICATION_JSON)
    public SceneFilingView getVerification(@QueryParam("tokenId") String tokenId, @QueryParam("code") String code) {
        ErrorBean errorBean = new ErrorBean() ;
        SceneFilingView sceneFilingView = new SceneFilingView() ;
        if (code == null || "".equals(code)) {
            errorBean.setErrno("300031");
            errorBean.setMsg("核销码不能为空");
            throw new SceneResponseException(errorBean) ;
        }
        if (Memcached.keyExists(tokenId)) {
            try {
                sceneFilingView = sceneDealBizService.verification(tokenId,code) ;
            } catch (SceneException e) {
                throw new SceneResponseException(e.getErrorBean()) ;
            }
        } else {
            errorBean.setErrno("300101");
            errorBean.setMsg("tokenId验证失败！");
            throw new SceneResponseException(errorBean) ;
        }
        return sceneFilingView ;
    }
}
