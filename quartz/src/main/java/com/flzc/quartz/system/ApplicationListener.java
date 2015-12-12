package com.flzc.quartz.system;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.ArrayList;
import java.util.List;

/**
 * 容器启动后，设置spring容器给调度管理器
 * Created by iverson on 2015/9/19.
 */
public class ApplicationListener implements ServletContextListener {
    private final  static Logger LOGGER = LoggerFactory.getLogger(ApplicationListener.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContextEvent.getServletContext());
        SchedulerManager.getInstance().init(webApplicationContext);

       // ConfigurableApplicationContext cc = (ConfigurableApplicationContext) webApplicationContext;
        // BeanDefinitionRegistry registry = (BeanDefinitionRegistry) cc.getBeanFactory();

        try {
//            SpringBeanRegister register = new SpringBeanRegister();
//            register.setApplicationContext(webApplicationContext);
//            register.registerBean("job", "com.flzc.quartz.jobs.TestJob", null);
//
//            List<PropertyWrapper> wrappers = new ArrayList<>();
//            PropertyWrapper targetMethod = new PropertyWrapper("targetMethod","execute");
//            PropertyWrapper targetObject = new PropertyWrapper("targetObject","job",true);
//            wrappers.add(targetMethod);
//            wrappers.add(targetObject);
//            register.registerBean("myTestJobDetailBean", MethodInvokingJobDetailFactoryBean.class, wrappers);
//
//            wrappers.clear();
//            PropertyWrapper cronExpression = new PropertyWrapper("cronExpression","*/5 * * * * ?");
//            PropertyWrapper jobDetail = new PropertyWrapper("jobDetail","myTestJobDetailBean",true);
//            wrappers.add(cronExpression);
//            wrappers.add(jobDetail);
//            register.registerBean("myTestTriggerBean", CronTriggerFactoryBean.class, wrappers);
//
//            wrappers.clear();
//            List tri = new ArrayList();
//            tri.add(webApplicationContext.getBean("myTestTriggerBean"));
//
//            PropertyWrapper autoStartup = new PropertyWrapper("autoStartup","false");
//            PropertyWrapper configLocation = new PropertyWrapper("configLocation","classpath:properties/quartz.properties");
//            PropertyWrapper triggers = new PropertyWrapper("triggers",tri);
//            wrappers.add(autoStartup);
//            wrappers.add(configLocation);
//            wrappers.add(triggers);
//            register.registerBean("myTestBean", SchedulerFactoryBean.class, wrappers);
//
//            SchedulerFactoryBean myTestBean = webApplicationContext.getBean("&myTestBean", SchedulerFactoryBean.class);
//            myTestBean.start();
        } catch (Exception e) {
            LOGGER.error("scheduler init fail",e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
