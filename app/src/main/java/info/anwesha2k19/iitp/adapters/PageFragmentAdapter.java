package info.anwesha2k19.iitp.adapters;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import info.anwesha2k19.iitp.fragments.GalleryFragmentNew;
import info.anwesha2k19.iitp.fragments.HighlightsPage;
import info.anwesha2k19.iitp.fragments.HomePage;

/**
 * Created by mayank on 26/5/17.
 */

public class PageFragmentAdapter extends FragmentPagerAdapter {

    public PageFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new HomePage();
            case 1:
                return new HighlightsPage();
            case 2:
                return new GalleryFragmentNew();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return null;
    }
}
