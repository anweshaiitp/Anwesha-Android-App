package info.anwesha2k18.iitp.activities;

import android.database.Cursor;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import info.anwesha2k18.iitp.R;
import info.anwesha2k18.iitp.data.EventData;
import info.anwesha2k18.iitp.database.WebSyncDB;
import info.anwesha2k18.iitp.fragments.EventCategoryFragment;

public class EventsActivityNew extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    public List<EventData> AllEvents = new ArrayList<>();
    WebSyncDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_tabs);

        getevents();

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setTitle("Events");
        viewPager = findViewById(R.id.viewpager);
        setupViewPager(viewPager);


        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void getevents(){
        db = new WebSyncDB(this);
        Cursor cursor = db.getAllEvents();
        int c = 0;
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            AllEvents.add(new EventData(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(3), cursor.getInt(4), cursor.getInt(5), cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9), cursor.getString(10), cursor.getString(11), cursor.getString(12), cursor.getString(13), cursor.getString(14), cursor.getString(15)));
            cursor.moveToNext();
            c++;
            if (c > 200) break;
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        for(int i=0;i<AllEvents.size();i++) {
            if(AllEvents.get(i).code == 0 && !AllEvents.get(i).name.equals("superAdmin"))
                adapter.addFrag(EventCategoryFragment.newInstance(AllEvents.get(i).id), AllEvents.get(i).name);
        }
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
