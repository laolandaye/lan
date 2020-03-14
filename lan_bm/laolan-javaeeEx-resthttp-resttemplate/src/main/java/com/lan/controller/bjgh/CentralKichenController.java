package com.lan.controller.bjgh;

import com.lan.bjgh.CentralKitchenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/centralKichen")
public class CentralKichenController {

    @Autowired
    private CentralKitchenService centralKitchenService;

    @GetMapping("/SP_CK_ScriptTotalCount")
    public void SP_CK_ScriptTotalCount() throws Exception {
        for (int i = 0; i < 100; i++) {
            centralKitchenService.SP_CK_ScriptTotalCountThread(i);
        }
    }

}
