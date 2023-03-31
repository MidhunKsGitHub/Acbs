package com.midhun.hawks_acbs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.midhun.hawks_acbs.Util.MidhunUtils;

public class SettingsActivity extends AppCompatActivity {
  ImageView img_back;
  LinearLayout edit,changepswd,logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        img_back=findViewById(R.id.img_back);
        edit=findViewById(R.id.edit);
        changepswd=findViewById(R.id.changepswd);
        logout=findViewById(R.id.logout);


        getSupportActionBar().hide();
        MidhunUtils.setStatusBarIcon(SettingsActivity.this,false);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.purple_700));
        }
        img_back.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.white));


        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent();
                in.setClass(SettingsActivity.this,EditProfileActivity.class);
                startActivity(in);
            }
        });
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        changepswd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent();
                in.setClass(SettingsActivity.this,ChangePasswordActivity.class);
                startActivity(in);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MidhunUtils.showProgress(SettingsActivity.this);
                SharedPreferences sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("UID","");
                myEdit.commit();
                Handler h = new Handler();
                h.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent in = new Intent();
                        in.setClass(SettingsActivity.this,MainHomeActivity.class);
                        startActivity(in);
                    }
                },1500);

            }
        });
    }
}