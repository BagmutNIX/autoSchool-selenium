package steps;

import io.qameta.allure.Step;
import io.qameta.htmlelements.WebPageFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.BasePage;
import pages.SearchResultsPage;

public class SearchResultsPageSteps extends BaseSteps {
    public SearchResultsPageSteps(WebDriver driver, HomePageSteps query) {
        super(driver);
        this.query = query;
    }

    private HomePageSteps query;

    // 3. проверяем, что над списком продуктов отображается надпись 'SEARCH  "SUMMER"'
    @Step
    public SearchResultsPageSteps checkLabel() {
        String searchLabelText = onSearchResultsPage().searchLabel().getText().replaceAll("\"", "").toLowerCase();
        Assert.assertEquals(searchLabelText, query.getQuery().toLowerCase());
        return this;
    }

    private SearchResultsPage onSearchResultsPage() {
        return new WebPageFactory().get(driver, SearchResultsPage.class);
    }
}
