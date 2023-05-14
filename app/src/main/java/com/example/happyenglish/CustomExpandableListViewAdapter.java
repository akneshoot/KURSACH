package com.example.happyenglish;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
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
}
