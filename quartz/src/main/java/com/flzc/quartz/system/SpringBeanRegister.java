package com.flzc.quartz.system;

import com.flzc.quartz.util.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Collections;
import java.util.List;

/**
 * 项目启动后，可以通过此类向spring容器里注入bean
 * Created by iverson on 2015/9/25.
 */
public class SpringBeanRegister implements ApplicationContextAware {

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(SpringBeanRegister.class);

    private ApplicationContext applicationContext;

    /**
     * Set the ApplicationContext that this object runs in.
     * Normally this call will be used to initialize the object.
     * <p>Invoked after population of normal bean properties but before an init callback such
     * as {@link InitializingBean#afterPropertiesSet()}
     * or a custom init-method. Invoked after {@link ResourceLoaderAware#setResourceLoader},
     * {@link ApplicationEventPublisherAware#setApplicationEventPublisher} and
     * {@link MessageSourceAware}, if applicable.
     *
     * @param applicationContext the ApplicationContext object to be used by this object
     * @throws ApplicationContextException in case of context initialization errors
     * @throws BeansException              if thrown by application context methods
     * @see BeanInitializationException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * 向spring容器里注入bean
     *
     * @param clazz      要注入的类型
     * @param properties 对象要设置的属性. key是属性名，value是对应的值
     * @param <T>
     */
    public <T> void registerBean(String beanId, Class<T> clazz, List<PropertyWrapper> properties) throws Exception {

        if (StringUtils.isBlank(beanId)) {
            throw new Exception("beanId不能为空");
        }
        if (clazz == null) {
            throw new Exception("要注入的类class信息不能为空");
        }
        if (properties == null || properties.isEmpty()) {
            LOGGER.warn("要注入的对象，没有初始化值");
        }
        BeanDefinitionBuilder beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(clazz);

        if(properties == null) properties = Collections.EMPTY_LIST;

        for (PropertyWrapper p : properties) {
            String fieldName = p.getFieldName();
            Object fieldValue = p.getFieldValue();
            boolean refType = p.isRefType();
            if (refType) {
                if( ! (fieldValue instanceof  String))
                    throw  new IllegalArgumentException(fieldValue.toString()+ ": 不是有效的spring bean对象id");
                beanDefinition.addPropertyReference(fieldName, fieldValue.toString());
            } else {
                beanDefinition.addPropertyValue(fieldName, fieldValue);
            }

        }
        getRegister().registerBeanDefinition(beanId, beanDefinition.getBeanDefinition());
        LOGGER.info(beanId + "注入sping容器完成");
    }

    /**
     *
     * @param beanId 对象在spring中的id
     * @param clazz 类全名
     * @param properties 要注入的对象的属性信息
     * @throws Exception
     */
    public void registerBean(String beanId, String clazz, List<PropertyWrapper> properties) throws Exception {
        if (StringUtils.isBlank(clazz)) {
            throw new NullPointerException("类名不能为空");
        }
        Class<?> clz = Class.forName(clazz);
        this.registerBean(beanId, clz, properties);

    }

    private BeanDefinitionRegistry getRegister() throws Exception {
        try {
            ConfigurableApplicationContext cc = (ConfigurableApplicationContext) this.applicationContext;
            BeanDefinitionRegistry registry = (BeanDefinitionRegistry) cc.getBeanFactory();
            return registry;
        } catch (IllegalStateException e) {
            LOGGER.error("获取spring bean注册器失败", e);
            throw e;
        }
    }

    public Object getBean(String beanId) {
        return this.applicationContext.getBean(beanId);
    }

    public <T> T getBean(String beanId, Class<T> clazz) {
        return this.applicationContext.getBean(beanId, clazz);
    }
}
