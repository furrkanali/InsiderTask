package insider.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Driver {
    public static WebDriver driver;

    public void openBrowser(String platform){
        if (platform.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            options.addArguments("--disable-gpu");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-notifications");
            options.addArguments("--window-size=1920,1080");
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(options);
        } else if (platform.equalsIgnoreCase("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--headless");
            options.addArguments("--disable-gpu");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-notifications");
            options.addArguments("--window-size=1920,1080");
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver(options);
        } else {
            throw new IllegalArgumentException("Invalid browser: " + platform);
        }
        driver.manage().window().maximize();
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
