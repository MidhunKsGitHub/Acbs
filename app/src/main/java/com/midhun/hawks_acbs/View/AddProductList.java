package com.midhun.hawks_acbs.View;

public class AddProductList {
    public AddProductList(String name, String imagename, String code, String customer, String category, String brand, String amount, String discount, String hsn, String latitude, String longitude, String tax, String description, String imagenames) {
        this.name = name;
        this.imagename = imagename;
        this.code = code;
        this.customer = customer;
        this.category = category;
        this.brand = brand;
        this.amount = amount;
        this.discount = discount;
        this.hsn = hsn;
        this.latitude = latitude;
        this.longitude = longitude;
        this.tax = tax;
        this.description = description;
        this.imagenames = imagenames;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagename() {
        return imagename;
    }

    public void setImagename(String imagename) {
        this.imagename = imagename;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getHsn() {
        return hsn;
    }

    public void setHsn(String hsn) {
        this.hsn = hsn;
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

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagenames() {
        return imagenames;
    }

    public void setImagenames(String imagenames) {
        this.imagenames = imagenames;
    }

    private String name,imagename,code,customer,category,brand,amount,discount,hsn,latitude,longitude,tax,description,imagenames;
}
