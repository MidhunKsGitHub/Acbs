package com.midhun.hawks_acbs;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;

import com.midhun.hawks_acbs.Util.MidhunUtils;

public class MainSplashActivity extends Activity {
    String UID;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_splash);
        MidhunUtils.setStatusBarIcon(MainSplashActivity.this,true);
        SharedPreferences sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
        UID = sharedPreferences.getString("UID", "");
        Handler h= new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {

             startActivity(new Intent(MainSplashActivity.this,MainHomeActivity.class));
             finish();
            }
        },1500);
    }
}
