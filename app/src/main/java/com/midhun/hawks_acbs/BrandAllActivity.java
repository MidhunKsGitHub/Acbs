package com.midhun.hawks_acbs;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.midhun.hawks_acbs.Adapter.HomeBrandsAdapter;
import com.midhun.hawks_acbs.Util.MidhunUtils;
import com.midhun.hawks_acbs.View.Brand;
import com.midhun.hawks_acbs.ViewModel.BrandsViewModel;

import java.util.ArrayList;
import java.util.List;

public class BrandAllActivity extends AppCompatActivity {
    RecyclerView recyclerView4;
    List<Brand> brandList;
    HomeBrandsAdapter brandsAdapter;
    ImageView img_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_all);
        recyclerView4 = findViewById(R.id.recyclerview4);
        img_back=findViewById(R.id.img_back);
        getSupportActionBar().hide();
        MidhunUtils.setStatusBarIcon(BrandAllActivity.this,true);
        recyclerView4.setHasFixedSize(true);
        brandList = new ArrayList<>();
        GridLayoutManager layoutManager2 = new GridLayoutManager(BrandAllActivity.this, 4);
        recyclerView4.setLayoutManager(layoutManager2);
        brandsAdapter = new HomeBrandsAdapter(BrandAllActivity.this, brandList);
        recyclerView4.setAdapter(brandsAdapter);
        BrandsPopulate();

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void BrandsPopulate() {

        BrandsViewModel model3 = ViewModelProviders.of(this).get(BrandsViewModel.class);
        brandList = new ArrayList<>();
        model3.getBrands("").observe(BrandAllActivity.this, new Observer<List<Brand>>() {
            @Override
            public void onChanged(@Nullable List<Brand> cList) {
                brandList.addAll(cList);

                //categoryadapter.notifyDataSetChanged();


                brandsAdapter = new HomeBrandsAdapter(BrandAllActivity.this, brandList);
                recyclerView4.setAdapter(brandsAdapter);
               // mShimmerViewContainer2.setVisibility(View.GONE);
            }
        });
    }
}