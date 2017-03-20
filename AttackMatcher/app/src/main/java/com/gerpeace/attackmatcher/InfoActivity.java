package com.gerpeace.attackmatcher;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class InfoActivity extends AppCompatActivity {

    SharedPreferences sp = null;
    SharedPreferences.Editor editor;

    ImageView chaPic, imgSkill;
    DecimalFormat df;
    TextView tvMoney, chaName, chaDetail, tvSkillName, tvSkillDetail, textUp;
    int cha_position,money,cha_pic,intImgSkill,skill_position=0,upMoney;
    String strName,strChaDetail,strSkillName,strSkillDetail;
    GridView gvSkill;
    int[] skillCha1,skillCha2,skillCha3,lv1,lv2,lv3,chaPicArray,iMoneyUp,skillResult1,skillResult2,skillResult3;
    String[] skillNameCha1,skillNameCha2,skillNameCha3,skillDetailCha1,skillDetailCha2,skillDetailCha3,chaNameArray,chaDetailArray;
    ArrayList<String> lv1List,lv2List,lv3List,userId,skillList1,skillList2,skillList3;
    Dialog upgrade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        BindData();
        BindArray();
        SetData();
        ShowData();
        SkillData();
    }
    public void BindData(){

        sp = getSharedPreferences("com.gerpeace", MODE_PRIVATE);
        editor = sp.edit();

        //upgrade dialog
        upgrade = new Dialog(this);
        upgrade.setContentView(R.layout.upgrade_dialog);
        textUp = (TextView) upgrade.findViewById(R.id.text_dialog);

        //Bind Data
        skillList1 = new ArrayList<String>();
        skillList2 = new ArrayList<String>();
        skillList3 = new ArrayList<String>();
        lv1List = new ArrayList<String>();
        lv2List = new ArrayList<String>();
        lv3List = new ArrayList<String>();
        userId = new ArrayList<String>();

        cha_position = getIntent().getIntExtra("cha_position",0);
        money = getIntent().getIntExtra("money",0);
        skillList1 = getIntent().getStringArrayListExtra("skillList1");
        skillList2 = getIntent().getStringArrayListExtra("skillList2");
        skillList3 = getIntent().getStringArrayListExtra("skillList3");
        lv1List = getIntent().getStringArrayListExtra("lv1List");
        lv2List = getIntent().getStringArrayListExtra("lv2List");
        lv3List = getIntent().getStringArrayListExtra("lv3List");
        userId = getIntent().getStringArrayListExtra("userId");

        lv1 = new int[lv1List.size()];
        lv2 = new int[lv1List.size()];
        lv3 = new int[lv1List.size()];
        for (int i=0;i<lv1List.size();i++){
            lv1[i] = new Integer(lv1List.get(i));
            lv2[i] = new Integer(lv2List.get(i));
            lv3[i] = new Integer(lv3List.get(i));
        }

        skillResult1 = new int[skillList1.size()];
        skillResult2 = new int[skillList1.size()];
        skillResult3 = new int[skillList1.size()];
        for (int i=0;i<lv1List.size();i++){
            skillResult1[i] = new Integer(skillList1.get(i));
            skillResult2[i] = new Integer(skillList2.get(i));
            skillResult3[i] = new Integer(skillList3.get(i));
        }

        cha_pic = R.mipmap.cha1_s;
        strName = "Character Name";
        strChaDetail = "Character Detail";
        chaPic = (ImageView) findViewById(R.id.pic);
        df = new DecimalFormat("0,000"+" G");
        tvMoney = (TextView) findViewById(R.id.tvMoney);
        chaName = (TextView) findViewById(R.id.cha_name);
        chaDetail = (TextView) findViewById(R.id.cha_detail);
        gvSkill = (GridView) findViewById(R.id.gv_skill);
        imgSkill =(ImageView) findViewById(R.id.img_skill);
        tvSkillName = (TextView) findViewById(R.id.tv_skill_name);
        tvSkillDetail = (TextView) findViewById(R.id.tv_skill_detail);
    }
    public void BindArray(){
        skillCha1 = new int[]{R.mipmap.cha1_skill_1, R.mipmap.cha1_skill_2, R.mipmap.cha1_skill_3, R.mipmap.cha1_skill_4, R.mipmap.cha1_skill_5
                , R.mipmap.cha1_skill_6, R.mipmap.cha1_skill_7, R.mipmap.cha1_skill_8, R.mipmap.cha1_skill_9, R.mipmap.cha1_skill_10
                , R.mipmap.cha1_skill_11, R.mipmap.cha1_skill_12, R.mipmap.cha1_skill_13, R.mipmap.cha1_skill_14, R.mipmap.cha1_skill_15};
        skillCha2 = new int[]{R.mipmap.cha2_skill_1, R.mipmap.cha2_skill_2, R.mipmap.cha2_skill_3, R.mipmap.cha2_skill_4, R.mipmap.cha2_skill_5
                , R.mipmap.cha2_skill_6, R.mipmap.cha2_skill_7, R.mipmap.cha2_skill_8, R.mipmap.cha2_skill_9, R.mipmap.cha2_skill_10
                , R.mipmap.cha2_skill_11, R.mipmap.cha2_skill_12, R.mipmap.cha2_skill_13, R.mipmap.cha2_skill_14, R.mipmap.cha2_skill_15};
        skillCha3 = new int[]{R.mipmap.cha3_skill_1, R.mipmap.cha3_skill_2, R.mipmap.cha3_skill_3, R.mipmap.cha3_skill_4, R.mipmap.cha3_skill_5
                , R.mipmap.cha3_skill_6, R.mipmap.cha3_skill_7, R.mipmap.cha3_skill_8, R.mipmap.cha3_skill_9, R.mipmap.cha3_skill_10
                , R.mipmap.cha3_skill_11, R.mipmap.cha3_skill_12, R.mipmap.cha3_skill_13, R.mipmap.cha3_skill_14, R.mipmap.cha3_skill_15};

        skillNameCha1 = new String[]{"Skill Cha1 Name 1 ","Skill Cha1 Name 2","Skill Cha1 Name 3","Skill Cha1 Name 4","Skill Cha1 Name 5"
                ,"Skill Cha1 Name 6","Skill Cha1 Name 7","Skill Cha1 Name 8","Skill Cha1 Name 9","Skill Cha1 Name 10"
                ,"Skill Cha1 Name 11","Skill Cha1 Name 12","Skill Cha1 Name 13","Skill Cha1 Name 14","Skill Cha1 Name 15"};
        skillNameCha2 = new String[]{"Skill Cha2 Name 1","Skill Cha2 Name 2","Skill Cha2 Name 3","Skill Cha2 Name 4","Skill Cha2 Name 5"
                ,"Skill Cha2 Name 6","Skill Cha2 Name 7","Skill Cha2 Name 8","Skill Cha2 Name 9","Skill Cha2 Name 10"
                ,"Skill Cha2 Name 11","Skill Cha2 Name 12","Skill Cha2 Name 13","Skill Cha2 Name 14","Skill Cha2 Name 15"};
        skillNameCha3 = new String[]{"Skill Cha3 Name 1","Skill Cha3 Name 2","Skill Cha3 Name 3","Skill Cha3 Name 4","Skill Cha3 Name 5"
                ,"Skill Cha3 Name 6","Skill Cha3 Name 7","Skill Cha3 Name 8","Skill Cha3 Name 9","Skill Cha3 Name 10"
                ,"Skill Cha3 Name 11","Skill Cha3 Name 12","Skill Cha3 Name 13","Skill Cha3 Name 14","Skill Cha3 Name 15"};

        skillDetailCha1 = new String[]{
                "Level : "+lv1[0]+"\nAttack : "+skillList1.get(0)
                ,"Level : "+lv1[1]+"\nAttack : "+skillList1.get(1)
                ,"Level : "+lv1[2]+"\nAttack : "+skillList1.get(2)
                ,"Level : "+lv1[3]+"\nAttack : "+skillList1.get(3)
                ,"Level : "+lv1[4]+"\nAttack : "+skillList1.get(4)
                ,"Level : "+lv1[5]+"\nAttack : "+skillList1.get(5)
                ,"Level : "+lv1[6]+"\nAttack : "+skillList1.get(6)
                ,"Level : "+lv1[7]+"\nAttack : "+skillList1.get(7)
                ,"Level : "+lv1[8]+"\nAttack : "+skillList1.get(8)
                ,"Level : "+lv1[9]+"\nAttack : "+skillList1.get(9)
                ,"Level : "+lv1[10]+"\nHeal : "+skillList1.get(10)
                ,"Level : "+lv1[11]+"\nHeal : "+skillList1.get(11)
                ,"Level : "+lv1[12]+"\nDefend attack : "+skillList1.get(12)+" turns"
                ,"Level : "+lv1[13]+"\nStun enemy "+skillList1.get(13)+" turns"
                ,"Level : "+lv1[14]+"\nRecover HP : "+skillList1.get(14)+" every 10 turns"};
        skillDetailCha2 = new String[]{
                "Level : "+lv2[0]+"\nAttack : "+skillList2.get(0)
                ,"Level : "+lv2[1]+"\nAttack : "+skillList2.get(1)
                ,"Level : "+lv2[2]+"\nAttack : "+skillList2.get(2)
                ,"Level : "+lv2[3]+"\nAttack : "+skillList2.get(3)
                ,"Level : "+lv2[4]+"\nAttack : "+skillList2.get(4)
                ,"Level : "+lv2[5]+"\nAttack : "+skillList2.get(5)
                ,"Level : "+lv2[6]+"\nAttack : "+skillList2.get(6)
                ,"Level : "+lv2[7]+"\nAttack : "+skillList2.get(7)
                ,"Level : "+lv2[8]+"\nAttack : "+skillList2.get(8)
                ,"Level : "+lv2[9]+"\nAttack : "+skillList2.get(9)
                ,"Level : "+lv2[10]+"\nAttack : "+skillList2.get(10)
                ,"Level : "+lv2[11]+"\nAttack : "+skillList2.get(11)
                ,"Level : "+lv2[12]+"\nAttack : "+skillList2.get(12)+"\nThere is bonus attack if use this skill after throw knife 1 and 2"
                ,"Level : "+lv2[13]+"\n100% evade attack "+skillList2.get(13)+" turns"
                ,"Level : "+lv2[14]+"\n"+skillList2.get(14)+"% evade attack"};

        skillDetailCha3 = new String[]{
                "Level : "+lv3[0]+"\nAttack : "+skillList3.get(0)+" (Lighting)"
                ,"Level : "+lv3[1]+"\nAttack : "+skillList3.get(1)+" (Lighting)"
                ,"Level : "+lv3[2]+"\nAttack : "+skillList3.get(2)+" (Lighting)"
                ,"Level : "+lv3[3]+"\nAttack : "+skillList3.get(3)+" (Lighting)"
                ,"Level : "+lv3[4]+"\nAttack : "+skillList3.get(4)+" (Fire)"
                ,"Level : "+lv3[5]+"\nAttack : "+skillList3.get(5)+" (Fire)"
                ,"Level : "+lv3[6]+"\nAttack : "+skillList3.get(6)+" (Fire)"
                ,"Level : "+lv3[7]+"\nAttack : "+skillList3.get(7)+" (Fire)"
                ,"Level : "+lv3[8]+"\nAttack both enemy and us : "+skillList3.get(8)+" (Darkness)"
                ,"Level : "+lv3[9]+"\nHeal : "+skillList3.get(9)
                ,"Level : "+lv3[10]+"\nHeal : "+skillList3.get(10)
                ,"Level : "+lv3[11]+"\nHeal : "+skillList3.get(11)
                ,"Level : "+lv3[12]+"\nHeal : "+skillList3.get(12)+" turns"
                ,"Level : "+lv3[13]+"\nStop enemy "+skillList3.get(13)+" turns\nif attack enemy by lighting, damage x 2"
                ,"Level : "+lv3[14]+"\nStop enemy : "+skillList3.get(14)+" turns\nif attack enemy by fire, damage x 2"};

        chaPicArray = new int[]{R.mipmap.cha1_s,R.mipmap.cha2_s,R.mipmap.cha3_s};
        chaNameArray = new String[]{"chaName1","chaName2","chaName3"};
        chaDetailArray = new String[]{"come from Final Fantasy VII, his weapon is large sword","come from One Piece, his weapon is leg","come from bra bra, her weapon is magic"};
        iMoneyUp = new int[10];
        for (int i=0;i<10;i++) {
            iMoneyUp[i] = ((i+1)*200)+1000;
        }
    }
    public void SetData(){
        cha_pic = chaPicArray[cha_position];
        strName = chaNameArray[cha_position];
        strChaDetail = chaDetailArray[cha_position];
        //Set Data
        if (cha_position == 0){
            intImgSkill = skillCha1[0];
            strSkillName = skillNameCha1[0];
            strSkillDetail = skillDetailCha1[0];
        }else if (cha_position == 1){
            intImgSkill = skillCha2[0];
            strSkillName = skillNameCha2[0];
            strSkillDetail = skillDetailCha2[0];
        }else if (cha_position == 2){
            intImgSkill = skillCha3[0];
            strSkillName = skillNameCha3[0];
            strSkillDetail = skillDetailCha3[0];
        }
    }
    public void ShowData(){
        //Show Data
        chaPic.setImageResource(cha_pic);
        tvMoney.setText(df.format(money));
        chaName.setText(strName);
        chaDetail.setText(strChaDetail);
        imgSkill.setImageResource(intImgSkill);
        tvSkillName.setText(strSkillName);
        tvSkillDetail.setText(strSkillDetail);
    }
    public void SkillData(){
        class ImageAdapter extends BaseAdapter {
            Context context;
            MenuActivity m = new MenuActivity();
            ImageView imageView;

            public ImageAdapter(Context ctx) {
                context = ctx;
            }

            @Override
            public int getCount() {
                return skillCha1.length;
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

                    imageView.setLayoutParams(new GridView.LayoutParams(280,280));
                    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    imageView.setPadding(10,25,10,25);

                }else{
                    imageView = (ImageView) convertView;
                }
                if (cha_position == 0) {
                    imageView.setImageResource(skillCha1[position]);
                }else if (cha_position == 1){
                    imageView.setImageResource(skillCha2[position]);
                }else if (cha_position == 2){
                    imageView.setImageResource(skillCha3[position]);
                }

                return imageView;

            }
        }
        gvSkill.setAdapter(new ImageAdapter(this));
        gvSkill.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                skill_position = position;
                if (cha_position == 0){
                    imgSkill.setImageResource(skillCha1[position]);
                    tvSkillName.setText(skillNameCha1[position]);
                    tvSkillDetail.setText(skillDetailCha1[position]);
                }else if (cha_position == 1){
                    imgSkill.setImageResource(skillCha2[position]);
                    tvSkillName.setText(skillNameCha2[position]);
                    tvSkillDetail.setText(skillDetailCha2[position]);
                }else if (cha_position == 2){
                    imgSkill.setImageResource(skillCha3[position]);
                    tvSkillName.setText(skillNameCha3[position]);
                    tvSkillDetail.setText(skillDetailCha3[position]);
                }
            }
        });
    }
    public void backToMenu(View view){
        String type = "login";
        String strUser = sp.getString("username","");
        String strPass = sp.getString("password","");

        am_backoffice office = new am_backoffice(this);
        office.execute(type,strUser,strPass);
    }
    public void OkUpgrade(View view){
        if (cha_position==0){
            lv1[skill_position] +=1;
            money = money-iMoneyUp[lv1[skill_position]];
            if (skill_position>=0 && skill_position<=11){
                skillResult1[skill_position]+=10;
            }else if (skill_position>=12 && skill_position<=13) {
                skillResult1[skill_position] += 1;
            }else if (skill_position==14){
                skillResult1[skill_position] += 5;
            }
        }else if (cha_position==1){
            lv2[skill_position] +=1;
            money = money-iMoneyUp[lv2[skill_position]];
            if (skill_position>=0 && skill_position<=12){
                skillResult2[skill_position]+=10;
            }else if (skill_position>=13 && skill_position<=14) {
                skillResult2[skill_position] += 1;
            }
        }else if (cha_position==2){
            lv3[skill_position] +=1;
            money = money-iMoneyUp[lv3[skill_position]];
            if (skill_position>=0 && skill_position<=12){
                skillResult3[skill_position]+=10;
            }else if (skill_position>=13 && skill_position<=14) {
                skillResult3[skill_position] += 1;
            }
        }

        String type = "upgrade";
        String strUserId = userId.get(0);
        String strMoney = money+"";
        String   strLv1_1=lv1[0]+""     ,strLv2_1=lv2[0]+""     ,strLv3_1=lv3[0]+""
                ,strLv1_2=lv1[1]+""     ,strLv2_2=lv2[1]+""     ,strLv3_2=lv3[1]+""
                ,strLv1_3=lv1[2]+""     ,strLv2_3=lv2[2]+""     ,strLv3_3=lv3[2]+""
                ,strLv1_4=lv1[3]+""     ,strLv2_4=lv2[3]+""     ,strLv3_4=lv3[3]+""
                ,strLv1_5=lv1[4]+""     ,strLv2_5=lv2[4]+""     ,strLv3_5=lv3[4]+""
                ,strLv1_6=lv1[5]+""     ,strLv2_6=lv2[5]+""     ,strLv3_6=lv3[5]+""
                ,strLv1_7=lv1[6]+""     ,strLv2_7=lv2[6]+""     ,strLv3_7=lv3[6]+""
                ,strLv1_8=lv1[7]+""     ,strLv2_8=lv2[7]+""     ,strLv3_8=lv3[7]+""
                ,strLv1_9=lv1[8]+""     ,strLv2_9=lv2[8]+""     ,strLv3_9=lv3[8]+""
                ,strLv1_10=lv1[9]+""    ,strLv2_10=lv2[9]+""    ,strLv3_10=lv3[9]+""
                ,strLv1_11=lv1[10]+""   ,strLv2_11=lv2[10]+""   ,strLv3_11=lv3[10]+""
                ,strLv1_12=lv1[11]+""   ,strLv2_12=lv2[11]+""   ,strLv3_12=lv3[11]+""
                ,strLv1_13=lv1[12]+""   ,strLv2_13=lv2[12]+""   ,strLv3_13=lv3[12]+""
                ,strLv1_14=lv1[13]+""   ,strLv2_14=lv2[13]+""   ,strLv3_14=lv3[13]+""
                ,strLv1_15=lv1[14]+""   ,strLv2_15=lv2[14]+""   ,strLv3_15=lv3[14]+"";
        String   strRes1_1=skillResult1[0]+""       ,strRes2_1=skillResult2[0]+""       ,strRes3_1=skillResult3[0]+""
                ,strRes1_2=skillResult1[1]+""       ,strRes2_2=skillResult2[1]+""       ,strRes3_2=skillResult3[1]+""
                ,strRes1_3=skillResult1[2]+""       ,strRes2_3=skillResult2[2]+""       ,strRes3_3=skillResult3[2]+""
                ,strRes1_4=skillResult1[3]+""       ,strRes2_4=skillResult2[3]+""       ,strRes3_4=skillResult3[3]+""
                ,strRes1_5=skillResult1[4]+""       ,strRes2_5=skillResult2[4]+""       ,strRes3_5=skillResult3[4]+""
                ,strRes1_6=skillResult1[5]+""       ,strRes2_6=skillResult2[5]+""       ,strRes3_6=skillResult3[5]+""
                ,strRes1_7=skillResult1[6]+""       ,strRes2_7=skillResult2[6]+""       ,strRes3_7=skillResult3[6]+""
                ,strRes1_8=skillResult1[7]+""       ,strRes2_8=skillResult2[7]+""       ,strRes3_8=skillResult3[7]+""
                ,strRes1_9=skillResult1[8]+""       ,strRes2_9=skillResult2[8]+""       ,strRes3_9=skillResult3[8]+""
                ,strRes1_10=skillResult1[9]+""      ,strRes2_10=skillResult2[9]+""      ,strRes3_10=skillResult3[9]+""
                ,strRes1_11=skillResult1[10]+""     ,strRes2_11=skillResult2[10]+""     ,strRes3_11=skillResult3[10]+""
                ,strRes1_12=skillResult1[11]+""     ,strRes2_12=skillResult2[11]+""     ,strRes3_12=skillResult3[11]+""
                ,strRes1_13=skillResult1[12]+""     ,strRes2_13=skillResult2[12]+""     ,strRes3_13=skillResult3[12]+""
                ,strRes1_14=skillResult1[13]+""     ,strRes2_14=skillResult2[13]+""     ,strRes3_14=skillResult3[13]+""
                ,strRes1_15=skillResult1[14]+""     ,strRes2_15=skillResult2[14]+""     ,strRes3_15=skillResult3[14]+"";

        am_backoffice office = new am_backoffice(this);
        office.execute(type,strUserId,strMoney
                ,strLv1_1,strLv1_2,strLv1_3,strLv1_4,strLv1_5,strLv1_6,strLv1_7,strLv1_8,strLv1_9,strLv1_10,strLv1_11,strLv1_12,strLv1_13,strLv1_14,strLv1_15
                ,strLv2_1,strLv2_2,strLv2_3,strLv2_4,strLv2_5,strLv2_6,strLv2_7,strLv2_8,strLv2_9,strLv2_10,strLv2_11,strLv2_12,strLv2_13,strLv2_14,strLv2_15
                ,strLv3_1,strLv3_2,strLv3_3,strLv3_4,strLv3_5,strLv3_6,strLv3_7,strLv3_8,strLv3_9,strLv3_10,strLv3_11,strLv3_12,strLv3_13,strLv3_14,strLv3_15
                ,strRes1_1,strRes1_2,strRes1_3,strRes1_4,strRes1_5,strRes1_6,strRes1_7,strRes1_8,strRes1_9,strRes1_10,strRes1_11,strRes1_12,strRes1_13,strRes1_14,strRes1_15
                ,strRes2_1,strRes2_2,strRes2_3,strRes2_4,strRes2_5,strRes2_6,strRes2_7,strRes2_8,strRes2_9,strRes2_10,strRes2_11,strRes2_12,strRes2_13,strRes2_14,strRes2_15
                ,strRes3_1,strRes3_2,strRes3_3,strRes3_4,strRes3_5,strRes3_6,strRes3_7,strRes3_8,strRes3_9,strRes3_10,strRes3_11,strRes3_12,strRes3_13,strRes3_14,strRes3_15
        );
    }
    public void OnUpgrade(View view){
        if (cha_position==0){
            upgrade.setTitle(skillNameCha1[skill_position]);
        }else if (cha_position==1){
            upgrade.setTitle(skillNameCha2[skill_position]);
        }else if (cha_position==2){
            upgrade.setTitle(skillNameCha3[skill_position]);
        }

        upgrade.show();

        if (cha_position==0){
            for (int i=0;i<=10;i++){
                if (lv1[skill_position]==(i+1)){
                    if (money>iMoneyUp[i]) {
                        textUp.setText("Need " + df.format(iMoneyUp[i]) + " to upgrade" + "\nAre you sure to upgrade?");
                    }else if (money<iMoneyUp[i]){
                        textUp.setText("Need " + df.format(iMoneyUp[i]) + " to upgrade" + "\nYour money is not enough");
                        Button Okbtn = (Button) upgrade.findViewById(R.id.ok_upgrade);
                        Okbtn.setVisibility(View.GONE);
                    }
                }else if (lv1[skill_position]==10){
                    textUp.setText("Cannot upgrade anymore\n max level skill is 10");
                    Button Okbtn = (Button) upgrade.findViewById(R.id.ok_upgrade);
                    Okbtn.setVisibility(View.GONE);
                }
            }
        }else if (cha_position==1){
            for (int i=0;i<=10;i++){
                if (lv1[skill_position]==(i+1)){
                    if (money>iMoneyUp[i]) {
                        textUp.setText("Need " + df.format(iMoneyUp[i]) + " to upgrade" + "\nAre you sure to upgrade?");
                    }else if (money<iMoneyUp[i]){
                        textUp.setText("Need " + df.format(iMoneyUp[i]) + " to upgrade" + "\nYour money is not enough");
                        Button Okbtn = (Button) upgrade.findViewById(R.id.ok_upgrade);
                        Okbtn.setVisibility(View.GONE);
                    }
                }else if (lv1[skill_position]==10){
                    textUp.setText("Cannot upgrade anymore\nlevel skill is full at 10");
                    Button Okbtn = (Button) upgrade.findViewById(R.id.ok_upgrade);
                    Okbtn.setVisibility(View.GONE);
                }
            }
        }else if (cha_position==2){
            for (int i=0;i<=10;i++){
                if (lv1[skill_position]==(i+1)){
                    if (money>iMoneyUp[i]) {
                        textUp.setText("Need " + df.format(iMoneyUp[i]) + " to upgrade" + "\nAre you sure to upgrade?");
                    }else if (money<iMoneyUp[i]){
                        textUp.setText("Need " + df.format(iMoneyUp[i]) + " to upgrade" + "\nYour money is not enough");
                        Button Okbtn = (Button) upgrade.findViewById(R.id.ok_upgrade);
                        Okbtn.setVisibility(View.GONE);
                    }
                }else if (lv1[skill_position]==10){
                    textUp.setText("Cannot upgrade anymore\nlevel skill is full at 10");
                    Button Okbtn = (Button) upgrade.findViewById(R.id.ok_upgrade);
                    Okbtn.setVisibility(View.GONE);
                }
            }
        }
    }
    public void CancelUp(View view){
        upgrade.dismiss();
    }
}
