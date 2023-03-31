package com.midhun.hawks_acbs.ViewModel;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.midhun.hawks_acbs.Api;
import com.midhun.hawks_acbs.Model.FilterApiModel;
import com.midhun.hawks_acbs.View.Filter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FilterViewModel extends ViewModel {

    //this is the data that we will fetch asynchronously
    private MutableLiveData<List<Filter>> filterList;

    //we will call this method to get the data
    public LiveData<List<Filter>> getFilterList(String max,String min,String cat,String lati,String longi) {
        //if the list is null
        if (filterList == null) {
            filterList = new MutableLiveData<List<Filter>>();

            //we will load it asynchronously from server in this method
            loadCategories(max,min,cat,lati,longi);
        }

        //finally we will return the list
        return filterList;
    }


    //This method is using Retrofit to get the JSON data from URL
    private void loadCategories(String max,String min,String cat,String lati,String longi) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<FilterApiModel> call = api.getFilterList("Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6Ikhhd2tzIFNvbHV0aW9ucyIsImFkbWluIjp0cnVlfQ.B5RoeVIIAVuLBbkq_yCFoZMmy4gXmiaIF-tSM3yPzQM",
                "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6Ikhhd2tzIFNvbHV0aW9ucyIsImFkbWluIjp0cnVlfQ.B5RoeVIIAVuLBbkq_yCFoZMmy4gXmiaIF-tSM3yPzQM", max,min,cat,"",lati,longi);
        call.enqueue(new Callback<FilterApiModel>() {
            @SuppressLint("SuspiciousIndentation")
            @Override
            public void onResponse(Call<FilterApiModel> call, Response<FilterApiModel>response) {
                try {
                    //finally we are setting the list to our MutableLiveData
                    if (response.code() == 200)
                        Log.e("fav api response", "" + response.body().getFilterList());

                    filterList.setValue(response.body().getFilterList());
                }
                catch(Exception e){

                }
            }


            @Override
            public void onFailure(Call<FilterApiModel> call, Throwable t) {

            }
        });
    }

}