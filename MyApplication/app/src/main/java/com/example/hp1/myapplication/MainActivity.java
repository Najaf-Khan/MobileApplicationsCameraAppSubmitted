package com.example.hp1.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private TextView number;
    private Button button1;
   private  int z;

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("num",z);
        editor.commit();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=(Button) findViewById(R.id.Plus);
        button1=(Button) findViewById(R.id.Minus);
        number=(TextView) findViewById(R.id.number);
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        z = sharedPref.getInt("num",0);
        number.setText(z + "");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(MainActivity.this,"I Love Android",Toast.LENGTH_LONG).show();
               z = Integer.parseInt(number.getText().toString());
                z++;
                number.setText(z + "");

            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toast.makeText(MainActivity.this,"I Love Android",Toast.LENGTH_LONG).show();
                z = Integer.parseInt(number.getText().toString());
                z--;
                number.setText(z + "");

            }
        });


    {

    }


    }
}
