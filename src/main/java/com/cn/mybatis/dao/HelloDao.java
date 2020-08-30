package com.cn.mybatis.dao;

import org.springframework.dao.DataAccessException;

/***
 * Hello Dao
 * 
 * @author yhr
 *
 */
public interface HelloDao {
	
	/**
	 *  获取当前时间
	 * 
	 * @return
	 */
	String getCurrentTime() throws DataAccessException;
	
}
