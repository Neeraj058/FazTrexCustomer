package com.faztrex.customer.ui.activities.docketbooking;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.cittasolutions.cittalibrary.utils.AppConstant;
import com.faztrex.customer.R;
import com.faztrex.customer.baseclasses.BaseActivity;
import com.faztrex.customer.baseclasses.BaseAdapter;
import com.faztrex.customer.customviews.MyDatePicker;
import com.faztrex.customer.databinding.CommanListLayoutBinding;
import com.faztrex.customer.databinding.CommonFilterLayoutBinding;
import com.faztrex.customer.listeners.bottomsheet.MasterBottomSheetActionListener;
import com.faztrex.customer.listeners.general.CommonActionListener;
import com.faztrex.customer.network.model.request.CommanRequestModel;
import com.faztrex.customer.network.response.CommonListResponse;
import com.faztrex.customer.network.response.CommonResponse;
import com.faztrex.customer.network.response.docket.DocketListResponseModel;
import com.faztrex.customer.retrofit.ApiClient;
import com.faztrex.customer.retrofit.ApiConstant;
import com.faztrex.customer.retrofit.ApiListener;
import com.faztrex.customer.retrofit.ApiManager;
import com.faztrex.customer.retrofit.ApiService;
import com.faztrex.customer.ui.activities.transaction.docket.DocketBookingActivity;
import com.faztrex.customer.ui.fragments.bottomsheet.BottomSheetDocketBookingRating;
import com.faztrex.customer.utils.AppConstants;
import com.faztrex.customer.utils.AppUtils;
import com.faztrex.customer.utils.NetworkUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;

import static com.faztrex.customer.retrofit.ApiConstant.API_DOCKET_RATING;
import static com.faztrex.customer.utils.AppConstants.SP_CITY;
import static com.faztrex.customer.utils.AppConstants.SP_DISPATCH_MODE;
import static com.faztrex.customer.utils.AppConstants.SP_PAYMENT_TYPE4;
import static com.faztrex.customer.utils.AppConstants.SP_STATE;
import static com.faztrex.customer.utils.AppConstants.SP_STATE2;

