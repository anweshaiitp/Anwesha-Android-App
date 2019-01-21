package info.anwesha2k18.iitp.activities;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import info.anwesha2k18.iitp.R;
import info.anwesha2k18.iitp.database.LocationsContentProvider;
import info.anwesha2k18.iitp.database.LocationsDB;

public class MapActivity extends FragmentActivity implements LoaderManager.LoaderCallbacks<Cursor>, GoogleMap.OnMapClickListener, OnMapReadyCallback {

    GoogleMap googleMap;
    double x, y;
    int flag = 0, flag2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        // Getting reference to the SupportMapFragment of activity_main.xml
        SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        // Getting GoogleMap object from the fragment
        fm.getMapAsync(this);

        // Enabling MyLocation Layer of Google MapActivity

      /*  googleMap.setOnMapLongClickListener(new OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng point) {
                // Removing all markers from the Google MapActivity
                googleMap.clear();
                // Creating an instance of LocationDeleteTask
                LocationDeleteTask deleteTask = new LocationDeleteTask();
                // Deleting all the rows from SQLite database table
                deleteTask.execute();
                Toast.makeText(getBaseContext(), "All markers are removed", Toast.LENGTH_LONG).show();
            }
        });  */
    }

    @Override
    public void onMapReady(GoogleMap googleMapp) {
        googleMap = googleMapp;
        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        } else {
            googleMap.setMyLocationEnabled(true);
        }
        googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        // Invoke LoaderCallbacks to retrieve and draw already saved locations in map
        getSupportLoaderManager().initLoader(0, null, this);

        initialise();
        googleMap.setOnMapClickListener(this);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    googleMap.setMyLocationEnabled(true);
                }
                break;
            }
        }
    }
    public void initialise() {
        LatLng iit = new LatLng(25.535721, 84.851003);
        MarkerOptions op1 = new MarkerOptions();
        op1.position(iit).title("IIT Patna")
                .draggable(false)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        Marker m1 = googleMap.addMarker(op1);
        m1.showInfoWindow();
        // Move the camera instantly to location with a zoom of 15.
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(iit, 15));

        // Zoom in, animating the camera.
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);

        LatLng bh = new LatLng(25.541070, 84.850971);
        MarkerOptions op3 = new MarkerOptions();
        op3.position(bh).title("Boy's hostel")
                .draggable(false)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        Marker m3 = googleMap.addMarker(op3);
        m3.showInfoWindow();

        LatLng gh = new LatLng(25.5376910, 84.8490230);
        MarkerOptions op4 = new MarkerOptions();
        op4.position(gh).title("Girl's hostel")
                .draggable(false)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        Marker m4 = googleMap.addMarker(op4);
        m4.showInfoWindow();

        LatLng ho = new LatLng(25.549734, 84.853759);
        MarkerOptions op5 = new MarkerOptions();
        op5.position(ho).title("Ruban hospital")
                .draggable(false)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        Marker m5 = googleMap.addMarker(op5);
        m5.showInfoWindow();

        LatLng ar = new LatLng(25.5341190, 84.8531960);
        MarkerOptions op8 = new MarkerOptions();
        op8.position(ar).title("Anwesha Main Arena")
                .draggable(false)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        Marker m8 = googleMap.addMarker(op8);
        m8.showInfoWindow();

        LatLng tb = new LatLng(25.5325160, 84.8515800);
        MarkerOptions op9 = new MarkerOptions();
        op9.position(tb).title("Tutorial Block")
                .draggable(false)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        Marker m9 = googleMap.addMarker(op9);
        m9.showInfoWindow();


        LatLng g1 = new LatLng(25.533538, 84.855608);
        MarkerOptions op6 = new MarkerOptions();
        op6.position(g1).title("Gate no. 1")
                .draggable(false)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        Marker m6 = googleMap.addMarker(op6);
        m6.showInfoWindow();

        LatLng g2 = new LatLng(25.554782, 84.857496);
        MarkerOptions op7 = new MarkerOptions();
        op7.position(g2).title("Gate no. 2")
                .draggable(false)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        Marker m7 = googleMap.addMarker(op7);
        m7.showInfoWindow();
    }

    private void drawMarker(LatLng point, String info) {
        // Creating an instance of MarkerOptions
        MarkerOptions markerOptions = new MarkerOptions();

        // Setting latitude and longitude for the marker
        markerOptions.position(point).title(info.toString());

        // Adding marker on the Google MapActivity
        googleMap.addMarker(markerOptions);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int arg0,
                                         Bundle arg1) {

        // Uri to the content provider LocationsContentProvider
        Uri uri = LocationsContentProvider.CONTENT_URI;

        // Fetches all the rows from locations table
        return new CursorLoader(this, uri, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> arg0,
                               Cursor arg1) {
        int locationCount = 0;
        double lat = 0;
        double lng = 0;
        String info = "";

        // Number of locations available in the SQLite database table
        locationCount = arg1.getCount();
        if (locationCount > 0) {
            // Move the current record pointer to the first row of the table
            arg1.moveToFirst();
        }

        for (int i = 0; i < locationCount; i++) {

            // Get the latitude
            lat = arg1.getDouble(arg1.getColumnIndex(LocationsDB.FIELD_LAT));

            // Get the longitude
            lng = arg1.getDouble(arg1.getColumnIndex(LocationsDB.FIELD_LNG));

            // Get the zoom level
            info = arg1.getString(arg1.getColumnIndex(LocationsDB.FIELD_INFO)).toString();

            // Creating an instance of LatLng to plot the location in Google Maps
            LatLng location = new LatLng(lat, lng);

            // Drawing the marker in the Google Maps
            drawMarker(location, info);

            // Traverse the pointer to the next row
            arg1.moveToNext();
        }

        if (locationCount > 0) {
            // Moving CameraPosition to last clicked position
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(lat, lng)));

            // Setting the zoom level in the map on last position  is clicked
            googleMap.animateCamera(CameraUpdateFactory.zoomTo(15));
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onMapClick(LatLng arg0) {
        // TODO Auto-generated method stub
        x = arg0.latitude;
        y = arg0.longitude;
        final AlertDialog alert2 = new AlertDialog.Builder(MapActivity.this).create();
        AlertDialog alert = new AlertDialog.Builder(MapActivity.this).create();
        alert.setTitle("Anwesha");
        alert.setButton(AlertDialog.BUTTON_POSITIVE, "Toggle View", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                if (flag == 1) {
                    googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                    flag = 0;
                } else {
                    googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                    flag = 1;
                }
            }
        });
        alert.setButton(AlertDialog.BUTTON_NEGATIVE, "Place a pinpoint", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                flag2 = 1;
                ///Toast.makeText(getApplicationContext(), "Abhishek", Toast.LENGTH_SHORT).show();
                alert2.show();
                dialog.dismiss();
            }
        });

        alert.show();

