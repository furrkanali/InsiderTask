package insider.testRunners;
import insider.pages.CareerPage;
import insider.pages.MainPage;
import insider.pages.QaPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MainTest extends BaseTest{
    MainPage mainPage;
    CareerPage careerPage;
    QaPage qaPage;

    @BeforeClass
    private void setUp() {
        mainPage = new MainPage(driver);
        careerPage = new CareerPage(driver);
        qaPage = new QaPage(driver);
    }

    @Test
    public void firstTest() {
        mainPage.openMainPage();
        mainPage.clickCompanyAndCareers();
        careerPage.checkCareerPage();
    }

    @Test
    public void secondTest() throws InterruptedException {
        qaPage.openQaPage();
        qaPage.checkAllJobs();
        qaPage.checkRedirection();
    }

}
