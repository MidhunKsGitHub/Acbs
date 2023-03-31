package com.midhun.hawks_acbs.Model;

import com.midhun.hawks_acbs.View.UserProducts;

import java.util.List;

public class UserProductsApiModel {
//    "data":{"currentPage":1,"pageData":[{"id":70,"name":"website",
//    "code":"tv3","image":"1660641329_d55ca446470d8cd1eb09.png",
//    "description":null,"amount":44000,"latitude":null,
//    "longitude":null,"categoryImg":null,"categoryName":"demo1","customer":null,"brand":null}
//    ,"total":87,"lastPage":9
    private UserProductsList data;
    private String total;
    private String lastPage;

    public UserProductsApiModel(UserProductsList data, String total, String lastPage) {
        this.data = data;
        this.total = total;
        this.lastPage = lastPage;
    }

    public UserProductsList getData() {
        return data;
    }
    public List<UserProducts> getPList() {
        return getData().pageDatas;
    }
    public void setData(UserProductsList data) {
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

    class UserProductsList {
        private String currentPage;
        private List<UserProducts> pageDatas;

        public String getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(String currentPage) {
            this.currentPage = currentPage;
        }

        public List<UserProducts> getPageDatas() {
            return pageDatas;
        }

        public void setPageDatas(List<UserProducts> pageDatas) {
            this.pageDatas= pageDatas;
        }
    }

}
