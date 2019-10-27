package com.lan.jobs;

import com.lan.bjgh.ChangpingService;
import com.lan.bjgh.MonitoreSupervisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class BjghScheduledJobs {

    @Autowired
    private MonitoreSupervisionService monitoreSupervisionService;

    @Autowired
    private ChangpingService changpingService;

    @Scheduled(fixedRate  = 1000 * 10)
    public void monitoreSupervisionScheduledJob() throws Exception {
        monitoreSupervisionService.SP_MS_GetBaseAccountList();
        monitoreSupervisionService.SP_MS_GetSpiderResultList();
        monitoreSupervisionService.SP_MS_GetSensitiveProgramList();
        System.out.println("***************************** monitoreSupervisionScheduledJob ******************************");
    }

    @Scheduled(fixedRate  = 1000 * 10)
    public void changpingScheduledJob() throws Exception {
        changpingService.SP_CK_AddPush();
        System.out.println("***************************** changpingScheduledJob ******************************");
    }

}
