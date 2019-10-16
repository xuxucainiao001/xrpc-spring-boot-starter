package com.xuxu.xrpc.springboot.processor;

import com.xuxu.rpc.xrpc.annotations.XrpcProvider;
import com.xuxu.rpc.xrpc.configuration.XrpcConfiguration;
import com.xuxu.rpc.xrpc.proxy.ServerBeanProxy;

public class XrpcServerBeanPostProcessor extends XrpcBeanPostProcessor {
	

	public XrpcServerBeanPostProcessor(XrpcConfiguration xrpcConfiguration) {
		super(xrpcConfiguration);
	}

	/**
	  * 服务客户端创建代理
	 */
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName){
		if (!xrpcConfiguration.getXrpcProperties().isOpenServer()) {
			return bean;
		}		
		Class<?> clazz = bean.getClass();
		if(clazz.isAnnotationPresent(XrpcProvider.class)) {
			ServerBeanProxy serverBeanProxy=xrpcConfiguration.getBeanProxyFactory().getServerBeanProxy();
			serverBeanProxy.createXrpcServerProxy(bean, clazz.getAnnotation(XrpcProvider.class));
		}
		return bean;
	}
}
