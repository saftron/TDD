package com.qa.tests;

import org.testng.annotations.Test;

import com.qa.BaseTest;
import com.qa.pages.LoginPage;
import com.qa.pages.ProductsPage;

import io.appium.java_client.MobileElement;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class LoginTests extends BaseTest {

	LoginPage loginPage;
	ProductsPage productsPage;
	InputStream datais;
	JSONObject loginUsers;

	@BeforeClass
	public void beforeClass() throws Exception {
		try {
			String dataFileName = "data/loginUsers.json";
			datais = getClass().getClassLoader().getResourceAsStream(dataFileName);
			JSONTokener tokerner = new JSONTokener(datais);
			loginUsers = new JSONObject(tokerner);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;

		} finally {
			if (datais != null) {
				datais.close();
			}
		}
	}

	@AfterClass
	public void afterClass() {
	}

	@BeforeMethod
	public void beforeMethod(Method m) {
		loginPage = new LoginPage();
		System.out.println("\n" + "******** Starting tests: " + m.getName() + "********************" + "\n");
	}

	@AfterMethod
	public void afterMethod() {
	}

	@Test
	public void invalidUsername() {
		loginPage.enterUserName(loginUsers.getJSONObject("invalidUser").getString("username"));
		loginPage.enterPassword(loginUsers.getJSONObject("invalidUser").getString("password"));
		loginPage.pressLoginBtn();

		String actErrTxt = loginPage.getErrTxt();
		String expErrTxt = strings.get("err_invalid_username_or_password");
		System.out.println("Actual error txt - " + actErrTxt + "\n" + "Expected error txt - " + expErrTxt);

		Assert.assertEquals(actErrTxt, expErrTxt);
	}

	@Test
	public void invalidPassword() {

		loginPage.enterUserName(loginUsers.getJSONObject("invalidPwd").getString("username"));
		loginPage.enterPassword(loginUsers.getJSONObject("invalidPwd").getString("password"));
		loginPage.pressLoginBtn();

		String actErrTxt = loginPage.getErrTxt();
		String expErrTxt = strings.get("err_invalid_username_or_password");
		System.out.println("Actual error txt - " + actErrTxt + "\n" + "Expected error txt - " + expErrTxt);

		Assert.assertEquals(actErrTxt, expErrTxt);
	}

	@Test
	public void successLogin() {

		loginPage.enterUserName(loginUsers.getJSONObject("validUser").getString("username"));
		loginPage.enterPassword(loginUsers.getJSONObject("validUser").getString("password"));
		productsPage = loginPage.pressLoginBtn();

		String actProductTitle = productsPage.getTitle();
		String expectProductTitle = strings.get("product_title");
		System.out.println("Actual Products Title - " + actProductTitle + "\n" + "Expected Products Title - "
				+ expectProductTitle);

		Assert.assertEquals(actProductTitle, expectProductTitle);

	}

}
