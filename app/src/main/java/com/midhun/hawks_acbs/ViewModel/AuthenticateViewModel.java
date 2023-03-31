package com.midhun.hawks_acbs.ViewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.midhun.hawks_acbs.Api;
import com.midhun.hawks_acbs.Model.MyProfile;

import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AuthenticateViewModel extends ViewModel {
    //this is the data that we will fetch asynchronously
    private MutableLiveData<List<MyProfile>> List;

    //we will call this method to get the data
    public LiveData<List<MyProfile>> getProfile(String username,String password) {
        //if the list is null
        if (List == null) {
            List = new MutableLiveData<List<MyProfile>>();
            //we will load it asynchronously from server in this method
            loadCategories(username, password);
        }

        //finally we will return the list
        return List;
    }


    //This method is using Retrofit to get the JSON data from URL
    private void loadCategories(String username,String password) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<List<MyProfile>> call = api.authenticate("Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6Ikhhd2tzIFNvbHV0aW9ucyIsImFkbWluIjp0cnVlfQ.B5RoeVIIAVuLBbkq_yCFoZMmy4gXmiaIF-tSM3yPzQM",
                "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6Ikhhd2tzIFNvbHV0aW9ucyIsImFkbWluIjp0cnVlfQ.B5RoeVIIAVuLBbkq_yCFoZMmy4gXmiaIF-tSM3yPzQM",username,password);
        Log.e("Call response",""+call.toString());

        call.enqueue(new Callback<List<MyProfile>>() {
            @Override
            public void onResponse(Call<List<MyProfile>> call, Response<List<MyProfile>>response) {
                Log.e("response",""+response.toString());
                //finally we are setting the list to our MutableLiveData
                if(response.code()==200) {
                    List.setValue(response.body());
                    Log.e("response Useri",""+response.body().get(0).getId());
                }
            }

            @Override
            public void onFailure(Call<List<MyProfile>> call, Throwable t) {
                Log.e("error response",""+t.toString());


            }
        });
    }
}
