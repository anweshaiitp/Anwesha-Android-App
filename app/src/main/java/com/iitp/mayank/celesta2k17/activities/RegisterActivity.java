package com.iitp.mayank.celesta2k17.activities;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.iitp.mayank.celesta2k17.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    Button buttonRegister;
    TextInputLayout firstNameWrapper;
    TextInputLayout lastNameWrapper;
    TextInputLayout collegeNameWrapper;
    TextInputLayout emailIDWrapper;
    TextInputLayout passwordWrapper;
    TextInputLayout confirmPasswordWrapper;

    String mName;
    String mCollege;
    String mEmail;
    String mPassword;
    String mConfirmPassword;

    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private Matcher matcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        buttonRegister = (Button) findViewById(R.id.button_register);
        firstNameWrapper = (TextInputLayout) findViewById(R.id.first_name_wrapper);
        lastNameWrapper = (TextInputLayout) findViewById(R.id.last_name_wrapper);
        collegeNameWrapper = (TextInputLayout) findViewById(R.id.college_name_wrapper);
        emailIDWrapper = (TextInputLayout) findViewById(R.id.email_id_wrapper);
        passwordWrapper = (TextInputLayout) findViewById(R.id.password_wrapper);
        confirmPasswordWrapper = (TextInputLayout) findViewById(R.id.confirm_password_wrapper);

        setHints();
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearErrors();
                boolean b = validateInputs();
                if(b)
                {
                    //Code for sending the details
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
    }

    private boolean validateInputs() {
        if(isAnyFieldEmpty())
            return false;
        mName = firstNameWrapper.getEditText().getText().toString() + " " + lastNameWrapper.getEditText().getText().toString();
        mCollege = collegeNameWrapper.getEditText().getText().toString();
        mEmail = emailIDWrapper.getEditText().getText().toString();
        mPassword = passwordWrapper.getEditText().getText().toString();
        mConfirmPassword = confirmPasswordWrapper.getEditText().getText().toString();

        if(!validateEmail(mEmail)) {
            emailIDWrapper.setError("Invalid Email!");
            return false;
        }
        if(!validPassword(mPassword))
        {
            passwordWrapper.setError("Length should be more than 5 characters");
            return false;
        }
        if(!matchingPassword(mPassword , mConfirmPassword))
        {
            passwordWrapper.setError("Passwords dont match");
            confirmPasswordWrapper.setError("Passwords dont match");
            return false;
        }
        return true;
    }

    private boolean validPassword(String password) {
        if(password.length() > 5)
            return true;
        else
            return false;
    }

    private boolean matchingPassword(String password , String confirmPassword) {
        return password.equals(confirmPassword);
    }

    private boolean validateEmail(String email) {
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean isAnyFieldEmpty() {
        boolean flag = false;
        if(TextUtils.isEmpty(firstNameWrapper.getEditText().getText().toString()))
        {
            flag = true;
            firstNameWrapper.setError("Empty Field");
        }
        if(TextUtils.isEmpty(lastNameWrapper.getEditText().getText().toString()))
        {
            flag = true;
            lastNameWrapper.setError("Empty field");
        }
        if(TextUtils.isEmpty(collegeNameWrapper.getEditText().getText().toString()))
        {
            flag = true;
            collegeNameWrapper.setError("Empty field");
        }
        if(TextUtils.isEmpty(emailIDWrapper.getEditText().getText().toString()))
        {
            flag = true;
            emailIDWrapper.setError("Empty field");
        }
        if(TextUtils.isEmpty(passwordWrapper.getEditText().getText().toString()))
        {
            flag = true;
            passwordWrapper.setError("Empty field");
        }
        if(TextUtils.isEmpty(confirmPasswordWrapper.getEditText().getText().toString()))
        {
            flag = true;
            confirmPasswordWrapper.setError("Empty field");
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
    }
}
