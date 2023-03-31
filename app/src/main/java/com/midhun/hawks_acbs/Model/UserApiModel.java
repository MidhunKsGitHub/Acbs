package com.midhun.hawks_acbs.Model;

import com.midhun.hawks_acbs.View.UserModel;

public class UserApiModel {
    private String status;
    private String message;

    public UserApiModel(String status, String message, UserModel details) {
        this.status = status;
        this.message = message;
        this.details = details;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserModel getDetails() {
        return details;
    }

    public void setDetails(UserModel details) {
        this.details = details;
    }

    UserModel details;

}
