package ru.job4j.design.lsp;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public abstract class Food {
   private String name;
   private final LocalDate expiryDate;
   private final LocalDate createDate;
   private final Price price;
   private int discount = 0;

    public Food(String name, LocalDate expiryDate, LocalDate createDate, double price) {
        if (expiryDate.isBefore(createDate) || LocalDate.now().isBefore(createDate)) {
            throw new IllegalArgumentException("Проверье правильность указания даты производства и срока годности");
        }
        this.name = name;
        this.expiryDate = expiryDate;
        this.createDate = createDate;
        this.price = new Price(price);
    }

    public double getPercentLifeTimePassed() {
        long lifeTime = ChronoUnit.DAYS.between(this.getCreateDate(), this.getExpiryDate());
        long lifeTimePassed = ChronoUnit.DAYS.between(this.getCreateDate(), LocalDate.now());
        return (lifeTimePassed + 1) * 100.0 / (lifeTime + 1);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public double getPrice() {
        return price.price;
    }

    public double getPriceWithDiscount() {
        return price.priceWithDiscount;
    }

    public void setPrice(double price) {
        this.price.price = price;
        if (discount == 0) {
            this.price.priceWithDiscount = price;
        } else {
            setPriceWithDiscount();
        }
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
        setPriceWithDiscount();
    }

    private void setPriceWithDiscount() {
        int a = (int) (this.price.price * (100 - discount));
        this.price.priceWithDiscount = a * 0.01;
    }

    private class Price {

        private double price;
        private double priceWithDiscount;

        private Price(double price) {
            this.price = price;
            this.priceWithDiscount = price;
        }
    }
}
