package com.midhun.hawks_acbs;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.midhun.hawks_acbs.Adapter.HomeProductsAdapter;
import com.midhun.hawks_acbs.Adapter.HomeSearchAdapter;
import com.midhun.hawks_acbs.Util.MidhunUtils;
import com.midhun.hawks_acbs.View.Products;
import com.midhun.hawks_acbs.View.Search;
import com.midhun.hawks_acbs.ViewModel.ProductsViewModel;
import com.midhun.hawks_acbs.ViewModel.SearchViewModel;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    RecyclerView recyclerView13;
    List<Search> searchList;
    HomeSearchAdapter homeSearchAdapter;
    AutoCompleteTextView edt;
    ImageView ic_search, img_back;
    ProgressDialog progress;
    LinearLayout not, top;
    List<Products> productsList;
    HomeProductsAdapter productsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        recyclerView13 = findViewById(R.id.recyclerview13);
        edt = findViewById(R.id.edt);
        ic_search = findViewById(R.id.ic_search);
        img_back = findViewById(R.id.img_back);
        not = findViewById(R.id.not);
        top = findViewById(R.id.top);
        getSupportActionBar().hide();
        MidhunUtils.setStatusBarIcon(SearchActivity.this, true);
        recyclerView13.setHasFixedSize(true);
        searchList = new ArrayList<>();
        GridLayoutManager layoutManager = new GridLayoutManager(SearchActivity.this, 2);
        recyclerView13.setLayoutManager(layoutManager);
        homeSearchAdapter = new HomeSearchAdapter(SearchActivity.this, searchList);
        recyclerView13.setAdapter(homeSearchAdapter);

       listProducts();
        if (getIntent().getExtras().getString("name").isEmpty()) {

        } else {
            edt.setText(getIntent().getExtras().getString("name"));
            ProductssPopulate(getIntent().getExtras().getString("name"));
            progress = ProgressDialog.show(SearchActivity.this, null, null, true);
            progress.setContentView(R.layout.progress_layout);
            progress.setCancelable(true);
            progress.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

            progress.show();
        }
        ic_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (edt.getText().toString().isEmpty()) {
                    Toast.makeText(SearchActivity.this, "Enter search item", Toast.LENGTH_SHORT).show();
                } else {
                    Intent in = new Intent();
                    in.setClass(SearchActivity.this, SearchActivity.class);
                    in.putExtra("name", edt.getText().toString());
                    startActivity(in);
                    finish();
                }
            }
        });
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        edt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                    if (edt.getText().toString().isEmpty()) {
                        Toast.makeText(SearchActivity.this, "Enter search item", Toast.LENGTH_SHORT).show();
                    } else {
                        Intent in = new Intent();
                        in.setClass(SearchActivity.this, SearchActivity.class);
                        in.putExtra("name", edt.getText().toString());
                        startActivity(in);
                        finish();
                    }
                    return true;
                }
                return false;
            }
        });

    }

    private void ProductssPopulate(String item) {
        SearchViewModel model1 = ViewModelProviders.of(SearchActivity.this).get(SearchViewModel.class);
        searchList = new ArrayList<>();
        model1.getSearch(item).observe(SearchActivity.this, new Observer<List<Search>>() {
            @Override
            public void onChanged(List<Search> search) {
                searchList.clear();
                searchList.addAll(search);


                try {
                    //categoryadapter.notifyDataSetChanged();

                    int Length = searchList.size();
                    int index = Length - 1;

                    for (int i = 0; i < Length; i++) {
                        Log.e("Fav response", "" + searchList.get(index).getCategory());
//                        if(productsList.get(index).getBrand().equals("Adidas")){
//
//                        }
//                        else
//                        {
//                            productsList.remove(index);
//                        }
                        index--;
                    }

                } catch (Exception e) {
                    Log.d("favList error", e.toString());
                }
//                categoryadapter.set
                homeSearchAdapter = new HomeSearchAdapter(SearchActivity.this, searchList);
                recyclerView13.setAdapter(homeSearchAdapter);
                progress.dismiss();
                if (searchList.size() == 0) {
                    top.setVisibility(View.GONE);
                    not.setVisibility(View.VISIBLE);
                }
                // mShimmerViewContainer.setVisibility(View.GONE);
            }
        });

    }

    private void listProducts() {
        ProductsViewModel model1 = ViewModelProviders.of(SearchActivity.this).get(ProductsViewModel.class);
        productsList = new ArrayList<>();
        model1.getProducts().observe(SearchActivity.this, new Observer<List<Products>>() {
            @Override
            public void onChanged(List<Products> products) {

                productsList.addAll(products);
                addToDropDown(productsList);

                try {
                    //categoryadapter.notifyDataSetChanged();

                    int Length = productsList.size();
                    int index = Length - 1;

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

                } catch (Exception e) {
                    Log.d("productsList error", e.toString());
                }
//                categoryadapter.set
                productsAdapter = new HomeProductsAdapter(SearchActivity.this, productsList);

            }
        });
    }

    private void addToDropDown(List<Products> productsList){
        ArrayList arrayList=new ArrayList<>();
        for(int i=0;i<productsList.size();i++) {
            arrayList.add(productsList.get(i).getName());
        }
        ArrayAdapter arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
        edt.setAdapter(arrayAdapter);
        edt.setThreshold(1);
        edt.requestFocus();
        edt.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int pos,
                                    long id) {
                Intent in = new Intent();
                in.setClass(SearchActivity.this, SearchActivity.class);
                in.putExtra("name", edt.getText().toString());
                startActivity(in);
                finish();
            }
        });

    }
}