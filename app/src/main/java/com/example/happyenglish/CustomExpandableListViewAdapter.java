package com.example.happyenglish;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class CustomExpandableListViewAdapter extends BaseExpandableListAdapter {
    Context context;
    List<String> list_group;
    HashMap<String, List<String>> list_child;

    CustomExpandableListViewAdapter(Context context, List<String> list_group, HashMap<String, List<String>> list_child) {
        this.context = context;
        this.list_group = list_group;
        this.list_child = list_child;

    }

    @Override
    public int getGroupCount() {
        return this.list_group.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.list_child.get(this.list_group.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.list_group.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.list_child.get(this.list_group.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String group_title = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_group_layout, null);

        }

        if(groupPosition==10){
            ProgressBar test_progressbar = convertView.findViewById(R.id.test_progressbar);
            TextView progressbar_text = convertView.findViewById(R.id.progressbar_text);
            progressbar_text.setVisibility(View.VISIBLE);
            test_progressbar.setVisibility(View.VISIBLE);

            progressbar_text.setText(Float.toString(getDataFromSharedPreferences())+ "%");
            test_progressbar.setProgress((int) getDataFromSharedPreferences());
        }


        TextView group_textview = convertView.findViewById(R.id.group_textview);
        group_textview.setText(group_title);

        return convertView;

    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String child_titles = (String) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate((R.layout.listview_child_layout), null);

        }
        TextView child_textview = convertView.findViewById((R.id.child_textview));
        child_textview.setText(child_titles);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public float getDataFromSharedPreferences(){

         float a1_average;
         float a2_average;
         float b1_average;
         float b2_average;
         float c1_average;
         float c2_average;
         float test_overall_average;

        SharedPreferences setting_a1 = context.getSharedPreferences("a1_average", context.MODE_PRIVATE);
        a1_average = setting_a1.getFloat("a1_average", 0);

        SharedPreferences setting_a2 = context.getSharedPreferences("a2_average", context.MODE_PRIVATE);
        a2_average = setting_a2.getFloat("a2_average", 0);

        SharedPreferences setting_b1 = context.getSharedPreferences("b1_average", context.MODE_PRIVATE);
        b1_average = setting_b1.getFloat("b1_average", 0);

        SharedPreferences setting_b2 = context.getSharedPreferences("b2_average", context.MODE_PRIVATE);
        b2_average = setting_b2.getFloat("b2_average", 0);

        SharedPreferences setting_c1 = context.getSharedPreferences("c1_average", context.MODE_PRIVATE);
        c1_average = setting_c1.getFloat("c1_average", 0);

        SharedPreferences setting_c2 = context.getSharedPreferences("c2_average", context.MODE_PRIVATE);
        c2_average = setting_c2.getFloat("c2_average", 0);


        test_overall_average = (a1_average + a2_average + b1_average + b2_average + c1_average + c2_average)/600*100;


        return test_overall_average;
    }
}
