package com.letskodeit.pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import utilities.CommonUtils;
import webdriver_manager.DriverManager;

public class PractisePage {
	
	private static PractisePage practisepageinstance;
	By Cardropdown = By.id("carselect");
	By selectClassLabel = By.xpath("//legend[text()='Select Class Example']");
	   
	
	
	private PractisePage() {
	
	}
	
	public static PractisePage getinstance()
	{
		if(practisepageinstance==null)
		{
			practisepageinstance = new PractisePage();
		}
		return practisepageinstance;
	}
	
	   public  String getPageTitle() {
		   String pagetitle = DriverManager.getinstance().getDriver().getTitle();
	        return pagetitle;
	        
	    }
	   public  String getUrl() {
		   String url = DriverManager.getinstance().getDriver().getCurrentUrl();
	        return url;
	        
	    }
	   public  int collectAllHyperlinks() {
	        List<WebElement> links = collectHyperlinks();
	        int linkCount = links.size();	        
	        return linkCount;
	    }

	    
	   public  List<WebElement> collectHyperlinks() {
		    
		    List<WebElement> hyperlinks = new ArrayList<>();		  
		    hyperlinks = DriverManager.getinstance().getDriver().findElements(By.tagName("a"));		    
		    return hyperlinks;
		}
	

	    
	    public List<WebElement> clickAllCheckboxesAndReturn() {
	        List<WebElement> checkboxes = DriverManager.getDriver().findElements(By.xpath("//div[@id='checkbox-example-div']//input"));

	        for (WebElement checkbox : checkboxes) {
	            checkbox.click();
	        }
	        
	        return checkboxes;
	    }
		public String selectCarDropDown(String carname){
			WebElement ele = DriverManager.getDriver().findElement(Cardropdown);
			CommonUtils.getinstance().waitForElementToBeClickable(DriverManager.getDriver(), ele, 15);	
			DriverManager.getDriver().findElement(Cardropdown).click();
			
			CommonUtils.getinstance().selectFromDropdown(DriverManager.getDriver().findElement(Cardropdown),"value",carname);
			
	        Select select = new Select(DriverManager.getDriver().findElement(Cardropdown));
	        WebElement selectedOption = select.getFirstSelectedOption();
	        String selectedValue = selectedOption.getAttribute("value");
	        return selectedValue;
		}

}
