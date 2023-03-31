package com.midhun.hawks_acbs;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.midhun.hawks_acbs.Util.MidhunUtils;
import com.midhun.hawks_acbs.View.Profile;
import com.midhun.hawks_acbs.View.UserModel;
import com.midhun.hawks_acbs.ViewModel.ViewProfileViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EditProfileActivity extends AppCompatActivity {
    EditText edit_name, edit_email, edit_address, edit_location,edit_pincode,edit_city,edit_state;
    TextView btn;
    LinearLayout base;
    ProgressBar pb;
    ImageView img_back;
    List<Profile> profileList;
    LinearLayout  loading;
    ScrollView top;
    ProgressBar pb1;
    String UID;
    String latitude, longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        getSupportActionBar().hide();
        base = findViewById(R.id.base);
        edit_name = findViewById(R.id.edt_pwd);
        edit_email = findViewById(R.id.edt_cpwd);
        edit_address = findViewById(R.id.edit_address);
        edit_location = findViewById(R.id.edit_location);
        btn = findViewById(R.id.btn);
        pb = findViewById(R.id.pb);
        img_back = findViewById(R.id.img_back);
        loading = findViewById(R.id.loading);
        pb1 = findViewById(R.id.pb1);
        top = findViewById(R.id.top);
        edit_pincode=findViewById(R.id.edit_pincode);
        edit_city=findViewById(R.id.edit_city);
        edit_state=findViewById(R.id.edit_state);

        loading.setVisibility(View.VISIBLE);
        top.setVisibility(View.GONE);
        SharedPreferences sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
        UID = sharedPreferences.getString("UID", "");
        MidhunUtils.changeProgressBarColor(pb1, R.color.black, EditProfileActivity.this);


        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.purple_700));
        }

        img_back.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.white));


        //location

        latitude = MidhunUtils.localData(EditProfileActivity.this, "location", "lati");
        longitude = MidhunUtils.localData(EditProfileActivity.this, "location", "longi");


        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        ProductssPopulate(UID);
        MidhunUtils.setStatusBarIcon(EditProfileActivity.this, false);
        MidhunUtils.changeProgressBarColor(pb, R.color.white, EditProfileActivity.this);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (edit_name.getText().toString().isEmpty()) {
                    edit_name.setError("Name is empty");
                } else if (edit_email.getText().toString().isEmpty()) {
                    edit_email.setError("Email is empty");
                } else if (edit_address.getText().toString().isEmpty()) {
                    edit_address.setError("Address is empty");
                } else if (edit_location.getText().toString().isEmpty()) {
                    edit_location.setError("Location is empty");
                }

                else if (edit_city.getText().toString().isEmpty()) {
                    edit_city.setError("City is empty");
                } else if (edit_pincode.getText().toString().isEmpty()) {
                    edit_pincode.setError("Pincode is empty");
                } else if (edit_state.getText().toString().isEmpty()) {
                    edit_state.setError("State is empty");
                }else {
                    editProfile();
                    pb.setVisibility(View.VISIBLE);
                    btn.setVisibility(View.GONE);
                }

            }
        });


    }


    public void editProfile() {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);
        Call<UserModel> call = api.editUser("Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6Ikhhd2tzIFNvbHV0aW9ucyIsImFkbWluIjp0cnVlfQ.B5RoeVIIAVuLBbkq_yCFoZMmy4gXmiaIF-tSM3yPzQM",
                "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6Ikhhd2tzIFNvbHV0aW9ucyIsImFkbWluIjp0cnVlfQ.B5RoeVIIAVuLBbkq_yCFoZMmy4gXmiaIF-tSM3yPzQM", edit_name.getText().toString(), edit_email.getText().toString(), edit_address.getText().toString(), edit_location.getText().toString(), "",UID,edit_city.getText().toString(),edit_pincode.getText().toString(),edit_state.getText().toString(),latitude, longitude);

        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {

                if (response.code() == 200) {
                    Log.e("response editProfile", "" + response.body().getName());

                    //     Toast.makeText(EditProfileActivity.this, "sucess", Toast.LENGTH_SHORT).show();
                    // MidhunUtils.showSnackbar(base, EditProfileActivity.this, true, "Profile updated", "Okay", new MidhunUtils());
//                    btn.setVisibility(View.VISIBLE);
//                    pb.setVisibility(View.GONE);

                    SharedPreferences sharedPreferences = getSharedPreferences("location", Context.MODE_PRIVATE);
                    SharedPreferences.Editor myEdit = sharedPreferences.edit();

                    myEdit.putString("city", edit_city.getText().toString());
                    myEdit.putString("pincode", edit_pincode.getText().toString());
                    myEdit.putString("state", edit_state.getText().toString());
                    myEdit.commit();
                    MidhunUtils.showProgress(EditProfileActivity.this);
                    Handler h = new Handler();
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent in = new Intent();
                            in.setClass(EditProfileActivity.this, MainHomeActivity.class);
                            startActivity(in);
                        }
                    }, 600);

                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                btn.setVisibility(View.VISIBLE);
                pb.setVisibility(View.GONE);
                Toast.makeText(EditProfileActivity.this, "failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void ProductssPopulate(String UID) {
        ViewProfileViewModel model1 = ViewModelProviders.of(EditProfileActivity.this).get(ViewProfileViewModel.class);
        profileList = new ArrayList<>();
        model1.getUser(UID).observe(EditProfileActivity.this, new Observer<List<Profile>>() {
            @Override
            public void onChanged(List<Profile> profile) {

                profileList.addAll(profile);
                edit_name.setText(profileList.get(0).getName());
                edit_address.setText(profileList.get(0).getAddress());
                edit_email.setText(profileList.get(0).getEmail());
                edit_location.setText(profileList.get(0).getLocation());
                edit_city.setText(profileList.get(0).getCity());
                edit_pincode.setText(profileList.get(0).getPincode());
                edit_state.setText(profileList.get(0).getState());

                top.setVisibility(View.VISIBLE);
                loading.setVisibility(View.GONE);

            }
        });
    }
}