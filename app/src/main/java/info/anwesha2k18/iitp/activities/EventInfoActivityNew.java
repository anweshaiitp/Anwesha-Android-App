package info.anwesha2k18.iitp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.PersistableBundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

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
    private String eveId;

    String reg_event_url="https://www.anwesha.info/register/";

    TextView eventNameDisplay;
    TextView eventDateDisplay;
    TextView eventTimeDisplay;
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
        eveId=bundle.getString("eveId");

        eventNameDisplay=(TextView) findViewById(R.id.event_info_name);
        eventDateDisplay=(TextView) findViewById(R.id.event_info_date);
        eventTimeDisplay = (TextView) findViewById(R.id.event_info_time);
        eventVenueDisplay=(TextView) findViewById(R.id.event_info_venue);
        eventRegLinkDisplay=(TextView) findViewById(R.id.event_reg_link);
        eventInfoDisplay=(TextView) findViewById(R.id.event_info_textview);
        eventRulesDisplay=(TextView) findViewById(R.id.event_rules_textview);
        eventOrganizersDisplay=(TextView) findViewById(R.id.event_organizers);
        RequestQueue MyRequestQueue = Volley.newRequestQueue(this);

        mAppBarLayout = (AppBarLayout) findViewById(R.id.main_appbar);
        mAppBarLayout.addOnOffsetChangedListener(this);

        mainTextViewTitle = (TextView) findViewById(R.id.main_hiding_textview_title);
        startAlphaAnimation(mainTextViewTitle, 0, View.INVISIBLE);

        mTitleContainer = (LinearLayout) findViewById(R.id.main_linearlayout_hide_title);


        eventCoverDisplay=(ImageView) findViewById(R.id.event_cover_display) ;


        Button reg_butt=findViewById(R.id.btn_register);
        reg_butt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                if (!sharedPreferences.getBoolean(getString(R.string.login_status), false)) {
                    Toast.makeText(getBaseContext(), "You have not yet logged in. Please Login to continue.", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(EventInfoActivityNew.this, LoginActivity.class);
                    startActivity(intent);
                }
                else {

                    Toast.makeText(getApplicationContext(), "Registering..", Toast.LENGTH_SHORT).show();
                    StringRequest MyStringRequest = new StringRequest(Request.Method.POST, reg_event_url+eveId, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            //This code is executed if the server responds, whether or not the response contains data.
                            //The String 'response' contains the server's response.
                            Toast.makeText(getApplicationContext(), "Getting response ", Toast.LENGTH_LONG).show();

                        }
                    }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //This code is executed if there is an error.
                            Toast.makeText(getApplicationContext(), "Found error ", Toast.LENGTH_LONG).show();
                        }
                    }) {
                        protected Map<String, String> getParams() {
                            Map<String, String> MyData = new HashMap<String, String>();
                            MyData.put("eveglid",eveId); //Add the data you'd like to send to the server.
                            return MyData;
                        }
                    };
                    MyRequestQueue.add(MyStringRequest);
                }
            }
        });




        displayData();

    }

    private void displayData(){
        eventNameDisplay.setText(eveName);
        eventDateDisplay.setText(date);
        eventTimeDisplay.setText(time);
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


