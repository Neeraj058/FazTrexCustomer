package com.faztrex.customer.helper;

import android.app.Activity;
import android.content.Context;
import android.content.IntentSender;
import android.location.LocationListener;
import android.location.LocationManager;

import androidx.annotation.NonNull;

import com.faztrex.customer.listeners.general.GPSLocationListener;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import static com.cittasolutions.cittalibrary.utils.AppUtils.errorLog;
import static com.faztrex.customer.utils.AppConstants.REQUEST_CODE_GPS_ENABLE;

public class GPSManager {

    private final Activity activity;
    private final SettingsClient mSettingsClient;
    private LocationSettingsRequest locationSettingsRequest;
    private final LocationManager locationManager;
    private LocationRequest locationRequest;
    private LocationListener locationListener;

    public GPSManager(Activity activity) {
        this.activity = activity;
        locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
        mSettingsClient = LocationServices.getSettingsClient(activity);
    }

    public void start(GPSLocationListener gpsLocationListener) {

        LocationRequest locationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(1000)
                .setFastestInterval(1000 / 2);

        LocationSettingsRequest.Builder settingsBuilder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);

        settingsBuilder.setAlwaysShow(true);

        Task<LocationSettingsResponse> result = LocationServices.getSettingsClient(activity)
                .checkLocationSettings(settingsBuilder.build());

        result.addOnCompleteListener(new OnCompleteListener<LocationSettingsResponse>() {
            @Override
            public void onComplete(@NonNull Task<LocationSettingsResponse> task) {

                try {

                    LocationSettingsResponse response = task.getResult(ApiException.class);

                    response.getLocationSettingsStates();

                    if (gpsLocationListener != null)
                        gpsLocationListener.onGPSAlreadyEnabled();

                } catch (ApiException e) {

                    switch (e.getStatusCode()) {
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                            try {
                                ResolvableApiException resolvableApiException = (ResolvableApiException) e;
                                resolvableApiException.startResolutionForResult(activity, REQUEST_CODE_GPS_ENABLE);
                            } catch (IntentSender.SendIntentException ex) {
                                errorLog(ex);
                            }
                            break;

                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                            break;
                    }
                    errorLog(e);
                }
            }
        });
    }
    
    public interface onGpsListener {
        void gpsStatus(boolean isGPSEnable);
    }
}

