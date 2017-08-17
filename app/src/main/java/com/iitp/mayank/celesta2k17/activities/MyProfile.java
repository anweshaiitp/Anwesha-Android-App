package com.iitp.mayank.celesta2k17.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.iitp.mayank.celesta2k17.R;

/**
 * Created by mayank on 15/7/17.
 */

public class MyProfile extends AppCompatActivity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        if(!sharedPreferences.getBoolean("login_status" , true))
        {
            setContentView(R.layout.sign_in);
        }
        else
        {
            setContentView(R.layout.activity_register_signup_or_signin);
            TextView fullNameTextView = (TextView) findViewById(R.id.fullName);
            TextView nameTextView = (TextView) findViewById(R.id.nameTextView);
            TextView idTextView = (TextView) findViewById(R.id.idValue);
            TextView collegeTextView = (TextView) findViewById(R.id.collegeNameValue);
            TextView eventTextView = (TextView) findViewById(R.id.eventsParticipatedValue);

            String full_name = sharedPreferences.getString("full_name" , "Mayank Vaidya");
            fullNameTextView.setText(sharedPreferences.getString("full_name" , "Mayank Vaidya"));
            nameTextView.setText("" + Character.toUpperCase(full_name.charAt(0)) + Character.toUpperCase(full_name.charAt(full_name.indexOf(' ') + 1)));
            idTextView.setText(sharedPreferences.getString("id" , "12345"));
            collegeTextView.setText(sharedPreferences.getString("college_name" , "IIT Patna"));
            eventTextView.setText(sharedPreferences.getString("event_participated" , "NJATH"));
        }
    }
}
