package com.agena.tonguetwist;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

//import android.app.ActivityManagerNative;
//import android.app.IActivityManager;
//import android.content.res.Configuration;
//import android.os.RemoteException;

import java.util.Locale;

/**
 * Created by cesar.agena on 04/11/2016.
 */
public class ChangeLanguageReceiver extends BroadcastReceiver{

    public static final String ACTION_CHANGE_LOCALE = "com.agena.tonguetwist.MYACTION";
    public static final String EXTRA_1 = "com.agena.tonguetwist.EXTRA_1";
    public static final String EXTRA_2 = "com.agena.tonguetwist.EXTRA_2";

    public ChangeLanguageReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() == ACTION_CHANGE_LOCALE) {
            setLocale(context, intent.getStringExtra(EXTRA_1), intent.getStringExtra(EXTRA_2));
        }
    }

    private void setLocale(Context context, String extra1, String extra2) {
        // do the job
        System.out.println(extra1 + "/" + extra2);
    }

    public static Intent buildChangeLocaleIntent(String extra1, String extra2 ) {
        Intent intent = new Intent();
        intent.setAction(ACTION_CHANGE_LOCALE);
        intent.putExtra(EXTRA_1, extra1);
        intent.putExtra(EXTRA_2, extra2);
        return intent;
    }
}
