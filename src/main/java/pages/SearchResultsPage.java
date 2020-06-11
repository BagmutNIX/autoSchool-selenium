package pages;

import blocks.Product;
import io.qameta.htmlelements.WebPage;
import io.qameta.htmlelements.annotation.FindBy;
import io.qameta.htmlelements.element.ExtendedList;
import io.qameta.htmlelements.element.HtmlElement;

import javax.naming.directory.SearchResult;

public interface SearchResultsPage extends WebPage {

    @FindBy("//span[@class='lighter']")
    HtmlElement searchLabel();

    @FindBy("//div[@id='uniform-selectProductSort']")
    HtmlElement sortDropdown();

    @FindBy("//select[@id='selectProductSort']/option[@value='price:desc']")
    HtmlElement sortPriceDesc();

    @FindBy("//*[@id='center_column']//div[@class='product-container']")
    ExtendedList<Product> productList();

    @FindBy("//*[@id='center_column']//div[1][@class='content_price']")
    ExtendedList<Product> priceList();

    @FindBy("//a[@title='Add to cart']")
    HtmlElement addToCartBtn();

    @FindBy("//a[@title='Proceed to checkout']")
    HtmlElement proceedToCheckoutBtn();

    @FindBy("//*[@id='center_column']//div[1]/*[@class='price product-price']")
    HtmlElement actualPrice();

    @FindBy("//*[@id='center_column']//div[1]/*[@class='old-price product-price']")
    HtmlElement oldPrice();


   // @FindBy("id('s-results-list-atf')//li[contains(@id,'result')]")
   // ExtendedList<SearchResult> searchResultList();
}
