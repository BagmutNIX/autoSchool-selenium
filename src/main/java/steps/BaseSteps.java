package steps;

import org.openqa.selenium.WebDriver;

public class BaseSteps {
    protected WebDriver driver;
    //protected String baseUrl;

    public BaseSteps(WebDriver driver) {
        this.driver = driver;
    }
    //private BaseDataProvider dataProvider;


}
