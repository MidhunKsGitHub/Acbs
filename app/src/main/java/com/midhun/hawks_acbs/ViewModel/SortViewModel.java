package com.midhun.hawks_acbs.ViewModel;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.midhun.hawks_acbs.Api;
import com.midhun.hawks_acbs.Model.SortApiModel;
import com.midhun.hawks_acbs.View.Sort;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SortViewModel extends ViewModel {

    //this is the data that we will fetch asynchronously
    private MutableLiveData<List<Sort>> sortList;

    //we will call this method to get the data
    public LiveData<List<Sort>> getSortList(String cat,String sort) {
        //if the list is null
        if (sortList == null) {
            sortList = new MutableLiveData<List<Sort>>();

            //we will load it asynchronously from server in this method
            loadCategories(cat,sort);
        }

        //finally we will return the list
        return sortList;
    }


    //This method is using Retrofit to get the JSON data from URL
    private void loadCategories(String cat,String sort) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<SortApiModel> call = api.getSortList("Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6Ikhhd2tzIFNvbHV0aW9ucyIsImFkbWluIjp0cnVlfQ.B5RoeVIIAVuLBbkq_yCFoZMmy4gXmiaIF-tSM3yPzQM",
                "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6Ikhhd2tzIFNvbHV0aW9ucyIsImFkbWluIjp0cnVlfQ.B5RoeVIIAVuLBbkq_yCFoZMmy4gXmiaIF-tSM3yPzQM", sort,cat,"0","10");
        call.enqueue(new Callback<SortApiModel>() {
            @SuppressLint("SuspiciousIndentation")
            @Override
            public void onResponse(Call<SortApiModel> call, Response<SortApiModel>response) {
                try {
                    //finally we are setting the list to our MutableLiveData
                    if (response.code() == 200)
                        Log.e("fav api response", "" + response.body().getSortList());

                    sortList.setValue(response.body().getSortList());
                }
                catch(Exception e){

                }
            }


            @Override
            public void onFailure(Call<SortApiModel> call, Throwable t) {

            }
        });
    }

}