package com.iitp.mayank.celesta2k17.activities;

import android.os.Bundle;
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
import com.iitp.mayank.celesta2k17.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    Button buttonRegister;
    TextInputLayout firstNameWrapper;
    TextInputLayout lastNameWrapper;
    TextInputLayout collegeNameWrapper;
    TextInputLayout emailIDWrapper;
    TextInputLayout passwordWrapper;
    TextInputLayout confirmPasswordWrapper;
    TextInputLayout mobileNoWrapper;
    String mName;
    String mCollege;
    String mEmail;
    String mPassword;
    String mConfirmPassword;
    String mMobile;
    RequestQueue mQueue;
    private String mUrl;
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private Matcher matcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mUrl = getString(R.string.register_url);
        mQueue = Volley.newRequestQueue(this);
        buttonRegister = (Button) findViewById(R.id.button_register);
        firstNameWrapper = (TextInputLayout) findViewById(R.id.first_name_wrapper);
        lastNameWrapper = (TextInputLayout) findViewById(R.id.last_name_wrapper);
        collegeNameWrapper = (TextInputLayout) findViewById(R.id.college_name_wrapper);
        emailIDWrapper = (TextInputLayout) findViewById(R.id.email_id_wrapper);
        passwordWrapper = (TextInputLayout) findViewById(R.id.password_wrapper);
        confirmPasswordWrapper = (TextInputLayout) findViewById(R.id.confirm_password_wrapper);
        mobileNoWrapper = (TextInputLayout) findViewById(R.id.mobile_no_wrapper);

        setHints();
        buttonRegister.setOnClickListener(new View.OnClickListener() {
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
                                                Toast.makeText(getApplicationContext(), R.string.message_registration_success, Toast.LENGTH_LONG).show();
                                                finish();
                                                break;
                                            case 409:
                                                Toast.makeText(getApplicationContext(), R.string.message_registration_duplicate, Toast.LENGTH_LONG).show();
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
                            params.put(getString(R.string.register_param_name), mName);
                            params.put(getString(R.string.register_param_college), mCollege);
                            params.put(getString(R.string.register_param_emailid), mEmail);
                            params.put(getString(R.string.register_param_password), mPassword);
                            params.put(getString(R.string.register_param_mobile), mMobile);
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
        firstNameWrapper.setErrorEnabled(false);
        lastNameWrapper.setErrorEnabled(false);
        collegeNameWrapper.setErrorEnabled(false);
        emailIDWrapper.setErrorEnabled(false);
        passwordWrapper.setErrorEnabled(false);
        confirmPasswordWrapper.setErrorEnabled(false);
        mobileNoWrapper.setErrorEnabled(false);
    }

    private boolean validateInputs() {
        if (isAnyFieldEmpty())
            return false;
        mName = firstNameWrapper.getEditText().getText().toString() + " " + lastNameWrapper.getEditText().getText().toString();
        mCollege = collegeNameWrapper.getEditText().getText().toString();
        mEmail = emailIDWrapper.getEditText().getText().toString();
        mPassword = passwordWrapper.getEditText().getText().toString();
        mConfirmPassword = confirmPasswordWrapper.getEditText().getText().toString();
        mMobile = mobileNoWrapper.getEditText().getText().toString();

        if (!validateEmail(mEmail)) {
            emailIDWrapper.setError(getString(R.string.error_invalid_email));
            return false;
        }
        if (!validPassword(mPassword)) {
            passwordWrapper.setError(getString(R.string.invalid_password_length));
            return false;
        }
        if (!validMobile(mMobile)) {
            mobileNoWrapper.setError(getString(R.string.error_invalid_mobile_no));
            return false;
        }
        if (!matchingPassword(mPassword, mConfirmPassword)) {
            passwordWrapper.setError(getString(R.string.error_password_match));
            confirmPasswordWrapper.setError(getString(R.string.error_password_match));
            return false;
        }
        return true;
    }

    private boolean validMobile(String mMobile) {
        if (mMobile.length() != 10)
            return false;
        return true;
    }

    private boolean validPassword(String password) {
        if (password.length() > 5)
            return true;
        else
            return false;
    }

    private boolean matchingPassword(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }

    private boolean validateEmail(String email) {
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean isAnyFieldEmpty() {
        boolean flag = false;
        if (TextUtils.isEmpty(firstNameWrapper.getEditText().getText().toString())) {
            flag = true;
            firstNameWrapper.setError(getString(R.string.error_empty_field));
        }
        if (TextUtils.isEmpty(lastNameWrapper.getEditText().getText().toString())) {
            flag = true;
            lastNameWrapper.setError(getString(R.string.error_empty_field));
        }
        if (TextUtils.isEmpty(collegeNameWrapper.getEditText().getText().toString())) {
            flag = true;
            collegeNameWrapper.setError(getString(R.string.error_empty_field));
        }
        if (TextUtils.isEmpty(emailIDWrapper.getEditText().getText().toString())) {
            flag = true;
            emailIDWrapper.setError(getString(R.string.error_empty_field));
        }
        if (TextUtils.isEmpty(passwordWrapper.getEditText().getText().toString())) {
            flag = true;
            passwordWrapper.setError(getString(R.string.error_empty_field));
        }
        if (TextUtils.isEmpty(confirmPasswordWrapper.getEditText().getText().toString())) {
            flag = true;
            confirmPasswordWrapper.setError(getString(R.string.error_empty_field));
        }
        if (TextUtils.isEmpty(mobileNoWrapper.getEditText().getText().toString())) {
            flag = true;
            mobileNoWrapper.setError(getString(R.string.error_empty_field));
        }

        return flag;
    }

    private void setHints() {
        firstNameWrapper.setHint(getString(R.string.first_name_hint));
        lastNameWrapper.setHint(getString(R.string.last_name_hint));
        collegeNameWrapper.setHint(getString(R.string.college_hint));
        emailIDWrapper.setHint(getString(R.string.email_id_hint));
        passwordWrapper.setHint(getString(R.string.password_hint));
        confirmPasswordWrapper.setHint(getString(R.string.confirm_password_hint));
        mobileNoWrapper.setHint(getString(R.string.mobile_no_hint));
    }
}
