package com.example.happyenglish;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewpager;

    FloatingActionButton btnMenu;
    DrawerLayout mDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        typcast_my_object();

        String mHeadings[]=getResources().getStringArray(R.array.headings);
        String mDescriptions[]=getResources().getStringArray(R.array.descriptions);;
        CustomSlider mCustomerslider = new CustomSlider(this, mHeadings, mDescriptions);

        mViewpager.setAdapter(mCustomerslider);

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mDrawer.isDrawerOpen(Gravity.LEFT)){
                    mDrawer.closeDrawer(Gravity.LEFT);
                }else{
                    mDrawer.openDrawer(Gravity.LEFT);
                }

            }
        });
    }

    private void typcast_my_object() {

        mViewpager=findViewById(R.id.viewPager);
        btnMenu=findViewById(R.id.btn_menu);
        mDrawer=findViewById(R.id.mDrawer);
    }
}