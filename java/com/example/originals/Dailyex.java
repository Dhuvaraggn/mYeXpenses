package com.example.originals;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;

import android.os.Bundle;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Locale;

public class Dailyex extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    Databaseex dbex3;
    EditText datefix;
    TextView tf, ts, tp, te, tl, tg, tt;
    Button del;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dailyex_main);
        dbex3 = new Databaseex(this);
        tf = findViewById(R.id.dfood);
        ts = findViewById(R.id.dsundry);
        tg = findViewById(R.id.dgroce);
        tp = findViewById(R.id.dpetrol);
        tl = findViewById(R.id.dloan);
        te = findViewById(R.id.deduca);
        tt = findViewById(R.id.dtotal);
        datefix = findViewById(R.id.ddate);
        del=findViewById(R.id.delbut);
        final Intent intenthome = new Intent(this, MainActivity.class);
        FloatingActionButton fab2 = findViewById(R.id.fab2);

        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(intenthome);


            }
        });
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ondelClick();
            }
        });

        final String date = new SimpleDateFormat("ddMMyyyy", Locale.getDefault()).format(new Date());

        datefix.setText(date);
        outputdata(datefix.getText().toString());


        datefix.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                DialogFragment datepicker = new Datepickerfragment();
                datepicker.show(getSupportFragmentManager(), "date picker");

            }


        });
        datefix.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                outputdata1(String.valueOf(s));
            }


            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
    public void ondelClick()
    {
        DialogFragment delpicker=new DeletePicker();
        delpicker.show(getSupportFragmentManager(),"del picker");
    }
    public void outputdata(String datef) {

        final Cursor output = dbex3.outdatedata(String.valueOf(datef));
        if (output.getCount() == 0) {
            Toast.makeText(Dailyex.this, "No Record of Expensed", Toast.LENGTH_SHORT).show();
        } else {
            if (output.moveToFirst()) {
                tf.setText(output.getString(1));
                tg.setText(output.getString(2));
                ts.setText(output.getString(3));
                te.setText(output.getString(4));
                tp.setText(output.getString(5));
                tl.setText(output.getString(6));
                tt.setText(output.getString(7));
            }
        }
    }

    public void outputdata1(String datef) {

        final Cursor output = dbex3.outdatedata1(String.valueOf(datef));
        if (output.getCount() == 0) {
            Toast.makeText(Dailyex.this, "No Record", Toast.LENGTH_SHORT).show();
        } else {
            if (output.moveToFirst()) {
                tf.setText(output.getString(1));
                tg.setText(output.getString(2));
                ts.setText(output.getString(3));
                te.setText(output.getString(4));
                tp.setText(output.getString(5));
                tl.setText(output.getString(6));
                tt.setText(output.getString(7));
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        // Show current date
        if ((month >= 0) && (month <= 8)) {
            if ((dayOfMonth >= 1) && (dayOfMonth <= 9)) {
                datefix.setText(new StringBuilder()
                        .append(0).append(dayOfMonth).append(0).append(month + 1)
                        .append(year).append(" "));
            } else {
                datefix.setText(new StringBuilder()
                        .append(dayOfMonth).append(0).append(month + 1)
                        .append(year).append(" "));
            }
        } else {
            if ((dayOfMonth >= 1) && (dayOfMonth <= 9)) {
                datefix.setText(new StringBuilder()
                        .append(0).append(dayOfMonth).append(month + 1)
                        .append(year).append(" "));

            } else {
                datefix.setText(new StringBuilder()
                        .append(dayOfMonth).append(month + 1)
                        .append(year).append(" "));

            }
        }
    }
}