package com.ton.ecorp.service;

import com.ton.ecorp.dao.LineDaoRestImpl;
import com.ton.ecorp.domain.LineMsgControllerRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    private LineDaoRestImpl lineDaoRest;

    private static Logger logger = Logger.getLogger(MessageService.class);

    public String addLineNoti(LineMsgControllerRequest lineRequest , String name) {

        String response = lineDaoRest.SendMsg(lineRequest , name );
        logger.info("Send line notification Successful");

        return response;
    }


}
