package com.example.hp1.webviewexample;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    private String url;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(getIntent().getData()!=null){
            url =getIntent().getData().toString();
        }
        else
            url="google.com";


        WebView webView = (WebView) findViewById(R.id.webview);
        webView.loadUrl("google.com");

        webView.setWebViewClient();
        {


        }
    }


}
