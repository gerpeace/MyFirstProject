package com.gerpeace.attackmatcher;

import android.content.Context;
import android.preference.PreferenceManager;

/**
 * Created by Administrator on 18/03/60.
 */

public class Setting {
    public static boolean getOptionMusic(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("music",true);
    }
    public static boolean getOptionHints(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("hints",true);
    }
}
