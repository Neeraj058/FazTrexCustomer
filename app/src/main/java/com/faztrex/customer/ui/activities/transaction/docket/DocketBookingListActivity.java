package com.faztrex.customer.ui.activities.transaction.docket;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.faztrex.customer.R;
import com.faztrex.customer.baseclasses.BaseActivity;
import com.faztrex.customer.baseclasses.BaseAdapter;
import com.faztrex.customer.databinding.CommanListLayoutBinding;
import com.faztrex.customer.listeners.general.CommonActionListener;
import com.faztrex.customer.network.model.request.CommanRequestModel;
import com.faztrex.customer.network.model.request.docket.DocketRequestModel;
import com.faztrex.customer.network.response.CommonResponse;
import com.faztrex.customer.network.response.docket.DocketListResponseModel;
import com.faztrex.customer.retrofit.ApiClient;
import com.faztrex.customer.retrofit.ApiConstant;
import com.faztrex.customer.retrofit.ApiListener;
import com.faztrex.customer.retrofit.ApiManager;
import com.faztrex.customer.retrofit.ApiService;
import com.faztrex.customer.utils.AppConstants;
import com.faztrex.customer.utils.NetworkUtils;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;

public class DocketBookingListActivity extends BaseActivity implements
        View.OnClickListener, CommonActionListener, ApiListener {

    private final Context mContext = this;
    private final String TAG = this.getClass().getSimpleName();
    private final int REQUEST_CODE_DOCKET = 1001;
    private CommanListLayoutBinding binding;
    private int pageCount = 0, pastVisibleItems, visibleItemCount, totalItemCount = 0, totalItems = 0;
    private boolean loading = true, isSwipeRefresh;
    private BaseAdapter baseAdapter;

    private ArrayList<DocketListResponseModel> docketListResponseModelArrayList = new ArrayList<>();

    @Override
    public Activity setCurrentActivity() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.comman_list_layout);

        binding.toolbar.tvHeaderTitle.setText(R.string.nav_header_title_docket_booking);
        binding.toolbar.ivBack.setOnClickListener(v -> {
            finish();
        });

        visibleView(binding.toolbar.ivSearch);
        visibleView(binding.toolbar.ivFilter);

        hideView(binding.fabAdd);

        binding.toolbar.ivSearch.setOnClickListener(this);
        binding.toolbar.ivFilter.setOnClickListener(this);
        binding.toolbar.ivClearEditext.setOnClickListener(this);

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

        makeDocketListApiCall();

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

            binding.rvCommanListLayout.addOnScrollListener(new RecyclerView.OnScrollListener() {

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
                                getDocketList();
                            }
                        }
                    }
                }
            });

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

        docketListResponseModelArrayList = new ArrayList<>();
        baseAdapter = new BaseAdapter(mContext, docketListResponseModelArrayList, R.layout.item_docket_list, this);
        binding.rvCommanListLayout.setAdapter(baseAdapter);

        // call method to get docket list
        getDocketList();
    }

    private void getDocketList() {


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
                commanRequestModel.setSearch("");
                commanRequestModel.setFromdate("");
                commanRequestModel.setTodate("");

                commanRequestModel.setCustomerid(prefUserModel.getId());

                commanRequestModel.setDispatchmode(null);
                commanRequestModel.setPaymenttype(null);
                commanRequestModel.setOriginid(null);

                commanRequestModel.setDestinationstateid(null);
                commanRequestModel.setDestinationcityid(null);

         /*       commanRequestModel.setBookingstateid("");
                commanRequestModel.setBookingcityid("");
                commanRequestModel.setBookingpostcodeid("");
                commanRequestModel.setDestinationpostcodeid("");
                commanRequestModel.setVehicletypeid("");
                commanRequestModel.setPackingtypeid("");*/

                ApiService apiService = ApiClient.createService(ApiService.class, "", "");
                Call<CommonResponse<ArrayList<DocketListResponseModel>>> call = apiService.getDocketList(commanRequestModel);
                ApiManager.callRetrofit(call, ApiConstant.API_DOCKET_LIST, this, false);

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


    @Override
    public void onApiSuccess(String endPointName, Object responseBody) {
        stopSwipeLayout();
        switch (endPointName) {

            case ApiConstant.API_DOCKET_LIST:

                CommonResponse<ArrayList<DocketListResponseModel>> docketResponse = (CommonResponse<ArrayList<DocketListResponseModel>>) responseBody;
                processResponse(docketResponse);
                break;
        }

    }

    @Override
    public void onApiError(String endPointName, String errorMessage) {
        printErrorLog(TAG, errorMessage);

        // stop swipe refresh layout
        stopSwipeLayout();

        switch (endPointName) {

            case ApiConstant.API_DOCKET_LIST:

                // show error toast message
                displayLongToast(mContext, errorMessage);

                // call method to show no data image
                showNoData();
                break;
        }
    }

    @Override
    public void onApiFailure(String endPointName, String failureMessage) {

        printErrorLog(TAG, failureMessage);

        switch (endPointName) {

            case ApiConstant.API_DOCKET_LIST:

                // show error toast message
                displayLongToast(mContext, failureMessage);

                // call method to show no data image
                showNoData();
                break;
        }

    }

    private void processResponse(CommonResponse<ArrayList<DocketListResponseModel>> docketResponse) {

        try {

            switch (docketResponse.getStatus()) {

                case AppConstants.STATUS_SUCCESS:

                    // call method to bind docket list
                    bindDocketList(docketResponse);
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

    private void bindDocketList(CommonResponse<ArrayList<DocketListResponseModel>> docketResponse) {

        try {

            ArrayList<DocketListResponseModel> tempDocketList = docketResponse.getData();

            if (tempDocketList != null && tempDocketList.size() > 0)
                docketListResponseModelArrayList.addAll(tempDocketList);


            if (docketListResponseModelArrayList != null && docketListResponseModelArrayList.size() > 0) {

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

    private void showNoData() {

        if (docketListResponseModelArrayList != null && docketListResponseModelArrayList.size() == 0) {

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

    private void filter(String text) {

        ArrayList<DocketListResponseModel> filtered = new ArrayList<>();

        for (DocketListResponseModel s : docketListResponseModelArrayList) {
            if (
                    s.getDocketNo().toLowerCase().contains((text.toLowerCase()))
                            || s.getCustomerType().toLowerCase().contains((text.toLowerCase()))
                            || s.getDispatchMode().toLowerCase().contains((text.toLowerCase()))
                            || s.getPaymentType().toLowerCase().contains((text.toLowerCase()))
                            || s.getOrigin().toLowerCase().contains((text.toLowerCase()))
                            || s.getDestination().toLowerCase().contains((text.toLowerCase()))

            ) {
                filtered.add(s);
            }
        }

        baseAdapter.filterList(filtered);
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

                    baseAdapter = new BaseAdapter(mContext, docketListResponseModelArrayList, R.layout.item_docket_list, this);
                    binding.rvCommanListLayout.setAdapter(baseAdapter);

                }
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
        DocketRequestModel.DocketList docketList = (DocketRequestModel.DocketList) object;
        navigateToBookingDetails(id, true);
    }

    @Override
    public void onInfoClick(int itemPosition, Object object, View... views) {

    }

    @Override
    public void onEditClick(int itemPosition, Object object) {

    }

    @Override
    public void onDeleteClick(int itemPosition, Object object) {

    }
}