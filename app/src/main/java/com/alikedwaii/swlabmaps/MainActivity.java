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
    EditText secondsearch;
    Geocoder geocoder;
    List<Address> addressList;
    Address addr;
    double latitude1, longitude1;
    double latitude2, longitude2;
    String feature1, feature2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchbar = findViewById(R.id.search_bar);
        secondsearch = findViewById(R.id.second_search);
    }

    public void locationSearch(View view){
        location = searchbar.getText().toString();
        //get coords
        geocoder = new Geocoder(this);
        try {
            addressList = geocoder.getFromLocationName(location, 1);
            addr = addressList.get(0);
        } catch (IOException e) {

        }
        latitude1 = addr.getLatitude();
        longitude1 = addr.getLongitude();
        feature1 = addr.getPhone();
        //send Intent to map
        Intent intent;
        intent = new Intent(MainActivity.this, MapsActivity.class);
        //add coords to bundle
        intent.putExtra("latitude1", latitude1);
        intent.putExtra("longitude1", longitude1);
        intent.putExtra("feature1", feature1);
        startActivity(intent);
    }

    public void doubleSearch(View view){
        location = searchbar.getText().toString();
        //get coords
        geocoder = new Geocoder(this);
        try {
            addressList = geocoder.getFromLocationName(location, 1);
            addr = addressList.get(0);
        } catch (IOException e) {

        }
        latitude1 = addr.getLatitude();
        longitude1 = addr.getLongitude();
        feature1 = addr.getLocality();

        location = secondsearch.getText().toString();
        //get coords
        geocoder = new Geocoder(this);
        try {
            addressList = geocoder.getFromLocationName(location, 1);
            addr = addressList.get(0);
        } catch (IOException e) {

        }
        latitude2 = addr.getLatitude();
        longitude2 = addr.getLongitude();
        feature2 = addr.getLocality();

        //send Intent to map
        Intent intent;
        intent = new Intent(MainActivity.this, MapsActivity.class);
        //add coords to bundle
        intent.putExtra("latitude1", latitude1);
        intent.putExtra("longitude1", longitude1);
        intent.putExtra("feature1", feature1);
        intent.putExtra("latitude2", latitude2);
        intent.putExtra("longitude2", longitude2);
        intent.putExtra("feature2", feature2);
        startActivity(intent);
    }
}
