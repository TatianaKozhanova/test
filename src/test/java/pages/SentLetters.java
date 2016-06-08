package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import driver.Driver;

public class SentLetters extends SuperPage {

	public Actions actionsObj;

	@FindBy(xpath = "//*[@class='tableCell tableSenderRow']")
	WebElement firstLetter;

	@FindBy(xpath = "//*[text() = 'Корзина']")
	WebElement junk;

	public SentLetters(WebDriver driver) {
		super(driver);
		this.actionsObj = new Actions(Driver.driver);
	}

	public void moveFirstLetter() {
		actionsObj.dragAndDrop(firstLetter, junk).perform();
	}

	public void clickLetter(String address) {
		String xpathPattern = String.format("//*[contains(@title, '%s')]", address);
		WebElement letterAddress = Driver.driver.findElement(By.xpath(xpathPattern));
		letterAddress.click();
	}
}
