package com.gerpeace.attackmatcher;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;

public class am_backoffice extends AsyncTask<String,Void,String> {

    String data;
    ProgressDialog progressDialog;

    Context context;
    AlertDialog alertDialog;

    am_backoffice(Context ctx) {
        context = ctx;
    }

    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        getType.mMyAppsBundle.putString(new getType().data,type);
        String regis_url = "http://gerpeace.96.lt/am_register.php";
        String login_url = "http://gerpeace.96.lt/am_login.php";
        String win_url = "http://gerpeace.96.lt/am_win.php";
        String upgrade_url = "http://gerpeace.96.lt/am_upgrade.php";

        if (type.equals("register")) {
            try {
                String username = params[1];
                String password = params[2];
                String email = params[3];
                String gender = params[4];
                String birth_date = params[5];
                URL url = new URL(regis_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&"
                        + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8") + "&"
                        + URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8") + "&"
                        + URLEncoder.encode("gender", "UTF-8") + "=" + URLEncoder.encode(gender, "UTF-8") + "&"
                        + URLEncoder.encode("birth_date", "UTF-8") + "=" + URLEncoder.encode(birth_date, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if (type.equals("login")){
            try {
                String username = params[1];
                String password = params[2];
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&"
                        + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if (type.equals("win")){
            try {
                String user_id = params[1];
                String mis1=params[2],mis2=params[3],mis3=params[4],mis4=params[5],mis5=params[6],mis6=params[7],mis7=params[8],
                        mis8=params[9],mis9=params[10],mis10=params[11],mis11=params[12],mis12=params[13],mis13=params[14],mis14=params[15],
                        mis15=params[16],mis16=params[17],mis17=params[18],mis18=params[19],mis19=params[20],mis20=params[21],mis21=params[22],
                        mis22=params[23],mis23=params[24],mis24=params[25],mis25=params[26],mis26=params[27],mis27=params[28],mis28=params[29],
                        mis29=params[30],mis30=params[31];
                String money = params[32];
                URL url = new URL(win_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("user_id", "UTF-8") + "=" + URLEncoder.encode(user_id, "UTF-8") + "&"
                        + URLEncoder.encode("mis1", "UTF-8") + "=" + URLEncoder.encode(mis1, "UTF-8") + "&"
                        + URLEncoder.encode("mis2", "UTF-8") + "=" + URLEncoder.encode(mis2, "UTF-8") + "&"
                        + URLEncoder.encode("mis3", "UTF-8") + "=" + URLEncoder.encode(mis3, "UTF-8") + "&"
                        + URLEncoder.encode("mis4", "UTF-8") + "=" + URLEncoder.encode(mis4, "UTF-8") + "&"
                        + URLEncoder.encode("mis5", "UTF-8") + "=" + URLEncoder.encode(mis5, "UTF-8") + "&"
                        + URLEncoder.encode("mis6", "UTF-8") + "=" + URLEncoder.encode(mis6, "UTF-8") + "&"
                        + URLEncoder.encode("mis7", "UTF-8") + "=" + URLEncoder.encode(mis7, "UTF-8") + "&"
                        + URLEncoder.encode("mis8", "UTF-8") + "=" + URLEncoder.encode(mis8, "UTF-8") + "&"
                        + URLEncoder.encode("mis9", "UTF-8") + "=" + URLEncoder.encode(mis9, "UTF-8") + "&"
                        + URLEncoder.encode("mis10", "UTF-8") + "=" + URLEncoder.encode(mis10, "UTF-8") + "&"
                        + URLEncoder.encode("mis11", "UTF-8") + "=" + URLEncoder.encode(mis11, "UTF-8") + "&"
                        + URLEncoder.encode("mis12", "UTF-8") + "=" + URLEncoder.encode(mis12, "UTF-8") + "&"
                        + URLEncoder.encode("mis13", "UTF-8") + "=" + URLEncoder.encode(mis13, "UTF-8") + "&"
                        + URLEncoder.encode("mis14", "UTF-8") + "=" + URLEncoder.encode(mis14, "UTF-8") + "&"
                        + URLEncoder.encode("mis15", "UTF-8") + "=" + URLEncoder.encode(mis15, "UTF-8") + "&"
                        + URLEncoder.encode("mis16", "UTF-8") + "=" + URLEncoder.encode(mis16, "UTF-8") + "&"
                        + URLEncoder.encode("mis17", "UTF-8") + "=" + URLEncoder.encode(mis17, "UTF-8") + "&"
                        + URLEncoder.encode("mis18", "UTF-8") + "=" + URLEncoder.encode(mis18, "UTF-8") + "&"
                        + URLEncoder.encode("mis19", "UTF-8") + "=" + URLEncoder.encode(mis19, "UTF-8") + "&"
                        + URLEncoder.encode("mis20", "UTF-8") + "=" + URLEncoder.encode(mis20, "UTF-8") + "&"
                        + URLEncoder.encode("mis21", "UTF-8") + "=" + URLEncoder.encode(mis21, "UTF-8") + "&"
                        + URLEncoder.encode("mis22", "UTF-8") + "=" + URLEncoder.encode(mis22, "UTF-8") + "&"
                        + URLEncoder.encode("mis23", "UTF-8") + "=" + URLEncoder.encode(mis23, "UTF-8") + "&"
                        + URLEncoder.encode("mis24", "UTF-8") + "=" + URLEncoder.encode(mis24, "UTF-8") + "&"
                        + URLEncoder.encode("mis25", "UTF-8") + "=" + URLEncoder.encode(mis25, "UTF-8") + "&"
                        + URLEncoder.encode("mis26", "UTF-8") + "=" + URLEncoder.encode(mis26, "UTF-8") + "&"
                        + URLEncoder.encode("mis27", "UTF-8") + "=" + URLEncoder.encode(mis27, "UTF-8") + "&"
                        + URLEncoder.encode("mis28", "UTF-8") + "=" + URLEncoder.encode(mis28, "UTF-8") + "&"
                        + URLEncoder.encode("mis29", "UTF-8") + "=" + URLEncoder.encode(mis29, "UTF-8") + "&"
                        + URLEncoder.encode("mis30", "UTF-8") + "=" + URLEncoder.encode(mis30, "UTF-8") + "&"
                        + URLEncoder.encode("money", "UTF-8") + "=" + URLEncoder.encode(money, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if (type.equals("upgrade")){
            try {
                String user_id = params[1];
                String money = params[2];
                String   lv1_1=params[3]    ,lv2_1=params[18]   ,lv3_1=params[33]
                        ,lv1_2=params[4]    ,lv2_2=params[19]   ,lv3_2=params[34]
                        ,lv1_3=params[5]    ,lv2_3=params[20]   ,lv3_3=params[35]
                        ,lv1_4=params[6]    ,lv2_4=params[21]   ,lv3_4=params[36]
                        ,lv1_5=params[7]    ,lv2_5=params[22]   ,lv3_5=params[37]
                        ,lv1_6=params[8]    ,lv2_6=params[23]   ,lv3_6=params[38]
                        ,lv1_7=params[9]    ,lv2_7=params[24]   ,lv3_7=params[39]
                        ,lv1_8=params[10]   ,lv2_8=params[25]   ,lv3_8=params[40]
                        ,lv1_9=params[11]   ,lv2_9=params[26]   ,lv3_9=params[41]
                        ,lv1_10=params[12]  ,lv2_10=params[27]  ,lv3_10=params[42]
                        ,lv1_11=params[13]  ,lv2_11=params[28]  ,lv3_11=params[43]
                        ,lv1_12=params[14]  ,lv2_12=params[29]  ,lv3_12=params[44]
                        ,lv1_13=params[15]  ,lv2_13=params[30]  ,lv3_13=params[45]
                        ,lv1_14=params[16]  ,lv2_14=params[31]  ,lv3_14=params[46]
                        ,lv1_15=params[17]  ,lv2_15=params[32]  ,lv3_15=params[47];
                String   skill1_1=params[48]    ,skill2_1=params[63]   ,skill3_1=params[78]
                        ,skill1_2=params[49]    ,skill2_2=params[64]   ,skill3_2=params[79]
                        ,skill1_3=params[50]    ,skill2_3=params[65]   ,skill3_3=params[80]
                        ,skill1_4=params[51]    ,skill2_4=params[66]   ,skill3_4=params[81]
                        ,skill1_5=params[52]    ,skill2_5=params[67]   ,skill3_5=params[82]
                        ,skill1_6=params[53]    ,skill2_6=params[68]   ,skill3_6=params[83]
                        ,skill1_7=params[54]    ,skill2_7=params[69]   ,skill3_7=params[84]
                        ,skill1_8=params[55]   ,skill2_8=params[70]   ,skill3_8=params[85]
                        ,skill1_9=params[56]   ,skill2_9=params[71]   ,skill3_9=params[86]
                        ,skill1_10=params[57]  ,skill2_10=params[72]  ,skill3_10=params[87]
                        ,skill1_11=params[58]  ,skill2_11=params[73]  ,skill3_11=params[88]
                        ,skill1_12=params[59]  ,skill2_12=params[74]  ,skill3_12=params[89]
                        ,skill1_13=params[60]  ,skill2_13=params[75]  ,skill3_13=params[90]
                        ,skill1_14=params[61]  ,skill2_14=params[76]  ,skill3_14=params[91]
                        ,skill1_15=params[62]  ,skill2_15=params[77]  ,skill3_15=params[92];
                URL url = new URL(upgrade_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data =
                          URLEncoder.encode("user_id", "UTF-8") + "=" + URLEncoder.encode(user_id, "UTF-8") + "&"
                        + URLEncoder.encode("money", "UTF-8") + "=" + URLEncoder.encode(money, "UTF-8") + "&"

                        + URLEncoder.encode("lv1_1", "UTF-8") + "=" + URLEncoder.encode(lv1_1, "UTF-8") + "&"
                        + URLEncoder.encode("lv1_2", "UTF-8") + "=" + URLEncoder.encode(lv1_2, "UTF-8") + "&"
                        + URLEncoder.encode("lv1_3", "UTF-8") + "=" + URLEncoder.encode(lv1_3, "UTF-8") + "&"
                        + URLEncoder.encode("lv1_4", "UTF-8") + "=" + URLEncoder.encode(lv1_4, "UTF-8") + "&"
                        + URLEncoder.encode("lv1_5", "UTF-8") + "=" + URLEncoder.encode(lv1_5, "UTF-8") + "&"
                        + URLEncoder.encode("lv1_6", "UTF-8") + "=" + URLEncoder.encode(lv1_6, "UTF-8") + "&"
                        + URLEncoder.encode("lv1_7", "UTF-8") + "=" + URLEncoder.encode(lv1_7, "UTF-8") + "&"
                        + URLEncoder.encode("lv1_8", "UTF-8") + "=" + URLEncoder.encode(lv1_8, "UTF-8") + "&"
                        + URLEncoder.encode("lv1_9", "UTF-8") + "=" + URLEncoder.encode(lv1_9, "UTF-8") + "&"
                        + URLEncoder.encode("lv1_10", "UTF-8") + "=" + URLEncoder.encode(lv1_10, "UTF-8") + "&"
                        + URLEncoder.encode("lv1_11", "UTF-8") + "=" + URLEncoder.encode(lv1_11, "UTF-8") + "&"
                        + URLEncoder.encode("lv1_12", "UTF-8") + "=" + URLEncoder.encode(lv1_12, "UTF-8") + "&"
                        + URLEncoder.encode("lv1_13", "UTF-8") + "=" + URLEncoder.encode(lv1_13, "UTF-8") + "&"
                        + URLEncoder.encode("lv1_14", "UTF-8") + "=" + URLEncoder.encode(lv1_14, "UTF-8") + "&"
                        + URLEncoder.encode("lv1_15", "UTF-8") + "=" + URLEncoder.encode(lv1_15, "UTF-8") + "&"

                        + URLEncoder.encode("lv2_1", "UTF-8") + "=" + URLEncoder.encode(lv2_1, "UTF-8") + "&"
                        + URLEncoder.encode("lv2_2", "UTF-8") + "=" + URLEncoder.encode(lv2_2, "UTF-8") + "&"
                        + URLEncoder.encode("lv2_3", "UTF-8") + "=" + URLEncoder.encode(lv2_3, "UTF-8") + "&"
                        + URLEncoder.encode("lv2_4", "UTF-8") + "=" + URLEncoder.encode(lv2_4, "UTF-8") + "&"
                        + URLEncoder.encode("lv2_5", "UTF-8") + "=" + URLEncoder.encode(lv2_5, "UTF-8") + "&"
                        + URLEncoder.encode("lv2_6", "UTF-8") + "=" + URLEncoder.encode(lv2_6, "UTF-8") + "&"
                        + URLEncoder.encode("lv2_7", "UTF-8") + "=" + URLEncoder.encode(lv2_7, "UTF-8") + "&"
                        + URLEncoder.encode("lv2_8", "UTF-8") + "=" + URLEncoder.encode(lv2_8, "UTF-8") + "&"
                        + URLEncoder.encode("lv2_9", "UTF-8") + "=" + URLEncoder.encode(lv2_9, "UTF-8") + "&"
                        + URLEncoder.encode("lv2_10", "UTF-8") + "=" + URLEncoder.encode(lv2_10, "UTF-8") + "&"
                        + URLEncoder.encode("lv2_11", "UTF-8") + "=" + URLEncoder.encode(lv2_11, "UTF-8") + "&"
                        + URLEncoder.encode("lv2_12", "UTF-8") + "=" + URLEncoder.encode(lv2_12, "UTF-8") + "&"
                        + URLEncoder.encode("lv2_13", "UTF-8") + "=" + URLEncoder.encode(lv2_13, "UTF-8") + "&"
                        + URLEncoder.encode("lv2_14", "UTF-8") + "=" + URLEncoder.encode(lv2_14, "UTF-8") + "&"
                        + URLEncoder.encode("lv2_15", "UTF-8") + "=" + URLEncoder.encode(lv2_15, "UTF-8") + "&"

                        + URLEncoder.encode("lv3_1", "UTF-8") + "=" + URLEncoder.encode(lv3_1, "UTF-8") + "&"
                        + URLEncoder.encode("lv3_2", "UTF-8") + "=" + URLEncoder.encode(lv3_2, "UTF-8") + "&"
                        + URLEncoder.encode("lv3_3", "UTF-8") + "=" + URLEncoder.encode(lv3_3, "UTF-8") + "&"
                        + URLEncoder.encode("lv3_4", "UTF-8") + "=" + URLEncoder.encode(lv3_4, "UTF-8") + "&"
                        + URLEncoder.encode("lv3_5", "UTF-8") + "=" + URLEncoder.encode(lv3_5, "UTF-8") + "&"
                        + URLEncoder.encode("lv3_6", "UTF-8") + "=" + URLEncoder.encode(lv3_6, "UTF-8") + "&"
                        + URLEncoder.encode("lv3_7", "UTF-8") + "=" + URLEncoder.encode(lv3_7, "UTF-8") + "&"
                        + URLEncoder.encode("lv3_8", "UTF-8") + "=" + URLEncoder.encode(lv3_8, "UTF-8") + "&"
                        + URLEncoder.encode("lv3_9", "UTF-8") + "=" + URLEncoder.encode(lv3_9, "UTF-8") + "&"
                        + URLEncoder.encode("lv3_10", "UTF-8") + "=" + URLEncoder.encode(lv3_10, "UTF-8") + "&"
                        + URLEncoder.encode("lv3_11", "UTF-8") + "=" + URLEncoder.encode(lv3_11, "UTF-8") + "&"
                        + URLEncoder.encode("lv3_12", "UTF-8") + "=" + URLEncoder.encode(lv3_12, "UTF-8") + "&"
                        + URLEncoder.encode("lv3_13", "UTF-8") + "=" + URLEncoder.encode(lv3_13, "UTF-8") + "&"
                        + URLEncoder.encode("lv3_14", "UTF-8") + "=" + URLEncoder.encode(lv3_14, "UTF-8") + "&"
                        + URLEncoder.encode("lv3_15", "UTF-8") + "=" + URLEncoder.encode(lv3_15, "UTF-8") + "&"

                        + URLEncoder.encode("skill1_1", "UTF-8") + "=" + URLEncoder.encode(skill1_1, "UTF-8") + "&"
                        + URLEncoder.encode("skill1_2", "UTF-8") + "=" + URLEncoder.encode(skill1_2, "UTF-8") + "&"
                        + URLEncoder.encode("skill1_3", "UTF-8") + "=" + URLEncoder.encode(skill1_3, "UTF-8") + "&"
                        + URLEncoder.encode("skill1_4", "UTF-8") + "=" + URLEncoder.encode(skill1_4, "UTF-8") + "&"
                        + URLEncoder.encode("skill1_5", "UTF-8") + "=" + URLEncoder.encode(skill1_5, "UTF-8") + "&"
                        + URLEncoder.encode("skill1_6", "UTF-8") + "=" + URLEncoder.encode(skill1_6, "UTF-8") + "&"
                        + URLEncoder.encode("skill1_7", "UTF-8") + "=" + URLEncoder.encode(skill1_7, "UTF-8") + "&"
                        + URLEncoder.encode("skill1_8", "UTF-8") + "=" + URLEncoder.encode(skill1_8, "UTF-8") + "&"
                        + URLEncoder.encode("skill1_9", "UTF-8") + "=" + URLEncoder.encode(skill1_9, "UTF-8") + "&"
                        + URLEncoder.encode("skill1_10", "UTF-8") + "=" + URLEncoder.encode(skill1_10, "UTF-8") + "&"
                        + URLEncoder.encode("skill1_11", "UTF-8") + "=" + URLEncoder.encode(skill1_11, "UTF-8") + "&"
                        + URLEncoder.encode("skill1_12", "UTF-8") + "=" + URLEncoder.encode(skill1_12, "UTF-8") + "&"
                        + URLEncoder.encode("skill1_13", "UTF-8") + "=" + URLEncoder.encode(skill1_13, "UTF-8") + "&"
                        + URLEncoder.encode("skill1_14", "UTF-8") + "=" + URLEncoder.encode(skill1_14, "UTF-8") + "&"
                        + URLEncoder.encode("skill1_15", "UTF-8") + "=" + URLEncoder.encode(skill1_15, "UTF-8") + "&"

                        + URLEncoder.encode("skill2_1", "UTF-8") + "=" + URLEncoder.encode(skill2_1, "UTF-8") + "&"
                        + URLEncoder.encode("skill2_2", "UTF-8") + "=" + URLEncoder.encode(skill2_2, "UTF-8") + "&"
                        + URLEncoder.encode("skill2_3", "UTF-8") + "=" + URLEncoder.encode(skill2_3, "UTF-8") + "&"
                        + URLEncoder.encode("skill2_4", "UTF-8") + "=" + URLEncoder.encode(skill2_4, "UTF-8") + "&"
                        + URLEncoder.encode("skill2_5", "UTF-8") + "=" + URLEncoder.encode(skill2_5, "UTF-8") + "&"
                        + URLEncoder.encode("skill2_6", "UTF-8") + "=" + URLEncoder.encode(skill2_6, "UTF-8") + "&"
                        + URLEncoder.encode("skill2_7", "UTF-8") + "=" + URLEncoder.encode(skill2_7, "UTF-8") + "&"
                        + URLEncoder.encode("skill2_8", "UTF-8") + "=" + URLEncoder.encode(skill2_8, "UTF-8") + "&"
                        + URLEncoder.encode("skill2_9", "UTF-8") + "=" + URLEncoder.encode(skill2_9, "UTF-8") + "&"
                        + URLEncoder.encode("skill2_10", "UTF-8") + "=" + URLEncoder.encode(skill2_10, "UTF-8") + "&"
                        + URLEncoder.encode("skill2_11", "UTF-8") + "=" + URLEncoder.encode(skill2_11, "UTF-8") + "&"
                        + URLEncoder.encode("skill2_12", "UTF-8") + "=" + URLEncoder.encode(skill2_12, "UTF-8") + "&"
                        + URLEncoder.encode("skill2_13", "UTF-8") + "=" + URLEncoder.encode(skill2_13, "UTF-8") + "&"
                        + URLEncoder.encode("skill2_14", "UTF-8") + "=" + URLEncoder.encode(skill2_14, "UTF-8") + "&"
                        + URLEncoder.encode("skill2_15", "UTF-8") + "=" + URLEncoder.encode(skill2_15, "UTF-8") + "&"

                        + URLEncoder.encode("skill3_1", "UTF-8") + "=" + URLEncoder.encode(skill3_1, "UTF-8") + "&"
                        + URLEncoder.encode("skill3_2", "UTF-8") + "=" + URLEncoder.encode(skill3_2, "UTF-8") + "&"
                        + URLEncoder.encode("skill3_3", "UTF-8") + "=" + URLEncoder.encode(skill3_3, "UTF-8") + "&"
                        + URLEncoder.encode("skill3_4", "UTF-8") + "=" + URLEncoder.encode(skill3_4, "UTF-8") + "&"
                        + URLEncoder.encode("skill3_5", "UTF-8") + "=" + URLEncoder.encode(skill3_5, "UTF-8") + "&"
                        + URLEncoder.encode("skill3_6", "UTF-8") + "=" + URLEncoder.encode(skill3_6, "UTF-8") + "&"
                        + URLEncoder.encode("skill3_7", "UTF-8") + "=" + URLEncoder.encode(skill3_7, "UTF-8") + "&"
                        + URLEncoder.encode("skill3_8", "UTF-8") + "=" + URLEncoder.encode(skill3_8, "UTF-8") + "&"
                        + URLEncoder.encode("skill3_9", "UTF-8") + "=" + URLEncoder.encode(skill3_9, "UTF-8") + "&"
                        + URLEncoder.encode("skill3_10", "UTF-8") + "=" + URLEncoder.encode(skill3_10, "UTF-8") + "&"
                        + URLEncoder.encode("skill3_11", "UTF-8") + "=" + URLEncoder.encode(skill3_11, "UTF-8") + "&"
                        + URLEncoder.encode("skill3_12", "UTF-8") + "=" + URLEncoder.encode(skill3_12, "UTF-8") + "&"
                        + URLEncoder.encode("skill3_13", "UTF-8") + "=" + URLEncoder.encode(skill3_13, "UTF-8") + "&"
                        + URLEncoder.encode("skill3_14", "UTF-8") + "=" + URLEncoder.encode(skill3_14, "UTF-8") + "&"
                        + URLEncoder.encode("skill3_15", "UTF-8") + "=" + URLEncoder.encode(skill3_15, "UTF-8");

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Downloading . . .");
        progressDialog.show();
    }

    @Override
    protected void onPostExecute(final String result) {
        progressDialog.dismiss();
        String type = getType.mMyAppsBundle.getString(new getType().data);
        if (type.equals("login")) {
            if (!result.equals("login_error")) {
                context.startActivity(new Intent(context, MenuActivity.class).putExtra("result",result));
            }else {
                Toast.makeText(context,"username or password are not correct", Toast.LENGTH_SHORT).show();
            }
        }else if (type.equals("register")){
            if (result.equals("repeat_error")){
                Toast.makeText(context,"username is not available", Toast.LENGTH_SHORT).show();
            }else {
                context.startActivity(new Intent(context, LoginActivity.class));
                Toast.makeText(context, "register successfully", Toast.LENGTH_SHORT).show();
            }
        }else if (type.equals("win")){
            Toast.makeText(context,"new mission unlock", Toast.LENGTH_SHORT).show();
            context.startActivity(new Intent(context, MissionActivity.class).putExtra("result",result));
        }else if (type.equals("upgrade")){
            Toast.makeText(context,"skill upgraded!!", Toast.LENGTH_SHORT).show();
            context.startActivity(new Intent(context, MenuActivity.class).putExtra("result",result));
        }
    }
    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
class getType {
    String data;
    public static Bundle mMyAppsBundle = new Bundle();
}
