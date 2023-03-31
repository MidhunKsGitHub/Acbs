package com.midhun.hawks_acbs;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
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
import com.midhun.hawks_acbs.Adapter.HomeOnViewCategoryAdapter;
import com.midhun.hawks_acbs.View.Products;
import com.midhun.hawks_acbs.ViewModel.ProductsViewModel;

import java.util.ArrayList;
import java.util.List;

public class CatViewActivity extends AppCompatActivity {
    List<Products> productsList;
    HomeOnViewCategoryAdapter homeOnViewCategoryAdapter;
    RecyclerView recyclerView8;
    ImageView img_back, img_sort,img_search;
    TextView catname;
    private ShimmerFrameLayout mShimmerViewContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_view);
        recyclerView8 = findViewById(R.id.recyclerView8);
        img_back = findViewById(R.id.img_back);
        img_sort = findViewById(R.id.img_sort);
        catname = findViewById(R.id.catname);
        img_search=findViewById(R.id.img_search);
        mShimmerViewContainer = findViewById(R.id.shimmer_view_container);


        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.purple_700));
        }


        img_back.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.white));
        img_search.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.white));
        img_sort.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.white));

        getSupportActionBar().hide();
        //MidhunUtils.setStatusBarIcon(CatViewActivity.this, true);
        catname.setText(getIntent().getExtras().getString("category_name"));
        catname.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.LEFT);
        img_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent();
                in.setClass(CatViewActivity.this, SearchActivity.class);
                in.putExtra("name", "");
                startActivity(in);

            }
        });
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
                in.setClass(CatViewActivity.this, Sort_filter_Activity.class);
                in.putExtra("category", getIntent().getExtras().getString("id"));
                in.putExtra("category_name", getIntent().getExtras().getString("category_name"));
                startActivity(in);
                finish();
            }
        });

        recyclerView8.setHasFixedSize(true);
        productsList = new ArrayList<>();
        recyclerView8.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        homeOnViewCategoryAdapter = new HomeOnViewCategoryAdapter(getApplicationContext(), productsList);
        recyclerView8.setAdapter(homeOnViewCategoryAdapter);
        ProductssPopulate();


    }

    private void ProductssPopulate() {
        ProductsViewModel model1 = ViewModelProviders.of(CatViewActivity.this).get(ProductsViewModel.class);
        productsList = new ArrayList<>();
        model1.getProducts().observe(CatViewActivity.this, new Observer<List<Products>>() {
            @Override
            public void onChanged(List<Products> products) {

                productsList.addAll(products);

                try {
                    //categoryadapter.notifyDataSetChanged();
                    if (!getIntent().getExtras().getString("category_name").equalsIgnoreCase("all")) {
                        int Length = productsList.size();
                        int index = Length - 1;

                        for (int i = 0; i < Length; i++) {
                            Log.e("Products response", "" + productsList.get(index).getCategoryName());
                            if (productsList.get(index).getCategoryName().equals(getIntent().getExtras().getString("category_name"))) {

                            } else {
                                productsList.remove(index);
                            }
                            index--;
                        }
                    }

                } catch (Exception e) {
                    Log.d("productsList error", e.toString());
                }

                homeOnViewCategoryAdapter = new HomeOnViewCategoryAdapter(CatViewActivity.this, productsList);
                recyclerView8.setAdapter(homeOnViewCategoryAdapter);
                mShimmerViewContainer.setVisibility(View.GONE);
            }
        });
    }
}