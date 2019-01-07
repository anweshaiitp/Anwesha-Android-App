package info.anwesha2k18.iitp.activities;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import info.anwesha2k18.iitp.R;

public class EventInfoActivityNew extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener{

    private String eveName;
    private String date;
    private String long_desc;
    private String short_desc;
    private String organisers;
    private String reg_url;
    private String rules_url;
    private String time;
    private String venue;
    private String cover_url;

    TextView eventNameDisplay;
    TextView eventDate_TimeDisplay;
    TextView eventVenueDisplay;
    TextView eventRegLinkDisplay;
    TextView eventInfoDisplay;
    TextView eventRulesDisplay;
    TextView eventOrganizersDisplay;
    ImageView eventCoverDisplay;
    TextView mainTextViewTitle;

    private boolean mIsTheTitleVisible = false;
    private static final int ALPHA_ANIMATIONS_DURATION = 200;
    private boolean mIsTheTitleContainerVisible = true;
    private LinearLayout mTitleContainer;
    private AppBarLayout mAppBarLayout;

    private static final float PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR  = 0.8f;
    private static final float PERCENTAGE_TO_HIDE_TITLE_DETAILS     = 0.3f;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_info_new);

        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();

        eveName = bundle.getString("eveName", "hello");
        date = bundle.getString("date");
        time = bundle.getString("time");
        venue = bundle.getString("venue");
        long_desc = bundle.getString("long_desc");
        organisers = bundle.getString("organisers");
        reg_url = bundle.getString("reg_url");
        rules_url = bundle.getString("rules_url");
        cover_url = bundle.getString("cover_url");
        short_desc = bundle.getString("short_desc");

        eventNameDisplay=(TextView) findViewById(R.id.event_info_name);
        eventDate_TimeDisplay=(TextView) findViewById(R.id.event_date_time);
        eventVenueDisplay=(TextView) findViewById(R.id.event_venue);
        eventRegLinkDisplay=(TextView) findViewById(R.id.event_reg_link);
        eventInfoDisplay=(TextView) findViewById(R.id.event_info_textview);
        eventRulesDisplay=(TextView) findViewById(R.id.event_rules_textview);
        eventOrganizersDisplay=(TextView) findViewById(R.id.event_organizers);

        mAppBarLayout = (AppBarLayout) findViewById(R.id.main_appbar);
        mAppBarLayout.addOnOffsetChangedListener(this);

        mainTextViewTitle = (TextView) findViewById(R.id.main_hiding_textview_title);
        startAlphaAnimation(mainTextViewTitle, 0, View.INVISIBLE);

        mTitleContainer = (LinearLayout) findViewById(R.id.main_linearlayout_hide_title);


        eventCoverDisplay=(ImageView) findViewById(R.id.event_cover_display) ;

        displayData();

    }

    private void displayData(){
        eventNameDisplay.setText(eveName);
        eventDate_TimeDisplay.setText(date+" "+time);
        eventVenueDisplay.setText(venue);
        eventRegLinkDisplay.setText(reg_url);
        eventInfoDisplay.setText(long_desc);
        eventRulesDisplay.setText(rules_url);
        eventOrganizersDisplay.setText(organisers);

        mainTextViewTitle.setText(eveName);

        Glide.with(this)
                .load(cover_url)
                .apply(new RequestOptions().error(R.drawable.anwesha_placeholder))
                .into(eventCoverDisplay);

    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int offset) {
        int maxScroll = appBarLayout.getTotalScrollRange();
        float percentage = (float) Math.abs(offset) / (float) maxScroll;

        handleAlphaOnTitle(percentage);
        handleToolbarTitleVisibility(percentage);
    }

    private void handleToolbarTitleVisibility(float percentage) {
        if (percentage >= PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR) {

            if(!mIsTheTitleVisible) {
                startAlphaAnimation(mainTextViewTitle, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                mIsTheTitleVisible = true;
            }

        } else {

            if (mIsTheTitleVisible) {
                startAlphaAnimation(mainTextViewTitle, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                mIsTheTitleVisible = false;
            }
        }
    }

    public static void startAlphaAnimation (View v, long duration, int visibility) {
        AlphaAnimation alphaAnimation = (visibility == View.VISIBLE)
                ? new AlphaAnimation(0f, 1f)
                : new AlphaAnimation(1f, 0f);

        alphaAnimation.setDuration(duration);
        alphaAnimation.setFillAfter(true);
        v.startAnimation(alphaAnimation);
    }

    private void handleAlphaOnTitle(float percentage) {
        if (percentage >= PERCENTAGE_TO_HIDE_TITLE_DETAILS) {
            if(mIsTheTitleContainerVisible) {
                startAlphaAnimation(mTitleContainer, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                mIsTheTitleContainerVisible = false;
            }

        } else {

            if (!mIsTheTitleContainerVisible) {
                startAlphaAnimation(mTitleContainer, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                mIsTheTitleContainerVisible = true;
            }
        }
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Glide.get(getApplicationContext()).clearMemory();
        Glide.get(getApplicationContext()).trimMemory(TRIM_MEMORY_COMPLETE);
        System.gc();
    }

}

