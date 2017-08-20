package com.ton.ecorp.service;

import com.ton.ecorp.config.BxApiConfiguration;
import com.ton.ecorp.dao.BxDaoRest;
import com.ton.ecorp.domain.LineMsgControllerRequest;
import com.ton.ecorp.object.Alert;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class ScheduledTask {

    private static Logger logger = Logger.getLogger(ScheduledTask.class);
    @Autowired
    private BxService bxService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private BxApiConfiguration bxconfig;
    @Autowired
    private BxDaoRest bxdao;
    public static   List<Alert> alerts = new ArrayList<>();
    int i_min = 30;
    static final int five = 300000;
    static final int twenty = 1200000;
    static final int ten = 600000;
    static final int half = 1800000;
    static final int hour = 3600000;

    @Scheduled(fixedRate = five)
    public void Scheduled() throws Exception {
//        logger.info("Do Scheduled Method Scheduled ::::::::::: \n" + bxService.GetListToString(alerts) + "::::: i_min:" + i_min);
//        LocalDateTime d_now = LocalDateTime.now(ZoneId.of("Asia/Bangkok"));
//        int i_now = d_now.getHour();
//        ScheduledPrice();
//        if (i_now >= bxconfig.getRunStartTimeM() && i_now <= bxconfig.getRunEndTimeM())
//        {
//            if (i_min >= 30) {
//                ScheduledTime();
//                i_min = 0;
//            } else {
//                i_min = i_min + 5;
//            }
//        }
    }
    public void ScheduledTime() throws Exception {
        logger.info("Do Method ScheduledTime");
        String data[] = bxService.GetDataMsg();
        String least_price = data[0];
        float least_buy = Float.parseFloat(data[1]);
        float least_sell = Float.parseFloat(data[2]);
        float price = Float.parseFloat(least_price);
        LocalDateTime d_now = LocalDateTime.now(ZoneId.of("Asia/Bangkok"));
        String s_date = d_now.getDayOfMonth()+"/"+d_now.getMonthValue()+"/"+d_now.getYear() +  " เวลา " + d_now.getHour()+":"+d_now.getMinute();
        String s_msg = "ราคาล่าสุด "  +price+ " บาท\n" ;
        s_msg = s_msg + "ราคารับซื้อล่าสุด : " + least_buy+ " บาท\n" ;
        s_msg = s_msg + "ราคาตั้งขายล่าสุด : " + least_sell+ " บาท\n" ;
        s_msg = s_msg + s_date;
        SendMessage(s_msg);
    }
    private  void  ScheduledPrice() throws Exception {
        logger.info("Do Method ScheduledPrice");
        String least_price = bxService.GetRecent();
        float price = Float.parseFloat(least_price);
        for (int i=0 ; i<alerts.size() ; i++)
        {
            Alert al = alerts.get(i);
            if(al.one_time_flag)
            {
                LocalDateTime d_now = LocalDateTime.now(ZoneId.of("Asia/Bangkok"));
                String s_date = d_now.getDayOfMonth()+"/"+d_now.getMonthValue()+"/"+d_now.getYear() +  " เวลา " + d_now.getHour()+":"+d_now.getMinute();
                String s_msg = "แจ้งเตือน ราคาล่าสุด" +price+ " บาท " + "\n" + s_date;
                switch (al.getType())
                {
                    case "less":
                        if (al.getPrice() >= price)
                        {
                            SendMessage(s_msg);
                            al.setOne_time_flag(false);
                        }
                        break;
                    case "more":
                        if (al.getPrice() <= price)
                        {
                            SendMessage(s_msg);
                            al.setOne_time_flag(false);
                        }
                        break;
                }
            }
        }
    }

    public void SendMessage(String msg)
    {
        System.out.println("Send Line MSG:" + msg);
        LineMsgControllerRequest lineRequest = new LineMsgControllerRequest();
        lineRequest.setMessage(msg);
        messageService.addLineNoti(lineRequest , "ton");
//        messageService.addLineNoti(lineRequest , "ko");
    }
}
