package com.abc.helloworld.config;

public class CommonConfig {
	static final String DB_PARAMETERMAP_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_PARAMETERMAP_URL = "jdbc:mysql://localhost:3306/DB";
	static final String DB_PARAMETERMAP_USER_NAME = "mysqlDB";
	static final String DB_PARAMETERMAP_PASSWORD = "123456";
	
	static final String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN = "com.abc.helloworld.model";
	static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
	static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
}
