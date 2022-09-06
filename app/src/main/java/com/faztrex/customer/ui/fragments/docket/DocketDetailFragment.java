package com.faztrex.customer.ui.fragments.docket;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.faztrex.customer.R;
import com.faztrex.customer.adapters.DocketDimensionAdapter;
import com.faztrex.customer.baseclasses.BaseActivity;
import com.faztrex.customer.baseclasses.BaseFragment;
import com.faztrex.customer.customviews.SimpleAlertDialog;
import com.faztrex.customer.databinding.FragmentDocketDetailsBinding;
import com.faztrex.customer.network.model.request.CommanRequestModel;
import com.faztrex.customer.network.response.CommonListResponse;
import com.faztrex.customer.network.response.CommonResponse;
import com.faztrex.customer.network.response.docket.Consignor;
import com.faztrex.customer.network.response.docket.Dimension;
import com.faztrex.customer.network.response.docket.DocketCalculation;
import com.faztrex.customer.network.response.docket.DocketDetail;
import com.faztrex.customer.retrofit.ApiClient;
import com.faztrex.customer.retrofit.ApiConstant;
import com.faztrex.customer.retrofit.ApiListener;
import com.faztrex.customer.retrofit.ApiManager;
import com.faztrex.customer.retrofit.ApiService;
import com.faztrex.customer.ui.activities.transaction.docket.DocketBookingActivity;
import com.faztrex.customer.utils.AppConstants;
import com.faztrex.customer.utils.NetworkUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;

import static com.faztrex.customer.baseclasses.BaseActivity.hideView;
import static com.faztrex.customer.baseclasses.BaseActivity.startProgressDialog;
import static com.faztrex.customer.baseclasses.BaseActivity.visibleView;
import static com.faztrex.customer.utils.AppUtils.castToInteger;
import static com.faztrex.customer.utils.AppUtils.convertDateFormat;
import static com.faztrex.customer.utils.AppUtils.getCurrentDate;
import static com.faztrex.customer.utils.AppUtils.getStringValue;


