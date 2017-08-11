package com.iitp.mayank.celesta2k17.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.iitp.mayank.celesta2k17.R;

/**
 * Created by mayank on 17/6/17.
 */

public class EventsAdapter extends PagerAdapter {
    Context context;
     int events[] = {R.drawable.ic_home_white_24dp,
                    R.drawable.ic_poll_white_24dp};

    public EventsAdapter(Context context){
        this.context=context;
    }

    @Override
    public int getCount() {
        return events.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((ImageView) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(events[position]);
        ((ViewPager) container).addView(imageView,0);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((ImageView) object);
    }
}
