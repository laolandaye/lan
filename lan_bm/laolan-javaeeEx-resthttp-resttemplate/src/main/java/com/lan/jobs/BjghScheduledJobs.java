package com.lan.jobs;

import com.lan.bjgh.DataCenterService;
import com.lan.bjgh.MonitoreSupervisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

//@Component
public class BjghScheduledJobs {

    @Autowired
    private DataCenterService dataCenterService;

    @Autowired
    private MonitoreSupervisionService monitoreSupervisionService;

    @Scheduled(fixedRate  = 1000 * 10)
    public void dataCenterScheduledJob() throws Exception {
        dataCenterService.SP_DS_ArticleList();
        System.out.println("***************************** dataCenterScheduledJob ******************************");
    }

    @Scheduled(fixedRate  = 1000 * 10)
    public void monitoreSupervisionScheduledJob() throws Exception {
    }

}
