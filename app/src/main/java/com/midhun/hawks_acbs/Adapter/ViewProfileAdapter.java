package com.midhun.hawks_acbs.Adapter;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.midhun.hawks_acbs.FavFragmentContainer;
import com.midhun.hawks_acbs.MyProductActivity;
import com.midhun.hawks_acbs.R;
import com.midhun.hawks_acbs.SettingsActivity;
import com.midhun.hawks_acbs.View.Profile;

import java.util.List;

public class ViewProfileAdapter extends RecyclerView.Adapter<ViewProfileAdapter.ViewProductsViewHolder> {
    private Context mtx;
    private List<Profile> profileList;

    public ViewProfileAdapter(Context mtx, List<Profile> profileList) {
        this.mtx = mtx;
        this.profileList = profileList;
    }

    @NonNull
    @Override
    public ViewProfileAdapter.ViewProductsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mtx).inflate(R.layout.profile_list_view, parent, false);
        return new ViewProductsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewProfileAdapter.ViewProductsViewHolder holder, int position) {
        Profile profile = profileList.get(position);

     holder.txt.setText(profile.getName());
     holder.email.setText(profile.getEmail());
     holder.myordders.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             Intent in = new Intent();
             in.setClass(mtx, FavFragmentContainer.class);
             mtx.startActivity(in);
         }
     });
        holder.myads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent();
                in.setClass(mtx, MyProductActivity.class);
                mtx.startActivity(in);
            }
        });

        holder.settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent();
                in.setClass(mtx, SettingsActivity.class);
                mtx.startActivity(in);
            }
        });

        holder.helpsupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent selectorIntent = new Intent(Intent.ACTION_SENDTO);
                selectorIntent.setData(Uri.parse("mailto:"));

                final Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"address@mail.com"});
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "The subject");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "The email body");
                emailIntent.setSelector( selectorIntent );

                mtx.startActivity(Intent.createChooser(emailIntent, "Send email..."));
            }
        });


    }

    @Override
    public int getItemCount() {
        return profileList.size();
    }

    class ViewProductsViewHolder extends RecyclerView.ViewHolder {
      TextView txt,email;
      LinearLayout myordders,myads,settings,helpsupport;

        public ViewProductsViewHolder(@NonNull View itemView) {
            super(itemView);
           txt = itemView.findViewById(R.id.name);
           email=itemView.findViewById(R.id.email);
           myordders=itemView.findViewById(R.id.myorders);
           myads=itemView.findViewById(R.id.myads);
           settings=itemView.findViewById(R.id.settings);
           helpsupport=itemView.findViewById(R.id.helpsupport);


        }
    }
}
