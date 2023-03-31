package com.midhun.hawks_acbs.Model;

import com.midhun.hawks_acbs.View.Brand;

import java.util.ArrayList;
import java.util.List;

public class BrandsApiModel {
    Brandmodel data;

    public BrandsApiModel(Brandmodel data) {
        this.data = data;
    }

    public Brandmodel getData() {
        return data;
    }
    public List<Brand> getPageData() {
        return data.pageData;
    }
    public void setData(Brandmodel data) {
        this.data = data;
    }

    class Brandmodel{
    private String currentPage;
    private List<Brand> pageData;
    private String total;

    public Brandmodel(String currentPage, List<Brand> pageData, String status) {
        this.currentPage = currentPage;
        this.pageData = pageData;
        this.total = status;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public List<Brand> getPageData() {
        return pageData;
    }

    public void setPageData(List<Brand> pageData) {
        this.pageData=new ArrayList<>();
        this.pageData = pageData;
    }

    public String getStatus() {
        return total;
    }

    public void setStatus(String status) {
        this.total = status;
    }

}
}

