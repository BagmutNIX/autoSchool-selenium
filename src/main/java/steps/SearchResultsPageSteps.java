package steps;

import blocks.Product;
import com.google.common.collect.Ordering;
import io.qameta.allure.Step;
import io.qameta.htmlelements.WebPageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import pages.SearchResultsPage;

import java.util.ArrayList;
import java.util.List;

import static matchers.BaseElementMatchers.isDisplayed;

public class SearchResultsPageSteps {

    private WebDriver driver;

    public SearchResultsPageSteps(WebDriver driver) {
        this.driver = driver;
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

    // 6. Берем первый из найденных товаров и запоминаем его полное название и цену
    @Step
    public String saveNameOfFirstproduct() {
        List<Product> productList = onSearchResultsPage().productList();
        String nameText = productList.get(0).should(isDisplayed()).productName().getText();
        return nameText;
    }

    @Step
    public String savePriceOfFirstproduct() {
        List<Product> productList = onSearchResultsPage().productList();
        String priceText = productList.get(0).productPriceActual().should(isDisplayed()).getText();
        return priceText;
    }

    // 7. добавляем его в корзину
    //TODO добавлять именно первый - для этого нужно кнопку добавить в blocks
    @Step
    public CartPageSteps addToCart() {
        List<Product> productList = onSearchResultsPage().productList();
        Actions action = new Actions(driver);
        action.moveToElement(productList.get(0)).build().perform();
        onSearchResultsPage().addToCartBtn().click();
        onSearchResultsPage().proceedToCheckoutBtn().click();
        return new CartPageSteps(driver);
    }

    private SearchResultsPage onSearchResultsPage() {
        return new WebPageFactory().get(driver, SearchResultsPage.class);
    }
}
