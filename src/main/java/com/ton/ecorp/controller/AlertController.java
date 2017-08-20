package com.ton.ecorp.controller;

import com.ton.ecorp.service.ScheduledTask;
import com.ton.ecorp.object.Alert;
import com.ton.ecorp.service.BxService;
import com.ton.ecorp.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.apache.log4j.Logger;

@RestController
public class AlertController {

    private static Logger logger = Logger.getLogger(AlertController.class);
    @Autowired
    private BxService bxService;
    @Autowired
    private MessageService messageService;

    @RequestMapping("/api")
    public String index() {
        return bxService.GetListToString(ScheduledTask.alerts);
    }

    @RequestMapping(value = "/api" ,
            method = RequestMethod.POST)
    public  String setalert(@RequestParam(name = "price") String price , @RequestParam(name = "type") String type)
    {
        bxService.SetNewAlert( new Alert(ScheduledTask.alerts.size()+1 , Float.parseFloat(price) , type , true));
        return bxService.GetListToString(ScheduledTask.alerts);
    }

}
