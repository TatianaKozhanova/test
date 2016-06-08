package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import driver.Driver;

public class MessagePage {

	public WebElement findTheme(String theme) {
		String xpathPattern = String.format("//*[text()[contains(.,'" + "%s" + "')]]", theme);
		WebElement letterTheme = Driver.driver.findElement(By.xpath(xpathPattern));
		return letterTheme;
	}
	public WebElement findText(String text) {
		String xpathPattern = String.format("//*[text()[contains(.,'" + "%s" + "')]]", text);
		WebElement letterText = Driver.driver.findElement(By.xpath(xpathPattern));
		return letterText;
	}
}
