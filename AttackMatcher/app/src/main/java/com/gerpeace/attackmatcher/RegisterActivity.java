package com.gerpeace.attackmatcher;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText user_et,pass_et,con_pass_et,email_et,date_et;
    RadioGroup radioGroup;
    RadioButton male_rb,female_rb,other_rb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        BindView();
    }
    public void OnRegis(View view){
        //check text condition
        if (user_et.getText().toString().trim().length()<6){
            user_et.setError("require at least 6 characters");
        }else if (pass_et.getText().toString().trim().length()<6){
            pass_et.setError("require at least 6 characters");
        }else if (!(con_pass_et.getText().toString().equals(pass_et.getText().toString()))) {
            con_pass_et.setError("require same password above");
        }else if (email_et.getText().toString().trim().length()<10) {
            email_et.setError("require at least 10 characters");
        }else if (date_et.getText().toString().trim().length()<10) {
            date_et.setError("e.g. 1991-02-27");
        }else {
            String strUser = user_et.getText().toString();
            String strPass = pass_et.getText().toString();
            String strEmail = email_et.getText().toString();
            String strBirthDate = date_et.getText().toString();
            String strGender = "";
            if (male_rb.isChecked()){
                strGender = "male";
            }else if (female_rb.isChecked()){
                strGender = "female";
            }else if (other_rb.isChecked()){
                strGender = "other";
            }
            String type = "register";

            am_backoffice office = new am_backoffice(this);
            office.execute(type, strUser, strPass, strEmail, strGender, strBirthDate);
        }
    }
    public void BindView(){
        user_et = (EditText) findViewById(R.id.editUser);
        pass_et = (EditText) findViewById(R.id.editPass);
        con_pass_et = (EditText) findViewById(R.id.editConPass);
        email_et = (EditText) findViewById(R.id.editEmail);
        date_et = (EditText) findViewById(R.id.editDate);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        male_rb = (RadioButton) findViewById(R.id.radioMale);
        female_rb = (RadioButton) findViewById(R.id.radioFemale);
        other_rb = (RadioButton) findViewById(R.id.radioOther);
    }
}
