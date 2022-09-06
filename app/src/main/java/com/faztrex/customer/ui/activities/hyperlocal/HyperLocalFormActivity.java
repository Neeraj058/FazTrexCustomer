package com.faztrex.customer.ui.activities.hyperlocal;

import static com.cittasolutions.cittalibrary.utils.AppUtils.errorLog;
import static com.faztrex.customer.retrofit.ApiConstant.API_HYPER_LOCAL_EDIT;
import static com.faztrex.customer.retrofit.ApiConstant.API_HYPER_LOCAL_Freight;
import static com.faztrex.customer.retrofit.ApiConstant.API_HYPER_LOCAL_MANAGE;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.faztrex.customer.R;
import com.faztrex.customer.baseclasses.BaseActivity;
import com.faztrex.customer.baseclasses.BaseAdapter;
import com.faztrex.customer.customviews.MyDatePicker;
import com.faztrex.customer.databinding.ActivityHyperLocalFormBinding;
import com.faztrex.customer.databinding.DialogProductDetailBinding;
import com.faztrex.customer.helper.ConfirmationDialogManager;
import com.faztrex.customer.listeners.bottomsheet.MasterBottomSheetActionListener;
import com.faztrex.customer.listeners.general.CommonActionListener;
import com.faztrex.customer.listeners.general.ConfirmationDialogClickListener;
import com.faztrex.customer.models.hyperlocal.HyperLocalDetailForm;
import com.faztrex.customer.models.hyperlocal.HyperLocalForm;
import com.faztrex.customer.models.hyperlocal.HyperLocalFreight;
import com.faztrex.customer.network.model.request.CommanRequestModel;
import com.faztrex.customer.network.response.CommonListResponse;
import com.faztrex.customer.network.response.CommonResponse;
import com.faztrex.customer.retrofit.ApiClient;
import com.faztrex.customer.retrofit.ApiConstant;
import com.faztrex.customer.retrofit.ApiListener;
import com.faztrex.customer.retrofit.ApiManager;
import com.faztrex.customer.retrofit.ApiService;
import com.faztrex.customer.utils.AppConstants;
import com.faztrex.customer.utils.AppUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;

