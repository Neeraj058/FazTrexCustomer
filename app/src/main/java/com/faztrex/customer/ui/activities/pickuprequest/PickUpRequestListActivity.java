package com.faztrex.customer.ui.activities.pickuprequest;

import static com.faztrex.customer.retrofit.ApiConstant.API_PICKUP_REQUEST_MANAGE;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cittasolutions.cittalibrary.utils.AppConstant;
import com.faztrex.customer.R;
import com.faztrex.customer.baseclasses.BaseActivity;
import com.faztrex.customer.baseclasses.BaseAdapter;
import com.faztrex.customer.customviews.MyDatePicker;
import com.faztrex.customer.customviews.SimpleAlertDialog;
import com.faztrex.customer.databinding.CommanListLayoutBinding;
import com.faztrex.customer.databinding.CommonFilterLayoutBinding;
import com.faztrex.customer.listeners.bottomsheet.MasterBottomSheetActionListener;
import com.faztrex.customer.listeners.general.CommonActionListener;
import com.faztrex.customer.models.pickuprequest.PickupRequestFormModel;
import com.faztrex.customer.network.model.request.CommanRequestModel;
import com.faztrex.customer.network.response.CommonListResponse;
import com.faztrex.customer.network.response.CommonResponse;
import com.faztrex.customer.network.response.pickuprequest.PickupListResponseModel;
import com.faztrex.customer.retrofit.ApiClient;
import com.faztrex.customer.retrofit.ApiConstant;
import com.faztrex.customer.retrofit.ApiListener;
import com.faztrex.customer.retrofit.ApiManager;
import com.faztrex.customer.retrofit.ApiService;
import com.faztrex.customer.utils.AppConstants;
import com.faztrex.customer.utils.AppUtils;
import com.faztrex.customer.utils.NetworkUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;

public class PickUpRequestListActivity extends BaseActivity implements View.OnClickListener, ApiListener, CommonActionListener, MasterBottomSheetActionListener {

    private final String TAG = this.getClass().getSimpleName();

    private final Context mContext = this;

    private CommanListLayoutBinding binding;

    private int pageCount = 0, pastVisibleItems, visibleItemCount, totalItemCount = 0, totalItems = 0, itemPosition;

    private boolean loading = true, isSwipeRefresh;

    private BaseAdapter baseAdapter;

    private CommonFilterLayoutBinding commonFilterLayoutBinding;

    private Map<String, Integer> inputParams;

    private Integer bookingStateIdFilter;

    private Integer bookingCityIdFilter;

    private Integer bookingPostcodeIdFilter;

    private Integer bookingPostcodeTypeIdFilter;

    private Integer destinationStateIdFilter;

    private Integer destinationCityIdFilter;

    private Integer destinationPostcodeIdFilter;

    private Integer destinationPostcodeTypeIdFilter;

    private Integer verticalIdFilter;

    private Integer parkingIdFilter;

    private String fromDate, toDate;

