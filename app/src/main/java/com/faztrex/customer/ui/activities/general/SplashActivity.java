package com.faztrex.customer.ui.activities.general;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.faztrex.customer.R;
import com.faztrex.customer.baseclasses.BaseActivity;
import com.faztrex.customer.ui.activities.home.HomeActivity;
import com.faztrex.customer.ui.activities.userauth.LoginActivity;
import com.faztrex.customer.utils.AppConstants;
import com.faztrex.customer.utils.AppPreference;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends BaseActivity {

    private final String TAG = this.getClass().getSimpleName();

    private final Context mContext = this;

    @Override
    public Activity setCurrentActivity() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        boolean isLogin = AppPreference.getInstance().getBooleanPreference(mContext, mContext.getResources().getString(R.string.pref_is_login));

        if (isLogin) {

            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            finish();

        } else {

            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {

                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();

                }
            }, AppConstants.SPLASH_TIME);
        }


    }

}