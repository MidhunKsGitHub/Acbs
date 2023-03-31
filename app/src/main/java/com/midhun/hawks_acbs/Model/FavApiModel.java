package com.midhun.hawks_acbs.Model;

import com.midhun.hawks_acbs.View.Favorites;

import java.util.List;

public class FavApiModel {
//    "data":{"currentPage":1,"pageData":[{"id":70,"name":"website",
//    "code":"tv3","image":"1660641329_d55ca446470d8cd1eb09.png",
//    "description":null,"amount":44000,"latitude":null,
//    "longitude":null,"categoryImg":null,"categoryName":"demo1","customer":null,"brand":null}
//    ,"total":87,"lastPage":9
    private FavList pagination;
    private String total;
    private String lastPage;

    public FavApiModel(FavList pagination, String total, String lastPage) {
        this.pagination = pagination;
        this.total = total;
        this.lastPage = lastPage;
    }

    public FavList getPagination() {
        return pagination;
    }
    public List<Favorites> getFList() {
        return getPagination().pageData;
    }
    public void setData(FavList pagination) {
        this.pagination = pagination;
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

    class FavList {
        private String currentPage;
        private List<Favorites> pageData;

        public String getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(String currentPage) {
            this.currentPage = currentPage;
        }

        public List<Favorites> getPageData() {
            return pageData;
        }

        public void setPageData(List<Favorites> pageData) {
            this.pageData = pageData;
        }
    }

}
