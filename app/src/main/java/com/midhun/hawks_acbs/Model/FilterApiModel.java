package com.midhun.hawks_acbs.Model;

import com.midhun.hawks_acbs.View.Filter;

import java.util.List;

public class FilterApiModel {
//    "data":{"currentPage":1,"pageData":[{"id":70,"name":"website",
//    "code":"tv3","image":"1660641329_d55ca446470d8cd1eb09.png",
//    "description":null,"amount":44000,"latitude":null,
//    "longitude":null,"categoryImg":null,"categoryName":"demo1","customer":null,"brand":null}
//    ,"total":87,"lastPage":9
    private FilterList pagination;
    private String total;
    private String lastPage;

    public FilterApiModel(FilterList pagination, String total, String lastPage) {
        this.pagination = pagination;
        this.total = total;
        this.lastPage = lastPage;
    }

    public FilterList getPagination() {
        return pagination;
    }
    public List<Filter> getFilterList() {
        return getPagination().pageData;
    }
    public void setData(FilterList pagination) {
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

    class FilterList {
        private String currentPage;
        private List<Filter> pageData;

        public String getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(String currentPage) {
            this.currentPage = currentPage;
        }

        public List<Filter> getPageData() {
            return pageData;
        }

        public void setPageData(List<Filter> pageData) {
            this.pageData = pageData;
        }
    }

}
