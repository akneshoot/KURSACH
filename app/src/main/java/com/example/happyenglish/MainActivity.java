package com.example.happyenglish;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewpager;

    FloatingActionButton btnMenu;
    DrawerLayout mDrawer;
    //
    ExpandableListView mExpandableListView;
    ExpandableListAdapter mExpandableListAdapter;

    List<String> list_group;
    HashMap<String, List<String>> list_children;
    //
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

        mExpandableListAdapter = new CustomExpandableListViewAdapter(this, list_group,list_children);
        mExpandableListView.setAdapter(mExpandableListAdapter);
    }

    private void typcast_my_object() {

        mViewpager=findViewById(R.id.viewPager);
        btnMenu=findViewById(R.id.btn_menu);
        mDrawer=findViewById(R.id.mDrawer);

        mExpandableListView = findViewById(R.id.expandable_listview);
        list_children= ExpandableListData.getData();
        list_group = new ArrayList<String>(list_children.keySet());
    }
}