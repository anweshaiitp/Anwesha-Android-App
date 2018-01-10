package info.anwesha2k18.iitp.database;

import android.app.Service;
import android.content.ContentValues;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by mayank on 26/11/17.
 */

public class BackgroundFetch extends Service {

    final static String BASE_URL = "http://anwesha.info";
    RequestQueue queue;
    WebSyncDB db;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        db = new WebSyncDB(getApplicationContext());
        Log.e("BackgroundFetch", "Service Created");

    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.e("BackgroundFetch", "Service Started");
        JsonArrayRequest events = new JsonArrayRequest(BASE_URL + "/allEvents", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                Log.e("BackgroundFetch", "EventSuccess");
                updateEvents(jsonArray);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("BackgroundFetch", "Cound't Fetch Event list");
            }
        });
        queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(events);

    }

    void updateEvents(JSONArray jsonArray) {
        try {
            JSONArray array = jsonArray.getJSONArray(1);// Second Element
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

                contentValues[i] = cv;

            }
            db.insertEvent(contentValues);

        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("BackgroundFetch", "Invalid JSON");
        }
    }
}