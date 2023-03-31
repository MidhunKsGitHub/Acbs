package com.midhun.hawks_acbs.View;

public class UserProducts {
    public UserProducts(String id, String name, String email, String address, String image, String latitude, String longitude, String location, String updated_at, String pid, String productName, String amount, String productImage, String categoryName) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.image = image;
        this.latitude = latitude;
        this.longitude = longitude;
        this.location = location;
        this.updated_at = updated_at;
        this.pid = pid;
        ProductName = productName;
        this.amount = amount;
        ProductImage = productImage;
        this.categoryName = categoryName;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getProductImage() {
        return ProductImage;
    }

    public void setProductImage(String productImage) {
        ProductImage = productImage;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    private String id,name,email,address,image,latitude,longitude,location,updated_at,pid,ProductName, amount,ProductImage,categoryName;
}
