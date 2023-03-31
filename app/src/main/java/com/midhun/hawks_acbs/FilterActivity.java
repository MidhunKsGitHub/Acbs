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
import com.midhun.hawks_acbs.Adapter.FilterAdapter;
import com.midhun.hawks_acbs.Util.MidhunUtils;
import com.midhun.hawks_acbs.View.Filter;
import com.midhun.hawks_acbs.ViewModel.FilterViewModel;

import java.util.ArrayList;
import java.util.List;

public class FilterActivity extends AppCompatActivity {
    List<Filter> filterList;
    FilterAdapter filterAdapter;
    RecyclerView recyclerView8;
    ImageView img_back, img_sort,img_search;
    TextView catname;
    private ShimmerFrameLayout mShimmerViewContainer;
    String latitude, longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_view);
        recyclerView8 = findViewById(R.id.recyclerView8);
        img_back = findViewById(R.id.img_back);
        catname = findViewById(R.id.catname);
        mShimmerViewContainer = findViewById(R.id.shimmer_view_container);
        img_sort = findViewById(R.id.img_sort);
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
      //  MidhunUtils.setStatusBarIcon(FilterActivity.this, true);
        catname.setText(getIntent().getExtras().getString("category_name"));
        catname.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.LEFT);

        //location
        latitude = MidhunUtils.localData(FilterActivity.this, "location", "lati");
        longitude = MidhunUtils.localData(FilterActivity.this, "location", "longi");


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
                in.setClass(FilterActivity.this, Sort_filter_Activity.class);
                in.putExtra("category", getIntent().getExtras().getString("category"));
                in.putExtra("category_name", getIntent().getExtras().getString("category_name"));
                startActivity(in);
                finish();
            }
        });
        recyclerView8.setHasFixedSize(true);
        filterList = new ArrayList<>();
        recyclerView8.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        filterAdapter = new FilterAdapter(getApplicationContext(), filterList);
        recyclerView8.setAdapter(filterAdapter);
        String max = getIntent().getExtras().getString("max");
        String min = getIntent().getExtras().getString("min");
        String cat = getIntent().getExtras().getString("category");
        ProductssPopulate(max, min, cat,latitude,longitude);


    }

    private void ProductssPopulate(String max, String min, String cat,String lati,String longi) {
        FilterViewModel model1 = ViewModelProviders.of(FilterActivity.this).get(FilterViewModel.class);
        filterList = new ArrayList<>();
        model1.getFilterList(max, min, cat,lati,longi).observe(FilterActivity.this, new Observer<List<Filter>>() {
            @Override
            public void onChanged(List<Filter> filter) {

                filterList.addAll(filter);

                filterAdapter = new FilterAdapter(FilterActivity.this, filterList);
                recyclerView8.setAdapter(filterAdapter);
                mShimmerViewContainer.setVisibility(View.GONE);
            }
        });
    }
}