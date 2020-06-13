package blocks;

import io.qameta.htmlelements.annotation.FindBy;
import io.qameta.htmlelements.element.ExtendedWebElement;
import io.qameta.htmlelements.element.HtmlElement;


public interface Product extends ExtendedWebElement<Product> {

    //name on search results page: //*[@id='center_column']//*[@class='product-name']
    //name on search results page option2:
    //*[@id='center_column']//div[@class='product-container']//a[@class='product-name']

    @FindBy(".//a[@class='product-name']")
    HtmlElement productName();

    //old price on search results page: //*[@id='center_column']//div[1]/*[@class='old-price product-price']
    //actual price on search results page: //*[@id='center_column']//div[1]/*[@class='price product-price']
    //*[@id='center_column']//div[1][@class='content_price']/*[@class='price product-price']

    //old price on search results page option2:
    //*[@id='center_column']//div[1][@class='content_price']/span[@class='old-price product-price']


    @FindBy(".//div[1]/*[@class='old-price product-price']")
    HtmlElement productPriceOld();

    @FindBy(".//div[1]/*[@class='price product-price']")
    HtmlElement productPriceActual();
}