package com.midhun.hawks_acbs;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.midhun.hawks_acbs.Adapter.HomeCategoryAdapter;
import com.midhun.hawks_acbs.Util.MidhunUtils;
import com.midhun.hawks_acbs.View.Category;
import com.midhun.hawks_acbs.ViewModel.CategoryViewModel;

import java.util.ArrayList;
import java.util.List;

public class CatAllActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Category> categoryList;
    HomeCategoryAdapter categoryadapter;
    ImageView img_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_all);
        img_back=findViewById(R.id.img_back);
        getSupportActionBar().hide();
        MidhunUtils.setStatusBarIcon(CatAllActivity.this,true);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        categoryList = new ArrayList<>();
        GridLayoutManager layoutManager1 = new GridLayoutManager(CatAllActivity.this, 4);
        recyclerView.setLayoutManager(layoutManager1);
        categoryadapter = new HomeCategoryAdapter(CatAllActivity.this, categoryList);
        recyclerView.setAdapter(categoryadapter);
        CategoriesPopulate();

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void CategoriesPopulate() {

        CategoryViewModel model = ViewModelProviders.of(this).get(CategoryViewModel.class);
        categoryList = new ArrayList<>();
        model.getCategories().observe(CatAllActivity.this, new Observer<List<Category>>() {
            @Override
            public void onChanged(@Nullable List<Category> cList) {
                categoryList.add(new Category("", "All", "0", "", "", ""));
                categoryList.addAll(cList);

                //categoryadapter.notifyDataSetChanged();

                Log.e("Category response", "" + categoryList.get(0).getCategory());
//                categoryadapter.set
                categoryadapter = new HomeCategoryAdapter(CatAllActivity.this, categoryList);
                recyclerView.setAdapter(categoryadapter);
                //mShimmerViewContainer.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }
        });
    }

}