package com.ton.ecorp.service;

import com.ton.ecorp.Entity.AlertRepository;
import com.ton.ecorp.Entity.UserRepository;
import com.ton.ecorp.Entity.Users;
import com.ton.ecorp.config.BxApiConfiguration;
import com.ton.ecorp.dao.BxDaoRest;
import com.ton.ecorp.domain.LineMsgControllerRequest;
import com.ton.ecorp.Entity.Alert;
import com.ton.ecorp.object.BxCion;
import com.ton.ecorp.object.ObjAlert;
import com.ton.ecorp.object.ObjUsers;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Component
@Transactional
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
    public static   List<ObjAlert> alerts = new ArrayList<>();
    public  static List<ObjUsers> users = new ArrayList<>();
    int i_min = 30;
    static final int five = 300000;
    static final int twenty = 1200000;
    static final int ten = 600000;
    static final int half = 1800000;
    static final int hour = 3600000;
    static final int one = 60000;
    @Scheduled(fixedRate = one)
    public void Scheduled() throws Exception {
        logger.info("Do Scheduled Method Scheduled");
        LocalDateTime d_now = LocalDateTime.now(ZoneId.of("Asia/Bangkok"));
        int i_now = d_now.getHour();
        int i_min = d_now.getMinute();
        //test();
        if (i_min%10 == 0 && i_min != 0 && i_min != 30)
        {
            //ScheduledPrice();
        }
        if (i_now >= bxconfig.getRunStartTimeM() && i_now <= bxconfig.getRunEndTimeM())
        {
           // if ( i_min == 0 || i_min == 30)
                //ScheduledTime();
        }
    }

//    @Autowired
//    private AlertRepository alertRepository;
//    @Autowired
//    private UserRepository userRepository;

//    private  void test(){
//        System.out.println("XXXXXXXXXXXXXXXXXXX");
////        Users u1 = new Users("jetsada2" , "1234" , "555");
//        Alert a1 = new Alert(900 , "less" , "1");
//        Alert a2 = new Alert(90000 , "less" , "26");
////        u1.setAlerts(new HashSet<Alert>(){{
////            add(a1);
////            add(a2);
////        }});
////        userRepository.save(new HashSet<Users>(){{
////            add(u1);
////        }});
//
//        userRepository.save(new HashSet<Users>(){
//            {
//                add(new Users("jetsada5", "1234", "555", new HashSet<Alert>() {{
//                    add(a1);
//                    add(a2);
//                }}));
//            }});
//
//        userRepository.save(new HashSet<Users>(){
//            {
//                add(new Users("jetsadaton", "1234", "555"));
//            }});
//        Users u1 = userRepository.findOne("jetsadaton");
//        u1.setAlerts( new HashSet<Alert>() {{
//            add(a1);
//        }});
//        userRepository.save(new HashSet<Users>(){
//            {
//                add(u1);
//            }});
//        System.out.println("COUNT : " + userRepository.count());
//        System.out.println("COUNT : " + alertRepository.count());
//
//    }
    public void ScheduledTime() throws Exception {
        logger.info("Do Method ScheduledTime");
        BxCion[] bxcoins = bxService.GetDataCoin();
        String s_msg = "";
        for (int h=0;h<users.size();h++) {
            s_msg = "";
            String[] pattern = users.get(h).getIs_pattern().split(",");
            for (int i = 0; i < pattern.length; i++) {
                s_msg += bxcoins[Integer.parseInt(pattern[i])].toString();
            }
            LocalDateTime d_now = LocalDateTime.now(ZoneId.of("Asia/Bangkok"));
            String s_date = d_now.getDayOfMonth() + "/" + d_now.getMonthValue() + "/" + d_now.getYear() + " เวลา " + d_now.getHour() + ":" + d_now.getMinute();
            s_msg += s_date;
            SendMessage(s_msg , users.get(h).getIs_user_name());
        }
    }
//    private  void  ScheduledPrice() throws Exception {
//        logger.info("Do Method ScheduledPrice");
//        BxCion[] bxcoins = bxService.GetDataCoin();
//        bxService.listAll();
//        NumberFormat formatter = NumberFormat.getInstance(new Locale("en_US"));
//        for (int i=0 ; i<alerts.size() ; i++)
//        {
//            ObjAlert al = alerts.get(i);
//            if(al.one_time_flag)
//            {
//                Double price = bxcoins[ al.paring_id ].getLeast_buy();
//                LocalDateTime d_now = LocalDateTime.now(ZoneId.of("Asia/Bangkok"));
//                String s_date = d_now.getDayOfMonth()+"/"+d_now.getMonthValue()+"/"+d_now.getYear() +  " เวลา " + d_now.getHour()+":"+d_now.getMinute();
//                String s_msg =  "แจ้งเตือน\n" + bxcoins[ al.paring_id ].getCoinName() + " : ";
//                s_msg += " ราคาล่าสุด" + formatter.format(price)+ " บาท " + "\n" + s_date;
//                switch (al.getType())
//                {
//                    case "less":
//                        if (al.getPrice() >= price)
//                        {
//                            SendMessage(s_msg);
//                            al.setOne_time_flag(false);
//                        }
//                        break;
//                    case "more":
//                        if (al.getPrice() <= price)
//                        {
//                            SendMessage(s_msg);
//                            al.setOne_time_flag(false);
//                        }
//                        break;
//                }
//            }
//        }
//    }

    public void SendMessage(String msg)
    {
        System.out.println("Send Line MSG:" + msg);
        LineMsgControllerRequest lineRequest = new LineMsgControllerRequest();
        lineRequest.setMessage(msg);
//        messageService.addLineNoti(lineRequest , "group");
        messageService.addLineNoti(lineRequest , "ton");
        messageService.addLineNoti(lineRequest , "ben");
//        messageService.addLineNoti(lineRequest , "ko");
    }

    public void SendMessage(String msg , String to_who) {
        System.out.println("Send Line MSG:" + msg);
        LineMsgControllerRequest lineRequest = new LineMsgControllerRequest();
        lineRequest.setMessage(msg);
        messageService.addLineNoti(lineRequest, to_who);
    }

    @Bean
    public ServletContextInitializer initializer() {
        return new ServletContextInitializer() {

            @Override
            public void onStartup(ServletContext servletContext) throws ServletException {
                bxService.initdata();
            }
        };
    }
}
