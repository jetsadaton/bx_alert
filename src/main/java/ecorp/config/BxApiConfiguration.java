package ecorp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BxApiConfiguration {
    @Value("${bx.api.url}")
    private String BxApiUrl;

    @Value("${heroku.run.start.morning}")
    private String RunStartTimeM;

    @Value("${heroku.run.end.morning}")
    private String RunEndTimeM;

    @Value("${heroku.run.start.night}")
    private String RunStartTimeN;

    @Value("${heroku.run.end.night}")
    private String RunEndTimeN;

    @Value("${heroku.url.morning}")
    private String HerokuUrlM;

    @Value("${hroku.url.night}")
    private String HerokuUrlN;

    @Value("${heroku.run.mode}")
    private String HerokuMode;


    public String getBxApiUrl() {
        return BxApiUrl;
    }
    public String getHerokuUrl() {
        if (HerokuMode.equals("m")) return HerokuUrlM;
        else return HerokuUrlN;
    }
    public String getHerokuUrlRevert() {
        if (HerokuMode.equals("m")) return HerokuUrlN;
        else return HerokuUrlM;
    }
    public int getRunStartTime() {
        if (HerokuMode.equals("m")) return Integer.parseInt(RunStartTimeM);
        else return Integer.parseInt(RunStartTimeN);
    }
    public int getRunEndTime() {
        if (HerokuMode.equals("m")) return Integer.parseInt(RunEndTimeM);
        else return Integer.parseInt(RunEndTimeN);
    }
    public String geHerokuMode() { return  HerokuMode;}

    public String getHerukuRes() {
        if (HerokuMode.equals("m")) return "Morning";
        else return "Night";
    }

}
