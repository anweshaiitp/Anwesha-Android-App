package info.anwesha2k18.iitp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import info.anwesha2k18.iitp.R;
import info.anwesha2k18.iitp.activities.AboutActivity;
import info.anwesha2k18.iitp.activities.EventsActivityNew;
import info.anwesha2k18.iitp.activities.MapActivity;
import info.anwesha2k18.iitp.activities.MyProfile;
import info.anwesha2k18.iitp.activities.SponsorsActivity;
import info.anwesha2k18.iitp.activities.TeamActivity;
import info.anwesha2k18.iitp.adapters.EventsAdapter;
import info.anwesha2k18.iitp.listeners.ViewPagerCustomDuration;

/**
 * Created by mayank on 26/5/17.
 */

public class HomePage extends android.support.v4.app.Fragment {

    final long DELAY_MS = 500;//delay info milliseconds before task is to be executed
    final long PERIOD_MS = 4000; // time info milliseconds between successive task executions.
    LinearLayout eventsLinearLayout;
    //    LinearLayout galleryLinearLayout;
    LinearLayout aboutFrameLayout;
    LinearLayout scheduleLinearLayout;
    LinearLayout sponsorsLinearLayout;
    LinearLayout teamLinearLayout;
    //    LinearLayout devLinearLayout;
    LinearLayout mapLinearLayout;

    LinearLayout socialLinearLayout ;
    Toast comingSoonToast;
    Timer timer ;
    private int currentPage = 0;
    private int NUM_PAGES;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        NUM_PAGES = getResources().obtainTypedArray(R.array.array_home_slide_show).length();

        final ViewPagerCustomDuration viewPagerCustomDuration = (ViewPagerCustomDuration) rootView.findViewById(R.id.events_pager);
        viewPagerCustomDuration.setScrollDuration(900);
        EventsAdapter eventsAdapter = new EventsAdapter(getContext(),
                getResources().obtainTypedArray(R.array.array_home_slide_show));

        viewPagerCustomDuration.setAdapter(eventsAdapter);
        /*Adding automatic swap to the images
        * */
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                viewPagerCustomDuration.setCurrentItem(currentPage++, true);
            }
        };

        timer = new Timer(); // This will create a new Thread
        timer.schedule(new TimerTask() { // task to be scheduled

            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, PERIOD_MS);


        comingSoonToast = Toast.makeText(getContext(), getResources().getString(R.string.coming_soon), Toast.LENGTH_SHORT);

        FloatingActionButton fab = rootView.findViewById(R.id.fab_maps);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String uri = "https://www.google.com/maps/@?api=1&map_action=map&center=25.535752,84.851065&zoom=16&basemap=satellite";
//                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
//                getContext().startActivity(intent);
                Intent intent = new Intent(rootView.getContext(), MyProfile.class);
                startActivity(intent);
            }
        });

        eventsLinearLayout = rootView.findViewById(R.id.events);
        eventsLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(rootView.getContext(), EventsActivityNew.class);
                startActivity(intent);
//                comingSoonToast.show();

            }
        });

        aboutFrameLayout = rootView.findViewById(R.id.about);
        aboutFrameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(rootView.getContext(), AboutActivity.class);
                startActivity(intent);
//                comingSoonToast.show();
            }
        });

        mapLinearLayout = rootView.findViewById(R.id.map);
        mapLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String uri = "https://www.google.com/maps/@?api=1&map_action=map&center=25.535752,84.851065&zoom=16&basemap=satellite";
//                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
//                startActivity(intent);
                Intent intent = new Intent(rootView.getContext(), MapActivity.class);
                startActivity(intent);
            }
        });

//        scheduleLinearLayout = (LinearLayout) rootView.findViewById(R.id.schedule);
//        scheduleLinearLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                comingSoonToast.show();
//            }
//        });

//        devLinearLayout = rootView.findViewById(R.id.developers);
//        devLinearLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(rootView.getContext(), DevelopersActivity.class);
//                startActivity(intent);
//            }
//        });

        sponsorsLinearLayout = rootView.findViewById(R.id.sponsors_menu_item);
        sponsorsLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(rootView.getContext(), SponsorsActivity.class);
                startActivity(intent);
            }
        });

        teamLinearLayout = rootView.findViewById(R.id.team);
        teamLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(rootView.getContext(), TeamActivity.class);
                startActivity(intent);

            }
        });
//        socialLinearLayout=(LinearLayout)rootView.findViewById(R.id.social);
//        socialLinearLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent= new Intent(rootView.getContext(), SocialActivity.class) ;
//                startActivity(intent);
//            }
//        });
        return rootView;
    }
}
