package info.anwesha2k18.iitp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Array;
import java.util.Arrays;

import info.anwesha2k18.iitp.R;


/**
 * Created by mayank on 15/7/17.
 */

//https://stackoverflow.com/questions/14542193/how-to-get-facebook-photo-full-name-gender-using-facebook-sdk-android

public class MyProfile extends AppCompatActivity {

    CallbackManager callbackManager;
    SharedPreferences.Editor shareEdit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void setView() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (!sharedPreferences.getBoolean(getString(R.string.login_status), false)) {
            setContentView(R.layout.sign_in);
            shareEdit = sharedPreferences.edit();
            Button buttonSignIn = (Button) findViewById(R.id.button_signin);
            Button buttonSignUp = (Button) findViewById(R.id.button_signup);
            callbackManager = CallbackManager.Factory.create();
            LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
            loginButton.setReadPermissions(Arrays.asList("email"));
            loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {
                    Log.v("FACEBOOK : ", "SUCCESS??");
                    setFacebookData(loginResult);
                    checkLoggedIn();
                }

                @Override
                public void onCancel() {
                    Log.v("FACEBOOK : ", "CANCEL????? :(");
                }

                @Override
                public void onError(FacebookException error) {
                    Log.v("FACEBOOK12 : ", error.toString());
                }
            });


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

    private void checkLoggedIn() {
    }

    @Override
    protected void onResume() {
        super.onResume();
        setView();
    }

    public void setFacebookData(LoginResult loginResult) {

        GraphRequest request = GraphRequest.newMeRequest(
                loginResult.getAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        // Application code
                        try {
                            Log.i("Response : ",response.toString());
                            JSONObject responseJSON = response.getJSONObject();

                            String name,email,gender,dob;

                            name = filterJSON(responseJSON, "name");
                            email = filterJSON(responseJSON, "email");
                            gender = filterJSON(responseJSON, "gender");
                            dob = filterJSON(responseJSON, "birthday");

                            Profile profile = Profile.getCurrentProfile();
                            String id = profile.getId();
                            String link = profile.getLinkUri().toString();
                            Uri profilePic = profile.getProfilePictureUri(200, 200);
                            Log.i("Link",link);
                            if (Profile.getCurrentProfile()!=null)
                            {
                                Log.i("Login", "ProfilePic" + Profile.getCurrentProfile().getProfilePictureUri(200, 200));
                            }

                            Log.v("Login Email: ", email);
                            Log.i("Login : "+ "Name", name);
                            Log.i("Login : " + "Gender", gender);

                            shareEdit.putString(getString(R.string.facebook_id), id);
                            shareEdit.putString(getString(R.string.full_name), name);
                            shareEdit.putString(getString(R.string.email), email);
                            shareEdit.putString(getString(R.string.gender), gender);
                            shareEdit.putString(getString(R.string.dateOfBirth), dob);
                            shareEdit.apply();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,email,name,gender,birthday");
        request.setParameters(parameters);
        request.executeAsync();

    }

    private String filterJSON(JSONObject responseJSON, String key) throws JSONException {
        if(responseJSON.has(key))
            return responseJSON.getString(key);
        else
            return "";
    }
}
