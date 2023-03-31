package com.midhun.hawks_acbs;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.lifecycle.Observer;


import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;


import java.util.ArrayList;
import java.util.List;

import com.midhun.hawks_acbs.Adapter.HomeBrandsAdapter;
import com.midhun.hawks_acbs.Adapter.HomeCategoryAdapter;
import com.midhun.hawks_acbs.Adapter.HomeProductsAdapter;
import com.midhun.hawks_acbs.View.Brand;
import com.midhun.hawks_acbs.View.Category;
import com.midhun.hawks_acbs.View.Products;
import com.midhun.hawks_acbs.ViewModel.BrandsViewModel;
import com.midhun.hawks_acbs.ViewModel.CategoryViewModel;
import com.midhun.hawks_acbs.ViewModel.ProductsViewModel;

public class MainActivity extends AppCompatActivity {

    //Recyclerview
    RecyclerView recyclerView;
    RecyclerView recyclerView2;
    RecyclerView recyclerView4;

  //List
    List<Category> categoryList;
    List<Products> productsList;
    List<Brand> brandList;

 //Adapter
    HomeProductsAdapter productsAdapter;
    HomeCategoryAdapter categoryadapter;
    HomeBrandsAdapter brandsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //strict mode vm policy
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder(StrictMode.getVmPolicy())
                .detectLeakedClosableObjects()
                .build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder(StrictMode.getVmPolicy())
                .detectLeakedClosableObjects()
                .build());
//material theme
        getSupportActionBar().hide();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decor = getWindow().getDecorView();

                decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                //decor.setSystemUiVisibility(0);

        }



      //category view
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        categoryList = new ArrayList<>();
        GridLayoutManager layoutManager1 = new GridLayoutManager(this, 4);
        recyclerView.setLayoutManager(layoutManager1);
        categoryadapter = new HomeCategoryAdapter(MainActivity.this, categoryList);
        recyclerView.setAdapter(categoryadapter);
        CategoriesPopulate();

        //product view
        recyclerView2 = findViewById(R.id.recyclerview2);
        recyclerView2.setHasFixedSize(true);
        productsList = new ArrayList<>();
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView2.setLayoutManager(layoutManager);
        productsAdapter = new HomeProductsAdapter(MainActivity.this, productsList);
        recyclerView2.setAdapter(productsAdapter);
        ProductssPopulate();

        //brand view

        recyclerView4 = findViewById(R.id.recyclerview4);
        recyclerView4.setHasFixedSize(true);
        brandList = new ArrayList<>();
        GridLayoutManager layoutManager2 = new GridLayoutManager(this, 4);
        recyclerView4.setLayoutManager(layoutManager2);      brandsAdapter = new HomeBrandsAdapter(MainActivity.this, brandList);
        recyclerView4.setAdapter(brandsAdapter);
        BrandsPopulate();
    }

    private void CategoriesPopulate() {

        CategoryViewModel model = ViewModelProviders.of(this).get(CategoryViewModel.class);
        categoryList = new ArrayList<>();
        model.getCategories().observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(@Nullable List<Category> cList) {
                categoryList.add(new Category("", "All", "0", "", "", ""));
                categoryList.addAll(cList);

                //categoryadapter.notifyDataSetChanged();

                Log.e("Category response", "" + categoryList.get(0).getCategory());
//                categoryadapter.set
                categoryadapter = new HomeCategoryAdapter(MainActivity.this, categoryList);
                recyclerView.setAdapter(categoryadapter);
            }
        });
    }

    private void ProductssPopulate() {
        ProductsViewModel model1 = ViewModelProviders.of(this).get(ProductsViewModel.class);
        productsList = new ArrayList<>();
        model1.getProducts().observe(this, new Observer<List<Products>>() {
            @Override
            public void onChanged(List<Products> products) {

                productsList.addAll(products);

                try {
                    //categoryadapter.notifyDataSetChanged();

                    int Length=productsList.size();
                    int index= Length-1;

                    for (int i = 0; i < Length; i++) {
                        Log.e("Products response", "" + productsList.get(index).getCategoryName());
//                        if(productsList.get(index).getBrand().equals("Adidas")){
//
//                        }
//                        else
//                        {
//                            productsList.remove(index);
//                        }
                        index--;
                    }

                }
                catch (Exception e) {
                    Log.d("productsList error", e.toString());
                }
//                categoryadapter.set
                productsAdapter = new HomeProductsAdapter(MainActivity.this, productsList);
                recyclerView2.setAdapter(productsAdapter);
            }
    });
}


    private void BrandsPopulate() {

        BrandsViewModel model3 = ViewModelProviders.of(this).get(BrandsViewModel.class);
        brandList = new ArrayList<>();
        model3.getBrands("").observe(this, new Observer<List<Brand>>() {
            @Override
            public void onChanged(@Nullable List<Brand>cList) {
              brandList.addAll(cList);

                //categoryadapter.notifyDataSetChanged();


               brandsAdapter = new HomeBrandsAdapter(MainActivity.this, brandList);
                recyclerView4.setAdapter(brandsAdapter);
            }
        });
    }

}