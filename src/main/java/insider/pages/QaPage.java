package insider.pages;

import insider.methods.Methods;
import insider.utils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class QaPage extends Methods {
    private final By seeJobs = By.cssSelector(".btn.btn-outline-secondary.rounded.text-medium.mt-2.py-3.px-lg-5.w-100.w-md-50");
    private final By insiderLogo = By.cssSelector("img[alt='insider_logo']");
    private final By careerPositionFilter = By.cssSelector("#career-position-filter");
    private final By location = By.cssSelector("#select2-filter-by-location-container");
    private final By selectedLocation = By.cssSelector("[data-select2-id*='Istanbul, Turkey']");
    private final By jobList = By.cssSelector(".position-list.col-12.d-flex.flex-wrap.mt-5.pl-2.pr-2.pl-lg-0.pr-lg-0.pt-4");
    private final By positions = By.cssSelector("div.position-list-item[data-team='qualityassurance']");
    private final By positionList = By.cssSelector(".position-list-item-wrapper.bg-light");
    private final By positionTitle = By.cssSelector(".position-title.font-weight-bold");
    private final By positionDepartment = By.cssSelector(".position-department.text-large.font-weight-600.text-primary");
    private final By positionLocation = By.cssSelector(".position-location.text-large");
    private final By clickFirst = By.cssSelector(".btn.btn-navy.rounded.pt-2.pr-5.pb-2.pl-5");
    private final By postingHeader = By.cssSelector(".section.page-centered.posting-header");

    public QaPage(WebDriver driver) {
        super(driver);
    }

    public void openQaPage() throws InterruptedException {
        String urlQaPage = ConfigReader.getProperty("urlQaPage");
        driver.get(urlQaPage);
        click(seeJobs);
        Assert.assertTrue(isElementVisible(insiderLogo));
        Assert.assertTrue(isElementVisible(careerPositionFilter));
    }

    public void checkAllJobs() throws InterruptedException {
        String titleDepartmentProp = ConfigReader.getProperty("title-department");
        String locationProp = ConfigReader.getProperty("location");
        Thread.sleep(1500);
        click(location);
        click(selectedLocation);
        Assert.assertTrue(isElementVisible(jobList));
        scrollUntilVisible(positionList);
        waitForSeen(positions);
        verifyAllPositionDetails(positionList, positionTitle, positionDepartment, positionLocation, titleDepartmentProp, locationProp);
    }

    public void checkRedirection() {
        clickViewRoleButton(positionList, clickFirst);
        switchSecondTab();
        Assert.assertTrue(isElementVisible(postingHeader));
    }

}
