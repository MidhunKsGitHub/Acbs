package com.midhun.hawks_acbs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class ChatFragmentContainer extends AppCompatActivity {
    FrameLayout fragment_container;
    ImageView img_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_fragment_container);
        fragment_container = findViewById(R.id.fragment_container);





        getSupportActionBar().hide();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decor = getWindow().getDecorView();

            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            //decor.setSystemUiVisibility(0);

        }


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_container, ChatFragment.class, null)
                    .commit();
        }
    }
//    private Drawable getColoredArrow() {
//        Drawable arrowDrawable = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
//        Drawable wrapped = DrawableCompat.wrap(arrowDrawable);
//
//        if (arrowDrawable != null && wrapped != null) {
//            // This should avoid tinting all the arrows
//            arrowDrawable.mutate();
//            DrawableCompat.setTint(wrapped, Color.BLACK);
//        }
//
//        return wrapped;
//    }
}