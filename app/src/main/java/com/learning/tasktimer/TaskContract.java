package com.learning.tasktimer;

import static com.learning.tasktimer.AppProvider.CONTENT_AUTHORITY;
import static com.learning.tasktimer.AppProvider.CONTENT_AUTHORITY_URI;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

public class TaskContract
{
    static final String TABLE_NAME = "Tasks";


    public static class Columns
    {
        public static final String _ID = BaseColumns._ID;
        public static final String TASKS_NAME = "Name";
        public static final String TASKS_DESCRIPTION = "Description";
        public static final String TASKS_SORT_ORDER = "SortOrder";


        public Columns()
        {
        }


    }


    public static final Uri CONTETNT_URI = Uri.withAppendedPath(CONTENT_AUTHORITY_URI, TABLE_NAME);

    static  final String CONTENT_TYPE ="vnd.android.cursor.dir/vnd" + CONTENT_AUTHORITY + "."+TABLE_NAME;
    static  final String CONTENT_ITEM_TYPE ="vnd.android.cursor.item/vnd" + CONTENT_AUTHORITY + "."+TABLE_NAME;

    static Uri buildTaskUri(long taskId)
    {
        return ContentUris.withAppendedId(CONTETNT_URI, taskId);

    }

    static long getTaskId(Uri uri)
    {
        return ContentUris.parseId(uri);
    }
}
