package com.gerpeace.attackmatcher;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends AppCompatActivity {
    RelativeLayout chaHp,mosHp,gameFrame;
    LinearLayout chaMaxHp,mosMaxHp,layout;
    PlayGifView gameResult;

    String[] strMission;
    String money;

    int open=0,numMatched=0,chaCount=0,mosCount=0,firstClick=0,secondClick=0,cha_position, mos_position,stunTurn=0,freezeTurn=0,stoneTurn=0;
    int hideTurn=0,shieldTurn=0,cha2Combo=0,mosAttack=0,recoverCount=0,a=0,intMoney,sumMoney,throwShield;
    boolean recoverSkill=false,cha2Combo1=false,cha2Combo2=false;
    float chaWeightSumHp=2000,chaWeightHp=2000,mosWeightSumHp=500,mosWeightHp=500;
    int[] chaMove,mosMove,mosDamage,mosHpData,skillCha1,skillCha2,skillCha3,skillPic1,skillPic2,skillPic3,skill,chaHpData,actionSkill1,
    actionSkill2,actionSkill3,mosMoney,mosAction;
    float[] skillResult1,skillResult2,skillResult3;
    GridView gvShowCard,gvHideCard;
    TextView textViewMos, textViewCha, getMoney;
    ImageView firstView,secondView;
    PlayGifView startMatch,chaGif,mosGif,actionGif,actionGif2;
    ArrayList<String> skillList1,skillList2,skillList3,misList,userId,moneyList;
    Handler handler = new Handler();
    LinearLayout.LayoutParams chaParam,mosParam;
    Random rd;
    Animation bounce,blink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        BindData();
        SetArray();
        sumMoney = mosMoney[mos_position]+intMoney;
        //ShowCard();
        HideCard();
        HpManager();
        Graphic();
        HpUpdate();

    }
    //main
    public void BindData(){
        bounce = AnimationUtils.loadAnimation(this, R.anim.bounce);
        blink = AnimationUtils.loadAnimation(this,R.anim.blink);

        moneyList = new ArrayList<String>();
        moneyList = getIntent().getStringArrayListExtra("moneyList");
        intMoney = new Integer(moneyList.get(0));
        mos_position = getIntent().getIntExtra("mos_position",0);
        cha_position = getIntent().getIntExtra("cha_position",0);
        textViewMos = (TextView) findViewById(R.id.mostest_hp);
        textViewCha = (TextView) findViewById(R.id.chatest_hp);
        rd = new Random();
        gameResult = (PlayGifView) findViewById(R.id.game_result);
        gameFrame = (RelativeLayout) findViewById(R.id.game_result_frame);
        layout = (LinearLayout) findViewById(R.id.layout);
        gameFrame.setVisibility(RelativeLayout.GONE);
        chaGif = (PlayGifView) findViewById(R.id.chaGame);
        mosGif = (PlayGifView) findViewById(R.id.mosGame);
        actionGif = (PlayGifView) findViewById(R.id.action_gif);
        actionGif.setVisibility(View.GONE);
        actionGif2 = (PlayGifView) findViewById(R.id.action_gif);
        actionGif2.setVisibility(View.GONE);
        getMoney = (TextView) findViewById(R.id.get_money);
    }
    public void Graphic(){
        //character graphic
        chaGif.setImageResource(chaMove[cha_position]);
        //monster graphic
        mosGif.setImageResource(mosMove[mos_position]);

    }
    public void HpManager(){
        //HP number
        textViewMos.setText((int)mosWeightHp+"");
        textViewCha.setText((int)chaWeightHp+"");

        //character HP
        chaWeightSumHp = chaHpData[cha_position];
        chaWeightHp = chaHpData[cha_position];

        chaParam = new LinearLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        chaParam.weight = chaWeightHp;
        chaHp = (RelativeLayout) findViewById(R.id.cha_hp);
        chaHp.setLayoutParams(chaParam);
        chaMaxHp = (LinearLayout) findViewById(R.id.cha_max_hp);
        chaMaxHp.setWeightSum(chaWeightSumHp);

        //moster HP
        mosWeightSumHp = mosHpData[mos_position];
        mosWeightHp = mosHpData[mos_position];

        mosParam = new LinearLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        mosParam.weight = mosWeightHp;
        mosHp = (RelativeLayout) findViewById(R.id.mos_hp);
        mosHp.setLayoutParams(mosParam);
        mosMaxHp = (LinearLayout) findViewById(R.id.mos_max_hp);
        mosMaxHp.setWeightSum(mosWeightSumHp);
    }
    public void SetArray(){

        mosAction = new int[] {R.mipmap.mos1_atk_gif};

        actionSkill1 = new int[]{R.mipmap.cha1_skill1_gif,R.mipmap.cha1_skill2_gif,R.mipmap.cha1_skill3_gif,R.mipmap.cha1_skill4_gif,R.mipmap.cha1_skill5_gif
                ,R.mipmap.cha1_skill6_gif,R.mipmap.cha1_skill7_gif,R.mipmap.cha1_skill8_gif,R.mipmap.cha1_skill9_gif,R.mipmap.cha1_skill10_gif
                ,R.mipmap.cha1_skill11_gif,R.mipmap.cha1_skill12_gif,R.mipmap.cha1_skill13_gif,R.mipmap.cha1_skill14_gif,R.mipmap.cha1_skill15_gif};

        throwShield = R.mipmap.cha1_skill14_2_gif;

        actionSkill2 = new int[]{R.mipmap.cha2_skill1_gif,R.mipmap.cha2_skill2_gif,R.mipmap.cha2_skill3_gif,R.mipmap.cha2_skill4_gif,R.mipmap.cha2_skill5_gif,
                R.mipmap.cha2_skill6_gif,R.mipmap.cha2_skill7_gif,R.mipmap.cha2_skill8_gif,R.mipmap.cha2_skill9_gif,R.mipmap.cha2_skill10_gif,
                R.mipmap.cha2_skill11_gif,R.mipmap.cha2_skill12_gif,R.mipmap.cha2_skill3_gif,R.mipmap.cha2_skill14_gif,R.mipmap.cha2_skill15_gif};

        chaMove = new int[]  {R.mipmap.cha1_gif,R.mipmap.cha2_gif,R.mipmap.cha3_gif};
        mosMove = new int[] {R.mipmap.mos1_gif,R.mipmap.mos2_gif,R.mipmap.mos3_gif};
        mosMoney = new int[] {50,75,100};
        getMoney.setText(mosMoney[mos_position]+"");
        mosDamage = new int[] {50,75,100};
        mosHpData = new int[] {500,600,700};
        chaHpData = new int[]{1600,1200,1200};

        skillList1 = new ArrayList<String>();
        skillList2 = new ArrayList<String>();
        skillList3 = new ArrayList<String>();
        misList = new ArrayList<String>();
        userId = new ArrayList<String>();

        skillList1 = getIntent().getStringArrayListExtra("skillList1");
        skillList2 = getIntent().getStringArrayListExtra("skillList2");
        skillList3 = getIntent().getStringArrayListExtra("skillList3");
        misList = getIntent().getStringArrayListExtra("misList");
        userId = getIntent().getStringArrayListExtra("userId");

        skillResult1 = new float[skillList1.size()];
        skillResult2 = new float[skillList2.size()];
        skillResult3 = new float[skillList3.size()];
        strMission = new String[misList.size()];
        for (int i=0;i<misList.size();i++){
            strMission[i] = misList.get(i);
        }

        for (int i=0;i<skillList1.size();i++){
            skillResult1[i] = new Float(skillList1.get(i));
            skillResult2[i] = new Float(skillList2.get(i));
            skillResult3[i] = new Float(skillList3.get(i));
        }

        skillPic1 = new int[]{R.mipmap.cha1_skill_1, R.mipmap.cha1_skill_2, R.mipmap.cha1_skill_3
                , R.mipmap.cha1_skill_4, R.mipmap.cha1_skill_5, R.mipmap.cha1_skill_6, R.mipmap.cha1_skill_7
                ,R.mipmap.cha1_skill_8, R.mipmap.cha1_skill_9, R.mipmap.cha1_skill_10, R.mipmap.cha1_skill_11, R.mipmap.cha1_skill_12
                ,R.mipmap.cha1_skill_13, R.mipmap.cha1_skill_14, R.mipmap.cha1_skill_15};


        skillPic2 = new int[]{R.mipmap.cha2_skill_1, R.mipmap.cha2_skill_2, R.mipmap.cha2_skill_3
                , R.mipmap.cha2_skill_4, R.mipmap.cha2_skill_5
                , R.mipmap.cha2_skill_6, R.mipmap.cha2_skill_7, R.mipmap.cha2_skill_8
                , R.mipmap.cha2_skill_9, R.mipmap.cha2_skill_10
                , R.mipmap.cha2_skill_11, R.mipmap.cha2_skill_12, R.mipmap.cha2_skill_13
                , R.mipmap.cha2_skill_14, R.mipmap.cha2_skill_15};

        skillPic3 = new int[]{R.mipmap.cha3_skill_1, R.mipmap.cha3_skill_2, R.mipmap.cha3_skill_3
                , R.mipmap.cha3_skill_4, R.mipmap.cha3_skill_5
                , R.mipmap.cha3_skill_6, R.mipmap.cha3_skill_7, R.mipmap.cha3_skill_8
                , R.mipmap.cha3_skill_9, R.mipmap.cha3_skill_10
                , R.mipmap.cha3_skill_11, R.mipmap.cha3_skill_12, R.mipmap.cha3_skill_13
                , R.mipmap.cha3_skill_14, R.mipmap.cha3_skill_15};

        skillCha1 = new int[]{R.mipmap.cha1_skill_1, R.mipmap.cha1_skill_1, R.mipmap.cha1_skill_2, R.mipmap.cha1_skill_2, R.mipmap.cha1_skill_3
                ,R.mipmap.cha1_skill_3, R.mipmap.cha1_skill_4, R.mipmap.cha1_skill_4, R.mipmap.cha1_skill_5, R.mipmap.cha1_skill_5
                ,R.mipmap.cha1_skill_6, R.mipmap.cha1_skill_6, R.mipmap.cha1_skill_7, R.mipmap.cha1_skill_7, R.mipmap.cha1_skill_8
                ,R.mipmap.cha1_skill_8, R.mipmap.cha1_skill_9, R.mipmap.cha1_skill_9, R.mipmap.cha1_skill_10, R.mipmap.cha1_skill_10
                ,R.mipmap.cha1_skill_11, R.mipmap.cha1_skill_11, R.mipmap.cha1_skill_12, R.mipmap.cha1_skill_12, R.mipmap.cha1_skill_13
                ,R.mipmap.cha1_skill_13, R.mipmap.cha1_skill_14, R.mipmap.cha1_skill_14, R.mipmap.cha1_skill_15, R.mipmap.cha1_skill_15};


        skillCha2 = new int[]{R.mipmap.cha2_skill_1, R.mipmap.cha2_skill_1, R.mipmap.cha2_skill_2, R.mipmap.cha2_skill_2, R.mipmap.cha2_skill_3
                ,R.mipmap.cha2_skill_3, R.mipmap.cha2_skill_4, R.mipmap.cha2_skill_4, R.mipmap.cha2_skill_5, R.mipmap.cha2_skill_5
                ,R.mipmap.cha2_skill_6, R.mipmap.cha2_skill_6, R.mipmap.cha2_skill_7, R.mipmap.cha2_skill_7, R.mipmap.cha2_skill_8
                ,R.mipmap.cha2_skill_8, R.mipmap.cha2_skill_9, R.mipmap.cha2_skill_9, R.mipmap.cha2_skill_10, R.mipmap.cha2_skill_10
                ,R.mipmap.cha2_skill_11, R.mipmap.cha2_skill_11, R.mipmap.cha2_skill_12, R.mipmap.cha2_skill_12, R.mipmap.cha2_skill_13
                ,R.mipmap.cha2_skill_13, R.mipmap.cha2_skill_14, R.mipmap.cha2_skill_14, R.mipmap.cha2_skill_15, R.mipmap.cha2_skill_15};

        skillCha3 = new int[]{R.mipmap.cha3_skill_1, R.mipmap.cha3_skill_1, R.mipmap.cha3_skill_2, R.mipmap.cha3_skill_2, R.mipmap.cha3_skill_3
                ,R.mipmap.cha3_skill_3, R.mipmap.cha3_skill_4, R.mipmap.cha3_skill_4, R.mipmap.cha3_skill_5, R.mipmap.cha3_skill_5
                ,R.mipmap.cha3_skill_6, R.mipmap.cha3_skill_6, R.mipmap.cha3_skill_7, R.mipmap.cha3_skill_7, R.mipmap.cha3_skill_8
                ,R.mipmap.cha3_skill_8, R.mipmap.cha3_skill_9, R.mipmap.cha3_skill_9, R.mipmap.cha3_skill_10, R.mipmap.cha3_skill_10
                ,R.mipmap.cha3_skill_11, R.mipmap.cha3_skill_11, R.mipmap.cha3_skill_12, R.mipmap.cha3_skill_12, R.mipmap.cha3_skill_13
                ,R.mipmap.cha3_skill_13, R.mipmap.cha3_skill_14, R.mipmap.cha3_skill_14, R.mipmap.cha3_skill_15, R.mipmap.cha3_skill_15};

        shuffleArray(skillCha1);
        shuffleArray(skillCha2);
        shuffleArray(skillCha3);
    }
    public void shuffleArray(int[] ar){
        Random rd = new Random();
        for(int i=ar.length-1;i>=0;i--){
            int idx = rd.nextInt(i+1);
            int a = ar[idx];
            ar[idx] = ar[i];
            ar[i] = a;
        }
    }
    public void ShowCard(){
        class ImageAdapter extends BaseAdapter {
            Context context;
            MenuActivity m = new MenuActivity();
            ImageView imageView;
            Handler handler = new Handler();

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

                    imageView.setLayoutParams(new GridView.LayoutParams(220,220));
                    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    imageView.setPadding(25,25,25,25);

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
        gvShowCard = (GridView) findViewById(R.id.gv_show_card);
        gvShowCard.setAdapter(new ImageAdapter(this));
    }
    public void HideCard(){
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

                    imageView.setLayoutParams(new GridView.LayoutParams(220,220));
                    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    imageView.setPadding(25,25,25,25);

                }else{
                    imageView = (ImageView) convertView;
                }

                    imageView.setImageResource(R.mipmap.back_card);
                    imageView.startAnimation(bounce);
                return imageView;
            }
        }
        gvHideCard = (GridView) findViewById(R.id.gv_hide_card);
        gvHideCard.setAdapter(new ImageAdapter(this));
        //gvHideCard.setVisibility(View.GONE);

        startMatch = (PlayGifView) findViewById(R.id.start_match);
        startMatch.setImageResource(R.mipmap.start_match);

        handler.postDelayed(hideCard,4000);

        skill = new int[skillCha1.length];
        for (int i=0;i<skillCha1.length;i++){
            skill[i] = 0;
        }
        gvHideCard.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ImageView imageView = (ImageView) view;
                playSound(R.raw.ting);

                if (cha_position == 0) {
                    skill[position] = skillCha1[position];
                }else if (cha_position == 1){
                    skill[position] = skillCha2[position];
                }else if (cha_position == 2){
                    skill[position] = skillCha3[position];
                }
                imageView.setImageResource(skill[position]);
                open++;
                chaCount++;
                mosCount++;
                a = rd.nextInt(100);

                setRecoverSkill();

                // card setting
                if (open==1){
                    firstClick = position;
                    firstView = (ImageView) view;
                    firstView.setClickable(true);
                }else if (open==2){
                    firstView.setClickable(false);
                    secondClick = position;
                    secondView = (ImageView) view;
                    if (skill[firstClick]==skill[secondClick]){
                        open=0;
                        handler.postDelayed(matched,100);
                        numMatched++;
                        if (numMatched==(skillCha1.length/2)){
                            SetArray();
                            gvHideCard.setAdapter(new ImageAdapter(GameActivity.this));
                        }
                    }else{
                        open=0;
                        handler.postDelayed(unmatched,100);
                    }
                }else if(open > 2){
                    open=0;
                }

                monsterAttack();
                HpUpdate();

            }
        });
    }

    public void monsterAttack(){

        /*
        * mosAttack
        * 0 = normal attack, 1 = shield skill, 2 = stun skill, 3 = hide skill, 4 = dodge skill
        * 5 = freeze skill, 6 = stone skill
        * */
        if (mosAttack==0) {
            if (mosCount == 10) {
                chaWeightHp = chaWeightHp - mosDamage[mos_position];
                setMosAction();
            } else if (mosCount == 20) {
                chaWeightHp = chaWeightHp - mosDamage[mos_position];
                setMosAction();
            } else if (mosCount == 30) {
                chaWeightHp = chaWeightHp - (mosDamage[mos_position] * 3);
                mosCount = 0;
                setMosAction();
            }
        }else if (mosAttack==1){
            setShieldSkill();
        }else if (mosAttack==2){
            setStunSkill();
        }else if (mosAttack==3){
            setHideSkill();
        }else if (mosAttack==4){
            setDodgeSkill();
        }else if (mosAttack==5){
            setFreezeSkill();
        }else if (mosAttack==6){
            setStoneSkill();
        }
    }

    //skill character 1
    public void skillCharacter1(){
        //skill character 1
        //attack skill
        for (int i=0;i<=9;i++){
            if (skill[secondClick] == skillPic1[i]) {
                mosWeightHp = mosWeightHp - skillResult1[i];
            }
        }
        //heal skill
        for (int i=10;i<=11;i++){
            if (skill[secondClick] == skillPic1[i]) {
                if ((chaWeightHp + skillResult1[i])>chaWeightSumHp) {
                    chaWeightHp = chaWeightSumHp;
                }else {
                    chaWeightHp = chaWeightHp + skillResult1[i];
                }
            }
        }
        // shield
        if (skill[secondClick] == skillPic1[12]) {
            mosAttack=1;
        }
        // stun 6 turns
        if (skill[secondClick] == skillPic1[13]){
            mosAttack=2;
        }
        // recover hp
        if (skill[secondClick] == skillPic1[14]) {
            recoverSkill = true;
        }
        HpUpdate();
    }
    public void setShieldSkill(){
        shieldTurn++;
        if (mosCount == 10) {
            chaWeightHp = chaWeightHp - 0;
            setMosActionBlock();
        } else if (mosCount == 20) {
            chaWeightHp = chaWeightHp - 0;
            setMosActionBlock();
        } else if (mosCount == 30) {
            chaWeightHp = chaWeightHp - 0;
            mosCount = 0;
            setMosActionBlock();
        }
        if (shieldTurn>skillResult1[12]){
            mosAttack=0;
            shieldTurn=0;
            chaGif.setImageResource(chaMove[cha_position]);
        }

    }
    public void setStunSkill(){
        stunTurn++;
        mosCount--;
        if (stunTurn>=skillResult1[13]) {
            mosAttack=0;
            stunTurn=0;
            actionGif2.setVisibility(View.GONE);
        }
    }
    public void setRecoverSkill(){
        if (recoverSkill == true){
            recoverCount++;
            if (recoverCount==10) {
                chaGif.setVisibility(View.GONE);
                actionGif.setVisibility(View.VISIBLE);
                actionGif.setImageResource(actionSkill1[14]);
                handler.postDelayed(ReturnActionHeal,700);
                chaWeightHp = chaWeightHp + skillResult1[14];
                if (chaWeightHp>chaWeightSumHp) {
                    chaWeightHp=chaWeightSumHp;
                }
                HpUpdate();
                recoverCount=0;
            }
        }
    }

    //skill character 2
    public  void skillCharacter2(){
        //attack skill
        for (int i=0;i<=9;i++){
            if (skill[secondClick] == skillPic2[i]) {
                mosWeightHp = mosWeightHp - skillResult2[i];
            }
        }
        //character 2 combo skill
        if (skill[secondClick] == skillPic2[10]){
            cha2Combo1=true;
            mosWeightHp = mosWeightHp - skillResult2[10];
        }
        if (skill[secondClick] == skillPic2[11]){
            cha2Combo2=true;
            mosWeightHp = mosWeightHp - skillResult2[11];
        }
        if (cha2Combo1==true && cha2Combo2==false) {
            cha2Combo = 1;
        }else if (cha2Combo1==false && cha2Combo2==true){
            cha2Combo = 1;
        }else if (cha2Combo1==true && cha2Combo2==true){
            cha2Combo = 2;
        }
        if (skill[secondClick] == skillPic2[12]){
            if (cha2Combo==0){
                mosWeightHp = mosWeightHp - skillResult2[12];
            }else if (cha2Combo==1){
                mosWeightHp = mosWeightHp - skillResult2[12]*2;
            }else if (cha2Combo==2){
                mosWeightHp = mosWeightHp - skillResult2[12]*3;
            }
        }

        //Hide
        if (skill[secondClick] == skillPic2[13]) {
            mosAttack=3;
        }

        //Dodge 30%
        if (skill[secondClick] == skillPic2[14]){
            mosAttack=4;
        }

        HpUpdate();
    }
    public void setHideSkill(){
        hideTurn++;
        if (mosCount == 10) {
            chaWeightHp = chaWeightHp - 0;
            setMosActionMiss();
        } else if (mosCount == 20) {
            chaWeightHp = chaWeightHp - 0;
            setMosActionMiss();
        } else if (mosCount == 30) {
            chaWeightHp = chaWeightHp - 0;
            mosCount = 0;
            setMosActionMiss();
        }
        if (hideTurn>skillResult2[13]){
            mosAttack=0;
            hideTurn=0;
            chaGif.setImageResource(chaMove[cha_position]);
        }
    }
    public void setDodgeSkill(){
        if (a<=skillResult2[14]) {
            if (mosCount == 10) {
                chaWeightHp = chaWeightHp - 0;
                setMosActionMiss();
            } else if (mosCount == 20) {
                chaWeightHp = chaWeightHp - 0;
                setMosActionMiss();
            } else if (mosCount == 30) {
                chaWeightHp = chaWeightHp - 0;
                mosCount = 0;
                setMosActionMiss();
            }
        }else if (a>skillResult2[14]){
            if (mosCount == 10) {
                chaWeightHp = chaWeightHp - mosDamage[mos_position];
                setMosAction();
            } else if (mosCount == 20) {
                chaWeightHp = chaWeightHp - mosDamage[mos_position];
                setMosAction();
            } else if (mosCount == 30) {
                chaWeightHp = chaWeightHp - (mosDamage[mos_position] * 3);
                mosCount = 0;
                setMosAction();
            }
        }
    }

    //skill character 3
    public void skillCharacter3(){
        //attack skill
        for (int i=0;i<=7;i++){
            if (skill[secondClick] == skillPic3[i]) {
                mosWeightHp = mosWeightHp - skillResult3[i];
            }
        }
        //attack all
        if (skill[secondClick] == skillPic3[8]){
            mosWeightHp = mosWeightHp - skillResult3[8];
            chaWeightHp = chaWeightHp - skillResult3[8];
        }

        //heal skill
        for (int i=9;i<=11;i++){
            if (skill[secondClick] == skillPic3[i]) {
                if ((chaWeightHp + skillResult3[i])>chaWeightSumHp) {
                    chaWeightHp = chaWeightSumHp;
                }else {
                    chaWeightHp = chaWeightHp + skillResult3[i];
                }
            }
        }
        // Freeze
        if (skill[secondClick] == skillPic3[13]) {
            mosAttack=5;
            for (int i=0;i<=3;i++) {
                skillResult3[i] = skillResult3[i]*2;
            }
        }

        // Stone
        if (skill[secondClick] == skillPic3[14]) {
            mosAttack=6;
            for (int i=4;i<=7;i++) {
                skillResult3[i] = skillResult3[i]*2;
            }
        }

        HpUpdate();
    }
    public void setFreezeSkill(){
        freezeTurn++;
        mosCount--;
        if (freezeTurn>=skillResult3[13]){
            mosAttack=0;
            freezeTurn=0;
            for (int i=0;i<=3;i++) {
                skillResult3[i] = skillResult3[i]/2;
            }
        }
    }
    public void setStoneSkill(){
        stoneTurn++;
        mosCount--;
        if (stoneTurn>=skillResult3[14]){
            mosAttack=0;
            stoneTurn=0;
            for (int i=4;i<=7;i++) {
                skillResult3[i] = skillResult3[i]/2;
            }
        }
    }

    //support
    public void HpUpdate(){
        //monster HP
        textViewMos.setText((int)mosWeightHp+"");
        mosParam.weight = mosWeightHp;
        mosHp.setLayoutParams(mosParam);

        //character HP
        textViewCha.setText((int)chaWeightHp+"");
        chaParam.weight = chaWeightHp;
        chaHp.setLayoutParams(chaParam);
    }
    public void GameWin(){
        if ((int)mosWeightHp<=0) {
            playSound(R.raw.boogiedrum);
            Music.stop();
            gameFrame.setVisibility(RelativeLayout.VISIBLE);
            textViewMos.setText("0");
            gameResult.setImageResource(R.mipmap.youwin);
            handler.postDelayed(WinResult,7000);
        }
        if (strMission[mos_position+1].equals("0")){
            strMission[mos_position+1] = "1";
        }
    }
    public void GameLose(){
        if ((int)chaWeightHp<=0){
            gameFrame.setVisibility(RelativeLayout.VISIBLE);
            textViewCha.setText("0");
            gameResult.setImageResource(R.mipmap.youlose);
        }
    }
    public void updateMission(){
        String type = "win";
        money = sumMoney+"";
        String mis1=strMission[0],mis2=strMission[1],mis3=strMission[2],mis4=strMission[3],mis5=strMission[4],mis6=strMission[5],mis7=strMission[6],
                mis8=strMission[7],mis9=strMission[8],mis10=strMission[9],mis11=strMission[10],mis12=strMission[11],mis13=strMission[12],mis14=strMission[13],
                mis15=strMission[14],mis16=strMission[15],mis17=strMission[16],mis18=strMission[17],mis19=strMission[18],mis20=strMission[19],mis21=strMission[20],
                mis22=strMission[21],mis23=strMission[22],mis24=strMission[23],mis25=strMission[24],mis26=strMission[25],mis27=strMission[26],mis28=strMission[27],
                mis29=strMission[28],mis30=strMission[29];
        am_backoffice office = new am_backoffice(GameActivity.this);
        office.execute(type,userId.get(0),mis1,mis2,mis3,mis4,mis5,mis6,mis7,mis8,mis9,mis10,mis11,mis12,mis13,mis14,mis15,mis16,mis17,mis18,mis19,mis20
                ,mis21,mis22,mis23,mis24,mis25,mis26,mis27,mis28,mis29,mis30,money);
    }
    public void setActionSkill(){
        //action skill character 1
        for (int i=0;i<=9;i++){
            if (skill[secondClick]==skillPic1[i]){
                chaGif.setVisibility(View.GONE);
                actionGif.setVisibility(View.VISIBLE);
                actionGif.setImageResource(actionSkill1[i]);
                handler.postDelayed(ReturnAction,700);
            }
        }
        for (int i=10;i<=11;i++){
            if (skill[secondClick]==skillPic1[i]){
                chaGif.setVisibility(View.GONE);
                actionGif.setVisibility(View.VISIBLE);
                actionGif.setImageResource(actionSkill1[i]);
                handler.postDelayed(ReturnActionHeal,700);
            }
        }
        if (skill[secondClick]==skillPic1[12]){
            actionGif.setImageResource(actionSkill1[12]);
        }
        if (skill[secondClick]==skillPic1[13]){
            actionGif.setVisibility(View.VISIBLE);
            actionGif.setImageResource(throwShield);
            handler.postDelayed(ReturnActionStun,700);
        }
        if (skill[secondClick]==skillPic1[14]){
            chaGif.setVisibility(View.GONE);
            actionGif.setVisibility(View.VISIBLE);
            actionGif.setImageResource(actionSkill1[13]);
            handler.postDelayed(ReturnActionNormal,700);
        }

        //action skill character 2
        for (int i=0;i<=12;i++){
            if (skill[secondClick]==skillPic2[i]){
                chaGif.setVisibility(View.GONE);
                actionGif.setVisibility(View.VISIBLE);
                actionGif.setImageResource(actionSkill2[i]);
                handler.postDelayed(ReturnAction,700);
            }
        }
        for (int i=13;i<=14;i++){
            if (skill[secondClick]==skillPic2[i]){
                chaGif.setImageResource(actionSkill2[i]);
            }
        }
    }
    public void setMosAction(){
        mosGif.setVisibility(View.GONE);
        actionGif.setVisibility(View.VISIBLE);
        actionGif.setImageResource(mosAction[mos_position]);
        handler.postDelayed(ReturnActionMos,700);
    }
    public void setMosActionMiss(){
        mosGif.setVisibility(View.GONE);
        chaGif.setVisibility(View.VISIBLE);
        actionGif.setVisibility(View.VISIBLE);
        actionGif.setImageResource(mosAction[mos_position]);
        handler.postDelayed(ReturnActionMosMiss,700);
    }
    public void setMosActionBlock(){
        mosGif.setVisibility(View.GONE);
        actionGif.setVisibility(View.VISIBLE);
        actionGif.setImageResource(mosAction[mos_position]);
        handler.postDelayed(ReturnActionMosBlock,700);
    }

    public void texttest(View view){
        Toast.makeText(this,skillPic1[0]+"",Toast.LENGTH_SHORT).show();
    }


    //handler-------------------------
    public Runnable hideCard = new Runnable() {
        @Override
        public void run() {
            startMatch.setVisibility(View.GONE);
            //gvHideCard.setVisibility(View.VISIBLE);
        }
    };
    public Runnable matched = new Runnable() {
        @Override
        public void run() {
            skillCharacter1();
            skillCharacter2();
            skillCharacter3();
            setActionSkill();
            firstView.setVisibility(View.GONE);
            secondView.setVisibility(View.GONE);
            handler.postDelayed(gameWin,800);
        }
    };
    public Runnable unmatched = new Runnable() {
        @Override
        public void run() {
            firstView.setImageResource(R.mipmap.back_card);
            secondView.setImageResource(R.mipmap.back_card);
            GameLose();
        }
    };
    public Runnable ReturnAction = new Runnable() {
        @SuppressWarnings("ResourceAsColor")
        @Override
        public void run() {
            playSound(R.raw.yeah);
            chaGif.setVisibility(View.VISIBLE);
            actionGif.setVisibility(View.GONE);
            mosHp.startAnimation(blink);
            mosHp.setBackgroundColor(ContextCompat.getColor(GameActivity.this, R.color.red));
            handler.postDelayed(mosReturnHpColor,1000);
        }
    };
    public Runnable ReturnActionHeal = new Runnable() {
        @SuppressWarnings("ResourceAsColor")
        @Override
        public void run() {
            playSound(R.raw.heal);
            chaGif.setVisibility(View.VISIBLE);
            actionGif.setVisibility(View.GONE);
            chaHp.startAnimation(blink);
        }
    };
    public Runnable ReturnActionMos = new Runnable() {
        @SuppressWarnings("ResourceAsColor")
        @Override
        public void run() {
            playSound(R.raw.hit);
            mosGif.setVisibility(View.VISIBLE);
            actionGif.setVisibility(View.GONE);
            chaHp.startAnimation(blink);
            chaHp.setBackgroundColor(ContextCompat.getColor(GameActivity.this, R.color.red));
            handler.postDelayed(chaReturnHpColor,1000);
        }
    };
    public Runnable ReturnActionMosMiss = new Runnable() {
        @SuppressWarnings("ResourceAsColor")
        @Override
        public void run() {
            //playSound(R.raw.miss);
            mosGif.setVisibility(View.VISIBLE);
            actionGif.setVisibility(View.GONE);
        }
    };
    public Runnable ReturnActionMosBlock = new Runnable() {
        @SuppressWarnings("ResourceAsColor")
        @Override
        public void run() {
            //playSound(R.raw.block);
            mosGif.setVisibility(View.VISIBLE);
            actionGif.setVisibility(View.GONE);
        }
    };
    public Runnable ReturnActionStun = new Runnable() {
        @SuppressWarnings("ResourceAsColor")
        @Override
        public void run() {
            chaGif.setVisibility(View.VISIBLE);
            actionGif.setVisibility(View.GONE);
            actionGif2.setVisibility(View.VISIBLE);
            actionGif2.setImageResource(actionSkill1[13]);
        }
    };
    public Runnable ReturnActionNormal = new Runnable() {
        @SuppressWarnings("ResourceAsColor")
        @Override
        public void run() {
            playSound(R.raw.heal);
            chaGif.setVisibility(View.VISIBLE);
            actionGif.setVisibility(View.GONE);
        }
    };
    public Runnable mosReturnHpColor = new Runnable() {
        @SuppressWarnings("ResourceAsColor")
        @Override
        public void run() {
            mosHp.setBackgroundColor(ContextCompat.getColor(GameActivity.this, R.color.green));
        }
    };
    public Runnable chaReturnHpColor = new Runnable() {
        @SuppressWarnings("ResourceAsColor")
        @Override
        public void run() {
            chaHp.setBackgroundColor(ContextCompat.getColor(GameActivity.this, R.color.green));
        }
    };

    public Runnable WinResult = new Runnable() {
        @Override
        public void run() {
            updateMission();
        }
    };
    public Runnable gameWin = new Runnable() {
        @Override
        public void run() {
            GameWin();
        }
    };
    //-------------------------

    //music and sound

    @Override
    protected void onResume(){
        super.onResume();
        Music.play(this,R.raw.guitar);
    }
    @Override
    protected void onPause(){
        super.onPause();
        Music.stop();
    }
    public MediaPlayer mPlayer;
    public void playSound(int id){
        if (mPlayer != null){
            mPlayer.stop();
            mPlayer.release();
        }
        mPlayer = MediaPlayer.create(GameActivity.this, id);
        mPlayer.start();
    }
}
