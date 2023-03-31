package com.midhun.hawks_acbs.ViewModel;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.midhun.hawks_acbs.Api;
import com.midhun.hawks_acbs.Model.ViewProductsApiModel;
import com.midhun.hawks_acbs.View.ProductById;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ViewProductsViewModel extends ViewModel {
    private MutableLiveData<List<ProductById>> productImageList;

    //we will call this method to get the data
    public LiveData<List<ProductById>>getProductById(String id) {
        //if the list is null
        if (productImageList == null) {
            productImageList = new MutableLiveData<List<ProductById>>();

            //we will load it asynchronously from server in this method

            loadProducts(id);
        }

        //finally we will return the list
        return productImageList;
    }


    //This method is using Retrofit to get the JSON data from URL
    private void loadProducts(String id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<ViewProductsApiModel> call = api.getProductById("Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6Ikhhd2tzIFNvbHV0aW9ucyIsImFkbWluIjp0cnVlfQ.B5RoeVIIAVuLBbkq_yCFoZMmy4gXmiaIF-tSM3yPzQM",
                "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6Ikhhd2tzIFNvbHV0aW9ucyIsImFkbWluIjp0cnVlfQ.B5RoeVIIAVuLBbkq_yCFoZMmy4gXmiaIF-tSM3yPzQM",
                "product",id);
        Log.e("ProductById response",""+call.toString());

        call.enqueue(new Callback<ViewProductsApiModel>() {
            @SuppressLint("SuspiciousIndentation")
            @Override
            public void onResponse(Call<ViewProductsApiModel> call, Response<ViewProductsApiModel> response) {

                  Log.e("ViewProducts response", "" + response.toString());
                  //finally we are setting the list to our MutableLiveData
                  if (response.code() == 200) {
                      productImageList.setValue(response.body().getIList());

//
                  } else if (response.code() == 503) {

                  } else {

                  }

            }

            @Override
            public void onFailure(Call<ViewProductsApiModel> call, Throwable t) {

            }
        });
    }
}
