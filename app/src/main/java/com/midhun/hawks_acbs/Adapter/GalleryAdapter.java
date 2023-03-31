package com.midhun.hawks_acbs.Adapter;



import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.midhun.hawks_acbs.R;

import java.util.ArrayList;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {

    private ArrayList list;

    public GalleryAdapter(ArrayList list) {
        this.list = list;
    }

    @NonNull
    @Override
    public GalleryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view =inflater.inflate(R.layout.gallery_cus,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryAdapter.ViewHolder holder, int position) {
        holder.imageView.setImageURI((Uri) list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.product_image);
        }
    }
}