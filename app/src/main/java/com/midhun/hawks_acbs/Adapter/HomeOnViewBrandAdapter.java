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
import com.midhun.hawks_acbs.View.Products;

import java.util.List;


public class HomeOnViewBrandAdapter extends RecyclerView.Adapter<HomeOnViewBrandAdapter.HomeProductsViewHolder> {

    Context mCtx;
    List<Products> productsList;

    public HomeOnViewBrandAdapter(Context mCtx, List<Products> productsList) {
        this.mCtx = mCtx;
        this.productsList = productsList;
    }

    // @NonNull
    @Override
    public HomeProductsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.home_onview_category, parent, false);
        return new HomeProductsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HomeProductsViewHolder holder, int position) {
        Products products = productsList.get(position);
        String base="https://acbsdemo.hawkssolutions.com/public/uploads/Products/cover/";
        String img_url = base.concat(products.getImage());
        Log.d("image", "onBindViewHolder: " + img_url);


        holder.txtlocation.setText(products.getName());
        holder.txtprice.setText("₹ "+products.getAmount());
        holder.brand_catview.setText(products.getCategoryName());

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
                in.putExtra("id",products.getId());
                in.putExtra("category",products.getCategoryName());
                mCtx.startActivity(in);
            }
        });
    }


    @Override
    public int getItemCount() {
        return productsList.size();
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