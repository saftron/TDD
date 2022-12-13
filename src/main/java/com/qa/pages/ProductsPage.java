package com.qa.pages;

import com.qa.MenuPage;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class ProductsPage extends MenuPage {
	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Cart drop zone\"]/android.view.ViewGroup/android.widget.TextView")
	private MobileElement productTitle;
	@AndroidFindBy(xpath="(//android.widget.TextView[@content-desc=\"test-Item title\"])[1]") private MobileElement SLBTitle;
	@AndroidFindBy(xpath="(//android.widget.TextView[@content-desc=\"test-Price\"])[1]") private MobileElement SLBPrice;

	public String getTitle() {
		return getAttribute(productTitle, "text");
	}
	
	public String getSLBTitle() {
		return getAttribute(SLBTitle, "text");
	}
	
	public String getSLBPrice() {
		return getAttribute(SLBPrice, "text");
	}
	
	public ProductDetailsPage pressSLBTitle() {
		Click(SLBTitle);
		return new ProductDetailsPage();
	}
}