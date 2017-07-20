package ecorp.service;

import ecorp.config.BxApiConfiguration;
import ecorp.dao.BxDaoRest;
import ecorp.domain.LineMsgControllerRequest;
import ecorp.object.Alert;
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
        logger.info("Do Scheduled Method Scheduled ::::::::::: \n" + bxService.GetListToString(alerts) + "::::: i_min:" + i_min);
        LocalDateTime d_now = LocalDateTime.now(ZoneId.of("Asia/Bangkok"));
        int i_now = d_now.getHour();
        ScheduledPrice();
        if (!(bxconfig.geHerokuMode().equals("n") && i_now >= 0  && i_now <= 7))
        {
            if (i_min >= 30) {
                ScheduledTime();
                i_min = 0;
            } else {
                i_min = i_min + 5;
            }
        }
    }
    @Scheduled(fixedRate = twenty)
    public void HerokuNonSleep() throws Exception {
        LocalDateTime d_now = LocalDateTime.now(ZoneId.of("Asia/Bangkok"));
        int i_now = d_now.getHour();
        int i_start = bxconfig.getRunStartTime();
        int i_end = bxconfig.getRunEndTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy HH:mm");
        Date dd_now = sdf.parse("01/01/1990 "+i_now+":"+d_now.getMinute());
        Date dd_start = sdf.parse("01/01/1990 "+i_start+":"+"00");
        Date dd_end = sdf.parse("01/01/1990 "+i_end+":"+"30");
        if (dd_now.after(dd_start) && dd_now.before(dd_end))
        {
            bxdao.GetBodyHtml(bxconfig.getHerokuUrl());
            logger.info("Do Scheduled Method HerokuNonSleep ::::::: " + bxconfig.getHerokuUrl());
        }else
        {
            bxdao.GetBodyHtml(bxconfig.getHerokuUrlRevert());
            logger.info("Do Scheduled Method HerokuNonSleep ::::::: " + bxconfig.getHerokuUrlRevert());
        }
    }

    private String GetBIP91Blocks()
    {   String s_bNeeded;
        String s_body = bxdao.GetBodyHtml("https://coin.dance/blocks");
        int index  = s_body.indexOf("Current Count:</span>");
        String current_blocks = s_body.substring(index+21 , index+21+16 );
        return current_blocks;
    }
    public void ScheduledTime() throws Exception {
        logger.info("Do Method ScheduledTime");
        String least_price = bxService.GetRecent();
        float price = Float.parseFloat(least_price);
        LocalDateTime d_now = LocalDateTime.now(ZoneId.of("Asia/Bangkok"));
        String s_date = d_now.getDayOfMonth()+"/"+d_now.getMonthValue()+"/"+d_now.getYear() +  " " + d_now.getHour()+":"+d_now.getMinute();
        String s_current_block =  GetBIP91Blocks();
        String s_msg = "ราคาชื้อขายล่าสุด " +price+ " บาท " + "\n" + s_date + "\n BIP 91: "+s_current_block ;
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
                String s_date = d_now.getDayOfMonth()+"/"+d_now.getMonthValue()+"/"+d_now.getYear() +  " " + d_now.getHour()+":"+d_now.getMinute();
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
        lineRequest.setMessage(msg + "\n Send From:" + bxconfig.getHerukuRes());
        messageService.addLineNoti(lineRequest , "ton");
//        messageService.addLineNoti(lineRequest , "ko");
    }
}
