package com.midhun.hawks_acbs;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.midhun.hawks_acbs.Adapter.HomeProductsAdapter;
import com.midhun.hawks_acbs.Adapter.RelatedProductsAdapter;
import com.midhun.hawks_acbs.Adapter.ViewProductsAdapter;
import com.midhun.hawks_acbs.Model.WishListModel;
import com.midhun.hawks_acbs.Util.CirclePagerIndicatorDecoration;
import com.midhun.hawks_acbs.Util.LinePagerIndicatorDecoration;
import com.midhun.hawks_acbs.Util.MidhunUtils;
import com.midhun.hawks_acbs.View.Delete;
import com.midhun.hawks_acbs.View.ProductById;
import com.midhun.hawks_acbs.View.Products;
import com.midhun.hawks_acbs.ViewModel.ProductsViewModel;
import com.midhun.hawks_acbs.ViewModel.ViewProductsViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OnViewActivity extends AppCompatActivity {
    RecyclerView recyclerView5;
    RecyclerView recyclerView6;

    List<Products> productsList;
    List<ProductById> productImageList;

    TextView txt_desc, price, username, date, location, address, location1, category, brand, call, email;
    ImageView profile_image, img_back, img_heart, img_msg, img_edit, img_delete;
    String mobile;
    ViewProductsAdapter viewProductsAdapter;
    HomeProductsAdapter productsAdapter;
    RelatedProductsAdapter relatedProductsAdapter;
    LinearLayout loading, base_container, top, bottom;
    ProgressBar progressHeart, load;


    SharedPreferences sharedPreferences;
    SharedPreferences.Editor myEdit;
    String UID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_view);
        recyclerView5 = findViewById(R.id.recyclerview5);
        recyclerView6 = findViewById(R.id.recyclerview6);
        txt_desc = findViewById(R.id.txt_desc);
        price = findViewById(R.id.lprice);
        username = findViewById(R.id.user_name);
        date = findViewById(R.id.date);
        location = findViewById(R.id.location);
        location1 = findViewById(R.id.location1);
        address = findViewById(R.id.address);
        category = findViewById(R.id.category);
        brand = findViewById(R.id.brand);
        profile_image = findViewById(R.id.profile_image);
        call = findViewById(R.id.call);
        loading = findViewById(R.id.loading);
        base_container = findViewById(R.id.base_container);
        top = findViewById(R.id.top);
        bottom = findViewById(R.id.bottom);
        img_back = findViewById(R.id.img_back);
        img_heart = findViewById(R.id.img_heart);
        img_msg = findViewById(R.id.img_msg);
        progressHeart = findViewById(R.id.progressHeart);
        load = findViewById(R.id.load);
        email = findViewById(R.id.email);
        img_edit = findViewById(R.id.img_edit);
        img_delete = findViewById(R.id.img_delete);


        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
           window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.purple_700));
        }

        img_delete.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.white));
        img_edit.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.white));
        img_heart.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.white));
        img_msg.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.white));
        img_back.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.white));


        Intent intent = getIntent();

        if (intent.hasExtra("myads")) {
            img_delete.setVisibility(View.VISIBLE);
            img_edit.setVisibility(View.VISIBLE);
            img_msg.setVisibility(View.GONE);
            img_heart.setVisibility(View.GONE);
        } else {
            img_delete.setVisibility(View.GONE);
            img_edit.setVisibility(View.GONE);
            img_msg.setVisibility(View.VISIBLE);
            img_heart.setVisibility(View.VISIBLE);
        }

        //progress bar color
        MidhunUtils.changeProgressBarColor(load, R.color.acbs_bold, OnViewActivity.this);
        progressHeart.getIndeterminateDrawable().setColorFilter(0xffffffff, PorterDuff.Mode.SRC_IN);
        //user
        sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
        UID = sharedPreferences.getString("UID", "");
        //visibility
        loading.setVisibility(View.VISIBLE);
        top.setVisibility(View.GONE);
        bottom.setVisibility(View.GONE);
        //checkfav

        isHeart();
        //back
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //msg

        img_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ChatFragmentContainer.class));

            }
        });
        //edit

        img_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent();
                in.setClass(OnViewActivity.this, AddProductActivity.class);
                in.putExtra("product_id", getIntent().getExtras().getString("id"));
                in.putExtra("category_name", productImageList.get(0).getCategoryName());
                in.putExtra("brand_name", productImageList.get(0).getBrand());
                in.putExtra("price", productImageList.get(0).getAmount());
                in.putExtra("desc", txt_desc.getText().toString());
                in.putExtra("title", getIntent().getExtras().getString("title"));
                startActivity(in);
            }
        });

        //fav

        img_heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // startActivity(new Intent(getApplicationContext(), FavFragmentContainer.class));

                if (MidhunUtils.localData(OnViewActivity.this, "login", "UID").isEmpty()) {
                    MidhunUtils.showSnackBarMsg(getApplicationContext(), img_heart, "Signin to continue", "Close");
                } else {
                    sharedPreferences = getSharedPreferences("heart", Context.MODE_PRIVATE);
                    String id = getIntent().getExtras().getString("id");
                    if (sharedPreferences.getString(id, "").equalsIgnoreCase("yes")) {
                        Intent in = new Intent();
                        in.setClass(OnViewActivity.this, FavFragmentContainer.class);
                        startActivity(in);
                    } else {
                        addTocart();
                        img_heart.setVisibility(View.GONE);
                        progressHeart.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

        //delete
        img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_delete.setVisibility(View.GONE);
                progressHeart.setVisibility(View.VISIBLE);
                softDelete();
            }
        });

        ///calling
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + mobile));
                startActivity(intent);
            }
        });

        getSupportActionBar().hide();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decor = getWindow().getDecorView();

           // decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            decor.setSystemUiVisibility(0);

        }

