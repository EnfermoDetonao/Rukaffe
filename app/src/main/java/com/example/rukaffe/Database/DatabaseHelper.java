package com.example.rukaffe.Database;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;

import com.example.rukaffe.Util.Constants;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static DatabaseHelper databaseHelper;

    // All Static variables
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = Constants.DATABASE_NAME;

    // Constructor
    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized DatabaseHelper getInstance(Context context) {
        if (databaseHelper == null) {
            databaseHelper = new DatabaseHelper(context);
        }
        return databaseHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Create tables SQL execution
        String CREATE_INVENTORY_TABLE = "CREATE TABLE " + Constants.TABLE_INVENTORY + "("
                + Constants.INVENTORY_NAME + " TEXT NOT NULL, "
                + Constants.INVENTORY_CANTIDAD + " INTEGER NOT NULL, "
                + Constants.INVENTORY_FECHA+ " TEXT NOT NULL "
                + ")";

        db.execSQL(CREATE_INVENTORY_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_INVENTORY);

        // Create tables again
        onCreate(db);
    }
}