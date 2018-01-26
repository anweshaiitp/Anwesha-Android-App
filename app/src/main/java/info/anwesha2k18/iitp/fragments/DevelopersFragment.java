package info.anwesha2k18.iitp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import info.anwesha2k18.iitp.R;

/**
 * Created by Muks14x on 1/26/18.
 */

public class DevelopersFragment extends Fragment {

    public DevelopersFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_developers, container, false);
    }

}
