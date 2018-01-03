package com.example.hp1.json_to_list_example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.ListView;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create a progress bar to display while the list loads
        progressBar = new ProgressBar(this);
        LayoutParams params = new LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        progressBar.setLayoutParams(params);
        progressBar.setIndeterminate(true);
        ViewGroup root = (ViewGroup) findViewById(R.id.root);
        root.addView(progressBar);

        listView = (ListView) findViewById(R.id.list);
        String url = "https://randomuser.me/api/?results=15";
        new DataDownloader(this, listView, progressBar).execute(url);
    }
}