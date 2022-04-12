package ru.job4j.design.lsp;

import org.junit.Assert;
import org.junit.Test;
import java.time.LocalDate;
import java.util.Arrays;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;

public class ControlQualityTest  {

    @Test
    public void whenPercentLifeTimeLess25AndAddToWarehouse() {
        ControlQuality controlQuality = new ControlQuality(Arrays.asList(
                new Warehouse(), new Shop(0), new Trash()
        ));
        Food milk = new Milk("Молоко", LocalDate.now().plusDays(65), LocalDate.now().minusDays(20), 100);
        controlQuality.addToStorage(milk);
        Assert.assertEquals(milk.getPercentLifeTimePassed(),24.4, 0.1);
        assertThat(controlQuality.getStorages().get(0).getProducts().get(0), is(milk));
    }

    @Test
    public void whenPercentLifeTimeIs25AndAddToShopWithoutDiscount() {
        ControlQuality controlQuality = new ControlQuality(Arrays.asList(
                new Warehouse(), new Shop(20), new Trash()
        ));
        Food milk = new Milk("Молоко", LocalDate.now().plusDays(63), LocalDate.now().minusDays(20), 100);
        controlQuality.addToStorage(milk);
        assertThat(milk.getPriceWithDiscount(), is(100.0));
        assertThat(milk.getPercentLifeTimePassed(), is(25.0));
        assertThat(controlQuality.getStorages().get(1).getProducts().get(0), is(milk));
    }

    @Test
    public void whenPercentLifeTimeIs75AndAddToShopWithoutDiscount() {
        ControlQuality controlQuality = new ControlQuality(Arrays.asList(
                new Warehouse(), new Shop(20), new Trash()
        ));
        Food milk = new Milk("Молоко", LocalDate.now().plusDays(1), LocalDate.now().minusDays(2), 100);
        controlQuality.addToStorage(milk);
        assertThat(milk.getPriceWithDiscount(), is(100.0));
        assertThat(milk.getPercentLifeTimePassed(), is(75.0));
        assertThat(controlQuality.getStorages().get(1).getProducts().get(0), is(milk));
    }

    @Test
    public void whenPercentLifeTimeMore75AndAddToShopWithDiscount() {
        ControlQuality controlQuality = new ControlQuality(Arrays.asList(
                new Warehouse(), new Shop(20), new Trash()
        ));
        Food milk = new Milk("Молоко", LocalDate.now().plusDays(1), LocalDate.now().minusDays(6), 100);
        controlQuality.addToStorage(milk);
        assertThat(milk.getPriceWithDiscount(), is(80.0));
        assertThat(milk.getPercentLifeTimePassed(), is(87.5));
        assertThat(controlQuality.getStorages().get(1).getProducts().get(0), is(milk));
    }

    @Test
    public void whenPercentLifeTimeIs100AndAddToShopWithDiscount() {
        ControlQuality controlQuality = new ControlQuality(Arrays.asList(
                new Warehouse(), new Shop(42), new Trash()
        ));
        Food meat = new Meat("Мясо", LocalDate.now(), LocalDate.now().minusDays(10), 399.99);
        controlQuality.addToStorage(meat);
        Assert.assertEquals(meat.getPriceWithDiscount(),231.99, 0.01);
        assertThat(meat.getPercentLifeTimePassed(), is(100.0));
        assertThat(controlQuality.getStorages().get(1).getProducts().get(0), is(meat));
    }

    @Test
    public void whenPercentLifeTimeMore100AndAddToTrash() {
        ControlQuality controlQuality = new ControlQuality(Arrays.asList(
                new Warehouse(), new Shop(42), new Trash()
        ));
        Food meat = new Meat("Мясо", LocalDate.now().minusDays(1), LocalDate.now().minusDays(123456), 100);
        controlQuality.addToStorage(meat);
        assertTrue(meat.getPercentLifeTimePassed() > 100);
        assertThat(controlQuality.getStorages().get(2).getProducts().get(0), is(meat));
    }

}