package info.anwesha2k18.iitp.fragments;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

import info.anwesha2k18.iitp.activities.LoginActivity;
import info.anwesha2k18.iitp.R;

import info.anwesha2k18.iitp.activities.AboutActivity;
import info.anwesha2k18.iitp.activities.EventsActivityNew;
import info.anwesha2k18.iitp.activities.MapActivity;
import info.anwesha2k18.iitp.activities.MyProfile;
import info.anwesha2k18.iitp.activities.SocialActivity;
import info.anwesha2k18.iitp.activities.SponsorsActivity;
import info.anwesha2k18.iitp.activities.TeamActivity;
import info.anwesha2k18.iitp.activities.TimelineActivity;
import info.anwesha2k18.iitp.adapters.EventsAdapter;
import info.anwesha2k18.iitp.adapters.SliderAdapter;
import info.anwesha2k18.iitp.listeners.ViewPagerCustomDuration;

/**
 * Created by mayank on 26/5/17.
 */

public class
HomePage extends android.support.v4.app.Fragment {
    SharedPreferences sharedPreferences;

    final long DELAY_MS = 500;//delay info milliseconds before task is to be executed
    final long PERIOD_MS = 4000; // time info milliseconds between successive task executions.
    ImageView SlideShowGallery;
    ImageView SlideShowEvents;
    View eventsLinearLayout;
    View galleryLinearLayout;
    View aboutFrameLayout;
    View profileFrameLayout;
    View scheduleLinearLayout;
    View sponsorsLinearLayout;
    View teamLinearLayout;
    View CardFrontView;
    View CardBackView;
    View devLinearLayout;
    View mapLinearLayout;
    View socialLinearLayout ;
    Toast comingSoonToast;
    Timer timer ;
    private int currentPage = 0;
    private int NUM_PAGES;
    private int toggle;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        super.onCreate(savedInstanceState);
    }


    private void SetDaysToAnwesha(final TextView view,final TextView view1,final TextView view2)
    {
        Calendar c = new GregorianCalendar();
        c.set(Calendar.YEAR, 2019);
        c.set(Calendar.MONTH, 1); // 11 = december
        c.set(Calendar.DAY_OF_MONTH, 2);

        Date xmas = c.getTime();
        Date today = new Date();
        long diff = xmas.getTime() - today.getTime();
        diff = diff / (1000L*60L*60L*24L);
        if(diff>0) {
            view.setText("" + diff);
            view2.setText("Days left");
            view2.setTextSize(20);
        }
        else
        {
            if(diff<=0 && diff>-3)
            {
                diff=diff*-1+1;
                view.setText("" +diff);
                view1.setText("Day");
                view1.setTextSize(20);
            }
            else
            {
                view.setText("Coming soon");
            }
        }
    }
    private void animate(final ImageView imageView, final int images[], final int imageIndex, final boolean forever, final int timeBetween) {

        //imageView <-- The View which displays the images for the anim
        //images[] <-- Holds R references to the images to display
        //imageIndex <-- index of the first image to show in images[]
        //frever <-- If equals true then after the last image it starts all over again with the first image resulting in an infinite loop. You have been warned.

        int fadeInDuration = 500; // Configure time values here
        int fadeOutDuration = 500;

        imageView.setVisibility(View.INVISIBLE);    //Visible or invisible by default - this will apply when the animation ends
        imageView.setImageResource(images[imageIndex]);

        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new DecelerateInterpolator()); // add this
        fadeIn.setDuration(fadeInDuration);

        Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateInterpolator()); // and this
        fadeOut.setStartOffset(fadeInDuration + timeBetween);
        fadeOut.setDuration(fadeOutDuration);

        AnimationSet animation = new AnimationSet(false); // change to false when i need
        animation.addAnimation(fadeIn);
        animation.addAnimation(fadeOut);
        animation.setRepeatCount(1);
        imageView.setAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                if (images.length - 1 > imageIndex) {
                    animate(imageView, images, imageIndex + 1,forever,timeBetween); //Calls itself until it gets to the end of the array
                }
                else {
                    if (forever){
                        animate(imageView, images, 0,forever,timeBetween);  //Calls itself to start the animation all over again in a loop if forever = true
                    }
                }
            }
            public void onAnimationRepeat(Animation animation) {

            }
            public void onAnimationStart(Animation animation) {

            }
        });
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        NUM_PAGES = getResources().obtainTypedArray(R.array.array_home_slide_show).length();

        final ViewPagerCustomDuration viewPagerCustomDuration = (ViewPagerCustomDuration) rootView.findViewById(R.id.events_pager);
        viewPagerCustomDuration.setScrollDuration(900);
       // EventsAdapter eventsAdapter = new EventsAdapter(getContext(), getResources().obtainTypedArray(R.array.array_home_slide_show));

        //viewPagerCustomDuration.setAdapter(eventsAdapter);
        /*Adding automatic swap to the images
        * */

        SliderAdapter sliderAdapter=new SliderAdapter(getContext(),getResources().obtainTypedArray(R.array.array_home_slide_show));
        viewPagerCustomDuration.setAdapter(sliderAdapter);
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES)
                {
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

        TextView textView = (TextView)rootView.findViewById(R.id.DaysLeft);
        TextView textViewAbove = (TextView)rootView.findViewById(R.id.otherTextAbove);
        TextView textViewBelow = (TextView)rootView.findViewById(R.id.otherTextBelow);
        SetDaysToAnwesha(textView,textViewAbove,textViewBelow);

//        FloatingActionButton fab = rootView.findViewById(R.id.fab_maps);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String uri = "https://www.google.com/maps/@?api=1&map_action=map&center=25.535752,84.851065&zoom=16&basemap=satellite";
//                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
//                getContext().startActivity(intent);
////                Intent intent = new Intent(rootView.getContext(), MyProfile.class);
////                startActivity(intent);
//            }
//        });
//
        eventsLinearLayout = rootView.findViewById(R.id.events);
        eventsLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(rootView.getContext(), EventsActivityNew.class);
                startActivity(intent);
            }
        });

