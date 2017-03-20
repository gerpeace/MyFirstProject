package com.gerpeace.attackmatcher;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MissionActivity extends AppCompatActivity {

    SharedPreferences sp = null;
    SharedPreferences.Editor editor;

    GridView gvMission;
    int[] imgLock,imgUnlock,imgCombine;
    ArrayList<String> misList,userId,moneyList,userList,chaList,skillList1,skillList2,skillList3,lv1List,lv2List,lv3List;
    int chaNum=10,misNum=30,skillNum=15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission);
        BindData();
        getData();
        BindArray();
        showMission();
        gvMission.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int mos_position = position;
                    if (misList.get(position).equals("1")){
                        int cha_position = sp.getInt("cha_position",0);

                        startActivity(new Intent(MissionActivity.this,GameActivity.class)
                                .putExtra("cha_position",cha_position)
                                .putExtra("mos_position",mos_position)
                                .putExtra("skillList1",skillList1)
                                .putExtra("skillList2",skillList2)
                                .putExtra("skillList3",skillList3)
                                .putExtra("misList",misList)
                                .putExtra("userId",userId)
                                .putExtra("moneyList",moneyList));

                    }else {
                        Toast.makeText(MissionActivity.this,"lock",Toast.LENGTH_SHORT).show();
                    }
            }
        });
    }
    public void BindData(){
        sp = getSharedPreferences("com.gerpeace", MODE_PRIVATE);
        editor = sp.edit();

        gvMission = (GridView) findViewById(R.id.gv_mission);
        misList = new ArrayList<String>();
        userId = new ArrayList<String>();
        moneyList = new ArrayList<String>();
        userList = new ArrayList<String>();
        chaList = new ArrayList<String>();

        skillList1 = new ArrayList<String>();
        skillList2 = new ArrayList<String>();
        skillList3 = new ArrayList<String>();

        lv1List = new ArrayList<String>();
        lv2List = new ArrayList<String>();
        lv3List = new ArrayList<String>();
        /*
        misList = getIntent().getStringArrayListExtra("mission");
        userId = getIntent().getStringArrayListExtra("userId");
        moneyList = getIntent().getStringArrayListExtra("moneyList");
        */

    }
    public void BindArray(){
        imgLock = new int[]{R.mipmap.lock1,R.mipmap.lock2,R.mipmap.lock3,R.mipmap.lock4,R.mipmap.lock5
                ,R.mipmap.lock6,R.mipmap.lock7,R.mipmap.lock8,R.mipmap.lock9,R.mipmap.lock10
                ,R.mipmap.lock11,R.mipmap.lock12,R.mipmap.lock13,R.mipmap.lock14,R.mipmap.lock15
                ,R.mipmap.lock16,R.mipmap.lock17,R.mipmap.lock18,R.mipmap.lock19,R.mipmap.lock20
                ,R.mipmap.lock21,R.mipmap.lock22,R.mipmap.lock23,R.mipmap.lock24,R.mipmap.lock25
                ,R.mipmap.lock26,R.mipmap.lock27,R.mipmap.lock28,R.mipmap.lock29,R.mipmap.lock30};
        imgUnlock = new int[]{R.mipmap.unlock1,R.mipmap.unlock2,R.mipmap.unlock3,R.mipmap.unlock4,R.mipmap.unlock5
                ,R.mipmap.unlock6,R.mipmap.unlock7,R.mipmap.unlock8,R.mipmap.unlock9,R.mipmap.unlock10
                ,R.mipmap.unlock11,R.mipmap.unlock12,R.mipmap.unlock13,R.mipmap.unlock14,R.mipmap.unlock15
                ,R.mipmap.unlock16,R.mipmap.unlock17,R.mipmap.unlock18,R.mipmap.unlock19,R.mipmap.unlock20
                ,R.mipmap.unlock21,R.mipmap.unlock22,R.mipmap.unlock23,R.mipmap.unlock24,R.mipmap.unlock25
                ,R.mipmap.unlock26,R.mipmap.unlock27,R.mipmap.unlock28,R.mipmap.unlock29,R.mipmap.unlock30};
        imgCombine = new int[imgLock.length];
        for (int i=0;i<imgCombine.length;i++){
            if (misList.get(i).equals("0")){
                imgCombine[i] = imgLock[i];
            }else{
                imgCombine[i] = imgUnlock[i];
            }
        }
    }
    public void showMission(){
        class ImageAdapter extends BaseAdapter {
            Context context;
            MenuActivity m = new MenuActivity();
            ImageView imageView;

            public ImageAdapter(Context ctx) {
                context = ctx;
            }

            @Override
            public int getCount() {
                return imgCombine.length;
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                if (convertView==null){

                    imageView = new ImageView(context);

                    //imageView.setLayoutParams(new GridView.LayoutParams(280,280));
                    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    imageView.setPadding(50,50,50,50);

                }else{
                    imageView = (ImageView) convertView;
                }
                imageView.setImageResource(imgCombine[position]);

                return imageView;
            }
        }
        gvMission.setAdapter(new ImageAdapter(this));
    }
    public void getData(){
        String data = getIntent().getStringExtra("result");
        try {
            //get all data
            JSONObject jsonObject = new JSONObject(data);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            for (int i=0;i<jsonArray.length();i++){
                JSONObject object = jsonArray.getJSONObject(i);
                userId.add(object.getString("user_id"));
                userList.add(object.getString("username"));
                moneyList.add(object.getString("money"));
                for (int c=1;c<=chaNum;c++){
                    chaList.add(object.getString("cha"+c));
                }
                for (int m=1;m<=misNum;m++){
                    misList.add(object.getString("mis"+m));
                }
                for (int m=1;m<=skillNum;m++){
                    skillList1.add(object.getString("skill1_"+m));
                    skillList2.add(object.getString("skill2_"+m));
                    skillList3.add(object.getString("skill3_"+m));

                    lv1List.add(object.getString("lv1_"+m));
                    lv2List.add(object.getString("lv2_"+m));
                    lv3List.add(object.getString("lv3_"+m));
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void backToMenu(View view){
        String type = "login";
        String strUser = sp.getString("username","");
        String strPass = sp.getString("password","");

        am_backoffice office = new am_backoffice(this);
        office.execute(type,strUser,strPass);
    }
}
