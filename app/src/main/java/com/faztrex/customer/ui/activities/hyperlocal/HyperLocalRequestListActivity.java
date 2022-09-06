package com.faztrex.customer.ui.activities.hyperlocal;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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

import com.faztrex.customer.R;
import com.faztrex.customer.baseclasses.BaseActivity;
import com.faztrex.customer.baseclasses.BaseAdapter;
import com.faztrex.customer.customviews.MyDatePicker;
import com.faztrex.customer.databinding.CommanListLayoutBinding;
import com.faztrex.customer.databinding.CommonFilterLayoutBinding;
import com.faztrex.customer.helper.ConfirmationDialogManager;
import com.faztrex.customer.listeners.bottomsheet.MasterBottomSheetActionListener;
import com.faztrex.customer.listeners.general.CommonActionListener;
import com.faztrex.customer.listeners.general.ConfirmationDialogClickListener;
import com.faztrex.customer.listeners.general.RatingActionListener;
import com.faztrex.customer.models.hyperlocal.HyperLocalForm;
import com.faztrex.customer.models.hyperlocal.HyperLocalList;
import com.faztrex.customer.network.model.request.CommanRequestModel;
import com.faztrex.customer.network.response.CommonListResponse;
import com.faztrex.customer.network.response.CommonResponse;
import com.faztrex.customer.retrofit.ApiClient;
import com.faztrex.customer.retrofit.ApiConstant;
import com.faztrex.customer.retrofit.ApiListener;
import com.faztrex.customer.retrofit.ApiManager;
import com.faztrex.customer.retrofit.ApiService;
import com.faztrex.customer.ui.activities.pickuprequest.PickUpRequestFormActivity;
import com.faztrex.customer.utils.AppConstants;
import com.faztrex.customer.utils.AppUtils;
import com.faztrex.customer.utils.NetworkUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;

import static com.cittasolutions.cittalibrary.utils.AppUtils.errorLog;
import static com.faztrex.customer.retrofit.ApiConstant.API_HYPER_LOCAL_LIST;
import static com.faztrex.customer.retrofit.ApiConstant.API_HYPER_LOCAL_MANAGE;

