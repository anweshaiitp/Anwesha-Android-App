package info.anwesha2k18.iitp.activities;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.mxn.soul.flowingdrawer_core.ElasticDrawer;
import com.mxn.soul.flowingdrawer_core.FlowingDrawer;

import info.anwesha2k18.iitp.R;
import info.anwesha2k18.iitp.adapters.PageFragmentAdapter;
import info.anwesha2k18.iitp.fragments.MenuListFragment;

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
    private FlowingDrawer mDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createNotificationChannel();

        MainActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        window.setStatusBarColor(ContextCompat.getColor(MainActivity.this, R.color.statusColor));

        setupMenu();


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
 //           drawerLayout.openDrawer(GravityCompat.START);
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

    @Override
    public void onBackPressed() {
        if (mDrawer.isMenuVisible()) {
            mDrawer.closeMenu();
        } else {
            Intent i = new Intent(MainActivity.this, thankyou.class);
            startActivity(i);
        }
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "FCM channel";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(getString(R.string.default_notification_channel_id), name, importance);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void setupMenu() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);

        mDrawer = (FlowingDrawer) findViewById(R.id.drawerlayout);
        mDrawer.setTouchMode(ElasticDrawer.TOUCH_MODE_BEZEL);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawer.openMenu();
            }
        });

        FragmentManager fm = getSupportFragmentManager();
        MenuListFragment mMenuFragment = (MenuListFragment) fm.findFragmentById(R.id.id_container_menu);
        if (mMenuFragment == null) {
            mMenuFragment = new MenuListFragment();
            fm.beginTransaction().add(R.id.id_container_menu, mMenuFragment).commit();
        }
    }

}
