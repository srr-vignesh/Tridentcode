package com.makemytrip.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utilities.CommonUtils;
import webdriver_manager.DriverManager;

public class FlightDetailPage {

	private static FlightDetailPage flightdetailpageinstance;
	By overlayCloseButton = By.xpath("//span[contains(@class,'overlayCrossIcon')]");
	By journeyTitle = By.xpath("//p[contains(@class,'journey-title makeFlex')]/span");
 
	 private WebDriver driver;
	private FlightDetailPage() 
	{
		driver = DriverManager.getDriver();
	}
	public static FlightDetailPage getinstance() {
	
	if(flightdetailpageinstance==null){
		flightdetailpageinstance=new FlightDetailPage();
	}
	
		return flightdetailpageinstance;
		
	}
	public void closeOverlayModal() {
        try {
            WebElement closeButton = CommonUtils.getinstance().waitForElementToBeVisible(driver, overlayCloseButton, 35);

            if (closeButton.isDisplayed()) {
                closeButton.click();
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
	
	public String getJourneyTitle()
	{
		CommonUtils.getinstance().waitForElementToBeVisible(driver, journeyTitle, 25);
		String title = driver.findElement(journeyTitle).getText();
		return title;
	
	
}}
