package com.qa.pages;

import com.qa.BaseTest;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class SettingsPage extends BaseTest {
	@AndroidFindBy(accessibility = "test-LOGOUT")
	private MobileElement logOut;
	
	public LoginPage pressLogoutBtn() {		
		Click(logOut);
		return new LoginPage();
	}

}
