package com.iscreamedu.analytics.homelearn.api.sparentapp.service;

import java.util.Map;

public interface SparentappService {

    public Map getPublishedList(Map<String,Object> paramMap) throws Exception;

    public Map getWeeklyData(Map<String,Object> paramMap) throws Exception;
}
