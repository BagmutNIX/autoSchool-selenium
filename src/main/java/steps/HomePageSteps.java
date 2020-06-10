package steps;

import io.qameta.allure.Step;
import io.qameta.htmlelements.WebPageFactory;
import org.openqa.selenium.WebDriver;
import pages.BasePage;

import static matchers.BaseElementMatchers.isDisplayed;

public class HomePageSteps extends BaseSteps {

    public WebDriver driver;

    public HomePageSteps(WebDriver driver) {
        super(driver);
    }

    //private String query;

    //public String getQuery() {
    //return query;
    //}

    // 2. в поле поиска вводим ключевое слово: 'Summer' и нажимаем значок поиска (лупу)
    @Step
    public HomePageSteps enterQueryToSearchInput(String query) {
        //this.query = query;
        onHomePage().searchInput().should(isDisplayed()).sendKeys(query);
        onHomePage().searchBtn().should(isDisplayed()).click();
        return this;
    }

    private BasePage onHomePage() {
        return new WebPageFactory().get(driver, BasePage.class);
    }
}
