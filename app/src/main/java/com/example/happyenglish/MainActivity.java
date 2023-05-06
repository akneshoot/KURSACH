package com.example.happyenglish;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        typcast_my_object();
        CustomSlider mCustomerslider = new CustomSlider(this);

        mViewpager.setAdapter(mCustomerslider);
    }

    private void typcast_my_object() {

        mViewpager=findViewById(R.id.viewPager);

    }
}