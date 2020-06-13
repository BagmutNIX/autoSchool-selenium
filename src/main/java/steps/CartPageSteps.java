package steps;

import io.qameta.allure.Step;
import io.qameta.htmlelements.WebPageFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.CartPage;
import pages.SearchResultsPage;

import static org.hamcrest.MatcherAssert.assertThat;

public class CartPageSteps {

    private WebDriver driver;

    public CartPageSteps(WebDriver driver) {
        this.driver = driver;
    }

    // 8. Открываем корзину и сравниваем название и цену в колонке "Total" у товара,
    // на соответствие с сохраненными значениями

    @Step
    public CartPageSteps checkName(String nameFromSearchResults) {
        String productNameInCart = onCartResultsPage().productNameInCart().getText();
        Assert.assertEquals(productNameInCart, nameFromSearchResults);
        return this;
    }

    @Step
    public CartPageSteps checkPrice(String priceFromSearchResults) {
        String productPriceInCart = onCartResultsPage().priceTotalInCart().getText();
        Assert.assertEquals(productPriceInCart, priceFromSearchResults);
        return this;
    }

    @Step
    public CartPageSteps deleteProduct() {
        onCartResultsPage().trashInCart().click();
        return this;
    }

    private CartPage onCartResultsPage() {
        return new WebPageFactory().get(driver, CartPage.class);
    }
}
