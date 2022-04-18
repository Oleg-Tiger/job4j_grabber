package ru.job4j.design.lsp.foodstorage;

import org.junit.Assert;
import org.junit.Test;
import java.time.LocalDate;
import java.util.Arrays;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;

public class ControlQualityTest  {

    @Test
    public void whenPercentLifeTimeLess25ThenAddToWarehouse() {
        FoodStorage warehouse = new Warehouse();
        ControlQuality controlQuality = new ControlQuality(Arrays.asList(
                warehouse, new Shop(0), new Trash()
        ));
        Food milk = new Milk("Молоко", LocalDate.now().plusDays(65), LocalDate.now().minusDays(20), 100);
        controlQuality.addToStorage(milk);
        Assert.assertEquals(warehouse.getPercentLifeTimePassed(milk), 24.4, 0.1);
        assertThat(warehouse.getProducts().get(0), is(milk));
    }

    @Test
    public void whenPercentLifeTimeIs25ThenAddToShopWithoutDiscount() {
        FoodStorage shop = new Shop(20);
        ControlQuality controlQuality = new ControlQuality(Arrays.asList(
                new Warehouse(), shop, new Trash()
        ));
        Food milk = new Milk("Молоко", LocalDate.now().plusDays(63), LocalDate.now().minusDays(20), 100);
        controlQuality.addToStorage(milk);
        assertThat(milk.getPriceWithDiscount(), is(100.0));
        assertThat(shop.getPercentLifeTimePassed(milk), is(25.0));
        assertThat(shop.getProducts().get(0), is(milk));
    }

    @Test
    public void whenPercentLifeTimeIs75ThenAddToShopWithoutDiscount() {
        FoodStorage shop = new Shop(20);
        ControlQuality controlQuality = new ControlQuality(Arrays.asList(
                new Warehouse(), shop, new Trash()
        ));
        Food milk = new Milk("Молоко", LocalDate.now().plusDays(1), LocalDate.now().minusDays(2), 100);
        controlQuality.addToStorage(milk);
        assertThat(milk.getPriceWithDiscount(), is(100.0));
        assertThat(shop.getPercentLifeTimePassed(milk), is(75.0));
        assertThat(shop.getProducts().get(0), is(milk));
    }

    @Test
    public void whenPercentLifeTimeMore75ThenAddToShopWithDiscount() {
        FoodStorage shop = new Shop(20);
        ControlQuality controlQuality = new ControlQuality(Arrays.asList(
                new Warehouse(), shop, new Trash()
        ));
        Food milk = new Milk("Молоко", LocalDate.now().plusDays(1), LocalDate.now().minusDays(6), 100);
        controlQuality.addToStorage(milk);
        assertThat(milk.getPriceWithDiscount(), is(80.0));
        assertThat(shop.getPercentLifeTimePassed(milk), is(87.5));
        assertThat(shop.getProducts().get(0), is(milk));
    }

    @Test
    public void whenPercentLifeTimeIs100ThenAddToShopWithDiscount() {
        FoodStorage shop = new Shop(42);
        ControlQuality controlQuality = new ControlQuality(Arrays.asList(
                new Warehouse(), shop, new Trash()
        ));
        Food meat = new Meat("Мясо", LocalDate.now(), LocalDate.now().minusDays(10), 399.99);
        controlQuality.addToStorage(meat);
        Assert.assertEquals(meat.getPriceWithDiscount(), 231.99, 0.01);
        assertThat(shop.getPercentLifeTimePassed(meat), is(100.0));
        assertThat(shop.getProducts().get(0), is(meat));
    }

    @Test
    public void whenPercentLifeTimeMore100ThenAddToTrash() {
        FoodStorage trash = new Trash();
        ControlQuality controlQuality = new ControlQuality(Arrays.asList(
                new Warehouse(), new Shop(42), trash
        ));
        Food meat = new Meat("Мясо", LocalDate.now().minusDays(1), LocalDate.now().minusDays(123456), 100);
        controlQuality.addToStorage(meat);
        assertTrue(trash.getPercentLifeTimePassed(meat) > 100);
        assertThat(trash.getProducts().get(0), is(meat));
    }

}