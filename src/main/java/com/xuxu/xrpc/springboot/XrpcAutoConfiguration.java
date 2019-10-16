package com.xuxu.xrpc.springboot;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.xuxu.rpc.xrpc.configuration.XrpcConfiguration;
import com.xuxu.rpc.xrpc.configuration.XrpcProperties;
import com.xuxu.xrpc.springboot.processor.XrpcClientBeanPostProcessor;
import com.xuxu.xrpc.springboot.processor.XrpcServerBeanPostProcessor;

@Configuration
public class XrpcAutoConfiguration {
	
	
	@Bean
	@ConditionalOnMissingBean
	public XrpcConfiguration createXrpcConfiguration(XrpcProperties xrpcProperties) {
		XrpcConfiguration con=new XrpcConfiguration(xrpcProperties);
		con.initialize();
		return new XrpcConfiguration(xrpcProperties);		
	}
	
	@Bean
	@ConditionalOnMissingBean
	public XrpcProperties getXrpcProperties() {
		return new XrpcProperties();
	}
	
	@Bean
	@ConditionalOnBean(XrpcConfiguration.class)
	public XrpcServerBeanPostProcessor createXrpcServerBeanPostProcessor(XrpcConfiguration xrpcConfiguration) {
		return new XrpcServerBeanPostProcessor(xrpcConfiguration);
	}
	
	@Bean
	@ConditionalOnBean(XrpcConfiguration.class)
	public XrpcClientBeanPostProcessor createXrpcClientBeanPostProcessor(XrpcConfiguration xrpcConfiguration) {
		return new XrpcClientBeanPostProcessor(xrpcConfiguration);
	}
	
}
