package com.automationpractice;

import com.core.BaseTest;
import com.google.common.collect.Ordering;
import com.sun.org.glassfish.gmbal.Description;
import com.sun.xml.internal.bind.v2.TODO;
import net.bytebuddy.description.type.TypeDescription;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.*;

public class UISearchSortCartTest extends BaseTest {

    @Description("Perform tests: search, sort, add to cart")
    @Test
    public void uiSearchSortCartTest() throws InterruptedException {

        // 1. открываем сайт http://automationpractice.com/
        driver.get(baseUrl);

        // 2. в поле поиска вводим ключевое слово: 'Summer' и нажимаем значок поиска (лупу)
        WebElement search = driver.findElement(By.xpath("//input[@id='search_query_top']"));
        search.sendKeys("Summer");
        driver.findElement(By.xpath("//button[@name='submit_search']")).click();

        // 3. проверяем, что над списком продуктов отображается надпись 'SEARCH  "SUMMER"'
        WebElement searchResultLabel = driver.findElement(By.xpath("//span[@class='lighter']"));
        String searchResultLabelText = searchResultLabel.getText().replaceAll("\"", "");
        Assert.assertEquals(searchResultLabelText, "SUMMER");

        // 4. открываем дропдаун сортировки и выбираем опцию 'Price: Highest first'
        driver.findElement(By.xpath("//div[@id='uniform-selectProductSort']")).click();
        driver.findElement(By.xpath("//select[@id='selectProductSort']/option[@value='price:desc']")).click();

        // 5. проверяем, что элементы отсортированы в соответствии с выбранной опцией (сейчас сортировка идёт по старой
        // цене - если у товара есть скидка, нужно смотреть на старую цену)
        //TODO with old price (пока что начала делать с просто прайсом, т.к. так проще)
        List<WebElement> priceElement = driver.findElements(By.xpath("//*[@class='price product-price']"));
        List<Double> priceNumber = new ArrayList<Double>();

        for (int i = 0; i < priceElement.size(); i++) {
            String priceText = priceElement.get(i).getText();//
            if (priceText.isEmpty())
                continue;
            priceText = priceText.replace("$", "");
            System.out.println(priceText);
            priceNumber.add(Double.valueOf(priceText));
        }

        System.out.println();
        for (int i = 0; i < priceNumber.size(); i++) {
            System.out.println(priceNumber.get(i));
        }

        //Collections.sort(priceNumber);
        boolean isSorted = Ordering.natural().isOrdered(priceNumber);
        System.out.println("Is sorted: " + isSorted);


        Assert.assertEquals(isSorted, true);
        if (isSorted = false) System.out.println("Prices are sorted incorrect");

        //List<Double> priceToDouble;

        // 6. берем первый из найденных товаров и запоминаем его полное название и цену
        // 7. добавляем его в корзину
        // 8. открываем корзину и сравниваем название и цену в "Unit price" на соответствие с сохраненными значениями
        // 9. используя аннотацию параметризации тестов, добавьте кроме 'Summer' сценарии поиска 'Dress' и 't-shirt'
    }
}
