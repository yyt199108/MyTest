package com.test.mytest.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.test.mytest.api.bean.AccountBean;
import com.test.mytest.application.MyApp;

/**
 * SharedPreferences的工具类
 */
public class PrefUtils {

    public static final String PREF_NAME = "pet_config";

    public static boolean getBoolean(Context ctx, String key, Boolean defaultValue) {
        SharedPreferences sp = ctx.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return sp.getBoolean(key, defaultValue);
    }

    public static void setBoolean(Context ctx, String key, Boolean value) {
        SharedPreferences sp = ctx.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        sp.edit().putBoolean(key, value).commit();
    }

    public static String getString(Context ctx, String key, String defaultValue) {
        SharedPreferences sp = ctx.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return sp.getString(key, defaultValue);
    }

    public static void setString(Context ctx, String key, String value) {
        SharedPreferences sp = ctx.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        sp.edit().putString(key, value).commit();
    }


    public static int getIntVaule(Context ctx, String key, int defaultValue) {
        SharedPreferences sp = ctx.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return sp.getInt(key, defaultValue);
    }

    public static void setIntVaule(Context ctx, String key, int value) {
        SharedPreferences sp = ctx.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        sp.edit().putInt(key, value).commit();
    }



    public final static String USER_INFO = "userInfo";



    /**
     * 保存用户信息
     */
    public static void saveUserInfo(AccountBean userInfo) {
        Gson gson = new Gson();
        setString(MyApp.getContext(), USER_INFO, gson.toJson(userInfo));
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    public static AccountBean getUserInfo() {
        String userInfo = getString(MyApp.getContext(), USER_INFO, null);
        if (userInfo != null && !userInfo.equals("")) {
            Gson gson = new Gson();
            return gson.fromJson(userInfo, AccountBean.class);
        } else {
            return null;
        }
    }

    public static void clearAll() {

        SharedPreferences sp = MyApp.getContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        sp.edit().clear().commit();
    }

}
