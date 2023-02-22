package com.iscreamedu.analytics.homelearn.api.common.config;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

public class DataSourceConfigSpjDbs {

    //dataSource 생성
    @Bean(name = "spjdbsDataSource")
    @ConfigurationProperties("spring.datasource3.hikari.spjdbs")
    public DataSource spjdbsDataSource(){
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    //sqlSessionFactory 생성
    @Bean(name = "spjdbsSessionFactory")
    public SqlSessionFactory spjdbsSessionFactory(@Qualifier("spjdbsDataSource") DataSource spjdbsDataSource, ApplicationContext applicationContext) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();

        //datsource설정
        factoryBean.setDataSource(spjdbsDataSource);
        //mapper위치
        factoryBean.setTypeAliasesPackage("");
        //mybatis설정
        factoryBean.setConfigLocation(applicationContext.getResource("classpath:/sqlmap/sql-mapper-config.xml"));
        //mapper_xml 위치
        factoryBean.setMapperLocations(applicationContext.getResources("classpath:/sqlmap/mappers/*.xml"));

        return factoryBean.getObject();

    }

    //sqlSession 생성
    @Bean(name = "spjdbsSessionTemplate")
    public SqlSessionTemplate spjdbsSessionTemplate(SqlSessionFactory spjdbsSessionFactory) throws Exception{
        return new SqlSessionTemplate(spjdbsSessionFactory);
    }
}