public class DocketBookingListActivity extends BaseActivity implements
        View.OnClickListener, CommonActionListener, ApiListener, MasterBottomSheetActionListener,
        BottomSheetDocketBookingRating.RatingClick {

    private CommonFilterLayoutBinding commonFilterLayoutBinding;

    private final Context mContext = this;

    private final String TAG = this.getClass().getSimpleName();

    private final int REQUEST_CODE_DOCKET = 1001;

    private CommanListLayoutBinding binding;

    private int pageCount = 0, pastVisibleItems, visibleItemCount, totalItemCount = 0, totalItems = 0;

    private boolean loading = true, isSwipeRefresh;

    private String docketID;

    private BaseAdapter baseAdapter;

    private Integer dispatchModeIdFilter;

    private Integer paymentTypeIdFilter;

    private Integer originIdFilter;

    private Integer destinationStateIdFilter;

    private BottomSheetDocketBookingRating bottomSheetDocketBookingRating;

    private Integer destinationIdFilter;

    private String fromDate, toDate;

    private ArrayList<DocketListResponseModel> docketListResponseModelArrayList = new ArrayList<>();

    private Map<String, Integer> inputParams;

    @Override
    public Activity setCurrentActivity() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.comman_list_layout);

        binding.toolbar.tvHeaderTitle.setText(R.string.nav_header_title_docket_booking);
        binding.toolbar.ivBack.setOnClickListener(v -> finish());

        visibleView(binding.toolbar.ivSearch);
        visibleView(binding.toolbar.ivFilter);

        hideView(binding.fabAdd);

        binding.toolbar.ivSearch.setOnClickListener(this);
        binding.toolbar.ivFilter.setOnClickListener(this);
        binding.toolbar.ivClearEditext.setOnClickListener(this);

        inputParams = new HashMap<>();

        binding.toolbar.edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });

        getSpinnerList(AppConstants.SP_STATE, null);
        getSpinnerList(AppConstants.SP_STATE2, null);
        getSpinnerList(AppConstants.SP_PAYMENT_TYPE4, null);
        getSpinnerList(AppConstants.SP_DISPATCH_MODE, null);

        makeDocketListApiCall();

        View headerView = binding.filterNavigationDrawer.getHeaderView(0);
        commonFilterLayoutBinding = CommonFilterLayoutBinding.bind(headerView);
        binding.filterNavigationDrawer.setVerticalScrollBarEnabled(true);

        float offset = .200f * getResources().getDisplayMetrics().widthPixels;
        float width = getResources().getDisplayMetrics().widthPixels - offset;
        DrawerLayout.LayoutParams params = (DrawerLayout.LayoutParams) binding.filterNavigationDrawer.getLayoutParams();
        params.width = (int) width;
        binding.filterNavigationDrawer.setLayoutParams(params);

        commonFilterLayoutBinding.ivDrawerClose.setOnClickListener(v -> closeDrawer(binding.drawerLayout));

        commonFilterLayoutBinding.edtDispatchModeFilter.setOnClickListener(this);
        commonFilterLayoutBinding.edtPaymentTypeFilter.setOnClickListener(this);
        commonFilterLayoutBinding.edtOriginFilter.setOnClickListener(this);
        commonFilterLayoutBinding.edtDestinationStateFilter.setOnClickListener(this);
        commonFilterLayoutBinding.edtDestinationFilter.setOnClickListener(this);

        commonFilterLayoutBinding.edtFromDate.setOnClickListener(v -> new MyDatePicker(commonFilterLayoutBinding.tnlFromDate, AppConstants.CALENDAR_DATE_FORMAT, false));

        commonFilterLayoutBinding.edtToDate.setOnClickListener(v -> new MyDatePicker(commonFilterLayoutBinding.tnlToDate, AppConstants.CALENDAR_DATE_FORMAT, false));

        commonFilterLayoutBinding.btnClear.setOnClickListener(v -> {
            clearAllFilterControl();
            getDocketList();
            closeDrawer(binding.drawerLayout);
        });

        commonFilterLayoutBinding.btnApply.setOnClickListener(v -> {
            fromDate = AppUtils.convertDateFormat(AppConstant.CALENDAR_DATE_FORMAT, AppConstant.YYYY_MM_DD, getTrimString(commonFilterLayoutBinding.tnlFromDate));
            toDate = AppUtils.convertDateFormat(AppConstant.CALENDAR_DATE_FORMAT, AppConstant.YYYY_MM_DD, getTrimString(commonFilterLayoutBinding.tnlToDate));
            getDocketList();
            closeDrawer(binding.drawerLayout);
        });
    }

    private void clearAllFilterControl() {

        Objects.requireNonNull(commonFilterLayoutBinding.edtToDate.getText()).clear();
        Objects.requireNonNull(commonFilterLayoutBinding.edtFromDate.getText()).clear();
        Objects.requireNonNull(commonFilterLayoutBinding.edtDispatchModeFilter.getText()).clear();
        Objects.requireNonNull(commonFilterLayoutBinding.edtPaymentTypeFilter.getText()).clear();
        Objects.requireNonNull(commonFilterLayoutBinding.edtOriginFilter.getText()).clear();
        Objects.requireNonNull(commonFilterLayoutBinding.edtDestinationStateFilter.getText()).clear();
        Objects.requireNonNull(commonFilterLayoutBinding.edtDestinationFilter.getText()).clear();

        originIdFilter = null;
        dispatchModeIdFilter = null;
        paymentTypeIdFilter = null;
        destinationIdFilter = null;
        destinationStateIdFilter = null;
        fromDate = null;
        toDate = null;

    }

    private void makeDocketListApiCall() {

        try {

            getPreferenceData();

            LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext);
            binding.rvCommanListLayout.setLayoutManager(mLayoutManager);
            binding.rvCommanListLayout.setItemAnimator(new DefaultItemAnimator());

            baseAdapter = new BaseAdapter(mContext, docketListResponseModelArrayList, R.layout.item_docket_list, this);
            binding.rvCommanListLayout.setAdapter(baseAdapter);

            getDocketList();

            /*binding.rvCommanListLayout.addOnScrollListener(new RecyclerView.OnScrollListener() {

                @Override
                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {

                    if (dy > 0) {

                        visibleItemCount = mLayoutManager.getChildCount();
                        totalItemCount = mLayoutManager.getItemCount();
                        pastVisibleItems = mLayoutManager.findFirstVisibleItemPosition();

                        loading = totalItems < totalItemCount;

                        if (loading) {

                            if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {

                                totalItems = totalItemCount;
                                loading = false;

                                // increment page index
                                pageCount = pageCount + AppConstants.DISPLAY_RECORD_COUNT;

                                // call method to get docket list
                                //getDocketList();
                            }
                        }
                    }
                }
            });*/

            binding.swipeRefreshLayout.setOnRefreshListener(() -> {
                isSwipeRefresh = true;
                refreshData();
            });

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    private void refreshData() {

        pageCount = 0;
        pastVisibleItems = 0;
        visibleItemCount = 0;
        totalItemCount = 0;
        totalItems = 0;
        loading = true;

        fromDate = null;
        toDate = null;
        dispatchModeIdFilter = null;
        paymentTypeIdFilter = null;
        originIdFilter = null;
        destinationStateIdFilter = null;
        destinationIdFilter = null;

        docketListResponseModelArrayList = new ArrayList<>();
        baseAdapter = new BaseAdapter(mContext, docketListResponseModelArrayList, R.layout.item_docket_list, this);
        binding.rvCommanListLayout.setAdapter(baseAdapter);

        // call method to get docket list
        getDocketList();
    }

    private void getDocketList() {


        try {

            if (NetworkUtils.isConnected(mContext)) {

                if (isSwipeRefresh)
                    startSwipeLayout();
                else
                    startProgressDialog(this, false);

                CommanRequestModel commanRequestModel = new CommanRequestModel();
                /*pickUpListRequestModel.setCid(prefUserModel.getCid());
                pickUpListRequestModel.setBid(prefUserModel.getBid());*/
                commanRequestModel.setSortdir("0");
                commanRequestModel.setSortdir("ASC");
                commanRequestModel.setPageindex(String.valueOf(pageCount));
                commanRequestModel.setPagesize("100000");
                commanRequestModel.setKeyword("");
                commanRequestModel.setFromdate(fromDate);
                commanRequestModel.setTodate(toDate);

                commanRequestModel.setCustomerid(prefUserModel.getId());

                commanRequestModel.setDispatchmode(dispatchModeIdFilter);
                commanRequestModel.setPaymenttype(paymentTypeIdFilter);
                commanRequestModel.setOriginid(originIdFilter);

                commanRequestModel.setDestinationstateid(destinationStateIdFilter);
                commanRequestModel.setDestinationcityid(destinationIdFilter);

                String requestString = new Gson().toJson(commanRequestModel);
                Log.d(TAG, requestString);

                ApiService apiService = ApiClient.createService(ApiService.class, "", "");
                Call<CommonResponse<ArrayList<DocketListResponseModel>>> call = apiService.getDocketList(commanRequestModel);
                ApiManager.callRetrofit(call, ApiConstant.API_DOCKET_LIST, this, false);

            } else
                displayInternetToastMessage(mContext);
        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    @Override
    public void onApiSuccess(String endPointName, Object responseBody) {

        super.onApiSuccess(endPointName, responseBody);

        stopSwipeLayout();

        switch (endPointName) {

            case ApiConstant.API_DOCKET_LIST:

                CommonResponse<ArrayList<DocketListResponseModel>> docketResponse = (CommonResponse<ArrayList<DocketListResponseModel>>) responseBody;
                processResponse(docketResponse);
                break;

            case API_DOCKET_RATING:
                CommonResponse<String> docketRatingResponse = (CommonResponse<String>) responseBody;
                processDocketRatingResponse(docketRatingResponse);
                break;
        }
    }

    @Override
    public void onApiError(String endPointName, String errorMessage) {
        super.onApiError(endPointName, errorMessage);
        printErrorLog(TAG, errorMessage);

        displayLongToast(mContext, errorMessage);

        switch (endPointName) {

            case ApiConstant.API_DOCKET_LIST:
                stopSwipeLayout();
                showNoData();
                break;

            case API_DOCKET_RATING:
                stopProgressDialog();
                break;
        }
    }

    @Override
    public void onApiFailure(String endPointName, String failureMessage) {
        super.onApiFailure(endPointName, failureMessage);

        displayLongToast(mContext, failureMessage);

        switch (endPointName) {

            case ApiConstant.API_DOCKET_LIST:
                showNoData();
                break;

            case API_DOCKET_RATING:
                stopProgressDialog();
                break;
        }
    }

    private void processResponse(CommonResponse<ArrayList<DocketListResponseModel>> docketResponse) {

        try {

            switch (docketResponse.getStatus()) {

                case AppConstants.STATUS_SUCCESS:
                    bindDocketList(docketResponse);
                    break;

                case AppConstants.STATUS_FAILURE:
                    showNoData();
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    private void bindDocketList(CommonResponse<ArrayList<DocketListResponseModel>> docketResponse) {

        try {

            /*if (tempDocketList != null && tempDocketList.size() > 0)
                docketListResponseModelArrayList.addAll(tempDocketList);*/


            docketListResponseModelArrayList = docketResponse.getData();

            if (docketListResponseModelArrayList != null && docketListResponseModelArrayList.size() > 0) {

                //baseAdapter.notifyDataSetChanged();
                baseAdapter = new BaseAdapter(mContext, docketListResponseModelArrayList, R.layout.item_docket_list, this);
                binding.rvCommanListLayout.setAdapter(baseAdapter);

                visibleView(binding.rvCommanListLayout);
                hideView(binding.ivNoDataFound);

            } else {

                visibleView(binding.ivNoDataFound);
                hideView(binding.rvCommanListLayout);
            }

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    private void processDocketRatingResponse(CommonResponse<String> docketRatingResponse) {

        try {

            if (NetworkUtils.isConnected(mContext)) {

                startProgressDialog(this, false);

                if (docketRatingResponse != null) {

                    if (docketRatingResponse.getStatus().equalsIgnoreCase("1")) {
                        displayLongSnackbar(binding.getRoot().getRootView(), "" + docketRatingResponse.getMessage());
                        getDocketList();
                    } else {
                        displayLongSnackbar(binding.getRoot().getRootView(), getResources().getString(R.string.err_msg_api_response_failure));
                    }
                    stopProgressDialog();
                }

            } else {
                Toast.makeText(mContext, "Something went wrong!", Toast.LENGTH_SHORT).show();
                stopProgressDialog();
            }

            stopProgressDialog();

        } catch (Exception e) {
            stopProgressDialog();
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    private void showNoData() {
        visibleView(binding.ivNoDataFound);
        hideView(binding.rvCommanListLayout);
    }

    private void stopSwipeLayout() {

        if (isSwipeRefresh) {

            if (binding.swipeRefreshLayout.isRefreshing()) {
                binding.swipeRefreshLayout.setRefreshing(false);
                isSwipeRefresh = false;
            }
        }
    }

    private void startSwipeLayout() {
        binding.swipeRefreshLayout.post(() -> binding.swipeRefreshLayout.setRefreshing(true));
    }

    private void filter(String text) {

        ArrayList<DocketListResponseModel> filtered = new ArrayList<>();

        for (DocketListResponseModel s : docketListResponseModelArrayList) {
            if (
                    s.getDocketNo().toLowerCase().contains((text.toLowerCase()))
                            || s.getCustomerType().toLowerCase().contains((text.toLowerCase()))
                            || s.getDispatchMode().toLowerCase().contains((text.toLowerCase()))
                            || s.getPaymentType().toLowerCase().contains((text.toLowerCase()))
                           /*|| s.getBookingDate().toLowerCase().contains((text.toLowerCase()))*/
                            /*|| s.getOrigin().toLowerCase().contains((text.toLowerCase()))*/
                            || s.getDestination().toLowerCase().contains((text.toLowerCase()))

            ) {
                filtered.add(s);
            }
        }

       // baseAdapter = new BaseAdapter(mContext, docketListResponseModelArrayList, R.layout.item_docket_list, this,"filter");
        baseAdapter.filterList(filtered);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.iv_Search:
                Objects.requireNonNull(binding.toolbar.edtSearch.getText()).clear();
                hideVisibilityWithAnimation(binding.toolbar.toolbarLayout);
                showVisibilityWithAnimation(binding.toolbar.searchLayout);
                break;

            case R.id.edtDispatchModeFilter:
                showFilterMasterBottomSheet(Objects.requireNonNull(commonFilterLayoutBinding.edtDispatchModeFilter.getHint()).toString(), this, SP_DISPATCH_MODE);
                break;

            case R.id.edtPaymentTypeFilter:
                showFilterMasterBottomSheet(Objects.requireNonNull(commonFilterLayoutBinding.edtPaymentTypeFilter.getHint()).toString(), this, SP_PAYMENT_TYPE4);
                break;

            case R.id.edtOriginFilter:
                showFilterMasterBottomSheet(Objects.requireNonNull(commonFilterLayoutBinding.edtDestinationStateFilter.getHint()).toString(), this, SP_STATE2);
                break;

            case R.id.edtDestinationStateFilter:
                showFilterMasterBottomSheet(Objects.requireNonNull(commonFilterLayoutBinding.edtDestinationStateFilter.getHint()).toString(), this, SP_STATE);
                break;

            case R.id.edtDestinationFilter:
                showFilterMasterBottomSheet(Objects.requireNonNull(commonFilterLayoutBinding.edtDestinationFilter.getHint()).toString(), this, SP_CITY);
                break;

            case R.id.ivClearEditext:
                hideVisibilityWithAnimation(binding.toolbar.searchLayout);
                showVisibilityWithAnimation(binding.toolbar.toolbarLayout);

                if (binding.toolbar.edtSearch.length() != 0) {

                    baseAdapter = new BaseAdapter(mContext, docketListResponseModelArrayList, R.layout.item_docket_list, this);
                    binding.rvCommanListLayout.setAdapter(baseAdapter);

                }
                break;

            case R.id.iv_Filter:
                commonFilterLayoutBinding.lLayoutFilter.setVisibility(View.GONE);
                commonFilterLayoutBinding.lLayoutFilterDocketBooking.setVisibility(View.VISIBLE);
                openDrawer(binding.drawerLayout);
                break;
        }
    }

    private void navigateToBookingDetails(String docketId, boolean readOnly) {

        try {

            Intent mIntent = new Intent(mContext, DocketBookingActivity.class);
            mIntent.putExtra(mContext.getResources().getString(R.string.key_docket_id), docketId);
            mIntent.putExtra(mContext.getResources().getString(R.string.key_read_only), readOnly);
            mIntent.putExtra(mContext.getResources().getString(R.string.key_is_edit), true);
            startActivityForResult(mIntent, REQUEST_CODE_DOCKET);

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    @Override
    public void onViewClick(int itemPosition, Object object) {
        String id = docketListResponseModelArrayList.get(itemPosition).getId();
        navigateToBookingDetails(id, true);
    }

    @Override
    public void onInfoClick(int itemPosition, Object object, View... views) {
    }

    @Override
    public void onEditClick(int itemPosition, Object object) {
        DocketListResponseModel docket = (DocketListResponseModel) object;
        this.docketID = docket.getId();
        bottomSheetDocketBookingRating = new BottomSheetDocketBookingRating(this, docket);
        bottomSheetDocketBookingRating.show(getSupportFragmentManager(), "RattingBottomSheet");
    }

    @Override
    public void onDeleteClick(int itemPosition, Object object) {
    }

    @Override
    public void onMasterBottomSheetViewClick(int itemPosition, Object object, String edtID) {

        CommonListResponse commonListResponse = (CommonListResponse) object;

        switch (edtID) {

            case SP_STATE:
                commonFilterLayoutBinding.edtDestinationStateFilter.setText(commonListResponse.getName());
                destinationStateIdFilter = AppUtils.castToInteger(commonListResponse.getId());
                inputParams.put(AppConstants.KEY_STATE_ID, destinationStateIdFilter);
                getSpinnerList(AppConstants.SP_CITY, inputParams);
                Log.d(TAG, "onMasterBottomSheetViewClick: " + destinationStateIdFilter + "\n" + prefUserModel.getId());
                break;

            case SP_STATE2:
                commonFilterLayoutBinding.edtOriginFilter.setText(commonListResponse.getName());
                originIdFilter = AppUtils.castToInteger(commonListResponse.getId());

            case SP_DISPATCH_MODE:
                commonFilterLayoutBinding.edtDispatchModeFilter.setText(commonListResponse.getName());
                dispatchModeIdFilter = AppUtils.castToInteger(commonListResponse.getId());
                break;

            case SP_PAYMENT_TYPE4:
                commonFilterLayoutBinding.edtPaymentTypeFilter.setText(commonListResponse.getName());
                paymentTypeIdFilter = AppUtils.castToInteger(commonListResponse.getId());
                break;

            case AppConstants.SP_CITY:
                commonFilterLayoutBinding.edtDestinationFilter.setText(commonListResponse.getName());
                destinationIdFilter = AppUtils.castToInteger(commonListResponse.getId());
                break;
        }
    }

    @Override
    public void onMasterBottomSheetInfoClick(int itemPosition, Object object, View... views) {
    }

    @Override
    public void onRatingClick(String rating) {

        try {

            startProgressDialog((Activity) mContext, false);

            CommanRequestModel commanRequestModel = new CommanRequestModel();
            commanRequestModel.setDocketId(String.valueOf(docketID));
            commanRequestModel.setRating(rating);

            String requestString = new Gson().toJson(commanRequestModel);
            Log.e(TAG, requestString);

            ApiService apiService = ApiClient.createService(ApiService.class, "", "");
            Call<CommonResponse<String>> call = apiService.docketRating(commanRequestModel);

            ApiManager.callRetrofit(call, API_DOCKET_RATING, this, false);

        } catch (Exception e) {
            stopProgressDialog();
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }
}