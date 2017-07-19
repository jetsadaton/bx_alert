package ecorp.dao;

import com.jayway.jsonpath.JsonPath;
import ecorp.config.BxApiConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Repository
public class BxDaoRest {

    private  final String USER_AGENT = "Mozilla/5.0";
    @Autowired
    private BxApiConfiguration bxconfig;
    public String GetRecent() throws Exception {

        return JsonPath.read(GetBodyHtml(bxconfig.getBxApiUrl()), "$.trades[9].rate");
    }

    public  String GetBodyHtml(String url)
    {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> result = restTemplate.getForEntity(url , String.class);
        return result.getBody();
    }
}