//prosductImage recyclerview
        recyclerView5.setHasFixedSize(true);
        productImageList = new ArrayList<>();
        recyclerView5.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView5.addItemDecoration(new CirclePagerIndicatorDecoration());
        viewProductsAdapter = new ViewProductsAdapter(this, productImageList);
        recyclerView5.setAdapter(viewProductsAdapter);
        viewProductsPopulate();

        //related ads recyclerview

        recyclerView6.setHasFixedSize(true);
        productsList = new ArrayList<>();
        recyclerView6.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView6.addItemDecoration(new LinePagerIndicatorDecoration());
        relatedProductsAdapter = new RelatedProductsAdapter(getApplicationContext(), productsList);
        recyclerView6.setAdapter(productsAdapter);
        ProductssPopulate();


    }


    private void viewProductsPopulate() {
        ViewProductsViewModel model5 = ViewModelProviders.of(OnViewActivity.this).get(ViewProductsViewModel.class);
        productImageList = new ArrayList<>();
        model5.getProductById(getIntent().getExtras().getString("id")).observe(OnViewActivity.this, new Observer<List<ProductById>>() {
            @Override
            public void onChanged(List<ProductById> products) {
                try {
                    productImageList.addAll(products);

                    txt_desc.setText(productImageList.get(0).getDescription());
                    price.setText("₹ " + productImageList.get(0).getAmount());
                    category.setText("Category : " + productImageList.get(0).getCategoryName());
                    brand.setText("Brand : " + productImageList.get(0).getBrand());
                    username.setText(productImageList.get(0).getUser());
                    date.setText(productImageList.get(0).getCreated());
                    location.setText(productImageList.get(0).getLocation());
                    mobile = productImageList.get(0).getMobile();
                    email.setText(productImageList.get(0).getEmail());
                    address.setText(productImageList.get(0).getAddress());
                    location1.setText(productImageList.get(0).getLocation());


                    String base = "https://acbsdemo.hawkssolutions.com/public/uploads/Products/cover/";

                    Glide.with(OnViewActivity.this)
                            .load(base.concat(productImageList.get(0)
                                    .getUserImage())).transition(withCrossFade())
                            .apply(new RequestOptions()
                                    .override(55, 55)
                                    .placeholder(R.drawable.avatar)
                                    .error(R.drawable.avatar)
                                    .centerCrop())
                            .into(profile_image);
                } catch (Exception e) {
                    Log.d("onload image ", "onChanged: " + e.toString());
                }
                //categoryadapter.notifyDataSetChanged();

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
                viewProductsAdapter = new ViewProductsAdapter(getApplicationContext(), productImageList);
                recyclerView5.setAdapter(viewProductsAdapter);
                loading.setVisibility(View.GONE);
                top.setVisibility(View.VISIBLE);
                bottom.setVisibility(View.VISIBLE);
            }
        });
    }


    private void ProductssPopulate() {
        ProductsViewModel model1 = ViewModelProviders.of(OnViewActivity.this).get(ProductsViewModel.class);
        productsList = new ArrayList<>();
        model1.getProducts().observe(OnViewActivity.this, new Observer<List<Products>>() {
            @Override
            public void onChanged(List<Products> products) {

                productsList.addAll(products);

                try {
                    //categoryadapter.notifyDataSetChanged();

                    int Length = productsList.size();
                    int index = Length - 1;

                    for (int i = 0; i < Length; i++) {
                        Log.e("Products response", "" + productsList.get(index).getCategoryName());
 img_delete.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.white));
        img_edit.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.white));
        img_heart.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.white));
        img_msg.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.white));
        img_back.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.white));
                       if (productsList.get(index).getCategoryName().equals(getIntent().getExtras().getString("category"))) {

                        } else {
                            productsList.remove(index);
                        }
                        index--;
                    }

                } catch (Exception e) {
                    Log.d("productsList error", e.toString());
                }
