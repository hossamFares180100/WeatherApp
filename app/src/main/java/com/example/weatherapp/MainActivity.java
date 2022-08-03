package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weatherapp.retrofit.ApiClient;
import com.example.weatherapp.retrofit.ApiInterface;
import com.example.weatherapp.retrofit.Example;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText city;
    TextView temp,desc,hum;
    ImageView search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        city=findViewById(R.id.et_city);
        temp=findViewById(R.id.tv_temp);
        desc=findViewById(R.id.tv_desc);
        hum=findViewById(R.id.tv_hum);
        search=findViewById(R.id.iv_search);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getWeatherData(city.getText().toString());
            }
        });

    }


    private void getWeatherData(String city){
        ApiInterface apiInterface= ApiClient.getClient().create(ApiInterface.class);
        Call<Example>call=apiInterface.getWeatherData(city);
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {

                temp.setText("Temp"+"  "+response.body().getMain().getTemp()+" C");
                desc.setText("Feels Like"+"  "+response.body().getMain().getFeels_like());
                hum.setText("Humidity"+"  "+response.body().getMain().getHumidity());
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Toast.makeText(MainActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

    }
}