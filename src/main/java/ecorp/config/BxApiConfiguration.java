package ecorp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BxApiConfiguration {
    @Value("${bx.api.url}")
    private String BxApiUrl;
//    @Value("${heroku.morning.starttime}")
//    private String RunStartTimeM;
//    @Value("${heroku.morning.endtime}")
//    private String RunEndTimeM;
//    @Value("${heroku.night.starttime}")
//    private String RunStartTimeN;
//    @Value("${heroku.night.endtime}")
//    private String RunEndTimeN;
//    @Value("${heroku.url.morning}")
//    private String HerokuUrlM;
//    @Value("${heroku.url.night}")
//    private String HerokuUrlN;
//    @Value("${heroku.api.mode}")
//    private String HerokuMode;


    public String getBxApiUrl() {
        return BxApiUrl;
    }
//    public String getHerokuUrl() {
//        if (HerokuMode == "morning") return HerokuUrlM;
//        else return HerokuUrlN;
//    }
//    public String getHerokuUrlRevert() {
//        if (HerokuMode == "morning") return HerokuUrlN;
//        else return HerokuUrlM;
//    }
//    public String getRunStartTime() {
//        if (HerokuMode == "morning") return RunStartTimeM;
//        else return RunStartTimeN;
//    }
//    public String getRunEndTime() {
//        if (HerokuMode == "morning") return RunEndTimeM;
//        else return RunEndTimeN;
//    }
//    public String geHerokuMode() { return  HerokuMode;}

}
