package com.example.weatherproject;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.design.widget.FloatingActionButton;
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

import com.example.weatherproject.internet_conection.DataLoader;
import com.example.weatherproject.settings.SettingsActivity;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton fab;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = findViewById(R.id.fab);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            initFragment(new PreviewFragment(),R.id.preview);
            fab.setOnClickListener(fabOnClickListener);
        }else if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            initFragment(new PreviewFragment(),R.id.preview);
            initFragment(new WeatherFragment(),R.id.weather);
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.settings:
                intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void initFragment(Fragment initiatedFrag, int conteinerResId){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(conteinerResId,initiatedFrag);
        fragmentTransaction.commit();
    }
    public void runService(){
        Log.d("Internet", "runService: starting service ");
        Intent intent = new Intent(this, DataLoader.class);
        startService(intent);
    }
    private void runFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.findFragmentById(new WeatherFragment().getId()) == null) {
            fragmentManager.beginTransaction()
                    .replace(R.id.preview, new WeatherFragment(), WeatherFragment.NAME)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .addToBackStack(WeatherFragment.NAME).commit();
        }
    }

    private View.OnClickListener fabOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            runFragment();
            runService();
        }
    };
}
