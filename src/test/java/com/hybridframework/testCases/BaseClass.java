package com.hybridframework.testCases;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;
import org.openqa.selenium.remote.CapabilityType;
import org.testng.annotations.*;

import com.hybridframework.utilities.ReadConfig;

public class BaseClass {
	
	ReadConfig readConfig = new ReadConfig();

	public String baseUrl = readConfig.getBaseUrl();
	public String username = readConfig.getUsername();
	public String password = readConfig.getPassword();
	public static WebDriver driver;
	public static Logger logger;
	
	@Parameters("browser")
	@BeforeClass
	public void setup(String browser) {
		// Initialize log4j
				logger = Logger.getLogger("ebanking");
				PropertyConfigurator.configure("Log4j.properties");
				
		// System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\Drivers\\chromedriver.exe");
		if(browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", readConfig.getChromeDriver());
			// Bypass SSL Certificate ChromeBrowser error
			ChromeOptions options = new ChromeOptions();
			options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
			driver = new ChromeDriver(options);
		} else if(browser.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", readConfig.getFirefoxDriver());
			ProfilesIni profile = new ProfilesIni();
			FirefoxProfile ffProfile = profile.getProfile("Selenium");
			ffProfile.setAcceptUntrustedCertificates(true);
			ffProfile.setAssumeUntrustedCertificateIssuer(true);
			FirefoxOptions options = new FirefoxOptions();
			options.setProfile(ffProfile);
			driver = new FirefoxDriver(options);
		}
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(baseUrl);
		logger.info("Url is opened");
		driver.manage().window().maximize();
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
}
