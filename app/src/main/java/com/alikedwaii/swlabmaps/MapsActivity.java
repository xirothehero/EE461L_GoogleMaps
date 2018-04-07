package com.alikedwaii.swlabmaps;

import android.content.Intent;
import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.w3c.dom.Text;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private TextView feature1;
    private TextView feature2;
    private TextView distance;

    private boolean has2;

    private String f1, f2;

    private double latitude1;
    private double longitude1;

    private double latitude2;
    private double longitude2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        feature1 = findViewById(R.id.feature1);
        feature2 = findViewById(R.id.feature2);
        distance = findViewById(R.id.distance);

        has2 = false;
        //set lat and long
        Intent intent = getIntent();
        latitude1 = intent.getDoubleExtra("latitude1", 0.00);
        longitude1 = intent.getDoubleExtra("longitude1", 0.00);
        f1 = intent.getStringExtra("feature1");
        if (intent.hasExtra("latitude2")) {
            latitude2 = intent.getDoubleExtra("latitude2", 0.00);
            has2 = true;
        }
        if (intent.hasExtra("longitude2")) longitude2 = intent.getDoubleExtra("longitude2", 0.00);
        if (intent.hasExtra("feature2")) f2 = intent.getStringExtra("feature2");
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in location and move the camera
        LatLng newLocation = new LatLng(latitude1, longitude1);
        mMap.addMarker(new MarkerOptions().position(newLocation).title("Marker 1"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(newLocation));
        mMap.setMinZoomPreference(3);
        if(f1 != null) {
            feature1.setText(f1);
        }

        if (has2){
            LatLng newLocation2 = new LatLng(latitude2, longitude2);
            mMap.addMarker(new MarkerOptions().position(newLocation2).title("Marker 2"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(newLocation2));
            if(f2 != null) {
                feature2.setText(f2);
            }

            float[] result = new float[1];
            Location.distanceBetween(latitude1, longitude1,
                    longitude2, longitude2,
                    result);
            String distText = "Distance between locations: " + String.valueOf(result[0]) + " m";
            distance.setText(distText);
        }

        
    }
}