//        SlideShowEvents = (ImageView) rootView.findViewById(R.id.slideShowEvents);
//        int imagesToShowEvents[] = { R.drawable.anwesha_clix, R.drawable.anwesha_cover,R.drawable.anwesha_telegraph };
//        animate(SlideShowEvents, imagesToShowEvents, 0,true,1500);

//        SlideShowGallery = (ImageView) rootView.findViewById(R.id.slideShowGallery);
//        int imagesToShowGallery[] = { R.drawable.temp1, R.drawable.temp2,R.drawable.temp3,R.drawable.temp4};
//        animate(SlideShowGallery, imagesToShowGallery, 0,true,1200);

        View view=(View)rootView.findViewById(R.id.flipCard);
        ObjectAnimator animation = ObjectAnimator.ofFloat(view, "rotationY", -90f, 90f);
        CardFrontView = rootView.findViewById(R.id.CardFront);
        CardBackView = rootView.findViewById(R.id.CardBack);
        animation.setDuration(6000);
        animation.setRepeatCount(ObjectAnimator.INFINITE);
        animation.setInterpolator(new DecelerateInterpolator());
        toggle=1;
        animation.start();
        animation.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }
            @Override
            public void onAnimationEnd(Animator animation) {
                CardBackView.setVisibility(View.INVISIBLE);
                CardFrontView.setVisibility(View.VISIBLE);
            }
            @Override
            public void onAnimationCancel(Animator animation) {

            }
            @Override
            public void onAnimationRepeat(Animator animation) {
                if (toggle == 1)
                {
                    CardFrontView.setVisibility(View.INVISIBLE);
                    CardBackView.setVisibility(View.VISIBLE);
                }
                else
                {
                    CardBackView.setVisibility(View.INVISIBLE);
                    CardFrontView.setVisibility(View.VISIBLE);
                }
                toggle*=-1;
            }
        });

        aboutFrameLayout = rootView.findViewById(R.id.about);
        aboutFrameLayout.setOnClickListener(new View.OnClickListener() {
              @Override
            public void onClick(View v) {
                Intent intent = new Intent(rootView.getContext(), AboutActivity.class);
                startActivity(intent);
                comingSoonToast.show();
            }
        });

        //Profile layout
        profileFrameLayout = rootView.findViewById(R.id.myProfile);
        profileFrameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!sharedPreferences.getBoolean(getString(R.string.login_status), false)) {
                    Intent intentLogin = new Intent(getContext(), LoginActivity.class);
                    startActivity(intentLogin);
                } else {
                    Intent intentLogin = new Intent(getContext(), MyProfile.class);
                    startActivity(intentLogin);
                }
            }
        });

        //Sponsors layout
        sponsorsLinearLayout = rootView.findViewById(R.id.sponsors);
        sponsorsLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SponsorsActivity.class);
                startActivity(intent);
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

        scheduleLinearLayout =  rootView.findViewById(R.id.schedule);
        scheduleLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(rootView.getContext(), TimelineActivity.class));
            }
        });

//        devLinearLayout = rootView.findViewById(R.id.developers);
//        devLinearLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(rootView.getContext(), DevelopersActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        sponsorsLinearLayout = rootView.findViewById(R.id.sponsors_menu_item);
//        sponsorsLinearLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(rootView.getContext(), SponsorsActivity.class);
//                startActivity(intent);
//            }
//        });

        teamLinearLayout = rootView.findViewById(R.id.team);
        teamLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(rootView.getContext(), TeamActivity.class);
                startActivity(intent);


            }
        });

        socialLinearLayout=rootView.findViewById(R.id.social);
        socialLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(rootView.getContext(), SocialActivity.class) ;
                startActivity(intent);
            }
        });
        return rootView;
    }
}