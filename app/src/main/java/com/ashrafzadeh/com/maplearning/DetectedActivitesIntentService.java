package com.ashrafzadeh.com.maplearning;

import android.app.IntentService;
import android.content.Intent;
import android.provider.SyncStateContract;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.android.gms.location.ActivityRecognitionResult;
import com.google.android.gms.location.DetectedActivity;

import java.util.ArrayList;

/**
 * Created by Asrar on 27/05/2017.
 */

public class DetectedActivitesIntentService extends IntentService {

    protected static final String TAG = "detection_is";

    public DetectedActivitesIntentService(String name) {
        super(name);
    }


    public DetectedActivitesIntentService() {
        super(TAG);
    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        ActivityRecognitionResult result = ActivityRecognitionResult.extractResult(intent);
        Intent localIntent = new Intent(Constants.BROADCAST_ACTION);

        // Get the list of the probable activities associated with the current state of the
        // device. Each activity is associated with a confidence level, which is an int between
        // 0 and 100.
        ArrayList<DetectedActivity> detectedActivities = (ArrayList) result.getProbableActivities();

        // Log each activity.
        Log.i(TAG, "activities detected");

        // Broadcast the list of detected activities.
        localIntent.putExtra(Constants.ACTIVITY_EXTRA, detectedActivities);
        LocalBroadcastManager.getInstance(this).sendBroadcast(localIntent);
    }
}
