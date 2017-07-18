package ecorp.service;

import ecorp.dao.BxDaoRest;
import ecorp.object.Alert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BxService {
    @Autowired
    private BxDaoRest bxdao;
    public String GetRecent() throws Exception {
       return  bxdao.GetRecent();
    }

    public List<Alert> listAll() {
        List<Alert> alerts = ScheduledTask.alerts;;
//        alerts.add(new Alert("1" , 87000 , "less than"));
//        countRepository.findAll().forEach(alerts::add);
        return alerts;
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
