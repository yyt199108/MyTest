package com.test.mytest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {
//        public Street street;
//    public City city;
    @Inject
    public Province province;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        street = new Street();
//        city = new City(street);
//        province = new Province(city);
//        Log.d("hcy", "onCreate: " + province.showAddress());

//        DaggerMainActivityComponent.create().inject(this);
        DaggerMainActivityComponent.builder().bean2Module(new Bean2Module()).build().inject(this);

        Log.d("hcy", "onCreate: " + province.showAddress());

    }
}
