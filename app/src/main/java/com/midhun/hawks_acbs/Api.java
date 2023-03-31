package com.midhun.hawks_acbs;


import com.midhun.hawks_acbs.Model.AddProductCategoriesApiModel;
import com.midhun.hawks_acbs.Model.BrandsApiModel;
import com.midhun.hawks_acbs.Model.FavApiModel;
import com.midhun.hawks_acbs.Model.FilterApiModel;
import com.midhun.hawks_acbs.Model.MyProfile;
import com.midhun.hawks_acbs.Model.ProductsApiModel;
import com.midhun.hawks_acbs.Model.SearchApiModel;
import com.midhun.hawks_acbs.Model.SignInModel;
import com.midhun.hawks_acbs.Model.SortApiModel;
import com.midhun.hawks_acbs.Model.UserApiModel;
import com.midhun.hawks_acbs.Model.UserProductsApiModel;
import com.midhun.hawks_acbs.View.Delete;
import com.midhun.hawks_acbs.View.ImageDelete;
import com.midhun.hawks_acbs.View.ImagesEdit;
import com.midhun.hawks_acbs.View.Pwd;
import com.midhun.hawks_acbs.View.RemoveWishList;
import com.midhun.hawks_acbs.View.UserModel;
import com.midhun.hawks_acbs.Model.ViewProductsApiModel;
import com.midhun.hawks_acbs.Model.ViewProfileApiModel;
import com.midhun.hawks_acbs.Model.WishListModel;
import com.midhun.hawks_acbs.Model.CategoriesApiModel;
import com.midhun.hawks_acbs.View.Images;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Api {

    String BASE_URL = "https://acbs.hawkssolutions.com/public/v1/";

    @FormUrlEncoded
    @POST("user/getCategory")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    Call<CategoriesApiModel> getCategories(@Header("Authorization") String authorization,

                                           @Field("table") String table,
                                           @Field("offset") String offset,
                                           @Field("pageLimit") String pageLimit);

    @FormUrlEncoded
    @POST("user/getBrandByCategory")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    Call<BrandsApiModel> getBrands(@Field("Authorization") String Authorization,
                                   @Header("Authorization") String authorization,

                                   @Field("table") String table,
                                   @Field("offset") String offset,
                                   @Field("pageLimit") String pageLimit,
                                   @Field("category") String category);

    @FormUrlEncoded
    @POST("user/getProducts")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    Call<ProductsApiModel> getProducts(@Field("Authorization") String Authorization,
                                       @Header("Authorization") String authorization,

                                       @Field("table") String table,
                                       @Field("offset") String offset,
                                       @Field("pageLimit") String pageLimit);

    @FormUrlEncoded
    @POST("common/authenticate")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    Call<List<MyProfile>> authenticate(@Field("Authorization") String Authorization,
                                       @Header("Authorization") String authorization,
                                       @Field("username") String username,
                                       @Field("password") String password
    );

    @FormUrlEncoded
    @POST("common/authenticate")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    Call<SignInModel> authenticate1(@Field("Authorization") String Authorization,
                                    @Header("Authorization") String authorization,
                                    @Field("username") String username,
                                    @Field("password") String password
    );

    @FormUrlEncoded
    @POST("user/getProductById")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    Call<ViewProductsApiModel> getProductById(@Field("Authorization") String Authorization,
                                              @Header("Authorization") String authorization,
                                              @Field("table") String table,
                                              @Field("id") String id
    );

    @FormUrlEncoded
    @POST("common/signUP")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    Call<UserApiModel> signUP(@Field("Authorization") String Authorization,
                              @Header("Authorization") String authorization,
                              @Field("name") String name,
                              @Field("email") String email,
                              @Field("mobile") String mobile,
                              @Field("password") String password,
                              @Field("address") String address,
                              @Field("location") String location,
                              @Field("imageUrl") String imageUrl,
                              @Field("latitude") String latitude,
                              @Field("longitude") String longitude,
                              @Field("city") String city,
                              @Field("pincode") String pincode,
                              @Field("state") String state);

    @FormUrlEncoded
    @POST("addwishList")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    Call<WishListModel> addwishList(@Field("Authorization") String Authorization,
                                    @Header("Authorization") String authorization,

                                    @Field("userid") String userid,
                                    @Field("productid") String id);


    @FormUrlEncoded
    @POST("addwishList/remove")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    Call<WishListModel> removewishList(@Field("Authorization") String Authorization,
                                    @Header("Authorization") String authorization,


                                    @Field("id") String id);


    @FormUrlEncoded
    @POST("addwishList/get")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    Call<FavApiModel> getwishList(@Field("Authorization") String Authorization,
                                  @Header("Authorization") String authorization,
                                  @Field("userid") String id);

    @FormUrlEncoded
    @POST("user/getCategory")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    Call<AddProductCategoriesApiModel> getAddProductCategories(@Header("Authorization") String authorization,

                                                               @Field("table") String table,
                                                               @Field("offset") String offset,
                                                               @Field("pageLimit") String pageLimit);


    @FormUrlEncoded
    @POST("user/getuser")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    Call<ViewProfileApiModel> getUser(@Field("Authorization") String Authorization,
                                      @Header("Authorization") String authorization,
                                      @Field("userid") String userid);

    @FormUrlEncoded
    @POST("user/updateProfile")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    Call<UserModel> editUser(@Field("Authorization") String Authorization,
                             @Header("Authorization") String authorization,
                             @Field("name") String name,
                             @Field("email") String email,
                             @Field("address") String address,
                             @Field("location") String location,
                             @Field("imageUrl") String imageUrl,
                             @Field("id") String id,
                             @Field("city") String city,
                             @Field("pincode") String pincode,
                             @Field("state") String state,
                             @Field("latitude") String latitude,
                             @Field("longitude") String longitude);

    @FormUrlEncoded
    @POST("user/addNewProduct")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    Call<Images> addProduct(@Field("Authorization") String Authorization,
                          @Header("Authorization") String authorization,
                          @Field("name") String name,
                          @Field("imageUrl") String imagename,
                          @Field("code") String code,
                          @Field("customer") String customer,
                          @Field("category") String category,
                          @Field("brand") String brand,
                          @Field("amount") String amount,
                          @Field("discount") String discount,
                          @Field("hsn") String hsn,
                          @Field("tax") String tax,
                          @Field("description") String description,
                          @Field("detail_images[]") List<String> detail_images,
                          @Field("latitude") String latitude,
                          @Field("longitude") String longitude,
                          @Field("city") String city,
                          @Field("pincode") String pincode,
                          @Field("state") String state);


    @FormUrlEncoded
    @POST("user/editProduct")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    Call<ImagesEdit> addProductEdit(@Field("Authorization") String Authorization,
                                    @Header("Authorization") String authorization,
                                    @Field("id") String productid,
                                    @Field("name") String name,
                                    @Field("imageUrl") String imagename,
                                    @Field("code") String code,
                                    @Field("customer") String customer,
                                    @Field("category") String category,
                                    @Field("brand") String brand,
                                    @Field("amount") String amount,
                                    @Field("discount") String discount,
                                    @Field("hsn") String hsn,
                                    @Field("tax") String tax,
                                    @Field("description") String description,
                                    @Field("imagenames[]") List<String> detail_images,
                                    @Field("latitude") String latitude,
                                    @Field("longitude") String longitude,
                                    @Field("city") String city,
                                    @Field("pincode") String pincode,
                                    @Field("state") String state);
    @FormUrlEncoded
    @POST("product/searchproduct")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    Call<SearchApiModel> getSearch(@Field("Authorization") String Authorization,
                                   @Header("Authorization") String authorization,
                                   @Field("offset") String offset,
                                   @Field("pageLimit") String pageLimit,
                                   @Field("categoryvalue") String categoryvalue,
                                   @Field("locationvalue") String locatipnvalue,
                                   @Field("searchvalue") String searchvalue
                                    );

    @FormUrlEncoded
    @POST("product/sortproduct")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    Call<SortApiModel> getSortList(@Field("Authorization") String Authorization,
                                   @Header("Authorization") String authorization,

                                   @Field("sort") String sort,
                                   @Field("category") String category,
                                   @Field("offset") String offset,
                                   @Field("pageLimit") String pageLimit);


    @FormUrlEncoded
    @POST("product/filterProduct")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    Call<FilterApiModel> getFilterList(@Field("Authorization") String Authorization,
                                       @Header("Authorization") String authorization,
                                       @Field("maxvalue") String maxvalue,
                                       @Field("minvalue") String minvalue,
                                       @Field("category") String category,
                                       @Field("brand") String brand,
                                       @Field("longitude") String longitude,
                                       @Field("latitude") String latitude
    );
    @FormUrlEncoded
    @POST("common/changepassword")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    Call<Pwd> changePwd(@Field("Authorization") String Authorization,
                        @Header("Authorization") String authorization,
                        @Field("newPaswrd") String newPaswrd,
                        @Field("loginId") String loginId
    );
    @FormUrlEncoded
    @POST("softdelete")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    Call<Delete> softdelete(@Field("Authorization") String Authorization,
                            @Header("Authorization") String authorization,
                            @Field("table") String table,
                            @Field("id") String id
    );



    @FormUrlEncoded
    @POST("user/getuserProduct")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    Call<UserProductsApiModel> getUserProducts(@Field("Authorization") String Authorization,
                                               @Header("Authorization") String authorization,
                                               @Field("offset") String offset,
                                               @Field("pageLimit") String pageLimit,
                                               @Field("userid") String userid);



    @FormUrlEncoded
    @POST("user/removeDetailImages")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    Call<ImageDelete> deleteImage(@Field("Authorization") String Authorization,
                                  @Header("Authorization") String authorization,

                                  @Field("image_id") String imageid);




    @FormUrlEncoded
    @POST("addwishList/remove")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    Call<RemoveWishList> removeFav(@Field("Authorization") String Authorization,
                                   @Header("Authorization") String authorization,

                                   @Field("id") String id);
}
