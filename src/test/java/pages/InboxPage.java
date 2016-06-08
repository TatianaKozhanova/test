package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import driver.Driver;

public class InboxPage extends SuperPage {
	public Actions actionsObj;

	@FindBy(css = ".uiButton.uiButtonNormal[title=\"�������� ������\"]")
	WebElement write;
	@FindBy(xpath = "//span[@class ='rambler-topline__user-name']")
	WebElement exitList;
	@FindBy(xpath = "//div[text()='�����']")
	WebElement exit;
	@FindBy(xpath = "//*[text()='�����']")
	WebElement mail;
	@FindBy(xpath = "//*[text()='������ ���������� � �������']")
	WebElement successfulMove;
	@FindBy(xpath = "//*[text() = '������������']")
	WebElement sent;
	@FindBy(tagName = "img")
	WebElement img;

	public InboxPage(WebDriver driver) {
		super(driver);
		this.actionsObj = new Actions(Driver.driver);
	}

	public void clickWrite() {
		actionsObj.click(write).perform();
	}

	public void clickSent() {
		actionsObj.click(sent).perform();
	}

	public void clickExit() {
		actionsObj.click(exitList).click(exit).build().perform();
	}

	public WebElement getImg() {
		return img;
	}

	public WebElement getMail() {
		return mail;
	}

	public WebElement getWrite() {
		return write;
	}

	public WebElement getSuccessfulMove() {
		return successfulMove;
	}
}
