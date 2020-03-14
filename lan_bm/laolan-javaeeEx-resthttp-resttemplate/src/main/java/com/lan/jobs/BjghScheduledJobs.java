package com.lan.jobs;

import com.lan.bjgh.DataCenterService;
import com.lan.bjgh.MonitoreSupervisionService;
import com.lan.bjsc.BjfcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class BjghScheduledJobs {

    //    public String openapiUrl = "http://47.95.182.97:8081/openapi/service/";
    private String openapiUrl = "http://localhost:8080/dam/service/";
    private String appKey = "402897816f846da9016f8480b57c0001";
    private String appSecret = "AnWsvq2xTN7fJ6H";

    @Autowired
    private BjfcService bjfcService;

    @Autowired
    private DataCenterService dataCenterService;

    @Autowired
    private MonitoreSupervisionService monitoreSupervisionService;

    @Scheduled(fixedRate  = 1000 * 10)
    public void dataCenterScheduledJob() throws Exception {
    }

}
