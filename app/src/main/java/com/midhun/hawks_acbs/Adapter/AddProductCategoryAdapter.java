package com.midhun.hawks_acbs.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.midhun.hawks_acbs.R;
import com.midhun.hawks_acbs.View.AddProductCategory;

import java.util.List;
//import android.annotation.;

public class AddProductCategoryAdapter extends RecyclerView.Adapter<AddProductCategoryAdapter.CategoryViewHolder> {

    Context mCtx;
    List<AddProductCategory> addProductCategoryListt;

    public AddProductCategoryAdapter(Context mCtx, List<AddProductCategory> addProductCategoryListt) {
        this.mCtx = mCtx;
        this.addProductCategoryListt = addProductCategoryListt;
    }

    // @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.homecat_item, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        AddProductCategory addProductCategory = addProductCategoryListt.get(position);

//        Glide.with(mCtx)
//                .load(hero.getImageurl())
//                .into(holder.imageView);

        holder.textView.setText(addProductCategory.getCategory());
        if(position==1){
            holder.imageView.setImageResource(R.drawable.furnitures);

        }
        if(position==2){
            holder.imageView.setImageResource(R.drawable.sports);

        }
        if(position==3){
            holder.imageView.setImageResource(R.drawable.dress);

        }
        if(position==4){
            holder.imageView.setImageResource(R.drawable.dress);

        }
        if(position==5){
            holder.imageView.setImageResource(R.drawable.sports);

        }

//        String base="https://acbsdemo.hawkssolutions.com/public/uploads/Products/cover/";
//        String img_url = base+category.getImage();
//        Glide.with(mCtx)
//                .load(img_url)
//                .transition(withCrossFade())
//                .apply(new RequestOptions().override(50, 50)
//                        .placeholder(R.drawable.ic_error)
//                        .error(R.drawable.ic_error).centerCrop()
//                )
//                .into(holder.imageView);

        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(mCtx, addProductCategory.getCategory(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return addProductCategoryListt.size();
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {


        TextView textView;
        ImageView imageView;

        public CategoryViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.cat_image);
            textView = itemView.findViewById(R.id.txtcategory);
        }
    }
}