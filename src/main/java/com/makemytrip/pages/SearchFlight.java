package com.makemytrip.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import utilities.CommonUtils;
import webdriver_manager.DriverManager;

public class SearchFlight {

    private static SearchFlight searchflightinstance;
   private WebDriver driver;

    By modalCloseButtonLocator = By.xpath("//span[@class='commonModal__close']");
    By fromCitylabel = By.xpath("//label[@for='fromCity']/span");
    By tocitylabel = By.xpath("//label[@for='toCity']/span");
    By FromCityInput = By.xpath("(//input[@type='text'])[1]");
    By ToCityInput = By.xpath("(//input[@type='text'])[3]");
    By FirstOptionInSuggestion = By.xpath("(//ul[contains(@class,'suggestions-list')]//div)[1]");
    By DepartureLabel = By.xpath("//span[contains(text(),'Departure')]");
    By SearchButton = By.xpath("//a[text()='Search']");
    By calendarNavigate = By.xpath("//span[@class='DayPicker-NavButton DayPicker-NavButton--next']");

    private SearchFlight() {
    	driver = DriverManager.getDriver();

    }

    public static SearchFlight getinstance() {
        if (searchflightinstance == null) {
            searchflightinstance = new SearchFlight();
        }

        return searchflightinstance;
    }

    public void closeAlertIfModalCloseButtonPresent(WebDriver driver) {
        Alert alert = driver.switchTo().alert();

        
        WebElement modalCloseButton = null;

        try {
            CommonUtils.getinstance().waitForElementToBeVisible(driver, modalCloseButtonLocator, 25);
            modalCloseButton = driver.findElement(modalCloseButtonLocator);
        } catch (org.openqa.selenium.NoSuchElementException e) {
           
        }

        if (modalCloseButton != null && modalCloseButton.isDisplayed()) {
      
            alert.accept();
        } else {
         
            alert.dismiss();
        }
    }

    public void closeModalIfDisplayed() {
        try {
            WebElement closeButton = CommonUtils.getinstance().waitForElementToBeVisible(driver, modalCloseButtonLocator, 25);

            if (closeButton.isDisplayed()) {
                closeButton.click();
            }
        } catch (Exception e) {
        	System.out.println("Close Modal is not displayed");
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    public void selectFromCity(String fromCityName) {
        try {
            Thread.sleep(3000);
            WebElement clickfromlabel = driver.findElement(fromCitylabel);
            clickfromlabel.click();
            Thread.sleep(2000);

            WebElement frominput = driver.findElement(FromCityInput);
            Actions actions = new Actions(driver);
            actions.moveToElement(frominput).click().sendKeys(fromCityName).build().perform();

            Thread.sleep(2000);
            CommonUtils.getinstance().waitForElementToBeVisible(driver, FirstOptionInSuggestion, 15);
            WebElement firstoption = driver.findElement(FirstOptionInSuggestion);
            firstoption.click();
        } catch (InterruptedException e) {
           
        }
    }

    public void selectToCity(String toCityName) {
        try {
            CommonUtils.getinstance().waitForElementToBeVisible(driver, tocitylabel, 15);
            WebElement clicktolabel = driver.findElement(tocitylabel);
            clicktolabel.click();

            WebElement toinput = driver.findElement(ToCityInput);
            toinput.sendKeys(toCityName);
            Thread.sleep(4000);

            CommonUtils.getinstance().waitForElementToBeVisible(driver, FirstOptionInSuggestion, 15);
            WebElement firstoption = driver.findElement(FirstOptionInSuggestion);
            firstoption.click();
        } catch (InterruptedException e) {
            
        }
    }

    public void selectDate(String targetDate) throws InterruptedException {
        String flag = "False";
        Thread.sleep(2000);
        while (flag.equals("False")) {

            By dynamicSelectDate = By.xpath("//div[@class='DayPicker-Day'][contains(@aria-label,'" + targetDate + "')]");

            if (driver.findElements(dynamicSelectDate).size() > 0) {

                driver.findElement(dynamicSelectDate).click();
                flag = "True";
                Thread.sleep(2000);
            } else {
                Thread.sleep(2000);
                System.out.println("MakeMyTrip Calendar section else condition");
                driver.findElement(calendarNavigate).click();
            }

        }
    }

    public void clickSearchButton() {
   
        driver.findElement(SearchButton).click();
    }
}
