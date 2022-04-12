package ru.job4j.design.lsp;

import org.junit.Assert;
import org.junit.Test;
import java.time.LocalDate;
import java.time.Month;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

public class FoodTest {

    @Test (expected = IllegalArgumentException.class)
    public void whenExpiryEarlierThenCreate() {
        LocalDate create = LocalDate.of(22, Month.APRIL, 4);
        LocalDate expiry = LocalDate.of(22, Month.APRIL, 2);
        new Milk("Молоко", expiry, create, 80);
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenDateNowEarlierThenCreate() {
        LocalDate create = LocalDate.now().plusDays(3);
        LocalDate expiry = LocalDate.now().plusDays(4);
        new Milk("Молоко", expiry, create, 80);
    }

    @Test
    public void whenCreateFoodThenDiscountIsZero() {
        LocalDate create = LocalDate.now().minusDays(3);
        LocalDate expiry = LocalDate.now().plusDays(4);
        Food milk = new Milk("Молоко", expiry, create, 80);
        assertThat(milk.getDiscount(), is(0));
        assertThat(milk.getPrice(), is(80.0));
        assertThat(milk.getPriceWithDiscount(), is(80.0));
    }

    @Test
    public void whenChangePriceThenDiscountPriceChanges() {
        LocalDate create = LocalDate.now().minusDays(3);
        LocalDate expiry = LocalDate.now().plusDays(4);
        Food milk = new Milk("Молоко", expiry, create, 80);
        milk.setPrice(79.35);
        assertThat(milk.getPrice(), is(79.35));
        assertThat(milk.getPriceWithDiscount(), is(79.35));
    }

    @Test
    public void whenChangeDiscountThenDiscountPriceChangesAndRounded() {
        LocalDate create = LocalDate.now().minusDays(3);
        LocalDate expiry = LocalDate.now().plusDays(4);
        Food milk = new Milk("Молоко", expiry, create, 101.55);
        milk.setDiscount(22);
        assertThat(milk.getPrice(), is(101.55));
        assertThat(milk.getDiscount(), is(22));
        Assert.assertEquals(milk.getPriceWithDiscount(), 79.21, 0.01);
    }
}