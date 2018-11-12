package info.anwesha2k18.iitp.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import info.anwesha2k18.iitp.OnSignUpListener;
import info.anwesha2k18.iitp.R;

public class SignUpFragment extends Fragment implements OnSignUpListener {
    private static final String TAG = "SignUpFragment";
    public SignUpFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.sign_up_fragment, container, false);


        return inflate;
    }

    @Override
    public void signUp() {
        Toast.makeText(getContext(), "Sign up", Toast.LENGTH_SHORT).show();
    }
}
