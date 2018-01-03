package com.example.hp1.list25102017;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

/**
 * Created by hp1 on 10/25/2017.
 */

public class MyAdapter extends ArrayAdapter{

    public MyAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View myView = convertView;
        if (myView==null)
        {
            myView = LayoutInflater.from(getContext()).inflate(R.layout.list_items,parent,false);
        }
        return myView;

    }
}
