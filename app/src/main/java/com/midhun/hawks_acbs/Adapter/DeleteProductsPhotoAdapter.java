package com.midhun.hawks_acbs.Adapter;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.midhun.hawks_acbs.Api;
import com.midhun.hawks_acbs.R;
import com.midhun.hawks_acbs.View.ImageDelete;
import com.midhun.hawks_acbs.View.ProductById;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DeleteProductsPhotoAdapter extends RecyclerView.Adapter<DeleteProductsPhotoAdapter.ViewProductsViewHolder> {
    private Context mtx;
    private List<ProductById> productImageList;
    private ProgressDialog progress;

    public DeleteProductsPhotoAdapter(Context mtx, List<ProductById> productImageList) {
        this.mtx = mtx;
        this.productImageList = productImageList;
    }

    @NonNull
    @Override
    public DeleteProductsPhotoAdapter.ViewProductsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mtx).inflate(R.layout.delete_product_photo_item, parent, false);
        return new ViewProductsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DeleteProductsPhotoAdapter.ViewProductsViewHolder holder, int position) {
        ProductById productImage = productImageList.get(position);
        String base = "https://acbsdemo.hawkssolutions.com/public/uploads/Products/details/";

        String img_url = base.concat(productImage.getImage());


        Glide.with(mtx)
                .load(img_url)
                .transition(withCrossFade())
                .apply(new RequestOptions()
                        //.override(60, 60)
                        .placeholder(R.drawable.background_color_black)
                        .error(R.drawable.ic_error).centerCrop()
                )
                .into(holder.product_image);

//        holder.product_image.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//              Intent in = new Intent();
//              in.setClass(mtx,View_Photo_Activity.class);
//              in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//              in.putExtra("id",productImage.getId());
//              in.putExtra("category",productImage.getCategoryName());
//              mtx.startActivity(in);
//            }
//        });


        holder.txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.view.setVisibility(View.GONE);
                String id = productImageList.get(position).getImageId();
                holder.product_image.setImageResource(R.drawable.background_color_black);
                deleteImage(id);

            }
        });

    }

    @Override
    public int getItemCount() {
        return productImageList.size();
    }

    class ViewProductsViewHolder extends RecyclerView.ViewHolder {
        ImageView product_image, img_del;
        TextView txt;
        LinearLayout view;

        public ViewProductsViewHolder(@NonNull View itemView) {
            super(itemView);
            product_image = itemView.findViewById(R.id.product_image);
            img_del = itemView.findViewById(R.id.img_delete);
            txt = itemView.findViewById(R.id.txt);
            view = itemView.findViewById(R.id.view);
        }
    }

    private void deleteImage(String imgid) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);
        Call<ImageDelete> call = api.deleteImage("Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6Ikhhd2tzIFNvbHV0aW9ucyIsImFkbWluIjp0cnVlfQ.B5RoeVIIAVuLBbkq_yCFoZMmy4gXmiaIF-tSM3yPzQM",
                "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6Ikhhd2tzIFNvbHV0aW9ucyIsImFkbWluIjp0cnVlfQ.B5RoeVIIAVuLBbkq_yCFoZMmy4gXmiaIF-tSM3yPzQM", imgid);
        call.enqueue(new Callback<ImageDelete>() {
            @Override
            public void onResponse(Call<ImageDelete> call, Response<ImageDelete> response) {
                Toast.makeText(mtx, "sucess", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<ImageDelete> call, Throwable t) {
                Toast.makeText(mtx, "failed", Toast.LENGTH_SHORT).show();

            }
        });
    }

}
