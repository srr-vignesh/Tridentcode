package testcases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.makemytrip.pages.FlightDetailPage;
import com.makemytrip.pages.SearchFlight;

import seleniumBase.LetsKodeBase;
import seleniumBase.MakeMyTripBase;
import webdriver_manager.DriverManager;

public class TC02_makemytrip {

	@BeforeClass
	public void setUpAndLaunch() throws InterruptedException
	{
		MakeMyTripBase.getinstance().makemytrip_Setup();
	}
	@Test(priority=0)
	public void selectFromAndToCities() throws InterruptedException
	{
		SearchFlight.getinstance().selectFromCity("MAA");
		SearchFlight.getinstance().selectToCity("DEL");
		 System.out.println("MakeMyTrip FirstTestCase selectFromAndToCities completed");
		
	}
	@Test(priority=1)
	public void SelectDateAndSearchFlights() throws InterruptedException
	{
		SearchFlight.getinstance().selectDate("Sat Nov 18 2023");
		SearchFlight.getinstance().clickSearchButton();
		System.out.println("MakeMyTrip SecondTestCase SelectDateAndSearchFlights completed");
		
				
	}
	@Test(priority=2)
	public void verifySearchResults()
	{
		FlightDetailPage.getinstance().closeOverlayModal();
		String title = FlightDetailPage.getinstance().getJourneyTitle();
		System.out.println("The MakeMyTrip Journey Title is :  "+title);
		
		String expectedtitle = "Flights from Chennai to New Delhi";
		assert title.contains(expectedtitle) : "Title does not contain the expected substring";		
		System.out.println("***********MakeMYTrip : TestCase completed successfully***********");
		
		
		
	}
	
	@AfterClass(alwaysRun = true)
	public void tearDown() {
		
		MakeMyTripBase.getinstance().quit();
	}
	
	
	
	
	
}
