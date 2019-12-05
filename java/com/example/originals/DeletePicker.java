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


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class DeletePicker extends AppCompatDialogFragment {

    Dailyex dp;
    Databaseex dbx;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        dbx=new Databaseex(dp);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.delselect, null);


        builder.setView(view);
        builder.setTitle("Delete today record you expensed?");
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i)
            {
                final String date = new SimpleDateFormat("ddMMyyyy", Locale.getDefault()).format(new Date());
                dbx.deldatedata1(date);

            }
        });
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });




        return builder.create();
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            dp = (Dailyex) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "fkjsfh");
        }
    }



}
