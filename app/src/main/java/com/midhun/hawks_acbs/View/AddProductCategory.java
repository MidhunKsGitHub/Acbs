package com.midhun.hawks_acbs.View;

public class AddProductCategory {
    private String id;
    private String category;
    private String head;
    private String image;
    private String description;
    private String status;

    public AddProductCategory(String id, String category, String head, String image, String description, String status) {
        this.id = id;
        this.category = category;
        this.head = head;
        this.image = image;
        this.description = description;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public String getHead() {
        return head;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
