package com.example.weatherproject;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.weatherproject.DataBase.DBHelper;
import com.example.weatherproject.internetСonection.DataLoader;
import com.example.weatherproject.settings.SettingsActivity;

public class MainActivity extends AppCompatActivity {

    private Location lastLocation;
    private LocationManager locationManager;
    private static DBHelper database;
    @Override
    protected void onStart() {
        super.onStart();
        if(database == null){
            DBHelper.initInstance(this);
            database = DBHelper.getInstance();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this,"You need a location permission",Toast.LENGTH_LONG).show();
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,10000,10,locationListener);
        }else{
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 10, locationListener);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            initFragment(new PreviewFragment(),R.id.preview);

        }else if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            initFragment(new PreviewFragment(),R.id.preview);
            initFragment(new WeatherFragment(),R.id.weather);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        locationManager.removeUpdates(locationListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        if (item.getItemId() == R.id.settings) {
            intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void initFragment(Fragment initiatedFrag, int conteinerResId){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(conteinerResId,initiatedFrag);
        fragmentTransaction.commit();
    }

    public void runFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.findFragmentById(new WeatherFragment().getId()) == null) {
            fragmentManager.beginTransaction()
                    .replace(R.id.preview, new WeatherFragment(), WeatherFragment.NAME)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .addToBackStack(WeatherFragment.NAME).commit();
        }
    }

    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            lastLocation = location;
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    public Location getLastLocation() {
        return lastLocation;
    }
}
