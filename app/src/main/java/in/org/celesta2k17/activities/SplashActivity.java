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

/**
 * Created by manish on 26/8/17.
 */

public class SplashActivity extends Activity {
    Handler handler;
    Runnable action;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);

        handler = new Handler();
        action = new Runnable() {
            @Override
            public void run() {
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
            handler.postDelayed(action, 1000);
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
            if (value == false) {
                Log.e(SplashActivity.class.getName(), "Can't fetch the data highlights ");
            }
        }
    }
}

