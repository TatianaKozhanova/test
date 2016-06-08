package tests.service;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;

import driver.Driver;
import pages.DraftPage;
import pages.InboxPage;
import pages.LoginPage;
import pages.MessagePage;
import pages.SentLetters;
import tests.business_objects.Account;
import tests.business_objects.Letter;


public class MailService {
	
	public void loginToMail(Account account) throws Exception {
		LoginPage loginPage = new LoginPage(Driver.driver);
		InboxPage inboxPage = PageFactory.initElements(Driver.driver, InboxPage.class);
		loginPage.goToInbox(account.getLogin(), account.getPassword());
		Assert.assertEquals(isPresent(inboxPage.getImg()), true, "Not displayed user mail.");
	}

	public void writeLetter(Letter letter) throws Exception {
		InboxPage inboxPage = PageFactory.initElements(Driver.driver, InboxPage.class);
		inboxPage.clickWrite();
		DraftPage draftPage = PageFactory.initElements(Driver.driver, DraftPage.class);
		Assert.assertEquals(isPresent(draftPage.getBackToLetters()), true, "Letters creation form is not opened.");
		draftPage.writeLetter(letter.getLetterAddress(), letter.getLetterSubject(), letter.getLetterText());
		Assert.assertEquals(isPresent(draftPage.getLetterSucSend()), true, "The letter was not created.");
	}

	public void goToSentLetters() {
		InboxPage inboxPage = PageFactory.initElements(Driver.driver, InboxPage.class);
		inboxPage.clickSent();
	}

	public void assertMessage(Letter letter)  {
		SentLetters sentLetters = PageFactory.initElements(Driver.driver, SentLetters.class);
		sentLetters.clickLetter(letter.getLetterAddress());
		MessagePage messagePage = PageFactory.initElements(Driver.driver, MessagePage.class);
		Assert.assertEquals(isPresent(messagePage.findTheme(letter.getLetterSubject())), true,
				"The element 'Letter's theme' is not correct.");
		Assert.assertEquals(isPresent(messagePage.findText(letter.getLetterText())), true,
				"The element 'Letter's text' is not correct.");
	}

	public void moveFirstLetter() throws Exception {
		SentLetters sentLetters = PageFactory.initElements(Driver.driver, SentLetters.class);
		sentLetters.moveFirstLetter();
		InboxPage inboxPage = PageFactory.initElements(Driver.driver, InboxPage.class);
		Assert.assertEquals(isPresent(inboxPage.getSuccessfulMove()), true, "The letter was not moved.");
	}

	public void exit() throws Exception {
		InboxPage inboxPage = PageFactory.initElements(Driver.driver, InboxPage.class);
		inboxPage.clickExit();
		Assert.assertEquals(isPresent(inboxPage.getMail()), true, "Logout not occurred.");
	}

	public boolean isPresent(WebElement button) {
		return (new FluentWait<WebDriver>(Driver.driver).withTimeout(10, TimeUnit.SECONDS)
				.pollingEvery(200, TimeUnit.MILLISECONDS)
				.ignoring(NoSuchElementException.class, ElementNotVisibleException.class)
				.until(new ExpectedCondition<Boolean>() {
					@Override
					public Boolean apply(WebDriver driver) {
						return button.isDisplayed();
					}
				}));
	}
}