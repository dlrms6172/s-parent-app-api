package com.iscreamedu.analytics.homelearn.api.sparentapp.controller;

import com.iscreamedu.analytics.homelearn.api.sparentapp.service.SparentappService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.Map;


@RequestMapping("/sparent")
@RestController
public class SparentappController {

    private final Logger LOGGER = LoggerFactory.getLogger(SparentappController.class);

    @Autowired
    private SparentappService sparentappService;

    public HttpHeaders headers;
    public LinkedHashMap body;

    public SparentappController() {
        headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json"));
        headers.setAccessControlAllowOrigin("*");
        headers.setAccessControlAllowCredentials(true);
    }

    /**
     * 주간데이터
     * @param params
     * @param req
     * @param res
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/getWeeklyData")
    public ResponseEntity getWeeklyData(@RequestParam Map<String, Object> params, HttpServletRequest req, HttpServletResponse res) throws Exception {
        body = (LinkedHashMap)sparentappService.getWeeklyData(params);
        return new ResponseEntity(body, headers, HttpStatus.OK);
    }

}
