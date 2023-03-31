package com.midhun.hawks_acbs;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.midhun.hawks_acbs.Adapter.SortAdapter;
import com.midhun.hawks_acbs.View.Sort;
import com.midhun.hawks_acbs.ViewModel.SortViewModel;

import java.util.ArrayList;
import java.util.List;

public class SortActivity extends AppCompatActivity {
    List<Sort> sortList;
    SortAdapter sortAdapter;
    RecyclerView recyclerView8;
    ImageView img_back,img_sort,img_search;
    TextView catname;
    private ShimmerFrameLayout mShimmerViewContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_view);
        recyclerView8 = findViewById(R.id.recyclerView8);
        img_back=findViewById(R.id.img_back);
        catname=findViewById(R.id.catname);
        mShimmerViewContainer = findViewById(R.id.shimmer_view_container);
        img_sort=findViewById(R.id.img_sort);
        img_search=findViewById(R.id.img_search);


        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.purple_700));
        }


        img_search.setVisibility(View.GONE);
        img_back.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.white));
        img_search.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.white));
        img_sort.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.white));

        getSupportActionBar().hide();
        //MidhunUtils.setStatusBarIcon(SortActivity.this,true);
        catname.setText(getIntent().getExtras().getString("category_name"));
        catname.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.LEFT);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        img_sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent();
                in.setClass(SortActivity.this,Sort_filter_Activity.class);
                in.putExtra("category",getIntent().getExtras().getString("category"));
                in.putExtra("category_name",getIntent().getExtras().getString("category_name"));
                startActivity(in);
                finish();
            }
        });
        recyclerView8.setHasFixedSize(true);
      sortList = new ArrayList<>();
        recyclerView8.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
     sortAdapter = new SortAdapter(getApplicationContext(), sortList);
        recyclerView8.setAdapter(sortAdapter);
        String cat=getIntent().getExtras().getString("category");
        String sort=getIntent().getExtras().getString("sort");
        ProductssPopulate(cat,sort);


    }

    private void ProductssPopulate(String cat,String sort) {
        SortViewModel model1 = ViewModelProviders.of(SortActivity.this).get(SortViewModel.class);
        sortList = new ArrayList<>();
        model1.getSortList(cat,sort).observe(SortActivity.this, new Observer<List<Sort>>() {
            @Override
            public void onChanged(List<Sort> sort) {

                sortList.addAll(sort);

                sortAdapter = new SortAdapter(SortActivity.this, sortList);
                recyclerView8.setAdapter(sortAdapter);
                 mShimmerViewContainer.setVisibility(View.GONE);
            }
        });
    }
}