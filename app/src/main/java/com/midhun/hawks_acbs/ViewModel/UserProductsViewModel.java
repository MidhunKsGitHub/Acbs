package com.midhun.hawks_acbs.ViewModel;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.midhun.hawks_acbs.Api;
import com.midhun.hawks_acbs.Model.UserProductsApiModel;
import com.midhun.hawks_acbs.View.UserProducts;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserProductsViewModel extends ViewModel {
    private MutableLiveData<List<UserProducts>> userProductsList;

    //we will call this method to get the data
    public LiveData<List<UserProducts>>getUserProducts(String uid) {
        //if the list is null
        if (userProductsList == null) {
            userProductsList = new MutableLiveData<List<UserProducts>>();

            //we will load it asynchronously from server in this method
            loadProducts(uid);
        }

        //finally we will return the list
        return userProductsList;
    }


    //This method is using Retrofit to get the JSON data from URL
    private void loadProducts(String uid) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<UserProductsApiModel> call = api.getUserProducts("Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6Ikhhd2tzIFNvbHV0aW9ucyIsImFkbWluIjp0cnVlfQ.B5RoeVIIAVuLBbkq_yCFoZMmy4gXmiaIF-tSM3yPzQM",
                "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6Ikhhd2tzIFNvbHV0aW9ucyIsImFkbWluIjp0cnVlfQ.B5RoeVIIAVuLBbkq_yCFoZMmy4gXmiaIF-tSM3yPzQM",
                "0","100",uid);
        Log.e("Product response",""+call.toString());

        call.enqueue(new Callback<UserProductsApiModel>() {
            @SuppressLint("SuspiciousIndentation")
            @Override
            public void onResponse(Call<UserProductsApiModel> call, Response<UserProductsApiModel> response) {

                Log.e("BrandsApiModel response",""+response.toString());
                //finally we are setting the list to our MutableLiveData
                if(response.code()==200) {
                    userProductsList.setValue(response.body().getPList());
//                    brandList.setValue(response.body().getPageData());
                }
                else if(response.code()==503) {

                }
                else{

                }
            }

            @Override
            public void onFailure(Call<UserProductsApiModel> call, Throwable t) {

            }
        });
    }
}
