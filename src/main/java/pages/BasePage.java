package pages;

import io.qameta.htmlelements.WebPage;
import io.qameta.htmlelements.annotation.FindBy;
import io.qameta.htmlelements.element.HtmlElement;

public interface BasePage extends WebPage {
    @FindBy("//input[@id='search_query_top']")
    HtmlElement searchInput();

    @FindBy("//button[@name='submit_search']")
    HtmlElement searchBtn();
}
