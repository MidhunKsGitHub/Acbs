package com.midhun.hawks_acbs;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.midhun.hawks_acbs.Adapter.ViewProfileAdapter;
import com.midhun.hawks_acbs.Util.MidhunUtils;
import com.midhun.hawks_acbs.View.Profile;
import com.midhun.hawks_acbs.ViewModel.ViewProfileViewModel;

import java.util.ArrayList;
import java.util.List;


public class UserFragment extends Fragment {
    List<Profile> profileList;
    RecyclerView recyclerView10;
    ViewProfileAdapter viewProfileAdapter;
    LinearLayout base, loading;
    ProgressBar pb;
    private ShimmerFrameLayout mShimmerViewContainer;
    String UID;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        recyclerView10 = view.findViewById(R.id.recyclerView10);
        base = view.findViewById(R.id.base);
        loading = view.findViewById(R.id.loading);
        pb = view.findViewById(R.id.pb);
        mShimmerViewContainer = view.findViewById(R.id.shimmer_view_container);

        base.setVisibility(View.GONE);
        mShimmerViewContainer.setVisibility(View.VISIBLE);
        MidhunUtils.changeProgressBarColor(pb, R.color.black, getActivity());
        recyclerView10.setHasFixedSize(true);
        profileList = new ArrayList<>();
        recyclerView10.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        viewProfileAdapter = new ViewProfileAdapter(getActivity(), profileList);
        recyclerView10.setAdapter(viewProfileAdapter);

        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        UID = sharedPreferences.getString("UID", "");
        ProductssPopulate(UID);
        return view;
    }

    private void ProductssPopulate(String UID) {
        ViewProfileViewModel model1 = ViewModelProviders.of(getActivity()).get(ViewProfileViewModel.class);
        profileList = new ArrayList<>();
        model1.getUser(UID).observe(getActivity(), new Observer<List<Profile>>() {
            @Override
            public void onChanged(List<Profile> profile) {
                profileList.clear();
                profileList.addAll(profile);

                viewProfileAdapter = new ViewProfileAdapter(getActivity(), profileList);
                recyclerView10.setAdapter(viewProfileAdapter);
                mShimmerViewContainer.setVisibility(View.GONE);
                base.setVisibility(View.VISIBLE);
                viewProfileAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onResume() {

        super.onResume();
        recyclerView10.setHasFixedSize(true);
        profileList = new ArrayList<>();
        recyclerView10.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        viewProfileAdapter = new ViewProfileAdapter(getActivity(), profileList);
        recyclerView10.setAdapter(viewProfileAdapter);
        ProductssPopulate(UID);
    }

}