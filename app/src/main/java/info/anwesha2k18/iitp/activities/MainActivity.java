package info.anwesha2k18.iitp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.facebook.login.LoginManager;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
