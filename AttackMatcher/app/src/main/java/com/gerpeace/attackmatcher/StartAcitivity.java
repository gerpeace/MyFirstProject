package com.gerpeace.attackmatcher;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class StartAcitivity extends AppCompatActivity {

    SharedPreferences sp = null;
    SharedPreferences.Editor editor;
    boolean isLogin = false;
    Intent intent;
    TextView txtHi,txtLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_acitivity);

        sp = getSharedPreferences("com.gerpeace", MODE_PRIVATE);
        editor = sp.edit();

        final PlayGifView pGif = (PlayGifView) findViewById(R.id.startGif);
        pGif.setImageResource(R.mipmap.start);

        txtHi = (TextView) findViewById(R.id.say_hi);
        txtHi.setText("Hi, "+sp.getString("username","Everybody"));

        if (sp.getString("username","")=="" || sp.getString("password","")==""){
            txtLogout = (TextView) findViewById(R.id.txt_logout);
            txtLogout.setText("[ log in ]");
        }
    }
    public void goMV(View view){
        startActivity(new Intent(StartAcitivity.this,MvAcitivity.class));
    }
    public void goLogin(View view){
        if (sp.getString("username","")=="" || sp.getString("password","")==""){
            isLogin = false;
        }else {
            isLogin = true;
        }
        if (isLogin==false) {
            intent = new Intent(StartAcitivity.this,LoginActivity.class);
            startActivity(intent);
        }else{
            String type = "login";
            String strUser = sp.getString("username","");
            String strPass = sp.getString("password","");

            am_backoffice office = new am_backoffice(this);
            office.execute(type,strUser,strPass);
        }
    }
    public void goRegisPage(View view){
        startActivity(new Intent(StartAcitivity.this,RegisterActivity.class));
    }
    public void goLogout(View view){
        if (sp.getString("username","")=="" || sp.getString("password","")==""){
            startActivity(new Intent(StartAcitivity.this,LoginActivity.class));
        }else {
            editor.putString("username", "");
            editor.putString("password", "");
            editor.commit();
            recreate();
        }
    }
}
