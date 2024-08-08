package insider.pages;

import insider.methods.Methods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CareerPage extends Methods {

    private final By teamSection = By.cssSelector(".career-load-more");
    private final By locationGlide = By.cssSelector(".glide__slides");
    private final By lifeAtInsider = By.cssSelector(".elementor-section.elementor-top-section.elementor-element.elementor-element-a8e7b90.elementor-section-full_width.elementor-section-height-default.elementor-section-height-default\n");

    public CareerPage(WebDriver driver) {
        super(driver);
    }

    public void checkCareerPage() {
        scrollUntilVisible(teamSection);
        Assert.assertTrue(isElementVisible(teamSection));
        scrollUntilVisible(locationGlide);
        Assert.assertTrue(isElementVisible(locationGlide));
        scrollUntilVisible(lifeAtInsider);
        Assert.assertTrue(isElementVisible(lifeAtInsider));
    }
}
