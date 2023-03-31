package com.midhun.hawks_acbs;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.midhun.hawks_acbs.Adapter.ViewProductsPhotoAdapter;
import com.midhun.hawks_acbs.Util.CirclePagerIndicatorDecorationWhite;
import com.midhun.hawks_acbs.Util.MidhunUtils;
import com.midhun.hawks_acbs.View.ProductById;
import com.midhun.hawks_acbs.ViewModel.ViewProductsViewModel;

import java.util.ArrayList;
import java.util.List;

public class View_Photo_Activity extends AppCompatActivity {
    ViewProductsPhotoAdapter viewProductsPhotoAdapter;

    List<ProductById> productImageList;
    RecyclerView recyclerView9;

    LinearLayout base,loading;
    ProgressBar pb1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_photo);
        recyclerView9 = findViewById(R.id.recyclerView9);
        base=findViewById(R.id.base);
        loading=findViewById(R.id.loading);
        pb1=findViewById(R.id.pb1);

        base.setVisibility(View.GONE);
        loading.setVisibility(View.VISIBLE);

        getSupportActionBar().hide();
        MidhunUtils.changeProgressBarColor(pb1,R.color.acbs_bold,View_Photo_Activity.this);
        MidhunUtils.makeStatusBar(View_Photo_Activity.this,R.color.black);
        recyclerView9.setHasFixedSize(true);
        productImageList = new ArrayList<>();
        recyclerView9.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView9.addItemDecoration(new CirclePagerIndicatorDecorationWhite());
        viewProductsPhotoAdapter = new ViewProductsPhotoAdapter(this, productImageList);
        recyclerView9.setAdapter(viewProductsPhotoAdapter);
        viewProductsPopulate();

    }

    private void viewProductsPopulate() {
        ViewProductsViewModel model5 = ViewModelProviders.of(View_Photo_Activity.this).get(ViewProductsViewModel.class);
        productImageList = new ArrayList<>();
        model5.getProductById(getIntent().getExtras().getString("id")).observe(View_Photo_Activity.this, new Observer<List<ProductById>>() {
            @Override
            public void onChanged(List<ProductById> products) {

                productImageList.addAll(products);


                int Length = productImageList.size();
                int index = Length - 1;

                for (int i = 0; i < Length; i++) {
                    Log.e("Products response", "" + productImageList.get(index).getName());
//                        if(productsList.get(index).getBrand().equals("Adidas")){
//
//                        }
//                        else
//                        {
//                            productsList.remove(index);
//                        }
                    index--;
                }


//                categoryadapter.set
                viewProductsPhotoAdapter = new ViewProductsPhotoAdapter(getApplicationContext(), productImageList);
                recyclerView9.setAdapter(viewProductsPhotoAdapter);
                loading.setVisibility(View.GONE);
                base.setVisibility(View.VISIBLE);

            }
        });
    }
}