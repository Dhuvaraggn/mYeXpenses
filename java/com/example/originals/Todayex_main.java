package com.example.originals;

import android.content.Intent;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;

import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Button;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


import static android.widget.Toast.makeText;

public class Todayex_main extends AppCompatActivity {
    Databaseex dbex2;
    EditText fd,gd,sd,ed,pd,ld;
    Button sub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        dbex2=new Databaseex(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todayex_main);

        fd=findViewById(R.id.tfood);
        gd=findViewById(R.id.tgroce);
        sd=findViewById(R.id.tsundry);
        ed=findViewById(R.id.teduca);
        pd=findViewById(R.id.tpetrol);
        ld=findViewById(R.id.tloan);
        sub=findViewById(R.id.submit);

        final Intent intenthome=new Intent(this,MainActivity.class);

        FloatingActionButton fab1 = findViewById(R.id.fab1);


        fab1.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View view) {

                                       startActivity(intenthome);

                                   }
                               });
        adddata();
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
    void adddata()
    {      final Intent intentdaily = new Intent(this, Dailyex.class);
        sub.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            makeText(Todayex_main.this, "Expensed is clicked", Toast.LENGTH_SHORT).show();
            int total = Integer.parseInt(fd.getText().toString()) + Integer.parseInt(gd.getText().toString()) + Integer.parseInt(sd.getText().toString()) + Integer.parseInt(ed.getText().toString())
                    + Integer.parseInt(pd.getText().toString()) + Integer.parseInt(ld.getText().toString());

            final String date = new SimpleDateFormat("ddMMyyyy",Locale.getDefault()).format(new Date());
            final String month = new SimpleDateFormat("MMyyyy",Locale.getDefault()).format(new Date());
         makeText(Todayex_main.this,date,Toast.LENGTH_SHORT).show();

            boolean bres = dbex2.insertdata(date,Integer.parseInt(fd.getText().toString()), Integer.parseInt(gd.getText().toString()), Integer.parseInt(sd.getText().toString()), Integer.parseInt(ed.getText().toString())
                    , Integer.parseInt(pd.getText().toString()), Integer.parseInt(ld.getText().toString()), total,month);


            if (bres)
            { Toast.makeText(Todayex_main.this, "Expensed", Toast.LENGTH_SHORT).show();

            startActivity(intentdaily);
        }
            else
                Toast.makeText(Todayex_main.this,"Already Expensed", Toast.LENGTH_SHORT).show();}
    });


    }



}


