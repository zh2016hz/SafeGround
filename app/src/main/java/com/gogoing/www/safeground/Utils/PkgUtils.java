package com.gogoing.www.safeground.Utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.gogoing.www.safeground.R;

/**
 * Created by Administrator on 2017/5/2 0002.
 */

public class PkgUtils {
    public static String getVersionName(Context context) {

        //获取包管理器
        PackageManager packageManager = context.getPackageManager();
        //获取包信息，第一个参数传包名，第二个传0返回所有信息
        try {
            PackageInfo info = packageManager.getPackageInfo(context.getPackageName(), 0);
            return info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "未知版本";
        }
    }
    public static int getVersioCode(Context context) {

        //获取包管理器
        PackageManager packageManager = context.getPackageManager();
        //获取包信息，第一个参数传包名，第二个传0返回所有信息
        try {
            PackageInfo info = packageManager.getPackageInfo(context.getPackageName(), 0);
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return -1;
        }
    }

}
