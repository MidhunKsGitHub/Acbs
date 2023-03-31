package com.midhun.hawks_acbs;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.midhun.hawks_acbs.Adapter.HomeBrandsAdapter;
import com.midhun.hawks_acbs.Adapter.HomeCategoryAdapter;
import com.midhun.hawks_acbs.Adapter.HomeProductsAdapter;
import com.midhun.hawks_acbs.View.Brand;
import com.midhun.hawks_acbs.View.Category;
import com.midhun.hawks_acbs.View.Products;
import com.midhun.hawks_acbs.ViewModel.BrandsViewModel;
import com.midhun.hawks_acbs.ViewModel.CategoryViewModel;
import com.midhun.hawks_acbs.ViewModel.ProductsViewModel;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {


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
    //imageview

    ImageView img_location, img_menu;

    //shimmer

    private ShimmerFrameLayout mShimmerViewContainer;
    private ShimmerFrameLayout mShimmerViewContainer1;
    private ShimmerFrameLayout mShimmerViewContainer2;

    //card

    CardView search;
    SwipeRefreshLayout mSwipeRefreshLayout;
    TextView location,cat_all,brand_all;
    SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        //image color filter
        img_location = view.findViewById(R.id.img_location);
        img_menu = view.findViewById(R.id.img_menu);
        location = view.findViewById(R.id.location);
        search = view.findViewById(R.id.search);
        cat_all=view.findViewById(R.id.cat_all);
        brand_all=view.findViewById(R.id.brand_all);
        mShimmerViewContainer = view.findViewById(R.id.shimmer_view_container);
        mShimmerViewContainer1 = view.findViewById(R.id.shimmer_view_container1);
        mShimmerViewContainer2 = view.findViewById(R.id.shimmer_view_container2);
        img_location.setColorFilter(ContextCompat.getColor(getContext(), R.color.white));
        img_menu.setColorFilter(ContextCompat.getColor(getContext(), R.color.white));
        //category view
        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        categoryList = new ArrayList<>();
        GridLayoutManager layoutManager1 = new GridLayoutManager(getActivity(), 4);
        recyclerView.setLayoutManager(layoutManager1);
        categoryadapter = new HomeCategoryAdapter(getActivity(), categoryList);
        recyclerView.setAdapter(categoryadapter);
        CategoriesPopulate();

        //product view
        recyclerView2 = view.findViewById(R.id.recyclerview2);
        recyclerView2.setHasFixedSize(true);
        productsList = new ArrayList<>();
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView2.setLayoutManager(layoutManager);
        productsAdapter = new HomeProductsAdapter(getActivity(), productsList);
        recyclerView2.setAdapter(productsAdapter);
        ProductssPopulate();

        //brand view

        recyclerView4 = view.findViewById(R.id.recyclerview4);
        recyclerView4.setHasFixedSize(true);
        brandList = new ArrayList<>();
        GridLayoutManager layoutManager2 = new GridLayoutManager(getActivity(), 4);
        recyclerView4.setLayoutManager(layoutManager2);
        brandsAdapter = new HomeBrandsAdapter(getActivity(), brandList);
        recyclerView4.setAdapter(brandsAdapter);
        BrandsPopulate();

        ///location
        cityLoaction();
//       String s=MidhunUtils.localData(getActivity(),"location","longi");
//        Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();

        cat_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),CatAllActivity.class));
            }
        });
        brand_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),BrandAllActivity.class));
            }
        });
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loc = new Intent();
                loc.setClass(getActivity(), Location2Activity.class);
                loc.putExtra("name", "");
                startActivity(loc);

            }
        });


        //swipe


        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.container);

//        mSwipeRefreshLayout.setColorScheme(R.color.blue,
//                R.color.green, R.color.orange, R.color.purple);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.e(getClass().getSimpleName(), "refresh");
                Handler h = new Handler();
                h.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(getActivity(), MainHomeActivity.class));
                    }
                }, 2500);

            }
        });

        img_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent();
                in.putExtra("category", "");
                in.putExtra("category_name", "All");
                in.setClass(getActivity(), Sort_filter_Activity.class);

                startActivity(in);
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent();
                in.setClass(getActivity(), SearchActivity.class);
                in.putExtra("name", "");
                startActivity(in);

            }
        });

        return view;
    }

    private void CategoriesPopulate() {

        CategoryViewModel model = ViewModelProviders.of(this).get(CategoryViewModel.class);
        categoryList = new ArrayList<>();
        model.getCategories().observe(getActivity(), new Observer<List<Category>>() {
            @Override
            public void onChanged(@Nullable List<Category> cList) {
                categoryList.add(new Category("", "All", "0", "", "", ""));
                categoryList.addAll(cList);

                //categoryadapter.notifyDataSetChanged();

                Log.e("Category response", "" + categoryList.get(0).getCategory());
//                categoryadapter.set
                categoryadapter = new HomeCategoryAdapter(getActivity(), categoryList);
                recyclerView.setAdapter(categoryadapter);
                mShimmerViewContainer.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }
        });
    }

    private void ProductssPopulate() {
        ProductsViewModel model1 = ViewModelProviders.of(getActivity()).get(ProductsViewModel.class);
        productsList = new ArrayList<>();
        model1.getProducts().observe(getActivity(), new Observer<List<Products>>() {
            @Override
            public void onChanged(List<Products> products) {

                productsList.addAll(products);


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
                productsAdapter = new HomeProductsAdapter(getActivity(), productsList);
                recyclerView2.setAdapter(productsAdapter);
                mShimmerViewContainer1.setVisibility(View.GONE);
            }
        });
    }


    private void BrandsPopulate() {

        BrandsViewModel model3 = ViewModelProviders.of(this).get(BrandsViewModel.class);
        brandList = new ArrayList<>();
        model3.getBrands("").observe(getActivity(), new Observer<List<Brand>>() {
            @Override
            public void onChanged(@Nullable List<Brand> cList) {
                brandList.addAll(cList);

                //categoryadapter.notifyDataSetChanged();


                brandsAdapter = new HomeBrandsAdapter(getActivity(), brandList);
                recyclerView4.setAdapter(brandsAdapter);
                mShimmerViewContainer2.setVisibility(View.GONE);
            }
        });
    }

    private void cityLoaction() {
        sharedPreferences = getActivity().getSharedPreferences("location", Context.MODE_PRIVATE);

        if (!sharedPreferences.getString("city", "").equalsIgnoreCase("")) {
            location.setText(sharedPreferences.getString("city", ""));
        } else {
            location.setText("Choose city");
        }
    }
}
