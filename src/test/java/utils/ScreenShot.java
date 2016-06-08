package utils;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;

public class ScreenShot {

	private static final String PATH_TO_REPORT = "test-output/html/";
	private static final String SCREENSHOTS_FOLDER = "screenshots";
	private final static Logger LOG = Logger.getLogger(ScreenShot.class);
	private static final String DEFAULT_MESSAGE = "See ScreenShot";

	public static void deleteAll() {
		File directory = new File(PATH_TO_REPORT + SCREENSHOTS_FOLDER);
		LOG.info("Delete all files from folder" + directory.getAbsolutePath());
		File[] files = directory.listFiles();
		if (files != null && files.length > 0) {
			for (File file : files) {
				if (!file.delete()) {
					LOG.info("Cannot delete file" + file);
				}
			}
		}
	}

	public static void make(WebDriver driver) {
		make(driver, DEFAULT_MESSAGE);
	}

	public static void make(WebDriver driver, String message) {
		if (driver == null) {
			return;
		}
		try {
			File screenshot = ((TakesScreenshot) driver)
					.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFileToDirectory(screenshot, new File(PATH_TO_REPORT
					+ SCREENSHOTS_FOLDER));
			String logMessage = "<a href='" + SCREENSHOTS_FOLDER + "/"
					+ screenshot.getName() + "'>"+message+"</a>";
			LOG.info(logMessage);
		} catch (Exception e) {
			LOG.error("The error with making a screenshot: " + e);
			throw new RuntimeException(e);
		}
	}
}
