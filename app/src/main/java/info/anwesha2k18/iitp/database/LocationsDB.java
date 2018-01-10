package info.anwesha2k18.iitp.database;

/**
 * Created by mayank on 10/1/18.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LocationsDB extends SQLiteOpenHelper {

    public static final String FIELD_ROW_ID = "_id";
    public static final String FIELD_LAT = "lat";
    public static final String FIELD_LNG = "lng";
    public static final String FIELD_INFO = "info";
    private static final String DATABASE_TABLE = "locations";
    private static String DBNAME = "locationmarkersqlite";
    private static int VERSION = 1;
    private SQLiteDatabase mDB;

    public LocationsDB(Context context) {
        super(context, DBNAME, null, VERSION);
        this.mDB = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table " + DATABASE_TABLE + " ( " +
                FIELD_ROW_ID + " integer primary key autoincrement , " +
                FIELD_LNG + " double , " +
                FIELD_LAT + " double , " +
                FIELD_INFO + " text " +
                " ) ";
        db.execSQL(sql);
        ;
    }

    public long insert(ContentValues contentValues) {
        long rowID = mDB.insert(DATABASE_TABLE, null, contentValues);
        return rowID;
    }

    public int del() {
        int cnt = mDB.delete(DATABASE_TABLE, null, null);
        return cnt;
    }

    public Cursor getAllLocations() {
        return mDB.query(DATABASE_TABLE, new String[]{FIELD_ROW_ID, FIELD_LAT, FIELD_LNG, FIELD_INFO}, null, null, null, null, null);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
