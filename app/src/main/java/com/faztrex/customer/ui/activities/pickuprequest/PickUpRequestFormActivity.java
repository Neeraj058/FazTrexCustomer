package com.faztrex.customer.ui.activities.pickuprequest;

import static com.cittasolutions.cittalibrary.utils.AppUtils.errorLog;
import static com.faztrex.customer.retrofit.ApiConstant.API_PICKUP_COUPON;
import static com.faztrex.customer.retrofit.ApiConstant.API_PICKUP_OTP;
import static com.faztrex.customer.retrofit.ApiConstant.API_PICKUP_REQUEST_EDIT;
import static com.faztrex.customer.retrofit.ApiConstant.API_PICKUP_REQUEST_MANAGE;
import static com.faztrex.customer.retrofit.ApiConstant.API_PICKUP_REQUEST_OTHER_CHARGES;
import static com.faztrex.customer.retrofit.ApiConstant.API_PICKUP_VERIFYOTP;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.faztrex.customer.R;
import com.faztrex.customer.baseclasses.BaseActivity;
import com.faztrex.customer.baseclasses.BaseAdapter;
import com.faztrex.customer.customviews.MyDatePicker;
import com.faztrex.customer.databinding.ActivityPickUpRequestFormBinding;
import com.faztrex.customer.databinding.DialogDimensionDetailBinding;
import com.faztrex.customer.listeners.bottomsheet.MasterBottomSheetActionListener;
import com.faztrex.customer.listeners.general.CommonActionListener;
import com.faztrex.customer.models.pickuprequest.CouponCode;
import com.faztrex.customer.models.pickuprequest.EditDimensionDetailModel;
import com.faztrex.customer.models.pickuprequest.EditPickUpRequestModel;
import com.faztrex.customer.models.pickuprequest.EditPickupRequestFormModel;
import com.faztrex.customer.models.pickuprequest.OtherCharges;
import com.faztrex.customer.models.pickuprequest.PickupRequestDetailModel;
import com.faztrex.customer.models.pickuprequest.PickupRequestFormModel;
import com.faztrex.customer.network.model.request.CommanRequestModel;
import com.faztrex.customer.network.response.CommonListResponse;
import com.faztrex.customer.network.response.CommonResponse;
import com.faztrex.customer.network.response.user.ConsignorInformationModel;
import com.faztrex.customer.retrofit.ApiClient;
import com.faztrex.customer.retrofit.ApiConstant;
import com.faztrex.customer.retrofit.ApiListener;
import com.faztrex.customer.retrofit.ApiManager;
import com.faztrex.customer.retrofit.ApiService;
import com.faztrex.customer.ui.fragments.bottomsheet.BottomSheetDimensionDetail;
import com.faztrex.customer.utils.AppConstants;
import com.faztrex.customer.utils.AppUtils;
import com.faztrex.customer.utils.NetworkUtils;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Timer;

import retrofit2.Call;

