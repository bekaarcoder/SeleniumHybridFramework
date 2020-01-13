package com.hybridframework.testCases;

import java.io.IOException;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hybridframework.pageObjects.HomePage;
import com.hybridframework.pageObjects.LoginPage;
import com.hybridframework.utilities.ExcelUtils;

public class TC_LoginDDT_01 extends BaseClass {
	
	@Test(dataProvider="LoginData")
	public void loginTestDTT(String uname, String pass) throws InterruptedException {
		LoginPage loginPage = new LoginPage(driver);
		HomePage home = new HomePage(driver);
		loginPage.setUsername(uname);
		loginPage.setPassword(pass);
		loginPage.clickLogin();
		
		Thread.sleep(3000);
		
		if(isAlertPresent()) {
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			logger.warn("Login Failed.");
			Assert.assertTrue(false);
		} else {
			Assert.assertTrue(true);
			logger.info("Login Successful.");
			home.clickLogout();
			Thread.sleep(3000);
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
		}
	}
	
	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch(NoAlertPresentException e) {
			return false;
		}
	}
	
	@DataProvider(name="LoginData")
	String[][] getData() throws IOException {
		String dataPath = System.getProperty("user.dir") + "/src/test/java/com/hybridframework/testData/TestData.xlsx";
		
		int rowNum = ExcelUtils.getRowCount(dataPath, "Sheet1");
		int colNum = ExcelUtils.getCellCount(dataPath, "Sheet1", 1);
		
		String loginData[][] = new String[rowNum][colNum];
		
		for(int i=1; i<=rowNum; i++) {
			for(int j=0; j<colNum; j++) {
				loginData[i-1][j] = ExcelUtils.getCellData(dataPath, "Sheet1", i, j);
			}
		}
		
		return loginData;
	}

}
