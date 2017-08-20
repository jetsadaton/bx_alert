package com.ton.ecorp.service;

import com.ton.ecorp.config.LineAPIConfiguration;
import com.ton.ecorp.dao.LineDaoRestImpl;
import com.ton.ecorp.domain.LineMsgControllerRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    private LineDaoRestImpl lineDaoRest;
    @Autowired
    private LineAPIConfiguration lineConfig;

    private static Logger logger = Logger.getLogger(MessageService.class);

    public String addLineNoti(LineMsgControllerRequest lineRequest , String name) {

        String token = "" , response ="";
        if (name == "ton") token =  lineConfig.getLineAPITokenTon();
        else if (name == "ko") token =  lineConfig.getLineAPITokenKo();
        else if (name == "ben") token =  lineConfig.getLineAPITokenBen();
        if (token.length() > 0){
            response = lineDaoRest.SendMsg(lineRequest , token );
        }
        logger.info("Send line notification Successful");
        return response;
    }


}
