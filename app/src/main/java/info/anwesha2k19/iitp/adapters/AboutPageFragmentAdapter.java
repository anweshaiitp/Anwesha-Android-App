package info.anwesha2k19.iitp.adapters;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import info.anwesha2k19.iitp.fragments.AboutFragment;
import info.anwesha2k19.iitp.fragments.DevelopersFragment;

/**
 * Created by Muks14x on 1/26/18.
 */

public class AboutPageFragmentAdapter extends FragmentPagerAdapter {
    public AboutPageFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new AboutFragment();
            case 1:
                return new DevelopersFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0) {
            title = "Anwesha";
        } else if (position == 1) {
            title = "App Developers";
        }
        return title;
    }
}