//if(flag2==1){
//	flag2=0;

        alert2.setTitle("Marker details :");
        final EditText input = new EditText(MapActivity.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        alert2.setView(input);
        alert2.setButton(AlertDialog.BUTTON_POSITIVE, "Add", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub

                MarkerOptions op = new MarkerOptions();
                op.position(new LatLng(x, y)).title(input.getText().toString());
                googleMap.addMarker(op);
                ContentValues contentValues = new ContentValues();

                // Setting latitude in ContentValues
                contentValues.put(LocationsDB.FIELD_LAT, x);

                // Setting longitude in ContentValues
                contentValues.put(LocationsDB.FIELD_LNG, y);

                // Setting zoom in ContentValues
                contentValues.put(LocationsDB.FIELD_INFO, input.getText().toString());

                // Creating an instance of LocationInsertTask
                LocationInsertTask insertTask = new LocationInsertTask();

                // Storing the latitude, longitude and zoom level to SQLite database
                insertTask.execute(contentValues);
            }
        });
        alert2.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                dialog.dismiss();
            }
        });
    }

    private class LocationInsertTask extends AsyncTask<ContentValues, Void, Void> {
        @Override
        protected Void doInBackground(ContentValues... contentValues) {

            /** Setting up values to insert the clicked location into SQLite database */
            getContentResolver().insert(LocationsContentProvider.CONTENT_URI, contentValues[0]);
            return null;
        }
    }

    private class LocationDeleteTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {

            /** Deleting all the locations stored in SQLite database */
            getContentResolver().delete(LocationsContentProvider.CONTENT_URI, null, null);
            return null;
        }
    }
}
