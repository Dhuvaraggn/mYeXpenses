package com.example.originals;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;

import java.util.Calendar;


public class Monthpicker extends AppCompatDialogFragment {
    private NumberPicker monthPicked, yearPicked;
   Monthex mp;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.monthselect, null);

        Calendar cal = Calendar.getInstance();

        builder.setView(view);
        builder.setTitle("select month");
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i)
            {
                String  month = String.valueOf(monthPicked.getValue());
                String year = String.valueOf(yearPicked.getValue());
               // mp.applyTexts(month, year);
                int m=Integer.parseInt(month);
                if((m>=1)&&(m<=9))
                {
                    mp.applytexts(0+month+year);
                }
                else
                {
                    mp.applytexts(month+year);
                }
            }
        });
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        monthPicked = view.findViewById(R.id.picker_month);
        yearPicked = view.findViewById(R.id.picker_year);
        monthPicked.setMinValue(1);
        monthPicked.setMaxValue(12);
        monthPicked.setValue(cal.get(Calendar.MONTH)+1);

        int year = cal.get(Calendar.YEAR);
        yearPicked.setMinValue(year);
        yearPicked.setMaxValue(2020);
        yearPicked.setValue(year);

        return builder.create();
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mp = (Monthex) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "fkjsfh");
        }
    }


}