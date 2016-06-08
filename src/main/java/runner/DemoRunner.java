/*package runner;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.testng.TestNG;
import runner.args4j_settings.Settings;

public class DemoRunner {

	private TestNG testNG = new TestNG();

	public DemoRunner(String[] args) {
		Settings settings = new Settings();
		CmdLineParser parser = new CmdLineParser(settings);
		try {
			parser.parseArgument(args);
			TestNGConfig.addTestNGConfigs(settings.pathToTestNG);
		} catch (CmdLineException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new DemoRunner(args).run();
	}

	private void run() {
		testNG.setTestClasses(new Class[] { tests.MailTest.class });
		testNG.run();
	}
}*/