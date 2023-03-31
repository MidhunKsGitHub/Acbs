package com.midhun.hawks_acbs.Adapter;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.midhun.hawks_acbs.Api;
import com.midhun.hawks_acbs.OnViewActivity;
import com.midhun.hawks_acbs.R;
import com.midhun.hawks_acbs.View.Favorites;
import com.midhun.hawks_acbs.View.RemoveWishList;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class HomeFavAdapter extends RecyclerView.Adapter<HomeFavAdapter.HomeProductsViewHolder> {

    Context mCtx;
    List<Favorites> favoritesList;

    public HomeFavAdapter(Context mCtx, List<Favorites> favoritesList) {
        this.mCtx = mCtx;
        this.favoritesList = favoritesList;
    }

    // @NonNull
    @Override
    public HomeProductsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.homefav_item, parent, false);
        return new HomeProductsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HomeProductsViewHolder holder, int position) {
        Favorites favorites = favoritesList.get(position);
        String base = "https://acbsdemo.hawkssolutions.com/public/uploads/Products/cover/";
        String img_url = base + favorites.getImage();
        Log.d("image", "onBindViewHolder: " + img_url);


        holder.txtlocation.setText(favorites.getName());
        holder.txtprice.setText("₹ " + favorites.getAmount());

        Glide.with(mCtx)
                .load(img_url)
                .transition(withCrossFade())
                .apply(new RequestOptions()
                        //.override(60, 60)
                        .placeholder(R.drawable.ic_error)
                        .error(R.drawable.ic_error).centerCrop()
                )
                .into(holder.image);

        holder.txt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteImage(favoritesList.get(position).getWishlistId());
                holder.cardview.setVisibility(View.GONE);
               addHeart(favoritesList.get(position).getId());
            }
        });

        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent();
                in.setClass(mCtx, OnViewActivity.class);
                in.putExtra("id", favorites.getId());
                in.putExtra("category", favorites.getCategory());
                mCtx.startActivity(in);
            }
        });


    }


    @Override
    public int getItemCount() {
        return favoritesList.size();
    }

    class HomeProductsViewHolder extends RecyclerView.ViewHolder {


        TextView txtprice, txtlocation, txt1;
        ImageView image;
        CardView cardview;

        public HomeProductsViewHolder(View itemView) {
            super(itemView);


            txtprice = itemView.findViewById(R.id.txt);
            txtlocation = itemView.findViewById(R.id.txtlocation);
            image = itemView.findViewById(R.id.product_image);
            cardview = itemView.findViewById(R.id.cardview);
            txt1 = itemView.findViewById(R.id.txt1);
        }
    }

    private void deleteImage(String id) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);
        Call<RemoveWishList> call = api.removeFav("Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6Ikhhd2tzIFNvbHV0aW9ucyIsImFkbWluIjp0cnVlfQ.B5RoeVIIAVuLBbkq_yCFoZMmy4gXmiaIF-tSM3yPzQM",
                "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6Ikhhd2tzIFNvbHV0aW9ucyIsImFkbWluIjp0cnVlfQ.B5RoeVIIAVuLBbkq_yCFoZMmy4gXmiaIF-tSM3yPzQM", id);
        call.enqueue(new Callback<RemoveWishList>() {
            @Override
            public void onResponse(Call<RemoveWishList> call, Response<RemoveWishList> response) {
                Toast.makeText(mCtx, "sucess", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<RemoveWishList> call, Throwable t) {
                Toast.makeText(mCtx, "failed", Toast.LENGTH_SHORT).show();

            }
        });
    }
    private void addHeart(String pid) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences("heart", Context.MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putString(pid, "no");
        myEdit.commit();

    }
}