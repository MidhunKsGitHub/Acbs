package com.midhun.hawks_acbs;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.midhun.hawks_acbs.Adapter.DeleteProductsPhotoAdapter;
import com.midhun.hawks_acbs.Adapter.GalleryAdapter;
import com.midhun.hawks_acbs.Util.MidhunUtils;
import com.midhun.hawks_acbs.View.Images;
import com.midhun.hawks_acbs.View.ImagesEdit;
import com.midhun.hawks_acbs.View.ProductById;
import com.midhun.hawks_acbs.ViewModel.ViewProductsViewModel;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FileActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    TextView post;
    List<ProductById> productImageList;
    RecyclerView recyclerView9;
    DeleteProductsPhotoAdapter deleteProductsPhotoAdapter;

    ArrayList<Uri> list;
    GalleryAdapter adaptor;
    ImageView img_back, pick;
    List<String> detail_images;
    String UID;
    String colum[] = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE};

    String latitude,longitude,city,pincode,state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);
        list = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler);
        recyclerView9 = findViewById(R.id.recyclerView9);
        post = findViewById(R.id.post);
        pick = findViewById(R.id.product_image);
        img_back = findViewById(R.id.img_back);

        adaptor = new GalleryAdapter(list);
        recyclerView.setLayoutManager(new GridLayoutManager(FileActivity.this, 3));
        recyclerView.setAdapter(adaptor);
        getSupportActionBar().hide();
        MidhunUtils.setStatusBarIcon(FileActivity.this, true);

        detail_images = new ArrayList<>();
        SharedPreferences sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
        UID = sharedPreferences.getString("UID", "");


        recyclerView9.setHasFixedSize(true);
        productImageList = new ArrayList<>();
        recyclerView9.setLayoutManager(new GridLayoutManager(FileActivity.this, 3));
          deleteProductsPhotoAdapter = new DeleteProductsPhotoAdapter(this, productImageList);
        recyclerView9.setAdapter(deleteProductsPhotoAdapter);
        viewProductsPopulate();

