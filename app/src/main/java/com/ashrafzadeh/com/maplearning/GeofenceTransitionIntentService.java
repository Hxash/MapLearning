package com.ashrafzadeh.com.maplearning;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Log;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DevOS on 6/5/2017.
 */

public class GeofenceTransitionIntentService extends IntentService {

    protected static final String TAG ="gfservice";

    public GeofenceTransitionIntentService() {
        // Use the TAG to name the worker thread.
        super(TAG);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);
        if(geofencingEvent.hasError()){
            Log.e(TAG,"Error");
            return;
        }


        int geofenceTransition = geofencingEvent.getGeofenceTransition();

        if ((geofenceTransition == Geofence.GEOFENCE_TRANSITION_ENTER)||(geofenceTransition == Geofence.GEOFENCE_TRANSITION_EXIT))
        {
            List<Geofence> triggeringGeofences = geofencingEvent.getTriggeringGeofences();
            String geofenceTransitionDetails = getGeofenceTransitionDetails(this,geofenceTransition,triggeringGeofences);
            sendNotification(geofenceTransitionDetails);
        }
    }

    private String getGeofenceTransitionDetails(GeofenceTransitionIntentService geofenceTransitionIntentService, int geofenceTransition, List<Geofence> triggeringGeofences) {

        String movement;

        if (geofenceTransition == Geofence.GEOFENCE_TRANSITION_ENTER)  movement = "Enter";
        else if (geofenceTransition == Geofence.GEOFENCE_TRANSITION_EXIT)  movement = "Exit";
        else  movement = "Unknown";

        ArrayList triggeringGeofenceidList = new ArrayList();

        for(Geofence geofence : triggeringGeofences)
        {
            triggeringGeofenceidList.add(geofence.getRequestId());
        }

        String ids = TextUtils.join(", ",triggeringGeofenceidList);
        return movement + ": " + ids;
    }

    private void sendNotification(String notificationDetails){
        Intent notificationIntent = new Intent(getApplicationContext(),GeoFencingActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(GeoFencingActivity.class);
        stackBuilder.addNextIntent(notificationIntent);
        PendingIntent notificationPendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher_round)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round))
                .setColor(Color.RED)
                .setContentTitle(notificationDetails)
                .setContentText("Click here to open app")
                .setContentIntent(notificationPendingIntent);

        builder.setAutoCancel(true);
        NotificationManager myNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        myNotificationManager.notify(0,builder.build());
    }
}
