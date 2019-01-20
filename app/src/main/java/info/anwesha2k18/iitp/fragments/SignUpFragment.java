package info.anwesha2k18.iitp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;


import info.anwesha2k18.iitp.OnSignUpListener;
import info.anwesha2k18.iitp.R;
import info.anwesha2k18.iitp.activities.RegisterActivity;


public class SignUpFragment extends Fragment implements OnSignUpListener {
    private static final String TAG = "SignUpFragment";
    private View root_view ;
    public SignUpFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root_view = inflater.inflate(R.layout.sign_up_fragment, container, false);
        return root_view;
    }

    @Override
    public void signUp() {
        EditText email_view = root_view.findViewById(R.id.sign_up_email) ;
        EditText password = root_view.findViewById(R.id.sign_up_password);
        EditText re_password = root_view.findViewById(R.id.sign_up_password_re);
        Intent intent = new Intent(getContext(), RegisterActivity.class);
        intent.putExtra("user_email", email_view.getText().toString().trim());
        intent.putExtra("user_pass", password.getText().toString().trim());
        intent.putExtra("user_pass_re", re_password.getText().toString().trim());
        startActivity(intent);
    }
}
