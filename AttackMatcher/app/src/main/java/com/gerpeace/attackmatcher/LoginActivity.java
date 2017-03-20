package com.gerpeace.attackmatcher;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    EditText etUser,etPass;
    String username,password;
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        BindView();
    }
    public void BindView(){
        etUser = (EditText) findViewById(R.id.editUser);
        etPass = (EditText) findViewById(R.id.editPass);
        sp = getSharedPreferences("com.gerpeace", MODE_PRIVATE);
        editor = sp.edit();
    }
    public void goRegisPage(View view){
        startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
    }
    public void UserLogin(View view){
        String type = "login";
        String strUser = etUser.getText().toString();
        String strPass = etPass.getText().toString();

        editor.putString("username",etUser.getText().toString());
        editor.putString("password",etPass.getText().toString());
        editor.commit();

        am_backoffice office = new am_backoffice(this);
        office.execute(type,strUser,strPass);
    }
}
