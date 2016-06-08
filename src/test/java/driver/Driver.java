package driver;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import exceptions.UnExpectedDriverTypeException;
import utils.ReadXmlFile;
import utils.ScreenShot;

import org.uncommons.reportng.HTMLReporter;

@Listeners({ HTMLReporter.class })
public class Driver {

	private static final Logger LOG = Logger.getLogger(Driver.class);

	{
		System.setProperty("org.uncommons.reportng.escape-output", "false");
	}

	// Set type!
	public static DriverEnum driverType = DriverEnum.FIREFOX;
	public static WebDriver driver;

	private String fileName2 = "letter_data.xml";
	private final static String URL = "http://mail.rambler.ru";
	@SuppressWarnings("unused")
	private ReadXmlFile provider;
	private final int numberDrafts = 2;

	@BeforeTest //Перед всеми тестами.
	public void initial() {
		ScreenShot.deleteAll();
		provider = new ReadXmlFile(fileName2, numberDrafts);
		driver = Driver.setWebDriverInstance(driverType);
		navigate();
	}

	public static WebDriver setWebDriverInstance(DriverEnum type) {
		WebDriver driver;
		switch (type) {
		case FIREFOX:
			File profileFile = new File("firebug-2.0.16-fx.xpi");
			FirefoxProfile profile = new FirefoxProfile();
			try {
				profile.addExtension(profileFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
			driver = new FirefoxDriver();
			break;
		default:
			throw new UnExpectedDriverTypeException("Unknown driver type.");
		}
		return driver;
	}

	public void navigate() {
		LOG.warn("Open browser in Driver.class");//
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(URL);
	}

	@AfterTest
	public void exit() throws InterruptedException {
		driver.quit();
		LOG.warn("Close browser in Driver.class");//
	}
}
