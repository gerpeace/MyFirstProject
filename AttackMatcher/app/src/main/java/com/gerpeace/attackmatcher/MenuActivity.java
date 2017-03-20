package com.gerpeace.attackmatcher;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    SharedPreferences sp = null;
    SharedPreferences.Editor editor;

    ArrayList<String> userList,moneyList,chaList,misList,skillList1,skillList2,skillList3,userId,lv1List,lv2List,lv3List;
    ArrayList<Integer> cha_s,cha;
    Float[] skillResult1,skillResult2,skillResult3;
    int chaNum = 10;
    int misNum = 30;
    int skillNum = 15;
    int[] chaPic_s = {R.mipmap.cha1_s,R.mipmap.cha2_s,R.mipmap.cha3_s};
    int[] chaPic = {R.mipmap.cha1,R.mipmap.cha2,R.mipmap.cha3};
    GridView lvCha_s;
    ImageView chaImg;
    TextView tvMoney;
    DecimalFormat df;
    int cha_position = 0;
    int money = 0;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        BindVar();
        getData();
        Toast.makeText(this,"Hi "+userList.get(0),Toast.LENGTH_SHORT).show();
        setData();
        showData();
    }
    public void BindVar(){
        sp = getSharedPreferences("com.gerpeace", MODE_PRIVATE);
        editor = sp.edit();
        userId = new ArrayList<String>();
        userList = new ArrayList<String>();
        moneyList = new ArrayList<String>();
        chaList = new ArrayList<String>();
        misList = new ArrayList<String>();
        skillList1 = new ArrayList<String>();
        skillList2 = new ArrayList<String>();
        skillList3 = new ArrayList<String>();
        lv1List = new ArrayList<String>();
        lv2List = new ArrayList<String>();
        lv3List = new ArrayList<String>();
        cha_s = new ArrayList<Integer>();
        cha = new ArrayList<Integer>();
        skillResult1 = new Float[skillNum];
        skillResult2 = new Float[skillNum];
        skillResult3 = new Float[skillNum];
        lvCha_s = (GridView) findViewById(R.id.cha_list);
        chaImg = (ImageView) findViewById(R.id.imageCha);
        tvMoney = (TextView) findViewById(R.id.money_tv);

        df = new DecimalFormat("0,000");
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
    public void setData(){
        for (int i=0;i<chaNum;i++){
            if (chaList.get(i).equals("1")){
                cha_s.add(chaPic_s[i]);
                cha.add(chaPic[i]);
            }
        }
    }
    public void showData(){
        chaImg.setImageResource(cha.get(sp.getInt("cha_position",0)));
        //character box
        class ImageAdapter extends BaseAdapter {
            Context context;
            MenuActivity m = new MenuActivity();
            ImageView imageView;

            public ImageAdapter(Context ctx) {
                context = ctx;
            }

            @Override
            public int getCount() {
                return cha_s.size();
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
                    imageView.setPadding(10,25,10,25);

                }else{
                    imageView = (ImageView) convertView;
                }
                imageView.setImageResource(cha_s.get(position));

                return imageView;

            }
        }
        lvCha_s.setAdapter(new ImageAdapter(this));
        lvCha_s.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                chaImg.setImageResource(cha.get(position));
                cha_position = position;
                editor.putInt("cha_position",position);
                editor.commit();
            }
        });

        //money
        money = new Integer(moneyList.get(0));
        tvMoney.setText(df.format(money)+" G");
    }
    public void goInfo(View view){
        startActivity(new Intent(MenuActivity.this,InfoActivity.class)
                .putExtra("skillList1",skillList1)
                .putExtra("skillList2",skillList2)
                .putExtra("skillList3",skillList3)
                .putExtra("cha_position",cha_position)
                .putExtra("lv1List",lv1List)
                .putExtra("lv2List",lv2List)
                .putExtra("lv3List",lv3List)
                .putExtra("userId",userId)
                .putExtra("money",money));
    }
    public void goMission(View view){
        String data = getIntent().getStringExtra("result");
        startActivity(new Intent(MenuActivity.this,MissionActivity.class)
                .putExtra("result",data));
        /*
        startActivity(new Intent(MenuActivity.this,MissionActivity.class)
                .putExtra("mission",misList)
                .putExtra("cha_position",cha_position)
                .putExtra("skillList1",skillList1)
                .putExtra("skillList2",skillList2)
                .putExtra("skillList3",skillList3)
                .putExtra("userId",userId)
                .putExtra("moneyList",moneyList));
                */

    }
}
