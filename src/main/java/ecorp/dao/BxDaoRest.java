package ecorp.dao;

import com.jayway.jsonpath.JsonPath;
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

    public String GetRecent() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> result = restTemplate.getForEntity("https://bx.in.th/api/trade/?pairing=1" , String.class);
        return JsonPath.read(result.getBody(), "$.trades[9].rate");
    }
}
