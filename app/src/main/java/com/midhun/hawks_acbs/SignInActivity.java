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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.midhun.hawks_acbs.Model.MyProfile;
import com.midhun.hawks_acbs.Util.MidhunUtils;
import com.midhun.hawks_acbs.ViewModel.AuthenticateViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SignInActivity extends AppCompatActivity {

    EditText username, password;
    private TextView btn, register;
    LinearLayout bg_email, bg_password;
    ProgressBar progressBar;
    CardView login_btn;
    List<MyProfile> ulist;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor myEdit;
    ImageView icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        btn = findViewById(R.id.btn);
        bg_email = findViewById(R.id.bg_email);
        bg_password = findViewById(R.id.bg_password);
        progressBar = findViewById(R.id.progressBar);
        login_btn = findViewById(R.id.login_btn);
        register = findViewById(R.id.login);
        icon=findViewById(R.id.icon);
     //   icon.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.teal_700));

        MidhunUtils.setStatusBarIcon(SignInActivity.this, true);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
                finish();
            }
        });


        Objects.requireNonNull(getSupportActionBar()).hide();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        //round

        MidhunUtils.round(bg_email, 0xffffffff, 0xffffffff, 18f);
        MidhunUtils.round(bg_password, 0xffffffff, 0xffffffff, 18f);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkCredentials();
            }
        });

    }
    //check credentials


    private void checkCredentials() {
        if (username.getText().toString().trim().equals("") || username.getText().toString().trim().isEmpty()) {
            //email.setError("Ibvalid Username");
            MidhunUtils.showSnackbar(bg_email, getApplicationContext(), true, "Provide Username", "Okay", new MidhunUtils());
        } else if (password.getText().toString().trim().equals("") || password.getText().toString().trim().isEmpty()) {
            //password.setError("provide passwored");
            MidhunUtils.showSnackbar(bg_password, getApplicationContext(), true, "Provide password", "Okay", new MidhunUtils());

        } else {
            login_btn.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
            Handler h = new Handler();
            h.postDelayed(new Runnable() {
                @Override
                public void run() {

                    userAuthenticate();
                }
            }, 600);

        }

    }


    private void userAuthenticate() {
        AuthenticateViewModel model = ViewModelProviders.of(this).get(AuthenticateViewModel.class);
        ulist = new ArrayList<>();
        model.getProfile(username.getText().toString(), password.getText().toString())
                .observe(this, new Observer<List<MyProfile>>() {
                    @Override
                    public void onChanged(@Nullable List<MyProfile> uList) {
                        ulist.clear();
                        ulist.addAll(uList);

                        //categoryadapter.notifyDataSetChanged();

                        Log.e("Authenticate response", "" + ulist.get(0).getPassword());
                        Log.e("Authenticate response", "" + ulist.get(0).getType());

                        if (ulist.get(0).getType().equalsIgnoreCase("invalid")) {

                            Log.d("username signin", username.getText().toString());
                            Log.d("password signim", password.getText().toString());
                            //btn.setText(ulist.get(0).getError());
                            progressBar.setVisibility(View.GONE);
                            login_btn.setVisibility(View.VISIBLE);
                            //  MidhunUtils.showSnackbar(bg_email, getApplicationContext(), true, ulist.get(0).getError(), "Close",new MidhunUtils());

                            startActivity(new Intent(SignInActivity.this, SignInActivity.class));
                            Toast.makeText(SignInActivity.this, ulist.get(0).getError(), Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            String UID = ulist.get(0).getId();
                            String CITY= ulist.get(0).getLocation();

                            sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
                            myEdit = sharedPreferences.edit();
                            myEdit.putString("UID", UID);
                            myEdit.commit();

                            sharedPreferences = getSharedPreferences("location", Context.MODE_PRIVATE);
                            myEdit = sharedPreferences.edit();
                            myEdit.putString("city", CITY);
                            myEdit.commit();

                            progressBar.setVisibility(View.GONE);
                            login_btn.setVisibility(View.VISIBLE);
                            startActivity(new Intent(SignInActivity.this, MainHomeActivity.class));
                            finish();

                        }


                    }
                });
    }


}