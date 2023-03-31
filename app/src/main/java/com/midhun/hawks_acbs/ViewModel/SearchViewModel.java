package com.midhun.hawks_acbs.ViewModel;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.midhun.hawks_acbs.Api;
import com.midhun.hawks_acbs.Model.SearchApiModel;
import com.midhun.hawks_acbs.View.Search;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchViewModel extends ViewModel {

    //this is the data that we will fetch asynchronously
    private MutableLiveData<List<Search>> searchList;

    //we will call this method to get the data
    public LiveData<List<Search>> getSearch(String item) {
        //if the list is null
        if (searchList == null) {
            searchList= new MutableLiveData<List<Search>>();

            //we will load it asynchronously from server in this method
            loadCategories(item);
        }

        //finally we will return the list
        return searchList;
    }


    //This method is using Retrofit to get the JSON data from URL
    private void loadCategories(String item) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<SearchApiModel> call = api.getSearch("Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6Ikhhd2tzIFNvbHV0aW9ucyIsImFkbWluIjp0cnVlfQ.B5RoeVIIAVuLBbkq_yCFoZMmy4gXmiaIF-tSM3yPzQM",
                "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6Ikhhd2tzIFNvbHV0aW9ucyIsImFkbWluIjp0cnVlfQ.B5RoeVIIAVuLBbkq_yCFoZMmy4gXmiaIF-tSM3yPzQM", "0","10","","",item);
        call.enqueue(new Callback<SearchApiModel>() {
            @SuppressLint("SuspiciousIndentation")
            @Override
            public void onResponse(Call<SearchApiModel> call, Response<SearchApiModel>response) {
                try {
                    //finally we are setting the list to our MutableLiveData
                    if (response.code() == 200)
                        Log.e("fav api response", "" + response.body().getSList());

                    searchList.setValue(response.body().getSList());
                }
                catch(Exception e){

                }
            }


            @Override
            public void onFailure(Call<SearchApiModel> call, Throwable t) {

            }
        });
    }

}