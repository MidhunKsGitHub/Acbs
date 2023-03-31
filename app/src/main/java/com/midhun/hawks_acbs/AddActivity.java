package com.midhun.hawks_acbs;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.midhun.hawks_acbs.Util.MidhunUtils;

import java.util.List;

public class AddActivity extends AppCompatActivity {
    EditText brand_name, category_name, adname, desc, price, customer;

    TextView next;
    ImageView img_back;
    LinearLayout base;
    String UID;
    List<String> detail_images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        getSupportActionBar().hide();
        MidhunUtils.setStatusBarIcon(AddActivity.this, true);
        brand_name = findViewById(R.id.edt_pwd);
        category_name = findViewById(R.id.category_name);
        brand_name.setText(getIntent().getExtras().getString("brand"));
        category_name.setText(getIntent().getExtras().getString("category"));
        img_back = findViewById(R.id.img_back);
        next = findViewById(R.id.next);
        price = findViewById(R.id.lprice);
        adname = findViewById(R.id.adname);
        desc = findViewById(R.id.desc);
        customer = findViewById(R.id.customer);
        base = findViewById(R.id.base);

        SharedPreferences sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
        UID = sharedPreferences.getString("UID", "");

        Intent intent = getIntent();

        if (intent.hasExtra("product_id")) {

            adname.setText(getIntent().getExtras().getString("title"));
            desc.setText(getIntent().getExtras().getString("desc"));
            price.setText(getIntent().getExtras().getString("price"));
        }

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (category_name.getText().toString().isEmpty()) {
                    MidhunUtils.showSnackBarMsg(getApplicationContext(), base, "Category needed", "dismiss");
                    category_name.setError("");
                } else if (brand_name.getText().toString().isEmpty()) {
                    MidhunUtils.showSnackBarMsg(getApplicationContext(), base, "Brand needed", "dismiss");
                    brand_name.setError("");
                } else if (adname.getText().toString().isEmpty()) {
                    MidhunUtils.showSnackBarMsg(getApplicationContext(), base, "Ad title needed", "dismiss");
                    adname.setError("");
                } else if (desc.getText().toString().isEmpty()) {
                    MidhunUtils.showSnackBarMsg(getApplicationContext(), base, "Ad description needed", "dismiss");
                    desc.setError("");
                }else if (price.getText().toString().isEmpty()) {
                    MidhunUtils.showSnackBarMsg(getApplicationContext(), base, "Ad price needed", "dismiss");
                    price.setError("");
                } else {





                    ProgressDialog progress = ProgressDialog.show(AddActivity.this, null, null, true);
                    progress.setContentView(R.layout.progress_layout);
                    progress.setCancelable(false);
                    progress.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

                    progress.show();


                    Handler h = new Handler();
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            progress.dismiss();
                            Intent in = new Intent();
                            in.putExtra("category", category_name.getText().toString());
                            in.putExtra("brand", brand_name.getText().toString());
                            in.putExtra("title", adname.getText().toString());
                            in.putExtra("desc", desc.getText().toString());
                            in.putExtra("customer", customer.getText().toString());
                            in.putExtra("price", price.getText().toString());
                            in.putExtra("category_id", getIntent().getExtras().getString("category_id"));
                            in.putExtra("brand_id", getIntent().getExtras().getString("brand_id"));

                            Intent intent = getIntent();
                            if (intent.hasExtra("product_id")) {
                                in.putExtra("product_id", getIntent().getExtras().getString("product_id"));
                            }
                            in.setClass(AddActivity.this, FileActivity.class);
                            startActivity(in);
                        }
                    }, 500);


                }
            }

        });
    }


}