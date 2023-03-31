package com.midhun.hawks_acbs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.midhun.hawks_acbs.Adapter.MyProductsAdapter;
import com.midhun.hawks_acbs.Util.MidhunUtils;
import com.midhun.hawks_acbs.View.UserProducts;
import com.midhun.hawks_acbs.ViewModel.UserProductsViewModel;

import java.util.ArrayList;
import java.util.List;

public class MyProductActivity extends AppCompatActivity {
    RecyclerView recyclerView2;
    List<UserProducts> userProductsList;
    MyProductsAdapter productsAdapter;
    private ShimmerFrameLayout mShimmerViewContainer;
    String UID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_product);
        mShimmerViewContainer = findViewById(R.id.shimmer_view_container);
        recyclerView2 = findViewById(R.id.recyclerview15);
        getSupportActionBar().hide();
        MidhunUtils.setStatusBarIcon(MyProductActivity.this,false);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.purple_700));
        }


        SharedPreferences sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
        UID = sharedPreferences.getString("UID", "");
        recyclerView2.setHasFixedSize(true);
        userProductsList = new ArrayList<>();
        GridLayoutManager layoutManager = new GridLayoutManager(MyProductActivity.this, 2);
        recyclerView2.setLayoutManager(layoutManager);
        productsAdapter = new MyProductsAdapter(MyProductActivity.this, userProductsList);
        recyclerView2.setAdapter(productsAdapter);
        ProductssPopulate();
    }
    private void ProductssPopulate() {
        UserProductsViewModel model1 = ViewModelProviders.of(MyProductActivity.this).get(UserProductsViewModel.class);
        userProductsList = new ArrayList<>();
        model1.getUserProducts(UID).observe(MyProductActivity.this, new Observer<List<UserProducts>>() {
            @Override
            public void onChanged(List<UserProducts> userProducts) {



                try {
                    //categoryadapter.notifyDataSetChanged();
                    userProductsList.addAll(userProducts);
//                    int Length=productsList.size();
//                    int index= Length-1;
//
//                    for (int i = 0; i < productsList.size(); i++) {
//                        Log.e("Products response", "" + productsList.get(i).getCategoryName());
//                        if(productsList.get(i).getCustomerid().equals(UID)){
//                        }
//                        else
//                        {
//                        }
//                        index--;
     //               }

                productsAdapter = new MyProductsAdapter(MyProductActivity.this, userProductsList);
                recyclerView2.setAdapter(productsAdapter);
                mShimmerViewContainer.setVisibility(View.GONE);
                }
                catch (Exception e) {
                    Log.d("productsList error", e.toString());
                    Toast.makeText(MyProductActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}