package ecorp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LineAPIConfiguration {

	@Value("${line.api.url}")
	private String lineAPIPath;

	@Value("${line.api.tokenton}")
	private String lineAPITokenTon;

	@Value("${line.api.tokenko}")
	private String lineAPITokenKo;

	public String getLineAPIPath() {
		return lineAPIPath;
	}

	public String[] getLineAPIToken() {
		return new String[]{lineAPITokenTon, lineAPITokenKo};
	}

	public String getLineAPITokenKo() {
		return  lineAPITokenKo;
	}

	public String getLineAPITokenTon() {
		return  lineAPITokenTon;
	}
}
