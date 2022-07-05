package com.example.retrofitcallapi.api;

import com.example.retrofitcallapi.modle.MyResponse;
import com.example.retrofitcallapi.modle.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService2 {
    Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyy").create();

    ApiService2 apiService = new Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService2.class);

    @GET("/")
    Call<MyResponse> callApiExample();

    //CallApiPost3
    @POST("/posts")
    Call<User> senUsers(@Body User user);


    //link API : http://apilayer.net/api/user/list
    @GET("api/user/list")
    Call<User> getListUser();

    //đường link API có tham số động
    //link API : http://apilayer.net/api/group/1/user/list
    @GET("api/group/{id}/user/list")
    Call<User> getListUserFromGroup(@Path("id") int groupId);

    //link API : http://apilayer.net/api/group/1/user/list?sort=desc
    @GET("api/group/{id}/user/list")
    Call<User> getListUserFromGroup2(@Path("id") int groupId,
                                     @Query("sort") String sort);
}