public class HyperLocalFormActivity extends BaseActivity
        implements View.OnClickListener, ApiListener, MasterBottomSheetActionListener,
        CommonActionListener, ConfirmationDialogClickListener, TextWatcher {

    private static final String TAG = HyperLocalFormActivity.class.getSimpleName();

    private final Context mContext = this;
    private final long DELAY = 10000; // in ms
    private int hyperLocalId;
    private int paymentTypeId;
    private int weighId;
    private int addressTypeId;
    //private int distancePaymentId;
    private int itemPosition;
    private boolean isEdit, isView;
    private Dialog mDialog;
    private DialogProductDetailBinding dialogProductDetailBinding;
    private ActivityHyperLocalFormBinding binding;
    private ArrayList<HyperLocalDetailForm> hyperLocalDetailFormArrayList = new ArrayList<>();
    private BaseAdapter baseAdapter;
    private double LatPostcodeDelivery = 0.0;
    private double LongPostcodeDelivery = 0.0;
    private double LatPostcode = 0.0;
    private double LongPostcode = 0.0;
    private Timer timer = new Timer();

    @Override
    public Activity setCurrentActivity() {
        return this;
    }

    @SuppressLint({"NonConstantResourceId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView((Activity) mContext, R.layout.activity_hyper_local_form);
        binding.toolbar.tvHeaderTitle.setText(R.string.hyper_local_request);
        binding.toolbar.ivBack.setOnClickListener(v -> finish());

        binding.toolbar.btnSave.setVisibility(View.VISIBLE);

        binding.toolbar.btnSave.setOnClickListener(this);

        String pickId = getIntent().getStringExtra(AppConstants.HYPER_LOCAL_REQUEST_ID);
        hyperLocalId = AppUtils.castToInteger(pickId);
        isView = AppUtils.getStringValue(getIntent().getStringExtra(AppConstants.MODE)).equalsIgnoreCase("view");
        isEdit = AppUtils.getStringValue(getIntent().getStringExtra(AppConstants.MODE)).equalsIgnoreCase("edit");

        if (!isEdit && !isView)
            Objects.requireNonNull(binding.tnlBookingDate.getEditText()).setText(AppUtils.getCurrentDate(AppConstants.CALENDAR_DATE_FORMAT));
        else
            getHyperLocalDataById();

        if (isEdit)
            binding.toolbar.btnSave.setText("Update");

        if (isView) {
            binding.toolbar.btnSave.setVisibility(View.GONE);
            disableMultipleViews(binding.rootLayout);
        }

        Objects.requireNonNull(binding.tnlBookingDate.getEditText()).setOnClickListener(this);
        Objects.requireNonNull(binding.tnlPaymentType.getEditText()).setOnClickListener(this);
        Objects.requireNonNull(binding.tnlWeight.getEditText()).setOnClickListener(this);
        Objects.requireNonNull(binding.tnlAddressType.getEditText()).setOnClickListener(this);
        Objects.requireNonNull(binding.tnlPostcode.getEditText()).addTextChangedListener(this);
        Objects.requireNonNull(binding.tnlDeliveryPostcode.getEditText()).addTextChangedListener(this);

      /*  binding.rgPaymentType.setOnCheckedChangeListener((group, checkedId) -> {

            switch (checkedId) {

                case R.id.rbPrepaid:
                    distancePaymentId = 1;
                    break;

                case R.id.rbCOD:
                    distancePaymentId = 2;
                    break;
            }
        });*/

        binding.btnAdd.setOnClickListener(this);

        getSpinnerList(AppConstants.SP_PAYMENT_TYPE5, null);
        getSpinnerList(ApiConstant.API_WEIGHT, null);
        getSpinnerList(ApiConstant.API_ADDRESS_TYPE, null);

        if (!isEdit && !isView)
            loadProductDetails();

        try {
            String AutoNo = getIntent().getStringExtra("AutoNo");
            String s = AutoNo.replace("HLR", "");
            //String s1= s.replace("L","");
            //String s2= s1.replace("R","");
            int no = (AppUtils.castToInteger(s) + 1);
            String No = Integer.toString(no);
            if (No.length() <= 5) {
                switch (No.length()) {
                    case 1:
                        binding.edtHlrNo.setText("HLR0000" + No);
                        break;
                    case 2:
                        binding.edtHlrNo.setText("HLR000" + No);
                        break;
                    case 3:
                        binding.edtHlrNo.setText("HLR00" + No);
                        break;
                    case 4:
                        binding.edtHlrNo.setText("HLR0" + No);
                        break;
                    default:
                        binding.edtHlrNo.setText("HLR" + No);
                        break;
                }

            } else
                binding.edtHlrNo.setText("HLR" + No);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getHyperLocalDataById() {
        try {

            CommanRequestModel requestModel = new CommanRequestModel();
            requestModel.setId(String.valueOf(hyperLocalId));
            startProgressDialog((Activity) mContext, false);
            ApiService apiService = ApiClient.createService(ApiService.class);
            Call<CommonResponse<HyperLocalForm>> call = apiService.getHyperLocalDetailBtyId(requestModel);
            ApiManager.callRetrofit(call, API_HYPER_LOCAL_EDIT, this, false);

        } catch (Exception e) {
            stopProgressDialog();
            e.printStackTrace();
            errorLog(e);
        }
    }

    private void loadProductDetails() {

        binding.rvProducts.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.VERTICAL, false));
        baseAdapter = new BaseAdapter(mContext, hyperLocalDetailFormArrayList, R.layout.item_product_details, isView, this);
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(binding.rvProducts);
        binding.rvProducts.setAdapter(baseAdapter);

        int totalQty = 0;
        double totalAmount = 0;
        for (int i = 0; i < hyperLocalDetailFormArrayList.size(); i++) {
            totalQty += hyperLocalDetailFormArrayList.get(i).getProductQuantity();
            totalAmount += hyperLocalDetailFormArrayList.get(i).getProductAmount();
        }
        binding.tvTotalQty.setText(String.valueOf(totalQty));
        binding.tvTotalAmount.setText(String.valueOf(totalAmount));

        if (isView) {
            binding.tvAction.setVisibility(View.GONE);
            binding.btnAdd.setVisibility(View.GONE);
        }
    }

    private void setDataToModel() {

        getPreferenceData();

        HyperLocalForm localForm = new HyperLocalForm();

        if (hyperLocalId > 0)
            localForm.setId(hyperLocalId);

        localForm.setBookingDateTime(AppUtils.convertDateFormat(AppConstants.CALENDAR_DATE_FORMAT,
                AppConstants.API_DATE_FORMAT, getTrimString(binding.tnlBookingDate)));
        localForm.setPaymentTypeId(paymentTypeId);

        localForm.setAddressTypeId(addressTypeId);
        localForm.setPickupPostcode(getTrimString(binding.tnlPostcode));
        localForm.setPickupAddress1(getTrimString(binding.tnlAddress1));
        localForm.setPickupAddress2(getTrimString(binding.tnlAddress2));
        localForm.setPickupPersonName(getTrimString(binding.tnlName));
        localForm.setPickupContactNo(getTrimString(binding.tnlContactNo));

        localForm.setDeliveryPostcode(getTrimString(binding.tnlDeliveryPostcode));
        localForm.setDeliveryAddress1(getTrimString(binding.tnlDeliveryAddress1));
        localForm.setDeliveryAddress2(getTrimString(binding.tnlDeliveryAddress2));
        localForm.setDeliveryPersonName(getTrimString(binding.tnlDeliveryName));
        localForm.setDeliveryContactNo(getTrimString(binding.tnlDeliveryContactNo));

        localForm.setIsDocument(binding.cbDocuments.isChecked() ? 1 : 0);
        localForm.setIsElectronic(binding.cbElectronics.isChecked() ? 1 : 0);
        localForm.setIsMedicine(binding.cbMedicine.isChecked() ? 1 : 0);
        localForm.setIsEssential(binding.cbEssentials.isChecked() ? 1 : 0);
        localForm.setIsOthers(binding.cbOthers.isChecked() ? 1 : 0);
        localForm.setIsFrom(2);
        localForm.setUid(prefUserModel.getId());
        localForm.setLastModifyBy(prefUserModel.getId());
        localForm.setDistancePaymentTypeId(0);

        localForm.setTotalQuantity(AppUtils.castToInteger(binding.tvTotalQty.getText().toString()));
        localForm.setTotalAmount(AppUtils.castToDouble(binding.tvTotalAmount.getText().toString()));
        localForm.setFreight(binding.tvFreightValue.getText().toString().trim());
        localForm.setKm(binding.tvTotalKMValue.getText().toString().trim());

        localForm.setWeightTypeId(weighId);
        localForm.setSpecialInstruction(getTrimString(binding.tnlSpecialInstruction));
        try {
            localForm.setNo("HLR" + binding.tnlHlrNo.getEditText().getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        localForm.setDetails(hyperLocalDetailFormArrayList);

        String request = new Gson().toJson(localForm);

        ApiService apiService = ApiClient.createService(ApiService.class, "", "");

        Call<CommonResponse<String>> call = apiService.manageHyperLocal(localForm);

        ApiManager.callRetrofit(call, API_HYPER_LOCAL_MANAGE, this, false);

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.edtBookingDate:
                new MyDatePicker(binding.tnlBookingDate, AppConstants.CALENDAR_DATE_FORMAT, true, 5);
                break;

            case R.id.edtPaymentType:
                showFilterMasterBottomSheet(Objects.requireNonNull(binding.edtPaymentType.getHint()).toString(), this, AppConstants.SP_PAYMENT_TYPE5);
                break;

            case R.id.edtAddressType:
                showFilterMasterBottomSheet(Objects.requireNonNull(binding.edtAddressType.getHint()).toString(), this, AppConstants.SP_ADDRESS_TYPE);
                break;

            case R.id.edtWeight:
                showFilterMasterBottomSheet(Objects.requireNonNull(binding.edtWeight.getHint()).toString(), this, AppConstants.SP_WEIGHT);
                break;

            case R.id.btnSave:
                if (validateHyperLocalRequest()) {
                    if (binding.toolbar.btnSave.isEnabled())
                        setDataToModel();
                    binding.toolbar.btnSave.setEnabled(false);
                    new Handler().postDelayed(() -> binding.toolbar.btnSave.setEnabled(true), 5000);
                }
                break;

            case R.id.btnAdd:
                openAddProductDialog();
                break;

            case R.id.btnAddDialog:
                if (validateProductDetails()) {
                    addProductDetails();
                }
                break;
        }
    }

    private void addProductDetails() {

        HyperLocalDetailForm detailForm = new HyperLocalDetailForm();
        detailForm.setProductName(getTrimString(dialogProductDetailBinding.tnlProductName));
        detailForm.setProductQuantity(AppUtils.castToInteger(Objects.requireNonNull(dialogProductDetailBinding.edtQty.getText()).toString()));
        detailForm.setProductAmount(AppUtils.castToDouble(getTrimString(dialogProductDetailBinding.tnlAmount)));
        detailForm.setIsFrom(2);
        detailForm.setUid(prefUserModel.getId());
        detailForm.setLastModifyBy(prefUserModel.getId());
        hyperLocalDetailFormArrayList.add(detailForm);

        mDialog.dismiss();

        baseAdapter.notifyDataSetChanged();

        int totalQty = 0;
        double totalAmount = 0;

        for (int i = 0; i < hyperLocalDetailFormArrayList.size(); i++) {
            totalQty += hyperLocalDetailFormArrayList.get(i).getProductQuantity();
            totalAmount += hyperLocalDetailFormArrayList.get(i).getProductAmount();
        }

        binding.tvTotalQty.setText(String.valueOf(totalQty));
        binding.tvTotalAmount.setText(String.valueOf(totalAmount));
    }

    private void openAddProductDialog() {

        mDialog = new Dialog(mContext);
        dialogProductDetailBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.dialog_product_detail, null, false);
        mDialog.setContentView(dialogProductDetailBinding.getRoot());
        mDialog.setCancelable(false);
        Window window = mDialog.getWindow();
        mDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        Objects.requireNonNull(window).setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        mDialog.show();

        dialogProductDetailBinding.btnCancel.setOnClickListener(v -> mDialog.dismiss());

        dialogProductDetailBinding.btnAddDialog.setOnClickListener(this);
    }

    @Override
    public void onApiSuccess(String endPointName, Object responseBody) {
        super.onApiSuccess(endPointName, responseBody);

        switch (endPointName) {

            case API_HYPER_LOCAL_MANAGE:
                CommonResponse<String> manageApiResponse = (CommonResponse<String>) responseBody;
                processManageApiResponse(manageApiResponse);
                break;

            case API_HYPER_LOCAL_EDIT:
                CommonResponse<HyperLocalForm> editApiResponse = (CommonResponse<HyperLocalForm>) responseBody;
                processEditApiResponse(editApiResponse);
                break;

            case API_HYPER_LOCAL_Freight:
                CommonResponse<HyperLocalFreight> FreightResponse = (CommonResponse<HyperLocalFreight>) responseBody;
                processFreightResponse(FreightResponse);
                break;

        }
    }

    private void processFreightResponse(@NonNull CommonResponse<HyperLocalFreight> freightResponse) {

        HyperLocalFreight HyperLocalFreight = freightResponse.getData();

        if (HyperLocalFreight != null) {
            binding.tvFreightValue.setText(HyperLocalFreight.getFreight());
            binding.tvTotalKMValue.setText(HyperLocalFreight.getTotalKM());
        }
    }

    private void processEditApiResponse(CommonResponse<HyperLocalForm> response) {

        try {
            String s = new Gson().toJson(response);
            Log.d("HyperlocalForm", s + "");
            stopProgressDialog();

            if (response != null) {

                if (response.getStatus().equalsIgnoreCase("1")) {

                    HyperLocalForm form = response.getData();

                    binding.edtBookingDate.setText(AppUtils.convertDateFormat(AppConstants.API_DATE_FORMAT, AppConstants.CALENDAR_DATE_FORMAT, form.getBookingDateTime()));

                    paymentTypeId = form.getPaymentTypeId();
                    addressTypeId = form.getAddressTypeId();
                    weighId = form.getWeightTypeId();
                    //     distancePaymentId = form.getDistancePaymentTypeId();

                    binding.edtPaymentType.setText(form.getPaymentTypeName());
                    binding.edtAddressType.setText(form.getAddressTypeName());
                    binding.edtWeight.setText(form.getWeightTypeName());

                    Objects.requireNonNull(binding.tnlPostcode.getEditText()).setText(form.getPickupPostcode());
                    Objects.requireNonNull(binding.tnlAddress1.getEditText()).setText(form.getPickupAddress1());
                    Objects.requireNonNull(binding.tnlAddress2.getEditText()).setText(form.getPickupAddress2());
                    Objects.requireNonNull(binding.tnlName.getEditText()).setText(form.getPickupPersonName());
                    Objects.requireNonNull(binding.tnlContactNo.getEditText()).setText(form.getPickupContactNo());

                    Objects.requireNonNull(binding.tnlDeliveryPostcode.getEditText()).setText(form.getDeliveryPostcode());
                    Objects.requireNonNull(binding.tnlDeliveryAddress1.getEditText()).setText(form.getDeliveryAddress1());
                    Objects.requireNonNull(binding.tnlDeliveryAddress2.getEditText()).setText(form.getDeliveryAddress2());
                    Objects.requireNonNull(binding.tnlDeliveryName.getEditText()).setText(form.getDeliveryPersonName());
                    Objects.requireNonNull(binding.tnlDeliveryContactNo.getEditText()).setText(form.getDeliveryContactNo());

                    try {
                        String s1 = form.getNo()/*.replace("HLR","")*/;
                        binding.tnlHlrNo.getEditText().setText(s1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    binding.cbDocuments.setChecked(form.getIsDocument() == 1);
                    binding.cbElectronics.setChecked(form.getIsElectronic() == 1);
                    binding.cbMedicine.setChecked(form.getIsMedicine() == 1);
                    binding.cbEssentials.setChecked(form.getIsEssential() == 1);
                    binding.cbOthers.setChecked(form.getIsOthers() == 1);

                    //binding.rbPrepaid.setChecked(form.getDistancePaymentTypeId() == 1);
                    //binding.rbCOD.setChecked(form.getDistancePaymentTypeId() == 2);

                    binding.tvFreightValue.setText(form.getFreight());
                    binding.tvTotalKMValue.setText(form.getKm());

                    Objects.requireNonNull(binding.tnlSpecialInstruction.getEditText()).setText(form.getSpecialInstruction());

                    hyperLocalDetailFormArrayList = form.getDetails();
                    loadProductDetails();

                } else
                    displayLongToast(mContext, response.getMessage());

            } else {
                displayLongToast(mContext, "Something went wrong!");
                finish();
            }

        } catch (Exception e) {
            stopProgressDialog();
            e.printStackTrace();
            errorLog(e);
        }
    }

    private void processManageApiResponse(CommonResponse<String> response) {

        try {

            if (response != null) {

                if (response.getStatus().equalsIgnoreCase("1")) {

                    displayLongToast(mContext, response.getMessage());
                    finish();
                }
            } else {
                displayLongToast(mContext, "Something went wrong!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            errorLog(e);
        }
    }

    @Override
    public void onApiError(String endPointName, String errorMessage) {

        stopProgressDialog();

        super.onApiError(endPointName, errorMessage);

        switch (endPointName) {

            case API_HYPER_LOCAL_MANAGE:
            case API_HYPER_LOCAL_EDIT:
            case API_HYPER_LOCAL_Freight:
                displayLongToast(mContext, errorMessage);
                break;
        }
    }

    @Override
    public void onApiFailure(String endPointName, String failureMessage) {

        stopProgressDialog();

        super.onApiFailure(endPointName, failureMessage);

        switch (endPointName) {

            case API_HYPER_LOCAL_MANAGE:
            case API_HYPER_LOCAL_EDIT:
            case API_HYPER_LOCAL_Freight:
                displayLongToast(mContext, "Response Failure!");
                break;
        }
    }

    @Override
    public void onMasterBottomSheetViewClick(int itemPosition, Object object, String edtID) {

        CommonListResponse commonListResponse = (CommonListResponse) object;

        switch (edtID) {

            case AppConstants.SP_PAYMENT_TYPE5:
                binding.edtPaymentType.setText(commonListResponse.getName());
                paymentTypeId = AppUtils.castToInteger(commonListResponse.getId());
                break;

            case AppConstants.SP_ADDRESS_TYPE:
                binding.edtAddressType.setText(commonListResponse.getName());
                addressTypeId = AppUtils.castToInteger(commonListResponse.getId());
                break;

            case AppConstants.SP_WEIGHT:
                binding.edtWeight.setText(commonListResponse.getName());
                weighId = AppUtils.castToInteger(commonListResponse.getId());
                break;
        }
    }

    @Override
    public void onMasterBottomSheetInfoClick(int itemPosition, Object object, View... views) {
    }

    private boolean validateProductDetails() {
        return appValidation.validateString(dialogProductDetailBinding.tnlProductName) &&
                appValidation.validateString(dialogProductDetailBinding.tnlQty) &&
                appValidation.validateString(dialogProductDetailBinding.tnlAmount);
    }

    private boolean validateHyperLocalRequest() {

        return appValidation.validateString(binding.tnlBookingDate) &&
                appValidation.validateString(binding.tnlPaymentType) &&
                appValidation.validateString(binding.tnlAddressType);
    }

    @Override
    public void onViewClick(int itemPosition, Object object) {
    }

    @Override
    public void onInfoClick(int itemPosition, Object object, View... views) {
    }

    @Override
    public void onEditClick(int itemPosition, Object object) {
    }

    @Override
    public void onDeleteClick(int itemPosition, Object object) {
        this.itemPosition = itemPosition;
        ConfirmationDialogManager.getInstance(mContext, this).askConsent("Do you want to delete this product details ?");
    }

    @Override
    public void onConfirmationPositiveClick() {
        hyperLocalDetailFormArrayList.remove(itemPosition);
        baseAdapter.notifyItemRemoved(itemPosition);
        int totalQty = 0;
        double totalAmount = 0;
        for (int i = 0; i < hyperLocalDetailFormArrayList.size(); i++) {
            totalQty += hyperLocalDetailFormArrayList.get(i).getProductQuantity();
            totalAmount += hyperLocalDetailFormArrayList.get(i).getProductAmount();
        }
        binding.tvTotalQty.setText(String.valueOf(totalQty));
        binding.tvTotalAmount.setText(String.valueOf(totalAmount));
    }

    @Override
    public void onConfirmationNegativeClick() {
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

        String postcode = binding.tnlPostcode.getEditText().getText().toString().trim();
        String postcodeDelivery = binding.tnlDeliveryPostcode.getEditText().getText().toString().trim();

        if (postcodeDelivery.length() >= 5) {
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {

                    getCalculationsOfFreight(postcode, postcodeDelivery);
                }

            }, DELAY);
        }
    }

    private void getCalculationsOfFreight(String postcode, String postcodeDelivery) {
        try {

            CommanRequestModel requestModel = new CommanRequestModel();
            requestModel.setPickupPostcode(postcode);
            requestModel.setDeliveryPostcode(postcodeDelivery);
            ApiService apiService = ApiClient.createService(ApiService.class);

            Call<CommonResponse<HyperLocalFreight>> call = apiService.getHyperLocalFreight(requestModel);

            ApiManager.callRetrofit(call, API_HYPER_LOCAL_Freight, this, false);

        } catch (Exception e) {
            stopProgressDialog();
            e.printStackTrace();
            errorLog(e);
        }
    }
}