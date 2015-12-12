package com.flzc.service.api;

import com.flzc.base.util.Memcached;
import com.flzc.service.bean.SceneStatisticsBean;
import com.flzc.service.exception.ErrorBean;
import com.flzc.service.exception.SceneException;
import com.flzc.service.exception.SceneResponseException;
import com.flzc.service.service.SceneStatisticsBizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * 数据统计接口
 * Created by song on 2015/12/2.
 */
@Component
@Scope
@Path("/statistics")
public class SceneStatisticsApi {

    @Autowired
    private SceneStatisticsBizService sceneStatisticsBizService ;

    /**
     *  一周数据统计
     * @param tokenId
     * @param fromDate
     * @param toDate
     * @return
     */
    @Path("/week")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public SceneStatisticsBean getWeek(@QueryParam("tokenId") String tokenId, @QueryParam("fromDate") long fromDate, @QueryParam("toDate") long toDate) {
        ErrorBean errorBean = new ErrorBean() ;
        SceneStatisticsBean sceneStatisticsBean = new SceneStatisticsBean() ;
        if (Memcached.keyExists(tokenId)) {
            try {
               sceneStatisticsBean = sceneStatisticsBizService.queryWeek(tokenId,fromDate,toDate);
            } catch (SceneException e) {
                throw new SceneResponseException(e.getErrorBean()) ;
            }
        } else {
            errorBean.setErrno("300101");
            errorBean.setMsg("tokenId验证失败！");
            throw new SceneResponseException(errorBean) ;
        }
        return sceneStatisticsBean ;
    }

    /**
     *   按月统计
     * @param tokenId
     * @param monthDate
     * @return
     */
    @Path("/month")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public SceneStatisticsBean getMonth(@QueryParam("tokenId") String tokenId, @QueryParam("monthDate") long monthDate) {
        ErrorBean errorBean = new ErrorBean() ;
        SceneStatisticsBean sceneStatisticsBean = new SceneStatisticsBean() ;
        if (Memcached.keyExists(tokenId)) {
            try {
                sceneStatisticsBean  = sceneStatisticsBizService.queryMonth(tokenId,monthDate);
            } catch (SceneException e) {
                throw new SceneResponseException(e.getErrorBean()) ;
            }
        } else {
            errorBean.setErrno("300101");
            errorBean.setMsg("tokenId验证失败！");
            throw new SceneResponseException(errorBean) ;
        }
        return sceneStatisticsBean ;
    }

    @Path("/year")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public SceneStatisticsBean getYear(@QueryParam("tokenId") String tokenId, @QueryParam("yearDate") long yearDate) {
        ErrorBean errorBean = new ErrorBean() ;
        SceneStatisticsBean sceneStatisticsBean = new SceneStatisticsBean() ;
        if (Memcached.keyExists(tokenId)) {
            try {
                sceneStatisticsBean = sceneStatisticsBizService.queryYear(tokenId,yearDate);
            } catch (SceneException e) {
                throw new SceneResponseException(e.getErrorBean()) ;
            }
        } else {
            errorBean.setErrno("300101");
            errorBean.setMsg("tokenId验证失败！");
            throw new SceneResponseException(errorBean) ;
        }
        return sceneStatisticsBean ;
    }

}
