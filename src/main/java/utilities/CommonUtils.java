package utilities;

import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.letskodeit.pages.PractisePage;
import com.makemytrip.pages.FlightDetailPage;
import com.makemytrip.pages.SearchFlight;

import constants.Constants;
import seleniumBase.LetsKodeBase;
import seleniumBase.MakeMyTripBase;
import webdriver_manager.DriverManager;

public class CommonUtils {
	
	private static CommonUtils CommonUtilsinstance;
	WebDriver driver = DriverManager.getDriver();
	 private static WebDriverWait createWebDriverWait(WebDriver driver, int timeoutInSeconds) {
	        return new WebDriverWait(driver, timeoutInSeconds);
	    }
	 
	
	private CommonUtils() {

	}
	public static CommonUtils getinstance() {
		if (CommonUtilsinstance == null) {
			CommonUtilsinstance = new CommonUtils();
		}
		return CommonUtilsinstance;

	}
	public void loadproperties() {

		Properties properties = new Properties();

		try {
			properties.load(getClass().getResourceAsStream("/config.properties"));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		Constants.letskodeit_URL = properties.getProperty("letskodeit_URL");
		Constants.makemytrip_URL = properties.getProperty("makemytrip_URL");
		Constants.browser = properties.getProperty("browser");

	}
	public static void initelements() {
		PageFactory.initElements(DriverManager.getDriver(), PractisePage.getinstance());
		PageFactory.initElements(DriverManager.getDriver(), LetsKodeBase.getinstance());
		PageFactory.initElements(DriverManager.getDriver(), SearchFlight.getinstance());
		PageFactory.initElements(DriverManager.getDriver(), MakeMyTripBase.getinstance());
		PageFactory.initElements(DriverManager.getDriver(), FlightDetailPage.getinstance());
		
		
	}
	
	public void selectFromDropdown(WebElement dropdown, String option, String value)
	{
		Select select = new Select(dropdown);
		switch (option) {
		case "index":
			select.selectByIndex(Integer.parseInt(value));
			break;
		case "value":
			select.selectByValue(value);
			break;
		case "text":
			select.selectByVisibleText(value);
			break;

		default:
			System.out.println("Please provide valid dropdown option");
			break;
		}
	}
	
	   public  WebElement waitForElementToBeClickable(WebDriver driver, WebElement element, int timeoutInSeconds) {
	        WebDriverWait wait = createWebDriverWait(driver, timeoutInSeconds);
	        return wait.until(ExpectedConditions.elementToBeClickable(element));
	    }

	    public  WebElement waitForElementToBeVisible(WebDriver driver, By element, int timeoutInSeconds) {
	        WebDriverWait wait = createWebDriverWait(driver, timeoutInSeconds);
	        return wait.until(ExpectedConditions.visibilityOfElementLocated(element));
	    }
	
	public  void switchToWindowByTitle(WebDriver driver, String targetTitle) {
        Set<String> windowHandles = driver.getWindowHandles();
        
        for (String handle : windowHandles) {
            driver.switchTo().window(handle);
            if (driver.getTitle().equalsIgnoreCase(targetTitle)) {
                return; 
            }
        }
    }
		 
		 public  void printWindowHandles(WebDriver driver) {
		        Set<String> windowHandles = driver.getWindowHandles();
		        int windowCount = windowHandles.size();
		        
		        System.out.println("Number of open windows: " + windowCount);
		        
		        for (String handle : windowHandles) {
		            System.out.println("Window Handle (ID): " + handle);
		        }
		    }

}
