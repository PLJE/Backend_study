package com.example.p2;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitInterface {

    @POST("/enroll")
    Call<EnrollResult> executeEnroll(@Body HashMap<String, String> map);


    @POST("/login")
    Call <Void> executeLogin(@Body HashMap<String,String> map);
}
