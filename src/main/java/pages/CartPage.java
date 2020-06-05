package pages;

import io.qameta.htmlelements.annotation.FindBy;
import io.qameta.htmlelements.element.HtmlElement;

public interface CartPage {

    @FindBy("//td/p[@class='product-name']")
    HtmlElement productNameInCart();

    @FindBy("//td[@class='cart_total'][@data-title='Total']")
    HtmlElement priceTotalInCart();

    @FindBy("//*[@class='icon-trash']")
    HtmlElement trashInCart();
}
