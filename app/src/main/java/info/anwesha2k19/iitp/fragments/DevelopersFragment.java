package info.anwesha2k19.iitp.fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import info.anwesha2k19.iitp.R;

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
        View rootView = inflater.inflate(R.layout.fragment_developers, container, false);

        ImageView github_vatsal = (ImageView) rootView.findViewById(R.id.github_vatsal);
        ImageView github_manish = (ImageView) rootView.findViewById(R.id.github_manish);
        ImageView fb_vatsal = (ImageView) rootView.findViewById(R.id.fb_vatsal);
        ImageView fb_manish = (ImageView) rootView.findViewById(R.id.fb_manish);

        String urls[] = {
                "https://github.com/ivary43",
                "https://github.com/vatsalsin",
                "https://www.facebook.com/profile.php?id=100009684360848",
                "https://www.facebook.com/vatsalsinghal1"
        };

        github_vatsal.setOnClickListener(new devOnClickListener(urls[1]));
        github_manish.setOnClickListener(new devOnClickListener(urls[0]));
        fb_vatsal.setOnClickListener(new devOnClickListener(urls[3]));
        fb_manish.setOnClickListener(new devOnClickListener(urls[2]));

        return rootView;
    }

    public class devOnClickListener implements View.OnClickListener {

        String mUrl;

        public devOnClickListener(String url) {
            super();
            mUrl = url;
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(mUrl));
            startActivity(intent);
        }
    }

}
