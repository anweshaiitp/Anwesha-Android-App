package info.anwesha2k18.iitp.activities;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;
import info.anwesha2k18.iitp.R;
import info.anwesha2k18.iitp.database.BackgroundFetch;
import info.anwesha2k18.iitp.utils.NetworkUtils;

import java.io.IOException;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;


public class SplashActivity extends Activity {
    int time=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(SplashActivity.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        },time);
        Window window=this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.cardBackgroundEvents));

    }
}

