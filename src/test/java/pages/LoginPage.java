package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import driver.Driver;

public class LoginPage extends SuperPage {

	private Actions actionsObj;

	@FindBy(xpath = "//input[@name='login']")
	WebElement login;
	WebElement password = (WebElement) ((JavascriptExecutor) Driver.driver)
			.executeScript("return document.getElementById('form_password');");
	@FindBy(xpath = "//button[@type='submit']")
	WebElement enter;
	
	public LoginPage(WebDriver driver) {
		super(driver);
		actionsObj = new Actions(driver);
	}

	public void goToInbox(String loginStr, String passwordStr) {
		actionsObj.click(login).sendKeys(loginStr).build().perform();
		password.sendKeys(passwordStr);
		((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click()", enter);
	}
}
