package runner;

import java.util.ArrayList;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.testng.TestNG;
import org.testng.xml.Parser;
import org.testng.xml.XmlSuite;
import org.uncommons.reportng.HTMLReporter;

import exceptions.TestNGRuntimeException;
import runner.args4j_settings.Settings;

public class Runner {

	private TestNG testNG = new TestNG();
	private ArrayList listeners;
	private String pathToTestNG = "testng.xml";

	public static void main(String[] args) {
		new Runner().parse(args);
	}

	public void parse(String[] args) {
		Settings settings = new Settings();
		CmdLineParser parser = new CmdLineParser(settings);
		try {
			parser.parseArgument(args);
			//System.out.println("pathToTestNG=" + settings.pathToTestNG);
		} catch (CmdLineException e) {
			System.err.println(e.getMessage());
			return;
		}
		//run(settings.pathToTestNG);
		run(pathToTestNG);
	}

	private void run(String pathToTestNG) {
		try {
			XmlSuite xmlSuite = new Parser(pathToTestNG).parseToList().get(0);
			this.testNG.setCommandLineSuite(xmlSuite);

			this.listeners = new ArrayList<Class>();
			this.listeners.add(HTMLReporter.class);
			this.testNG.setListenerClasses(listeners);

			this.testNG.run();
		} catch (Exception e) {
			throw new TestNGRuntimeException("Error running TestNG suite.");
		}
	}
	/*
	 * private void run() { testNG.setTestClasses(new Class[] {
	 * tests.MailTest.class }); testNG.run(); }
	 */
}
