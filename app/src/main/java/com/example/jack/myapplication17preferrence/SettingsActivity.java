package com.example.jack.myapplication17preferrence;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by jack on 5/31/15.
 */
public class SettingsActivity extends PreferenceActivity {


    @Deprecated
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.settings_preferrence);
    }
}
