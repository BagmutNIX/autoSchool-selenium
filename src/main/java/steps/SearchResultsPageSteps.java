package steps;

import core.BaseTest;
import io.qameta.allure.Step;
import io.qameta.htmlelements.WebPageFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.BasePage;
import pages.SearchResultsPage;

public class SearchResultsPageSteps extends BaseSteps {

    //public WebDriver driver;

    public SearchResultsPageSteps(WebDriver driver) {
        super(driver);
    }

    //private HomePageSteps query;

    // 3. проверяем, что над списком продуктов отображается надпись 'SEARCH  "SUMMER"'
    @Step
    public SearchResultsPageSteps checkLabel(String query) {
        String searchLabelText = onSearchResultsPage().searchLabel().getText().replaceAll("\"", "").toLowerCase();
        Assert.assertEquals(searchLabelText, query.toLowerCase());
        return this;
    }

    // 4. открываем дропдаун сортировки и выбираем опцию 'Price: Highest first'
    @Step
    public SearchResultsPageSteps sortByPriceDesc() {
        onSearchResultsPage().sortDropdown().click();
        onSearchResultsPage().sortPriceDesc().click();
        return this;
    }

    // 5. проверяем, что элементы отсортированы в соответствии с выбранной опцией (сейчас сортировка идёт по старой
    // цене - если у товара есть скидка, нужно смотреть на старую цену)
    @Step
    public SearchResultsPageSteps checkSortPricesDesc() {
        onSearchResultsPage().sortDropdown().click();
        onSearchResultsPage().sortPriceDesc().click();
        return this;
    }

    // 6. берем первый из найденных товаров и запоминаем его полное название и цену
    @Step
    public SearchResultsPageSteps saveNameOfFirstproduct() {

        return this;
    }

    @Step
    public SearchResultsPageSteps savePriceOfFirstproduct() {

        return this;
    }

    // 7. добавляем его в корзину
    @Step
    public SearchResultsPageSteps addToCart() {
        onSearchResultsPage().addToCartBtn().click();
        onSearchResultsPage().proceedToCheckoutBtn().click();
        return this;
    }

    private SearchResultsPage onSearchResultsPage() {
        return new WebPageFactory().get(driver, SearchResultsPage.class);
    }
}
