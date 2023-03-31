package com.midhun.hawks_acbs.ViewModel;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.midhun.hawks_acbs.Api;
import com.midhun.hawks_acbs.Model.ViewProfileApiModel;
import com.midhun.hawks_acbs.View.Profile;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ViewProfileViewModel extends ViewModel {
    private MutableLiveData<List<Profile>> profileDetailList;

    //we will call this method to get the data
    public LiveData<List<Profile>>getUser(String UID) {
        //if the list is null
        if (profileDetailList == null) {
            profileDetailList = new MutableLiveData<List<Profile>>();

            //we will load it asynchronously from server in this method

            loadProducts(UID);
        }

        //finally we will return the list
        return profileDetailList;
    }


    //This method is using Retrofit to get the JSON data from URL
    private void loadProducts(String UID) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<ViewProfileApiModel> call = api.getUser("Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6Ikhhd2tzIFNvbHV0aW9ucyIsImFkbWluIjp0cnVlfQ.B5RoeVIIAVuLBbkq_yCFoZMmy4gXmiaIF-tSM3yPzQM",
                "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6Ikhhd2tzIFNvbHV0aW9ucyIsImFkbWluIjp0cnVlfQ.B5RoeVIIAVuLBbkq_yCFoZMmy4gXmiaIF-tSM3yPzQM", UID);

        call.enqueue(new Callback<ViewProfileApiModel>() {
            @SuppressLint("SuspiciousIndentation")
            @Override
            public void onResponse(Call<ViewProfileApiModel> call, Response<ViewProfileApiModel> response) {

                  Log.e("ViewProfile response", "" + response.toString());
                  //finally we are setting the list to our MutableLiveData
                  if (response.code() == 200) {
                      profileDetailList.setValue(response.body().getProList());
                      Log.d("response profile", "onResponse: "+response.body().getProList());

//
                  } else if (response.isSuccessful()) {
                      profileDetailList.setValue(response.body().getProList());
                  } else {

                  }

            }

            @Override
            public void onFailure(Call<ViewProfileApiModel> call, Throwable t) {
                Log.d("profile live error", "onFailure: ");
            }
        });
    }
}
