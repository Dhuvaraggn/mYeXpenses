package com.example.originals;

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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Locale;

public class Monthex extends AppCompatActivity/* implements Monthpicker.MonthpickerListener*/ {
    Databaseex dbex4;
    TextView fd,gd,sd,ed,pd,ld,td;
    EditText monthfix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.monthex_main);
        dbex4=new Databaseex(this);
        fd=findViewById(R.id.mfood);
        gd=findViewById(R.id.mgroce);
        sd=findViewById(R.id.msundry);
        ed=findViewById(R.id.meduca);
        pd=findViewById(R.id.mpetrol);
        ld=findViewById(R.id.mloan);
        td=findViewById(R.id.mtotal);
        monthfix=findViewById(R.id.mdate);



        final Intent intenthome=new Intent(this,MainActivity.class);

        FloatingActionButton fab3= findViewById(R.id.fab3);
        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(intenthome);


            }
        });

            final String month = new SimpleDateFormat("MMyyyy", Locale.getDefault()).format(new Date());
        monthfix.setText(String.valueOf(month));
       final Cursor output = dbex4.outmonthdata(String.valueOf(monthfix.getText()));
       output.moveToFirst();
        int fm=0,gm=0,sm=0,em=0,pm=0,lm=0,tm=0;
        if (output.getCount() == 0) {
            Toast.makeText(Monthex.this, "No Record of Expensed", Toast.LENGTH_LONG).show();
        }
        else {

            do {
                fm += Integer.parseInt(output.getString(1));
                gm += Integer.parseInt(output.getString(2));
                sm += Integer.parseInt(output.getString(3));
                em += Integer.parseInt(output.getString(4));
                pm += Integer.parseInt(output.getString(5));
                lm += Integer.parseInt(output.getString(6));
                tm += Integer.parseInt(output.getString(7));
            } while (output.moveToNext());
        }
        output.close();
        fd.setText(String.valueOf(fm));
        gd.setText(String.valueOf(gm));
        sd.setText(String.valueOf(sm));
        ed.setText(String.valueOf(em));
        pd.setText(String.valueOf(pm));
        ld.setText(String.valueOf(lm));
        td.setText(String.valueOf(tm));

        monthfix.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    DialogFragment mpicker=new Monthpicker();
                    mpicker.show(getSupportFragmentManager(),"month picker");
                }
            });





        monthfix.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    int fm=0,gm=0,sm=0,em=0,pm=0,lm=0,tm=0;
                    final Cursor output = dbex4.outmonthdata(String.valueOf(s));
                    output.moveToFirst();
                    if (output.getCount() == 0) {
                        Toast.makeText(Monthex.this, "No Record of Expensed", Toast.LENGTH_LONG).show();
                    }
                    else {
                        do{
                            fm += Integer.parseInt(output.getString(1));
                            gm += Integer.parseInt(output.getString(2));
                            sm += Integer.parseInt(output.getString(3));
                            em += Integer.parseInt(output.getString(4));
                            pm += Integer.parseInt(output.getString(5));
                            lm += Integer.parseInt(output.getString(6));
                            tm += Integer.parseInt(output.getString(7));

                        }while(output.moveToNext());
                        fd.setText(String.valueOf(fm));
                        gd.setText(String.valueOf(gm));
                        sd.setText(String.valueOf(sm));
                        ed.setText(String.valueOf(em));
                        pd.setText(String.valueOf(pm));
                        ld.setText(String.valueOf(lm));
                        td.setText(String.valueOf(tm));
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {


                }

            });


    }

    public void applytexts(String month) {

        monthfix.setText(month);

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






}

