package testcases;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.letskodeit.pages.PractisePage;

import constants.Constants;
import seleniumBase.LetsKodeBase;
import utilities.CommonUtils;
import webdriver_manager.DriverManager;

public class TC01_letsKodeit {
	
	@BeforeClass
	public void setUpAndLaunch() 
	{
		LetsKodeBase.getinstance().letskodeit_Setup();
		
	}
	@Test(priority=0)
	public void practisePageTest()
	{
		String expectedTitle = "Practice Page";
		String Actualtitle = PractisePage.getinstance().getPageTitle();
		System.out.println("LetsKOdeIT : The page title is :"+Actualtitle);
		String url = PractisePage.getinstance().getUrl();
		System.out.println("LetsKOdeIT :The Current URL is :"+url);
		 Assert.assertEquals(Actualtitle, expectedTitle, "Page title does not match the expected title");
		 
		 int linkCount = PractisePage.getinstance().collectAllHyperlinks();
		System.out.println("Number of hyperlinks on the LetsKodeIT Practise page: " + linkCount);
		 Assert.assertTrue(linkCount >= 10, "Expected at least 10 hyperlinks on the page.");
		 System.out.println("LetsKodeIT FirstTestCase getAllLinks completed");
		 
		    String selectedValue = PractisePage.getinstance().selectCarDropDown("honda");
		    System.out.println("The LetsKodeIT selected DropDown value is : "+selectedValue);
		    Assert.assertEquals(selectedValue, "honda", "Selected value is not 'honda'");
		    System.out.println("LetsKodeIT SecondTestCase selectCarBrand completed");
		
			 List<WebElement> checkboxes = PractisePage.getinstance().clickAllCheckboxesAndReturn();				
		        for (WebElement checkbox : checkboxes) {
		            Assert.assertTrue(checkbox.isSelected(), "Checkbox is not selected.");
		        }
		        System.out.println("******LetsKOdeIT :TestCase completed successfully***********");
	}
	
		

	
	@AfterClass
	public void tearDown() {
		LetsKodeBase.getinstance().quit();
	}
	

}
