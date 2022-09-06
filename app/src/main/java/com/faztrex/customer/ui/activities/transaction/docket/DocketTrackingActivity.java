package com.faztrex.customer.ui.activities.transaction.docket;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.faztrex.customer.R;
import com.faztrex.customer.adapters.DocketTrackingAdapter;
import com.faztrex.customer.baseclasses.BaseActivity;
import com.faztrex.customer.databinding.ActivityDocketTrackingBinding;
import com.faztrex.customer.network.model.request.docket.DocketRequestModel;
import com.faztrex.customer.network.response.CommonResponse;
import com.faztrex.customer.network.response.docket.tracking.DocketTracking;
import com.faztrex.customer.network.response.docket.tracking.TrackingInfo;
import com.faztrex.customer.retrofit.ApiClient;
import com.faztrex.customer.retrofit.ApiConstant;
import com.faztrex.customer.retrofit.ApiListener;
import com.faztrex.customer.retrofit.ApiManager;
import com.faztrex.customer.retrofit.ApiService;
import com.faztrex.customer.ui.fragments.bottomsheet.DocketDetailsBottomSheet;
import com.faztrex.customer.utils.AppConstants;
import com.faztrex.customer.utils.AppValidation;
import com.faztrex.customer.utils.NetworkUtils;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;

import static com.faztrex.customer.utils.AppUtils.getStringValue;
import static com.faztrex.customer.utils.AppUtils.isEmptyString;


public class DocketTrackingActivity extends BaseActivity implements ApiListener {

    private final String TAG = this.getClass().getSimpleName();

    private Context mContext = this;

    private ActivityDocketTrackingBinding binding;

    private ArrayList<DocketTracking> docketTracking;

    private ArrayList<TrackingInfo> trackingInfoList;

    @Override
    public Activity setCurrentActivity() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_docket_tracking);

        // initialize toolbar
        initToolbar(binding.toolbarMain, getResources().getString(R.string.nav_header_title_docket_tracking));

        disableView(binding.layoutCommonList.swipeRefreshLayout);

        // call method to start operational flow
        doYourWork();
    }

    private void doYourWork() {

        try {

            // call method to get preference values
            getPreferenceData();

            binding.btnTrack.setOnClickListener(v -> {

                if (AppValidation.getInstance().validateString(mContext, binding.tnlDocketNo, "Enter Docket No.")) {

                    // call method to get tracking data from server for docket number
                    getTrackingInformation();
                }
            });

            binding.btnViewDetails.setOnClickListener(v -> {

                DocketDetailsBottomSheet docketDetailsBottomSheet = new DocketDetailsBottomSheet(docketTracking.get(0));
                docketDetailsBottomSheet.setCancelable(false);
                docketDetailsBottomSheet.show(getSupportFragmentManager(), docketDetailsBottomSheet.getTag());
            });

            binding.btnViewPod.setOnClickListener(v -> {

                String podPath = getStringValue(docketTracking.get(0).getDeliveryInfo().get(0).getDeliveryDetails().get(0).getPodUrl());

                if (!isEmptyString(podPath))
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(podPath)));
            });

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    // region get docket tracking information request

    /*
     * Method to get docket tracking information from server
     *
     *
     * */
    private void getTrackingInformation() {

        try {

            if (NetworkUtils.isConnected(mContext)) {

                // start progress indicator
                BaseActivity.startProgressDialog(this, false);

                // prepare request body
                DocketRequestModel.DocketTrackingRequest trackingRequest = new DocketRequestModel().new DocketTrackingRequest();

                trackingRequest.setDocketNo(getTrimString(binding.tnlDocketNo));
                trackingRequest.setConsignorId(prefUserModel.getId());

                printInfoLog(TAG, new Gson().toJson(trackingRequest));

                ApiService apiService = ApiClient.createService(ApiService.class, "", "");
                Call<CommonResponse<ArrayList<DocketTracking>>> call = apiService.getDocketTrackingDetail(trackingRequest);

                // call API
                ApiManager.callRetrofit(call, ApiConstant.API_DOCKET_TRACKING, this, false);

            } else {
                displayInternetToastMessage(mContext);
            }

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    // endregion

    @Override
    public void onApiSuccess(String endPointName, Object responseBody) {
        super.onApiSuccess(endPointName, responseBody);

        switch (endPointName) {

            case ApiConstant.API_DOCKET_TRACKING:

                CommonResponse<ArrayList<DocketTracking>> docketTrackingResponse = (CommonResponse<ArrayList<DocketTracking>>) responseBody;
                processDocketTrackingResponse(docketTrackingResponse);
                break;
        }
    }

    @Override
    public void onApiError(String endPointName, String errorMessage) {
        super.onApiError(endPointName, errorMessage);

        printErrorLog(TAG, errorMessage);

        switch (endPointName) {

            case ApiConstant.API_DOCKET_TRACKING:

                hideView(binding.btnViewDetails);
                hideView(binding.btnViewPod);
                hideView(binding.layoutCommonList.recyclerView);
                break;
        }
    }

    @Override
    public void onApiFailure(String endPointName, String failureMessage) {
        super.onApiFailure(endPointName, failureMessage);

        printErrorLog(TAG, failureMessage);

        switch (endPointName) {

            case ApiConstant.API_DOCKET_TRACKING:

                hideView(binding.btnViewDetails);
                hideView(binding.btnViewPod);
                hideView(binding.layoutCommonList.recyclerView);
                break;
        }
    }

    /*
     * Method to process docket tracking response
     *
     *
     * */
    private void processDocketTrackingResponse(CommonResponse<ArrayList<DocketTracking>> response) {

        try {

            if (response != null) {

                switch (response.getStatus()) {

                    case AppConstants.STATUS_SUCCESS:

                        docketTracking = response.getData();

                        visibleView(binding.btnViewDetails);
                        visibleView(binding.layoutCommonList.recyclerView);

                        if (response.getData().get(0).getDeliveryInfo() != null && response.getData().get(0).getDeliveryInfo().size() > 0) {
                            visibleView(binding.btnViewPod);
                        } else {
                            hideView(binding.btnViewPod);
                        }

                        setTrackingActivity(response);
                        break;

                    case AppConstants.STATUS_FAILURE:

                        printErrorLog(TAG, response.getMessage());
                        displayShortToast(mContext, "Docket not exist");

                        hideView(binding.btnViewDetails);
                        hideView(binding.btnViewPod);
                        hideView(binding.layoutCommonList.recyclerView);
                        break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    private void setTrackingActivity(CommonResponse<ArrayList<DocketTracking>> response) {

        try {

            trackingInfoList = response.getData().get(0).getTrackingInfo();

            if (trackingInfoList != null && trackingInfoList.size() > 0) {

                LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext);
                binding.layoutCommonList.recyclerView.setLayoutManager(mLayoutManager);
                binding.layoutCommonList.recyclerView.setItemAnimator(new DefaultItemAnimator());

                DocketTrackingAdapter docketTrackingAdapter = new DocketTrackingAdapter(mContext, trackingInfoList);
                binding.layoutCommonList.recyclerView.setAdapter(docketTrackingAdapter);

                visibleView(binding.layoutCommonList.recyclerView);
                hideView(binding.layoutCommonList.layoutNoData.ivNoDataFound);

          } else {

                hideView(binding.layoutCommonList.recyclerView);
                visibleView(binding.layoutCommonList.layoutNoData.ivNoDataFound);
            }

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }
}
