package com.midhun.hawks_acbs;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.midhun.hawks_acbs.Adapter.AddProductBrandsAdapter;
import com.midhun.hawks_acbs.Adapter.AddProductCategoryAdapter;
import com.midhun.hawks_acbs.Util.MidhunUtils;
import com.midhun.hawks_acbs.Util.RecyclerItemClickListener;
import com.midhun.hawks_acbs.View.AddProductCategory;
import com.midhun.hawks_acbs.View.Brand;
import com.midhun.hawks_acbs.ViewModel.AddProductCategoryViewModel;
import com.midhun.hawks_acbs.ViewModel.BrandsViewModel;

import java.util.ArrayList;
import java.util.List;

public class AddProductActivity extends AppCompatActivity {
    List<AddProductCategory> addProductCategoryList;
    List<Brand> brandList;

    AddProductCategoryAdapter addProductCategoryAdapter;
    AddProductBrandsAdapter brandsAdapter;

    RecyclerView recyclerView7;
    RecyclerView recyclerView11;

    ImageView img_back;
    LinearLayout loading,base,brand,category;
    ProgressBar pb;

    String CATNAME;
    String CATID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        recyclerView7=findViewById(R.id.recyclerView7);
        img_back=findViewById(R.id.img_back);
        loading=findViewById(R.id.loading);
        base=findViewById(R.id.base);
        pb=findViewById(R.id.pb1);
        brand=findViewById(R.id.brand);
        category=findViewById(R.id.category);

        //visibility

        loading.setVisibility(View.VISIBLE);
        base.setVisibility(View.GONE);

        MidhunUtils.changeProgressBarColor(pb, R.color.acbs_bold,AddProductActivity.this);



        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        getSupportActionBar().hide();
        MidhunUtils.setStatusBarIcon(AddProductActivity.this,true);



        recyclerView7.setHasFixedSize(true);
       addProductCategoryList = new ArrayList<>();
        GridLayoutManager layoutManager1 = new GridLayoutManager(AddProductActivity.this, 3);
        recyclerView7.setLayoutManager(layoutManager1);
        addProductCategoryAdapter= new AddProductCategoryAdapter(AddProductActivity.this, addProductCategoryList);
        recyclerView7.setAdapter(addProductCategoryAdapter);
        CategoriesPopulate();

        //brand

        recyclerView11= findViewById(R.id.recyclerView11);
        recyclerView11.setHasFixedSize(true);
        brandList = new ArrayList<>();
        GridLayoutManager layoutManager2 = new GridLayoutManager(AddProductActivity.this, 4);
        recyclerView11.setLayoutManager(layoutManager2);
        brandsAdapter = new AddProductBrandsAdapter(AddProductActivity.this, brandList);
        recyclerView11.setAdapter(brandsAdapter);


        recyclerView7.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), recyclerView11 , new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                CATNAME= addProductCategoryList.get(position).getCategory();
                CATID= addProductCategoryList.get(position).getId();

                BrandsPopulate(addProductCategoryList.get(position).getId());
                category.setVisibility(View.GONE);
                loading.setVisibility(View.VISIBLE);
                base.setVisibility(View.GONE);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));

        recyclerView11.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), recyclerView11, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent in = new Intent();
                in.setClass(AddProductActivity.this, AddActivity.class);
                in.putExtra("category",CATNAME);
                in.putExtra("brand",brandList.get(position).getBrand());
                in.putExtra("brand_id",brandList.get(position).getId());
                in.putExtra("category_id",CATID);
                Intent intent = getIntent();

                if (intent.hasExtra("product_id")){
                  in.putExtra("product_id",getIntent().getExtras().getString("product_id"));
                    in.putExtra("category_name",getIntent().getExtras().getString("category_name"));
                    in.putExtra("brand_name",getIntent().getExtras().getString("brand_name"));
                    in.putExtra("price",getIntent().getExtras().getString("price"));
                    in.putExtra("desc",getIntent().getExtras().getString("desc"));
                    in.putExtra("title",getIntent().getExtras().getString("title"));
                }
                startActivity(in);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));
    }


    private void CategoriesPopulate() {

        AddProductCategoryViewModel model = ViewModelProviders.of(this).get(AddProductCategoryViewModel.class);
    addProductCategoryList = new ArrayList<>();
        model.getAddProductCategories().observe(AddProductActivity.this, new Observer<List<AddProductCategory>>() {
            @Override
            public void onChanged(@Nullable List<AddProductCategory> cList) {
                addProductCategoryList.addAll(cList);

                //categoryadapter.notifyDataSetChanged();

                Log.e("Category response", "" + addProductCategoryList.get(0).getCategory());
//                categoryadapter.set
         addProductCategoryAdapter= new AddProductCategoryAdapter(AddProductActivity.this, addProductCategoryList);
                recyclerView7.setAdapter(addProductCategoryAdapter);
                base.setVisibility(View.VISIBLE);
                loading.setVisibility(View.GONE);


            }
        });
    }

    private void BrandsPopulate(String category) {

        BrandsViewModel model3 = ViewModelProviders.of(this).get(BrandsViewModel.class);
        brandList = new ArrayList<>();
        model3.getBrands(category).observe(AddProductActivity.this, new Observer<List<Brand>>() {
            @Override
            public void onChanged(@Nullable List<Brand>cList) {
                brandList.addAll(cList);

                //categoryadapter.notifyDataSetChanged();


                brandsAdapter = new AddProductBrandsAdapter(AddProductActivity.this, brandList);
                recyclerView11.setAdapter(brandsAdapter);
                brand.setVisibility(View.VISIBLE);
                loading.setVisibility(View.GONE);
                base.setVisibility(View.VISIBLE);

            }
        });
    }
}