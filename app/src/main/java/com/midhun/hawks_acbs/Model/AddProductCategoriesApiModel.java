package com.midhun.hawks_acbs.Model;

import com.midhun.hawks_acbs.View.AddProductCategory;

import java.util.List;

public class AddProductCategoriesApiModel {
//    "currentPage":1,"pageData":[{"id":1,"category":"demo1","head":"demohead","image":null,"description":"demo","status":1}
    private String currentPage;
    private List<AddProductCategory> pageData;
    private String status;

    public AddProductCategoriesApiModel(String currentPage, List<AddProductCategory> pageData, String status) {
        this.currentPage = currentPage;
        this.pageData = pageData;
        this.status = status;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public List<AddProductCategory> getPageData() {
        return pageData;
    }

    public void setPageData(List<AddProductCategory> pageData) {
        this.pageData = pageData;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
