package runner.args4j_settings;

import org.kohsuke.args4j.Option;

public class Settings {

	@Option(name = "--testng", required = false) // --testng "src/test/java/xml/testng.xml"
	public String pathToTestNG;

	@Option(name = "--login", required = false) //testwebdr
	public String login;

	@Option(name = "--password", required = false) //test_web
	public String password;

	@Option(name = "--url", required = false) //http://mail.rambler.ru
	public String url;
	
  /*  @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }*/
}

