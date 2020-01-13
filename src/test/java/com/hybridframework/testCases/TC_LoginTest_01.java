package com.hybridframework.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.hybridframework.pageObjects.LoginPage;
import com.hybridframework.utilities.GetScreenshot;

public class TC_LoginTest_01 extends BaseClass {
	
	@Test
	public void loginTest() throws IOException {		
		LoginPage loginPage = new LoginPage(driver);
		
		loginPage.setUsername(username);
		logger.info("Username is entered");
		
		loginPage.setPassword(password);
		logger.info("Password is entered");
		
		loginPage.clickLogin();
		System.out.println(driver.getTitle());
		
		if(driver.getTitle().equals("Guru99 Bank Manager HomePage fail")) {
			Assert.assertTrue(true);
			logger.info("Test case passed.");
		} else {
			GetScreenshot.getScreenshot(driver, "loginTest");
			logger.info("Test case failed");
			Assert.assertTrue(false);
		}
	}

}
