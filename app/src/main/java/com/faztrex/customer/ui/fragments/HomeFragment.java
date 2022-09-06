package com.faztrex.customer.ui.fragments;

import static com.cittasolutions.cittalibrary.utils.AppUtils.errorLog;
import static com.faztrex.customer.retrofit.ApiConstant.API_DASHBOARD_COUNT_NEW;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.faztrex.customer.R;
import com.faztrex.customer.baseclasses.BaseFragment;
import com.faztrex.customer.databinding.FragmentHomeBinding;
import com.faztrex.customer.models.general.DashboardCount;
import com.faztrex.customer.network.model.request.CommanRequestModel;
import com.faztrex.customer.network.response.CommonResponse;
import com.faztrex.customer.retrofit.ApiClient;
import com.faztrex.customer.retrofit.ApiConstant;
import com.faztrex.customer.retrofit.ApiListener;
import com.faztrex.customer.retrofit.ApiManager;
import com.faztrex.customer.retrofit.ApiService;
import com.faztrex.customer.utils.AppConfig;
import com.faztrex.customer.utils.AppConstants;
import com.faztrex.customer.utils.AppUtils;
import com.faztrex.customer.utils.NetworkUtils;
import com.google.gson.Gson;

import retrofit2.Call;

public class HomeFragment extends BaseFragment implements ApiListener {

    private final String TAG = this.getClass().getSimpleName();

    private FragmentHomeBinding fragmentHomeBinding;

    private boolean isSwipeRefresh;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);

        getDashboardCounts();

        fragmentHomeBinding.swipeRefreshLayout.setOnRefreshListener(() -> {
            isSwipeRefresh = true;
            fragmentHomeBinding.swipeRefreshLayout.setRefreshing(isSwipeRefresh);
            getDashboardCounts();

        });

        return fragmentHomeBinding.getRoot();
    }

    private void getDashboardCounts() {

        try {

            if (NetworkUtils.isConnected(getActivity())) {

                getPreferenceData();

                CommanRequestModel request = new CommanRequestModel();
                request.setCustomerid(prefUserModel.getId());

                String requestString = new Gson().toJson(request);

                ApiService apiService = ApiClient.createService(ApiService.class, "", "");

                Call<CommonResponse<DashboardCount>> call = apiService.getDashboardCounts(request);

                ApiManager.callRetrofit(call, API_DASHBOARD_COUNT_NEW, this, false);

            } else
                displayInternetToastMessage(getActivity());

        } catch (Exception e) {
            e.printStackTrace();
            errorLog(e);
        }
    }

    @Override
    public void onApiSuccess(String endPointName, Object responseBody) {

        switch (endPointName) {

            case API_DASHBOARD_COUNT_NEW:
                CommonResponse<DashboardCount> dashboardCountApiResponse = (CommonResponse<DashboardCount>) responseBody;
                processDashboardApiResponse(dashboardCountApiResponse);
                break;
        }
    }

    @SuppressLint("SetTextI18n")
    private void processDashboardApiResponse(CommonResponse<DashboardCount> response) {

        String s= new Gson().toJson(response);
        Log.d("Dashboard", s+ "");
        try {

            if (isSwipeRefresh) {
                isSwipeRefresh = false;
                fragmentHomeBinding.swipeRefreshLayout.setRefreshing(isSwipeRefresh);
            }

            if (response != null) {
                DashboardCount dashboardCount = response.getData();
                fragmentHomeBinding.tvDashboardValue1.setText(AppUtils.getFormattedString(dashboardCount.getPickUpRequestCount(), AppConstants.FORMAT_2_F));
                fragmentHomeBinding.tvDashboardValue3.setText(AppUtils.getFormattedString(dashboardCount.getDocketBookingCount(), AppConstants.FORMAT_2_F));
                fragmentHomeBinding.tvDashboardValue4.setText(AppUtils.getFormattedString(dashboardCount.getTotalBills(), AppConstants.FORMAT_2_F));
                fragmentHomeBinding.tvDashboardValue5.setText(AppUtils.getFormattedString(dashboardCount.getOutstandingAmount(), AppConstants.FORMAT_2_F));
            } else {
                fragmentHomeBinding.tvDashboardValue1.setText("0.00");
                fragmentHomeBinding.tvDashboardValue3.setText("0.00");
                fragmentHomeBinding.tvDashboardValue4.setText("0.00");
                fragmentHomeBinding.tvDashboardValue5.setText("0.00");
            }

        } catch (Exception e) {
            e.printStackTrace();
            errorLog(e);
            if (isSwipeRefresh) {
                isSwipeRefresh = false;
                fragmentHomeBinding.swipeRefreshLayout.setRefreshing(isSwipeRefresh);
            }
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onApiError(String endPointName, String errorMessage) {

        if (isSwipeRefresh) {
            isSwipeRefresh = false;
            fragmentHomeBinding.swipeRefreshLayout.setRefreshing(isSwipeRefresh);
        }

        switch (endPointName) {

            case API_DASHBOARD_COUNT_NEW:
                displayLongToast(getActivity(), errorMessage);
                fragmentHomeBinding.tvDashboardValue1.setText("0.00");
                fragmentHomeBinding.tvDashboardValue3.setText("0.00");
                fragmentHomeBinding.tvDashboardValue4.setText("0.00");
                fragmentHomeBinding.tvDashboardValue5.setText("0.00");
                break;
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onApiFailure(String endPointName, String failureMessage) {

        if (isSwipeRefresh) {
            isSwipeRefresh = false;
            fragmentHomeBinding.swipeRefreshLayout.setRefreshing(isSwipeRefresh);
        }

        switch (endPointName) {

            case API_DASHBOARD_COUNT_NEW:
                displayLongToast(getActivity(), "Something went wrong!");
                fragmentHomeBinding.tvDashboardValue1.setText("0.00");
                fragmentHomeBinding.tvDashboardValue3.setText("0.00");
                fragmentHomeBinding.tvDashboardValue4.setText("0.00");
                fragmentHomeBinding.tvDashboardValue5.setText("0.00");
                break;
        }
    }
}