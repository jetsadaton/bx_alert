package com.ton.ecorp.controller;

import com.ton.ecorp.Entity.AlertRepository;
import com.ton.ecorp.Entity.Alert;
import com.ton.ecorp.service.BxService;
import com.ton.ecorp.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.apache.log4j.Logger;

@RestController
@RequestMapping(path="/api")
public class AlertController {

    private static Logger logger = Logger.getLogger(AlertController.class);
    @Autowired
    private BxService bxService;
    @Autowired
    private MessageService messageService;

//    @RequestMapping("/")
//    public String index() {
//        return bxService.GetListToString(ScheduledTask.alerts);
//    }
//
//    @RequestMapping(value = "/" ,
//            method = RequestMethod.POST)
//    public  String setalert(@RequestParam(name = "price") String price , @RequestParam(name = "paring_id") String paring_id,@RequestParam(name = "type") String type)
//    {
//        bxService.SetNewAlert( new ObjAlert(ScheduledTask.alerts.size()+1 , Float.parseFloat(price) ,type , Integer.parseInt(paring_id),true));
//        return bxService.GetListToString(ScheduledTask.alerts);
//    }

    @Autowired
    private AlertRepository alertRepository;


    @RequestMapping(path = "/addalert" ,
            method = RequestMethod.POST)
    public @ResponseBody
    String addNewUser(@RequestParam String coin
            , @RequestParam double price
            , @RequestParam String type
            ) {
        Alert alert = new Alert();
        alert.setCoin_type(coin);
        alert.setPrice(price);
        alert.setType(type);
        alertRepository.save(alert);
        return "Saved";
    }

    @RequestMapping(path = "/getalertall" ,
            method = RequestMethod.POST)
    public @ResponseBody
    Iterable<Alert> getAllAlert() {
        return alertRepository.findAll();
    }

}
