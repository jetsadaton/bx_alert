package com.ton.ecorp.controller;

import com.ton.ecorp.object.Alert;
import com.ton.ecorp.service.ScheduledTask;
import com.ton.ecorp.service.BxService;
import com.ton.ecorp.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AlertTemplateController  {

    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(AlertController.class);
    @Autowired
    private BxService bxService;
    @Autowired
    private MessageService messageService;

    @RequestMapping("/index")
    public String test() {
        return "index";
    }

    @RequestMapping("/")
    public String index(Alert alert , Model model) {
        model.addAttribute("alerts", bxService.listAll());
        model.addAttribute("liscion", bxService.GetListCoin());
        return "setalert";
    }

    @RequestMapping(value = "/" ,
            method = RequestMethod.POST)
    public  String setalert(@ModelAttribute Alert alert , Model model)
    {
        bxService.SetNewAlert( new Alert(ScheduledTask.alerts.size()+1 , alert.price , alert.type , true));
        model.addAttribute("alerts", bxService.listAll());
        return "setalert";
    }

}
