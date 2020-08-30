package com.cn.mybatis.config;

import java.util.Properties;

import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.mapping.VendorDatabaseIdProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/***
 * Mybatis数据库兼容
 * 
 * @author yhr
 *
 */
@Configuration
public class DatabaseConfig {

	@Bean
	public DatabaseIdProvider getDatabaseIdProvider(){
	    DatabaseIdProvider databaseIdProvider = new VendorDatabaseIdProvider();
	    Properties properties = new Properties();
	    properties.setProperty("Oracle","oracle");
	    properties.setProperty("MySQL","mysql");
	    properties.setProperty("DB2","db2");
	    properties.setProperty("Derby","derby");
	    properties.setProperty("H2","h2");
	    properties.setProperty("HSQL","hsql");
	    properties.setProperty("Informix","informix");
	    properties.setProperty("Microsoft SQL Server","sqlserver");
	    properties.setProperty("PostgreSQL","postgresql");
	    properties.setProperty("Sybase","sybase");
	    properties.setProperty("SQLite","sqlite");
	    databaseIdProvider.setProperties(properties);
	    return databaseIdProvider;
	}
	
}
