package com.xuxu.xrpc.springboot.processor;

import org.springframework.beans.factory.config.BeanPostProcessor;

import com.xuxu.rpc.xrpc.configuration.XrpcConfiguration;

public abstract class XrpcBeanPostProcessor implements BeanPostProcessor{
	
	
	protected XrpcConfiguration xrpcConfiguration;
	
	
	public XrpcBeanPostProcessor(XrpcConfiguration xrpcConfiguration) {		
		this.xrpcConfiguration=xrpcConfiguration;		
	}
	
	
}
