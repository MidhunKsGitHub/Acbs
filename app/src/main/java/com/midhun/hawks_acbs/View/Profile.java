package com.midhun.hawks_acbs.View;

public class Profile {

    private String id;
    private String name;
    private String password;
    private String address;
    private String email,city,pincode,state;


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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    private String image;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Profile(String id, String name, String password, String address, String email, String image, String mobile, String location, String latitude, String longitude, String pincode, String city, String state) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.address = address;
        this.email = email;
        this.image = image;
        this.mobile = mobile;
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
        this.pincode=pincode;
        this.city=city;
        this.state=state;
    }

    private String mobile;
    private String location;
    private String latitude;
    private String longitude;


}