package com.midhun.hawks_acbs.Adapter;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.midhun.hawks_acbs.OnViewActivity;
import com.midhun.hawks_acbs.R;
import com.midhun.hawks_acbs.View.Sort;

import java.util.List;


public class SortAdapter extends RecyclerView.Adapter<SortAdapter.HomeProductsViewHolder> {

    Context mCtx;
    List<Sort> sortList;

    public SortAdapter(Context mCtx, List<Sort> sortList) {
        this.mCtx = mCtx;
        this.sortList = sortList;
    }

    // @NonNull
    @Override
    public HomeProductsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.home_onview_category, parent, false);
        return new HomeProductsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HomeProductsViewHolder holder, int position) {
        Sort sort= sortList.get(position);
        String base="https://acbsdemo.hawkssolutions.com/public/uploads/Products/cover/";
        String img_url = base.concat(sort.getImage());
        Log.d("image", "onBindViewHolder: " + img_url);


        holder.txtlocation.setText(sort.getName());
        holder.txtprice.setText("₹ "+sort.getAmount());
        holder.brand_catview.setText(sort.getBrand());

        Glide.with(mCtx)
                .load(img_url)
                .transition(withCrossFade())
                .apply(new RequestOptions()
                        //.override(60, 60)
                        .placeholder(R.drawable.ic_error)
                        .error(R.drawable.ic_error).centerCrop()
                )
                .into(holder.image);



        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent();
                in.setClass(mCtx, OnViewActivity.class);
                in.putExtra("id",sort.getPid());
                in.putExtra("category",sort.getCategory());
                mCtx.startActivity(in);
            }
        });
    }


    @Override
    public int getItemCount() {
        return sortList.size();
    }

    class HomeProductsViewHolder extends RecyclerView.ViewHolder {


        TextView txtprice, txtlocation,brand_catview;
        ImageView image;
        LinearLayout cardview;

        public HomeProductsViewHolder(View itemView) {
            super(itemView);


            txtprice = itemView.findViewById(R.id.txt);
            txtlocation = itemView.findViewById(R.id.txtlocation);
            image = itemView.findViewById(R.id.product_image);
            cardview=itemView.findViewById(R.id.cardview);
            brand_catview=itemView.findViewById(R.id.brand_catview);
        }
    }
}