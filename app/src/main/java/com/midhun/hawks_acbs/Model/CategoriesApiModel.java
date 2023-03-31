package com.midhun.hawks_acbs.Model;

import com.midhun.hawks_acbs.View.Category;

import java.util.List;

public class CategoriesApiModel {
//    "currentPage":1,"pageData":[{"id":1,"category":"demo1","head":"demohead","image":null,"description":"demo","status":1}
    private String currentPage;
    private List<Category> pageData;
    private String status;

    public CategoriesApiModel(String currentPage, List<Category> pageData, String status) {
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

    public List<Category> getPageData() {
        return pageData;
    }

    public void setPageData(List<Category> pageData) {
        this.pageData = pageData;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
