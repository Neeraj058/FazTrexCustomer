package com.faztrex.customer.customviews;

import android.app.DatePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.DatePicker;

import com.faztrex.customer.R;
import com.faztrex.customer.utils.AppConstants;
import com.faztrex.customer.utils.AppUtils;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class MyDatePicker implements DatePickerDialog.OnDateSetListener {
    private final String TAG = this.getClass().getSimpleName();
    private final Context mContext;
    private final String dateFormat;
    private TextInputEditText textInputEditText;
    private TextInputLayout textInputLayout;
    private Calendar calendar;
    private SimpleDateFormat simpleDateFormat;
    private String selectedDate = "";
    private boolean isOnlyFutureDates;
    private int futureDateWithLimitedDays;

    public MyDatePicker(TextInputEditText textInputEditText, String dateFormat) {
        this.textInputEditText = textInputEditText;
        this.dateFormat = dateFormat;
        mContext = textInputEditText.getContext();
        showPicker(this.textInputEditText);
    }

    public MyDatePicker(TextInputEditText textInputEditText, String dateFormat, boolean isOnlyFutureDates) {
        this.textInputEditText = textInputEditText;
        this.dateFormat = dateFormat;
        this.isOnlyFutureDates = isOnlyFutureDates;
        mContext = textInputEditText.getContext();
        showPicker(this.textInputEditText);
    }

    public MyDatePicker(TextInputLayout textInputLayout, String dateFormat) {
        this.textInputLayout = textInputLayout;
        this.dateFormat = dateFormat;
        mContext = textInputLayout.getContext();
        showPicker(this.textInputLayout);
    }

    public MyDatePicker(TextInputLayout textInputLayout, String dateFormat, boolean isOnlyFutureDates) {
        this.textInputLayout = textInputLayout;
        this.dateFormat = dateFormat;
        this.isOnlyFutureDates = isOnlyFutureDates;
        mContext = textInputLayout.getContext();
        showPicker(this.textInputLayout);
    }

    public MyDatePicker(TextInputLayout textInputLayout, String dateFormat, boolean isOnlyFutureDates, int futureDateWithLimitedDays) {
        this.textInputLayout = textInputLayout;
        this.dateFormat = dateFormat;
        this.futureDateWithLimitedDays = futureDateWithLimitedDays;
        this.isOnlyFutureDates = isOnlyFutureDates;
        mContext = textInputLayout.getContext();
        showPicker(this.textInputLayout);
    }

    private void showPicker(View view) {
        if (calendar == null)
            calendar = Calendar.getInstance(TimeZone.getDefault());
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(mContext, R.style.CustomCalendar, this, year, month, dayOfMonth);
        DatePicker datePicker = datePickerDialog.getDatePicker();
        if (isOnlyFutureDates) {
            datePicker.setMinDate(new Date().getTime());
        }
        if (futureDateWithLimitedDays > 0) {
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth + futureDateWithLimitedDays);
            datePicker.setMaxDate(calendar.getTimeInMillis());
        }
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
        selectedDate = AppUtils.convertDateFormat(AppConstants.CALENDAR_DATE_FORMAT, dateFormat, selectedDate);
        if (textInputEditText != null)
            textInputEditText.setText(selectedDate);
        else if (textInputLayout != null)
            textInputLayout.getEditText().setText(selectedDate);
    }
}