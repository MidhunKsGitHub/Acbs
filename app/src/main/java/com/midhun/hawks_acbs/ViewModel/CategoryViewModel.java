package com.midhun.hawks_acbs.ViewModel;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.midhun.hawks_acbs.Api;
import com.midhun.hawks_acbs.View.Category;
import com.midhun.hawks_acbs.Model.CategoriesApiModel;

import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CategoryViewModel extends ViewModel {

    //this is the data that we will fetch asynchronously
    private MutableLiveData<List<Category>> categoryList;

    //we will call this method to get the data
    public LiveData<List<Category>> getCategories() {
        //if the list is null
        if (categoryList == null) {
            categoryList = new MutableLiveData<List<Category>>();
            //we will load it asynchronously from server in this method
            loadCategories();
        }

        //finally we will return the list
        return categoryList;
    }


    //This method is using Retrofit to get the JSON data from URL
    private void loadCategories() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<CategoriesApiModel> call = api.getCategories(
        "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6Ikhhd2tzIFNvbHV0aW9ucyIsImFkbWluIjp0cnVlfQ.B5RoeVIIAVuLBbkq_yCFoZMmy4gXmiaIF-tSM3yPzQM","category","0","8");
        Log.e("Category response",""+call.toString());

        call.enqueue(new Callback<CategoriesApiModel>() {
            @SuppressLint("SuspiciousIndentation")
            @Override
            public void onResponse(Call<CategoriesApiModel> call, Response<CategoriesApiModel>response) {
                try {
                    //finally we are setting the list to our MutableLiveData
                    if (response.code() == 200)
                        Log.e("Category response plus", "" + response.body().getPageData());

                    categoryList.setValue(response.body().getPageData());
                }
                catch(Exception e){

                }
            }


            @Override
            public void onFailure(Call<CategoriesApiModel> call, Throwable t) {

            }
        });
    }

}