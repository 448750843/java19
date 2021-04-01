package com.turing.sm.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 配置项
 * @author 大飞哥
 *
 */

@Configuration //配置项
@ComponentScan(basePackages="com.turing.sm") //扫描组件
@MapperScan(basePackages="com.turing.sm.mapper") //扫描Mapper
//@ImportResource("classpath:spring-transaction.xml")//文件的加载方式：classpath:类路径（默认）；file：文件路径
@EnableTransactionManagement
public class SpringConfig {

	/**
	 * DBCP2数据源
	 * @return
	 */
	@Bean
	public DataSource dataSource(){
		BasicDataSource ds = new  BasicDataSource();
	       ds.setDriverClassName("com.mysql.jdbc.Driver");
	       ds.setUrl("jdbc:mysql:///t1");
	       ds.setUsername("root");
	       ds.setPassword("root");
	       
	       ds.setInitialSize(5);
	       ds.setMaxTotal(15);
	       ds.setMaxIdle(8);
	       ds.setMinIdle(3);
	       ds.setMaxWaitMillis(10000);
	       ds.setValidationQuery("select 1");
	       
	       return ds;
	}
	
	//配置sqlSessionFactory，其实就等同于之前MyBatis配置的mybatis-config.xml
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception{
		//创建SqlSessionFactoryBean对象
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		//设置数据源
		bean.setDataSource(dataSource);
		//返回SqlSessionFactory对象
		return bean.getObject();
	}
	
	//使用MyBatis的模板---SqlSessionTemplate
	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory){
		return new SqlSessionTemplate(sqlSessionFactory);
	}
	
	//配置事务管理器
	@Bean
	public DataSourceTransactionManager transactionManager(DataSource dataSource){
		return new DataSourceTransactionManager(dataSource);
	}
	
	
}
