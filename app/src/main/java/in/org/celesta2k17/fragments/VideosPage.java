package in.org.celesta2k17.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.org.celesta2k17.R;


/**
 * Created by mayank on 26/5/17.
 */

public class VideosPage extends android.support.v4.app.Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_videos, container, false);
        return rootView;
    }
}
