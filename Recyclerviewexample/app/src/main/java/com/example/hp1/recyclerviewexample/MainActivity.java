package com.example.hp1.recyclerviewexample;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Created by hp1 on 11/2/2017.
 */

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "TESTLOG";
    RecyclerView.Adapter adapter;
    ItemData dataLoader;
    RecyclerView rv;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        dataLoader = ItemData.getInstance();
//        dataArray = new ArrayList<>();
//        for (int i=0; i<10; i++)
//            dataArray.add(dataLoader.next());

        ListDataManager listDataManager = new ListDataManager(this);
//        listDataManager.deleteData();
        listDataManager.loadData();
        ArrayList<ItemData.Item> dataArray;
        dataArray = listDataManager.getAllRows();

        RecyclerView rv = (RecyclerView) findViewById(R.id.recycler);
        MyRecyclerAdapter adapter = new MyRecyclerAdapter(this, dataArray);
        rv.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager;
        layoutManager = new LinearLayoutManager(this);
//        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
//        layoutManager = new GridLayoutManager(this, 2);
        rv.setLayoutManager(layoutManager);
    }
}
