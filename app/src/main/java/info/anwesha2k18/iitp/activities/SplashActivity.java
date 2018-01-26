package info.anwesha2k18.iitp.activities;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import info.anwesha2k18.iitp.R;
import info.anwesha2k18.iitp.database.BackgroundFetch;
import info.anwesha2k18.iitp.utils.NetworkUtils;

import java.io.IOException;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

/**
 * Created by manish on 26/8/17.
 */

public class SplashActivity extends Activity {
    Handler handler;
    Runnable action;
    private GifDrawable splashGif;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            // This process is dedicated to LeakCanary for heap analysis.
//            // You should not init your app in this process.
//            return;
//        }
//        LeakCanary.install(getApplication());
        setContentView(R.layout.splashscreen);

        // Splash Image settings
//        GifImageView splashImageView = findViewById(R.id.splash);
//        try {
//            splashGif = new GifDrawable(getResources(),R.drawable.splash2);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        splashImageView.setImageDrawable(splashGif);

        handler = new Handler();
        action = new Runnable() {
            @Override
            public void run() {
//                splashGif.stop();
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        };

        DownloadImagesAysncTask downloadImage = new DownloadImagesAysncTask();
        downloadImage.execute(new ContextWrapper(getApplicationContext()), this);
        fetchHighlihtsAsynctask fetchHighlihtsAsynctaskwork = new fetchHighlihtsAsynctask();
        fetchHighlihtsAsynctaskwork.execute(new ContextWrapper(getApplicationContext()), this);

        // Update Events database
        Intent eventData = new Intent(this, BackgroundFetch.class);
        startService(eventData);
    }

    // to trigger download task info background thread
    private class DownloadImagesAysncTask extends AsyncTask<Object, Void, Boolean> {
        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if (!aBoolean) {
                Toast.makeText(getBaseContext(), "Download failed. Please try again later", Toast.LENGTH_LONG).show();
            }

            handler.postDelayed(action,1000);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(Object... params) {
            return new NetworkUtils().downloadImages((ContextWrapper) params[0], (Context) params[1]);
        }
    }

    //to trigger download task for extracting highlights
    private class fetchHighlihtsAsynctask extends AsyncTask<Object, Void, Boolean> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(Object... params) {
            return new NetworkUtils().extractHighlights((ContextWrapper) params[0], (Context) params[1]);
        }

        @Override
        protected void onPostExecute(Boolean value) {
            //if the data is not uploaded
            if (!value) {
                Log.e(SplashActivity.class.getName(), "Can't fetch the data highlights ");
            }
        }
    }
}

