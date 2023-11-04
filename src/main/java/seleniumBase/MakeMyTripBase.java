package seleniumBase;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import com.makemytrip.pages.SearchFlight;

import constants.Constants;
import utilities.CommonUtils;
import webdriver_manager.DriverManager;

public class MakeMyTripBase {

	private static MakeMyTripBase makemytripbaseinstance;
	
	private MakeMyTripBase()
	{
		
	}
	public static MakeMyTripBase getinstance()
	{
		if(makemytripbaseinstance==null)
		{
			makemytripbaseinstance=new MakeMyTripBase();
		}
		
		return makemytripbaseinstance;
		
	}
	public void makemytrip_Setup() throws InterruptedException
	{
    CommonUtils.getinstance().loadproperties();
		
		if(DriverManager.getDriver() == null)
		{

			DriverManager.launchBrowser();
			CommonUtils.getinstance().initelements();			
			System.out.println("browser launched and navigating to MakemyTrip url");
			
		}
	
		launchURLAndCloseAlert();
	}
	
	public void launchURLAndCloseAlert() throws InterruptedException {
		
		WebDriver driver = DriverManager.getDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(Constants.makemytrip_URL);
		Thread.sleep(5000);
		SearchFlight.getinstance().closeModalIfDisplayed();
	}
	
	public void quit() {
		
		try {
	        if (DriverManager.getinstance().getDriver() != null) {
	            DriverManager.getinstance().getDriver().quit();
	        }
	    } catch (Exception e) {
	        
	        System.out.println("Exception occurred during Makemytrip driver quit: " + e.getMessage());
	        e.printStackTrace();
	    } finally {
	        DriverManager.getinstance().unload();
	    }	
		
	}
	
}
