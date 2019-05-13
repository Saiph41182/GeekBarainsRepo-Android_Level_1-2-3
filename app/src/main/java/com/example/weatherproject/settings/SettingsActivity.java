package com.example.weatherproject.settings;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.weatherproject.R;
import com.example.weatherproject.settings.subSettings.BaseFragment;
import com.example.weatherproject.settings.controllers.FragmentController;
import com.example.weatherproject.settings.subSettings.SettingsParams;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class SettingsActivity extends AppCompatActivity {
    public static final String APP_PREFERENCES = "SettingsPreferences";
    private FragmentController fragmentController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        fragmentController = BaseFragment.getFragmentController();

        if (fragmentController.isEmpty()) {
            showFragment(new SettingsFragment());
        }else{
            showFragment(fragmentController.getLast());
        }
    }
    public void showFragment(BaseFragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.settings, fragment,fragment.getName())
                .commit();
        fragmentController.add(fragment);
    }

    @Override
    public void onBackPressed() {
        if(fragmentController.getSize() <= 1){
            super.onBackPressed();
        }else{
            fragmentController.popFragment();
            showFragment(fragmentController.getLast());
        }
    }
}
