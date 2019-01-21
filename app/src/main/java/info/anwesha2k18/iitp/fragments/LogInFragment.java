package info.anwesha2k18.iitp.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import info.anwesha2k18.iitp.R;

import info.anwesha2k18.iitp.OnLoginListener;
import info.anwesha2k18.iitp.activities.MyProfile;
import info.anwesha2k18.iitp.activities.SignInActivity;

public class LogInFragment extends Fragment implements OnLoginListener {
    private static final String TAG = "LoginFragment";
    private View root_view ;
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    Button buttonSignIn;
    EditText emailIDWrapper;
    EditText passwordWrapper;
    String mEmail;
    String mPassword;
    RequestQueue mQueue;
    SharedPreferences.Editor sharedPreferenceEditor;
    private String mUrl;
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private Matcher matcher;

    public LogInFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root_view = inflater.inflate(R.layout.login_fragment, container, false);
        mUrl = getString(R.string.login_api_url);
        emailIDWrapper = root_view.findViewById(R.id.email_id_signin);
        passwordWrapper = root_view.findViewById(R.id.password_signin);
        mQueue = Volley.newRequestQueue(getContext());
        sharedPreferenceEditor = PreferenceManager.getDefaultSharedPreferences(getContext()).edit();
        setHints();
        return root_view;
    }

    @Override
    public void login() {
        clearErrors();
        boolean b = validateInputs();
        if (b) {
            Toast.makeText(getContext(), "Signing In...", Toast.LENGTH_SHORT).show();
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
                                        Toast.makeText(getContext(), "Log In Successful", Toast.LENGTH_LONG).show();
                                        JSONArray jsonArray = jsonObject.getJSONArray("event");
                                        Set<String> eventSet = new HashSet<>();
                                        for (int i = 0; i < jsonArray.length(); i++) {
                                            eventSet.add(jsonArray.getString(i));
                                        }
                                        sharedPreferenceEditor.putStringSet(getString(R.string.events_list), eventSet);
                                        sharedPreferenceEditor.putString(getString(R.string.key), jsonObject.getString("key"));
                                        sharedPreferenceEditor.apply();
                                        MyProfile.setData(getContext(), jsonObject.getJSONObject("user"), sharedPreferenceEditor);
                                        getActivity().finish();
                                        break;
                                    case 400:
                                    case 403:
                                        Toast.makeText(getContext(), "Invalid Login", Toast.LENGTH_LONG).show();
                                        break;
                                    default:
                                        Toast.makeText(getContext(), R.string.login_error, Toast.LENGTH_LONG).show();
                                }

                            } catch (JSONException e) {

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
                    params.put(getString(R.string.sign_in_param_username), mEmail);
                    params.put(getString(R.string.register_param_password), mPassword);
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

    private void setHints() {
        emailIDWrapper.setHint("Anwesha ID (ANWXXXX)");
        passwordWrapper.setHint(getString(R.string.password_hint));
    }

    private void clearErrors() {
        emailIDWrapper.setError("");
        passwordWrapper.setError("");
    }
    private boolean validateInputs() {
        if (isAnyFieldEmpty())
            return false;

        mEmail = emailIDWrapper.getText().toString();
        mPassword = passwordWrapper.getText().toString();

        return true;
    }
    private boolean isAnyFieldEmpty() {
        boolean flag = false;
        if (TextUtils.isEmpty(emailIDWrapper.getText().toString())) {
            flag = true;
            emailIDWrapper.setError(getString(R.string.error_empty_field));
        }
        if (TextUtils.isEmpty(passwordWrapper.getText().toString())) {
            flag = true;
            passwordWrapper.setError(getString(R.string.error_empty_field));
        }
        return flag;
    }
}
