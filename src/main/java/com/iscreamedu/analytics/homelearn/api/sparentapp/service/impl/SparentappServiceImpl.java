package com.iscreamedu.analytics.homelearn.api.sparentapp.service.impl;

import com.iscreamedu.analytics.homelearn.api.common.mapper.CommonMapperSpjDbs;
import com.iscreamedu.analytics.homelearn.api.sparentapp.service.SparentappService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SparentappServiceImpl implements SparentappService {

    @Autowired
    private CommonMapperSpjDbs commonMapperSpjDbs;

    @Override
    public Map getPublishedList(Map<String, Object> paramMap) throws Exception {

        List data = (ArrayList<Map<String,Object>>)commonMapperSpjDbs.getList(paramMap,"Monthly.getPublishedList");

        return null;
    }
}
