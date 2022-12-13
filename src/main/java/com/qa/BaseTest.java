package com.qa;

import org.testng.annotations.Test;

import com.beust.jcommander.Parameter;
import com.qa.utils.TestUtils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.MobileCapabilityType;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;

public class BaseTest {

	protected static AppiumDriver driver;
	protected static Properties props;
	protected static HashMap<String, String> strings = new HashMap<String, String>();
	InputStream inputStream;
	InputStream stringsis;
	TestUtils utils;

	public BaseTest() {

		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@Parameters({ "platformName", "platformVersion", "deviceName" })
	@BeforeTest
	public void beforeTest(String platformName, String platformVersion, String deviceName) throws Exception {

		try {
			props = new Properties();
			String propfileName = "config.properties";
			String XmlFileName = "strings/strings.xml";

			inputStream = getClass().getClassLoader().getResourceAsStream(propfileName);
			props.load(inputStream);

			stringsis = getClass().getClassLoader().getResourceAsStream(XmlFileName);
			utils = new TestUtils();
			strings = utils.parseStringXML(stringsis);

			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
			URL url = new URL(props.getProperty("appiumURL"));
			
			caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
			caps.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
			caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, props.getProperty("androidAutomationName"));
			caps.setCapability(MobileCapabilityType.UDID, "RZ8RC08NA6R");
			URL appUrl = getClass().getClassLoader().getResource(props.getProperty("androidAppLocation"));
//			String appUrl= getClass().getResource(props.getProperty("androidAppLocation")).getFile();
			System.out.println("appURL is "+appUrl);
			caps.setCapability("app", appUrl);
			caps.setCapability("appPackage", props.getProperty("androidAppPackage"));
			caps.setCapability("appActivity", props.getProperty("androidAppActivity"));
			driver = new AndroidDriver(url, caps);
			String sessionId = driver.getSessionId().toString();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}
		if (stringsis != null) {
			stringsis.close();
		}

	}

	public void waitForVisibility(MobileElement e) {
		WebDriverWait wait = new WebDriverWait(driver, TestUtils.wait);
		wait.until(ExpectedConditions.visibilityOf(e));
	}

	public void Click(MobileElement e) {
		waitForVisibility(e);
		e.click();
	}

	public void sendKeys(MobileElement e, String txt) {
		waitForVisibility(e);
		e.sendKeys(txt);
	}

	public String getAttribute(MobileElement e, String attribute) {
		waitForVisibility(e);
		return e.getAttribute(attribute);
	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}
