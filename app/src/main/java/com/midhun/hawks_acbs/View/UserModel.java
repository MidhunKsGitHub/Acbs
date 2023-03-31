package com.midhun.hawks_acbs.View;

public class UserModel {
private String name,email,password,address,location,imageUrl,latitude,longitude,city,picode,state,status,measage,id;

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPicode() {
        return picode;
    }

    public void setPicode(String picode) {
        this.picode = picode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMeasage() {
        return measage;
    }

    public void setMeasage(String measage) {
        this.measage = measage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserModel(String name, String email, String password, String address, String location, String imageUrl, String latitude, String longitude, String city, String picode, String state, String status, String measage, String id) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.location = location;
        this.imageUrl = imageUrl;
        this.latitude = latitude;
        this.longitude = longitude;
        this.city=city;
        this.picode=picode;
        this.state=state;
        this.status=status;
        this.measage=measage;
        this.id=id;
    }
}
