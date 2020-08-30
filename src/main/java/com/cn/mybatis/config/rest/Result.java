package com.cn.mybatis.config.rest;

import java.util.HashMap;

/**
 * 统一返回结果封装
 * 
 * @author yhr
 *
 * @param <T> 泛型
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class Result<T> {

	public static <T> RestInfo<T> success(T data) {
		return new RestInfo<T>(ResponseConstant.SUCCESS, data, "success");
	}

	public static <T> RestInfo<T> success(T data, String msg) {
		return new RestInfo<T>(ResponseConstant.SUCCESS, data, msg);
	}

	public static <T> RestInfo<T> fail(String msg) {
		return new RestInfo<T>(ResponseConstant.INTERNET_SERVER_ERROR, (T) new HashMap(), msg);
	}

}
