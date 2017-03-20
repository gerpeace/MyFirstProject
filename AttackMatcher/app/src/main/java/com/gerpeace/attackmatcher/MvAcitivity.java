package com.gerpeace.attackmatcher;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MvAcitivity extends AppCompatActivity {

    Handler handler = new Handler();
    TextView skip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mv_acitivity);

        final PlayGifView pGif = (PlayGifView) findViewById(R.id.mvGif);
        pGif.setImageResource(R.mipmap.mv);

        handler.postDelayed(openStart,5000);
    }
    public Runnable openStart = new Runnable() {
        @Override
        public void run() {
            startActivity(new Intent(MvAcitivity.this,StartAcitivity.class));
        }
    };
    public void goStart(View view){
        handler.postDelayed(openStart,0);
    }
}
