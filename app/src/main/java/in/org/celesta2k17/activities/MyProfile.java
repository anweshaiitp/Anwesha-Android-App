package in.org.celesta2k17.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import in.org.celesta2k17.R;


/**
 * Created by mayank on 15/7/17.
 */

public class MyProfile extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView();
    }

    private void setView() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (!sharedPreferences.getBoolean(getString(R.string.login_status), false)) {
            setContentView(R.layout.sign_in);
            Button buttonSignIn = (Button) findViewById(R.id.button_signin);
            Button buttonSignUp = (Button) findViewById(R.id.button_signup);

            buttonSignIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MyProfile.this, SignInActivity.class);
                    startActivity(intent);
                }
            });

            buttonSignUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MyProfile.this, RegisterActivity.class);
                    startActivity(intent);
                }
            });
        } else {
            setContentView(R.layout.activity_register_signup_or_signin);
            TextView fullNameTextView = (TextView) findViewById(R.id.fullName);
            TextView nameTextView = (TextView) findViewById(R.id.nameTextView);
            TextView idTextView = (TextView) findViewById(R.id.idValue);
            TextView collegeTextView = (TextView) findViewById(R.id.collegeNameValue);
            TextView eventTextView = (TextView) findViewById(R.id.eventsParticipatedValue);

            String full_name = sharedPreferences.getString(getString(R.string.full_name), "Mayank Vaidya");

            fullNameTextView.setText(sharedPreferences.getString(getString(R.string.full_name), "Mayank Vaidya"));
            nameTextView.setText("" + Character.toUpperCase(full_name.charAt(0)) + Character.toUpperCase(full_name.charAt(full_name.indexOf(' ') + 1)));
            idTextView.setText(sharedPreferences.getString(getString(R.string.id), "12345"));
            collegeTextView.setText(sharedPreferences.getString(getString(R.string.college_name), "IIT Patna"));
            eventTextView.setText(sharedPreferences.getString(getString(R.string.event_participated), "-"));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        setView();
    }
}
