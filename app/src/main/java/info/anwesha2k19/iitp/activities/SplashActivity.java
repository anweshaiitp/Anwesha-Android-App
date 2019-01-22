package info.anwesha2k19.iitp.activities;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import com.nineoldandroids.animation.AnimatorInflater;
import com.nineoldandroids.animation.ObjectAnimator;

import info.anwesha2k19.iitp.R;
import info.anwesha2k19.iitp.database.BackgroundFetch;
import info.anwesha2k19.iitp.utils.NetworkUtils;

import java.io.IOException;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;


public class SplashActivity extends Activity {
    int time = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.splashscreen );
        View view = (ImageView) findViewById( R.id.logo );
        final ObjectAnimator animation = ObjectAnimator.ofFloat( view, "rotationY", 0.0f, 360f );
        animation.setDuration( 2000 );
        animation.setRepeatCount( ObjectAnimator.INFINITE );
        animation.setInterpolator( new AccelerateDecelerateInterpolator() );
        animation.start();
        new Handler().postDelayed( new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent( SplashActivity.this, MainActivity.class );
                startActivity( i );
                finish();
            }
        }, time );
        Window window = this.getWindow();
        window.setStatusBarColor( ContextCompat.getColor( this, R.color.cardBackgroundEvents ) );


    }
}

