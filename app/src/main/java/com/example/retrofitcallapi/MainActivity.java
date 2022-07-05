package com.example.retrofitcallapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.retrofitcallapi.adaper.UserAdapter;
import com.example.retrofitcallapi.api.ApiService;
import com.example.retrofitcallapi.api.ApiService2;
import com.example.retrofitcallapi.modle.MyResponse;
import com.example.retrofitcallapi.modle.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rcvUsers;
    private List<User> mListUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rcvUsers = findViewById(R.id.rcv_user);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvUsers.setLayoutManager(linearLayoutManager);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        rcvUsers.addItemDecoration(itemDecoration);

        mListUsers = new ArrayList<>();
        callApiGetUsers();
        callApi2();
        callApiPost3();
    }
    //link API bình thường
    private void callApiGetUsers(){
        ApiService.apiService.getListUsers(1).enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                mListUsers = response.body();
                UserAdapter userAdapter = new UserAdapter(mListUsers);
                rcvUsers.setAdapter(userAdapter);
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(MainActivity.this,"onFailure",Toast.LENGTH_SHORT).show();
            }
        });
    }
    //link API đặc biệt
    private void callApi2(){
        ApiService2.apiService.callApiExample().enqueue(new Callback<MyResponse>() {
            @Override
            public void onResponse(Call<MyResponse> call, Response<MyResponse> response) {
                Log.e("HuyTu","onSuccess");
                MyResponse myResponse = response.body();
                if(myResponse != null){
                    Log.e("HuyTu",myResponse.toString());
                }
            }

            @Override
            public void onFailure(Call<MyResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this,"onFailure",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void callApiPost3(){
        User user = new User(1,"Huy 1");
        ApiService2.apiService.senUsers(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User userResult = response.body();
                if(userResult != null){
                    Log.e("HuyTu",userResult.toString());
                }
                Toast.makeText(MainActivity.this,"onSuccess",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(MainActivity.this,"onFailure",Toast.LENGTH_SHORT).show();
            }
        });
    }
}