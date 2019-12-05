package com.example.originals;

import android.app.AlarmManager;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
Databaseex dbex4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbex4 = new Databaseex(this);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.activity_main);

            }
        });
        final String date = new SimpleDateFormat("ddMMyyyy", Locale.getDefault()).format(new Date());
        final Cursor cur = dbex4.outdatedata(date);
        if (!cur.moveToFirst()) {

            Intent intent = new Intent(getApplicationContext(), Notification.class);
            PendingIntent pintent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, 0);
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(System.currentTimeMillis());
            cal.set(Calendar.HOUR_OF_DAY, 21);
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pintent);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    public void onclicktoday(View v)
    {
        final Intent intent=new Intent(this,Todayex_main.class);
        startActivity(intent);
    }
    public void onclickdaily(View v){ final Intent intent=new Intent(this,Dailyex.class);
        startActivity(intent);}
    public void onclickmonth(View v)
    {final Intent intent=new Intent(this,Monthex.class);
    startActivity(intent);}
    public void ondelClick()
    {
        DialogFragment delpicker=new DeletePicker();
        delpicker.show(getSupportFragmentManager(),"del picker");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
