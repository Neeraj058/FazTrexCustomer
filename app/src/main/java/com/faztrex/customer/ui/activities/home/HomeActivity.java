package com.faztrex.customer.ui.activities.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.graphics.drawable.DrawerArrowDrawable;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.faztrex.customer.R;
import com.faztrex.customer.baseclasses.BaseActivity;
import com.faztrex.customer.databinding.ActivityHomeBinding;
import com.faztrex.customer.ui.activities.notifications.PushNotifications;
import com.faztrex.customer.ui.fragments.DrawerFragment;
import com.faztrex.customer.ui.fragments.HomeFragment;
import com.google.firebase.messaging.FirebaseMessaging;

public class HomeActivity extends BaseActivity {

    private final String TAG = this.getClass().getSimpleName();

    private final Context mContext = this;

    private ActivityHomeBinding binding;

    private boolean doubleBackToExitPressedOnce = false;

    private DrawerFragment drawerFragment;

    private HomeFragment homeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);

        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(task -> {

            if (!task.isSuccessful())
                return;

            String token = task.getResult();
            appPreference.setStringPreference(mContext, "token", token);
            printDebugLog("Firebase TOKEN", token);
        });

        setUpNavigationDrawer();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public Activity setCurrentActivity() {
        return this;
    }

    private void setUpNavigationDrawer() {

        try {

            binding.tvNavigationHeaderTitle.setText(getResources().getString(R.string.nav_header_title_home));

            setSupportActionBar(binding.toolbar);

            binding.toolbar.setNavigationIcon(new DrawerArrowDrawable(mContext));


            binding.toolbar.setNavigationOnClickListener(view -> {

                if (binding.drawerLayout.isDrawerOpen(binding.navigationViewContainer))
                    binding.drawerLayout.closeDrawer(binding.navigationViewContainer);
                else
                    binding.drawerLayout.openDrawer(binding.navigationViewContainer);
            });

            //homeBinding.drawerLayout.setScrimColor(Color.TRANSPARENT);
            binding.drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {

                @Override
                public void onDrawerSlide(View drawerView, float slideOffset) {

                    // Scale the View based on current slide offset
                    /*final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                    final float offsetScale = 1 - diffScaledOffset;
                    binding.holder.setScaleX(offsetScale);
                    binding.holder.setScaleY(offsetScale);*/

                    // Translate the View, accounting for the scaled width
                    /*final float xOffset = drawerView.getWidth() * slideOffset;
                    final float xOffsetDiff = binding.holder.getWidth() * diffScaledOffset / 2;
                    final float xTranslation = xOffset - xOffsetDiff;
                    binding.holder.setTranslationX(xTranslation);*/
                }

                @Override
                public void onDrawerOpened(View drawerView) {
                    hideSoftKeyboard();
                }

                @Override
                public void onDrawerClosed(View drawerView) {
                    //binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                }
            });


            doYourWork();

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    private void doYourWork() {

        try {

            drawerFragment = new DrawerFragment();
            homeFragment = new HomeFragment();

            setFragment(drawerFragment, TAG, R.id.navigation_view_container);
            setFragment(homeFragment, TAG, R.id.content);

            /*boolean isFirstLogin = AppPreference.getInstance().getBooleanPreference(mContext, mContext.getResources().getString(R.string.pref_is_first_login));

            if (!isFirstLogin)
                authenticateUser();
            else
                AppPreference.getInstance().setBooleanPreference(mContext, mContext.getResources().getString(R.string.pref_is_first_login), false);*/

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }

        binding.ivNotification.setOnClickListener(v -> startActivity(new Intent(mContext, PushNotifications.class)));
    }

    public boolean closeDrawer() {

        try {

            if (binding.drawerLayout.isDrawerOpen(binding.navigationViewContainer)) {
                binding.drawerLayout.closeDrawer(binding.navigationViewContainer);
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public void onBackPressed() {

        if (closeDrawer()) {

            Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.content);

            if (currentFragment instanceof HomeFragment) {

                if (!doubleBackToExitPressedOnce) {

                    this.doubleBackToExitPressedOnce = true;
                    displayShortToast(mContext, getResources().getString(R.string.msg_back_pressed));

                    new Handler().postDelayed(() -> doubleBackToExitPressedOnce = false, 1000);

                } else
                    finishAffinity();

            } else
                super.onBackPressed();
        }
    }

}