package insider.testRunners;

import insider.driver.Driver;
import insider.utils.ConfigReader;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest extends Driver {

    @BeforeTest
    public void setUpDriver() {
        String browser = ConfigReader.getProperty("browser");
        openBrowser(browser);
    }

    @AfterTest
    public void driverQuit() {
        quitDriver();
    }
}
