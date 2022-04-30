package ru.job4j.design.lsp.foodstorage;

import java.time.LocalDate;

public abstract class Food {
   private String name;
   private LocalDate expiryDate;
   private final LocalDate createDate;
   private final Price price;

    public Food(String name, LocalDate expiryDate, LocalDate createDate, double price) {
        if (expiryDate.isBefore(createDate) || LocalDate.now().isBefore(createDate)) {
            throw new IllegalArgumentException("Проверье правильность указания даты производства и срока годности");
        }
        this.name = name;
        this.expiryDate = expiryDate;
        this.createDate = createDate;
        this.price = new Price(price);
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
        return price.getPrice();
    }

    public double getPriceWithDiscount() {
        return price.getPriceWithDiscount();
    }

    public int getDiscount() {
        return price.getDiscount();
    }

    public void setPrice(double price) {
        this.price.setPrice(price);
        Shop.setFoodPriceAndDiscount(this.price, this.price.getDiscount());
    }

    public void setDiscount(int discount) {
        Shop.setFoodPriceAndDiscount(this.price, discount);
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public class Price {

        private double price;
        private double priceWithDiscount;
        private int discount;

        private Price(double price) {
            this.price = price;
            this.priceWithDiscount = price;
            this.discount = 0;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public double getPriceWithDiscount() {
            return priceWithDiscount;
        }

        public void setPriceWithDiscount(double priceWithDiscount) {
            this.priceWithDiscount = priceWithDiscount;
        }


        public int getDiscount() {
            return discount;
        }

        public void setDiscount(int discount) {
            this.discount = discount;
        }
    }
}
