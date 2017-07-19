package ecorp.service;

import ecorp.domain.LineMsgControllerRequest;
import ecorp.object.Alert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class ScheduledTask {

    @Autowired
    private BxService bxService;
    @Autowired
    private MessageService messageService;
    public static   List<Alert> alerts = new ArrayList<>();
    int i_min = 30;
    static final int five = 300000;
    static final int ten = 600000;
    static final int half = 1800000;
    static final int hour = 3600000;
    @Scheduled(fixedRate = five)
    public void Scheduled() throws Exception {
        System.out.println("Do Scheduled \n" + bxService.GetListToString(alerts) + "\n i_min:" + i_min);
        ScheduledPrice();
        if (i_min == 30) {
            ScheduledTime();
            i_min = 0;
        }
        else
        {
            i_min = i_min + 5;
        }
    }
    public void ScheduledTime() throws Exception {
        String least_price = bxService.GetRecent();
        float price = Float.parseFloat(least_price);
        LocalDateTime d_now = LocalDateTime.now(ZoneId.of("Asia/Bangkok"));
        String s_date = d_now.getDayOfMonth()+"/"+d_now.getMonthValue()+"/"+d_now.getYear() +  "\nTIME: " + d_now.getHour()+":"+d_now.getMinute();
        String s_msg = "ราคาชื้อขายล่าสุด " +price+ " บาท " + "\n DATE: " + s_date ;
        SendMessage(s_msg);
    }
    private  void  ScheduledPrice() throws Exception {
        String least_price = bxService.GetRecent();
        float price = Float.parseFloat(least_price);
        for (int i=0 ; i<alerts.size() ; i++)
        {
            Alert al = alerts.get(i);
            if(al.one_time_flag)
            {
                LocalDateTime d_now = LocalDateTime.now(ZoneId.of("Asia/Bangkok"));
                String s_date = d_now.getDayOfMonth()+"/"+d_now.getMonthValue()+"/"+d_now.getYear() +  "\nTIME: " + d_now.getHour()+":"+d_now.getMinute();
                String s_msg = "แจ้งเตือน ราคาล่าสุด" +price+ " บาท " + "\n DATE: " + s_date;
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
        messageService.addLineNoti(lineRequest , "ko");
    }
}
