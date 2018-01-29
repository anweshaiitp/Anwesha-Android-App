package info.anwesha2k18.iitp.activities;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import info.anwesha2k18.iitp.R;


/**
 * Created by mayank on 15/7/17.
 */

//https://stackoverflow.com/questions/14542193/how-to-get-facebook-photo-full-name-gender-using-facebook-sdk-android

public class MyProfile extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    CallbackManager callbackManager;
    private AccessTokenTracker fbTracker;
    SharedPreferences.Editor shareEdit;
    RequestQueue mQueue;
    private boolean checkRegistered;
    private JSONObject jsonObject;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mQueue = Volley.newRequestQueue(this);
        setView();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 10) {
            switch (resultCode) {
                case 200:
                    setView();
                    break;
                default:
                    finish();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
    }

    private void setView() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        if (!sharedPreferences.getBoolean(getString(R.string.login_status), false)) {
            SharedPreferences.Editor spEdit = sharedPreferences.edit();
            spEdit.putBoolean(getString(R.string.is_qr_downloaded), false).apply();

            setContentView(R.layout.sign_in);
            shareEdit = sharedPreferences.edit();
            Button buttonSignIn = findViewById(R.id.button_signin);
            Button buttonSignUp = findViewById(R.id.button_signup);
            callbackManager = CallbackManager.Factory.create();
            LoginButton loginButton = findViewById(R.id.login_button);
            loginButton.setReadPermissions(Arrays.asList("email"));

            fbTracker = new AccessTokenTracker() {
                @Override
                protected void onCurrentAccessTokenChanged(AccessToken accessToken, AccessToken accessToken2) {
                    if (accessToken2 != null) {
                        shareEdit.putBoolean(getString(R.string.facebook_logged_in), true).apply();
                    } else {
                        shareEdit.putBoolean(getString(R.string.facebook_logged_in), false).apply();
                        clearSharedPreferences(shareEdit);
                    }
                }
            };
            fbTracker.startTracking();
            loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {
                    Log.v("FACEBOOK : ", "SUCCESS!!");
                    setFacebookData(loginResult);
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
            TextView idTextView = (TextView) findViewById(R.id.idValue);
            TextView collegeTextView = (TextView) findViewById(R.id.collegeNameValue);
            TextView eventTextView = (TextView) findViewById(R.id.eventsParticipatedValue);

            fullNameTextView.setText(sharedPreferences.getString(getString(R.string.full_name), "Mayank Vaidya"));
            idTextView.setText(sharedPreferences.getString(getString(R.string.anwesha_id), "12345"));
            collegeTextView.setText(sharedPreferences.getString(getString(R.string.college), "IIT Patna"));
            StringBuilder stringBuilder = new StringBuilder();
            Set<String> eventsP = sharedPreferences.getStringSet(getString(R.string.events_list), null);
            if (eventsP == null)
                stringBuilder.append("-");
            else
                for (String s : eventsP) {
                    stringBuilder.append(s + ", ");
                }
            eventTextView.setText(stringBuilder);
            if (sharedPreferences.getBoolean(getString(R.string.is_qr_downloaded), false)) {
                showQRCode();
            } else {
                hideQRCode();
                downloadQR(this, sharedPreferences.getString(getApplicationContext().getString(R.string.qr_url), ""));
            }
        }
    }

    private void hideQRCode() {
        ImageView qrImage = findViewById(R.id.image_view_qr_code);
        ProgressBar progressBar = findViewById(R.id.pb_qr);
        progressBar.setVisibility(View.VISIBLE);
        qrImage.setVisibility(View.GONE);
    }

    private void showQRCode() {
        ImageView qrImage = findViewById(R.id.image_view_qr_code);
        ProgressBar progressBar = findViewById(R.id.pb_qr);
        if (progressBar != null)
            progressBar.setVisibility(View.GONE);
        qrImage.setVisibility(View.VISIBLE);
        qrImage.setImageBitmap(getQRBitmap());
    }

    private Bitmap getQRBitmap() {
        ContextWrapper contextWrapper = new ContextWrapper(getApplicationContext());
        File folder = contextWrapper.getDir(getString(R.string.qr_code_folder_name), Context.MODE_PRIVATE);

        File image = new File(folder, "qr_code.png");
        try {
            return BitmapFactory.decodeStream(new FileInputStream(image));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void clearSharedPreferences(SharedPreferences.Editor shareEdit) {
        shareEdit.putString(getString(R.string.facebook_id), "");
        shareEdit.putString(getString(R.string.first_name), "");
        shareEdit.putString(getString(R.string.last_name), "");
        shareEdit.putString(getString(R.string.full_name), "");
        shareEdit.putString(getString(R.string.email), "");
        shareEdit.putString(getString(R.string.gender), "");
        shareEdit.putString(getString(R.string.dateOfBirth), "");
        shareEdit.apply();
    }


    private void checkRegistered() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String checkUrl = "https://anwesha.info/user/CAcheck/" + sharedPreferences.getString(getString(R.string.facebook_id), "1");
        Log.v("CHEK URL : ", checkUrl);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, checkUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.v("RESPONSE : ", response);
                try {
                    jsonObject = new JSONObject(response);
                    int status = jsonObject.getInt("0");
                    Log.v("STATUS : ", String.valueOf(status));
                    if (status == 1) {
                        checkRegistered = true;
                        clearSharedPreferences(shareEdit);
                        setData(MyProfile.this, jsonObject.getJSONObject("1"), shareEdit);
                        Toast.makeText(getApplicationContext(), R.string.log_in_successful, Toast.LENGTH_LONG).show();
//                        finish();
                    } else if (status == -1) {
                        checkRegistered = false;
                        Intent intent = new Intent(MyProfile.this, RegisterActivity.class);
                        startActivityForResult(intent, 10);
                        finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.v("VOLLEY ERROR", error.toString());
                    }

                });

        mQueue.add(stringRequest);
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
                            Log.i("Response : ", response.toString());
                            JSONObject responseJSON = response.getJSONObject();

                            String firstName, lastName, email, gender, dob;

                            firstName = filterJSON(responseJSON, "first_name");
                            lastName = filterJSON(responseJSON, "last_name");
                            email = filterJSON(responseJSON, "email");
                            gender = filterJSON(responseJSON, "gender");
                            dob = filterJSON(responseJSON, "birthday");

                            Profile profile = Profile.getCurrentProfile();
                            String id = profile.getId();
                            String link = profile.getLinkUri().toString();
                            Uri profilePic = profile.getProfilePictureUri(200, 200);
                            Log.i("Link", link);
                            if (Profile.getCurrentProfile() != null) {
                                Log.i("Login", "ProfilePic" + Profile.getCurrentProfile().getProfilePictureUri(200, 200));
                            }

                            Log.v("Login Email: ", email);
                            Log.i("Login : " + "Name ", firstName + " ::: " + lastName);
                            Log.i("Login : " + "Gender ", gender);

                            shareEdit.putString(getString(R.string.facebook_id), id);
                            shareEdit.putString(getString(R.string.first_name), firstName);
                            shareEdit.putString(getString(R.string.last_name), lastName);
                            shareEdit.putString(getString(R.string.full_name), firstName + " " + lastName);
                            shareEdit.putString(getString(R.string.email), email);
                            shareEdit.putString(getString(R.string.gender), gender);
                            shareEdit.putString(getString(R.string.dateOfBirth), parseDate(dob));
                            shareEdit.apply();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        checkRegistered();
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,email,first_name,last_name,gender,birthday");
        request.setParameters(parameters);
        request.executeAsync();
    }

    public static void setData(Context context, JSONObject userJson, SharedPreferences.Editor shareEdit) {
        if (userJson != null) {
            try {
                shareEdit.putString(context.getString(R.string.anwesha_id), userJson.getString("pId"));
                shareEdit.putString(context.getString(R.string.full_name), userJson.getString("name"));
                shareEdit.putString(context.getString(R.string.college), userJson.getString("college"));
                shareEdit.putString(context.getString(R.string.mobile), userJson.getString("mobile"));
                shareEdit.putString(context.getString(R.string.city), userJson.getString("city"));
                shareEdit.putString(context.getString(R.string.ref_code), userJson.getString("refcode"));
                shareEdit.putString(context.getString(R.string.fee_paid), userJson.getString("feePaid"));
                shareEdit.putString(context.getString(R.string.qr_url), userJson.getString("qrurl"));
                downloadQR(context, userJson.getString("qrurl"));
                shareEdit.putString(context.getString(R.string.email), userJson.getString("email"));
                shareEdit.putString(context.getString(R.string.gender), userJson.getString("sex"));
                shareEdit.putString(context.getString(R.string.dateOfBirth), parseDate(userJson.getString("dob")));
                shareEdit.putBoolean(context.getString(R.string.login_status), true);
                try {
                    JSONArray jsonArray = userJson.getJSONArray("event");
                    Set<String> eventSet = new HashSet<>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        eventSet.add(jsonArray.getString(i));
                    }
                    shareEdit.putStringSet(context.getString(R.string.events_list), eventSet);
                    shareEdit.putString(context.getString(R.string.key), userJson.getString("key"));
                } catch (Exception ignored) {

                }
                shareEdit.apply();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private static void downloadQR(final Context context, String qrurl) {
        Log.e("muks", "qrurl = " + qrurl);
        if (qrurl != null) {
            Glide.with(context)
                    .asBitmap()
                    .load(qrurl)
                    .into(new SimpleTarget<Bitmap>(500, 500) {

                        @Override
                        public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition) {
                            ContextWrapper contextWrapper = new ContextWrapper(context.getApplicationContext());
                            File folder = contextWrapper.getDir(context.getString(R.string.qr_code_folder_name), Context.MODE_PRIVATE);
                            File file = new File(folder, context.getString(R.string.qr_code_file_name));

                            try {
                                FileOutputStream fos = new FileOutputStream(file);
                                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
                                Log.v("QR : ", "QR saved successfully");
                                SharedPreferences.Editor spEdit = PreferenceManager.getDefaultSharedPreferences(context).edit();
                                spEdit.putBoolean(context.getString(R.string.is_qr_downloaded), true);
                                spEdit.apply();
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                        }
                    });
        }
    }

    static private String parseDate(String dateStr) {
        Date date = null;
        try {
            date = (Date) (new SimpleDateFormat("MM-dd-yyyy")).parse(dateStr);
        } catch (ParseException e) {
            return "";
        }
        return (new SimpleDateFormat("yyyy-MM-dd")).format(date);
    }

    private String filterJSON(JSONObject responseJSON, String key) throws JSONException {
        if (responseJSON.has(key))
            return responseJSON.getString(key);
        else
            return "";
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(getString(R.string.is_qr_downloaded))) {
            if (sharedPreferences.getBoolean(getString(R.string.is_qr_downloaded), false)) {
                setView();
            }
        }
    }
}
