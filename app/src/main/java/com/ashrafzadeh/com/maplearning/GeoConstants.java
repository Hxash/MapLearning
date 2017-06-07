package com.ashrafzadeh.com.maplearning;

import com.google.android.gms.maps.model.LatLng;

import java.util.HashMap;

/**
 * Created by DevOS on 6/5/2017.
 */

public final class GeoConstants {

    private GeoConstants() {
    }

    public static final String PACKAGE_NAME = "com.google.android.gms.location.Geofence";

    public static final String SHARED_PREFERENCES_NAME = PACKAGE_NAME + ".SHARED_PREFERENCES_NAME";

    public static final String GEOFENCES_ADDED_KEY = PACKAGE_NAME + ".GEOFENCES_ADDED_KEY";

    /**
     * Used to set an expiration time for a geofence. After this amount of time Location Services
     * stops tracking the geofence.
     */
    public static final long GEOFENCE_EXPIRATION_IN_HOURS = 12;

    /**
     * For this sample, geofences expire after twelve hours.
     */
    public static final long GEOFENCE_EXPIRATION_IN_MILLISECONDS =
            GEOFENCE_EXPIRATION_IN_HOURS * 60 * 60 * 1000;
    //public static final float GEOFENCE_RADIUS_IN_METERS = 1609; // 1 mile, 1.6 km
    public static final float GEOFENCE_RADIUS_IN_METERS = 100; // 1 mile, 1.6 km

    /**
     * Map for storing information about airports in the San Francisco bay area.
     */
    public static final HashMap<String, LatLng> BAY_AREA_LANDMARKS = new HashMap<String, LatLng>();
    static {
        // San Francisco International Airport.
        BAY_AREA_LANDMARKS.put("Home Sweet Home", new LatLng(35.7473867,51.2808182));

        // Googleplex.
        BAY_AREA_LANDMARKS.put("Asrar ICT", new LatLng(35.7237349,51.3278852));

        // Test
        BAY_AREA_LANDMARKS.put("IUST Research Center", new LatLng(35.7380138,51.5004731));
    }

}