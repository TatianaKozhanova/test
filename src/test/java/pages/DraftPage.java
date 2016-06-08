package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import driver.Driver;


public class DraftPage extends SuperPage {

	public Actions actionsObj;

	@FindBy(xpath = "//input[@class='uiAutocompleteTextInput']")
	WebElement address;
	@FindBy(css = "#subject")
	WebElement theme;
	@FindBy(id = "composeEditor")
	WebElement text;
	@FindBy(xpath = "//*[@title='К письмам']")
	WebElement backToLetters;
	@FindBy(xpath = "//button[@title='Отправить письмо']")
	WebElement sendLetter;
	@FindBy(xpath = "//div[text()='Письмо успешно отправлено']")
	WebElement letterSucSend;

	public DraftPage(WebDriver driver) {
		super(driver);
		this.actionsObj = new Actions(Driver.driver);
	}

	public WebElement getLetterSucSend() {
		return letterSucSend;
	}

	public WebElement getBackToLetters() {
		return backToLetters;
	}

	public void writeLetter(String address, String theme, String text) {
		actionsObj.click(this.address).sendKeys(address + Keys.ENTER).build().perform();
		actionsObj.click(this.theme).sendKeys(theme).build().perform();
		((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click()", this.text);
		actionsObj.click(this.text).sendKeys(text).build().perform();
		actionsObj.click(this.sendLetter).perform();
	}
}