//location

        latitude=MidhunUtils.localData(FileActivity.this,"location","lati");
        longitude=MidhunUtils.localData(FileActivity.this,"location","longi");
        city=MidhunUtils.localData(FileActivity.this,"location","city");
        pincode=MidhunUtils.localData(FileActivity.this,"location","pincode");
        state=MidhunUtils.localData(FileActivity.this,"location","state");

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        pick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGalley();
            }
        });
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                if (detail_images.size() < 1 ) {

                        Toast.makeText(FileActivity.this, "Add images", Toast.LENGTH_SHORT).show();
                } else {
                    ProgressDialog progress = ProgressDialog.show(FileActivity.this, null, null, true);
                    progress.setContentView(R.layout.progress_layout);
                    progress.setCancelable(false);
                    progress.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

                    progress.show();

                    if (intent.hasExtra("product_id")) {
                        uploadImageEdit();
                    }
                    else{
                        uploadImage();
                    }

                }
            }
        });

        if ((ActivityCompat.checkSelfPermission(
                this, colum[0]) != PackageManager.PERMISSION_GRANTED) &&
                (ActivityCompat.checkSelfPermission(
                        this, colum[1]) != PackageManager.PERMISSION_GRANTED)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(colum, 123);
            }
        }

    }


    private void openGalley() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Selcet Picture"), 123);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 123 && resultCode == RESULT_OK) {
            if (data.getClipData() != null) {
                int x = data.getClipData().getItemCount();


                    for (int j = 0; j < x; j++) {
                        list.add(data.getClipData().getItemAt(j).getUri());
                        final Uri imageUri = data.getClipData().getItemAt(j).getUri();
                        final InputStream imageStream;
                        try {
                            imageStream = getContentResolver().openInputStream(imageUri);
                            final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                            String encodedImage = encodeImage(selectedImage);
                            detail_images.add(j, encodedImage);

                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                    //end

                adaptor.notifyDataSetChanged();


            } else if (data.getData() != null) {
                String imgurl = data.getData().getPath();
                list.add(Uri.parse(imgurl));

            }
        }
    }

    private String encodeImage(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();
        String encImage = "data:image/png;base64," + Base64.encodeToString(b, Base64.DEFAULT);

        return encImage;
    }

    private void uploadImage() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);
        Call<Images> call = api.addProduct("Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6Ikhhd2tzIFNvbHV0aW9ucyIsImFkbWluIjp0cnVlfQ.B5RoeVIIAVuLBbkq_yCFoZMmy4gXmiaIF-tSM3yPzQM",
                "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6Ikhhd2tzIFNvbHV0aW9ucyIsImFkbWluIjp0cnVlfQ.B5RoeVIIAVuLBbkq_yCFoZMmy4gXmiaIF-tSM3yPzQM", getIntent().getExtras().getString("title"), detail_images.get(0), "00", UID, getIntent().getExtras().getString("category_id"), getIntent().getExtras().getString("brand_id"), getIntent().getExtras().getString("price"), "0", "null", "0", getIntent().getExtras().getString("desc"), detail_images, latitude, longitude,city,pincode,state);
        call.enqueue(new Callback<Images>() {
            @Override
            public void onResponse(Call<Images> call, Response<Images> response) {
                if (response.code() == 200) {
                    Intent next = new Intent();
                    next.setClass(FileActivity.this, MainHomeActivity.class);
                    startActivity(next);
                    finish();
                    Toast.makeText(FileActivity.this, "success", Toast.LENGTH_SHORT).show();
                }
                if (response.code() == 500) {
                    Intent next = new Intent();
                    next.setClass(FileActivity.this, MainHomeActivity.class);
                    startActivity(next);
                    finish();
                    Toast.makeText(FileActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Images> call, Throwable t) {
                Intent next = new Intent();
                next.setClass(FileActivity.this, MainHomeActivity.class);
                startActivity(next);
                finish();
                Toast.makeText(FileActivity.this, "failed", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void uploadImageEdit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);
        Call<ImagesEdit> call = api.addProductEdit("Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6Ikhhd2tzIFNvbHV0aW9ucyIsImFkbWluIjp0cnVlfQ.B5RoeVIIAVuLBbkq_yCFoZMmy4gXmiaIF-tSM3yPzQM",
                "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6Ikhhd2tzIFNvbHV0aW9ucyIsImFkbWluIjp0cnVlfQ.B5RoeVIIAVuLBbkq_yCFoZMmy4gXmiaIF-tSM3yPzQM",getIntent().getExtras().getString("product_id"),getIntent().getExtras().getString("title"), detail_images.get(0), "00", UID, getIntent().getExtras().getString("category_id"), getIntent().getExtras().getString("brand_id"), getIntent().getExtras().getString("price"), "0", "null", "0", getIntent().getExtras().getString("desc"), detail_images, latitude, longitude,city,pincode,state);
        call.enqueue(new Callback<ImagesEdit>() {
            @Override
            public void onResponse(Call<ImagesEdit> call, Response<ImagesEdit> response) {
                if (response.code() == 200) {
                    Intent next = new Intent();
                    next.setClass(FileActivity.this, MainHomeActivity.class);
                    startActivity(next);
                    finish();
                    Toast.makeText(FileActivity.this, "success", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ImagesEdit> call, Throwable t) {
                Intent next = new Intent();
                next.setClass(FileActivity.this, MainHomeActivity.class);
                startActivity(next);
                finish();
               // Toast.makeText(FileActivity.this, "failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void viewProductsPopulate() {
        ViewProductsViewModel model5 = ViewModelProviders.of(FileActivity.this).get(ViewProductsViewModel.class);
        productImageList = new ArrayList<>();
        model5.getProductById(getIntent().getExtras().getString("product_id")).observe(FileActivity.this, new Observer<List<ProductById>>() {
            @Override
            public void onChanged(List<ProductById> products) {

                productImageList.addAll(products);


                int Length = productImageList.size();
                int index = Length - 1;

                for (int i = 0; i < Length; i++) {
                    Log.e("Products response", "" + productImageList.get(index).getName());
//                        if(productsList.get(index).getBrand().equals("Adidas")){
//
//                        }
//                        else
//                        {
//                            productsList.remove(index);
//                        }
                    index--;
                }


//                categoryadapter.set
                deleteProductsPhotoAdapter = new DeleteProductsPhotoAdapter(getApplicationContext(), productImageList);
                recyclerView9.setAdapter(deleteProductsPhotoAdapter);


            }
        });
    }
}