package steps;

import com.google.common.collect.Ordering;
import core.BaseTest;
import io.qameta.allure.Step;
import io.qameta.htmlelements.WebPageFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.BasePage;
import pages.SearchResultsPage;

import java.util.ArrayList;
import java.util.List;

public class SearchResultsPageSteps extends BaseSteps {

    //public WebDriver driver;

    public SearchResultsPageSteps(WebDriver driver) {
        super(driver);
    }

    //private HomePageSteps query;

    // 3. Проверяем, что над списком продуктов в надписи 'SEARCH' отображается наш поисковый запрос
    @Step
    public SearchResultsPageSteps checkSearchLabel(String query) {
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
        List<Double> priceNumber = new ArrayList<Double>();
        for (int i = 0; i < onSearchResultsPage().priceList().size(); i++) {
            String priceText = onSearchResultsPage().priceList().get(i).getText();
            if (priceText.isEmpty())
                continue;
            priceText = priceText.replace("$", "");
            System.out.println(priceText);
            priceNumber.add(Double.valueOf(priceText));
        }
        //Collections.sort(priceNumber);
        boolean isSorted = Ordering.natural().isOrdered(priceNumber);
        System.out.println("Is sorted: " + isSorted);
        Assert.assertEquals(isSorted, true);
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
