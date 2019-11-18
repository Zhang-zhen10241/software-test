package com.xes.qa.test.dbutils;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DbUtils {
//	private static Logger logger = Logger.getLogger(DbUtils.class);  
	
//	 static String dbConfigXml = "mybatis/mysqlConfiguration.xml";
//	 
//	    public static SqlSession getSession() {
//	    	try{
//		    	Reader reader    = Resources.getResourceAsReader(dbConfigXml);
//		    	SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
//		        SqlSession session = sqlSessionFactory.openSession();
//		        return session;
//		    	}catch(IOException e){
//		    		String errorMessage="Cannot find db config file，like：mysqlConfiguration.xml";
//		    		System.err.println(errorMessage);
////		    		logger.error(errorMessage, e.getCause());
//		    		return null;
//		    	}
//	    }
	 
}
