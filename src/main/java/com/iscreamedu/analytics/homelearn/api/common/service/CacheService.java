package com.iscreamedu.analytics.homelearn.api.common.service;

import com.iscreamedu.analytics.homelearn.api.common.mapper.CommonMapperSpjDbs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("cacheService")
public class CacheService {

    @Autowired
    private EhCacheCacheManager ehCache;

    @Autowired
    CommonMapperSpjDbs commonMapper;

    private HttpSession session;
    private String CACHE_NAME = "commonCache"; //캐시 이름

    /**
     * 메시지 목록 가져오기
     * @param examSeq
     * @return
     * @throws Exception
     */
    public HashMap getMessageList(String lang) throws Exception {
        // Cache Name을 가지고 cache 찾기 - ehcache.xml 참조
        Cache cc = ehCache.getCache(CACHE_NAME);

        /* 학년에 따라 key 값 변경 로직 추가 (2020.05.28 PX-99 학년별 과목명 매핑 작업 - 오희택) START */
        int grade = gradeCheck();

        String key = null;

        if(grade < 1) {
            key = "MESSAGE_"+lang+"_0";
        }else {
            key = "MESSAGE_"+lang;
        }
        /* 학년에 따라 key 값 변경 로직 추가 (2020.05.28 PX-99 학년별 과목명 매핑 작업 - 오희택) END */

        //찾는 캐시 데이터가 없으면 DB에서 조회
        if(cc.get(key) == null){
            Map param = new HashMap();
            param.put("lang", lang);

            /* 학년에 따라 param > studGrade 값 변경 로직 추가 (2020.05.28 PX-99 학년별 과목명 매핑 작업 - 오희택) START */
            if( grade < 1) {
                param.put("studGrade", "0");
            }else {
                param.put("studGrade", "1");
            }
            /* 학년에 따라 param > studGrade 값 변경 로직 추가 (2020.05.28 PX-99 학년별 과목명 매핑 작업 - 오희택) END */

            List list = commonMapper.getList(param, "Common.selectCommMsgList");

            int size = list.size();
            Map msgMap = new HashMap();
            for(int i=0; i<size; i++) {
                Map data = (HashMap)list.get(i);
                msgMap.put( data.get("msgCd"), data.get("msg") );
            }

            cc.put(key, msgMap);
        }

        Cache.ValueWrapper obj = cc.get(key);

        return (HashMap)obj.get();
    }

    public String getMessage(String lang, String key) throws Exception {
        HashMap msgMap = this.getMessageList(lang);
        String msg = "";

        if(msgMap.containsKey(key)){
            msg = (String)msgMap.get(key);
        } else {
            msg = key;
        }

        return msg;
    }

    public void removeCache(String key) throws Exception {
        Cache cc = ehCache.getCache(CACHE_NAME);
        cc.evict(key);
    }

    public void clearCache() throws Exception {
        Cache cc = ehCache.getCache(CACHE_NAME);
        cc.clear();
    }

    /* session에 저장된 학년 정보 조회 및 반환 로직 추가 (2020.05.28 PX-99 학년별 과목명 매핑 작업 - 오희택) START */
    public int gradeCheck() throws Exception{
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        this.session = request.getSession();

        String gradeCheck = null;
        int studGrade;
        int grade;

        if( session.getAttribute("STUD_INFO") == null ) {
            studGrade = 1;
        }else {
            Map<String, Object> studInfo = new HashMap();

            studInfo = (Map<String, Object>) session.getAttribute("STUD_INFO");

            gradeCheck  = studInfo.get("grade").toString();
            grade = Integer.parseInt(gradeCheck);

            if( grade < 1) {
                studGrade = 0;
            }else {
                studGrade = 1;
            }
        }
        return studGrade;
    }
    /* session에 저장된 학년 정보 조회 및 반환 로직 추가 (2020.05.28 PX-99 학년별 과목명 매핑 작업 - 오희택) END */
}

