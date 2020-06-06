package steps;

import io.qameta.allure.Step;
import io.qameta.htmlelements.WebPageFactory;
import org.openqa.selenium.WebDriver;
import pages.BasePage;

public class HomePageSteps extends BaseSteps {

    public HomePageSteps(WebDriver driver) {
        super(driver);
    }

    private String query;

    public String getQuery() {
        return query;
    }

    // 2. в поле поиска вводим ключевое слово: 'Summer' и нажимаем значок поиска (лупу)
    @Step

    public HomePageSteps enterQueryToSearchInput(String query) {
        this.query = query;
        onBasePage().searchInput().sendKeys(query);
        onBasePage().searchBtn().click();
        return this;
    }

    private BasePage onBasePage() {
        return new WebPageFactory().get(driver, BasePage.class);
    }

}
