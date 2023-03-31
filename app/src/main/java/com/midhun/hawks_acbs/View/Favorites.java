package com.midhun.hawks_acbs.View;

public class Favorites {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getWishlistId() {
        return wishlistId;
    }

    public void setWishlistId(String wishlistId) {
        this.wishlistId = wishlistId;
    }

    public Favorites(String id, String name, String image, String description, String customer, String category, String brand, String amount, String wishlistId) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.description = description;
        this.customer = customer;
        this.category = category;
        this.brand = brand;
        this.amount = amount;
        this.wishlistId = wishlistId;
    }

    private String id,name,image,description,customer,category,brand,amount,wishlistId;
}
