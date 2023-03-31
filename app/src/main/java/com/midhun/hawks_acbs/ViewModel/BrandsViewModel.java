package com.midhun.hawks_acbs.ViewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.midhun.hawks_acbs.Api;
import com.midhun.hawks_acbs.View.Brand;
import com.midhun.hawks_acbs.Model.BrandsApiModel;

import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BrandsViewModel extends ViewModel {
    //this is the data that we will fetch asynchronously
//    private MutableLiveData<BrandsApiModel> brandsList;
    private MutableLiveData<List<Brand>> brandList;
    //we will call this method to get the data
    public LiveData<List<Brand>> getBrands(String category) {
        //if the list is null
        if (brandList == null) {
            brandList = new MutableLiveData<List<Brand>>();
//            brandsList = new MutableLiveData<BrandsApiModel>();
            //we will load it asynchronously from server in this method
            loadBrands(category);
        }

        //finally we will return the list
        return brandList;
    }


    //This method is using Retrofit to get the JSON data from URL
    private void loadBrands(String category) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<BrandsApiModel> call = api.getBrands("Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6Ikhhd2tzIFNvbHV0aW9ucyIsImFkbWluIjp0cnVlfQ.B5RoeVIIAVuLBbkq_yCFoZMmy4gXmiaIF-tSM3yPzQM",
                "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6Ikhhd2tzIFNvbHV0aW9ucyIsImFkbWluIjp0cnVlfQ.B5RoeVIIAVuLBbkq_yCFoZMmy4gXmiaIF-tSM3yPzQM",
                "brand","0","8",category);
        Log.e("Category response",""+call.toString());

        call.enqueue(new Callback<BrandsApiModel>() {
            @Override
            public void onResponse(Call<BrandsApiModel> call, Response<BrandsApiModel> response) {
             //   Log.e("BrandsApiModel response",""+(response.body().getPageData()));
              //  Log.e("BrandsApiModel response",""+response.toString());
                //finally we are setting the list to our MutableLiveData
                if(response.code()==200) {
                    try {
                        brandList.setValue(response.body().getPageData());
                    }
                    catch (Exception e){
                        Log.d("brand err", e.toString());
                    }
                }
                else if(response.code()==503) {

                }
                else{

                }
            }

            @Override
            public void onFailure(Call<BrandsApiModel> call, Throwable t) {

            }
        });
    }
}
