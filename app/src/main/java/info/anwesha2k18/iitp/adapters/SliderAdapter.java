package info.anwesha2k18.iitp.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import info.anwesha2k18.iitp.R;
import info.anwesha2k18.iitp.data.EventListData;
import info.anwesha2k18.iitp.data.SliderEventListData;

import static java.security.AccessController.getContext;

public class SliderAdapter extends PagerAdapter {
        Context context;
        TypedArray images;

        public SliderAdapter(Context context, TypedArray images) {
            this.context=context;
            this.images=images;
        }

    @Override
    public int getCount() {
        return images.length();
    }

    @Override
    public boolean isViewFromObject( View view, Object object) {
        return view==((ImageView) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(context)
                .load(images.getResourceId(position, -1))
                .into(imageView);
        ((ViewPager) container).addView(imageView, 0);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((ImageView) object);
    }
}
