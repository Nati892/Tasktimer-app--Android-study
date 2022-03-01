package com.learning.tasktimer;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.util.Log;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * provider for the tasks timer app
 * this is the only task that knows about {@link AppDatabase}
 */

public class AppProvider extends ContentProvider
{
    private static final String TAG = "AppProvider";
    private AppDatabase mOpenHelper;
    private static final UriMatcher sUriMatcher = builUriMatcher();
    public static final String CONTENT_AUTHORITY = "com.learning.tasktimer.provider";
    public static final Uri CONTENT_AUTHORITY_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    private static final int TASKS = 100;
    private static final int TASKS_ID = 101;
    private static final int TIMINGS = 200;
    private static final int TIMINGS_ID = 201;

    /*
     * private static final int TASK_TIMINGS=300;
     * private static final int TASK_TIMINGS_ID=301;
     */
    private static final int TASK_DURATION = 400;
    private static final int TASK_DURATION_ID = 401;

    private static UriMatcher builUriMatcher()
    {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        //content://com.learning.Tasktimer.provider/Tasks
        matcher.addURI(CONTENT_AUTHORITY, TaskContract.TABLE_NAME, TASKS);
        //content://com.learning.Tasktimer.provider/Tasks/8
        matcher.addURI(CONTENT_AUTHORITY, TaskContract.TABLE_NAME + "/#", TASKS_ID);

//        matcher.addURI("CONTENT_AUTHORITY", TimingsContract.TABLE_NAME, TIMINGS);
//
//        matcher.addURI("CONTENT_AUTHORITY", TimingsContract.TABLE_NAME + "/#", TIMINGS_ID);
//
//        matcher.addURI("CONTENT_AUTHORITY", DurationsContract.TABLE_NAME, TASK_DURATION);
//
//        matcher.addURI("CONTENT_AUTHORITY", DurationsContract.TABLE_NAME + "/#", TASK_DURATION_ID);

        return matcher;

    }

    @Override
    public boolean onCreate()
    {
        mOpenHelper = AppDatabase.getInstance(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder)
    {
        Log.d(TAG, "query: called with uri " + uri);
        final int match = sUriMatcher.match(uri);
        Log.d(TAG, "query: match is " + match);

        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        switch (match)
        {
            case TASKS:
                queryBuilder.setTables(TaskContract.TABLE_NAME);
                break;
            case TASKS_ID:
                queryBuilder.setTables(TaskContract.TABLE_NAME);
                long taskId = TaskContract.getTaskId(uri);
                queryBuilder.appendWhere(TaskContract.Columns._ID + "=" + taskId);
                break;
//
//            case TIMINGS:
//                queryBuilder.setTables(TimingsContract.TABLE_NAME);
//                break;
//            case TIMINGS_ID:
//                queryBuilder.setTables(TimingsContract.TABLE_NAME);
//                long timingId=TimingsContract.getTimingId(uri);
//                queryBuilder.appendWhere(TimingsContract.Columns._ID + "=" +timingId);
//                break;
//
//            case TASK_DURATION:
//                queryBuilder.setTables(DurationsContract.TABLE_NAME);
//                break;
//            case TASK_DURATION_ID:
//                queryBuilder.setTables(DurationsContract.TABLE_NAME);
//                long durationId=DurationsContract.getDurationId(uri);
//                queryBuilder.appendWhere(DurationsContract.Columns._ID + "=" +durationId);
//                break;

            default:
                throw new IllegalArgumentException("unkown uri");
        }
        SQLiteDatabase db = mOpenHelper.getReadableDatabase();
        Cursor cursor = queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri)
    {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues)
    {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings)
    {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings)
    {
        return 0;
    }
}
