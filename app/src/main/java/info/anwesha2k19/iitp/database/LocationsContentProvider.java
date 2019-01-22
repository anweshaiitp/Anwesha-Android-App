package info.anwesha2k19.iitp.database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.net.Uri;

/**
 * Created by mayank on 10/1/18.
 */

public class LocationsContentProvider extends ContentProvider {

    public static final String PROVIDER_NAME = "in.ac.iitp.anwesha2k17.locations";
    public static final Uri CONTENT_URI = Uri.parse("content://" + PROVIDER_NAME + "/locations");
    private static final int LOCATIONS = 1;
    private static final UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "locations", LOCATIONS);
    }

    LocationsDB mLocationsDB;

    public boolean onCreate() {
        mLocationsDB = new LocationsDB(getContext());
        return true;
    }

    public Uri insert(Uri uri, ContentValues values) {
        long rowID = mLocationsDB.insert(values);
        Uri _uri = null;
        if (rowID > 0) {
            _uri = ContentUris.withAppendedId(CONTENT_URI, rowID);
        } else {
            try {
                throw new SQLException("Failed to insert : " + uri);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return _uri;
        }
        return _uri;
    }

    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int cnt = 0;
        cnt = mLocationsDB.del();
        return cnt;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        if (uriMatcher.match(uri) == LOCATIONS) {
            return mLocationsDB.getAllLocations();
        }
        return null;
    }

    public String getType(Uri uri) {
        return null;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO Auto-generated method stub
        return 0;
    }
}
