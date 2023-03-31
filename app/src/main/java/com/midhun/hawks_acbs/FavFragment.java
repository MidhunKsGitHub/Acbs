package com.midhun.hawks_acbs;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.midhun.hawks_acbs.Adapter.HomeFavAdapter;
import com.midhun.hawks_acbs.View.Favorites;
import com.midhun.hawks_acbs.ViewModel.FavViewModel;

import java.util.ArrayList;
import java.util.List;

public class FavFragment extends Fragment {

    RecyclerView recyclerView12;
    private List<Favorites> favoritesList;
    HomeFavAdapter homeFavAdapter;
    String UID;
    private ShimmerFrameLayout mShimmerViewContainer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fav, container, false);
        recyclerView12 = view.findViewById(R.id.recyclerview12);
        mShimmerViewContainer = view.findViewById(R.id.shimmer_view_container);
        //product view
        recyclerView12 = view.findViewById(R.id.recyclerview12);
        recyclerView12.setHasFixedSize(true);
        favoritesList = new ArrayList<>();
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView12.setLayoutManager(layoutManager);
        homeFavAdapter = new HomeFavAdapter(getActivity(), favoritesList);
        recyclerView12.setAdapter(homeFavAdapter);

        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        UID = sharedPreferences.getString("UID", "");
        ProductssPopulate();
        return view;
    }

    private void ProductssPopulate() {
        FavViewModel model1 = ViewModelProviders.of(getActivity()).get(FavViewModel.class);
        favoritesList = new ArrayList<>();
        model1.getWishList(UID).observe(getActivity(), new Observer<List<Favorites>>() {
            @Override
            public void onChanged(List<Favorites> favorites) {
                favoritesList.addAll(favorites);

                try {
                    //categoryadapter.notifyDataSetChanged();

                    int Length = favoritesList.size();
                    int index = Length - 1;

                    for (int i = 0; i < Length; i++) {
                        Log.e("Fav response", "" + favoritesList.get(index).getCategory());
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
                homeFavAdapter = new HomeFavAdapter(getActivity(), favoritesList);
                recyclerView12.setAdapter(homeFavAdapter);
                mShimmerViewContainer.setVisibility(View.GONE);
            }
        });

    }
}
