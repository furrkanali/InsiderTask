package insider.methods;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class Methods {
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public WebElement element;
    public JavascriptExecutor js;

    public Methods(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        this.actions = new Actions(driver);
    }

    public void waitFor(By locator){
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public WebElement findByLocator(By locator){
        return driver.findElement(locator);
    }

    public void click(By locator){
        waitFor(locator);
        findByLocator(locator).click();
    }

    public void waitForSeen(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public boolean isElementVisible(By locator) {
        try {
            waitForSeen(locator);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void scrollUntilVisible(By locator) {
        waitFor(locator);
        element = findByLocator(locator);
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'});", element);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void verifyAllPositionDetails(By positionList, By positionTitle, By positionDepartment, By positionLocation, String titleDepartmentProp, String locationProp) {
        waitFor(positionList);
        List<WebElement> positionItems = driver.findElements(positionList);
        Assert.assertFalse(positionItems.isEmpty());

        for (WebElement item : positionItems) {
            WebElement titleElement = item.findElement(positionTitle);
            WebElement departmentElement = item.findElement(positionDepartment);
            WebElement locationElement = item.findElement(positionLocation);

            String titleText = titleElement.getText();
            Assert.assertTrue(titleText.contains(titleDepartmentProp));

            String departmentText = departmentElement.getText();
            Assert.assertEquals(departmentText, titleDepartmentProp);

            String locationText = locationElement.getText();
            Assert.assertEquals(locationText, locationProp);
        }
    }

    public void clickViewRoleButton(By positionList, By clickFirst) {
        waitFor(positionList);
        List<WebElement> positionItems = driver.findElements(positionList);
        for (WebElement item : positionItems) {
            try {
                actions.moveToElement(item).perform();
                item.findElement(clickFirst).click();
                break;
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }

    public void switchSecondTab() {
        String originalWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();
        for (String windowHandle : allWindows) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }

    public void closeNotificationIfPresent(By notificationLocator, By closeButtonLocator) {
        if (isElementVisible(notificationLocator)) {
            try {
                WebElement closeButton = findByLocator(closeButtonLocator);
                closeButton.click();
            } catch (Exception e) {
                throw new RuntimeException("Failed to click the close button: " + e.getMessage(), e);
            }
        }
    }
}

