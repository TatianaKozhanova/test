package tests;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import driver.Driver;
import tests.business_objects.Account;
import tests.business_objects.Letter;
import tests.service.MailService;
import utils.ReadXmlFile;
import utils.ScreenShot;

public class MailTest extends Driver {
	
	private static final Logger LOG = Logger.getLogger(MailTest.class);
	
	private MailService mailService = new MailService();
	
	@Test(priority = 0)
	public void login() throws Exception {
		LOG.info("Start 'login' test");
		Account account = new Account();
		account.setLogin("testwebdr");
		account.setPassword("test_web");
		mailService.loginToMail(account);
		LOG.info("Finish 'login' test");
	}

	@Test(priority = 1, dataProvider = "letterInfo", dataProviderClass = ReadXmlFile.class)
	public void createLetter(String address, String theme, String text) throws Exception {
		LOG.info("Start 'createLetter' test");
		Letter letter = new Letter(address, theme, text);
		mailService.writeLetter(letter);
		LOG.info("Finish 'createLetter' test");
	}
	
	/*
	@Test(priority = 1, dependsOnMethods = "createLetter")
	public void failTest(){
		LOG.fatal("Start 'failTest' test.");
		ScreenShot.make(Driver.driver);//SCREEN
		Assert.fail("Fail test!");
		LOG.fatal("Finish 'failTest' test.");
	}
	*/
	
	@Test(priority = 2, dataProvider = "letterInfo", dataProviderClass = ReadXmlFile.class, dependsOnMethods = "createLetter")
	public void assertLetter(String address, String theme, String text) throws Exception {
		LOG.info("Start 'assertLetter' test");
		mailService.goToSentLetters();
		Letter letter = new Letter(address, theme, text);
		mailService.assertMessage(letter);
		mailService.goToSentLetters();
		LOG.info("Finish 'assertLetter' test");
	}
	
	@Test(priority = 3, dependsOnMethods = "assertLetter")
	public void dragAndDrop() throws Exception {
		LOG.info("Start 'dragAndDrop' test");
		mailService.moveFirstLetter();
		LOG.info("Finish 'dragAndDrop' test");
	}
	
	@Test(priority = 4, dependsOnMethods = "dragAndDrop")
	public void logOut() throws Exception {
		LOG.info("Start 'logOut' test");
		// ScreenShot.make(Driver.driver);//SCREEN
		mailService.exit();
		LOG.info("Finish 'logOut' test");
	}
}
