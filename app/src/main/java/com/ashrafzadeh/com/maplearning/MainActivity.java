package com.ashrafzadeh.com.maplearning;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap myMap;
    boolean mapReady = false;

    static final CameraPosition TEHRAN = CameraPosition.builder()
            .target(new LatLng(35.723889,51.327780))
            .zoom(15)
            .bearing(195)
            .tilt(34)
            .build();

    MarkerOptions ASRARICT;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button btnMap = (Button) findViewById(R.id.btnMap);
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mapReady)
                    myMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            }
        });

        Button btnSatellite = (Button) findViewById(R.id.btnSatellite);
        btnSatellite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mapReady)
                    myMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            }
        });

        Button btnHybrid = (Button) findViewById(R.id.btnHybrid);
        btnHybrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mapReady)
                    myMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            }
        });


        Button btnTehran = (Button) findViewById(R.id.btnTehran);
        btnTehran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mapReady)
                {
                    flyTo(TEHRAN);
                }
            }
        });

        Button btnSydney= (Button) findViewById(R.id.btnsydney);
        btnSydney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mapReady)
                {
                    LatLng sydney = new LatLng(-33.8706948,151.2073142);
                    CameraPosition sY = CameraPosition.builder().target(sydney).zoom(17).build();
                    myMap.animateCamera(CameraUpdateFactory.newCameraPosition(sY),5000,null);
                }
            }
        });

        Button btnToronto =(Button) findViewById(R.id.btnToronto);
        btnToronto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mapReady)
                {
                    LatLng toronto = new LatLng(43.6346061,-79.3907455);
                    CameraPosition tR = CameraPosition.builder().target(toronto).zoom(14).build();
                    myMap.animateCamera(CameraUpdateFactory.newCameraPosition(tR),5000,null);
                }
            }
        });

        Button btnStreetView =(Button) findViewById(R.id.btnStreetView);
        btnStreetView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,StreetViewActivity.class);
                startActivity(intent);
            }
        });


        Button btnLocationBasic =(Button) findViewById(R.id.btnLocationBasic);
        btnLocationBasic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,LocationBasicActivity.class);
                startActivity(intent);
            }
        });


        Button btnActvityRecognition =(Button) findViewById(R.id.btnActivityRecognition);
        btnActvityRecognition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,LocationServiceActivity.class);
                startActivity(intent);
            }
        });

        Button btnGeofence =(Button) findViewById(R.id.btnGeoFence);
        btnGeofence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,GeoFencingActivity.class);
                startActivity(intent);
            }
        });


        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mapReady= true;
        myMap = googleMap;

        ASRARICT = new MarkerOptions().position(new LatLng(35.723889, 51.327780)).title("Asrar ICT").icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher_round)); //R.drawable.<x>
        myMap.addMarker(ASRARICT);


        LatLng newYork = new LatLng(40.7484,-73.9857);
        CameraPosition nY = CameraPosition.builder().target(newYork).zoom(14).tilt(67).bearing(135).build();
        myMap.moveCamera(CameraUpdateFactory.newCameraPosition(nY));
    }

    public void flyTo(CameraPosition target)
    {
        myMap.animateCamera(CameraUpdateFactory.newCameraPosition(target),8000,null);
        myMap.addPolyline(new PolylineOptions()
                .add(new LatLng(35.721889, 51.317780))
                .add(new LatLng(35.724889, 51.347780))
                .add(new LatLng(35.723889, 51.325780))
                .add(new LatLng(35.721889, 51.317780)));
        myMap.addCircle(new CircleOptions()
                .center(new LatLng(35.723889, 51.327780))
                .radius(10)
                .strokeColor(Color.RED)
                .fillColor(Color.argb(64,0,255,0)));
    }
}
