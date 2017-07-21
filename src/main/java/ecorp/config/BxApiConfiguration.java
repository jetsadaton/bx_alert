package ecorp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BxApiConfiguration {
    @Value("${bx.api.url}")
    private String BxApiUrl;

    @Value("${heroku.run.start}")
    private int RunStartTimeM;

    @Value("${heroku.run.end}")
    private int RunEndTimeM;

    public String getBxApiUrl() {
        return BxApiUrl;
    }

    public int getRunEndTimeM() {
        return RunEndTimeM;
    }

    public int getRunStartTimeM() {
        return RunStartTimeM;
    }

    public void setBxApiUrl(String bxApiUrl) {
        BxApiUrl = bxApiUrl;
    }

    public void setRunEndTimeM(int runEndTimeM) {
        RunEndTimeM = runEndTimeM;
    }

    public void setRunStartTimeM(int runStartTimeM) {
        RunStartTimeM = runStartTimeM;
    }
}
