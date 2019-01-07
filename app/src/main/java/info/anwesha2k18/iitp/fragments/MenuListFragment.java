
package info.anwesha2k18.iitp.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mxn.soul.flowingdrawer_core.FlowingDrawer;

import java.util.Objects;

import info.anwesha2k18.iitp.R;
import info.anwesha2k18.iitp.activities.AboutActivity;
import info.anwesha2k18.iitp.activities.EventsActivityNew;
import info.anwesha2k18.iitp.activities.FaqActivity;
import info.anwesha2k18.iitp.activities.LectureActivity;
import info.anwesha2k18.iitp.activities.LoginActivity;
import info.anwesha2k18.iitp.activities.MainActivity;
import info.anwesha2k18.iitp.activities.MyProfile;
import info.anwesha2k18.iitp.activities.SponsorsActivity;
import info.anwesha2k18.iitp.activities.TimelineActivity;
import info.anwesha2k18.iitp.activities.multiCityActivity;
import info.anwesha2k18.iitp.activities.webActivity;

import info.anwesha2k18.iitp.activities.WorkshopActivity;

/**
 * Created by mxn on 2016/12/13.
 * MenuListFragment
 */

public class MenuListFragment extends Fragment {

    SharedPreferences sharedPreferences;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_floating_menu, container,
                false);
        NavigationView vNavigation = (NavigationView) view.findViewById(R.id.nav_view);
        vNavigation.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.menu_item_sponsors:
                    Intent intent = new Intent(getContext(), SponsorsActivity.class);
                    startActivity(intent);
                    break;
                case R.id.menu_item_faq:
                    startActivity(new Intent(getContext(), FaqActivity.class));
//                    Toast.makeText(MainActivity.this, "Coming Soon!", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.menu_item_about:
                    startActivity(new Intent(getContext(), AboutActivity.class));
                    break;
                case R.id.menu_item_profile:
                    if (!sharedPreferences.getBoolean(getString(R.string.login_status), false)) {
                        Intent intentLogin = new Intent(getContext(), LoginActivity.class);
                        startActivity(intentLogin);
                    } else {
                        Intent intentLogin = new Intent(getContext(), MyProfile.class);
                        startActivity(intentLogin);
                    }
                    break;
//                case R.id.menu_item_logout:
//                    SharedPreferences.Editor sharedPreferencesLogout = PreferenceManager.getDefaultSharedPreferences(this).edit();
//                    sharedPreferencesLogout.putBoolean(getString(R.string.login_status), false);
//                    sharedPreferencesLogout.apply();
//                    refreshMenu();
//                    Toast.makeText(this, "Logged Out Successfully", Toast.LENGTH_SHORT).show();
//                    break;
                case R.id.menu_item_schedule:
                    startActivity(new Intent(getContext(), TimelineActivity.class));
//                    Toast.makeText(MainActivity.this, "Coming Soon!", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.menu_item_lectures:
//                    Toast.makeText(getContext(), "Coming Soon!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getContext(), LectureActivity.class));
                    break;
                case R.id.menu_item_workshops:

                    startActivity(new Intent(getContext(), WorkshopActivity.class));

                    break;
                case R.id.menu_item_exhibitions:
                    Toast.makeText(getContext(), "Coming Soon!", Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(MainActivity.this, ExpoEvents.class));
                    break;
                case R.id.menu_item_ca:
                    Intent intentweb = new Intent(getContext(), webActivity.class);
                    intentweb.putExtra("link", "https://beta.anwesha.info");
                    startActivity(intentweb);
                    break;
                case R.id.menu_item_multicity:
                    startActivity(new Intent(getContext(), multiCityActivity.class));
                    break;
                case R.id.menu_item_movie:
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=tare6Drw0Ng&t=91s")));
                    break;
                case R.id.menu_item_team:
                    Toast.makeText(getContext(), "Coming Soon!", Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(MainActivity.this, TeamActivity.class));
//                  startActivity(new Intent(MainActivity.this, TeamActivity.class));
                    break;
                case R.id.menu_item_events:
                    startActivity(new Intent(getContext(), EventsActivityNew.class));
                    break;
                case R.id.menu_item_map:
                    String uri = "https://www.google.com/maps/d/viewer?mid=1Tub6_KM_0Tv8UHkh97SP9Tehv78HBv1e&usp=sharingax&basemap=satellite";
                    Intent intentMap = new Intent(Intent.ACTION_VIEW, android.net.Uri.parse(uri));
                    Objects.requireNonNull(getContext()).startActivity(intentMap);
                    break;
            }
            item.setChecked(true);

            FlowingDrawer drawer = (FlowingDrawer) getActivity().findViewById(R.id.drawerlayout);
            drawer.closeMenu();
            if(!getActivity().getClass().equals(MainActivity.class)) {
                getActivity().finish();
            }
            return true;

        });
        return view;
    }

}

