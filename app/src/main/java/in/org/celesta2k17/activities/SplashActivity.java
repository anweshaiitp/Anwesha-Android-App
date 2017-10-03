package in.org.celesta2k17.activities;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import in.org.celesta2k17.R;
import in.org.celesta2k17.utils.NetworkUtils;

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

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);

        // Splash Image settings
        GifImageView splashImageView = findViewById(R.id.splash);
        try {
            splashGif = new GifDrawable(getResources(),R.drawable.splash2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        splashImageView.setImageDrawable(splashGif);

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
    }

    // to trigger download task in background thread
    private class DownloadImagesAysncTask extends AsyncTask<Object, Void, Boolean> {
        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if (!aBoolean) {
                Toast.makeText(getBaseContext(), "Download failed. Please try again later", Toast.LENGTH_LONG).show();
            }
            int time = splashGif.getDuration() - (splashGif.getCurrentPosition())%splashGif.getDuration();
            handler.postDelayed(action, time);
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

