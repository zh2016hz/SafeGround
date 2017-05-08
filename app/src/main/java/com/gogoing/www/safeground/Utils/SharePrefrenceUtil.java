package com.gogoing.www.safeground.Utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2017/5/7 0007.
 */

public class SharePrefrenceUtil {
    public static SharedPreferences instance;

    public static SharedPreferences getShareprefrences(Context context) {
        if (instance == null) {
            instance = context.getSharedPreferences(Constant.Shareprefrence, Context.MODE_PRIVATE);
        }
        return instance;
    }

    public  static  Boolean getBoolean(Context context, String xx, Boolean flag) {
        SharedPreferences shareprefrences = getShareprefrences(context);
        return shareprefrences.getBoolean(xx, flag);
    }

    public static Boolean getBoolean(Context context, String xx) {
        return getBoolean(context, xx, true);
    }

    public static  void putBoolean(Context context, String key, Boolean value) {
        SharedPreferences shareprefrences = getShareprefrences(context);
        SharedPreferences.Editor editor = shareprefrences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public  static  String  getpwd(Context context, String xx, String  pwd) {
        SharedPreferences shareprefrences = getShareprefrences(context);
        return shareprefrences.getString(xx, pwd);
    }
    public  static  String  getpwd(Context context, String xx) {
        return getpwd(context,xx, null);
    }
        public  static  void putpwd(Context context, String key, String  value) {
        SharedPreferences shareprefrences = getShareprefrences(context);
        SharedPreferences.Editor editor = shareprefrences.edit();
        editor.putString(key, value);
        editor.commit();
    }

}
