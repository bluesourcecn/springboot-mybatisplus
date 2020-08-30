package com.cn.mybatis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cn.mybatis.config.rest.RestInfo;
import com.cn.mybatis.config.rest.Result;
import com.cn.mybatis.service.IHelloService;

/***
 * 用于测试的Controller
 * 
 * @author yhr
 *
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

	@Autowired
	private IHelloService helloService;

	@GetMapping("/time")
	public RestInfo<String> getCurrentTime() {
		try {
			return Result.success(helloService.getCurrentTime());
		} catch (Exception e) {
			return Result.fail("获取时间异常");
		}

	}

}