public class PickUpRequestFormActivity extends BaseActivity implements View.OnClickListener,
        CommonActionListener, MasterBottomSheetActionListener, ApiListener, TextWatcher {

    private final String TAG = this.getClass().getSimpleName();

    private final Context mContext = this;

    private final Double cft = 0.0;

    private final ArrayList<PickupRequestDetailModel> dimensionDetailModelArrayList = new ArrayList<>();
    private final long DELAY = 10000; // in ms
    int dimensionItemPosition = -1;
    private Map<String, Integer> inputParams;
    private String selectedPaymentValue = "Prepaid";
    private int destinationStateId = 0;
    private int destinationCityId = 0;
    private ActivityPickUpRequestFormBinding binding;
    private Dialog mDialog;
    private DialogDimensionDetailBinding dialogDimensionDetailBinding;
    private BaseAdapter baseAdapter;
    private MasterBottomSheetActionListener masterBottomSheetActionListener;
    private Integer pickupRequestId;
    private int stateId = 0;
    private int cityId = 0;
    private int postcodeId = 0;
    private int postcodeTypeId = 0;
    private int destinationPostcodeId = 0;
    private int destinationPostcodeTypeId = 0;
    private int paymentId = 0;
    private String paymentType;
    private int verticalId = 0;
    private int parkingId = 0;
    private Double boxes = 0.0;
    private Double length = 0.0;
    private Double width = 0.0;
    private Double height = 0.0;
    private Double actualWeight = 0.0;
    private Double actualWeightPerBox = 0.0;
    private Double volumetricWeight = 0.0;
    private Double chargeableWeight = 0.0;
    private Double chargeableWeightPer = 0.0;
    private Double basicChargeAmount = 0.0;
    private double totalActualWeight;
    private double totalVolumetricWeight;
    private int totalBoxes;
    private boolean isView;
    private boolean isOTPverified = false;
    private boolean isFromCoupon = false;
    private BottomSheetDimensionDetail bottomSheetFragment;
    private Timer timer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_pick_up_request_form);

        binding.toolbar.tvHeaderTitle.setText(R.string.pick_up_form);

        binding.btnAdd.setOnClickListener(this);

        binding.btnInfo.setOnClickListener(this);

        String pickId = getIntent().getStringExtra(AppConstants.PICK_UP_REQUEST_ID);
        String AutoNo = getIntent().getStringExtra("AutoNo");

        pickupRequestId = AppUtils.castToInteger(pickId);
        isView = AppUtils.getStringValue(getIntent().getStringExtra(AppConstants.MODE)).equalsIgnoreCase("view");

        if (pickupRequestId > 0) {
            editPickUpRequestList(pickupRequestId);
        }

        inputParams = new HashMap<>();

        getSpinnerList(AppConstants.SP_STATE, null);
        getSpinnerList(AppConstants.SP_STATE2, null);
        getSpinnerList(AppConstants.SP_PAYMENT_TYPE3, null);
        getSpinnerList(ApiConstant.API_VERTICLE, null);
        getSpinnerList(ApiConstant.API_PACKING_TYPE, null);

        binding.edtPaymentType.setOnClickListener(this);
        binding.edtBookingCountry.setOnClickListener(this);
        binding.edtBookingCity.setOnClickListener(this);
        binding.edtBookingPostCode.setOnClickListener(this);
        binding.edtDestinationCounty.setOnClickListener(this);
        binding.edtDestinationCity.setOnClickListener(this);
        binding.edtDestinationPostCode.setOnClickListener(this);
        binding.edtVerticle.setOnClickListener(this);
        binding.edtPackingType.setOnClickListener(this);
        binding.btnCouponCode.setOnClickListener(this);
        binding.btnOTP.setOnClickListener(this);

        int no = (AppUtils.castToInteger(AutoNo) + 1);


        Objects.requireNonNull(binding.tnlDate.getEditText()).setOnClickListener(this);
        Objects.requireNonNull(binding.tnlNo.getEditText()).setOnClickListener(this);
        binding.edtNo.setText(Integer.toString(no));
        binding.toolbar.ivBack.setOnClickListener(v -> finish());

        binding.toolbar.btnSave.setVisibility(View.VISIBLE);

        binding.toolbar.btnSave.setOnClickListener(this);

        binding.edtDate.setText(AppUtils.getCurrentDate(AppConstants.CALENDAR_DATE_FORMAT));

        Objects.requireNonNull(binding.tnlMobileNo.getEditText()).setText(prefUserModel.getMobileNo());
        Objects.requireNonNull(binding.tnlAddress.getEditText()).setText(prefUserModel.getBillGenAdd1() + ", " + prefUserModel.getBillGenAdd2());
        Objects.requireNonNull(binding.tnlPostcode.getEditText()).setText(prefUserModel.getBillGenPostCode());

        Objects.requireNonNull(binding.tnlConsignor.getEditText()).setText(prefUserModel.getCustomerName());

        binding.toggleGroupPaymentMode.addOnButtonCheckedListener((group, checkedId, isChecked) -> {
            MaterialButton btn = binding.toggleGroupPaymentMode.findViewById(checkedId);
            if (isChecked)
                selectedPaymentValue = btn.getText().toString();
        });
        try {
            binding.edtProductName.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        } catch (Exception e) {
            e.printStackTrace();
        }

        //  binding.tnlOTP.getEditText().addTextChangedListener(this);

        loadDimensionDetailsList();

    }

    private void editPickUpRequestList(int pickRequestId) {

        try {

            CommanRequestModel commanRequestModel = new CommanRequestModel();
            commanRequestModel.setId(String.valueOf(pickRequestId));

            String requestString = new Gson().toJson(commanRequestModel);
            Log.e(TAG, requestString);

            ApiService apiService = ApiClient.createService(ApiService.class, "", "");
            Call<CommonResponse<EditPickUpRequestModel>> call = apiService.editPickUpRequest(commanRequestModel);

            ApiManager.callRetrofit(call, API_PICKUP_REQUEST_EDIT, this, false);

        } catch (Exception e) {

            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());

        }
    }

    private void loadDimensionDetailsList() {

        binding.rvDimensionDetail.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false));
        baseAdapter = new BaseAdapter(mContext, dimensionDetailModelArrayList, R.layout.item_dimension_detail, this);
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(binding.rvDimensionDetail);
        binding.rvDimensionDetail.setAdapter(baseAdapter);

        totalBoxes = 0;
        totalActualWeight = 0;
        totalVolumetricWeight = 0;

        for (int i = 0; i < dimensionDetailModelArrayList.size(); i++) {
            totalBoxes += dimensionDetailModelArrayList.get(i).getBoxes();
            totalActualWeight += dimensionDetailModelArrayList.get(i).getActualWeight();
            totalVolumetricWeight += dimensionDetailModelArrayList.get(i).getVolumetricWeight();
        }

        Objects.requireNonNull(binding.tnlNoofBoxes.getEditText()).setText(String.valueOf(totalBoxes));
    }

    @Override
    public Activity setCurrentActivity() {
        return this;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnAdd:
                openAddDimensionDetailsDialog(null);

                break;

            case R.id.btnInfo:
                if (pickupRequestId > 0)
                    bottomSheetFragment = new BottomSheetDimensionDetail(pickupRequestDetailModelBottomSheet);
                else
                    bottomSheetFragment = new BottomSheetDimensionDetail(pickupRequestDetailModel);
                bottomSheetFragment.show(getSupportFragmentManager(), bottomSheetFragment.getTag());
                break;

            case R.id.edtBookingCountry:
                showFilterMasterBottomSheet(Objects.requireNonNull(binding.edtBookingCountry.getHint()).toString(), this, AppConstants.SP_STATE);
                break;

            case R.id.edtBookingCity:
                showFilterMasterBottomSheet(Objects.requireNonNull(binding.edtBookingCity.getHint()).toString(), this, AppConstants.SP_CITY);
                break;

            case R.id.edtBookingPostCode:
                showFilterMasterBottomSheet(Objects.requireNonNull(binding.edtBookingPostCode.getHint()).toString(), this, AppConstants.SP_POSTCODE);
                break;

            case R.id.edtDestinationCounty:
                showFilterMasterBottomSheet(Objects.requireNonNull(binding.edtDestinationCounty.getHint()).toString(), this, AppConstants.SP_STATE2);
                break;

            case R.id.edtDestinationCity:
                showFilterMasterBottomSheet(Objects.requireNonNull(binding.edtDestinationCity.getHint()).toString(), this, AppConstants.SP_CITY2);
                break;

            case R.id.edtDate:
                new MyDatePicker(binding.tnlDate, AppConstants.CALENDAR_DATE_FORMAT, true, 5);
                //openDatePickerDialog(binding.tnlDate, AppConstants.CALENDAR_DATE_FORMAT);
                break;

            case R.id.edtDestinationPostCode:
                showFilterMasterBottomSheet(Objects.requireNonNull(binding.edtDestinationPostCode.getHint()).toString(), this, AppConstants.SP_POSTCODE2);
                break;

            case R.id.edtPaymentType:
                showFilterMasterBottomSheet(Objects.requireNonNull(binding.edtPaymentType.getHint()).toString(), this, AppConstants.SP_PAYMENT_TYPE3);
                break;

            case R.id.edtVerticle:
                showFilterMasterBottomSheet(Objects.requireNonNull(binding.edtVerticle.getHint()).toString(), this, AppConstants.SP_VERTICAL);
                break;

            case R.id.edtPackingType:
                showFilterMasterBottomSheet(Objects.requireNonNull(binding.edtPackingType.getHint()).toString(), this, AppConstants.SP_PACKING_TYPE);
                break;

            case R.id.btnAddDialog:
                if (validateAddDimensionDetail())
                    AddDimensionDetails();
                break;

            case R.id.btnSave:
                if (dimensionDetailModelArrayList.size() > 0) {
                    if (validatePickRequestForm()) {
                        if (binding.toolbar.btnSave.isEnabled())
                            //setPickUpForm();
                            binding.toolbar.btnSave.setEnabled(false);
                        new Handler().postDelayed(() -> binding.toolbar.btnSave.setEnabled(true), 5000);
                    }
                } else
                    Snackbar.make(binding.getRoot().getRootView(), "Please Enter at least one Dimension Details!", Snackbar.LENGTH_LONG).show();
                break;
            case R.id.btnCouponCode:
                binding.btnCouponCode.setEnabled(false);
                if (binding.tnlCouponCode.getEditText().getText().toString().trim().isEmpty()) {

                    Snackbar.make(binding.getRoot().getRootView(), "Please Enter Coupon code", Snackbar.LENGTH_LONG).show();
                    binding.btnCouponCode.setEnabled(true);
                    isFromCoupon = false;
                } else {
                    isFromCoupon = true;
                    getNetPaybleAmount(binding.tnlCouponCode.getEditText().getText().toString().trim(), basicChargeAmount, isFromCoupon);
                }
                break;
            case R.id.btnOTP:
                generateOTP();
                break;
        }
    }

    private void generateOTP() {
        System.out.println("Welcome");
        try {
            Snackbar.make(binding.getRoot().getRootView(), "OTP has been sent to your registered mobile number.", Snackbar.LENGTH_LONG).show();
            CommanRequestModel requestModel = new CommanRequestModel();
            //  requestModel.setId(String.valueOf(hyperLocalId));
            requestModel.setCustomerid(prefUserModel.getId());
            System.out.println(prefUserModel.getId().toString());
            ApiService apiService = ApiClient.createService(ApiService.class);

            Call<CommonResponse> call = apiService.generateOTP(requestModel);

            ApiManager.callRetrofit(call, API_PICKUP_OTP, this, false);

        } catch (Exception e) {
            stopProgressDialog();
            e.printStackTrace();
            errorLog(e);
        }
    }

    private void setPickUpForm() {

        try {

            pickupRequestFormModel = new PickupRequestFormModel();

            if (pickupRequestId > 0)
                pickupRequestFormModel.setId(pickupRequestId);

            pickupRequestFormModel.setDate(AppUtils.convertDateFormat(AppConstants.CALENDAR_DATE_FORMAT, AppConstants.API_DATE_FORMAT, getTrimString(binding.tnlDate)));
            pickupRequestFormModel.setPaymentType(paymentId);
            pickupRequestFormModel.setBookingStateId(stateId);
            pickupRequestFormModel.setBookingCityId(cityId);
            pickupRequestFormModel.setBookingPostCodeId(postcodeId);
            pickupRequestFormModel.setDestinationStateId(destinationStateId);
            pickupRequestFormModel.setDestinationCityId(destinationCityId);

            pickupRequestFormModel.setDestinationPostCodeId(destinationPostcodeId);
            pickupRequestFormModel.setDestinationPostCodeType(getTrimString(binding.tnlPostcodeType));
            pickupRequestFormModel.setConsignorId(prefUserModel.getId());

            pickupRequestFormModel.setConsignorName(prefUserModel.getCustomerName());
            pickupRequestFormModel.setConsignorAddress(getTrimString(binding.tnlAddress));
            pickupRequestFormModel.setConsignorPostCode(getTrimString(binding.tnlPostcode));
            pickupRequestFormModel.setConsignorMobileNo(getTrimString(binding.tnlMobileNo));
            pickupRequestFormModel.setConsignorGSTNo(getTrimString(binding.tnlVATNo));

            pickupRequestFormModel.setConsigneeName(getTrimString(binding.tnlConsigneeOfConsignee));
            pickupRequestFormModel.setConsigneeAddress(getTrimString(binding.tnlAddressOfConsignee));
            pickupRequestFormModel.setConsigneePostCode(getTrimString(binding.tnlPostcodeOfConsignee));
            pickupRequestFormModel.setConsigneeMobileNo(getTrimString(binding.tnlMobileNoOfConsignee));
            pickupRequestFormModel.setConsigneeGSTNo(getTrimString(binding.tnlVATNoOfConsignee));

            pickupRequestFormModel.setVerticleTypeId(verticalId);
            pickupRequestFormModel.setProductName(getTrimString(binding.tnlProductName));
            pickupRequestFormModel.setPackingTypeId(parkingId);
            pickupRequestFormModel.setNoOfPackages(AppUtils.castToInteger(getTrimString(binding.tnlNoofBoxes)));
            pickupRequestFormModel.setActualWeight(actualWeight);
            pickupRequestFormModel.setChargeWeight(chargeableWeight);
            pickupRequestFormModel.setChargeWeightPercentage(chargeableWeightPer);
            pickupRequestFormModel.setBasicChargeAmount(basicChargeAmount);
            pickupRequestFormModel.setAutoNo(AppUtils.castToInteger(binding.edtNo.getText().toString().trim()));
            if (binding.radiobtnNormal.isChecked())
                pickupRequestFormModel.setPickUpRequestType("1");
            else if (binding.radiobtnHyperlocal.isChecked())
                pickupRequestFormModel.setPickUpRequestType("2");


            if (selectedPaymentValue.equalsIgnoreCase("Prepaid"))
                pickupRequestFormModel.setPaymentModeId(1);
            else if (selectedPaymentValue.equalsIgnoreCase("COD"))
                pickupRequestFormModel.setPaymentModeId(2);

            pickupRequestFormModel.setSid(0);
            pickupRequestFormModel.setCid(0);
            pickupRequestFormModel.setBid(0);
            pickupRequestFormModel.setUid(0);
            pickupRequestFormModel.setIsActive(1);
            pickupRequestFormModel.setIsDelete(0);

            pickupRequestFormModel.setLastModifyBy(0);
            pickupRequestFormModel.setIsDefault(0);
            pickupRequestFormModel.setIsEnable(1);

            pickupRequestFormModel.setIsSync(0);
            pickupRequestFormModel.setIsFrom(2);

            pickupRequestFormModel.setVolumetricWeight(volumetricWeight);

            pickupRequestFormModel.setDimensions(dimensionDetailModelArrayList);
            pickupRequestFormModel.setNetPayable(Double.valueOf(getTrimString(binding.tnlNetPayable)));


            String requestString = new Gson().toJson(pickupRequestFormModel);
            Log.e(TAG, requestString);
            ApiService apiService = ApiClient.createService(ApiService.class, "", "");

            Call<CommonResponse<String>> call = apiService.managePickupRequestForm(pickupRequestFormModel);

            ApiManager.callRetrofit(call, API_PICKUP_REQUEST_MANAGE, this, false);

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    private void AddDimensionDetails() {

        pickupRequestDetailModel = new PickupRequestDetailModel();
        pickupRequestDetailModel.setBoxes(AppUtils.castToInteger(getTrimString(dialogDimensionDetailBinding.tnlBoxes)));
        pickupRequestDetailModel.setLength(AppUtils.castToDouble(getTrimString(dialogDimensionDetailBinding.tnlLength)));
        pickupRequestDetailModel.setWidth(AppUtils.castToDouble(getTrimString(dialogDimensionDetailBinding.tnlWidth)));
        pickupRequestDetailModel.setHeight(AppUtils.castToDouble(getTrimString(dialogDimensionDetailBinding.tnlHeight)));
        pickupRequestDetailModel.setActualWeight(AppUtils.castToDouble(getTrimString(dialogDimensionDetailBinding.tnlActualWeight)));
        pickupRequestDetailModel.setActualWeightPerBox(actualWeightPerBox);
        pickupRequestDetailModel.setVolumetricWeight(AppUtils.castToDouble(getTrimString(dialogDimensionDetailBinding.tnlVolumetricWeight)));

        pickupRequestDetailModel.setSid(0);
        pickupRequestDetailModel.setCid(0);
        pickupRequestDetailModel.setBid(0);
        pickupRequestDetailModel.setUid(0);
        pickupRequestDetailModel.setIsActive(1);
        pickupRequestDetailModel.setIsDelete(0);
        pickupRequestDetailModel.setIsEnable(0);
        pickupRequestDetailModel.setIsSync(0);
        pickupRequestDetailModel.setIsFrom(2);

        if (dimensionItemPosition >= 0)
            dimensionDetailModelArrayList.set(dimensionItemPosition, pickupRequestDetailModel);
        else
            dimensionDetailModelArrayList.add(pickupRequestDetailModel);

        baseAdapter.notifyDataSetChanged();

        mDialog.dismiss();

        int newTotalBoxes = 0;
        totalActualWeight = 0;
        totalVolumetricWeight = 0;

        for (int i = 0; i < dimensionDetailModelArrayList.size(); i++) {
            newTotalBoxes += dimensionDetailModelArrayList.get(i).getBoxes();
            totalActualWeight += dimensionDetailModelArrayList.get(i).getActualWeight();
            totalVolumetricWeight += dimensionDetailModelArrayList.get(i).getVolumetricWeight();
        }
        binding.edtNoofBoxes.setText(String.valueOf(newTotalBoxes));

        getPickupRequestOtherCharges();
    }

    private void openAddDimensionDetailsDialog(PickupRequestDetailModel dimensionDetailModel) {

        mDialog = new Dialog(mContext);
        dialogDimensionDetailBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.dialog_dimension_detail, null, false);
        mDialog.setContentView(dialogDimensionDetailBinding.getRoot());
        mDialog.setCancelable(false);
        Window window = mDialog.getWindow();
        mDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        Objects.requireNonNull(window).setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        mDialog.show();

        dialogDimensionDetailBinding.btnCancel.setOnClickListener(v -> mDialog.dismiss());

        dialogDimensionDetailBinding.btnAddDialog.setOnClickListener(this);

        dialogDimensionDetailBinding.edtBoxes.addTextChangedListener(this);
        dialogDimensionDetailBinding.edtLength.addTextChangedListener(this);
        dialogDimensionDetailBinding.edtWidth.addTextChangedListener(this);
        dialogDimensionDetailBinding.edtHeight.addTextChangedListener(this);
        dialogDimensionDetailBinding.edtActualWeight.addTextChangedListener(this);

        if (dimensionItemPosition >= 0 && dimensionDetailModel != null) {

            dialogDimensionDetailBinding.edtBoxes.setText(String.valueOf(dimensionDetailModel.getBoxes()));
            dialogDimensionDetailBinding.edtLength.setText(String.valueOf(dimensionDetailModel.getLength()));
            dialogDimensionDetailBinding.edtWidth.setText(String.valueOf(dimensionDetailModel.getWidth()));
            dialogDimensionDetailBinding.edtHeight.setText(String.valueOf(dimensionDetailModel.getHeight()));
            dialogDimensionDetailBinding.edtActualWeight.setText(String.valueOf(dimensionDetailModel.getActualWeight()));
            dialogDimensionDetailBinding.edtActualWeightPerBox.setText(String.valueOf(dimensionDetailModel.getActualWeightPerBox()));
            dialogDimensionDetailBinding.edtVolumetricWeight.setText(String.valueOf(dimensionDetailModel.getVolumetricWeight()));


        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {

        boxes = AppUtils.castToDouble(getTrimString(dialogDimensionDetailBinding.tnlBoxes));

        length = AppUtils.castToDouble(getTrimString(dialogDimensionDetailBinding.tnlLength));

        width = AppUtils.castToDouble(getTrimString(dialogDimensionDetailBinding.tnlWidth));

        height = AppUtils.castToDouble(getTrimString(dialogDimensionDetailBinding.tnlHeight));

        actualWeight = AppUtils.castToDouble(getTrimString(dialogDimensionDetailBinding.tnlActualWeight));

        actualWeightPerBox = actualWeight / boxes;
        //Formula= VolumetricWeight = (Length  Width  Height / 1728) * Boxes;
        volumetricWeight = (length * width * height / AppConstants.DIMENSION_DETAIL_VALUE)/* * Objects.requireNonNull(prefUserModel.getCFT()) */ * boxes;

        @SuppressLint("DefaultLocale") String actualWeightPerBoxRoundOff = String.format("%.2f", actualWeightPerBox);

        dialogDimensionDetailBinding.edtActualWeightPerBox.setText(actualWeightPerBoxRoundOff);

        double roundOff = Math.round(volumetricWeight);

        @SuppressLint("DefaultLocale") String volumetricWeightroundOff = String.format("%.2f", volumetricWeight);
        Log.d(TAG, "afterTextChanged: " + volumetricWeightroundOff);

        dialogDimensionDetailBinding.edtVolumetricWeight.setText(volumetricWeightroundOff);


    }

    private void verifyOTP(String id, String otp) {
        try {
            CommanRequestModel requestModel = new CommanRequestModel();
            requestModel.setCustomerid(prefUserModel.getId());
            requestModel.setOtp(otp);
            ApiService apiService = ApiClient.createService(ApiService.class);

            String s = new Gson().toJson(requestModel);
            Call<CommonResponse<Boolean>> call = apiService.verifyOTP(requestModel);

            ApiManager.callRetrofit(call, API_PICKUP_VERIFYOTP, this, false);

        } catch (Exception e) {
            //stopProgressDialog();
            e.printStackTrace();
            errorLog(e);
        }
    }

    @Override
    public void onViewClick(int itemPosition, Object object) {
    }

    @Override
    public void onInfoClick(int itemPosition, Object object, View... views) {
    }

    @Override
    public void onEditClick(int itemPosition, Object object) {
        this.dimensionItemPosition = itemPosition;
        PickupRequestDetailModel dimensionDetailModel = (PickupRequestDetailModel) object;
        openAddDimensionDetailsDialog(dimensionDetailModel);
    }

    @Override
    public void onDeleteClick(int itemPosition, Object object) {

        PickupRequestDetailModel dimensionDetailModel = (PickupRequestDetailModel) object;
        dimensionDetailModelArrayList.remove(itemPosition);
        baseAdapter.notifyItemRemoved(itemPosition);

        int newTotalBoxes = 0;
        totalActualWeight = 0;
        totalVolumetricWeight = 0;

        for (int i = 0; i < dimensionDetailModelArrayList.size(); i++) {
            newTotalBoxes += dimensionDetailModelArrayList.get(i).getBoxes();
            totalActualWeight += dimensionDetailModelArrayList.get(i).getActualWeight();
            totalVolumetricWeight += dimensionDetailModelArrayList.get(i).getVolumetricWeight();
        }
        binding.edtNoofBoxes.setText(String.valueOf(newTotalBoxes));

        getPickupRequestOtherCharges();
    }

    @Override
    public void onMasterBottomSheetViewClick(int itemPosition, Object object, String edtID) {

        CommonListResponse commonListResponse = (CommonListResponse) object;

        switch (edtID) {

            case AppConstants.SP_STATE:
                binding.edtBookingCountry.setText(commonListResponse.getName());
                stateId = AppUtils.castToInteger(commonListResponse.getId());
                inputParams.put(AppConstants.KEY_STATE_ID, stateId);
                getSpinnerList(AppConstants.SP_CITY, inputParams);
                cityId = 0;
                postcodeId = 0;
                postcodeTypeId = 0;
                binding.edtBookingCity.setText("");
                binding.edtBookingPostCode.setText("");
                binding.edtPostcodeType.setText("");
                if (cityMasterList != null)
                    cityMasterList.clear();
                if (postcodeMasterList != null)
                    postcodeMasterList.clear();

                getConsignorInformation(stateId, prefUserModel.getId());
                getPickupRequestOtherCharges();
                break;

            case AppConstants.SP_STATE2:
                binding.edtDestinationCounty.setText(commonListResponse.getName());
                destinationStateId = AppUtils.castToInteger(commonListResponse.getId());
                inputParams.put(AppConstants.KEY_STATE_ID, destinationStateId);
                getSpinnerList(AppConstants.SP_CITY2, inputParams);
                destinationCityId = 0;
                binding.edtDestinationCity.setText("");
                if (cityDestinationMasterList != null)
                    cityDestinationMasterList.clear();
                getPickupRequestOtherCharges();
                break;

            case AppConstants.SP_CITY:
                binding.edtBookingCity.setText(commonListResponse.getName());
                cityId = AppUtils.castToInteger(commonListResponse.getId());
                inputParams.put(AppConstants.KEY_CITY_ID, cityId);
                getSpinnerList(AppConstants.SP_POSTCODE, inputParams);
                binding.edtBookingPostCode.setText("");
                binding.edtPostcodeType.setText("");
                postcodeId = 0;
                postcodeTypeId = 0;
                if (postcodeMasterList != null)
                    postcodeMasterList.clear();
                break;

            case AppConstants.SP_CITY2:
                binding.edtDestinationCity.setText(commonListResponse.getName());
                destinationCityId = AppUtils.castToInteger(commonListResponse.getId());
                inputParams.put(AppConstants.KEY_CITY_ID, destinationCityId);
                getSpinnerList(AppConstants.SP_POSTCODE2, inputParams);
                destinationPostcodeId = 0;
                destinationPostcodeTypeId = 0;
                binding.edtDestinationPostCode.setText("");
                if (destinationPostcodeMasterList != null)
                    destinationPostcodeMasterList.clear();
                break;

            case AppConstants.SP_POSTCODE:
                binding.edtBookingPostCode.setText(commonListResponse.getName());
                postcodeId = AppUtils.castToInteger(commonListResponse.getId());
                postcodeTypeId = AppUtils.castToInteger(commonListResponse.getPostcodeTypeId());
                break;

            case AppConstants.SP_POSTCODE2:
                binding.edtDestinationPostCode.setText(commonListResponse.getName());
                destinationPostcodeId = AppUtils.castToInteger(commonListResponse.getId());
                destinationPostcodeTypeId = AppUtils.castToInteger(commonListResponse.getPostcodeTypeId());
                binding.edtPostcodeType.setText(commonListResponse.getPostcodeTypeName());
                break;

            case AppConstants.SP_PAYMENT_TYPE3:
                paymentId = AppUtils.castToInteger(commonListResponse.getId());
                binding.edtPaymentType.setText(commonListResponse.getName());
                break;

            case AppConstants.SP_VERTICAL:
                verticalId = AppUtils.castToInteger(commonListResponse.getId());
                binding.edtVerticle.setText(commonListResponse.getName());
                break;

            case AppConstants.SP_PACKING_TYPE:
                parkingId = AppUtils.castToInteger(commonListResponse.getId());
                binding.edtPackingType.setText(commonListResponse.getName());
                break;
        }
    }

    @Override
    public void onMasterBottomSheetInfoClick(int itemPosition, Object object, View... views) {
    }

    private void getConsignorInformation(int stateId, int userId) {

        try {

            if (NetworkUtils.isConnected(mContext)) {

                startProgressDialog(this, false);

                CommanRequestModel commanRequestModel = new CommanRequestModel();
                commanRequestModel.setConsignorid(userId);
                commanRequestModel.setBookingstateid(stateId);

                ApiService apiService = ApiClient.createService(ApiService.class, "", "");
                Call<CommonResponse<ConsignorInformationModel>> call = apiService.getConsignorInformation(commanRequestModel);
                ApiManager.callRetrofit(call, ApiConstant.API_CONSIGNOR_INFORMATION, this, false);

                String request = new Gson().toJson(commanRequestModel);
                Log.d("Reqest", "ReqestString: " + request);

            } else
                displayInternetToastMessage(mContext);
        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }

    }

    private void getPickupRequestOtherCharges() {

        try {

            if (stateId > 0 && destinationStateId > 0 && totalActualWeight > 0 && totalVolumetricWeight > 0) {

                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("bookingstateid", stateId);
                jsonObject.addProperty("destinationstateid", destinationStateId);
                jsonObject.addProperty("consignorid", prefUserModel.getId());
                jsonObject.addProperty("totalactualweight", totalActualWeight);
                jsonObject.addProperty("totalvolumetricweight", totalVolumetricWeight);
                jsonObject.addProperty("bookingcityid", cityId);
                jsonObject.addProperty("destinationcityid", destinationCityId);

                String requestString = new Gson().toJson(jsonObject);
                Log.e(TAG, requestString);

                ApiService apiService = ApiClient.createService(ApiService.class, "", "");

                Call<CommonResponse<OtherCharges>> call = apiService.getPickUpRequestOtherCharges(jsonObject);

                ApiManager.callRetrofit(call, API_PICKUP_REQUEST_OTHER_CHARGES, this, false);
            }

        } catch (Exception e) {

            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());

        }
    }

    @Override
    public void onApiSuccess(String endPointName, Object responseBody) {

        super.onApiSuccess(endPointName, responseBody);

        switch (endPointName) {


            case ApiConstant.API_CONSIGNOR_INFORMATION:
                CommonResponse<ConsignorInformationModel> pickUpResponse = (CommonResponse<ConsignorInformationModel>) responseBody;
                processResponse(pickUpResponse);
                break;

            case API_PICKUP_REQUEST_MANAGE:
                CommonResponse<String> manageResponse = (CommonResponse<String>) responseBody;
                processPickupRequestFormResponse(manageResponse);
                break;

            case API_PICKUP_REQUEST_EDIT:
                CommonResponse<EditPickUpRequestModel> editPickUpRequestModelCommonResponse = (CommonResponse<EditPickUpRequestModel>) responseBody;
                processPickupRequestEditFormResponse(editPickUpRequestModelCommonResponse);
                break;

            case API_PICKUP_REQUEST_OTHER_CHARGES:
                CommonResponse<OtherCharges> otherChargesModelResponse = (CommonResponse<OtherCharges>) responseBody;
                processOtherChargesModelResponse(otherChargesModelResponse);
                break;

            case API_PICKUP_COUPON:
                CommonResponse<CouponCode> response = (CommonResponse<CouponCode>) responseBody;
                processCouponResponse(response);
                break;

            case API_PICKUP_OTP:
                CommonResponse OTPresponse = (CommonResponse) responseBody;
                processOTPresponse(OTPresponse);
                break;

            case API_PICKUP_VERIFYOTP:
                CommonResponse<Boolean> verifyOtpResponse = (CommonResponse<Boolean>) responseBody;
                processVerifyOtpResponse(verifyOtpResponse);
                break;

        }
    }

    private void processVerifyOtpResponse(CommonResponse<Boolean> verifyOtpResponse) {
        if (verifyOtpResponse.getStatus().equals("1")) {
            Boolean data = verifyOtpResponse.getData();
            if (data) {
                isOTPverified = true;
                //  Snackbar.make(binding.getRoot().getRootView(), "Success", Snackbar.LENGTH_LONG).show();
                setPickUpForm();
            } else {
                isOTPverified = false;
                Snackbar.make(binding.getRoot().getRootView(), "Invalid OTP", Snackbar.LENGTH_LONG).show();
            }
        } else {
            isOTPverified = false;
            Snackbar.make(binding.getRoot().getRootView(), "Invalid OTP", Snackbar.LENGTH_LONG).show();
        }
    }

    private void processOTPresponse(CommonResponse OTPresponse) {
        isOTPverified = false;
    }

    private void processCouponResponse(CommonResponse<CouponCode> response) {
        if (response.getData() == null) {
            if (isFromCoupon) {
                binding.btnCouponCode.setEnabled(true);
                Snackbar.make(binding.getRoot().getRootView(), "Invalid Coupon Code.", Snackbar.LENGTH_LONG).show();
            }
        } else {
            binding.tnlNetPayable.getEditText().setText(response.getData().getNetPayable());
            binding.tvDiscountValue.setText(response.getData().getDiscount());
            Snackbar.make(binding.getRoot().getRootView(), "Coupon Code applied", Snackbar.LENGTH_LONG).show();
            binding.btnCouponCode.setEnabled(true);
        }

    }

    private void processOtherChargesModelResponse(CommonResponse<OtherCharges> response) {

        try {

            if (response != null) {

                if (response.getStatus().equalsIgnoreCase("1")) {

                    double basicChargeAmount = response.getData().getBasicChargeAmount();
                    double chargeableWeight = response.getData().getChargableWeight();
                    double chargeableWeightPer = response.getData().getChargableWeightPer();

                    if (pickupRequestDetailModel != null) {
                        pickupRequestDetailModel.setBasicChargeAmount(basicChargeAmount);
                        binding.edtNetPayable.setText(basicChargeAmount + "");
                        pickupRequestDetailModel.setChargableWeight(chargeableWeight);
                        pickupRequestDetailModel.setChargableWeightPer(chargeableWeightPer);

                        this.chargeableWeight = chargeableWeight;
                        this.chargeableWeightPer = chargeableWeightPer;
                        this.basicChargeAmount = basicChargeAmount;

                        isFromCoupon = false;
                        getNetPaybleAmount("", basicChargeAmount, isFromCoupon);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            errorLog(e);
        }
    }

    private void getNetPaybleAmount(String couponCode, double basicChargeAmount, boolean flag) {
        try {
            CommanRequestModel requestModel = new CommanRequestModel();
            requestModel.setCouponCode(couponCode);
            requestModel.setBasicCharge(basicChargeAmount);
            ApiService apiService = ApiClient.createService(ApiService.class);

            Call<CommonResponse<CouponCode>> call = apiService.getPickUpCouponCode(requestModel);

            ApiManager.callRetrofit(call, API_PICKUP_COUPON, this, false);

        } catch (Exception e) {
            binding.btnCouponCode.setEnabled(true);
            e.printStackTrace();
            errorLog(e);
        }
    }

    @Override
    public void onApiError(String endPointName, String errorMessage) {

        super.onApiError(endPointName, errorMessage);

        switch (endPointName) {

            case ApiConstant.API_CONSIGNOR_INFORMATION:
            case API_PICKUP_REQUEST_MANAGE:
            case API_PICKUP_REQUEST_EDIT:
            case API_PICKUP_OTP:
            case API_PICKUP_VERIFYOTP:
                isOTPverified = false;
            case API_PICKUP_COUPON:
                displayLongToast(mContext, errorMessage);
                break;
        }
    }

    @Override
    public void onApiFailure(String endPointName, String failureMessage) {

        super.onApiError(endPointName, failureMessage);

        switch (endPointName) {

            case ApiConstant.API_CONSIGNOR_INFORMATION:
            case API_PICKUP_REQUEST_MANAGE:
            case API_PICKUP_REQUEST_EDIT:
            case API_PICKUP_OTP:
            case API_PICKUP_VERIFYOTP:
                isOTPverified = false;
            case API_PICKUP_COUPON:
                displayLongToast(mContext, failureMessage);
                break;
        }
    }

    private void processPickupRequestFormResponse(CommonResponse<String> manageResponse) {

        try {

            if (NetworkUtils.isConnected(mContext)) {

                startProgressDialog(this, false);

                if (manageResponse != null) {

                    if (manageResponse.getStatus().equalsIgnoreCase("1")) {

                        Snackbar.make(binding.getRoot().getRootView(), "" + manageResponse.getMessage(), Snackbar.LENGTH_LONG).show();

                        printInfoLog(TAG, "Data saved Successfully.");
                        //displayLongToast(mContext, "Location Added Successfully");
                        stopProgressDialog();
                        finish();
                    }
                }

            } else
                displayInternetToastMessage(mContext);

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
            stopProgressDialog();
        }
    }

    private void processResponse(CommonResponse<ConsignorInformationModel> consignorInformationModel) {

        try {

            if (consignorInformationModel.getStatus().equalsIgnoreCase("0")) {
                Objects.requireNonNull(binding.tnlAddress.getEditText()).setText("");
                Objects.requireNonNull(binding.tnlPostcode.getEditText()).setText("");
                Objects.requireNonNull(binding.tnlVATNo.getEditText()).setText("");
            } else {
                Objects.requireNonNull(binding.tnlAddress.getEditText()).setText(consignorInformationModel.getData().getAddress());
                Objects.requireNonNull(binding.tnlPostcode.getEditText()).setText(consignorInformationModel.getData().getPostcode());
                Objects.requireNonNull(binding.tnlVATNo.getEditText()).setText(consignorInformationModel.getData().getGstNo());
            }
        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    @SuppressLint("SetTextI18n")
    private void processPickupRequestEditFormResponse(CommonResponse<EditPickUpRequestModel> response) {

        try {

            if (NetworkUtils.isConnected(mContext)) {

                startProgressDialog(this, false);

                if (isView) {
                    disableMultipleViews(binding.nestedScrollView);
                    disableView(binding.toolbar.btnSave);
                    disableView(binding.btnAdd);
                } else {
                    enableView(binding.toolbar.btnSave);
                    enableView(binding.btnAdd);
                }

                if (response != null) {

                    if (response.getStatus().equalsIgnoreCase("1")) {

                        EditPickupRequestFormModel mainDetails = response.getData().getEditPickupRequestFormModel();
                        ArrayList<EditDimensionDetailModel> subDetails = response.getData().getEditDimensionDetailModel();

                        binding.edtNo.setText(mainDetails.getAutoNo());
                        //    binding.edtDate.setText(AppUtils.convertDateFormat(AppConstants.DD_MM_YYYY, AppConstants.CALENDAR_DATE_FORMAT, mainDetails.getdATE()));
                        binding.edtDate.setText(AppUtils.convertDateFormat(AppConstants.CALENDAR_DATE_FORMAT, AppConstants.DD_MM_YYYY, mainDetails.getdATE()));
                        //binding.edtDate.setText(mainDetails.getdATE());
                        binding.edtPaymentType.setText("To be Billed");

                        binding.edtBookingCountry.setText(mainDetails.getBookingStateName());
                        binding.edtBookingCity.setText(mainDetails.getBookingCityName());
                        binding.edtBookingPostCode.setText(mainDetails.getBookingPostCode());
                        binding.edtPostcodeType.setText(mainDetails.getDestinationPostCodeType());

                        paymentId = AppUtils.castToInteger(mainDetails.getPaymentTypeId());
                        stateId = AppUtils.castToInteger(mainDetails.getBookingStateId());
                        cityId = AppUtils.castToInteger(mainDetails.getBookingCityId());
                        postcodeId = AppUtils.castToInteger(mainDetails.getBookingPostCodeId());

                        binding.edtDestinationCounty.setText(mainDetails.getDestinationStateName());
                        binding.edtDestinationCity.setText(mainDetails.getDestinationCityName());
                        binding.edtDestinationPostCode.setText(mainDetails.getDestinationPostCode());

                        destinationStateId = AppUtils.castToInteger(mainDetails.getDestinationStateId());
                        destinationCityId = AppUtils.castToInteger(mainDetails.getDestinationCityId());
                        destinationPostcodeId = AppUtils.castToInteger(mainDetails.getDestinationPostCodeId());

                        binding.edtConsignor.setText(mainDetails.getConsignorName());
                        Objects.requireNonNull(binding.tnlAddress.getEditText()).setText(mainDetails.getConsignorAddress());
                        Objects.requireNonNull(binding.tnlPostcode.getEditText()).setText(mainDetails.getConsignorPostCode());
                        Objects.requireNonNull(binding.tnlMobileNo.getEditText()).setText(mainDetails.getConsignorMobileNo());
                        Objects.requireNonNull(binding.tnlVATNo.getEditText()).setText(mainDetails.getConsignorGSTNo());

                        Objects.requireNonNull(binding.tnlConsigneeOfConsignee.getEditText()).setText(mainDetails.getConsigneeName());
                        Objects.requireNonNull(binding.tnlAddressOfConsignee.getEditText()).setText(mainDetails.getConsigneeAddress());
                        Objects.requireNonNull(binding.tnlPostcodeOfConsignee.getEditText()).setText(mainDetails.getConsigneePostCode());
                        Objects.requireNonNull(binding.tnlMobileNoOfConsignee.getEditText()).setText(mainDetails.getConsigneeMobileNo());
                        Objects.requireNonNull(binding.tnlVATNoOfConsignee.getEditText()).setText(mainDetails.getConsigneeGSTNo());

                        binding.edtVerticle.setText(mainDetails.getVerticleTypeName());
                        binding.edtProductName.setText(mainDetails.getProductName());
                        binding.edtPackingType.setText(mainDetails.getPackingTypeName());

                        verticalId = AppUtils.castToInteger(mainDetails.getVerticleTypeId());
                        parkingId = AppUtils.castToInteger(mainDetails.getPackingTypeId());

                        binding.edtNoofBoxes.setText(mainDetails.getNoOfPackages());
                        binding.edtNetPayable.setText(mainDetails.getNetPayable());
                        binding.tvDiscountValue.setText("0.00");
                        if (mainDetails.getPickUpRequestType().equalsIgnoreCase("1"))
                            binding.radiobtnNormal.setChecked(true);
                        else
                            binding.radiobtnHyperlocal.setChecked(true);


                        if (mainDetails.getPaymentModeId().equalsIgnoreCase("1"))
                            binding.btnPrepaid.setChecked(true);
                        else if (mainDetails.getPaymentModeId().equalsIgnoreCase("2"))
                            binding.btnCOD.setChecked(true);

                        if (dimensionDetailModelArrayList.size() > 0)
                            dimensionDetailModelArrayList.clear();

                        if (subDetails != null && subDetails.size() > 0) {

                            for (int j = 0; j < subDetails.size(); j++) {

                                PickupRequestDetailModel details = new PickupRequestDetailModel();
                                details.setId(AppUtils.castToInteger(subDetails.get(j).getId()));

                                details.setActualWeight(AppUtils.castToDouble(subDetails.get(j).getActualWeight()));
                                details.setHeight(AppUtils.castToDouble(subDetails.get(j).getHeight()));
                                details.setBoxes(AppUtils.castToInteger(subDetails.get(j).getBoxes()));
                                details.setWidth(AppUtils.castToDouble(subDetails.get(j).getWidth()));
                                details.setVolumetricWeight(AppUtils.castToDouble(subDetails.get(j).getVolumetricWeight()));
                                details.setLength(AppUtils.castToDouble(subDetails.get(j).getLength()));

                                try {
                                    boxes = AppUtils.castToDouble(subDetails.get(j).getBoxes());
                                    actualWeight = AppUtils.castToDouble(subDetails.get(j).getActualWeight());
                                    actualWeightPerBox = actualWeight / boxes;
                                    details.setActualWeightPerBox(actualWeightPerBox);
                                } catch (Exception e) {
                                    printErrorLog(TAG, e.getLocalizedMessage());
                                }
                                details.setBid(0);
                                details.setCid(0);
                                details.setUid(0);
                                details.setSid(0);
                                details.setIsDelete(0);
                                details.setIsActive(1);
                                details.setIsEnable(0);
                                details.setIsFrom(2);
                                details.setIsSync(0);

                                dimensionDetailModelArrayList.add(details);
                            }
                            baseAdapter.notifyDataSetChanged();
                        }

                        actualWeight = AppUtils.castToDouble(mainDetails.getActualWeight());
                        volumetricWeight = AppUtils.castToDouble(mainDetails.getVolumetricWeight());

                        pickupRequestDetailModelBottomSheet = new PickupRequestDetailModel();
                        pickupRequestDetailModelBottomSheet.setActualWeight(actualWeight);
                        pickupRequestDetailModelBottomSheet.setVolumetricWeight(volumetricWeight);
                        pickupRequestDetailModelBottomSheet.setBasicChargeAmount(AppUtils.castToDouble(mainDetails.getBasicChargeAmount()));

                    } else {
                        Toast.makeText(mContext, response.getMessage(), Toast.LENGTH_SHORT).show();
                        finish();
                    }
                } else {
                    Toast.makeText(mContext, "Something went wrong!", Toast.LENGTH_SHORT).show();
                    stopProgressDialog();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
            stopProgressDialog();
        }
    }

    private boolean validateAddDimensionDetail() {

        return appValidation.validateString(dialogDimensionDetailBinding.tnlBoxes) &&
                appValidation.validateString(dialogDimensionDetailBinding.tnlLength) &&
                appValidation.validateString(dialogDimensionDetailBinding.tnlWidth) &&
                appValidation.validateString(dialogDimensionDetailBinding.tnlHeight) &&
                appValidation.validateString(dialogDimensionDetailBinding.tnlActualWeight);
    }


    private boolean validatePickRequestForm() {

        return appValidation.validateString(binding.tnlPaymentType) &&
                appValidation.validateString(binding.tnlBookingCountry) &&
                appValidation.validateString(binding.tnlBookingCity) &&
                appValidation.validateString(binding.tnlBookingPostCode) &&
                appValidation.validateString(binding.tnlDestinationCounty) &&
                appValidation.validateString(binding.tnlDestinationCity) &&
                appValidation.validateString(binding.tnlDestinationPostCode) &&
                appValidation.validateString(binding.tnlConsigneeOfConsignee) &&
                appValidation.validateString(binding.tnlAddressOfConsignee) &&
                appValidation.validateString(binding.tnlPostcodeOfConsignee) &&
                appValidation.validateString(binding.tnlMobileNoOfConsignee) &&
                //appValidation.validateString(binding.tnlVATNoOfConsignee) &&
                appValidation.validateString(binding.tnlVerticle) &&
                appValidation.validateString(binding.tnlPackingType) &&
                appValidation.validateString(binding.tnlOTP) && validateOTP();

    }

    private boolean getOtpVerification() {
        return isOTPverified;
    }

    private boolean validateOTP() {
        if (appValidation.validateString(binding.tnlOTP)) {
            verifyOTP(String.valueOf(prefUserModel.getId()), binding.tnlOTP.getEditText().getText().toString().trim());
        }
        return true;

    }
}