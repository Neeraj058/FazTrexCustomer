package com.faztrex.customer.baseclasses;

import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.faztrex.customer.R;
import com.faztrex.customer.customviews.CustomDialog;
import com.faztrex.customer.customviews.SimpleAlertDialog;
import com.faztrex.customer.network.response.user.User;
import com.faztrex.customer.utils.AppPreference;
import com.google.gson.Gson;

public class BaseFragment extends Fragment {

    public static Fragment currentFragment;
    private final String TAG = this.getClass().getSimpleName();
    public SimpleAlertDialog simpleAlertDialog = null;
    public CustomDialog customDialog = null;
    public View view;
    public boolean prefIsLogin;

    public User prefUserModel;

    /*
     * Method to display short toast message
     *
     * */
    public void displayShortToast(Context context, String displayMessage) {
        Toast.makeText(context, displayMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // For FileURIExposedException Handling
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        // To Prevent NetworkOnMainThread Exception
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

    }

    public void getPreferenceData() {

        try {

            // get data from preference
            String userDataJson = AppPreference.getInstance().getStringPreference(requireActivity(), getActivity().getResources().getString(R.string.pref_user_data));
            User user = new Gson().fromJson(userDataJson, User.class);

            prefIsLogin = AppPreference.getInstance().getBooleanPreference(requireActivity(), getResources().getString(R.string.pref_is_login));

            if (user != null) {
                prefUserModel = user;
            }

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    /*
     * Method to display long toast message
     *
     * */
    public void displayLongToast(Context context, String displayMessage) {
        Toast.makeText(context, displayMessage, Toast.LENGTH_LONG).show();
    }

    /*
     * Method to display no internet toast messages
     *
     * */
    public void displayInternetToastMessage(Context context) {
        Toast.makeText(context, getResources().getString(R.string.msg_no_internet), Toast.LENGTH_SHORT).show();
    }

    public void printErrorLog(String tag, String msg) {
        Log.e(tag, msg);
    }

    public void printInfoLog(String tag, String msg) {
        Log.i(tag, msg);
    }

    public void printVerboseLog(String tag, String msg) {
        Log.v(tag, msg);
    }

    public void printDebugLog(String tag, String msg) {
        Log.d(tag, msg);
    }

}
