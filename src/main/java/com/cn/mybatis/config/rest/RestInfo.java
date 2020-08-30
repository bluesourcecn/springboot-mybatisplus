package com.cn.mybatis.config.rest;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestInfo<T> implements Serializable {
	private static final long serialVersionUID = -5832738461827079805L;

	/**
	 * 返回码
	 */
	private Integer code;
	
	/**
	 * 返回数据
	 */
	private T data ;
	
	/**
	 * 返回描述
	 */
	private String msg;
}
