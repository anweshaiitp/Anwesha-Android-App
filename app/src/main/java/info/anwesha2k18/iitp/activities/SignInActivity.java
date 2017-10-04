package info.anwesha2k18.iitp.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import info.anwesha2k18.iitp.R;

public class SignInActivity extends AppCompatActivity {

    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    Button buttonSignIn;
    TextInputLayout emailIDWrapper;
    TextInputLayout passwordWrapper;
    String mEmail;
    String mPassword;
    RequestQueue mQueue;
    SharedPreferences.Editor sharedPreferences;
    private String mUrl;
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private Matcher matcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mQueue = Volley.newRequestQueue(this);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this).edit();
        mUrl = "https://celesta.tameesh.info/login";
        buttonSignIn = (Button) findViewById(R.id.button_signin);
        emailIDWrapper = (TextInputLayout) findViewById(R.id.eamil_id_wrapper_signin);
        passwordWrapper = (TextInputLayout) findViewById(R.id.password_wrapper_signin);

        setHints();
        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearErrors();
                boolean b = validateInputs();
                if (b) {
                    //Code for sending the details
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, mUrl,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Log.v("Response:", response);
                                    try {
                                        JSONObject jsonObject = new JSONObject(response);
                                        int status = Integer.parseInt(jsonObject.getString(getString(R.string.JSON_status)));

                                        switch (status) {
                                            case 200:
                                                Toast.makeText(getApplicationContext(), "Log In Successful", Toast.LENGTH_LONG).show();
                                                int userID = Integer.parseInt(jsonObject.getString("userID"));
                                                String name = jsonObject.getString("name");
                                                String college = jsonObject.getString("college");
                                                String events = jsonObject.getString("events");
                                                sharedPreferences.putBoolean(getString(R.string.login_status), true);
                                                sharedPreferences.putString(getString(R.string.full_name), name);
                                                sharedPreferences.putString(getString(R.string.id), userID + "");
                                                sharedPreferences.putString(getString(R.string.college_name), college);
//                                                sharedPreferences.putString(getString(R.string.event_participated) , events);
                                                sharedPreferences.apply();
                                                finish();
                                                break;
                                            case 409:
                                                Toast.makeText(getApplicationContext(), R.string.message_registration_duplicate, Toast.LENGTH_LONG).show();
                                                finish();
                                                break;
                                            case 403:
                                                Toast.makeText(getApplicationContext(), "Invalid Login", Toast.LENGTH_LONG).show();
                                                finish();
                                                break;
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Log.v("Error : ", error.toString());
                                    error.printStackTrace();
                                }
                            }
                    ) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
                            params.put(getString(R.string.register_param_emailid), mEmail);
                            params.put(getString(R.string.register_param_password), mPassword);
                            params.put(getString(R.string.register_param_apiKey), getString(R.string.api_key));

                            return params;
                        }

                        @Override
                        public Map<String, String> getHeaders() throws AuthFailureError {
                            Map<String, String> headers = new HashMap<>();
                            headers.put("Accept", "application/json");
                            return headers;
                        }
                    };
                    mQueue.add(stringRequest);
                }
            }
        });
    }

    private void clearErrors() {
        emailIDWrapper.setErrorEnabled(false);
        passwordWrapper.setErrorEnabled(false);
    }

    private boolean validateInputs() {
        if (isAnyFieldEmpty())
            return false;

        mEmail = emailIDWrapper.getEditText().getText().toString();
        mPassword = passwordWrapper.getEditText().getText().toString();

        return true;
    }

    private boolean isAnyFieldEmpty() {
        boolean flag = false;
        if (TextUtils.isEmpty(emailIDWrapper.getEditText().getText().toString())) {
            flag = true;
            emailIDWrapper.setError(getString(R.string.error_empty_field));
        }
        if (TextUtils.isEmpty(passwordWrapper.getEditText().getText().toString())) {
            flag = true;
            passwordWrapper.setError(getString(R.string.error_empty_field));
        }
        return flag;
    }

    private void setHints() {
        emailIDWrapper.setHint(getString(R.string.email_id_hint));
        passwordWrapper.setHint(getString(R.string.password_hint));
    }
}
