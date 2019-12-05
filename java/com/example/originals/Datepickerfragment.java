package com.example.originals;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;


import java.util.Calendar;


public class Datepickerfragment extends DialogFragment {
    int year,month,day;


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle  savedInstanceState)
    {final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(),(DatePickerDialog.OnDateSetListener) getActivity(),year,month,day);
    }
}
