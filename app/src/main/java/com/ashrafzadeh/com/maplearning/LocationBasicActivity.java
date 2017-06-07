package com.ashrafzadeh.com.maplearning;

import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LocationBasicActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener, LocationListener {


    private final String LOG_TAG ="LOCATION-LOG";

    TextView txtLatitude;
    TextView txtLongitude;
    TextView txtTime;
    TextView txtAccuracy;
    TextView txtSpeed;
    TextView txtAltitude;
    GoogleApiClient myGoogleApiClient;
    LocationRequest myLocationRequest;

    Location myLastLocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_basic);

        myGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        txtLatitude = (TextView)findViewById(R.id.lblLatitude);
        txtLongitude = (TextView)findViewById(R.id.lblLongitude);
        txtAccuracy = (TextView) findViewById(R.id.lblAccuracy);
        txtSpeed =(TextView) findViewById(R.id.lblSpeed);
        txtTime=(TextView) findViewById(R.id.lblTime);
        txtAltitude = (TextView) findViewById(R.id.lblAltitude);

    }

    @Override
    protected void onStart() {
        super.onStart();
        myGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(myGoogleApiClient.isConnected())
            myGoogleApiClient.disconnect();
    }

    @Override
    public void onConnected(Bundle bundle) {

        myLocationRequest = LocationRequest.create();
        myLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        myLocationRequest.setInterval(1000);

        LocationServices.FusedLocationApi.requestLocationUpdates(myGoogleApiClient,myLocationRequest,this);


    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(LOG_TAG,"Google Client API has been SUSPENDED");

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.i(LOG_TAG,"Google Client API has been FAILED");
    }

    @Override
    public void onLocationChanged(Location location) {
        txtLatitude.setText(Double.toString(location.getLatitude()));
        txtLongitude.setText(String.valueOf(location.getLongitude()));
        txtAccuracy.setText(String.valueOf(location.getAccuracy()));
        txtSpeed.setText(String.valueOf(location.getSpeed()));
        txtTime.setText(new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z").format(new Date(location.getTime())));
        txtAltitude.setText(String.valueOf(location.getAltitude()));

    }
}
