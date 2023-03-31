package com.midhun.hawks_acbs;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.os.Build.VERSION.SDK_INT;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.midhun.hawks_acbs.Util.MidhunUtils;
import com.midhun.hawks_acbs.View.Images;
import com.midhun.hawks_acbs.View.ImagesEdit;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddImageActivity extends AppCompatActivity {
    private static final int PERMISSION_REQUEST_CODE = 2296;
    ImageView pick, pick1, pick2, pick3, pick4, pick5, pick6, img_back;
    int count = 0, cover = 0;
    TextView post;
    String temp, coverbase64 = "null", pick1base64, pick2base64, pick3base64, pick4base64, pick5base64, pick6base64;
    List<String> detail_images;
    String UID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_image);
        pick = findViewById(R.id.product_image);
        pick1 = findViewById(R.id.pick1);
        pick2 = findViewById(R.id.pick2);
        pick3 = findViewById(R.id.pick3);
        pick4 = findViewById(R.id.pick4);
        pick5 = findViewById(R.id.pick5);
        pick6 = findViewById(R.id.pick6);
        post = findViewById(R.id.post);
        img_back = findViewById(R.id.img_back);
        getSupportActionBar().hide();
        MidhunUtils.setStatusBarIcon(AddImageActivity.this, true);

        //user

        SharedPreferences sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
        UID = sharedPreferences.getString("UID", "");

        if (!checkPermission()) {
            // requestPermission();
        }
        detail_images = new ArrayList<>();
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count == 0 || cover == 0) {
                    MidhunUtils.showSnackBarMsg(AddImageActivity.this, post, "Add atleast two photos", "dismiss");
                } else {
                    Toast.makeText(AddImageActivity.this, "Please Wait", Toast.LENGTH_SHORT).show();

                    ProgressDialog progress = ProgressDialog.show(AddImageActivity.this, null, null, true);
                    progress.setContentView(R.layout.progress_layout);
                    progress.setCancelable(false);
                    progress.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

                    progress.show();
                    Intent intent = getIntent();

                    if (intent.hasExtra("product_id")) {
                        uploadImageEdit();
                    }
                    else{
                        uploadImage();
                    }
                    // Toast.makeText(AddImageActivity.this, Integer.toString(count), Toast.LENGTH_SHORT).show();
                }
            }
        });
        pick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageChooser();
            }
        });

        pick1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in1 = new Intent();
                in1.setType("image/*");
                in1.setAction(Intent.ACTION_GET_CONTENT);
                SomeAction1.launch(in1);
            }
        });

        pick2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in2 = new Intent();
                in2.setType("image/*");
                in2.setAction(Intent.ACTION_GET_CONTENT);
                SomeAction2.launch(in2);
            }
        });

        pick3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in3 = new Intent();
                in3.setType("image/*");
                in3.setAction(Intent.ACTION_GET_CONTENT);
                SomeAction3.launch(in3);
            }
        });

        pick4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in4 = new Intent();
                in4.setType("image/*");
                in4.setAction(Intent.ACTION_GET_CONTENT);
                SomeAction4.launch(in4);
            }
        });


        pick5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent();
                in.setType("image/*");
                in.setAction(Intent.ACTION_GET_CONTENT);
                SomeAction5.launch(in);
            }
        });

        pick6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in5 = new Intent();
                in5.setType("image/*");
                in5.setAction(Intent.ACTION_GET_CONTENT);
                SomeAction6.launch(in5);
            }
        });

    }

    private void imageChooser() {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        SomeAction.launch(i);
    }

    ActivityResultLauncher<Intent> SomeAction
            = registerForActivityResult(
            new ActivityResultContracts
                    .StartActivityForResult(),
            result -> {
                if (result.getResultCode()
                        == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    // do your operation from here....
                    if (data != null
                            && data.getData() != null) {
                        //setImage(pick, data);
                        cover++;

                        Uri selectedImageUri = data.getData();
                        Bitmap selectedImageBitmap = null;
                        try {
                            selectedImageBitmap
                                    = MediaStore.Images.Media.getBitmap(
                                    this.getContentResolver(),
                                    selectedImageUri);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        pick.setImageBitmap(
                                selectedImageBitmap);
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        selectedImageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                        byte[] b = baos.toByteArray();
                        coverbase64 = "data:image/png;base64," + Base64.encodeToString(b, Base64.DEFAULT);
                        detail_images.add(0, coverbase64);

                    }
                }
            });


    ActivityResultLauncher<Intent> SomeAction1
            = registerForActivityResult(
            new ActivityResultContracts
                    .StartActivityForResult(),
            result -> {
                if (result.getResultCode()
                        == Activity.RESULT_OK) {
                    Intent data1 = result.getData();
                    // do your operation from here....
                    if (data1 != null
                            && data1.getData() != null) {

                        count++;


                        Uri selectedImageUri1 = data1.getData();
                        Bitmap selectedImageBitmap1 = null;
                        try {
                            selectedImageBitmap1
                                    = MediaStore.Images.Media.getBitmap(
                                    this.getContentResolver(),
                                    selectedImageUri1);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        pick1.setImageBitmap(
                                selectedImageBitmap1);
                        ByteArrayOutputStream baos1 = new ByteArrayOutputStream();
                        selectedImageBitmap1.compress(Bitmap.CompressFormat.JPEG, 100, baos1);
                        byte[] b1 = baos1.toByteArray();
                        pick1base64 = "data:image/png;base64," + Base64.encodeToString(b1, Base64.DEFAULT);
                        detail_images.add(1, pick1base64);

//                        BitMapToString(selectedImageBitmap);
                        // Midhun.showMsg(BitMapToString(selectedImageBitmap), getApplicationContext());
                    }
                }
            });

    ActivityResultLauncher<Intent> SomeAction2
            = registerForActivityResult(
            new ActivityResultContracts
                    .StartActivityForResult(),
            result -> {
                if (result.getResultCode()
                        == Activity.RESULT_OK) {
                    Intent data2 = result.getData();
                    // do your operation from here....
                    if (data2 != null
                            && data2.getData() != null) {
                        //setImage(pick2, data1);
                        count++;

                        Uri selectedImageUri2 = data2.getData();
                        Bitmap selectedImageBitmap2 = null;
                        try {
                            selectedImageBitmap2
                                    = MediaStore.Images.Media.getBitmap(
                                    this.getContentResolver(),
                                    selectedImageUri2);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        pick2.setImageBitmap(
                                selectedImageBitmap2);
                        ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
                        selectedImageBitmap2.compress(Bitmap.CompressFormat.JPEG, 100, baos2);
                        byte[] b2 = baos2.toByteArray();
                        pick2base64 = "data:image/png;base64," + Base64.encodeToString(b2, Base64.DEFAULT);
                        detail_images.add(2, pick2base64);

                    }
                }
            });

    ActivityResultLauncher<Intent> SomeAction3
            = registerForActivityResult(
            new ActivityResultContracts
                    .StartActivityForResult(),
            result -> {
                if (result.getResultCode()
                        == Activity.RESULT_OK) {
                    Intent data3 = result.getData();
                    // do your operation from here....
                    if (data3 != null
                            && data3.getData() != null) {
                        count++;

                        Uri selectedImageUri3 = data3.getData();
                        Bitmap selectedImageBitmap3 = null;
                        try {
                            selectedImageBitmap3
                                    = MediaStore.Images.Media.getBitmap(
                                    this.getContentResolver(),
                                    selectedImageUri3);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        pick3.setImageBitmap(
                                selectedImageBitmap3);
                        ByteArrayOutputStream baos3 = new ByteArrayOutputStream();
                        selectedImageBitmap3.compress(Bitmap.CompressFormat.JPEG, 100, baos3);
                        byte[] b3 = baos3.toByteArray();
                        pick3base64 = "data:image/png;base64," + Base64.encodeToString(b3, Base64.DEFAULT);
                        detail_images.add(3, pick3base64);

                    }
                }
            });

    ActivityResultLauncher<Intent> SomeAction4
            = registerForActivityResult(
            new ActivityResultContracts
                    .StartActivityForResult(),
            result -> {
                if (result.getResultCode()
                        == Activity.RESULT_OK) {
                    Intent data4 = result.getData();
                    // do your operation from here....
                    if (data4 != null
                            && data4.getData() != null) {

                        count++;

                        Uri selectedImageUri4 = data4.getData();
                        Bitmap selectedImageBitmap4 = null;
                        try {
                            selectedImageBitmap4
                                    = MediaStore.Images.Media.getBitmap(
                                    this.getContentResolver(),
                                    selectedImageUri4);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        pick4.setImageBitmap(
                                selectedImageBitmap4);
                        ByteArrayOutputStream baos4 = new ByteArrayOutputStream();
                        selectedImageBitmap4.compress(Bitmap.CompressFormat.JPEG, 100, baos4);
                        byte[] b4 = baos4.toByteArray();
                        pick4base64 = "data:image/png;base64," + Base64.encodeToString(b4, Base64.DEFAULT);
                        detail_images.add(4, pick4base64);


                    }
                }
            });


    ActivityResultLauncher<Intent> SomeAction5
            = registerForActivityResult(
            new ActivityResultContracts
                    .StartActivityForResult(),
            result -> {
                if (result.getResultCode()
                        == Activity.RESULT_OK) {
                    Intent data5 = result.getData();
                    // do your operation from here....
                    if (data5 != null
                            && data5.getData() != null) {
                        count++;
                        Uri selectedImageUri5 = data5.getData();
                        Bitmap selectedImageBitmap5 = null;
                        try {
                            selectedImageBitmap5
                                    = MediaStore.Images.Media.getBitmap(
                                    this.getContentResolver(),
                                    selectedImageUri5);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        pick5.setImageBitmap(
                                selectedImageBitmap5);
                        ByteArrayOutputStream baos5 = new ByteArrayOutputStream();
                        selectedImageBitmap5.compress(Bitmap.CompressFormat.JPEG, 100, baos5);
                        byte[] b5 = baos5.toByteArray();
                        pick5base64 = "data:image/png;base64," + Base64.encodeToString(b5, Base64.DEFAULT);
                        detail_images.add(5, pick5base64);

                    }
                }
            });


    ActivityResultLauncher<Intent> SomeAction6
            = registerForActivityResult(
            new ActivityResultContracts
                    .StartActivityForResult(),
            result -> {
                if (result.getResultCode()
                        == Activity.RESULT_OK) {
                    Intent data6 = result.getData();
                    // do your operation from here....
                    if (data6 != null
                            && data6.getData() != null) {

                        count++;
                        Uri selectedImageUri6 = data6.getData();
                        Bitmap selectedImageBitmap6 = null;
                        try {
                            selectedImageBitmap6
                                    = MediaStore.Images.Media.getBitmap(
                                    this.getContentResolver(),
                                    selectedImageUri6);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        pick6.setImageBitmap(
                                selectedImageBitmap6);
                        ByteArrayOutputStream baos6 = new ByteArrayOutputStream();
                        selectedImageBitmap6.compress(Bitmap.CompressFormat.JPEG, 100, baos6);
                        byte[] b6 = baos6.toByteArray();
                        pick6base64 = "data:image/png;base64," + Base64.encodeToString(b6, Base64.DEFAULT);
                        detail_images.add(6, pick6base64);

                    }
                }
            });


    private void setImage(ImageView image, Intent data) {
        Uri selectedImageUri = data.getData();
        Bitmap selectedImageBitmap = null;
        try {
            selectedImageBitmap
                    = MediaStore.Images.Media.getBitmap(
                    this.getContentResolver(),
                    selectedImageUri);
        } catch (IOException e) {
            e.printStackTrace();
        }
        image.setImageBitmap(
                selectedImageBitmap);


    }

    public String BitMapToString(Bitmap bitmap, String temp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();
        temp = Base64.encodeToString(b, Base64.DEFAULT);
        //txt.setText(temp);
        return "data:image/png;base64," + temp;
    }

    private void uploadImage() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);
        Call<Images> call = api.addProduct("Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6Ikhhd2tzIFNvbHV0aW9ucyIsImFkbWluIjp0cnVlfQ.B5RoeVIIAVuLBbkq_yCFoZMmy4gXmiaIF-tSM3yPzQM",
                "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6Ikhhd2tzIFNvbHV0aW9ucyIsImFkbWluIjp0cnVlfQ.B5RoeVIIAVuLBbkq_yCFoZMmy4gXmiaIF-tSM3yPzQM", getIntent().getExtras().getString("title"), coverbase64, "00", UID, getIntent().getExtras().getString("category_id"), getIntent().getExtras().getString("brand_id"), getIntent().getExtras().getString("price"), "0", "null", "0", getIntent().getExtras().getString("desc"), detail_images, "", "","","","");
        call.enqueue(new Callback<Images>() {
            @Override
            public void onResponse(Call<Images> call, Response<Images> response) {
                if (response.code() == 200) {
                    Intent next = new Intent();
                    next.setClass(AddImageActivity.this, MainHomeActivity.class);
                    startActivity(next);
                    finish();
                    Toast.makeText(AddImageActivity.this, "success", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Images> call, Throwable t) {
                Intent next = new Intent();
                next.setClass(AddImageActivity.this, MainHomeActivity.class);
                startActivity(next);
                finish();
                Toast.makeText(AddImageActivity.this, "failed", Toast.LENGTH_SHORT).show();
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
                "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6Ikhhd2tzIFNvbHV0aW9ucyIsImFkbWluIjp0cnVlfQ.B5RoeVIIAVuLBbkq_yCFoZMmy4gXmiaIF-tSM3yPzQM",getIntent().getExtras().getString("product_id"),getIntent().getExtras().getString("title"), coverbase64, "00", UID, getIntent().getExtras().getString("category_id"), getIntent().getExtras().getString("brand_id"), getIntent().getExtras().getString("price"), "0", "null", "0", getIntent().getExtras().getString("desc"), detail_images, "", "","","","");
        call.enqueue(new Callback<ImagesEdit>() {
            @Override
            public void onResponse(Call<ImagesEdit> call, Response<ImagesEdit> response) {
                if (response.code() == 200) {
                    Intent next = new Intent();
                    next.setClass(AddImageActivity.this, MainHomeActivity.class);
                    startActivity(next);
                    finish();
                    Toast.makeText(AddImageActivity.this, "success", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ImagesEdit> call, Throwable t) {
                Intent next = new Intent();
                next.setClass(AddImageActivity.this, MainHomeActivity.class);
                startActivity(next);
                finish();
                Toast.makeText(AddImageActivity.this, "failed", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void requestPermission() {
        if (SDK_INT >= Build.VERSION_CODES.R) {
            try {
                Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                intent.addCategory("android.intent.category.DEFAULT");
                intent.setData(Uri.parse(String.format("package:%s", getApplicationContext().getPackageName())));
                startActivityForResult(intent, 2296);
            } catch (Exception e) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                startActivityForResult(intent, 2296);
            }
        } else {
            //below android 11
            ActivityCompat.requestPermissions(AddImageActivity.this, new String[]{WRITE_EXTERNAL_STORAGE}, 2296);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2296) {
            if (SDK_INT >= Build.VERSION_CODES.R) {
                if (Environment.isExternalStorageManager()) {
                    // perform action when allow permission success
                } else {
                    Toast.makeText(this, "Allow permission for storage access!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0) {
                    boolean READ_EXTERNAL_STORAGE = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean WRITE_EXTERNAL_STORAGE = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                    if (READ_EXTERNAL_STORAGE && WRITE_EXTERNAL_STORAGE) {
                        // perform action when allow permission success
                    } else {
                        Toast.makeText(this, "Allow permission for storage access!", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }


    }

    private boolean checkPermission() {
        if (SDK_INT >= Build.VERSION_CODES.R) {
            return Environment.isExternalStorageManager();
        } else {
            int result = ContextCompat.checkSelfPermission(AddImageActivity.this, READ_EXTERNAL_STORAGE);
            int result1 = ContextCompat.checkSelfPermission(AddImageActivity.this, WRITE_EXTERNAL_STORAGE);
            return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED;
        }
    }

}