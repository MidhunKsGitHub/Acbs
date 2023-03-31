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
import com.midhun.hawks_acbs.View.Search;

import java.util.List;


public class HomeSearchAdapter extends RecyclerView.Adapter<HomeSearchAdapter.HomeProductsViewHolder> {

    Context mCtx;
    List<Search> searchList;

    public HomeSearchAdapter(Context mCtx, List<Search> searchList) {
        this.mCtx = mCtx;
        this.searchList = searchList;
    }

    // @NonNull
    @Override
    public HomeProductsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.homeproduct_item, parent, false);
        return new HomeProductsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HomeProductsViewHolder holder, int position) {
        Search search = searchList.get(position);
        String base="https://acbsdemo.hawkssolutions.com/public/uploads/Products/cover/";
        String img_url = base+search.getImage();
        Log.d("image", "onBindViewHolder: " + img_url);


        holder.txtlocation.setText(search.getName());
        holder.txtprice.setText("₹ "+search.getAmount());

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
                in.putExtra("id",search.getId());
                in.putExtra("category",search.getCategory());
                mCtx.startActivity(in);
            }
        });
    }


    @Override
    public int getItemCount() {
        return searchList.size();
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