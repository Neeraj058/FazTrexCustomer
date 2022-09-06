package com.faztrex.customer.adapters.general;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.databinding.BindingAdapter;

import com.faztrex.customer.utils.AppConstants;
import com.faztrex.customer.utils.AppUtils;


public class ViewBinding {

    @BindingAdapter("convertDateFormat")
    public static void convertDateFormat(AppCompatTextView textView, String inputString) {
        textView.setText(AppUtils.convertDateFormat(AppConstants.API_DATE_FORMAT, AppConstants.DISPLAY_DATE_FORMAT_1, inputString));
    }

    @BindingAdapter("convertLongDateFormat")
    public static void convertLongDateFormat(AppCompatTextView textView, String inputString) {
        textView.setText(AppUtils.convertDateFormat(AppConstants.API_DATE_FORMAT, AppConstants.DISPLAY_DATE_FORMAT_3, inputString));
    }

    @BindingAdapter(value = {"convertToFormattedString", "formatPattern"})
    public static void convertToFormattedString(AppCompatTextView textView, String inputString, String formatPattern) {
        textView.setText(AppUtils.getFormattedString(inputString, formatPattern));
    }

    @BindingAdapter("checknullstring")
    public static void checknullstring(AppCompatTextView textView, String inputString) {
        if (inputString == null || inputString.equals("")) {
            textView.setText("-");
        } else {
            textView.setText(inputString)
            ;
        }
    }
}
