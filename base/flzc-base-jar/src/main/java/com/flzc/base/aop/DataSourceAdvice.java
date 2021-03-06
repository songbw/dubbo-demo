package com.flzc.base.aop;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;

public class DataSourceAdvice implements MethodBeforeAdvice, AfterReturningAdvice, ThrowsAdvice {
	private  static  final Logger LOGGER = LoggerFactory.getLogger(DataSourceAdvice.class);
	
	// service方法执行之前被调用
	public void before(Method method, Object[] args, Object target) throws Throwable {
		System.out.println("切入点: " + target.getClass().getName() + "类中" + method.getName() + "方法");
		LOGGER.info("切入点: " + target.getClass().getName() + "类中" + method.getName() + "方法");
		if (method.getName().startsWith("add") || method.getName().startsWith("create")
				|| method.getName().startsWith("save") || method.getName().startsWith("edit")
				|| method.getName().startsWith("update") || method.getName().startsWith("delete")
				|| method.getName().startsWith("remove")) {
			System.out.println("切换到: master");
			DataSourceSwitcher.setMaster();
		} else {
			System.out.println("切换到: slave");
			DataSourceSwitcher.setSlave();
		}
	}

	// service方法执行完之后被调用
	public void afterReturning(Object arg0, Method method, Object[] args, Object target) throws Throwable {
		DataSourceSwitcher.setMaster();
	}

	// 抛出Exception之后被调用
	public void afterThrowing(Method method, Object[] args, Object target, Exception ex) throws Throwable {
		DataSourceSwitcher.setSlave();
		System.out.println("出现异常,切换到: slave");
		LOGGER.error("出现异常,切换到:",ex);
	}

}