public class DocketDetailFragment extends BaseFragment implements
        DocketDimensionAdapter.OnDimensionClickListener,
        ApiListener {
    //AdapterView.OnItemSelectedListener,
    // PermissionGrantedListener,

    // MasterItemSelectListener {

    private final String TAG = this.getClass().getSimpleName();

    private final String CUSTOMER_TYPE_CREDIT = "1";
    private final String CUSTOMER_TYPE_RETAIL = "2";
    private final String MODE_TYPE_SURFACE = "1";
    private final String MODE_TYPE_AIR = "2";
    private final String PAYMENT_TYPE_PAID = "1";
    private final String PAYMENT_TYPE_TO_PAY = "2";
    private final String PAYMENT_TYPE_TO_BE_BILLED = "3";
    private final String RISK_TYPE_OWNER = "1";
    private final String RISK_TYPE_CARRIER = "2";
    private final String PARCEL_TYPE_BOOKING = "1";
    private final String DOCUMENT_TYPE_BOOKING = "2";

    private final String AUTO_TYPE = "1";
    private final String MANUAL_TYPE = "2";

    private final String VOLUMETRIC_WISE = "1";
    private final String ACTUAL_WEIGHT_WISE = "2";


    private final int REQUEST_CODE_CAMERA = 1001;
    private final String postcode = "";
    private final String postcodeTypeName = "";
    private final String stateName = "";
    private final int postcodeTypeId = 0;
    private final boolean firstTime = true;
    private final String docketAutoNo = "";
    private final String consignorCode = "";
    private final String consignorName = "";
    public ArrayList<CommonListResponse> postcodeListMaster = new ArrayList<>();
    private FragmentDocketDetailsBinding binding;
    //private DialogDocketDimensionDetailBinding dialogBinding;
    private DocketDimensionAdapter dimensionAdapter;
    private ArrayList<Dimension> dimensionList = new ArrayList<>();
    private String customerType = CUSTOMER_TYPE_CREDIT;
    private String dispatchMode = MODE_TYPE_SURFACE;
    private String paymentType = PAYMENT_TYPE_PAID;
    private String riskType = RISK_TYPE_OWNER;
    private String bookingType = PARCEL_TYPE_BOOKING;
    private String docketType = AUTO_TYPE;
    private String weightType = VOLUMETRIC_WISE;
    private final boolean isAddDimentionDetail = true;
    private String docketId = "";
    private String isSezGst = "0";
    private int consignorId = 0;
    private int stateId = 0;
    private int cityId = 0;
    private int postcodeId = 0;
    private int deliveryTypeId = 0;
    private int verticalId = 0;
    private int packingTypeId = 0;
    private int departmentId = 0;
    private boolean isEditable = false;
    private boolean isReadOnly = false;
    private boolean fmCreated = false;
    private final boolean isForCalculation = false;
    private boolean isPressCalculate = false;
    //private boolean isAddressUploadViaImage = true; //10/03/2021
    private boolean isAddressUploadViaImage = false; //10/03/2021
    //private ArrayList<Consignor> consignorList = new ArrayList<>();
    // model class to store docket calculation response
    private DocketCalculation docketCalculation;
    // model class to store docket detail in edit mode
    private DocketDetail docketDetail;
    private String addressFilePath = "";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_docket_details, container, false);

        // hide keyboard
        requireActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        // get intent values
        isEditable = getActivity().getIntent().getBooleanExtra(getActivity().getResources().getString(R.string.key_is_edit), false);
        isReadOnly = getActivity().getIntent().getBooleanExtra(getActivity().getResources().getString(R.string.key_read_only), false);
        docketId = getActivity().getIntent().getStringExtra(getActivity().getResources().getString(R.string.key_docket_id));

        // get preference data
        getPreferenceData();

        // call method to start operational flow
        doYourWork();

        return binding.getRoot();
    }

    // region initial method
    @SuppressLint("NonConstantResourceId")
    private void doYourWork() {

        try {

            // set layout manager for dimension recycler view
            binding.recyclerViewDimensionDetails.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
            binding.recyclerViewDimensionDetails.setItemAnimator(new DefaultItemAnimator());

            // set adapter for recycler view
            dimensionAdapter = new DocketDimensionAdapter(dimensionList, DocketDetailFragment.this, isReadOnly, fmCreated);
            binding.recyclerViewDimensionDetails.setAdapter(dimensionAdapter);

            getPreferenceData();

            if (isEditable) {

                // disable docket number field
                BaseActivity.disableView(binding.tnlDocketNo.getEditText());
                BaseActivity.disableView(binding.tnlBookingDate.getEditText());

                // call method to get docket details from docket Id
                getDocketDetails();

            } else {

                // enable docket number field
                BaseActivity.enableView(binding.tnlDocketNo.getEditText());
                BaseActivity.enableView(binding.tnlBookingDate.getEditText());

                // set booking date
                Objects.requireNonNull(binding.tnlBookingDate.getEditText()).setText(getCurrentDate(AppConstants.CALENDAR_DATE_FORMAT));

                // set origin
                Objects.requireNonNull(binding.tnlOrigin.getEditText()).setText(getStringValue(prefUserModel.getBranchName()).toUpperCase());


                // set components
                hideView(binding.rvContainerPaymentType);//Change Dt-10/03/2021
//                visibleView(binding.rvContainerConsignorDetails);
//                visibleView(binding.rvContainerDepartmentDetails); //New Code 11-03-2021
                hideView(binding.tnlConsignor);
//                visibleView(binding.rvContainerVerticalDetails);
//                BaseActivity.disableView(binding.spinnerPackingType);
                BaseActivity.disableView(binding.tnlProduct.getEditText());

                BaseActivity.disableView(binding.tnlConsignorAddress.getEditText());
                BaseActivity.disableView(binding.tnlConsignorPostcode.getEditText());
                BaseActivity.disableView(binding.tnlConsignorMobileNumber.getEditText());
                BaseActivity.disableView(binding.tnlConsignorGstNumber.getEditText());

                binding.rbTypeOwner.setChecked(true);

                //Set default value
                customerType = CUSTOMER_TYPE_CREDIT;
                dispatchMode = MODE_TYPE_SURFACE;
                paymentType = PAYMENT_TYPE_TO_BE_BILLED;
                riskType = RISK_TYPE_OWNER;
                bookingType = PARCEL_TYPE_BOOKING;
                docketType = AUTO_TYPE;

            }

            // region customer type selection
            binding.rbGroupCustomerType.setOnCheckedChangeListener((group, checkedId) -> {

                switch (checkedId) {

                    case R.id.rb_type_credit:

                        hideView(binding.rvContainerPaymentType);
                        //  visibleView(binding.rvContainerConsignorDetails);
                        //visibleView(binding.rvContainerDepartmentDetails);//Change 11-03-2021
                        hideView(binding.tnlConsignor);
                        //visibleView(binding.rvContainerVerticalDetails);
                        //BaseActivity.disableView(binding.spinnerVertical);
                        BaseActivity.disableView(binding.tnlPackingType);
                        BaseActivity.disableView(binding.tnlProduct.getEditText());

                        BaseActivity.disableView(binding.tnlConsignorAddress.getEditText());
                        BaseActivity.disableView(binding.tnlConsignorPostcode.getEditText());
                        BaseActivity.disableView(binding.tnlConsignorMobileNumber.getEditText());
                        BaseActivity.disableView(binding.tnlConsignorGstNumber.getEditText());

                        binding.rbTypeOwner.setChecked(true);

                        customerType = CUSTOMER_TYPE_CREDIT;
                        paymentType = PAYMENT_TYPE_TO_BE_BILLED;
                        riskType = RISK_TYPE_OWNER;

                        break;

                    case R.id.rb_type_retail:

                        visibleView(binding.rvContainerPaymentType);
                        // hideView(binding.rvContainerConsignorDetails);
                        //  hideView(binding.rvContainerDepartmentDetails);//Change 11-03-2021
                        visibleView(binding.tnlConsignor);
                        //visibleView(binding.rvContainerVerticalDetails);
                        BaseActivity.enableView(binding.tnlPackingType);
                        BaseActivity.enableView(binding.tnlProduct.getEditText());

                        BaseActivity.enableView(binding.tnlConsignorAddress.getEditText());
                        BaseActivity.enableView(binding.tnlConsignorPostcode.getEditText());
                        BaseActivity.enableView(binding.tnlConsignorMobileNumber.getEditText());
                        BaseActivity.enableView(binding.tnlConsignorGstNumber.getEditText());

                        binding.rbTypePaid.setChecked(true);
                        binding.rbTypeCarrier.setChecked(true);

                        customerType = CUSTOMER_TYPE_RETAIL;
                        paymentType = PAYMENT_TYPE_PAID;
                        riskType = RISK_TYPE_CARRIER;
                        break;

                }
            });
            // endregion

            //region dispatch mode selection
            binding.rbGroupDispatchMode.setOnCheckedChangeListener((group, checkedId) -> {

                switch (checkedId) {

                    case R.id.rb_mode_surface:
                        dispatchMode = MODE_TYPE_SURFACE;
                        break;

                    case R.id.rb_mode_air:
                        dispatchMode = MODE_TYPE_AIR;
                        break;
                }
            });
            // endregion

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }
    // endregion

    // endregion

    // region click listener for dimension details
    @Override
    public void onUpdate(int itemPosition, Dimension dimension) {
    }

    @Override
    public void onDelete(int itemPosition, Dimension dimension) {
        displayConfirmationDialog(itemPosition);
    }
    // endregion

    private void displayConfirmationDialog(int itemPosition) {

        simpleAlertDialog = new SimpleAlertDialog(getActivity()) {

            @Override
            public boolean setDialogCancelable() {
                return false;
            }

            @Override
            public String setDialogTitle() {
                return requireActivity().getResources().getString(R.string.dialog_title_confirmation);
            }

            @Override
            public String setDialogMessage() {
                return requireActivity().getResources().getString(R.string.dialog_msg_dimension_delete_confirmation);
            }

            @Override
            public Drawable setDialogIcon() {
                return requireActivity().getResources().getDrawable(R.mipmap.ic_launcher);
            }

            @Override
            public String setDialogPositiveButtonText() {
                return requireActivity().getResources().getString(R.string.btn_title_proceed);
            }

            @Override
            public DialogInterface.OnClickListener onDialogPositiveButtonClick() {

                return (dialog, which) -> {

                    // removed item from list
                    dimensionList.remove(itemPosition);
                    dimensionAdapter.notifyItemRemoved(itemPosition);
                    dimensionAdapter.notifyItemRangeChanged(itemPosition, dimensionList.size());
                    isPressCalculate = false;


                    // call API to calculate dimension details
                    //calculateDimensions();
                };
            }

            @Override
            public String setDialogNegativeButtonText() {
                return requireActivity().getResources().getString(R.string.btn_title_cancel);
            }

            @Override
            public DialogInterface.OnClickListener onDialogNegativeButtonClick() {
                return (dialog, which) -> dialog.dismiss();
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

    // region get docket details from docket ID
    private void getDocketDetails() {

        try {

            if (NetworkUtils.isConnected(getActivity())) {

                // start progress indicator
                startProgressDialog(getActivity(), false);

                CommanRequestModel requestModel = new CommanRequestModel();
                requestModel.setId(docketId);

                String requestString = new Gson().toJson(requestModel);

                printInfoLog(TAG, requestString);

                ApiService apiService = ApiClient.createService(ApiService.class, "", "");
                Call<CommonResponse<DocketDetail>> call = apiService.getDocketById(requestModel);

                // call API
                ApiManager.callRetrofit(call, ApiConstant.API_GET_DOCKET_DETAIL, this, false);

            } else {
                displayInternetToastMessage(getActivity());
            }

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }
    // endregion

    //region Response handler listener

    // region API success listener
    @Override
    public void onApiSuccess(String endPointName, Object responseBody) {

        switch (endPointName) {

            case ApiConstant.API_GET_DOCKET_DETAIL:

                CommonResponse<DocketDetail> docketDetailResponse = (CommonResponse<DocketDetail>) responseBody;
                processDocketDetailResponse(docketDetailResponse);
                break;
        }
    }
    // endregion

    // region API error listener
    @Override
    public void onApiError(String endPointName, String errorMessage) {

        switch (endPointName) {

            case ApiConstant.API_GET_DOCKET_DETAIL:

                printErrorLog(TAG, errorMessage);
                break;
        }
    }
    // endregion

    // region API failure listener
    @Override
    public void onApiFailure(String endPointName, String failureMessage) {

        switch (endPointName) {

            case ApiConstant.API_GET_DOCKET_DETAIL:

                printErrorLog(TAG, failureMessage);
                break;


        }
    }
    // endregion

    //endregion

    private void processDocketDetailResponse(CommonResponse<DocketDetail> response) {

        try {

            if (response != null) {

                switch (response.getStatus()) {

                    case AppConstants.STATUS_SUCCESS:

                        // call method to set docket details
                        setDocketDetails(response.getData());
                        break;

                    case AppConstants.STATUS_FAILURE:

                        printErrorLog(TAG, response.getMessage());
                        displayShortToast(getActivity(), requireActivity().getResources().getString(R.string.err_msg_api_response_failure));
                        break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    private String concatAddress(Consignor consignor) {

        if (consignor != null) {

            String addressLine1 = getStringValue(consignor.getAdd1());
            String addressLine2 = getStringValue(consignor.getAdd2());
            String addressLine3 = getStringValue(consignor.getAdd3());

            return addressLine1.concat(addressLine2.concat(addressLine3)).trim();
        }
        return "";
    }

    private void setDocketDetails(DocketDetail docketDetail) {

        try {

            if (docketDetail != null) {

                this.docketDetail = docketDetail;

                fmCreated = castToInteger(getStringValue(docketDetail.getfMId())) > 0;

                // hide view if FM is created
                if (fmCreated) {
                    hideView(binding.btnAddDimension);
                    hideView(binding.btnSave);
                    hideView(binding.btnCalculate);
                }

                consignorId = castToInteger(docketDetail.getConsignorId());
                stateId = castToInteger(docketDetail.getDestinationStateId());
                cityId = castToInteger(docketDetail.getDestinationId());
                postcodeId = castToInteger(docketDetail.getDestinationPostCodeId());
                deliveryTypeId = castToInteger(docketDetail.getDeliveryType());
                verticalId = castToInteger(docketDetail.getVerticleTypeId());
                packingTypeId = castToInteger(docketDetail.getPackingTypeId());
                departmentId = castToInteger(docketDetail.getDepartmentId());

                customerType = getStringValue(docketDetail.getCustomerType());

                isSezGst = getStringValue(docketDetail.getIsConsignorSEZ()).equalsIgnoreCase("1") ? "1" : "0";

                switch (customerType) {

                    case CUSTOMER_TYPE_CREDIT:

                        binding.rbTypeCredit.setChecked(true);

                        hideView(binding.rvContainerPaymentType);
                        //  visibleView(binding.rvContainerConsignorDetails);
                        //  visibleView(binding.rvContainerDepartmentDetails);
                        hideView(binding.tnlConsignor);
                        // BaseActivity.hideView(binding.tnlDepartment);//New Code Dt - 11/03/2021
                        //visibleView(binding.rvContainerVerticalDetails);
                        BaseActivity.disableView(binding.tnlVertical);
                        BaseActivity.disableView(binding.tnlPackingType);
                        BaseActivity.disableView(binding.tnlProduct.getEditText());

                        BaseActivity.disableView(binding.tnlConsignorAddress.getEditText());
                        BaseActivity.disableView(binding.tnlConsignorPostcode.getEditText());
                        BaseActivity.disableView(binding.tnlConsignorMobileNumber.getEditText());
                        BaseActivity.disableView(binding.tnlConsignorGstNumber.getEditText());

                        break;

                    case CUSTOMER_TYPE_RETAIL:

                        binding.rbTypeRetail.setChecked(true);

                        visibleView(binding.rvContainerPaymentType);
                        //hideView(binding.rvContainerConsignorDetails);
                        //hideView(binding.rvContainerDepartmentDetails);
                        visibleView(binding.tnlConsignor);
                        //hideView(binding.rvContainerVerticalDetails);
                        BaseActivity.enableView(binding.tnlPackingType);
                        BaseActivity.enableView(binding.tnlProduct.getEditText());
                        BaseActivity.enableView(binding.tnlConsignorAddress.getEditText());
                        BaseActivity.enableView(binding.tnlConsignorPostcode.getEditText());
                        BaseActivity.enableView(binding.tnlConsignorMobileNumber.getEditText());
                        BaseActivity.enableView(binding.tnlConsignorGstNumber.getEditText());

                        break;
                }

                dispatchMode = getStringValue(docketDetail.getDispatchMode());
                paymentType = getStringValue(docketDetail.getPaymentType());
                riskType = getStringValue(docketDetail.getRiskType());
                bookingType = getStringValue(docketDetail.getBookingType());
                docketType = getStringValue(docketDetail.getDocketType());
                weightType = getStringValue(docketDetail.getWeightType());

                switch (dispatchMode) {
                    case MODE_TYPE_SURFACE:
                        binding.rbModeSurface.setChecked(true);
                        break;

                    case MODE_TYPE_AIR:
                        binding.rbModeAir.setChecked(true);
                        break;
                }

                switch (paymentType) {
                    case PAYMENT_TYPE_PAID:
                        binding.rbTypePaid.setChecked(true);
                        break;

                    case PAYMENT_TYPE_TO_PAY:
                        binding.rbTypeToPay.setChecked(true);
                        break;

                    case PAYMENT_TYPE_TO_BE_BILLED:
                        binding.rbTypeToBeBilled.setChecked(true);
                        break;
                }

                switch (riskType) {
                    case RISK_TYPE_OWNER:
                        binding.rbTypeOwner.setChecked(true);
                        break;

                    case RISK_TYPE_CARRIER:
                        binding.rbTypeCarrier.setChecked(true);
                        break;
                }


                Objects.requireNonNull(binding.tnlDocketNo.getEditText()).setText(getStringValue(docketDetail.getDocketNo()));
                //Objects.requireNonNull(binding.tnlBookingDate.getEditText()).setText(convertDateFormat(AppConstants.API_DATE_FORMAT, AppConstants.CALENDAR_DATE_FORMAT, getStringValue(docketDetail.getBookingDate())));
                Objects.requireNonNull(binding.tnlBookingDate.getEditText()).setText(convertDateFormat(AppConstants.API_DATE_FORMAT, AppConstants.CALENDAR_DATE_FORMAT, getStringValue(docketDetail.getBookingDate())));
                Objects.requireNonNull(binding.tnlOrigin.getEditText()).setText(getStringValue(docketDetail.getBookingBranchName()));
                Objects.requireNonNull(binding.tnlPostcodeType.getEditText()).setText(getStringValue(docketDetail.getDestinationPostCodeType()));
                //Objects.requireNonNull(binding.tnlEstimatedDeliveryDate.getEditText()).setText(convertDateFormat(AppConstants.API_DATE_FORMAT, AppConstants.CALENDAR_DATE_FORMAT, getStringValue(docketDetail.getEstimatedDeliveryDate())));
                //Objects.requireNonNull(binding.tnlActualDeliveryDate.getEditText()).setText(convertDateFormat(AppConstants.API_DATE_FORMAT, AppConstants.CALENDAR_DATE_FORMAT, getStringValue(docketDetail.getActualDeliveryDate())));
                Objects.requireNonNull(binding.tnlEstimatedDeliveryDate.getEditText()).setText(convertDateFormat(AppConstants.API_DATE_FORMAT, AppConstants.CALENDAR_DATE_FORMAT, getStringValue(docketDetail.getEstimatedDeliveryDate())));
                Objects.requireNonNull(binding.tnlActualDeliveryDate.getEditText()).setText(convertDateFormat(AppConstants.API_DATE_FORMAT, AppConstants.CALENDAR_DATE_FORMAT, getStringValue(docketDetail.getActualDeliveryDate())));

                Objects.requireNonNull(binding.tnlConsignor.getEditText()).setText(getStringValue(docketDetail.getConsignorName()));
                Objects.requireNonNull(binding.tnlConsignorAddress.getEditText()).setText(getStringValue(docketDetail.getConsignorAddress()));
                Objects.requireNonNull(binding.tnlConsignorPostcode.getEditText()).setText(getStringValue(docketDetail.getConsignorPostCode()));
                Objects.requireNonNull(binding.tnlConsignorMobileNumber.getEditText()).setText(getStringValue(docketDetail.getConsignorMobileNo()));
                Objects.requireNonNull(binding.tnlConsignorGstNumber.getEditText()).setText(getStringValue(docketDetail.getConsignorGSTNo()));
                Objects.requireNonNull(binding.tnlConsignee.getEditText()).setText(getStringValue(docketDetail.getConsigneeName()));

                Objects.requireNonNull(binding.tnlConsigneeAddress.getEditText()).setText(getStringValue(docketDetail.getConsigneeAddress()));
                Objects.requireNonNull(binding.tnlConsigneePostcode.getEditText()).setText(getStringValue(docketDetail.getConsigneePostCode()));
                Objects.requireNonNull(binding.tnlConsigneeMobileNumber.getEditText()).setText(getStringValue(docketDetail.getConsigneeMobileNo()));
                Objects.requireNonNull(binding.tnlConsigneeGstNumber.getEditText()).setText(getStringValue(docketDetail.getConsigneeGSTNo()));

                Objects.requireNonNull(binding.tnlInvoiceNumber.getEditText()).setText(getStringValue(docketDetail.getInvoiceNo()));
                Objects.requireNonNull(binding.tnlInvoiceAmount.getEditText()).setText(getStringValue(docketDetail.getInvoiceValue()));
                Objects.requireNonNull(binding.tnlPoNumber.getEditText()).setText(getStringValue(docketDetail.getpONo()));
                Objects.requireNonNull(binding.tnlEwayBillNumber.getEditText()).setText(getStringValue(docketDetail.getEwayBillNo()));
                Objects.requireNonNull(binding.tnlProduct.getEditText()).setText(getStringValue(docketDetail.getProductName()));
                //  binding.tvTotalNoOfBoxes.setText(getStringValue(docketDetail.getNoOfPackages()));
                binding.tvActualWeight.setText(getStringValue(docketDetail.getActualWeight()));
                binding.tvVolumetricWeight.setText(getStringValue(docketDetail.getVolumetricWeight()));
                binding.tvChargeableWeight.setText(getStringValue(docketDetail.getChargeWeight()));
                binding.tvChargeablePercentageWeight.setText(getStringValue(docketDetail.getChargeWeightPercentage()));

                //New Change 15-03-2021
                Objects.requireNonNull(binding.tnlDestinationState.getEditText()).setText(getStringValue(docketDetail.getDestinationStateName()));
                Objects.requireNonNull(binding.tnlDestinationCity.getEditText()).setText(getStringValue(docketDetail.getDestinationCityName()));
                Objects.requireNonNull(binding.tnlPostcode.getEditText()).setText(getStringValue(docketDetail.getDestinationPostCode()));
                Objects.requireNonNull(binding.tnlVertical.getEditText()).setText(getStringValue(docketDetail.getVerticleTypeName()));
                Objects.requireNonNull(binding.tnlPackingType.getEditText()).setText(getStringValue(docketDetail.getPackingTypeName()));
                Objects.requireNonNull(binding.tnlNoOfBoxes.getEditText()).setText(getStringValue(docketDetail.getNoOfPackages()));

                addressFilePath = docketDetail.getAddressPath();

                //New Code Dt.- 10/03/2021
                //binding.rbTypeText.setChecked(true);
                isAddressUploadViaImage = false;

                // set dimension list
                dimensionList = docketDetail.getListDocketDimension();

                if (dimensionList != null && dimensionList.size() > 0) {

                    dimensionAdapter = new DocketDimensionAdapter(dimensionList, DocketDetailFragment.this, isReadOnly, fmCreated);
                    binding.recyclerViewDimensionDetails.setAdapter(dimensionAdapter);
                    visibleView(binding.btnCalculate);//New Change 11-03-2020
                }
                // hide view if it is read only mode
                if (isReadOnly) {

                    BaseActivity.disableMultipleViews(binding.mainContainer);
                    hideView(binding.btnAddDimension);
                    hideView(binding.btnCalculate);
                    hideView(binding.btnSave);
                }

                // set docket charges
                docketCalculation = new DocketCalculation();
                docketCalculation.setBasicChargeAmount(docketDetail.getBasicChargeAmount());
                docketCalculation.setValueSurchargeAmount(docketDetail.getValueSurchargeAmount());
                docketCalculation.setDocketChargeAmount(docketDetail.getDocketChargeAmount());
                docketCalculation.setGreenChargeAmount(docketDetail.getGreenChargeAmount());
                docketCalculation.setDeliveryChargeAmount(docketDetail.getDeliveryChargeAmount());
                docketCalculation.setOdaChargesAmount(docketDetail.getoDAChargesAmount());
                docketCalculation.setToPayChargesAmount(docketDetail.getToPayChargesAmount());
                docketCalculation.setPkgHandlingChargeAmount(docketDetail.getPkgHandlingChargeAmount());
                docketCalculation.setHardCopyChargeAmount(docketDetail.getHardCopyChargeAmount());
                docketCalculation.setSubTotalAmount(docketDetail.getSubTotalAmount());
                docketCalculation.setSurchargeAmount(docketDetail.getSurchargeAmount());
                docketCalculation.setTotalFreightAmount(docketDetail.getTotalFreightAmount());
                docketCalculation.setSgstPercentage(docketDetail.getsGSTPercentage());
                docketCalculation.setSgstAmount(docketDetail.getsGSTAmount());
                docketCalculation.setCgstPercentage(docketDetail.getcGSTPercentage());
                docketCalculation.setCgstAmount(docketDetail.getcGSTAmount());
                docketCalculation.setIgstPercentage(docketDetail.getiGSTPercentage());
                docketCalculation.setIgstAmount(docketDetail.getiGSTAmount());
                docketCalculation.setTotalGstAmount(docketDetail.getTotalGSTAmount());
                docketCalculation.setNetAmount(docketDetail.getNetAmount());

                // call method from other fragment to set docket charges
                DocketChargesFragment.setDocketCharges(docketCalculation);
            }

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    private void setDocketCharges() {

        try {

            if (weightType.equals("2")) {
                //binding.tvTotalNoOfBoxes.setText(getStringValue(docketCalculation.getNoOfPackages()));
                binding.tvActualWeight.setText(getStringValue(docketCalculation.getActualWeight()));
                binding.tvVolumetricWeight.setText(getStringValue(docketCalculation.getVolumetricWeight()));
                binding.tvChargeableWeight.setText(getStringValue(docketCalculation.getChargeWeight()));
                binding.tvChargeablePercentageWeight.setText(getStringValue(docketCalculation.getChargeWeightPercentage()));
            }

            ((DocketBookingActivity) requireActivity()).binding.viewPagerBookingDetails.setCurrentItem(1);

            // call method from other fragment to set docket charges
            DocketChargesFragment.setDocketCharges(docketCalculation);

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }
}