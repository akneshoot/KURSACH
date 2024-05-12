package com.example.happyenglish;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewpager;

    Button btnListWord;
    Button btnStart;

    Button btnExit;

    FloatingActionButton btnMenu;
    DrawerLayout mDrawer;
    //
    ExpandableListView mExpandableListView;
    ExpandableListAdapter mExpandableListAdapter;

    List<String> list_group;
    HashMap<String, List<String>> list_children;
    //

    private ActionBarDrawerToggle mActionBarDrawerToggle;

    private int lastExpandedPosition=-1;

    //dotLayout
    private LinearLayout dotsLayout;
    private TextView dotsView [];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addButtonListener();
        
        typcast_my_object();

        String mHeadings[]=getResources().getStringArray(R.array.headings);
        String mDescriptions[]=getResources().getStringArray(R.array.descriptions);
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


        mExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long l) {
                if (groupPosition==10){
                    Intent intent = new Intent(MainActivity.this, GrammarTestActivity.class);
                    intent.putExtra("child_id",childPosition);
                    startActivity(intent);
                }else if(groupPosition!=10){
                    Intent intent = new Intent(MainActivity.this, PDFViewer.class);
                    intent.putExtra("child_id",childPosition);
                    intent.putExtra("group_id", groupPosition);
                    startActivity(intent);
                }


                return true;
            }
        });

        mExpandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {

            if (lastExpandedPosition!=-1 && groupPosition!= lastExpandedPosition){
                mExpandableListView.collapseGroup(lastExpandedPosition);
            }
            lastExpandedPosition=groupPosition;
            }
        });


        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawer,R.string.drawer_open,R.string.drawer_closer){



            @Override
            public void onDrawerOpened(View drawerView) {

                super.onDrawerOpened(drawerView);
                Toast.makeText(MainActivity.this, R.string.drawer_open,Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                Toast.makeText(MainActivity.this, R.string.drawer_closer,Toast.LENGTH_SHORT).show();
            }
        };
         mDrawer.setDrawerListener(mActionBarDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        sliderDotCreator(0);
        mViewpager.addOnPageChangeListener(onPageChangeListener);



    }

    @SuppressLint("WrongViewCast")
    private void addButtonListener() {
        btnListWord = (Button) findViewById (R.id.btnWord);
        btnListWord.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent e = new Intent(MainActivity.this, WORD.class);
                startActivity(e);


            }
        });
        btnStart = (Button) findViewById(R.id.Start);
        btnStart.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent e = new Intent(MainActivity.this, SPEECH.class);
                startActivity(e);

            }

        });
        btnExit = (Button) findViewById(R.id.Exit);
        btnExit.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "You have logged out of the profile.", Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, "Please log in to the app.", Toast.LENGTH_SHORT).show();
                Intent e = new Intent(MainActivity.this, Vhod.class);
                startActivity(e);

            }

        });
    }



    private void typcast_my_object() {

        mViewpager=findViewById(R.id.viewPager);
        btnMenu=findViewById(R.id.btn_menu);
        mDrawer=findViewById(R.id.mDrawer);

        mExpandableListView = findViewById(R.id.expandable_listview);
        list_children= ExpandableListData.getData();
        list_group = new ArrayList<String>(list_children.keySet());

        dotsLayout=findViewById(R.id.dotsLayout);

    }

    protected void onPostCreate(@Nullable Bundle saveInstanceState) {
        super.onPostCreate(saveInstanceState);
        mActionBarDrawerToggle.syncState();

    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (mActionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super .onOptionsItemSelected(item);
    }

    private void sliderDotCreator(int position){
        dotsView = new TextView[7];
        dotsLayout.removeAllViews();

        for(int i=0; i<dotsView.length; i++){
            dotsView[i]= new TextView(this);
            dotsView[i].setText(Html.fromHtml("&#8226;"));
            dotsView[i].setTextSize(35);
            dotsView[i].setTextColor(getResources().getColor(R.color.teal_700));
            dotsLayout.addView(dotsView[i]);
        }
        if(dotsView.length>0){
            dotsView[position].setTextColor(Color.LTGRAY);
            dotsView[position].setTextSize(40);
        }

    }


    ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


        }

        @Override
        public void onPageSelected(int position) {

            sliderDotCreator(position);

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }

    };

}