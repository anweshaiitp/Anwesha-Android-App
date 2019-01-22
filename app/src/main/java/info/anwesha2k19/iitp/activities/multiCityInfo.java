package info.anwesha2k19.iitp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import info.anwesha2k19.iitp.R;

import static info.anwesha2k19.iitp.activities.EventInfoActivity.startAlphaAnimation;

public class multiCityInfo extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener{

    private static final float PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR  = 0.9f;
    private static final float PERCENTAGE_TO_HIDE_TITLE_DETAILS     = 0.3f;
    private static final int ALPHA_ANIMATIONS_DURATION              = 200;

    private boolean mIsTheTitleVisible          = false;
    private boolean mIsTheTitleContainerVisible = true;
    public Button rulesButton,registerButton;
    private LinearLayout mTitleContainer;
    private TextView mTitle;
    private AppBarLayout mAppBarLayout;
    private Toolbar mToolbar;
    public static final String EXTRA_HEADER = "Header",
            EXTRA_RULES = "Rules",
            EXTRA_VENUE = "Venue",
            EXTRA_DATE_TIME = "DateTime",
            EXTRA_IMAGE_ID = "ImageId";
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Glide.get(getApplicationContext()).clearMemory();
        Glide.get(getApplicationContext()).trimMemory(TRIM_MEMORY_COMPLETE);
        System.gc();
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multicity_info);
        mToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        mTitle = (TextView) findViewById(R.id.main_textview_title);
        mTitleContainer = (LinearLayout) findViewById(R.id.main_linearlayout_title);
        mAppBarLayout = (AppBarLayout) findViewById(R.id.main_appbar);
        mAppBarLayout.addOnOffsetChangedListener(this);
        startAlphaAnimation(mTitle, 0, View.INVISIBLE);
        Intent intent = getIntent();
        final String header = intent.getStringExtra(EXTRA_HEADER);
        final String dateTime = intent.getStringExtra(EXTRA_DATE_TIME);
        String venue = intent.getStringExtra(EXTRA_VENUE);
        String rules = intent.getStringExtra(EXTRA_RULES);
        final int imageId = intent.getIntExtra(EXTRA_IMAGE_ID, -1);
        ((TextView) findViewById(R.id.event_info_name)).setText(header);
        mTitle.setText(header);
        if (imageId != -1)
            Glide.with(this)
                    .load(imageId)
                    .into((ImageView) findViewById(R.id.event_info_imageview));

        TextView rulesTextView = findViewById(R.id.event_rules_textview);
        if (rules.equals("-1")) {
            rulesTextView.setVisibility(View.GONE);
        } else {
            rulesTextView.setText(rules);
        }

        if (!dateTime.equals("-1"))
            ((TextView) findViewById(R.id.event_date_time)).setText(dateTime);
        else
            findViewById(R.id.event_date_time).setVisibility(View.GONE);

        if (!venue.equals("-1"))
            ((TextView) findViewById(R.id.event_venue)).setText(venue);
        else
            findViewById(R.id.event_venue).setVisibility(View.GONE);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(item);
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
                startAlphaAnimation(mTitle, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                mIsTheTitleVisible = true;
            }

        } else {

            if (mIsTheTitleVisible) {
                startAlphaAnimation(mTitle, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                mIsTheTitleVisible = false;
            }
        }
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

    public static void startAlphaAnimation (View v, long duration, int visibility) {
        AlphaAnimation alphaAnimation = (visibility == View.VISIBLE)
                ? new AlphaAnimation(0f, 1f)
                : new AlphaAnimation(1f, 0f);

        alphaAnimation.setDuration(duration);
        alphaAnimation.setFillAfter(true);
        v.startAnimation(alphaAnimation);
    }
}
