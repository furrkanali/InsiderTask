package insider.pages;

import insider.methods.Methods;
import insider.utils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class MainPage extends Methods {

    private final By acceptAll = By.cssSelector("#wt-cli-accept-all-btn");
    private final By notificationLocator = By.cssSelector(".ins-notification-content");
    private final By closeButtonLocator = By.cssSelector(".ins-close-button");
    private final By insiderLogo = By.cssSelector("img[alt='insider_logo']");
    private final By firstBlock = By.cssSelector("#desktop_hero_24");
    private final By companyButton = By.xpath("(//a[normalize-space()='Company'])");
    private final By CareersButton = By.xpath("//a[normalize-space()='Careers']");
    private final By CareersPageHead = By.cssSelector("#page-head");


    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void openMainPage() {
        String url = ConfigReader.getProperty("url");
        driver.get(url);
        click(acceptAll);
        closeNotificationIfPresent(notificationLocator, closeButtonLocator);
        Assert.assertTrue(isElementVisible(insiderLogo));
        Assert.assertTrue(isElementVisible(firstBlock));

    }

    public void clickCompanyAndCareers() {
        click(companyButton);
        click(CareersButton);
        Assert.assertTrue(isElementVisible(insiderLogo));
        Assert.assertTrue(isElementVisible(CareersPageHead));
    }
}
