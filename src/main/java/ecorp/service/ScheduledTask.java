package ecorp.service;

import ecorp.domain.LineMsgControllerRequest;
import ecorp.object.Alert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Component
public class ScheduledTask {

    @Autowired
    private BxService bxService;
    @Autowired
    private MessageService messageService;
      public static   List<Alert> alerts = new ArrayList<>();

    @Scheduled(fixedRate = 600000)
    public void Scheduled() throws Exception {
        ScheduledTime();
    }

    private  void ScheduledTime() throws Exception {
        String least_price = bxService.GetRecent();
        float price = Float.parseFloat(least_price);
        System.out.println("DO Scheduled Price :" + price + " , Time :" + LocalDateTime.now(ZoneId.of("Asia/Bangkok")));
        SendMessage(price);
    }
    private  void  ScheduledPrice() throws Exception {
        String least_price = bxService.GetRecent();
        float price = Float.parseFloat(least_price);
        System.out.println("DO Scheduled Price :" + price + " , Time :" + LocalDateTime.now(ZoneId.of("Asia/Bangkok")));
        for (int i=0 ; i<alerts.size() ; i++)
        {
            Alert al = alerts.get(i);
            if(al.one_time_flag)
            {
                switch (al.getType())
                {
                    case "less":
                        if (al.getPrice() >= price)
                        {
                            SendMessage(price);
                            al.setOne_time_flag(false);
                        }
                        break;
                    case "more":
                        if (al.getPrice() <= price)
                        {
                            SendMessage(price);
                            al.setOne_time_flag(false);
                        }
                        break;
                }
            }
        }
    }

    public void SendMessage(float price)
    {
          String s_msg = "ราคาชื้อขายล่าสุด " +price+ " บาท" + "ณ. เวลา: " +  LocalDateTime.now(ZoneId.of("Asia/Bangkok"));
          System.out.println("Send Line At Price :" + price);
          LineMsgControllerRequest lineRequest = new LineMsgControllerRequest();
          lineRequest.setMessage(s_msg);
          messageService.addLineNoti(lineRequest , "ton");
          //messageService.addLineNoti(lineRequest , "ko");
    }
}
