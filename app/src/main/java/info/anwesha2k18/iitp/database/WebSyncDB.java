package info.anwesha2k18.iitp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mayank on 26/11/17.
 */

public class WebSyncDB extends SQLiteOpenHelper {

    static final String EVENT_ID = "id";
    static final String EVENT_NAME = "name";
    static final String EVENT_fee = "fee";
    static final String EVENT_day = "day";
    static final String EVENT_size = "size";
    static final String EVENT_code = "code";
    static final String EVENT_tagline = "tagline";
    static final String EVENT_date = "date";
    static final String EVENT_time = "time";
    static final String EVENT_venue = "venue";
    static final String EVENT_organisers = "organisers";
    static final String EVENT_short_desc = "short_desc";
    static final String EVENT_long_desc = "long_desc";
    static final String EVENT_image_url = "cover_url";
    static final String EVENT_rules_url = "rules_url";
    static final String EVENT_reg_url = "reg_url";
    private static final String TABLE_EVENT = "Event";
    private static String DBNAME = "websyncdb";
    private static int VERSION = 4;
    private SQLiteDatabase mDB;

    public WebSyncDB(Context context) {
        super(context, DBNAME, null, VERSION);
        this.mDB = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table " + TABLE_EVENT + " ( " +
                EVENT_ID + " int(3), " +
                EVENT_NAME + " varchar(35), " +
                EVENT_fee + " int(4), " +
                EVENT_day + " int(1), " +
                EVENT_size + " int(2), " +
                EVENT_code + " int(2), " +
                EVENT_tagline + " varchar(300), "+
                EVENT_date + " varchar(20), " +
                EVENT_time + " varchar(20), "+
                EVENT_venue + " varchar(30), "+
                EVENT_organisers + " varchar(100), "+
                EVENT_short_desc + " varchar(1000), "+
                EVENT_long_desc + " varchar(1000), " +
                EVENT_image_url + " varchar(1000), " +
                EVENT_rules_url + " varchar(1000), " +
                EVENT_reg_url + " varchar(1000) " +
                ")";
        db.execSQL(sql);

    }

    public long insertEvent(ContentValues[] contentValues) {
        mDB.delete(TABLE_EVENT, null, null);
        for (ContentValues cv : contentValues)
            mDB.insert(TABLE_EVENT, null, cv);
        return contentValues.length;
    }

    public int clearEvent() {
        return mDB.delete(TABLE_EVENT, null, null);
    }

    public Cursor getAllEvents() {
        return mDB.query(TABLE_EVENT, new String[]{EVENT_ID, EVENT_NAME, EVENT_fee, EVENT_day, EVENT_size, EVENT_code, EVENT_tagline, EVENT_date, EVENT_time, EVENT_venue, EVENT_organisers, EVENT_short_desc, EVENT_long_desc, EVENT_image_url, EVENT_rules_url, EVENT_reg_url}, null, null, null, null, null);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENT);

        // Create tables again
        onCreate(db);
    }
}
