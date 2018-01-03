package com.example.hp1.json_to_list_example;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Sarmad on 15-Nov-17.
 */

public class MyListAdapter extends BaseAdapter {

    Context context;
    ArrayList<PersonData> data;
    int item_layout_Resource;

    public MyListAdapter(Context context, int item_layout_Resource,
                         ArrayList<PersonData> data){
        this.context = context;
        this.data = data;
        this.item_layout_Resource = item_layout_Resource;
    }

    @Override
    public int getCount() {  return data.size(); }

    @Override
    public Object getItem(int i) { return data.get(i); }

    @Override
    public long getItemId(int i) { return i; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        }

        PersonData p = data.get(position);

        TextView tv1 = (TextView) view.findViewById(R.id.text1);
        tv1.setText(p.getFullName());

        TextView tv2 = (TextView) view.findViewById(R.id.text2);
        tv2.setText(p.getUserName());

        ImageView iv = (ImageView) view.findViewById(R.id.imageView);
        iv.setImageBitmap(p.getImage());

        return view;
    }
}
