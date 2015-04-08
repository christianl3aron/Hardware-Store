package com.geekytheory.orderregister.libraries;

import java.util.ArrayList;

import com.example.android.navigationdrawerexample.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;

public class ExpandListAdapter extends BaseExpandableListAdapter {

	private Context context;
    private ArrayList<ExpandListGroup> groups;
    
    public ExpandListAdapter(Context context, ArrayList<ExpandListGroup> groups) {
        this.context = context;
        this.groups = groups;
    }
    
    public void addItem(ExpandListChild item, ExpandListGroup group) {
        if (!groups.contains(group)) {
            groups.add(group);
        }
        int index = groups.indexOf(group);
        ArrayList<ExpandListChild> ch = groups.get(index).getItems();
        ch.add(item);
        groups.get(index).setItems(ch);
    }

    public Object getChild(int groupPosition, int childPosition) {
        ArrayList<ExpandListChild> chList = groups.get(groupPosition).getItems();
        return chList.get(childPosition);
    }

    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }
    
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view,
            ViewGroup parent) {
        ExpandListChild child = (ExpandListChild) getChild(groupPosition, childPosition);
        if (view == null) {
            LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = infalInflater.inflate(R.layout.item_expandlist_child, null);
        }
        Button tv = (Button) view.findViewById(R.id.tvChild);
        tv.setText(child.getName().toString());
        tv.setTag(child.getTag());
        return view;
    }
    	    
    public int getChildrenCount(int groupPosition) {
        ArrayList<ExpandListChild> chList = groups.get(groupPosition).getItems();
        return chList.size();
    }
    
    public Object getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }
    
    public int getGroupCount() {
        return groups.size();
    }
    
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }
    
    public View getGroupView(int groupPosition, boolean isLastChild, View view,
            ViewGroup parent) {
        ExpandListGroup group = (ExpandListGroup) getGroup(groupPosition);
        if (view == null) {
            LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inf.inflate(R.layout.item_expandlist_group, null);
        }
        TextView tv = (TextView) view.findViewById(R.id.tvGroup);
        tv.setText(group.getName());
        return view;
    }
    
    public boolean hasStableIds() {
        return true;
    }
    
    public boolean isChildSelectable(int arg0, int arg1) {
        return true;
    }
}
