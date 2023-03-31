package com.midhun.hawks_acbs.ViewModel;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.midhun.hawks_acbs.Api;
import com.midhun.hawks_acbs.Model.ProductsApiModel;
import com.midhun.hawks_acbs.View.Products;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductsViewModel extends ViewModel {
    private MutableLiveData<List<Products>> productsList;

    //we will call this method to get the data
    public LiveData<List<Products>>getProducts() {
        //if the list is null
        if (productsList == null) {
            productsList = new MutableLiveData<List<Products>>();

            //we will load it asynchronously from server in this method
            loadProducts();
        }

        //finally we will return the list
        return productsList;
    }


    //This method is using Retrofit to get the JSON data from URL
    private void loadProducts() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<ProductsApiModel> call = api.getProducts("Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6Ikhhd2tzIFNvbHV0aW9ucyIsImFkbWluIjp0cnVlfQ.B5RoeVIIAVuLBbkq_yCFoZMmy4gXmiaIF-tSM3yPzQM",
                "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6Ikhhd2tzIFNvbHV0aW9ucyIsImFkbWluIjp0cnVlfQ.B5RoeVIIAVuLBbkq_yCFoZMmy4gXmiaIF-tSM3yPzQM",
                "product","0","100");
        Log.e("Product response",""+call.toString());

        call.enqueue(new Callback<ProductsApiModel>() {
            @SuppressLint("SuspiciousIndentation")
            @Override
            public void onResponse(Call<ProductsApiModel> call, Response<ProductsApiModel> response) {

                Log.e("BrandsApiModel response",""+response.toString());
                //finally we are setting the list to our MutableLiveData
                if(response.code()==200) {
                    productsList.setValue(response.body().getPList());
//                    brandList.setValue(response.body().getPageData());
                }
                else if(response.code()==503) {

                }
                else{

                }
            }

            @Override
            public void onFailure(Call<ProductsApiModel> call, Throwable t) {

            }
        });
    }
}
