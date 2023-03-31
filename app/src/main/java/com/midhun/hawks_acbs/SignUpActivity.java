package com.midhun.hawks_acbs;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ResultReceiver;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.midhun.hawks_acbs.Location.Constants;
import com.midhun.hawks_acbs.Location.FetchAddressIntentServices;
import com.midhun.hawks_acbs.Model.UserApiModel;
import com.midhun.hawks_acbs.Util.MidhunUtils;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUpActivity extends AppCompatActivity {
    private TextView signup, login;
    private EditText signup_name, signup_email, signup_city, signup_mobile, signup_town, signup_password, signup_state, signup_pincode;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    ResultReceiver resultReceiver;
    public String Longitude, Latitude;
    ProgressDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        signup = findViewById(R.id.signup);
        signup_name = findViewById(R.id.signup_name);
        signup_email = findViewById(R.id.signup_email);
        signup_mobile = findViewById(R.id.signup_mobile);
        signup_password = findViewById(R.id.signup_password);
        signup_city = findViewById(R.id.signup_city);
        signup_town = findViewById(R.id.signup_town);
        signup_state = findViewById(R.id.signup_state);
        signup_pincode = findViewById(R.id.signup_pincode);
        resultReceiver = new SignUpActivity.AddressResultReceiver(new Handler());
        MidhunUtils.setStatusBarIcon(SignUpActivity.this, true);


        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SignInActivity.class));
           finish();
            }
        });
        Objects.requireNonNull(getSupportActionBar()).hide();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        if (ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(SignUpActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
        } else {
            getCurrentLocation();

        }
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(signup_name.getText().toString().isEmpty()){
                    signup_name.setError("Field required");
                    MidhunUtils.showSnackBarMsg(getApplicationContext(),signup,"Field Required","close");
                    signup_name.requestFocus();
                }
                else if(signup_email.getText().toString().isEmpty()){
                    signup_email.setError("Field required");
                    MidhunUtils.showSnackBarMsg(getApplicationContext(),signup,"Field Required","close");
                    signup_email.requestFocus();
                }
                else if(signup_mobile.getText().toString().isEmpty()){
                    signup_mobile.setError("Field required");
                    MidhunUtils.showSnackBarMsg(getApplicationContext(),signup,"Field Required","close");
                    signup_mobile.requestFocus();
                }
                else if(signup_password.getText().toString().isEmpty()){
                    signup_password.setError("Field required");
                    MidhunUtils.showSnackBarMsg(getApplicationContext(),signup,"Field Required","close");
                    signup_password.requestFocus();
                }
                else if(signup_city.getText().toString().isEmpty()){
                    signup_city.setError("Field required");
                    MidhunUtils.showSnackBarMsg(getApplicationContext(),signup,"Field Required","close");
                    signup_city.requestFocus();
                }
                else if(signup_town.getText().toString().isEmpty()){
                    signup_town.setError("Field required");
                    MidhunUtils.showSnackBarMsg(getApplicationContext(),signup,"Field Required","close");
                    signup_town.requestFocus();

                }
                else if(signup_pincode.getText().toString().isEmpty()){
                    signup_pincode.setError("Field required");
                    MidhunUtils.showSnackBarMsg(getApplicationContext(),signup,"Field Required","close");
                    signup_pincode.requestFocus();
                }
                else if(signup_state.getText().toString().isEmpty()){
                    signup_state.setError("Field required");
                    MidhunUtils.showSnackBarMsg(getApplicationContext(),signup,"Field Required","close");
                    signup_state.requestFocus();
                }
                else {
                    Toast.makeText(SignUpActivity.this, "Please Wait", Toast.LENGTH_SHORT).show();
                    progress = ProgressDialog.show(SignUpActivity.this, null, null, true);
                    progress.setContentView(R.layout.progress_layout);
                    progress.setCancelable(false);
                    progress.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

                    progress.show();
                    create();
                }
            }
        });

    }

    public void create() {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);
        Call<UserApiModel> call = api.signUP("Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6Ikhhd2tzIFNvbHV0aW9ucyIsImFkbWluIjp0cnVlfQ.B5RoeVIIAVuLBbkq_yCFoZMmy4gXmiaIF-tSM3yPzQM",
                "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6Ikhhd2tzIFNvbHV0aW9ucyIsImFkbWluIjp0cnVlfQ.B5RoeVIIAVuLBbkq_yCFoZMmy4gXmiaIF-tSM3yPzQM", signup_name.getText().toString(), signup_email.getText().toString(), signup_mobile.getText().toString(), signup_password.getText().toString(), signup_city.getText().toString(), signup_town.getText().toString(), "", Latitude, Longitude, signup_city.getText().toString(), signup_pincode.getText().toString(), signup_state.getText().toString());

        call.enqueue(new Callback<UserApiModel>() {
            @Override
            public void onResponse(Call<UserApiModel> call, Response<UserApiModel> response) {

                if (response.code() == 200) {

                    if (response.body().getStatus().equalsIgnoreCase("1")) {
                        String UID = response.body().getDetails().getId();
                        SharedPreferences sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
                        SharedPreferences.Editor myEdit = sharedPreferences.edit();
                        myEdit.putString("UID", UID);
                        myEdit.commit();
                        Log.e("response signup", "" + response.body().getDetails().getLatitude());
                        Toast.makeText(SignUpActivity.this, "success", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), MainHomeActivity.class));
                        progress.dismiss();
                    } else {
                        progress.dismiss();
                        MidhunUtils.showSnackBarMsg(getApplicationContext(),signup,"User already exist","Close");
                    }

                }

                if (response.code() == 500) {
                    progress.dismiss();
                    Toast.makeText(SignUpActivity.this, "something went wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserApiModel> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(SignUpActivity.this, "Something went fishy", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE && grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getCurrentLocation();
            } else {
                Toast.makeText(this, "Permission is denied!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void getCurrentLocation() {
        //  progressBar.setVisibility(View.VISIBLE);
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(3000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        LocationServices.getFusedLocationProviderClient(SignUpActivity.this)
                .requestLocationUpdates(locationRequest, new LocationCallback() {

                    @Override
                    public void onLocationResult(LocationResult locationResult) {
                        super.onLocationResult(locationResult);
                        LocationServices.getFusedLocationProviderClient(getApplicationContext())
                                .removeLocationUpdates(this);
                        if (locationResult != null && locationResult.getLocations().size() > 0) {
                            int latestlocIndex = locationResult.getLocations().size() - 1;
                            double lati = locationResult.getLocations().get(latestlocIndex).getLatitude();
                            double longi = locationResult.getLocations().get(latestlocIndex).getLongitude();
                            //   textLatLong.setText(String.format("Latitude : %s\n Longitude: %s", lati, longi));
//                            Toast.makeText(SignUpActivity.this, String.valueOf(lati), Toast.LENGTH_SHORT).show();
//                            Toast.makeText(SignUpActivity.this, String.valueOf(longi), Toast.LENGTH_SHORT).show();

                            Latitude = String.valueOf(lati);
                            Longitude = String.valueOf(longi);

                            SharedPreferences sharedPreferences = getSharedPreferences("location", Context.MODE_PRIVATE);
                            SharedPreferences.Editor myEdit = sharedPreferences.edit();
                            myEdit.putString("lati", Latitude);
                            myEdit.putString("longi", Longitude);
                            myEdit.putString("city", signup_city.getText().toString());
                            myEdit.commit();

                            Location location = new Location("providerNA");
                            location.setLongitude(longi);
                            location.setLatitude(lati);
                            fetchaddressfromlocation(location);

                        } else {
                            //progressBar.setVisibility(View.GONE);

                        }
                    }
                }, Looper.getMainLooper());

    }

    private class AddressResultReceiver extends ResultReceiver {
        public AddressResultReceiver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            super.onReceiveResult(resultCode, resultData);
            if (resultCode == Constants.SUCCESS_RESULT) {
                //signup_city.setText(resultData.getString(Constants.ADDRESS));
                signup_city.setText(resultData.getString(Constants.LOCAITY));
                signup_state.setText(resultData.getString(Constants.STATE));
                signup_town.setText(resultData.getString(Constants.DISTRICT));
//                country.setText(resultData.getString(Constants.COUNTRY));
                signup_pincode.setText(resultData.getString(Constants.POST_CODE));
                SharedPreferences sharedPreferences = getSharedPreferences("location", Context.MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();

                myEdit.putString("city", signup_city.getText().toString());
                myEdit.putString("pincode", signup_pincode.getText().toString());
                myEdit.putString("state", signup_state.getText().toString());
                myEdit.commit();
            } else {
                Toast.makeText(SignUpActivity.this, resultData.getString(Constants.RESULT_DATA_KEY), Toast.LENGTH_SHORT).show();
            }
            //progressBar.setVisibility(View.GONE);
        }


    }


    private void fetchaddressfromlocation(Location location) {
        Intent intent = new Intent(this, FetchAddressIntentServices.class);
        intent.putExtra(Constants.RECEVIER, resultReceiver);
        intent.putExtra(Constants.LOCATION_DATA_EXTRA, location);
        startService(intent);
    }
}