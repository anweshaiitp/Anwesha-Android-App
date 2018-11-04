package info.anwesha2k18.iitp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Toast;

import com.facebook.login.LoginManager;

import java.util.Objects;

import info.anwesha2k18.iitp.R;
import info.anwesha2k18.iitp.adapters.PageFragmentAdapter;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment info memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private PageFragmentAdapter mPageFragmentAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private Menu menu = null;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.home);
        navigationView = findViewById(R.id.navigation_view);
        MainActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(MainActivity.this,R.color.cardview_dark_background));
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        navigationView.setNavigationItemSelectedListener(item -> {

            switch (item.getItemId()) {
                case R.id.menu_item_sponsors:
                    Intent intent = new Intent(MainActivity.this,SponsorsActivity.class);
                    startActivity(intent);
                    break;
                case R.id.menu_item_faq:
//                    startActivity(new Intent(MainActivity.this, FaqActivity.class));
                    Toast.makeText(MainActivity.this, "Coming Soon!", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.menu_item_about:
                    startActivity(new Intent(MainActivity.this, AboutActivity.class));
                    break;
                case R.id.menu_item_profile:
                    if (!sharedPreferences.getBoolean(getString(R.string.login_status), false)) {
                        Intent intentLogin = new Intent(this, LoginActivity.class);
                        startActivity(intentLogin);
                    } else {
                        Intent intentLogin = new Intent(this, MyProfile.class);
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
                    Toast.makeText(MainActivity.this, "Coming Soon!", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.menu_item_lectures:
                    Toast.makeText(MainActivity.this, "Coming Soon!", Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(MainActivity.this, LecturesActivity.class));
                    break;
                case R.id.menu_item_workshops:
                    Toast.makeText(MainActivity.this, "Coming Soon!", Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(MainActivity.this, WorkshopsActivity.class));
                    break;
                case R.id.menu_item_exhibitions:
                    Toast.makeText(MainActivity.this, "Coming Soon!", Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(MainActivity.this, ExpoEvents.class));
                    break;
                case R.id.menu_item_ca:
                    Intent intentweb = new Intent(MainActivity.this,webActivity.class);
                    intentweb.putExtra("link","https://beta.anwesha.info");
                    startActivity(intentweb);
                    break;
                case R.id.menu_item_multicity:
                    startActivity(new Intent(MainActivity.this, multiCityActivity.class));
                    break;
                case R.id.menu_item_movie:
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=tare6Drw0Ng&t=91s")));
                    break;
                case R.id.menu_item_team:
                    Toast.makeText(MainActivity.this, "Coming Soon!", Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(MainActivity.this, TeamActivity.class));
//                  startActivity(new Intent(MainActivity.this, TeamActivity.class));
                    break;
                case R.id.menu_item_events:
                    startActivity(new Intent(MainActivity.this, EventsActivityNew.class));
                    break;
                case R.id.menu_item_map:
                    String uri = "https://www.google.com/maps/d/viewer?mid=1Tub6_KM_0Tv8UHkh97SP9Tehv78HBv1e&usp=sharingax&basemap=satellite";
                    Intent intentMap = new Intent(Intent.ACTION_VIEW, android.net.Uri.parse(uri));
                    Objects.requireNonNull(MainActivity.this).startActivity(intentMap);
                    break;
            }
            item.setChecked(true);
            drawerLayout.closeDrawers();

            return true;
        });
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mPageFragmentAdapter = new PageFragmentAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mPageFragmentAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        int tabIcons[] = {R.drawable.ic_home_white_24dp,
                R.drawable.ic_stars_white_24dp,
                R.drawable.ic_photo_library_white_24dp};

        for (int i = 0; i < mPageFragmentAdapter.getCount(); i++)
            tabLayout.getTabAt(i).setIcon(tabIcons[i]);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        this.menu = menu;
        refreshMenu();
        return true;
    }

    private void refreshMenu() {
        if (menu != null) {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            if (!sharedPreferences.getBoolean(getString(R.string.login_status), false)) {
                menu.findItem(R.id.action_log_out).setVisible(false);
                menu.findItem(R.id.action_log_in).setVisible(true);
                menu.findItem(R.id.action_register).setVisible(false);
            } else {
                menu.findItem(R.id.action_log_in).setVisible(false);
                menu.findItem(R.id.action_log_out).setVisible(true);
                menu.findItem(R.id.action_register).setVisible(false);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity info AndroidManifest.xml.
        int id = item.getItemId();
        if (id == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
            return true;
        }
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_log_out) {
            SharedPreferences.Editor sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this).edit();
            sharedPreferences.putBoolean(getString(R.string.login_status), false);
            sharedPreferences.apply();
            refreshMenu();
            LoginManager.getInstance().logOut();
            Toast.makeText(this, "Logged Out Successfully", Toast.LENGTH_SHORT).show();
        }

        if (id == R.id.action_log_in) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            refreshMenu();
        }

//        if (id == R.id.action_register) {
//            Intent intent = new Intent(MainActivity.this, MyProfile.class);
//            startActivity(intent);
//            refreshMenu();
//        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */

    // Go to myProfile Activity
    public void toMyProfile(View view) {
        Intent intent = new Intent(this, MyProfile.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (menu != null) {
            refreshMenu();
        }
    }
}
