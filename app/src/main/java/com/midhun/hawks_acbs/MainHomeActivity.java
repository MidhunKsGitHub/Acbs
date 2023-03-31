package com.midhun.hawks_acbs;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.midhun.hawks_acbs.Util.MidhunUtils;

public class MainHomeActivity extends AppCompatActivity {
    FrameLayout fragment_container;
    ImageView icon_home, icon_msg, icon_add, icon_fav, icon_user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);
        fragment_container = findViewById(R.id.fragment_container);
        icon_home = findViewById(R.id.icon_home);
        icon_msg = findViewById(R.id.icon_msg);
        icon_add = findViewById(R.id.icon_add);
        icon_fav = findViewById(R.id.icon_fav);
        icon_user = findViewById(R.id.icon_user);



        //material theme
        getSupportActionBar().hide();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decor = getWindow().getDecorView();

            //  decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            decor.setSystemUiVisibility(0);

        }


        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.purple_700));
        }

        //add fragment
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_container, HomeFragment.class, null)
                    .commit();
        }

        icon_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fragment_container, HomeFragment.class, null)
                        .addToBackStack(null)
                        .commit();

                icon_home.setImageResource(R.drawable.home);
                icon_msg.setImageDrawable(getDrawable(R.drawable.bell_outline));
                icon_fav.setImageDrawable(getDrawable(R.drawable.heart_outline));
                icon_user.setImageDrawable(getDrawable(R.drawable.user_outline));

                MidhunUtils.setStatusBarIcon(MainHomeActivity.this, false);
                MidhunUtils.makeStatusBar(MainHomeActivity.this, R.color.acbs_blue);
            }
        });


        icon_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (MidhunUtils.localData(MainHomeActivity.this, "login", "UID").isEmpty()) {
                    MidhunUtils.showSnackBarMsg(getApplicationContext(), icon_fav, "Signin to continue", "Close");
                } else {
                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.fragment_container, ChatFragment.class, null)
                            .addToBackStack(null)
                            .commit();

                    icon_msg.setImageResource(R.drawable.bell);
                    icon_home.setImageDrawable(getDrawable(R.drawable.home_outline));
                    icon_fav.setImageDrawable(getDrawable(R.drawable.heart_outline));
                    icon_user.setImageDrawable(getDrawable(R.drawable.user_outline));


                    MidhunUtils.setStatusBarIcon(MainHomeActivity.this, false);
                    MidhunUtils.makeStatusBar(MainHomeActivity.this, R.color.acbs_blue);
                }
            }
        });

        icon_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MidhunUtils.localData(MainHomeActivity.this, "login", "UID").isEmpty()) {
                    MidhunUtils.showSnackBarMsg(getApplicationContext(), icon_fav, "Signin to continue", "Close");
                } else {
                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.fragment_container, FavFragment.class, null)
                            .addToBackStack(null)
                            .commit();

                    icon_msg.setImageResource(R.drawable.bell_outline);
                    icon_home.setImageDrawable(getDrawable(R.drawable.home_outline));
                    icon_fav.setImageDrawable(getDrawable(R.drawable.heart));
                    icon_user.setImageDrawable(getDrawable(R.drawable.user_outline));

                    MidhunUtils.setStatusBarIcon(MainHomeActivity.this, false);
                    MidhunUtils.makeStatusBar(MainHomeActivity.this, R.color.acbs_blue);
                }
            }
        });
        icon_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MidhunUtils.localData(MainHomeActivity.this, "login", "UID").isEmpty()) {
                    startActivity(new Intent(getApplicationContext(), SignInActivity.class));
                } else {
                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.fragment_container, UserFragment.class, null)
                            .addToBackStack(null)
                            .commit();

                    icon_msg.setImageResource(R.drawable.bell_outline);
                    icon_home.setImageDrawable(getDrawable(R.drawable.home_outline));
                    icon_fav.setImageDrawable(getDrawable(R.drawable.heart_outline));
                    icon_user.setImageDrawable(getDrawable(R.drawable.user));

                    MidhunUtils.setStatusBarIcon(MainHomeActivity.this, false);
                    MidhunUtils.makeStatusBar(MainHomeActivity.this, R.color.acbs_blue);
                }
            }
        });

        icon_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MidhunUtils.localData(MainHomeActivity.this, "login", "UID").isEmpty()) {
                    startActivity(new Intent(getApplicationContext(), SignInActivity.class));
                } else {
                    startActivity(new Intent(getApplicationContext(), AddProductActivity.class));
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count == 0) {
            super.onBackPressed();
            finishAffinity();
            ;
        } else {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragment_container, HomeFragment.class, null)
                    .addToBackStack(null)
                    .commit();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            icon_home.setImageResource(R.drawable.home);
            icon_msg.setImageDrawable(getDrawable(R.drawable.bell_outline));
            icon_fav.setImageDrawable(getDrawable(R.drawable.heart_outline));
            icon_user.setImageDrawable(getDrawable(R.drawable.user_outline));

            MidhunUtils.setStatusBarIcon(MainHomeActivity.this, false);
            MidhunUtils.makeStatusBar(MainHomeActivity.this, R.color.purple_700);
        }
    }


}
