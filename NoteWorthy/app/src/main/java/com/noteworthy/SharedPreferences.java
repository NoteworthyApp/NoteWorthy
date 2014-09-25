package com.noteworthy;

import android.content.Context;
import android.preference.PreferenceManager;

/**
 * Created by israelhill on 9/21/14.
 */
public class SharedPreferences {

    static final String USERNAME_PREF = "username";

    static final android.content.SharedPreferences getSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void setUsername(Context context, String username) {
        android.content.SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(USERNAME_PREF, username);
        editor.commit();
    }

    public static String getUsernamePref(Context context) {
        return getSharedPreferences(context).getString(USERNAME_PREF, "");
    }
        }
