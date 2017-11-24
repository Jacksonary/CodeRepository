package com.hhu.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 创建获取SpringBean的工具类
 * @author Weiguo Liu
 *
 */
public class SpringBeanUtil implements ApplicationContextAware {
	private static ApplicationContext applicationContext = null;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringBeanUtil.applicationContext = applicationContext;
	}

	public static Object getBeanByName(String beanName) {
		if (applicationContext == null) {
			return null;
		}
		return applicationContext.getBean(beanName);
	}

	public static <T> T getBean(Class<T> type) {
		return applicationContext.getBean(type);
	}
}
