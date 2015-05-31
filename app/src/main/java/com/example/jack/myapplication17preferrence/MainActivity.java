package com.example.jack.myapplication17preferrence;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends ActionBarActivity {

    private EditText editText;
    private Button stnButton;
    private final static int Setting_Code =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText)findViewById(R.id.notesEditText);

        if (savedInstanceState != null){

            String savedString = savedInstanceState.getString("NOTES").toString();

            editText.setText(savedString);

        }
        String spNotes = getPreferences(Context.MODE_PRIVATE).getString("NOTES","EMPTY");
        if (!spNotes.equals("EMPTY")){
            editText.setText(spNotes);
        }

        //set button Onclick handler
        stnButton = (Button)findViewById(R.id.btnSettings);

        stnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent settingIntent = new Intent(getApplicationContext(),SettingsActivity.class);

                startActivityForResult(settingIntent,Setting_Code);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Setting_Code){

            updateNotesEditText();
        }
    }

    //using sharedpreferrence manager to get the data

    private void updateNotesEditText(){


        //
        SharedPreferences setActivityPrefer = PreferenceManager.getDefaultSharedPreferences(this);

        //check if the bold clicked
        if (setActivityPrefer.getBoolean("pref_text_bold", false)){

            editText.setTypeface(null, Typeface.BOLD);

        }else{
            editText.setTypeface(null,Typeface.NORMAL);
        }


        String textSize = setActivityPrefer.getString("pref_text_size","25");

        float size = Float.parseFloat(textSize);

        editText.setTextSize(size);

    }
    //if android want to kill the app or user kill the app normally
    //will invoke this one
    @Override
    protected void onSaveInstanceState(Bundle outState) {

        outState.putString("NOTES",editText.getText().toString());
        super.onSaveInstanceState(outState);

    }
    //if the user force kill the app


    @Override
    protected void onStop() {

        saveSettings();
        super.onStop();
    }
    //save the key pairs into sharedpreffrences
    private void saveSettings(){

        //using sharedPreferrence to save the data.

        SharedPreferences.Editor sPEditor = getPreferences(Context.MODE_PRIVATE).edit();

        sPEditor.putString("NOTES", editText.getText().toString());

        sPEditor.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
