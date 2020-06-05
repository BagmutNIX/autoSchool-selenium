package pages;

import io.qameta.htmlelements.WebPage;
import io.qameta.htmlelements.annotation.FindBy;
import io.qameta.htmlelements.element.ExtendedList;
import io.qameta.htmlelements.element.HtmlElement;

import javax.naming.directory.SearchResult;

public interface SearchResultsPage extends WebPage {

    @FindBy("//div[@id='uniform-selectProductSort']")
    HtmlElement sortDropdown();

    @FindBy("//select[@id='selectProductSort']/option[@value='price:desc']")
    HtmlElement sortPriceDesc();

    @FindBy("//*[@id='center_column']//div[@class='product-container']")
    ExtendedList<SearchResult> productList();

    @FindBy("//*[@id='center_column']//div[1][@class='content_price']")
    ExtendedList<SearchResult> priceList();

    @FindBy("//a[@title='Add to cart']")
    HtmlElement addToCartBtn();

    @FindBy("//a[@title='Proceed to checkout']")
    HtmlElement proceedToCheckoutBtn();

   // @FindBy("id('s-results-list-atf')//li[contains(@id,'result')]")
   // ExtendedList<SearchResult> searchResultList();
}
