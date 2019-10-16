package com.xuxu.xrpc.springboot.processor;

import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xuxu.rpc.xrpc.annotations.XrpcConsumer;
import com.xuxu.rpc.xrpc.configuration.XrpcConfiguration;
import com.xuxu.rpc.xrpc.proxy.ClientBeanProxy;

public class XrpcClientBeanPostProcessor extends XrpcBeanPostProcessor {

	private Logger logger = LoggerFactory.getLogger(XrpcClientBeanPostProcessor.class);

	public XrpcClientBeanPostProcessor(XrpcConfiguration xrpcConfiguration) {
		super(xrpcConfiguration);
	}

	/**
	 * 客户端创建代理
	 */
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName){
		if (!xrpcConfiguration.getXrpcProperties().isOpenClient()) {
			return bean;
		}
		Class<?> clazz = bean.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			if (field.isAnnotationPresent(XrpcConsumer.class)) {
				Class<?> intf = field.getType();
				try {
					ClientBeanProxy clientBeanProxy = xrpcConfiguration.getBeanProxyFactory().getClientBeanProxy();
					Object clientProxy = clientBeanProxy.createXrpcClientProxy(intf,
							field.getAnnotation(XrpcConsumer.class));
					field.setAccessible(true);
					field.set(bean, clientProxy);
				} catch (Exception e) {
					logger.error("xrpc client 注册失败：{}", intf);
				}
			}
		}
		return bean;
	}
}
