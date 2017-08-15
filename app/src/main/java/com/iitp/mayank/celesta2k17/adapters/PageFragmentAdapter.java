package com.iitp.mayank.celesta2k17.adapters;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.iitp.mayank.celesta2k17.fragments.HighlightsPage;
import com.iitp.mayank.celesta2k17.fragments.HomePage;
import com.iitp.mayank.celesta2k17.fragments.VideosPage;

/**
 * Created by mayank on 26/5/17.
 */

public class PageFragmentAdapter extends FragmentPagerAdapter {

    public PageFragmentAdapter(FragmentManager fm)
    {
        super(fm);
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position)
    {
      switch (position)
      {
          case 0 :
              return new HomePage();
          case 1 :
              return new HighlightsPage();
          case 2 :
              return new VideosPage();
          case 3 :
              return new VideosPage();
          default:
              return null;
      }
    }

    @Override
    public int getCount()
    {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return null;
    }
}
