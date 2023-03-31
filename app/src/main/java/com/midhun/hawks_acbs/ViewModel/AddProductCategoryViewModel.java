package com.midhun.hawks_acbs.ViewModel;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.midhun.hawks_acbs.Api;
import com.midhun.hawks_acbs.Model.AddProductCategoriesApiModel;
import com.midhun.hawks_acbs.View.AddProductCategory;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddProductCategoryViewModel extends ViewModel {

    //this is the data that we will fetch asynchronously
    private MutableLiveData<List<AddProductCategory>> addProductList;

    //we will call this method to get the data
    public LiveData<List<AddProductCategory>> getAddProductCategories() {
        //if the list is null
        if (addProductList == null) {
            addProductList = new MutableLiveData<List<AddProductCategory>>();
            //we will load it asynchronously from server in this method
            loadCategories();
        }

        //finally we will return the list
        return addProductList;
    }


    //This method is using Retrofit to get the JSON data from URL
    private void loadCategories() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<AddProductCategoriesApiModel> call = api.getAddProductCategories(
        "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6Ikhhd2tzIFNvbHV0aW9ucyIsImFkbWluIjp0cnVlfQ.B5RoeVIIAVuLBbkq_yCFoZMmy4gXmiaIF-tSM3yPzQM","category","0","10");
        Log.e("Category response",""+call.toString());

        call.enqueue(new Callback<AddProductCategoriesApiModel>() {
            @SuppressLint("SuspiciousIndentation")
            @Override
            public void onResponse(Call<AddProductCategoriesApiModel> call, Response<AddProductCategoriesApiModel>response) {
                try {
                    //finally we are setting the list to our MutableLiveData
                    if (response.code() == 200)
                        Log.e("Category response plus", "" + response.body().getPageData());

                    addProductList.setValue(response.body().getPageData());
                }
                catch(Exception e){

                }
            }


            @Override
            public void onFailure(Call<AddProductCategoriesApiModel> call, Throwable t) {

            }
        });
    }

}