package com.flzc.quartz.controller;

import com.alibaba.fastjson.JSONObject;
import com.flzc.quartz.entity.SchedulerConfigInfo;
import com.flzc.quartz.service.CommonService;
import com.flzc.quartz.service.SchedulerJobService;
import com.flzc.quartz.util.SchedulerUtils;
import com.flzc.quartz.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by iverson on 2015/9/19.
 */
@Controller
@RequestMapping("scheduler")
public class SchedulerJobController {

    @Autowired
    private SchedulerJobService jobService;

    @Autowired
    private CommonService commonService;

    @RequestMapping("list")
    public ModelAndView listJobs(){
        ModelAndView  mv = new ModelAndView("scheduler_jobs_list");
        try {
            List<SchedulerConfigInfo> jobs = this.jobService.queryAll();
            mv.addObject("jobs",jobs);
        } catch (Exception e) {
        }
        return mv;
    }

    @RequestMapping("cron/modify")
    @ResponseBody
    public String modifyCron(SchedulerConfigInfo info){
        JSONObject result = JsonUtils.getDefaultJsonForWeb();
        try {
            //验证表达式
            String cronExpress = SchedulerUtils.concatExpress(info);
            if( SchedulerUtils.validateCronExpress(cronExpress)){
                SchedulerConfigInfo old = this.commonService.findById(info.getClass(), info.getId());
                old.setSecond(info.getSecond());
                old.setMinute(info.getMinute());
                old.setHour(info.getHour());
                old.setMonth(info.getMonth());
                old.setYear(info.getYear());
                old.setWeek(info.getWeek());
                old.setDay(info.getDay());
                this.jobService.update(old);
            }else{
                result.put(JsonUtils.SUCC,false);
                result.put(JsonUtils.MSG,"表达式有误");
            }
        } catch (Exception e) {
            result.put(JsonUtils.SUCC,false);
            result.put(JsonUtils.MSG,"系统异常");
        }
        return result.toJSONString();
    }

    /**
     * 启用，禁用
     * @param id
     * @param status
     * @return
     */
    @RequestMapping("disabled")
    @ResponseBody
    public String modifyCron(Integer id , Integer status){
        JSONObject result = JsonUtils.getDefaultJsonForWeb();
        try {
            SchedulerConfigInfo old = this.commonService.findById(SchedulerConfigInfo.class,id);
            old.setStatus(status);
            this.jobService.update(old);
        } catch (Exception e) {
            result.put(JsonUtils.SUCC,false);
        }
        return result.toJSONString();
    }

}
