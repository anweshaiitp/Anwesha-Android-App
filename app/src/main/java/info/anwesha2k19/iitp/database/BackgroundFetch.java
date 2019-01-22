package info.anwesha2k19.iitp.database;

import android.app.Service;
import android.content.ContentValues;
import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Created by mayank on 26/11/17.
 */

public class BackgroundFetch extends Service {



    /** Tag for the log messages */
    private static final String LOG_TAG = "myLogMessage";


    final static String BASE_URL = "https://anwesha.info";
    RequestQueue queue;
    WebSyncDB db;
    final static String EVENT_URL = "https://firebasestorage.googleapis.com/v0/b/anwesha2k19-grobo.appspot.com/o/event.json?alt=media";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        db = new WebSyncDB(getApplicationContext());
        Log.e(LOG_TAG, "Service Created");
        updateEvents();
    }

    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // Closing the input stream could throw an IOException, which is why
                // the makeHttpRequest(URL url) method signature specifies than an IOException
                // could be thrown.
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    //Reading the file
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    /**
     * Returns new URL object from the given string URL.
     */
    private static URL createUrl() {
        URL url = null;
        try {
            url = new URL(EVENT_URL);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL ", e);
        }
        return url;
    }

   public void updateEvents() {
       URL events= createUrl();
       Log.e(LOG_TAG,"Checking by amartya");
       String jsonArray = null;

       try {
         jsonArray = makeHttpRequest(events);
       } catch (IOException e) {
           e.printStackTrace();
       }
       try {
           JSONObject baseJsonResponse=new JSONObject(jsonArray);
            JSONArray array=baseJsonResponse.getJSONArray("events");
            ContentValues[] contentValues = new ContentValues[array.length()];
            for (int i = 0; i < array.length(); i++) {
                JSONObject row = array.getJSONObject(i);
                ContentValues cv = new ContentValues();

                try {
                    cv.put(WebSyncDB.EVENT_ID, row.getInt("eveId"));
                } catch (JSONException e) {
                    cv.put(WebSyncDB.EVENT_ID, 0);
                }
                try {
                    cv.put(WebSyncDB.EVENT_NAME, row.getString("eveName"));
                } catch (JSONException e) {
                    cv.put(WebSyncDB.EVENT_NAME, " ");
                }
                try {
                    cv.put(WebSyncDB.EVENT_fee, row.getInt("fee"));
                } catch (JSONException e) {
                    cv.put(WebSyncDB.EVENT_fee, 0);
                }
                try {
                    cv.put(WebSyncDB.EVENT_day, row.getInt("day"));
                } catch (JSONException e) {
                    cv.put(WebSyncDB.EVENT_day, 0);
                }
                try {
                    cv.put(WebSyncDB.EVENT_size, row.getInt("size"));
                } catch (JSONException e) {
                    cv.put(WebSyncDB.EVENT_size, 0);
                }
                try {
                    cv.put(WebSyncDB.EVENT_code, row.getInt("code"));
                } catch (JSONException e) {
                    cv.put(WebSyncDB.EVENT_code, 0);
                }
                try {
                    cv.put(WebSyncDB.EVENT_tagline, row.getString("tagline"));
                } catch (JSONException e) {
                    cv.put(WebSyncDB.EVENT_tagline, " ");
                }
                try {
                    cv.put(WebSyncDB.EVENT_date, row.getString("date"));
                } catch (JSONException e) {
                    cv.put(WebSyncDB.EVENT_date, " ");
                }
                try {
                    cv.put(WebSyncDB.EVENT_time, row.getString("time"));
                } catch (JSONException e) {
                    cv.put(WebSyncDB.EVENT_time, " ");
                }
                try {
                    cv.put(WebSyncDB.EVENT_venue, row.getString("venue"));
                } catch (JSONException e) {
                    cv.put(WebSyncDB.EVENT_venue, " ");
                }
                try {
                    cv.put(WebSyncDB.EVENT_organisers, row.getString("organisers"));
                } catch (JSONException e) {
                    cv.put(WebSyncDB.EVENT_organisers, " ");
                }
                try {
                    cv.put(WebSyncDB.EVENT_short_desc, row.getString("short_desc"));
                } catch (JSONException e) {
                    cv.put(WebSyncDB.EVENT_short_desc, " ");
                }
                try {
                    cv.put(WebSyncDB.EVENT_long_desc, row.getString("long_desc"));
                } catch (JSONException e) {
                    cv.put(WebSyncDB.EVENT_long_desc, " ");
                }
                try {
                    cv.put(WebSyncDB.EVENT_image_url, row.getString("cover_url"));
                } catch (JSONException e) {
                    cv.put(WebSyncDB.EVENT_image_url, " ");
                }
                try {
                    cv.put(WebSyncDB.EVENT_rules_url, row.getString("rules_url"));
                } catch (JSONException e) {
                    cv.put(WebSyncDB.EVENT_rules_url, "To be updated soon.");
                }
                try {
                    cv.put(WebSyncDB.EVENT_reg_url, row.getString("reg_url"));
                } catch (JSONException e) {
                    cv.put(WebSyncDB.EVENT_reg_url, "To be updated soon.");
                }

                contentValues[i] = cv;

            }
            db.insertEvent(contentValues);

        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("BackgroundFetch", "Invalid JSON");
        }
    }
}