public class HyperLocalRequestListActivity extends BaseActivity implements
        View.OnClickListener,
        MasterBottomSheetActionListener,
        RatingActionListener,
        ApiListener,
        CommonActionListener, ConfirmationDialogClickListener {
       /* GPSLocationListener,
        PermissionManagerListener*/

    @Override
    public Activity setCurrentActivity() {
        return this;
    }

    private CommanListLayoutBinding binding;

    private CommonFilterLayoutBinding commonFilterLayoutBinding;

    private final String TAG = this.getClass().getSimpleName();

    private ArrayList<HyperLocalList> hyperLocalListArrayList = new ArrayList<>();

    private BaseAdapter baseAdapter;

    private final Context mContext = this;

    private boolean loading = true;

    private boolean isSwipeRefresh;

    private int hyperitemPosition = -1;

    private Integer statusId;

    private int pageCount = 0;

    private int pastVisibleItems;

    private int visibleItemCount;

    private int totalItemCount = 0;

    private int totalItems = 0;

    private HyperLocalList hyperLocalList;

    private int itemPosition;

   /* private FusedLocationProviderClient fusedLocationClient;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 2;
    private Location currentLocation;
    private LocationCallback locationCallback;
    private GPSManager gpsManager;*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.comman_list_layout);

        binding.toolbar.tvHeaderTitle.setText(R.string.nav_menu_title_hyper_local_request);

        binding.toolbar.ivBack.setOnClickListener(v -> {
            finish();
        });

        visibleView(binding.toolbar.ivSearch);
        visibleView(binding.toolbar.ivFilter);

        binding.toolbar.ivSearch.setOnClickListener(this);
        binding.toolbar.ivFilter.setOnClickListener(this);
        binding.toolbar.ivClearEditext.setOnClickListener(this);

        //binding.fabAdd.setOnClickListener(v -> startActivity(new Intent(mContext, HyperLocalFormActivity.class)));

        binding.fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(mContext, HyperLocalFormActivity.class);
                String autoNo = hyperLocalListArrayList.isEmpty() ? "0" : hyperLocalListArrayList.get(0).getNo();
                intent.putExtra("AutoNo",autoNo);
                startActivity(intent);
            }
        });


        getSpinnerList(AppConstants.SP_STATE, null);

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

        View headerView = binding.filterNavigationDrawer.getHeaderView(0);
        commonFilterLayoutBinding = CommonFilterLayoutBinding.bind(headerView);
        binding.filterNavigationDrawer.setVerticalScrollBarEnabled(true);

        float offset = .200f * getResources().getDisplayMetrics().widthPixels;
        float width = getResources().getDisplayMetrics().widthPixels - offset;
        DrawerLayout.LayoutParams params = (DrawerLayout.LayoutParams) binding.filterNavigationDrawer.getLayoutParams();
        params.width = (int) width;
        binding.filterNavigationDrawer.setLayoutParams(params);

        commonFilterLayoutBinding.lLayoutFilter.setVisibility(View.GONE);

        commonFilterLayoutBinding.ivDrawerClose.setOnClickListener(v -> closeDrawer(binding.drawerLayout));

        commonFilterLayoutBinding.edtFromDate.setOnClickListener(v -> {

            new MyDatePicker(commonFilterLayoutBinding.tnlFromDate, AppConstants.CALENDAR_DATE_FORMAT, false);

        });

        commonFilterLayoutBinding.edtToDate.setOnClickListener(v -> {

            new MyDatePicker(commonFilterLayoutBinding.tnlToDate, AppConstants.CALENDAR_DATE_FORMAT, false);

        });

        commonFilterLayoutBinding.btnClear.setOnClickListener(v -> {
            clearAllFilterControls();
        });

        commonFilterLayoutBinding.btnApply.setOnClickListener(v -> {

            applyFilter();
            closeDrawer(binding.drawerLayout);

        });

       /* gpsManager = new GPSManager(this);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        PermissionManagerListener permissionManagerListener = this;
        PermissionManager permissionManager = new PermissionManager(this, permissionManagerListener);
        permissionManager.setSinglePermission(Manifest.permission.ACCESS_FINE_LOCATION);*/
    }

    /*@Override
    public void onPause() {
        super.onPause();
        if (fusedLocationClient != null && locationCallback != null)
            fusedLocationClient.removeLocationUpdates(locationCallback);
    }*/

    @Override
    protected void onResume() {
        super.onResume();

        if (hyperLocalListArrayList != null)
            hyperLocalListArrayList.clear();
        makeHyperLocalRequestApiCall();
    }

    private void makeHyperLocalRequestApiCall() {

        try {

            getPreferenceData();

            LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext);
            binding.rvCommanListLayout.setLayoutManager(mLayoutManager);
            binding.rvCommanListLayout.setItemAnimator(new DefaultItemAnimator());

            baseAdapter = new BaseAdapter(mContext, hyperLocalListArrayList, R.layout.item_hyper_local_request, (RatingActionListener) this);
            binding.rvCommanListLayout.setAdapter(baseAdapter);

            getHyperLocalRequestList();


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

        hyperLocalListArrayList = new ArrayList<>();
        baseAdapter = new BaseAdapter(mContext, hyperLocalListArrayList, R.layout.item_hyper_local_request, (RatingActionListener) this);
        binding.rvCommanListLayout.setAdapter(baseAdapter);

        // call method to get docket list
        getHyperLocalRequestList();
    }

    private void getHyperLocalRequestList() {

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
                commanRequestModel.setSortdir("0");
                commanRequestModel.setSortdir("DESC");
                commanRequestModel.setPageindex(String.valueOf(pageCount));
                commanRequestModel.setPagesize(String.valueOf(AppConstants.DISPLAY_RECORD_COUNT));
                commanRequestModel.setKeyword("");
                commanRequestModel.setFromdate("");
                commanRequestModel.setTodate("");
                commanRequestModel.setConsignorid(prefUserModel.getId());

                ApiService apiService = ApiClient.createService(ApiService.class, "", "");
                Call<CommonResponse<ArrayList<HyperLocalList>>> call = apiService.getHyperLocalList(commanRequestModel);
                ApiManager.callRetrofit(call, ApiConstant.API_HYPER_LOCAL_LIST, this, false);

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

    private void applyFilter() {

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
                commanRequestModel.setSortdir("0");
                commanRequestModel.setSortdir("DESC");
                commanRequestModel.setPageindex(String.valueOf(pageCount));
                commanRequestModel.setPagesize(String.valueOf(AppConstants.DISPLAY_RECORD_COUNT));
                commanRequestModel.setKeyword("");
                commanRequestModel.setFromdate("");
                commanRequestModel.setTodate("");
                commanRequestModel.setConsignorid(prefUserModel.getId());

                ApiService apiService = ApiClient.createService(ApiService.class, "", "");
                Call<CommonResponse<ArrayList<HyperLocalList>>> call = apiService.getHyperLocalList(commanRequestModel);
                ApiManager.callRetrofit(call, ApiConstant.API_HYPER_LOCAL_LIST, this, false);

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

    private void clearAllFilterControls() {

        commonFilterLayoutBinding.edtToDate.getText().clear();
        commonFilterLayoutBinding.edtFromDate.getText().clear();

    }

    private void filter(String text) {

        ArrayList<HyperLocalList> filtered = new ArrayList<>();

        for (HyperLocalList s : hyperLocalListArrayList) {
            if (
                    s.getNo().toLowerCase().contains((text.toLowerCase()))
                            || s.getNo().toLowerCase().contains((text.toLowerCase()))
                            || s.getDeliveryPersonName().toLowerCase().contains((text.toLowerCase()))
                            || s.getPickupPersonName().toLowerCase().contains((text.toLowerCase()))
                            || s.getPickupContactNo().toLowerCase().contains((text.toLowerCase()))
                            || s.getProduct().toLowerCase().contains((text.toLowerCase()))
                            || s.getTotalAmount().toLowerCase().contains((text.toLowerCase()))

            ) {
                filtered.add(s);
            }
        }

        baseAdapter.filterList(filtered);
    }

    private void showNoData() {

        if (hyperLocalListArrayList != null && hyperLocalListArrayList.size() == 0) {

            visibleView(binding.ivNoDataFound);
            hideView(binding.rvCommanListLayout);
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


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.iv_Search:
                binding.toolbar.edtSearch.getText().clear();
                hideVisibilityWithAnimation(binding.toolbar.toolbarLayout);
                showVisibilityWithAnimation(binding.toolbar.searchLayout);

                break;

            case R.id.ivClearEditext:
                hideVisibilityWithAnimation(binding.toolbar.searchLayout);
                showVisibilityWithAnimation(binding.toolbar.toolbarLayout);

                if (binding.toolbar.edtSearch.length() != 0) {

                   /* baseAdapter = new BaseAdapter(mContext, pickupListRequestModelArrayList, R.layout.item_pickup_request_list, this);
                    binding.rvCommanListLayout.setAdapter(baseAdapte
                    r);
*/
                }

                break;

            case R.id.fabAdd:
             startActivity(new Intent(mContext, PickUpRequestFormActivity.class));

                break;

            case R.id.iv_Filter:
                openDrawer(binding.drawerLayout);
                break;

        }
    }

    @Override
    public void onApiSuccess(String endPointName, Object responseBody) {

        super.onApiSuccess(endPointName, responseBody);
        stopSwipeLayout();

        switch (endPointName) {

            case API_HYPER_LOCAL_LIST:
                CommonResponse<ArrayList<HyperLocalList>> pickUpResponse = (CommonResponse<ArrayList<HyperLocalList>>) responseBody;
                processListResponse(pickUpResponse);
                break;

            case API_HYPER_LOCAL_MANAGE:
                CommonResponse<String> manageResponse = (CommonResponse<String>) responseBody;
                processManageApiResponse(manageResponse);
                break;
        }
    }

    @Override
    public void onApiError(String endPointName, String errorMessage) {
        super.onApiError(endPointName, errorMessage);
        printErrorLog(TAG, errorMessage);

        // stop swipe refresh layout
        stopSwipeLayout();

        switch (endPointName) {

            case ApiConstant.API_HYPER_LOCAL_LIST:
            case API_HYPER_LOCAL_MANAGE:
                // show error toast message
                displayLongToast(mContext, errorMessage);
                // call method to show no data image
                showNoData();
                break;
        }
    }

    @Override
    public void onApiFailure(String endPointName, String failureMessage) {
        super.onApiFailure(endPointName, failureMessage);
        printErrorLog(TAG, failureMessage);

        switch (endPointName) {

            case ApiConstant.API_HYPER_LOCAL_LIST:
            case API_HYPER_LOCAL_MANAGE:
                // show error toast message
                displayLongToast(mContext, failureMessage);
                // call method to show no data image
                showNoData();
                break;
        }
    }

    private void processManageApiResponse(CommonResponse<String> response) {

        try {

            if (response != null) {

                if (response.getStatus().equalsIgnoreCase("1")) {
                    //getHyperLocalRequestList();
                    hyperLocalListArrayList.remove(itemPosition);
                    baseAdapter.notifyItemRemoved(itemPosition);
                }
                displayLongToast(mContext, response.getMessage());

            } else {
                displayLongToast(mContext, "Something went wrong!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            errorLog(e);
        }
    }


    private void processListResponse(CommonResponse<ArrayList<HyperLocalList>> response) {

        try {
            switch (response.getStatus()) {

                case AppConstants.STATUS_SUCCESS:

                    // call method to bind docket list
                    hyperLocalListArrayList.clear();
                    bindHyperRequestList(response);
                    break;

                case AppConstants.STATUS_FAILURE:

                    // call method to show no data image
                    showNoData();
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }

    }

    private void bindHyperRequestList(CommonResponse<ArrayList<HyperLocalList>> response) {

        try {

            ArrayList<HyperLocalList> tempHyperList = response.getData();

            if (tempHyperList != null && tempHyperList.size() > 0)
                hyperLocalListArrayList.addAll(tempHyperList);


            if (hyperLocalListArrayList != null && hyperLocalListArrayList.size() > 0) {

                baseAdapter.notifyDataSetChanged();

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
        this.itemPosition = itemPosition;
        HyperLocalList list = (HyperLocalList) object;
        Intent intent = new Intent(mContext, HyperLocalFormActivity.class);
        intent.putExtra(AppConstants.HYPER_LOCAL_REQUEST_ID, list.getId());
        intent.putExtra(AppConstants.MODE, "view");
        startActivity(intent);
    }

    @Override
    public void onInfoClick(int itemPosition, Object object, View... views) {
        String latitude = "22.285141";
        String longitude = "70.748188";
        String labelLocation = "Label";

        /*String geoUri = "google.navigation:q="+latitude+","+longitude;
        Uri gmmIntentUri = Uri.parse(geoUri);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);*/

        String urlAddress = "http://maps.google.com/maps?q=" + latitude + "," + longitude + "(" + labelLocation + ")&iwloc=A&hl=es";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlAddress));
        startActivity(intent);
    }

    @Override
    public void onEditClick(int itemPosition, Object object) {
        this.itemPosition = itemPosition;
        HyperLocalList list = (HyperLocalList) object;
        Intent intent = new Intent(mContext, HyperLocalFormActivity.class);
        intent.putExtra(AppConstants.HYPER_LOCAL_REQUEST_ID, list.getId());
        intent.putExtra(AppConstants.MODE, "edit");
        startActivity(intent);
    }

    @Override
    public void onDeleteClick(int itemPosition, Object object) {
        this.itemPosition = itemPosition;
        ConfirmationDialogManager.getInstance(mContext, this).askConsent("Do you want to delete this request?");
    }

    @Override
    public void onConfirmationPositiveClick() {

        try {

            HyperLocalList list = hyperLocalListArrayList.get(itemPosition);

            HyperLocalForm form = new HyperLocalForm();
            form.setId(Integer.valueOf(list.getId()));
            form.setIsDelete(1);
            form.setLastModifyBy(Integer.valueOf(prefUserModel.getId()));

            String request = new Gson().toJson(form);

            ApiService apiService = ApiClient.createService(ApiService.class, "", "");

            Call<CommonResponse<String>> call = apiService.manageHyperLocal(form);

            ApiManager.callRetrofit(call, API_HYPER_LOCAL_MANAGE, this, false);

        } catch (Exception e) {
            e.printStackTrace();
            errorLog(e);
        }
    }

    @Override
    public void onConfirmationNegativeClick() {
    }


    @Override
    public void onMasterBottomSheetViewClick(int itemPosition, Object object, String edtID) {

        CommonListResponse commonListResponse = (CommonListResponse) object;

        switch (edtID) {

            case AppConstants.SP_STATE:
                hyperLocalList.setStatus(commonListResponse.getName());
                hyperLocalListArrayList.set(hyperitemPosition, hyperLocalList);
                statusId = AppUtils.castToInteger(commonListResponse.getId());
                baseAdapter.notifyItemChanged(hyperitemPosition);
                break;

        }
    }

    @Override
    public void onMasterBottomSheetInfoClick(int itemPosition, Object object, View... views) {

    }

    @Override
    public void onRatingClick(int itemPosition, Object object) {

    }

    @Override
    public void onSubmitClick(int itemPosition, Object object, View... views) {

        hyperLocalList = (HyperLocalList) object;
        this.hyperitemPosition = itemPosition;
        showFilterMasterBottomSheet(Objects.requireNonNull(commonFilterLayoutBinding.edtBookingCountyFilter.getHint()).toString(), this, AppConstants.SP_STATE);

    }

    @Override
    public void onCancelClick(int itemPosition, Object object) {

    }

  /*  @Override
    public void onGPSAlreadyEnabled() {
    *//*    locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                currentLocation = locationResult.getLocations().get(0);
            }
        };
        startLocationUpdates();*//*
    }


    @Override
    public void onSinglePermissionGranted(String permissionName, String... endPoint) {
        switch (permissionName) {
            case Manifest.permission.ACCESS_FINE_LOCATION:
                gpsManager.start(this);
                break;
        }
    }

    @Override
    public void onMultiplePermissionGranted(ArrayList<String> permissionName, String... endPoint) {

    }*/
}