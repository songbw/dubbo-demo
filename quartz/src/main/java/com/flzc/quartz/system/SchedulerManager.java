package com.flzc.quartz.system;

import com.flzc.quartz.entity.SchedulerConfigInfo;
import com.flzc.quartz.service.SchedulerJobService;
import com.flzc.quartz.util.SchedulerUtils;
import com.flzc.quartz.util.StringUtils;
import org.quartz.*;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 调度任务管理类
 * Created by iverson on 2015/9/19.
 */
public class SchedulerManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(SchedulerManager.class);

    private static final SchedulerManager instance = new SchedulerManager();

    private ApplicationContext context;

    private ConcurrentMap<String, SchedulerFactoryBean> schedulerMap = new ConcurrentHashMap();


    private SchedulerManager() {
    }

    public void init(ApplicationContext context)  {
        if (context == null) {
            LOGGER.error("the scheduler manager failed.......");
            throw new RuntimeException("the scheduler manager failed ");
        }
        this.context = context;

//        Map<String, SchedulerFactoryBean> schedulerFactoryMap = this.context.getBeansOfType(SchedulerFactoryBean.class);
//        if (schedulerFactoryMap == null || schedulerFactoryMap.isEmpty()) {
//            LOGGER.error("#################System dose not detect schedulers ##############################");
//            return;
//        }
//        this.schedulerMap.putAll(schedulerFactoryMap);

        SchedulerJobService schedulerJobService = this.context.getBean(SchedulerJobService.class);
        try {
            List<SchedulerConfigInfo> schedulerConfigInfos = schedulerJobService.queryAll();
            for (int i = 0 ; i<schedulerConfigInfos.size();i++ ){
                 registerJob(schedulerConfigInfos.get(i));
            }
        } catch (Exception e) {
            LOGGER.error("can not load the scheduler configuration from database",e);
            throw new RuntimeException(e);
        }
    }

    public static SchedulerManager getInstance() {
        return instance;
    }

    public void stopScheduler(String code) throws Exception {
        SchedulerFactoryBean schedulerFactoryBean = getScheduler(code);
        if (schedulerFactoryBean != null) {
            boolean running = schedulerFactoryBean.isRunning();
            if (running) {
                LOGGER.info("stop the  scheduler:" + code);
                schedulerFactoryBean.stop();
            }
        } else {
            LOGGER.error("scheduler:" + code + " not found");
        }
    }

    public void startScheduler(String code) throws Exception {

        SchedulerFactoryBean schedulerFactoryBean = getScheduler(code);
        if (schedulerFactoryBean != null) {
            boolean running = schedulerFactoryBean.isRunning();
            if (!running) {
                LOGGER.info("start the  scheduler:" + code);
                schedulerFactoryBean.start();
            }
        } else {
            LOGGER.error("scheduler:" + code + " not found");
        }

    }

    private SchedulerFactoryBean getScheduler(String code) throws Exception {
        if (StringUtils.isBlank(code)) {
            throw new Exception("scheduler's id is null");
        }
       // return this.schedulerMap.get("&" + code);
        return this.context.getBean("&" + code ,SchedulerFactoryBean.class );
    }

    public Trigger resetCron(String code, String cronExpress) throws Exception {
        LOGGER.info(String.format("reset the cron of the scheduler:param{%1s:%2s}", code, cronExpress));
        if (StringUtils.isBlank(cronExpress) || StringUtils.isBlank(code)) return null;
        Scheduler scheduler = getScheduler(code).getScheduler();
        TriggerKey triggerKey = new TriggerKey(code + "_trigger");
        CronTriggerImpl trigger = (CronTriggerImpl) scheduler.getTrigger(triggerKey);
        trigger.setCronExpression(cronExpress);
       // scheduler.rescheduleJob(trigger.getKey(), trigger);
        return trigger;
    }

    public void rescheduleJob(String code)throws Exception{
        Scheduler scheduler = getScheduler(code).getScheduler();
        TriggerKey triggerKey = new TriggerKey(code + "_trigger");
        Trigger trigger = scheduler.getTrigger(triggerKey);
        scheduler.rescheduleJob(trigger.getKey(), trigger);
    }

    public void rescheduleJob(String schedulerId , Trigger trigger)throws Exception{
        Scheduler scheduler = getScheduler(schedulerId).getScheduler();
        scheduler.rescheduleJob(trigger.getKey(), trigger);
    }

    /**
     * 把从数据库里读取出来的定时任务信息转化成一个定时任务工厂类
     * @param config
     * @throws Exception
     */
    private void registerJob(SchedulerConfigInfo config) throws Exception {
        SpringBeanRegister register = new SpringBeanRegister();
        register.setApplicationContext(this.context);

        //设置业务逻辑处理类
        String jobId = "flzc_jobClass_" + config.getId(); //设置对象在spring 中的id
        if(StringUtils.isBlank(config.getClassName())){
            throw  new NullPointerException(config.getJobName() + "业务处理类不能为空");
        }
        register.registerBean(jobId, config.getClassName(), null);

        //配置jobdetail ,依赖  flzc_jobClass_
        List<PropertyWrapper> wrappers = new ArrayList<>();
        PropertyWrapper targetMethod = new PropertyWrapper("targetMethod","execute");
        PropertyWrapper targetObject = new PropertyWrapper("targetObject",jobId,true);
        wrappers.add(targetMethod);
        wrappers.add(targetObject);
        String jobDetailKey = "flzc_jobDetail_" + config.getId();
        register.registerBean(jobDetailKey, MethodInvokingJobDetailFactoryBean.class, wrappers);

        wrappers.clear();

        // 配置 trigger 依赖 flzc_jobDetail_
        String concatExpress = SchedulerUtils.concatExpress(config);
        boolean valid = SchedulerUtils.validateCronExpress(concatExpress);
        if(!valid){
            throw  new RuntimeException(config.getJobName()+ "的任务时间表达式不正确--》" +  concatExpress );
        }
        PropertyWrapper cronExpression = new PropertyWrapper("cronExpression", concatExpress);
        PropertyWrapper jobDetail = new PropertyWrapper("jobDetail",jobDetailKey,true);
        wrappers.add(cronExpression);
        wrappers.add(jobDetail);
        String triggerKey = "flzc_job_factory_" + config.getId() + "_trigger";
        register.registerBean(triggerKey, CronTriggerFactoryBean.class, wrappers);

        wrappers.clear();

        //配置flzc_job_factory_
        List tri = new ArrayList();
        tri.add(this.context.getBean(triggerKey));

        PropertyWrapper autoStartup = new PropertyWrapper("autoStartup","false");
        PropertyWrapper configLocation = new PropertyWrapper("configLocation","classpath:properties/quartz.properties");
        PropertyWrapper triggers = new PropertyWrapper("triggers",tri);
        wrappers.add(autoStartup);
        wrappers.add(configLocation);
        wrappers.add(triggers);
        String schedulerFactoryKey = "flzc_job_factory_" + config.getId();
        register.registerBean(schedulerFactoryKey, SchedulerFactoryBean.class, wrappers);

        SchedulerFactoryBean schedulerFactoryBean = this.context.getBean("&"+schedulerFactoryKey, SchedulerFactoryBean.class);
        if(config.getStatus()==0){
            schedulerFactoryBean.start();
            rescheduleJob(schedulerFactoryKey);
            LOGGER.info(schedulerFactoryKey + "启动============================");
        }
    }
}
