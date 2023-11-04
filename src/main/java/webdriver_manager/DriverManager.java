package webdriver_manager;

import java.util.Objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import constants.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
/**
 * @description : Used Singleton Design pattern with Thread local concept.
 * 
 *
 */

public class DriverManager {
    private static DriverManager driverManagerInstance;
    private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    private static WebDriver driver = null;

    private DriverManager() {
    }

    public static synchronized DriverManager getinstance() {
        if (driverManagerInstance == null) {
            driverManagerInstance = new DriverManager();
        }
        return driverManagerInstance;
    }

    public static WebDriver getDriver() {
    	
        return driverThreadLocal.get();
    }

    public static void setDriver(WebDriver driverRef) {
        driverThreadLocal.set(driverRef);
    }

    public static void unload() {
    	WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            driver.quit();
            driverThreadLocal.remove();
        }
    }

    public static void launchBrowser() {
        try {
            if (getDriver() == null) {
                switch (Constants.browser) {
                    case "chrome":
                        WebDriverManager.chromedriver().setup();
                        driver = new ChromeDriver();
                        setDriver(driver);
                        break;          

                    default:
                        System.err.println("Unsupported browser type: " + Constants.browser);
                        break;
                }
            }
        } catch (Exception e) {
            System.err.println("An exception occurred during WebDriver initialization: " + e.getMessage());
            e.printStackTrace();
            
        }
    }
}