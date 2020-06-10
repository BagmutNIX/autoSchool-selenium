package com;

import core.BaseTest;
//import org.junit.Test;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import steps.HomePageSteps;
import steps.SearchResultsPageSteps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class UISearchSortCartTest extends BaseTest {

    @Test(dataProvider = "searchQuery")
    public void searchTest(String query) throws InterruptedException {
        driver.get(baseUrl);
        //BaseSteps baseSteps = new BaseSteps(driver);
        HomePageSteps homePageSteps = new HomePageSteps(driver);
        SearchResultsPageSteps searchResultsPageSteps = new SearchResultsPageSteps(driver);

        System.out.println(query);

        homePageSteps
                .enterQueryToSearchInput(query);

        searchResultsPageSteps
                .sortByPriceDesc();

    }

    @DataProvider(name = "searchQuery")
    public Object[][] searchQuery() {
        List<String> data = new ArrayList<>();
        data.add("Summer");
        data.add("t-shirt");
        data.add("Dress");

        Object[][] result = new Object[data.size()][3];
        for (int i = 0; i < data.size(); i++) {
            result[i] = data.get(i).split(",");
        }
        System.out.println(result.toString());
        return result;
    }
}
