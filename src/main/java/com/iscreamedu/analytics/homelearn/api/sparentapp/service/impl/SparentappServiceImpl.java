package com.iscreamedu.analytics.homelearn.api.sparentapp.service.impl;

import com.iscreamedu.analytics.homelearn.api.common.mapper.CommonMapperSpjDbs;
import com.iscreamedu.analytics.homelearn.api.common.util.ValidationCode;
import com.iscreamedu.analytics.homelearn.api.sparentapp.service.SparentappService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class SparentappServiceImpl implements SparentappService {

    // 로그 객체
    private final Logger LOGGER = LoggerFactory.getLogger(SparentappServiceImpl.class);

    @Autowired
    private CommonMapperSpjDbs commonMapperSpjDbs;

    private LinkedHashMap<String, Object> result; //Output Object
    private String msgKey = "msg";   //메시지 object key
    private String dataKey = "data"; //데이터 object key

    @Override
    public Map getPublishedList(Map<String, Object> paramMap) throws Exception {

        List data = (ArrayList<Map<String,Object>>) commonMapperSpjDbs.getList(paramMap,"Monthly.getPublishedList");

        return null;
    }

    @Override
    public Map getWeeklyData(Map<String, Object> paramMap) throws Exception {

        Map<String,Object> data = (LinkedHashMap)commonMapperSpjDbs.get(paramMap,"test.test");

        Map<String,Object> data2 = (LinkedHashMap) commonMapperSpjDbs.get(paramMap,"test.test2");

        Map<String,Object> data3 = (LinkedHashMap) commonMapperSpjDbs.get(paramMap,"test.test3");

        data2.forEach((key,value)-> {
            if(!data.containsKey(key)){
                data.put(key,value);
            }
        });

        data3.forEach((key,value)->{
            if(!data.containsKey(key)){
                data.put(key,value);
            }
        });

        setResult(dataKey,data);
        return result;
    }


    /**
     * 서비스단에서 리턴되는 결과(메시지,데이터 object를 포함한 result)세팅.
     * @param key
     * @param data
     */
    private void setResult(String key, Object data) {
        result = new LinkedHashMap();

        if(key.equals(dataKey)) {
            LinkedHashMap message = new LinkedHashMap();
            if(data == null
                    || (data instanceof List && ((List)data).size() == 0)
                    || (data instanceof Map && ((Map)data).isEmpty())) {
                //조회결과가 없는 경우 메시지만 나감.
                message.put("resultCode", ValidationCode.NO_DATA.getCode());
                result.put(msgKey, message);
            } else {
                //정상데이터, 정상메시지
                message.put("resultCode", ValidationCode.SUCCESS.getCode());
                result.put(msgKey, message);

                result.put(dataKey, data);
            }
        } else {
            result.put(msgKey, data); //validation 걸린 메시지, 데이터 없음
        }
    }
}
