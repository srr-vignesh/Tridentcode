package seleniumBase;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import constants.Constants;
import utilities.CommonUtils;
import webdriver_manager.DriverManager;

public class LetsKodeBase {
	By practiselink = By.xpath("//a[contains(@href,'practice')]");	
	
	private static LetsKodeBase letskodeitbaseinstance;
	
	private LetsKodeBase() {
		
	}
	
	public static LetsKodeBase getinstance()
	{
		if(letskodeitbaseinstance==null)
		{
			letskodeitbaseinstance = new LetsKodeBase();
		}
		return letskodeitbaseinstance;
		
	}
	
	
	public void letskodeit_Setup() {
	    try {
	        CommonUtils.getinstance().loadproperties();

	        if (DriverManager.getDriver() == null) {
	            DriverManager.launchBrowser();
	            System.out.println("Browser launched and navigating to LetsKodeIT URL");
	        }

	        launchurlAndSwitchtoPractisePage();
	    } catch (Exception e) {
	        System.err.println("An exception occurred during setup: " + e.getMessage());
	        e.printStackTrace();
	        
	    }
	}

	private void launchurlAndSwitchtoPractisePage() throws InterruptedException {
		
		
		
		DriverManager.getDriver().manage().window().maximize();
		DriverManager.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		DriverManager.getDriver().get(Constants.letskodeit_URL);
		WebElement element = DriverManager.getDriver().findElement(practiselink);
		CommonUtils.getinstance().waitForElementToBeClickable(DriverManager.getDriver(), element, 25);		
		element.click();
		String currentWindowHandle = DriverManager.getDriver().getWindowHandle();
		CommonUtils.getinstance().switchToWindowByTitle(DriverManager.getDriver(),"Practise Page");
		Thread.sleep(3000);
		
	}
	

	
	public void quit() {
		try {
	        if (DriverManager.getinstance().getDriver() != null) {
	            DriverManager.getinstance().getDriver().quit();
	        }
	    } catch (Exception e) {
	        
	        System.err.println("Exception occurred during LetsKOde driver quit: " + e.getMessage());
	        e.printStackTrace();
	    } finally {
	        DriverManager.getinstance().unload();
	    }	
	}
	
	
	
	

}
