package info.anwesha2k18.iitp.utils;

import android.text.TextUtils;
import android.util.Log;

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
import java.util.ArrayList;
import java.util.List;

import info.anwesha2k18.iitp.data.WorkshopData;


public final class ExtractWorkshop {

    public static final String LOG_TAG = ExtractWorkshop.class.getName();

    private ExtractWorkshop() {
    }


    public static List<WorkshopData> doEverything(String requestUrl){
        URL url = createUrl(requestUrl);

        String jsonResponse = null;

        try {
            jsonResponse = makeHttpRequest(url);
        }
        catch (IOException e){
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        List<WorkshopData> workshopDataList = extractWorkshop(jsonResponse);
        return workshopDataList;
    }

    public static List<WorkshopData> extractWorkshop(String jsonResponse) {

        if (TextUtils.isEmpty(jsonResponse)) {
            return null;
        }

        List<WorkshopData> workshopDataList = new ArrayList<>();
        try {

            JSONObject jsonObject = new JSONObject(jsonResponse);
            JSONArray workshopArray = jsonObject.getJSONArray("workshops");

            for (int j = 0; j < workshopArray.length(); j++) {

                JSONObject currentWorkshop = workshopArray.getJSONObject(j);

                String name = currentWorkshop.getString("name");
                String description = currentWorkshop.getString("description");
                String date = currentWorkshop.getString("date");
                String time = currentWorkshop.getString("time");
                String venue = currentWorkshop.getString("venue");
                String imageUrl = currentWorkshop.getString("imageurl");

                workshopDataList.add(new WorkshopData(name, description, date, time, venue, imageUrl));
            }

        } catch (JSONException e) {
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        return workshopDataList;
    }

    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL", e);
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {

        String jsonResponse = "";

        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;
        try {
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();

            if (httpURLConnection.getResponseCode() == 200) {
                inputStream = httpURLConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + httpURLConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving json result", e);
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder outputJson = new StringBuilder();

        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = bufferedReader.readLine();

            while (line != null) {
                outputJson.append(line);
                line = bufferedReader.readLine();
            }
        }
        return outputJson.toString();
    }

    public static List<WorkshopData> extLectures(String requestUrl){
        URL url = createUrl(requestUrl);

        String jsonResponse = null;

        try {
            jsonResponse = makeHttpRequest(url);
        }
        catch (IOException e){
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        List<WorkshopData> lecturesDataList = extractLectures(jsonResponse);
        return lecturesDataList;
    }

    public static List<WorkshopData> extractLectures(String jsonResponse) {

        if (TextUtils.isEmpty(jsonResponse)) {
            return null;
        }

        List<WorkshopData> lecturesDataList = new ArrayList<>();
        try {

            JSONObject jsonObject = new JSONObject(jsonResponse);
            JSONArray lecturesArray = jsonObject.getJSONArray("lectures");

            for (int j = 0; j < lecturesArray.length(); j++) {

                JSONObject currentLecture = lecturesArray.getJSONObject(j);

                String name = currentLecture.getString("name");
                String description = currentLecture.getString("description");
                String date = currentLecture.getString("date");
                String time = currentLecture.getString("time");
                String venue = currentLecture.getString("venue");
                String imageUrl = currentLecture.getString("imageurl");

                lecturesDataList.add(new WorkshopData(name, description, date, time, venue, imageUrl));
            }

        } catch (JSONException e) {
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        return lecturesDataList;
    }

}