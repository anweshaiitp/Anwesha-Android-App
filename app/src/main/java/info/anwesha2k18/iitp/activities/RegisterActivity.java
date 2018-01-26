package info.anwesha2k18.iitp.activities;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import info.anwesha2k18.iitp.R;

public class RegisterActivity extends AppCompatActivity {

    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    Button buttonRegister;
    TextInputLayout firstNameWrapper;
    TextInputLayout lastNameWrapper;
    Spinner genderSpinner;
    TextInputLayout collegeNameWrapper;
    TextInputLayout cityWrapper;
    TextInputLayout birthdayWrapper;
    TextInputLayout emailIDWrapper;
    TextInputLayout passwordWrapper;
    TextInputLayout confirmPasswordWrapper;
    TextInputLayout mobileNoWrapper;
    TextInputLayout refCodeWrapper;
    String mFbID;
    String mName;
    String mGender;
    String mCollege;
    String mCity;
    String mDob;
    String mEmail;
    String mPassword;
    String mConfirmPassword;
    String mMobile;
    String mRefCode;
    SetDate setDate;
    RequestQueue mQueue;
    private String mUrl;
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private Matcher matcher;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mUrl = getString(R.string.register_url);
        mQueue = Volley.newRequestQueue(this);
        buttonRegister = findViewById(R.id.button_register);
        firstNameWrapper = findViewById(R.id.first_name_wrapper);
        lastNameWrapper = findViewById(R.id.last_name_wrapper);
        collegeNameWrapper = findViewById(R.id.college_name_wrapper);
        cityWrapper = findViewById(R.id.city_wrapper);
        birthdayWrapper = findViewById(R.id.birthday_wrapper);
        emailIDWrapper = findViewById(R.id.email_id_wrapper);
        passwordWrapper = findViewById(R.id.password_wrapper);
        confirmPasswordWrapper = findViewById(R.id.confirm_password_wrapper);
        mobileNoWrapper = findViewById(R.id.mobile_no_wrapper);
        refCodeWrapper = findViewById(R.id.ref_code_wrapper);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        genderSpinner = findViewById(R.id.gender_spinner);
        final ArrayAdapter<CharSequence> genderAdapter = ArrayAdapter.createFromResource(this,
                R.array.gender_array, R.layout.spinner_item);
        genderAdapter.setDropDownViewResource(R.layout.spinner_item);
        genderSpinner.setAdapter(genderAdapter);
        genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        setDate = new SetDate(birthdayWrapper.getEditText(), this);
        setHints();
        setFetchedFacebookData();
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearErrors();
                boolean b = validateInputs();
                if (b) {
                    Toast.makeText(getApplicationContext(), "Registering..", Toast.LENGTH_SHORT).show();
                    buttonRegister.setVisibility(View.GONE);
                    //Code for sending the details
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, mUrl,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Log.v("Response:", response);
                                    try {
//                                        JSONObject jsonObject = new JSONObject(response);
                                        JSONArray jsonArray = new JSONArray(response);
                                        int status = (int) jsonArray.get(0);

                                        switch (status) {
                                            case 1:
                                                JSONObject jsonObject = (JSONObject) jsonArray.get(1);
                                                Toast.makeText(getApplicationContext(), R.string.message_registration_success, Toast.LENGTH_LONG).show();
                                                MyProfile.setData(RegisterActivity.this, jsonObject, sharedPreferences.edit());
                                                break;
                                            case -1:
                                                String errorMessage = (String) jsonArray.get(1);
                                                Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_LONG).show();
                                                break;
                                            default:
                                                Toast.makeText(getApplicationContext(), "Error registering. Please try again later.", Toast.LENGTH_SHORT).show();
                                        }
                                        buttonRegister.setVisibility(View.VISIBLE);
                                        Intent intent = new Intent();
                                        setResult(status, intent);
                                        finish();
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
                                    Toast.makeText(getApplicationContext(), "Error registering. Please try again later", Toast.LENGTH_SHORT).show();
                                    buttonRegister.setVisibility(View.VISIBLE);
                                }
                            }
                    ) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
                            params.put(getString(R.string.register_param_fb_id), mFbID);
                            params.put(getString(R.string.register_param_name), mName);
                            params.put(getString(R.string.register_param_gender), mGender);
                            params.put(getString(R.string.register_param_city), mCity);
                            params.put(getString(R.string.register_param_college), mCollege);
                            params.put(getString(R.string.register_param_dob), mDob);
                            params.put(getString(R.string.register_param_emailid), mEmail);
                            params.put(getString(R.string.register_param_password), mPassword);
                            params.put(getString(R.string.register_param_mobile), mMobile);
                            params.put(getString(R.string.register_param_ref_code), mRefCode);
