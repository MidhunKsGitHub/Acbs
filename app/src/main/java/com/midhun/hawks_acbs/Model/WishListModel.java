package com.midhun.hawks_acbs.Model;

public class WishListModel {
    private String userid;
    private String productid;
    private String name;
    private String status;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getProductid() {
        return productid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public WishListModel(String userid, String productid, String name, String status) {
        this.userid = userid;
        this.productid = productid;
        this.name=name;
        this.status=status;
    }
}
