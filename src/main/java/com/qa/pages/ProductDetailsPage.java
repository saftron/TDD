package com.qa.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class ProductDetailsPage extends MenuPage {


	@AndroidFindBy(xpath = "(//android.widget.TextView[@content-desc=\"test-Item title\"])[1]")
	private MobileElement SLBTitle;
	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Description\"]/android.widget.TextView[2]")
	private MobileElement SLBTxt;
	@AndroidFindBy(accessibility="test-BACK TO PRODUCTS") private MobileElement bactToProductsBtn;


	public String getSLBTitle() {
		return getText(SLBTitle);
	}

	public String getSLBTxt() {
		return getText(SLBTxt);
	}

	public ProductsPage pressBackToProductsBtn() {
		Click(bactToProductsBtn);
		return new ProductsPage();
	}

}
