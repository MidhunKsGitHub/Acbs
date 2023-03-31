package com.midhun.hawks_acbs.Model;

import com.midhun.hawks_acbs.View.Products;

import java.util.List;

public class ProductsApiModel {
//    "data":{"currentPage":1,"pageData":[{"id":70,"name":"website",
//    "code":"tv3","image":"1660641329_d55ca446470d8cd1eb09.png",
//    "description":null,"amount":44000,"latitude":null,
//    "longitude":null,"categoryImg":null,"categoryName":"demo1","customer":null,"brand":null}
//    ,"total":87,"lastPage":9
    private ProductsList data;
    private String total;
    private String lastPage;

    public ProductsApiModel(ProductsList data, String total, String lastPage) {
        this.data = data;
        this.total = total;
        this.lastPage = lastPage;
    }

    public ProductsList getData() {
        return data;
    }
    public List<Products> getPList() {
        return getData().pageData;
    }
    public void setData(ProductsList data) {
        this.data = data;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getLastPage() {
        return lastPage;
    }

    public void setLastPage(String lastPage) {
        this.lastPage = lastPage;
    }

    class ProductsList {
        private String currentPage;
        private List<Products> pageData;

        public String getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(String currentPage) {
            this.currentPage = currentPage;
        }

        public List<Products> getPageData() {
            return pageData;
        }

        public void setPageData(List<Products> pageData) {
            this.pageData = pageData;
        }
    }

}
