package com.iscreamedu.analytics.homelearn.api.common.mapper;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Repository
public class CommonMapperSpjDbs extends SqlSessionDaoSupport {

    @Resource
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    /**
     * 단건 조회
     * @param param
     * @param sqlId
     * @return
     */
    public Object get(Map<String,Object> param, String sqlId){
        return getSqlSession().selectOne(sqlId,param);
    }

    /**
     * 목록 조회
     * @param param
     * @param sqlId
     * @return
     */
    public List getList(Map<String,Object> param,String sqlId){
        return getSqlSession().selectList(sqlId,param);
    }

}
