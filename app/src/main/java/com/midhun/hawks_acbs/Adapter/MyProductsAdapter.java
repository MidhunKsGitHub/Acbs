package com.midhun.hawks_acbs.Adapter;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.midhun.hawks_acbs.OnViewActivity;
import com.midhun.hawks_acbs.R;
import com.midhun.hawks_acbs.View.UserProducts;

import java.util.List;


public class MyProductsAdapter extends RecyclerView.Adapter<MyProductsAdapter.HomeProductsViewHolder> {

    Context mCtx;
    List<UserProducts> userProductsList;

    public MyProductsAdapter(Context mCtx, List<UserProducts> userProductsList) {
        this.mCtx = mCtx;
        this.userProductsList = userProductsList;
    }

    // @NonNull
    @Override
    public HomeProductsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.homeproduct_item, parent, false);
        return new HomeProductsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HomeProductsViewHolder holder, int position) {
        UserProducts userProducts = userProductsList.get(position);
        String base="https://acbsdemo.hawkssolutions.com/public/uploads/Products/cover/";
        String img_url = base+userProducts.getProductImage();
        Log.d("image", "onBindViewHolder: " + img_url);


        holder.txtlocation.setText(userProducts.getProductName());
        holder.txtprice.setText("₹ "+userProducts.getAmount());

        Glide.with(mCtx)
                .load(img_url)
                .transition(withCrossFade())
                .apply(new RequestOptions()
                        //.override(60, 60)
                        .placeholder(R.drawable.background_color_black)
                        .error(R.drawable.background_color_black).centerCrop()
                )
                .into(holder.image);



        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent();
                in.setClass(mCtx, OnViewActivity.class);
                in.putExtra("id",userProducts.getPid());
                in.putExtra("myads",userProducts.getPid());
                in.putExtra("title",holder.txtlocation.getText().toString());
                in.putExtra("category",userProducts.getCategoryName());
                mCtx.startActivity(in);
            }
        });
    }


    @Override
    public int getItemCount() {
        return userProductsList.size();
    }

    class HomeProductsViewHolder extends RecyclerView.ViewHolder {


        TextView txtprice, txtlocation;
        ImageView image;
        CardView cardview;

        public HomeProductsViewHolder(View itemView) {
            super(itemView);


            txtprice = itemView.findViewById(R.id.txt);
            txtlocation = itemView.findViewById(R.id.txtlocation);
            image = itemView.findViewById(R.id.product_image);
            cardview=itemView.findViewById(R.id.cardview);
        }
    }
}