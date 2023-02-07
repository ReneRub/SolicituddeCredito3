package com.example.renerubio.solicituddecredito2.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.renerubio.solicituddecredito2.R;


public class SessionManager {
    public static final SessionManager INSTANCE = new SessionManager();

    protected SessionManager() {

    }

    protected String getSharedPreferencesName(Context context) {
        String preferencesName = context.getString(R.string.app_name).replace(" ", "");
        preferencesName.replace(" ", "");
        return preferencesName;
    }

    public void setPreferences(Context context, String key, String value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(getSharedPreferencesName(context), Context.MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.commit();
    }

    public String getPreferences(Context context, String key) {
        SharedPreferences prefs = context.getSharedPreferences(getSharedPreferencesName(context), Context.MODE_PRIVATE);
        String position = prefs.getString(key, "");
        return position;
    }
}
