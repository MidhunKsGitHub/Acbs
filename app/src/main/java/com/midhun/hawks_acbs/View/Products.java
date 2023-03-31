package com.midhun.hawks_acbs.View;

public class Products {
    private String id;
    private String name;
    private String website;
    private String code;
    private String tv3;
    private String image;
    private String description;
    private String amount;
    private String latitude;
    private String longitude;
    private String categoryImg;
    private String categoryName;
    private String customer;
    private String customerid;
    private String brand;
    private String location;
    private String city;

    public String getCustomerid() {
        return customerid;
    }

    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Products(String id, String name, String website, String code, String tv3,
                    String image, String description, String amount, String latitude, String longitude,
                    String categoryImg, String categoryName, String customer, String brand, String customerid, String location, String city) {
        this.id = id;
        this.name = name;
        this.website = website;
        this.code = code;
        this.tv3 = tv3;
        this.image = image;
        this.description = description;
        this.amount = amount;
        this.latitude = latitude;
        this.longitude = longitude;
        this.categoryImg = categoryImg;
        this.categoryName = categoryName;
        this.customer = customer;
        this.brand = brand;
        this.customerid=customerid;
        this.location=location;
        this.city=city;
    }

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

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTv3() {
        return tv3;
    }

    public void setTv3(String tv3) {
        this.tv3 = tv3;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getCategoryImg() {
        return categoryImg;
    }

    public void setCategoryImg(String categoryImg) {
        this.categoryImg = categoryImg;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
