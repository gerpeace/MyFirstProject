package com.gerpeace.attackmatcher;

import android.content.Context;
import android.media.MediaPlayer;

public class Music {
    private static MediaPlayer mPlayer = null;

    public static void play(Context context, int resId){
        stop();

        if (Setting.getOptionMusic(context)) {
            mPlayer = MediaPlayer.create(context, resId);
            mPlayer.setLooping(true);
            mPlayer.start();
        }
    }

    public static void stop(){
        if (mPlayer != null){
            mPlayer.stop();
            mPlayer.release();
            mPlayer = null;
        }
    }
}