//                categoryadapter.set
                relatedProductsAdapter = new RelatedProductsAdapter(OnViewActivity.this, productsList);
                recyclerView6.setAdapter(relatedProductsAdapter);
            }
        });
    }

    private void addTocart() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);
        Call<WishListModel> call = api.addwishList("Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6Ikhhd2tzIFNvbHV0aW9ucyIsImFkbWluIjp0cnVlfQ.B5RoeVIIAVuLBbkq_yCFoZMmy4gXmiaIF-tSM3yPzQM",
                "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6Ikhhd2tzIFNvbHV0aW9ucyIsImFkbWluIjp0cnVlfQ.B5RoeVIIAVuLBbkq_yCFoZMmy4gXmiaIF-tSM3yPzQM", UID, getIntent().getExtras().getString("id"));
        call.enqueue(new Callback<WishListModel>() {
            @Override
            public void onResponse(Call<WishListModel> call, Response<WishListModel> response) {
                Log.d("add to cart", "onResponse: " + response.body().getStatus());

                if (response.body().getStatus().equalsIgnoreCase("true")) {
                    MidhunUtils.showSnackbar(base_container, OnViewActivity.this, true, "Product added to wishlist", "Okay", new MidhunUtils());
                    img_heart.setImageResource(R.drawable.heart);
                    img_heart.setVisibility(View.VISIBLE);
                    progressHeart.setVisibility(View.GONE);
                    addHeart();
                }
            }

            @Override
            public void onFailure(Call<WishListModel> call, Throwable t) {
                Toast.makeText(OnViewActivity.this, "failed", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void softDelete() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);
        Call<Delete> call = api.softdelete("Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6Ikhhd2tzIFNvbHV0aW9ucyIsImFkbWluIjp0cnVlfQ.B5RoeVIIAVuLBbkq_yCFoZMmy4gXmiaIF-tSM3yPzQM",
                "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6Ikhhd2tzIFNvbHV0aW9ucyIsImFkbWluIjp0cnVlfQ.B5RoeVIIAVuLBbkq_yCFoZMmy4gXmiaIF-tSM3yPzQM", "product", getIntent().getExtras().getString("id"));
        call.enqueue(new Callback<Delete>() {
            @Override
            public void onResponse(Call<Delete> call, Response<Delete> response) {
                if (response.code() == 200) {
                    img_delete.setVisibility(View.VISIBLE);
                    progressHeart.setVisibility(View.GONE);
                    startActivity(new Intent(getApplicationContext(), MyProductActivity.class));
                }
            }

            @Override
            public void onFailure(Call<Delete> call, Throwable t) {
                Toast.makeText(OnViewActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void addHeart() {
        sharedPreferences = getSharedPreferences("heart", Context.MODE_PRIVATE);
        myEdit = sharedPreferences.edit();
        myEdit.putString(getIntent().getExtras().getString("id"), "yes");
        myEdit.commit();

    }

    private void isHeart() {
        sharedPreferences = getSharedPreferences("heart", Context.MODE_PRIVATE);
        String id = getIntent().getExtras().getString("id");
        if (sharedPreferences.getString(id, "").equalsIgnoreCase("yes")) {
            img_heart.setImageResource(R.drawable.heart);
        } else {
            img_heart.setImageResource(R.drawable.heart_outline);
        }
    }

}