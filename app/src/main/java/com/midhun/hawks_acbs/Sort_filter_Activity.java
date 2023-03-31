package com.midhun.hawks_acbs;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.midhun.hawks_acbs.Util.MidhunUtils;

public class Sort_filter_Activity extends AppCompatActivity {
    LinearLayout budget, sort, date, hprice, lprice, apply, clear, t, m, d, budget_layout, sort_layout;
    EditText max, min;
    String selected = "bydate";
    String type = "sort";
    TextView date_txt, hprice_txt, lprice_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort);
        getSupportActionBar().hide();
        MidhunUtils.setStatusBarIcon(Sort_filter_Activity.this, true);

        budget = findViewById(R.id.budget);
        sort = findViewById(R.id.sort);
        date = findViewById(R.id.date);
        hprice = findViewById(R.id.hprice);
        lprice = findViewById(R.id.lprice);
        apply = findViewById(R.id.apply);
        clear = findViewById(R.id.clear);
        budget_layout = findViewById(R.id.budget_layout);
        sort_layout = findViewById(R.id.sort_layout);
        max = findViewById(R.id.max);
        min = findViewById(R.id.min);
        date_txt = findViewById(R.id.date_txt);
        hprice_txt = findViewById(R.id.hprice_txt);
        lprice_txt = findViewById(R.id.lprice_txt);

        MidhunUtils.round(date, 0xff25b3bf, 0xff25b3bf, 15f);
        MidhunUtils.round(hprice, 0xffF5F6F9, 0xffF5F6F9, 15f);
        MidhunUtils.round(lprice, 0xffF5F6F9, 0xffF5F6F9, 15f);
        MidhunUtils.round(max, 0xffF5F6F9, 0xffF5F6F9, 15f);
        MidhunUtils.round(min, 0xffF5F6F9, 0xffF5F6F9, 15f);
        budget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sort.setBackgroundColor(getResources().getColor(R.color.acbs_white));
                budget.setBackgroundColor(getResources().getColor(R.color.white));
                budget_layout.setVisibility(View.VISIBLE);
                sort_layout.setVisibility(View.GONE);
                type = "budget";
            }
        });

        sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                budget.setBackgroundColor(getResources().getColor(R.color.acbs_white));
                sort.setBackgroundColor(getResources().getColor(R.color.white));
                sort_layout.setVisibility(View.VISIBLE);
                budget_layout.setVisibility(View.GONE);
                type = "sort";
            }
        });
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MidhunUtils.showProgress(Sort_filter_Activity.this);
                Handler h = new Handler();
                h.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (type.equalsIgnoreCase("sort")) {


                            Intent in = new Intent();
                            in.setClass(Sort_filter_Activity.this, SortActivity.class);
                            in.putExtra("category",getIntent().getExtras().getString("category"));
                            in.putExtra("category_name",getIntent().getExtras().getString("category_name"));
                            in.putExtra("sort",selected);
                            startActivity(in);
                            finish();
                        }
                        else{
                            Intent in = new Intent();
                            in.setClass(Sort_filter_Activity.this, FilterActivity.class);
                            in.putExtra("category",getIntent().getExtras().getString("category"));
                            in.putExtra("category_name",getIntent().getExtras().getString("category_name"));
                            in.putExtra("max",max.getText().toString());
                            in.putExtra("min",min.getText().toString());
                            startActivity(in);
                            finish();
                        }
                    }
                }, 300);
            }

        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MidhunUtils.round(date, 0xff25b3bf, 0xff25b3bf, 15f);
                MidhunUtils.round(hprice, 0xffF5F6F9, 0xffF5F6F9, 15f);
                MidhunUtils.round(lprice, 0xffF5F6F9, 0xffF5F6F9, 15f);
                date_txt.setTextColor(getResources().getColor(R.color.white));
                hprice_txt.setTextColor(getResources().getColor(R.color.black_light));
                lprice_txt.setTextColor(getResources().getColor(R.color.black_light));
                selected = "bydate";
                max.setText("");
                min.setText("");
            }
        });

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MidhunUtils.round(date, 0xff25b3bf, 0xff25b3bf, 15f);
                MidhunUtils.round(hprice, 0xffF5F6F9, 0xffF5F6F9, 15f);
                MidhunUtils.round(lprice, 0xffF5F6F9, 0xffF5F6F9, 15f);
                date_txt.setTextColor(getResources().getColor(R.color.white));
                hprice_txt.setTextColor(getResources().getColor(R.color.black_light));
                lprice_txt.setTextColor(getResources().getColor(R.color.black_light));
                selected = "bydate";
            }
        });


        hprice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MidhunUtils.round(hprice, 0xff25b3bf, 0xff25b3bf, 15f);
                MidhunUtils.round(date, 0xffF5F6F9, 0xffF5F6F9, 15f);
                MidhunUtils.round(lprice, 0xffF5F6F9, 0xffF5F6F9, 15f);
                hprice_txt.setTextColor(getResources().getColor(R.color.white));
                date_txt.setTextColor(getResources().getColor(R.color.black_light));
                lprice_txt.setTextColor(getResources().getColor(R.color.black_light));
                selected = "hightolow";
            }
        });

        lprice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MidhunUtils.round(lprice, 0xff25b3bf, 0xff25b3bf, 15f);
                MidhunUtils.round(hprice, 0xffF5F6F9, 0xffF5F6F9, 15f);
                MidhunUtils.round(date, 0xffF5F6F9, 0xffF5F6F9, 15f);
                lprice_txt.setTextColor(getResources().getColor(R.color.white));
                date_txt.setTextColor(getResources().getColor(R.color.black_light));
                hprice_txt.setTextColor(getResources().getColor(R.color.black_light));
                selected = "lowtohigh";
            }
        });
    }
}