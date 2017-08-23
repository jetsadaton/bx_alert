package com.ton.ecorp.service;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.JsonPathException;
import com.jayway.jsonpath.PathNotFoundException;
import com.ton.ecorp.config.BxApiConfiguration;
import com.ton.ecorp.dao.BxDaoRest;
import com.ton.ecorp.object.Alert;
import com.ton.ecorp.object.BxCion;
import com.ton.ecorp.object.Users;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BxService {
    private static Logger logger = Logger.getLogger(ScheduledTask.class);

    @Autowired
    private BxDaoRest bxdao;
    @Autowired
    private BxApiConfiguration bxconfig;
    public BxCion[] GetDataCoin() throws Exception {
        String body = bxdao.GetBodyHtml(bxconfig.getBxApiUrl());
        BxCion[] bxcoins = new BxCion[27];
        for(int i =1;i<=26;i++)
        {
            try {
                BxCion bxcoin = new BxCion(JsonPath.read(body, "$." + i + ".primary_currency").toString() ,
                        JsonPath.read(body, "$." + i + ".secondary_currency").toString(),
                        JsonPath.read(body, "$." + i + ".last_price").toString(),
                        JsonPath.read(body, "$." + i + ".orderbook.bids.highbid").toString(),
                        JsonPath.read(body, "$." + i + ".orderbook.asks.highbid").toString(),
                        JsonPath.read(body, "$." + i + ".change").toString());
                bxcoins[i] = bxcoin;
            }
            catch ( PathNotFoundException e)
            {
                logger.info("GetDataMsg : " + e.getMessage());
            }

        }
        return bxcoins;
    }

    public  BxCion[] GetListCoin()
    {
        BxCion[] bxcoins = new BxCion[7];
        bxcoins[0] = new BxCion("THB" , "BTC" , "1");
        bxcoins[1] = new BxCion("THB" , "ETH" , "21");
        bxcoins[2] = new BxCion("THB" , "DAS" , "22");
        bxcoins[3] = new BxCion("THB" , "REP" , "23");
        bxcoins[4] = new BxCion("THB" , "GNO" , "24");
        bxcoins[5] = new BxCion("THB" , "XRP" , "25");
        bxcoins[6] = new BxCion("THB" , "OMG" , "26");
        return bxcoins;
    }
    public List<Alert> listAll() {
        List<Alert> alerts = ScheduledTask.alerts;;
        return alerts;
    }

    public List<Users> listUsers() {
        List<Users> user = ScheduledTask.users;;
        return user;
    }

    public void initdata()
    {
        List<Users> user = ScheduledTask.users;;
        user.add(new Users("ton" , "1,21,26"));
        user.add(new Users("ben" , "1,21,26"));
//        List<Alert> alerts = ScheduledTask.alerts;;
//        alerts.add(new Alert(1,100,"more" , 1 , true));
//        alerts.add(new Alert(2,120,"more" , 21 , true));
//        alerts.add(new Alert(3,130,"more" , 26 , true));
//        alerts.add(new Alert(4,140,"more" , 21 , true));
//        alerts.add(new Alert(5,150,"more" , 1 , true));
    }

    public void SetNewAlert(Alert alert)
    {
        List<Alert> alerts = ScheduledTask.alerts;
        alerts.add(alert);
    }

    public  String GetListToString(List<Alert> alert)
    {
        String msg="";
        for (int i=0;i<alert.size();i++)
        {
            msg = msg.concat(alert.get(i).toString()).concat("<br>");
        }

        return  msg;
    }



}
