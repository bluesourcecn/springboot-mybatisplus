package com.cn.mybatis.config.rest;

/***
 * 常用状态码
 * 
 * @author yhr
 *
 */
public interface ResponseConstant {
	
	/**
	 * 返回成功状态码
	 */
	static final Integer SUCCESS = 200;
	
	/**
	 * 资源不存在
	 */
	static final Integer NOT_FOUND = 404;
	
	/**
	 * 后台报错
	 */
	static final Integer INTERNET_SERVER_ERROR = 500;
}
