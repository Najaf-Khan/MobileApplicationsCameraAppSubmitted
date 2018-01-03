package com.example.hp1.recyclerviewexample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by hp1 on 11/2/2017.
 */

public class MyRecyclerAdapter extends
        RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> {

    private String TAG = "TESTLOG";
    private Context context;
    private ArrayList<ItemData.Item> data;
    private LayoutInflater inflater;

    public MyRecyclerAdapter(Context context, ArrayList<ItemData.Item> data){
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        Log.i(TAG, "Create");
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Log.i(TAG, "Bind "+position);
        holder.itemText.setText(data.get(position).getItemText());
        holder.itemImage.setImageResource(data.get(position).getImageId());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView itemText;
        public ImageView itemImage;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemText = (TextView) itemView.findViewById(R.id.textView);
            itemImage = (ImageView) itemView.findViewById(R.id.icon);
        }
    }
}

