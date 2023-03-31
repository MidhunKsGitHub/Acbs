package com.midhun.hawks_acbs.View;

public class ProductById {
    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    private String name;
    private String image;
    private String amount;
    private String imageId;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserImage() {
        return UserImage;
    }

    public void setUserImage(String userImage) {
        UserImage = userImage;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public ProductById(String name, String description, String image, String amount, String UserImage, String User, String location, String created, String MultiImages, String categoryName, String brand, String mobile, String id, String imageId) {
        this.name = name;
        this.description = description;
        this.image=image;
        this.amount=amount;
        this.UserImage=UserImage;
        this.User=User;
        this.location=location;
        this.created=created;
        this.MultiImages=MultiImages;
        this.categoryName=categoryName;
        this.brand=brand;
        this.mobile=mobile;
        this.id=id;
        this.imageId=imageId;
    }

    public String getMultiImages() {
        return MultiImages;
    }

    public void setMultiImages(String multiImages) {
        MultiImages = multiImages;
    }

    private  String description;
    private String UserImage;
    private String User;
    private String location;
    private String created;
    private String MultiImages;
    private String categoryName;
    private String brand;
    private String mobile;
    private String id;
    private String email;
    private String address;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
