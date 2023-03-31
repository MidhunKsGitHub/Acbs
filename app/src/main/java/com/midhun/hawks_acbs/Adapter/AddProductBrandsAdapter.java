package com.midhun.hawks_acbs.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.midhun.hawks_acbs.R;
import com.midhun.hawks_acbs.View.Brand;

import java.util.List;


public class AddProductBrandsAdapter extends RecyclerView.Adapter<AddProductBrandsAdapter.HomeBrandsViewHolder> {

    Context mCtx;
    List<Brand> brandList;

    public AddProductBrandsAdapter(Context mCtx, List<Brand> brandList) {
        this.mCtx = mCtx;
        this.brandList = brandList;
    }

    // @NonNull
    @Override
    public HomeBrandsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.homebrand_item, parent, false);
        return new HomeBrandsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HomeBrandsViewHolder holder, int position) {
        Brand brand = brandList.get(position);

        holder.txtbrand.setText(brand.getBrand());
        holder.txtbrand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

    }

    @Override
    public int getItemCount() {
        return brandList.size();
    }

    class HomeBrandsViewHolder extends RecyclerView.ViewHolder {


        TextView txtbrand;

        public HomeBrandsViewHolder(View itemView) {
            super(itemView);


            txtbrand = itemView.findViewById(R.id.txtbrand);


        }
    }
}