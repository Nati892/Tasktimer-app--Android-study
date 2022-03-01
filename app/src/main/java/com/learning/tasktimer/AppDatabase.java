package com.learning.tasktimer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

class AppDatabase extends SQLiteOpenHelper
{
    private static final String TAG = "AppDatabase";
    public static final String DATA_BASE_NAME = "TaskTimer.db";
    public static final int DATA_BASE_VERSION = 1;
    //the singleton object
    private static AppDatabase instance = null;

    private AppDatabase(Context context)
    {
        super(context, DATA_BASE_NAME, null, DATA_BASE_VERSION);
        Log.d(TAG, "AppDatabase: constructor");
    }


    /**
     * get instance of the app's singleton database helper object
     *
     * @param context the context providers context
     * @return a SQlite database helper object
     * the only one that should use this class is {@link  AppProvider}
     */
    static AppDatabase getInstance(Context context)
    {
        if (instance == null)
        {
            Log.d(TAG, "getInstance: creating new instance");
            instance = new AppDatabase(context);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        Log.d(TAG, "onCreate: starts");
        String sSQL;
//        sSQL = "CREATE TABLE Tasks (_id INTEGER PRIMARY KEY NOT NULL, Name TEXT NOT NULL, Description TEXT, SortOrder INTEGER);";
        sSQL = "CREATE TABLE " + TaskContract.TABLE_NAME + "(" +
                TaskContract.Columns._ID + "  INTEGER PRIMARY KEY NOT NULL , " +
                TaskContract.Columns.TASKS_NAME + " TEXT NOT NULL , " +
                TaskContract.Columns.TASKS_DESCRIPTION + " TEXT ," +
                TaskContract.Columns.TASKS_SORT_ORDER + " INTEGER);";
        Log.d(TAG, "sSQL->" + sSQL);
        db.execSQL(sSQL);
        Log.d(TAG, "onCreate: ends");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion)
    {
        Log.d(TAG, "onUpgrade: starts");
        switch (oldVersion)
        {
            case 1:
                break;
            default:
                throw new IllegalStateException("onUpgrade() with unknown  newVersion: " + newVersion);


        }
        Log.d(TAG, "onUpgrade: ends");
    }
}