//                            params.put(getString(R.string.register_param_apiKey), getString(R.string.api_key));
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
        birthdayWrapper.setErrorEnabled(false);
        cityWrapper.setErrorEnabled(false);
        emailIDWrapper.setErrorEnabled(false);
        passwordWrapper.setErrorEnabled(false);
        confirmPasswordWrapper.setErrorEnabled(false);
        mobileNoWrapper.setErrorEnabled(false);
        refCodeWrapper.setErrorEnabled(false);
    }

    private boolean validateInputs() {
        if (isAnyFieldEmpty())
            return false;
        mName = firstNameWrapper.getEditText().getText().toString() + " " + lastNameWrapper.getEditText().getText().toString();
        mGender = (genderSpinner.getSelectedItemPosition() == 0) ? "M" : "F";
        mCollege = collegeNameWrapper.getEditText().getText().toString();
        mCity = cityWrapper.getEditText().getText().toString();
        mDob = setDate.getDateStr();
        mEmail = emailIDWrapper.getEditText().getText().toString();
        mPassword = passwordWrapper.getEditText().getText().toString();
        mConfirmPassword = confirmPasswordWrapper.getEditText().getText().toString();
        mMobile = mobileNoWrapper.getEditText().getText().toString();
        mRefCode = refCodeWrapper.getEditText().getText().toString();

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
        if (!validRefCode(mRefCode)) {
            refCodeWrapper.setError("ID has to be 4 digits long.");
            return false;
        }

        return true;
    }

    private boolean validRefCode(String mRefCode) {
        return !(!TextUtils.isEmpty(mRefCode) && mRefCode.length() != 4);
    }

    private boolean validMobile(String mMobile) {
        return mMobile.length() == 10;
    }

    private boolean validPassword(String password) {
        return password.length() > 5;
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
        if (TextUtils.isEmpty(birthdayWrapper.getEditText().getText().toString())) {
            flag = true;
            birthdayWrapper.setError(getString(R.string.error_empty_field));
        }
        if (TextUtils.isEmpty(cityWrapper.getEditText().getText().toString())) {
            flag = true;
            cityWrapper.setError(getString(R.string.error_empty_field));
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
        birthdayWrapper.setHint(getString(R.string.Birthday));
        emailIDWrapper.setHint(getString(R.string.email_id_hint));
        passwordWrapper.setHint(getString(R.string.password_hint));
        confirmPasswordWrapper.setHint(getString(R.string.confirm_password_hint));
        mobileNoWrapper.setHint(getString(R.string.mobile_no_hint));
        refCodeWrapper.setHint(getString(R.string.referral_code_hint));
    }

    private void setFetchedFacebookData() {
        mFbID = sharedPreferences.getString(getString(R.string.facebook_id), "0");
        firstNameWrapper.getEditText().setText(sharedPreferences.getString(getString(R.string.first_name), ""));
        lastNameWrapper.getEditText().setText(sharedPreferences.getString(getString(R.string.last_name), ""));
        if (sharedPreferences.getString(getString(R.string.gender), "").equals("female")) {
            genderSpinner.setSelection(1);
        } else {
            genderSpinner.setSelection(0);
        }
        emailIDWrapper.getEditText().setText(sharedPreferences.getString(getString(R.string.email), ""));
        birthdayWrapper.getEditText().setText(sharedPreferences.getString(getString(R.string.dateOfBirth), ""));
    }
}

class SetDate implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    private EditText editText;
    private Calendar myCalendar;
    private Context mContext;
    private String dateStr;

    public SetDate(EditText editText, Context ctx) {
        this.editText = editText;
        this.editText.setOnClickListener(this);
        myCalendar = Calendar.getInstance();
        mContext = ctx;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        // this.editText.setText();

        SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
        myCalendar.set(Calendar.YEAR, year);
        myCalendar.set(Calendar.MONTH, monthOfYear);
        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        dateStr = sdformat.format(myCalendar.getTime());
        editText.setText(dateStr);

    }

    public String getDateStr() {
        return dateStr;
    }

    @Override
    public void onClick(View view) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(mContext,
                R.style.DatePickerTheme,
                this,
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH));

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -10);
        datePickerDialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
        datePickerDialog.show();
    }
}