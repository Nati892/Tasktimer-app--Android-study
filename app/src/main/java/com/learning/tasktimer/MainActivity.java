package com.learning.tasktimer;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.learning.tasktimer.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity
{
    private static final String TAG = "MainActivity";
    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        ContentResolver contentResolver = getContentResolver();

//        ContentValues values=new ContentValues();
//                values.put(TaskContract.Columns.TASKS_NAME,"CONTENTsfs PROVIDER");
//        values.put(TaskContract.Columns.TASKS_DESCRIPTION,"USaa");
//        int count =contentResolver.update(TaskContract.buildTaskUri(6),values,null,null);

//        AppDatabase appDatabase = AppDatabase.getInstance(this);
//        final SQLiteDatabase db = appDatabase.getReadableDatabase();


//        Log.d(TAG, "onCreate: ******->"+TaskContract.buildTaskUri(6)+"<-********");
//       int count =contentResolver.delete(TaskContract.buildTaskUri(2),null,null);
//        Log.d(TAG, "onCreate: deleted "+ count +" rows");


        for (int j = 0; j < 20; j++)
        {
            ContentValues values = new ContentValues();
            values.put(TaskContract.Columns.TASKS_NAME, "record " + j);
            values.put(TaskContract.Columns.TASKS_DESCRIPTION, "Last " + j + " was brutally murdered");
//        values.put(TaskContract.Columns.TASKS_SORT_ORDER, 2);
            contentResolver.insert(TaskContract.CONTETNT_URI, values);
        }
//        ContentValues values = new ContentValues();
//        values.put(TaskContract.Columns.TASKS_NAME, "SECOND record");
//        values.put(TaskContract.Columns.TASKS_DESCRIPTION, "Last was brutally murdered");
//        values.put(TaskContract.Columns.TASKS_SORT_ORDER, 2);
//        contentResolver.insert(TaskContract.CONTETNT_URI,values);


        String[] projection = {
                TaskContract.Columns._ID,
                TaskContract.Columns.TASKS_NAME,
                TaskContract.Columns.TASKS_DESCRIPTION,
                TaskContract.Columns.TASKS_SORT_ORDER};

        Cursor cursor = contentResolver.query(TaskContract.CONTETNT_URI,
                //      Cursor cursor = contentResolver.query(TaskContract.buildTaskUri(2),
                projection,
                null,
                null
                , TaskContract.Columns.TASKS_NAME);

        if (cursor != null)
        {
            Log.d(TAG, "onCreate: number of rows i n curser " + cursor.getCount());
        }
        while (cursor.moveToNext())
        {
            for (int i = 0; i < cursor.getColumnCount(); i++)
            {
                Log.d(TAG, "onCreate: " + cursor.getColumnName(i) + ": " + cursor.getString(i));
                Log.d(TAG, "onCreate: ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            }

        }
        cursor.close();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp()
    {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}