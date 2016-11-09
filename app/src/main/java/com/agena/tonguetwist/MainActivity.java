package com.agena.tonguetwist;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.widget.RemoteViews;


public class MainActivity extends Activity {

    NotificationManager manager;
    Notification.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_view);

        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        builder = new Notification.Builder(getApplicationContext());
        builder.setSmallIcon(R.drawable.world).setContentTitle("");
        builder.setVisibility(Notification.VISIBILITY_PUBLIC).setContentTitle("").setContentText("");

        RemoteViews remoteView = new RemoteViews(getPackageName(),R.layout.activity_notification_view);

        remoteView.setOnClickPendingIntent(R.id.button1, buildPendingIntent("e1_b1", "e2_b1", 0));
        remoteView.setOnClickPendingIntent(R.id.button2, buildPendingIntent("e1_b2", "e2_b2", 1));
        remoteView.setOnClickPendingIntent(R.id.button3, buildPendingIntent("e1_b3", "e2_b3", 2));

        //the intent that is started when the notification is clicked (works)
        Intent notificationIntent = new Intent(this, NotificationView.class);
        PendingIntent pendingNotificationIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

        Notification notification = builder.build();
        notification.flags |= Notification.FLAG_NO_CLEAR;
        notification.contentView = remoteView;
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nm.notify(Notification.PRIORITY_MAX, notification);

    }

    private PendingIntent buildPendingIntent(String extra1, String extra2, int id) {
        Intent localeIntent = ChangeLanguageReceiver.buildChangeLocaleIntent(extra1, extra2);
        return PendingIntent.getBroadcast(this, id, localeIntent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

}
