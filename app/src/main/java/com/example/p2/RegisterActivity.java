package com.example.p2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import javax.net.ssl.HttpsURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity{
    EditText title_et, content_et;
    Button reg_button;

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://192.249.18.169:80";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);

        title_et = findViewById(R.id.title_et);
        content_et = findViewById(R.id.content_et);
        reg_button = findViewById(R.id.reg_button);

        // 버튼 이벤트 추가
        reg_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 게시물 등록 함수
                handleEnrollDialog();
            }
        });
    }

    private void handleEnrollDialog(){
        HashMap<String, String> map = new HashMap<>();

        map.put("title", title_et.getText().toString());
        map.put("content", content_et.getText().toString());

        //----------------------------------------------------------------------------------
        Call<EnrollResult> call = retrofitInterface.executeEnroll(map);

        call.enqueue(new Callback<EnrollResult>() {
            @Override
            public void onResponse(Call<EnrollResult> call, Response<EnrollResult> response) {

                if (response.code() == 200) {

                    EnrollResult result = response.body();

                    AlertDialog.Builder builder1 = new AlertDialog.Builder(RegisterActivity.this);
                    builder1.setTitle(result.getTitle());
                    builder1.setMessage(result.getContent());
                    builder1.show();

                } else if (response.code() == 404) {
                    Toast.makeText(RegisterActivity.this, "Wrong Credentials",
                            Toast.LENGTH_LONG).show();
                }

            }
            @Override
            public void onFailure(Call<EnrollResult> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}
