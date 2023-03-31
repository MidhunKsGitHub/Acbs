package com.midhun.hawks_acbs.View;

public class ImageDelete {

    private String r_status,r_message;

    public String getR_status() {
        return r_status;
    }

    public void setR_status(String r_status) {
        this.r_status = r_status;
    }

    public String getR_message() {
        return r_message;
    }

    public void setR_message(String r_message) {
        this.r_message = r_message;
    }

    public ImageDelete(String r_status, String r_message) {
        this.r_status = r_status;
        this.r_message = r_message;
    }
}