    private ArrayList<PickupListResponseModel> pickupListRequestModelArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.comman_list_layout);

        binding.toolbar.tvHeaderTitle.setText(R.string.nav_menu_title_pick_up_request);

        binding.toolbar.ivBack.setOnClickListener(v -> {
            finish();
        });

        visibleView(binding.toolbar.ivSearch);
        visibleView(binding.toolbar.ivFilter);

        binding.toolbar.ivSearch.setOnClickListener(this);
        binding.toolbar.ivFilter.setOnClickListener(this);
        binding.toolbar.ivClearEditext.setOnClickListener(this);
        binding.fabAdd.setOnClickListener(this);

        binding.rvCommanListLayout.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0 || dy < 0 && binding.fabAdd.isShown()) {
                    hideVisibilityWithAnimation(binding.fabAdd);
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    showVisibilityWithAnimation(binding.fabAdd);
                }

                super.onScrollStateChanged(recyclerView, newState);
            }
        });

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

        //makePickupRequestApiCall();

        View headerView = binding.filterNavigationDrawer.getHeaderView(0);
        commonFilterLayoutBinding = CommonFilterLayoutBinding.bind(headerView);
        binding.filterNavigationDrawer.setVerticalScrollBarEnabled(true);

        float offset = .200f * getResources().getDisplayMetrics().widthPixels;
        float width = getResources().getDisplayMetrics().widthPixels - offset;
        DrawerLayout.LayoutParams params = (DrawerLayout.LayoutParams) binding.filterNavigationDrawer.getLayoutParams();
        params.width = (int) width;
        binding.filterNavigationDrawer.setLayoutParams(params);

        commonFilterLayoutBinding.ivDrawerClose.setOnClickListener(v -> closeDrawer(binding.drawerLayout));

        commonFilterLayoutBinding.edtFromDate.setOnClickListener(this);
        commonFilterLayoutBinding.edtToDate.setOnClickListener(this);
        commonFilterLayoutBinding.edtBookingCountyFilter.setOnClickListener(this);
        commonFilterLayoutBinding.edtBookingCityFilter.setOnClickListener(this);
        commonFilterLayoutBinding.edtBookingPostCodeFilter.setOnClickListener(this);
        commonFilterLayoutBinding.edtDestinationCityFilter.setOnClickListener(this);
        commonFilterLayoutBinding.edtDestinationCountyFilter.setOnClickListener(this);
        commonFilterLayoutBinding.edtDestinationPostCodeFilter.setOnClickListener(this);
        commonFilterLayoutBinding.edtVerticleTypeFilter.setOnClickListener(this);
        commonFilterLayoutBinding.edtPackingTypeFilter.setOnClickListener(this);

        commonFilterLayoutBinding.edtFromDate.setOnClickListener(v -> new MyDatePicker(commonFilterLayoutBinding.tnlFromDate, AppConstants.CALENDAR_DATE_FORMAT, false));

        commonFilterLayoutBinding.edtToDate.setOnClickListener(v -> new MyDatePicker(commonFilterLayoutBinding.tnlToDate, AppConstants.CALENDAR_DATE_FORMAT, false));

        commonFilterLayoutBinding.btnClear.setOnClickListener(v -> {
            clearAllFilterControl();
            getPickUpRequestList();
            closeDrawer(binding.drawerLayout);
        });

        commonFilterLayoutBinding.btnApply.setOnClickListener(v -> {

            fromDate = AppUtils.convertDateFormat(AppConstant.CALENDAR_DATE_FORMAT, AppConstant.YYYY_MM_DD, commonFilterLayoutBinding.edtFromDate.getText().toString());
            toDate = AppUtils.convertDateFormat(AppConstant.CALENDAR_DATE_FORMAT, AppConstant.YYYY_MM_DD, commonFilterLayoutBinding.edtToDate.getText().toString());
            getPickUpRequestList();
            closeDrawer(binding.drawerLayout);

        });

        inputParams = new HashMap<>();

        getSpinnerList(AppConstants.SP_STATE, null);
        getSpinnerList(AppConstants.SP_STATE2, null);
        getSpinnerList(AppConstants.SP_PAYMENT_TYPE2, null);
        getSpinnerList(ApiConstant.API_VERTICLE, null);
        getSpinnerList(ApiConstant.API_PACKING_TYPE, null);


    }

    private void clearAllFilterControl() {

        commonFilterLayoutBinding.edtFromDate.getText().clear();
        commonFilterLayoutBinding.edtToDate.getText().clear();
        commonFilterLayoutBinding.edtBookingCountyFilter.getText().clear();
        commonFilterLayoutBinding.edtBookingCityFilter.getText().clear();
        commonFilterLayoutBinding.edtBookingPostCodeFilter.getText().clear();
        commonFilterLayoutBinding.edtDestinationCountyFilter.getText().clear();
        commonFilterLayoutBinding.edtDestinationCityFilter.getText().clear();
        commonFilterLayoutBinding.edtDestinationPostCodeFilter.getText().clear();
        commonFilterLayoutBinding.edtVerticleTypeFilter.getText().clear();
        commonFilterLayoutBinding.edtPackingTypeFilter.getText().clear();

        fromDate = null;
        toDate = null;
        bookingCityIdFilter = null;
        bookingPostcodeIdFilter = null;
        bookingPostcodeTypeIdFilter = null;
        bookingStateIdFilter = null;
        destinationCityIdFilter = null;
        destinationPostcodeIdFilter = null;
        destinationPostcodeTypeIdFilter = null;
        destinationStateIdFilter = null;
        parkingIdFilter = null;
        verticalIdFilter = null;

    }


    private void filter(String text) {

        ArrayList<PickupListResponseModel> filtered = new ArrayList<>();

        for (PickupListResponseModel s : pickupListRequestModelArrayList) {
            if (
                    s.getNo().toLowerCase().contains((text.toLowerCase()))
                            || s.getBookingState().toLowerCase().contains((text.toLowerCase()))
                            || s.getConsigneeName().toLowerCase().contains((text.toLowerCase()))
                            || s.getDestinationState().toLowerCase().contains((text.toLowerCase()))

            ) {
                filtered.add(s);
            }
        }

        baseAdapter.filterList(filtered);
    }

    private void makePickupRequestApiCall() {

        try {

            getPreferenceData();

            LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext);
            binding.rvCommanListLayout.setLayoutManager(mLayoutManager);
            binding.rvCommanListLayout.setItemAnimator(new DefaultItemAnimator());

            baseAdapter = new BaseAdapter(mContext, pickupListRequestModelArrayList, R.layout.item_pickup_request_list, this);
            binding.rvCommanListLayout.setAdapter(baseAdapter);

            getPickUpRequestList();

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
                                getPickUpRequestList();
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


        bookingStateIdFilter = null;
        bookingCityIdFilter = null;
        bookingPostcodeIdFilter = null;
        bookingPostcodeTypeIdFilter = null;
        destinationStateIdFilter = null;
        destinationCityIdFilter = null;
        destinationPostcodeIdFilter = null;
        destinationPostcodeTypeIdFilter = null;
        verticalIdFilter = null;
        parkingIdFilter = null;
        toDate = null;
        fromDate = null;


        pageCount = 0;
        pastVisibleItems = 0;
        visibleItemCount = 0;
        totalItemCount = 0;
        totalItems = 0;
        loading = true;


        pickupListRequestModelArrayList = new ArrayList<>();
        baseAdapter = new BaseAdapter(mContext, pickupListRequestModelArrayList, R.layout.item_pickup_request_list, this);
        binding.rvCommanListLayout.setAdapter(baseAdapter);

        // call method to get docket list
        getPickUpRequestList();
    }

    private void getPickUpRequestList() {

        try {

            if (NetworkUtils.isConnected(mContext)) {

                if (isSwipeRefresh) {

                    // start swipe layout
                    startSwipeLayout();

                } else {

                    // start progress indicator
                    startProgressDialog(this, false);
                }

                CommanRequestModel commanRequestModel = new CommanRequestModel();
                /*pickUpListRequestModel.setCid(prefUserModel.getCid());
                pickUpListRequestModel.setBid(prefUserModel.getBid());*/

                commanRequestModel.setSortdir("0");
                commanRequestModel.setSortdir("DESC");

                commanRequestModel.setPageindex(String.valueOf(pageCount));
                commanRequestModel.setPagesize(String.valueOf(AppConstants.DISPLAY_RECORD_COUNT));
                commanRequestModel.setKeyword("");

                commanRequestModel.setFromdate(fromDate);
                commanRequestModel.setTodate(toDate);

                commanRequestModel.setFyfromdate("");
                commanRequestModel.setFytodate("");

                commanRequestModel.setConsignorid(prefUserModel.getId());

                commanRequestModel.setBookingstateid(bookingStateIdFilter);
                commanRequestModel.setBookingcityid(bookingCityIdFilter);
                commanRequestModel.setBookingpostcodeid(bookingPostcodeIdFilter);
                commanRequestModel.setDestinationstateid(destinationStateIdFilter);
                commanRequestModel.setDestinationcityid(destinationCityIdFilter);
                commanRequestModel.setDestinationpostcodeid(destinationPostcodeIdFilter);
                commanRequestModel.setVehicletypeid(verticalIdFilter);
                commanRequestModel.setPackingtypeid(parkingIdFilter);

                ApiService apiService = ApiClient.createService(ApiService.class, "", "");
                Call<CommonResponse<ArrayList<PickupListResponseModel>>> call = apiService.getPickUpList(commanRequestModel);
                ApiManager.callRetrofit(call, ApiConstant.API_PICKUP_LIST, this, false);

                String request = new Gson().toJson(commanRequestModel);
                Log.d("Reqest", "ReqestString: " + request);


            } else {
                displayInternetToastMessage(mContext);
            }
        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    private void stopSwipeLayout() {

        if (isSwipeRefresh) {

            if (binding.swipeRefreshLayout != null && binding.swipeRefreshLayout.isRefreshing()) {
                binding.swipeRefreshLayout.setRefreshing(false);
                isSwipeRefresh = false;
            }
        }
    }

    private void startSwipeLayout() {
        binding.swipeRefreshLayout.post(() -> binding.swipeRefreshLayout.setRefreshing(true));
    }

    @Override
    public Activity setCurrentActivity() {
        return this;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.iv_Search:
                binding.toolbar.edtSearch.getText().clear();
                hideVisibilityWithAnimation(binding.toolbar.toolbarLayout);
                showVisibilityWithAnimation(binding.toolbar.searchLayout);

                /*binding.toolbar.ivSearch.animate()
                        .translationY(binding.toolbar.ivSearch.getHeight())
                        .alpha(0.0f)
                        .setDuration(300)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                binding.toolbar.toolbarLayout.setVisibility(View.GONE);
                                binding.toolbar.searchLayout.setVisibility(View.VISIBLE);

                            }
                        });*/
                /*hideView(binding.toolbar.toolbarLayout);
                visibleView(binding.toolbar.searchLayout);*/


                break;

            case R.id.ivClearEditext:
                hideVisibilityWithAnimation(binding.toolbar.searchLayout);
                showVisibilityWithAnimation(binding.toolbar.toolbarLayout);

                if (binding.toolbar.edtSearch.length() != 0) {
                    baseAdapter = new BaseAdapter(mContext, pickupListRequestModelArrayList, R.layout.item_pickup_request_list, this);
                    binding.rvCommanListLayout.setAdapter(baseAdapter);
                }


                /*binding.toolbar.toolbarLayout.setVisibility(View.VISIBLE);
                binding.toolbar.ivClearEditext.animate()
                        .alpha(0f)
                        .setDuration(300)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                binding.toolbar.searchLayout.setVisibility(View.GONE);
                            }

                        });
                binding.toolbar.ivSearch.setVisibility(View.VISIBLE);*/

                /*hideView(binding.toolbar.searchLayout);
                visibleView(binding.toolbar.toolbarLayout);*/

                break;

            case R.id.fabAdd:
                if (pickupListRequestModelArrayList == null)
                    pickupListRequestModelArrayList = new ArrayList<>();
                String autoNo = pickupListRequestModelArrayList.isEmpty() ? "0" : pickupListRequestModelArrayList.get(0).getNo();
                Intent intent = new Intent(mContext, PickUpRequestFormActivity.class);
                intent.putExtra("AutoNo", autoNo);
                startActivity(intent);
                //startActivity(new Intent(mContext, PickUpRequestFormActivity.class));
                break;

            case R.id.iv_Filter:
                openDrawer(binding.drawerLayout);
                break;

            case R.id.edtBookingCountyFilter:
                showFilterMasterBottomSheet(Objects.requireNonNull(commonFilterLayoutBinding.edtBookingCountyFilter.getHint()).toString(), this, AppConstants.SP_STATE);
                break;

            case R.id.edtBookingCityFilter:
                showFilterMasterBottomSheet(Objects.requireNonNull(commonFilterLayoutBinding.edtBookingCityFilter.getHint()).toString(), this, AppConstants.SP_CITY);
                break;

            case R.id.edtBookingPostCodeFilter:
                showFilterMasterBottomSheet(Objects.requireNonNull(commonFilterLayoutBinding.edtBookingPostCodeFilter.getHint()).toString(), this, AppConstants.SP_POSTCODE);
                break;

            case R.id.edtDestinationCountyFilter:
                showFilterMasterBottomSheet(Objects.requireNonNull(commonFilterLayoutBinding.edtDestinationCountyFilter.getHint()).toString(), this, AppConstants.SP_STATE2);
                break;

            case R.id.edtDestinationCityFilter:
                showFilterMasterBottomSheet(Objects.requireNonNull(commonFilterLayoutBinding.edtDestinationCityFilter.getHint()).toString(), this, AppConstants.SP_CITY2);
                break;

            case R.id.edtFromDate:
                new MyDatePicker(commonFilterLayoutBinding.tnlFromDate, AppConstants.CALENDAR_DATE_FORMAT, true, 5);
                //openDatePickerDialog(binding.tnlDate, AppConstants.CALENDAR_DATE_FORMAT);
                break;

            case R.id.tnlToDate:
                new MyDatePicker(commonFilterLayoutBinding.tnlToDate, AppConstants.CALENDAR_DATE_FORMAT, true, 5);
                //openDatePickerDialog(binding.tnlDate, AppConstants.CALENDAR_DATE_FORMAT);
                break;

            case R.id.edtDestinationPostCodeFilter:
                showFilterMasterBottomSheet(Objects.requireNonNull(commonFilterLayoutBinding.edtDestinationPostCodeFilter.getHint()).toString(), this, AppConstants.SP_POSTCODE2);
                break;


            case R.id.edtVerticleTypeFilter:
                showFilterMasterBottomSheet(Objects.requireNonNull(commonFilterLayoutBinding.edtVerticleTypeFilter.getHint()).toString(), this, AppConstants.SP_VERTICAL);
                break;

            case R.id.edtPackingTypeFilter:
                showFilterMasterBottomSheet(Objects.requireNonNull(commonFilterLayoutBinding.edtPackingTypeFilter.getHint()).toString(), this, AppConstants.SP_PACKING_TYPE);
                break;
        }
    }

    private void showNoData() {
        visibleView(binding.ivNoDataFound);
        hideView(binding.rvCommanListLayout);
    }

    @Override
    public void onApiSuccess(String endPointName, Object responseBody) {
        super.onApiSuccess(endPointName, responseBody);
        stopSwipeLayout();
        switch (endPointName) {

            case ApiConstant.API_PICKUP_LIST:
                CommonResponse<ArrayList<PickupListResponseModel>> pickUpResponse = (CommonResponse<ArrayList<PickupListResponseModel>>) responseBody;
                processResponse(pickUpResponse);
                break;

            case API_PICKUP_REQUEST_MANAGE:
                CommonResponse<String> manageResponse = (CommonResponse<String>) responseBody;
                processPickupRequestFormResponse(manageResponse);
                break;
        }
    }

    private void processPickupRequestFormResponse(CommonResponse<String> response) {

        try {

            if (response != null) {

                if (response.getStatus().equalsIgnoreCase("1")) {

                    pickupListRequestModelArrayList.remove(itemPosition);
                    baseAdapter.notifyItemRemoved(itemPosition);

                } else {
                    displayLongToast(mContext, response.getMessage());
                }

            } else {

                displayLongToast(mContext, "Something went wrong!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
            stopProgressDialog();
        }
    }

    @Override
    public void onApiError(String endPointName, String errorMessage) {
        super.onApiSuccess(endPointName, errorMessage);
        printErrorLog(TAG, errorMessage);

        // stop swipe refresh layout
        stopSwipeLayout();

        switch (endPointName) {

            case ApiConstant.API_PICKUP_LIST:
            case API_PICKUP_REQUEST_MANAGE:

                // show error toast message
                displayLongToast(mContext, errorMessage);

                // call method to show no data image
                showNoData();
                break;
        }
    }

    @Override
    public void onApiFailure(String endPointName, String failureMessage) {
        super.onApiSuccess(endPointName, failureMessage);
        printErrorLog(TAG, failureMessage);

        switch (endPointName) {

            case ApiConstant.API_PICKUP_LIST:
            case API_PICKUP_REQUEST_MANAGE:

                // show error toast message
                displayLongToast(mContext, failureMessage);

                // call method to show no data image
                showNoData();
                break;
        }
    }

    private void processResponse(CommonResponse<ArrayList<PickupListResponseModel>> response) {

        try {

            switch (response.getStatus()) {

                case AppConstants.STATUS_SUCCESS:

                    // call method to bind docket list
                    bindPickupList(response);
                    break;

                case AppConstants.STATUS_FAILURE:
                    pickupListRequestModelArrayList.clear();
                    baseAdapter.notifyDataSetChanged();
                    // call method to show no data image
                    showNoData();
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    private void bindPickupList(CommonResponse<ArrayList<PickupListResponseModel>> response) {

        try {

            pickupListRequestModelArrayList = new ArrayList<>();
            pickupListRequestModelArrayList = response.getData();

            if (pickupListRequestModelArrayList != null && pickupListRequestModelArrayList.size() > 0) {

//                baseAdapter.notifyDataSetChanged();
                baseAdapter = new BaseAdapter(mContext, pickupListRequestModelArrayList, R.layout.item_pickup_request_list, this);
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

    @Override
    public void onViewClick(int itemPosition, Object object) {

        PickupListResponseModel pickupListResponseModel = (PickupListResponseModel) object;
        Intent intent = new Intent(mContext, PickUpRequestFormActivity.class);
        intent.putExtra(AppConstants.PICK_UP_REQUEST_ID, pickupListResponseModel.getId());
        intent.putExtra(AppConstants.MODE, "view");
        startActivity(intent);

    }

    @Override
    public void onInfoClick(int itemPosition, Object object, View... views) {
    }

    @Override
    public void onEditClick(int itemPosition, Object object) {

        this.itemPosition = itemPosition;
        PickupListResponseModel pickupListResponseModel = (PickupListResponseModel) object;
        Intent intent = new Intent(mContext, PickUpRequestFormActivity.class);
        intent.putExtra(AppConstants.PICK_UP_REQUEST_ID, pickupListResponseModel.getId());
        intent.putExtra(AppConstants.MODE, "edit");
        startActivity(intent);

    }

    @Override
    public void onDeleteClick(int itemPosition, Object object) {

        this.itemPosition = itemPosition;
        displayConfirmationDialog(itemPosition);

    }

    @Override
    protected void onResume() {
        super.onResume();

        if (pickupListRequestModelArrayList != null)
            pickupListRequestModelArrayList.clear();

        makePickupRequestApiCall();
    }

    private void displayConfirmationDialog(int itemPosition) {

        simpleAlertDialog = new SimpleAlertDialog(PickUpRequestListActivity.this) {

            @Override
            public boolean setDialogCancelable() {
                return false;
            }

            @Override
            public String setDialogTitle() {
                return Objects.requireNonNull(PickUpRequestListActivity.this).getResources().getString(R.string.dialog_title_Delete);
            }

            @Override
            public String setDialogMessage() {
                return Objects.requireNonNull(PickUpRequestListActivity.this).getResources().getString(R.string.dialog_msg_delete_confirmation);
            }

            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public Drawable setDialogIcon() {
                return Objects.requireNonNull(PickUpRequestListActivity.this).getResources().getDrawable(R.mipmap.ic_launcher);
            }

            @Override
            public String setDialogPositiveButtonText() {
                return Objects.requireNonNull(PickUpRequestListActivity.this).getResources().getString(R.string.dialog_title_Delete);
            }

            @Override
            public DialogInterface.OnClickListener onDialogPositiveButtonClick() {

                return (dialog, which) -> {

                    deletePickupRequestItem(itemPosition);

                };
            }

            @Override
            public String setDialogNegativeButtonText() {
                return Objects.requireNonNull(PickUpRequestListActivity.this).getResources().getString(R.string.btn_title_cancel);
            }

            @Override
            public DialogInterface.OnClickListener onDialogNegativeButtonClick() {

                return (dialog, which) -> {

                    dialog.dismiss();
                };
            }

            @Override
            public String setDialogNeutralButtonText() {
                return null;
            }

            @Override
            public DialogInterface.OnClickListener onDialogNeutralButtonClick() {
                return null;
            }

            @Override
            public DialogInterface.OnDismissListener onDialogDismissListener() {
                return null;
            }
        };
    }

    private void deletePickupRequestItem(int itemPosition) {

        PickupListResponseModel pr = pickupListRequestModelArrayList.get(itemPosition);

        PickupRequestFormModel form = new PickupRequestFormModel();
        form.setId(Integer.valueOf(pr.getId()));
        form.setIsDelete(1);
        form.setIsFrom(2);

        ApiService apiService = ApiClient.createService(ApiService.class, "", "");

        Call<CommonResponse<String>> call = apiService.managePickupRequestForm(form);

        ApiManager.callRetrofit(call, API_PICKUP_REQUEST_MANAGE, this, false);

    }

    @Override
    public void onMasterBottomSheetViewClick(int itemPosition, Object object, String edtID) {
        CommonListResponse commonListResponse = (CommonListResponse) object;

        switch (edtID) {

            case AppConstants.SP_STATE:
                commonFilterLayoutBinding.edtBookingCountyFilter.setText(commonListResponse.getName());
                bookingStateIdFilter = AppUtils.castToInteger(commonListResponse.getId());
                inputParams.put(AppConstants.KEY_STATE_ID, bookingStateIdFilter);
                getSpinnerList(AppConstants.SP_CITY, inputParams);
                bookingCityIdFilter = null;
                bookingPostcodeIdFilter = null;
                bookingPostcodeTypeIdFilter = null;
                commonFilterLayoutBinding.edtBookingCityFilter.setText("");
                commonFilterLayoutBinding.edtBookingPostCodeFilter.setText("");

                if (cityMasterList != null)
                    cityMasterList.clear();

               /* if (postcodeMasterList != null)
                    postcodeMasterList.clear();
*/
                Log.d(TAG, "onMasterBottomSheetViewClick: " + bookingStateIdFilter + "\n" + prefUserModel.getId());

                break;

            case AppConstants.SP_STATE2:
                commonFilterLayoutBinding.edtDestinationCountyFilter.setText(commonListResponse.getName());
                destinationStateIdFilter = AppUtils.castToInteger(commonListResponse.getId());
                inputParams.put(AppConstants.KEY_STATE_ID, destinationStateIdFilter);

                getSpinnerList(AppConstants.SP_CITY2, inputParams);

                //destinationCityId = 0;
                commonFilterLayoutBinding.edtDestinationCityFilter.setText("");
                if (cityDestinationMasterList != null)
                    cityDestinationMasterList.clear();
                break;

            case AppConstants.SP_CITY:
                commonFilterLayoutBinding.edtBookingCityFilter.setText(commonListResponse.getName());
                bookingCityIdFilter = AppUtils.castToInteger(commonListResponse.getId());
                inputParams.put(AppConstants.KEY_CITY_ID, bookingCityIdFilter);
                getSpinnerList(AppConstants.SP_POSTCODE, inputParams);
                commonFilterLayoutBinding.edtBookingPostCodeFilter.setText("");
                if (postcodeMasterList != null)
                    postcodeMasterList.clear();
                break;

            case AppConstants.SP_CITY2:
                commonFilterLayoutBinding.edtDestinationCityFilter.setText(commonListResponse.getName());
                destinationCityIdFilter = AppUtils.castToInteger(commonListResponse.getId());
                inputParams.put(AppConstants.KEY_CITY_ID, destinationCityIdFilter);
                getSpinnerList(AppConstants.SP_POSTCODE2, inputParams);
                //destinationCityId = 0;
                if (destinationPostcodeMasterList != null)
                    destinationPostcodeMasterList.clear();
                break;

            case AppConstants.SP_POSTCODE:
                commonFilterLayoutBinding.edtBookingPostCodeFilter.setText(commonListResponse.getName());
                bookingPostcodeIdFilter = AppUtils.castToInteger(commonListResponse.getId());
                bookingPostcodeTypeIdFilter = AppUtils.castToInteger(commonListResponse.getPostcodeTypeId());
                break;

            case AppConstants.SP_POSTCODE2:
                commonFilterLayoutBinding.edtDestinationPostCodeFilter.setText(commonListResponse.getName());
                destinationPostcodeIdFilter = AppUtils.castToInteger(commonListResponse.getId());
                destinationPostcodeTypeIdFilter = AppUtils.castToInteger(commonListResponse.getPostcodeTypeId());
                break;

            case AppConstants.SP_VERTICAL:
                verticalIdFilter = AppUtils.castToInteger(commonListResponse.getId());
                commonFilterLayoutBinding.edtVerticleTypeFilter.setText(commonListResponse.getName());
                break;

            case AppConstants.SP_PACKING_TYPE:
                parkingIdFilter = AppUtils.castToInteger(commonListResponse.getId());
                commonFilterLayoutBinding.edtPackingTypeFilter.setText(commonListResponse.getName());
                break;
        }

    }

    @Override
    public void onMasterBottomSheetInfoClick(int itemPosition, Object object, View... views) {

    }
}