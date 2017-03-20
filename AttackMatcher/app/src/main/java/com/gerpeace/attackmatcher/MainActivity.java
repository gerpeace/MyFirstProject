package com.gerpeace.attackmatcher;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.annotation.BoolRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sp = null;
    SharedPreferences.Editor editor;
    TextView textView;
    Boolean firstRun = true;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp = getSharedPreferences("com.gerpeace", MODE_PRIVATE);
    }
    @Override
    protected void onResume() {
        super.onResume();

        if (sp.getBoolean("firstRun", true)) {
            //You can perform anything over here. This will call only first time
            handler.postDelayed(openMV,1000); // open MV
            editor = sp.edit();
            editor.putBoolean("firstRun", false);
            editor.commit();
        }else{
            handler.postDelayed(openStart,1000);
        }
    }
    public Runnable openMV = new Runnable() {
        @Override
        public void run() {
            startActivity(new Intent(MainActivity.this,MvAcitivity.class));
        }
    };
    public Runnable openStart = new Runnable() {
        @Override
        public void run() {
            startActivity(new Intent(MainActivity.this,StartAcitivity.class));
        }
    };

}
