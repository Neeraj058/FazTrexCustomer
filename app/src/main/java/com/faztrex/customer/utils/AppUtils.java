package com.faztrex.customer.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;

public class AppUtils {

    private static final String TAG = AppUtils.class.getSimpleName();

    public static boolean isEmptyString(String inputString) {
        return TextUtils.isEmpty(inputString);
    }

    public static String convertDateFormat(String inputDateFormat, String outputDateFormat, String inputDate) {

        try {

            if (!isEmptyString(inputDate)) {

                SimpleDateFormat sdf = new SimpleDateFormat(inputDateFormat, Locale.US);
                SimpleDateFormat output = new SimpleDateFormat(outputDateFormat, Locale.US);
                Date d2 = sdf.parse(inputDate);
                return output.format(d2);
            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, e.getLocalizedMessage());
        }

        return "";
    }

    public static String getCurrentDate(String outputDateFormat) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getDefault());
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat(outputDateFormat);
        return simpleDateFormat.format(calendar.getTime());
    }


    public static String getFormattedString(String inputString, String formatPattern) {
        return String.format(Locale.ENGLISH, formatPattern, castToDouble(inputString));
    }

    public static int castToInteger(String inputString) {
        return (!isEmptyString(inputString) && inputString != null && !inputString.equalsIgnoreCase("null")) ? Integer.parseInt(inputString) : 0;
    }

    public static double castToDouble(String inputString) {
        return (!isEmptyString(inputString) && inputString != null && !inputString.equalsIgnoreCase("null")) ? Double.parseDouble(inputString) : 0.0;
    }

    public static String getStringValue(String inputString) {
        return (!isEmptyString(inputString) && inputString != null && !inputString.equalsIgnoreCase("null")) ? inputString : "";
    }

    public static String getTrimString(TextInputLayout inputLayout) {
        return Objects.requireNonNull(inputLayout.getEditText()).getText().toString().trim();
    }


    public static String convertToUpperCase(String inputString) {

        StringBuilder convertedString = new StringBuilder();

        try {

            if (!isEmptyString(inputString)) {

                inputString = inputString.toLowerCase();

                if (inputString.contains(" ")) {

                    String[] separateString = inputString.split(" ");

                    if (separateString.length > 0) {

                        for (String s : separateString) {

                            String capString = s.substring(0, 1).toUpperCase() + s.substring(1);
                            convertedString.append(capString).append(" ");
                        }
                    }

                } else {
                    convertedString.append(inputString.substring(0, 1).toUpperCase()).append(inputString.substring(1)).append(" ");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, e.getLocalizedMessage());
        }

        return String.valueOf(convertedString).trim();
    }

    //region Created By Jigar
    /*
     * Method to download file from the server
     *
     * */
    public static void downloadFileFromServer(Activity mActivity, String downloadFilePath, String fileName) {

        try {

            //downloadFilePath = "https://www.sample-videos.com/zip/50mb.zip";

            if (ActivityCompat.checkSelfPermission(mActivity, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.checkSelfPermission(mActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                // this will request for permission when user has not granted permission for the app
                ActivityCompat.requestPermissions(mActivity, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

            } else {

                DownloadManager downloadManager = (DownloadManager) mActivity.getSystemService(Context.DOWNLOAD_SERVICE);
                Uri uri = Uri.parse(downloadFilePath);
                DownloadManager.Request request = new DownloadManager.Request(uri);
                request.setTitle(fileName);
                request.setDescription("");
                request.setVisibleInDownloadsUi(true);
                request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, uri.getLastPathSegment());
                downloadManager.enqueue(request);

                Toast.makeText(mActivity, "Downloading...", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, e.getLocalizedMessage());
        }
    }
    //endregion

}
