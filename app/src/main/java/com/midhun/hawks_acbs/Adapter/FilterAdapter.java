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
import com.midhun.hawks_acbs.View.Filter;

import java.util.List;


public class FilterAdapter extends RecyclerView.Adapter<FilterAdapter.HomeProductsViewHolder> {

    Context mCtx;
    List<Filter> filterList;

    public FilterAdapter(Context mCtx, List<Filter> filterList) {
        this.mCtx = mCtx;
        this.filterList = filterList;
    }

    // @NonNull
    @Override
    public HomeProductsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.home_onview_category, parent, false);
        return new HomeProductsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HomeProductsViewHolder holder, int position) {
    Filter filter= filterList.get(position);
        String base="https://acbsdemo.hawkssolutions.com/public/uploads/Products/cover/";
        String img_url = base.concat(filter.getImage());
        Log.d("image", "onBindViewHolder: " + img_url);


        holder.txtlocation.setText(filter.getName());
        holder.txtprice.setText("₹ "+filter.getAmount());
        holder.brand_catview.setText(filter.getBrand());

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
                in.putExtra("id",filter.getCategoryId());
                in.putExtra("category",filter.getCategory());
                mCtx.startActivity(in);
            }
        });
    }


    @Override
    public int getItemCount() {
        return filterList.size();
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