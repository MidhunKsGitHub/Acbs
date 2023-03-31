package com.midhun.hawks_acbs.Adapter;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.midhun.hawks_acbs.R;
import com.midhun.hawks_acbs.View.ProductById;

import java.util.List;

public class ViewProductsPhotoAdapter extends RecyclerView.Adapter<ViewProductsPhotoAdapter.ViewProductsViewHolder> {
    private Context mtx;
    private List<ProductById> productImageList;

    public ViewProductsPhotoAdapter(Context mtx, List<ProductById> productImageList) {
        this.mtx = mtx;
        this.productImageList = productImageList;
    }

    @NonNull
    @Override
    public ViewProductsPhotoAdapter.ViewProductsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mtx).inflate(R.layout.view_product_photo_item, parent, false);
        return new ViewProductsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewProductsPhotoAdapter.ViewProductsViewHolder holder, int position) {
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

    }

    @Override
    public int getItemCount() {
        return productImageList.size();
    }

    class ViewProductsViewHolder extends RecyclerView.ViewHolder {
        ImageView product_image;


        public ViewProductsViewHolder(@NonNull View itemView) {
            super(itemView);
            product_image = itemView.findViewById(R.id.product_image);


        }
    }
}
