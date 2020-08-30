package com.cn.mybatis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.mybatis.dao.HelloDao;
import com.cn.mybatis.service.IHelloService;

import lombok.extern.slf4j.Slf4j;

/***
 * 测试service接口实现类
 * 
 * @author yhr
 *
 */
@Service
@Slf4j
public class HelloServiceImpl implements IHelloService {

	@Autowired
	private HelloDao helloDao;

	@Override
	public String getCurrentTime() throws Exception {
		String currentTime = null;
		
		try {
			 currentTime = helloDao.getCurrentTime();
		} catch (Exception ex) {
			log.error("获取当前时间异常:\n" + ex);
			throw new Exception(ex);
		}
		
		return currentTime;
	}

}
