package info.anwesha2k19.iitp.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import info.anwesha2k19.iitp.fragments.EventCategoryFragment;

public class EventFragmentAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 4;
    private String tabTitles[] = new String[] { "All Events", "Technical", "Cultural", "Arts & Welfare" };
    private Context mContext;

    public EventFragmentAdapter(Context context, FragmentManager fm){
        super(fm);
        mContext = context;
    }


    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        return EventCategoryFragment.newInstance(position + 1);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }}
