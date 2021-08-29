package com.example.hotelakal.Repository;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    public static MyApi getClient() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://hotelapi-env.eba-pvxaa95s.us-east-1.elasticbeanstalk.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MyApi api = retrofit.create(MyApi.class);

        return api;
    }
}
