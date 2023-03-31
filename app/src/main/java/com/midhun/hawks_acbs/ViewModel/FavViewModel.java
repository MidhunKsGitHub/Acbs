package com.midhun.hawks_acbs.ViewModel;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.midhun.hawks_acbs.Api;
import com.midhun.hawks_acbs.Model.FavApiModel;
import com.midhun.hawks_acbs.View.Favorites;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FavViewModel extends ViewModel {

    //this is the data that we will fetch asynchronously
    private MutableLiveData<List<Favorites>> favoritesList;

    //we will call this method to get the data
    public LiveData<List<Favorites>> getWishList(String UID) {
        //if the list is null
        if (favoritesList == null) {
            favoritesList = new MutableLiveData<List<Favorites>>();

            //we will load it asynchronously from server in this method
            loadCategories(UID);
        }

        //finally we will return the list
        return favoritesList;
    }


    //This method is using Retrofit to get the JSON data from URL
    private void loadCategories(String UID) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<FavApiModel> call = api.getwishList("Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6Ikhhd2tzIFNvbHV0aW9ucyIsImFkbWluIjp0cnVlfQ.B5RoeVIIAVuLBbkq_yCFoZMmy4gXmiaIF-tSM3yPzQM",
                "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6Ikhhd2tzIFNvbHV0aW9ucyIsImFkbWluIjp0cnVlfQ.B5RoeVIIAVuLBbkq_yCFoZMmy4gXmiaIF-tSM3yPzQM", UID);
        call.enqueue(new Callback<FavApiModel>() {
            @SuppressLint("SuspiciousIndentation")
            @Override
            public void onResponse(Call<FavApiModel> call, Response<FavApiModel>response) {
                try {
                    //finally we are setting the list to our MutableLiveData
                    if (response.code() == 200)
                        Log.e("fav api response", "" + response.body().getFList());

                    favoritesList.setValue(response.body().getFList());
                }
                catch(Exception e){

                }
            }


            @Override
            public void onFailure(Call<FavApiModel> call, Throwable t) {

            }
        });
    }

}