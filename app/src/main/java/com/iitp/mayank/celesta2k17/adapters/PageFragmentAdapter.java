package com.iitp.mayank.celesta2k17.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by mayank on 26/5/17.
 */

public class PageFragmentAdapter extends FragmentPagerAdapter {

    PageFragmentAdapter(FragmentManager fm)
    {
        super(fm);
    }

    @Override
    public Fragment getItem(int position)
    {
      switch (position)
      {
          case 1 :
              break;

      }
      return null;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
