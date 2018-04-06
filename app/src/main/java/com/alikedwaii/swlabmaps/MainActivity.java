package com.alikedwaii.swlabmaps;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String location;
    String query;
    String key;
    EditText searchbar;
    Geocoder geocoder;
    List<Address> addressList;
    Address addr;
    double latitude, longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchbar = findViewById(R.id.search_bar);
    }

    public void locationSearch(View view){
        location = searchbar.getText().toString();
        //location = location.replace(' ', '+');
        //API query
        key = getString(R.string.google_maps_key);
        query = "https://maps.googleapis.com/maps/api/geocode/json?address=" + location + "&key=" + key;
        //get coords
        geocoder = new Geocoder(this);
        try {
            addressList = geocoder.getFromLocationName(location, 1);
            addr = addressList.get(0);
        } catch (IOException e) {

        }
        latitude = addr.getLatitude();
        longitude = addr.getLongitude();
        //send Intent to map
        Intent intent;
        intent = new Intent(MainActivity.this, MapsActivity.class);
        //add coords to bundle
        intent.putExtra("latitude", latitude);
        intent.putExtra("longitude", longitude);
        startActivity(intent);
    }
}
