package com.midhun.hawks_acbs;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.midhun.hawks_acbs.Util.MidhunUtils;
import com.midhun.hawks_acbs.View.Pwd;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChangePasswordActivity extends AppCompatActivity {
    EditText edt_pwd, edt_cpwd;
    TextView btn;
    String UID;
  List<Pwd> pwdList;
ImageView img_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        edt_pwd = findViewById(R.id.edt_pwd);
        edt_cpwd = findViewById(R.id.edt_cpwd);
        btn = findViewById(R.id.btn);
        img_back=findViewById(R.id.img_back);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        getSupportActionBar().hide();


        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.purple_700));
        }
        img_back.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.white));

        MidhunUtils.setStatusBarIcon(ChangePasswordActivity.this, false);
        SharedPreferences sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
        UID = sharedPreferences.getString("UID", "");
        pwdList=new ArrayList<>();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (edt_pwd.getText().toString().isEmpty()) {
                    Toast.makeText(ChangePasswordActivity.this, "Password can't be empty", Toast.LENGTH_SHORT).show();
                } else if (edt_cpwd.getText().toString().isEmpty()) {
                    Toast.makeText(ChangePasswordActivity.this, "Password can't be empty", Toast.LENGTH_SHORT).show();
                } else if (!edt_pwd.getText().toString().equalsIgnoreCase(edt_cpwd.getText().toString())) {
                    Toast.makeText(ChangePasswordActivity.this, "Password didn't match", Toast.LENGTH_SHORT).show();
                } else {
                    changePassword();
                    Toast.makeText(ChangePasswordActivity.this, "Please wait for a while", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void changePassword() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);
        Call<Pwd> call = api.changePwd("Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6Ikhhd2tzIFNvbHV0aW9ucyIsImFkbWluIjp0cnVlfQ.B5RoeVIIAVuLBbkq_yCFoZMmy4gXmiaIF-tSM3yPzQM",
                "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6Ikhhd2tzIFNvbHV0aW9ucyIsImFkbWluIjp0cnVlfQ.B5RoeVIIAVuLBbkq_yCFoZMmy4gXmiaIF-tSM3yPzQM", edt_pwd.getText().toString(), UID);
        call.enqueue(new Callback<Pwd>() {
            @Override
            public void onResponse(Call<Pwd> call, Response<Pwd> response) {

                if (response.code() == 200) {
                    Toast.makeText(ChangePasswordActivity.this, "Password change successfully", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Pwd> call, Throwable t) {

                Toast.makeText(ChangePasswordActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}