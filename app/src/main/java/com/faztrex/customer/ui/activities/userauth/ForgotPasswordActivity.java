package com.faztrex.customer.ui.activities.userauth;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.faztrex.customer.R;
import com.faztrex.customer.baseclasses.BaseActivity;
import com.faztrex.customer.customviews.SimpleAlertDialog;
import com.faztrex.customer.databinding.ActivityForgotPasswordBinding;
import com.faztrex.customer.network.model.request.AuthenticationRequestModel;
import com.faztrex.customer.network.response.CommonResponse;
import com.faztrex.customer.network.response.user.ForgotPassword;
import com.faztrex.customer.retrofit.ApiClient;
import com.faztrex.customer.retrofit.ApiConstant;
import com.faztrex.customer.retrofit.ApiListener;
import com.faztrex.customer.retrofit.ApiManager;
import com.faztrex.customer.retrofit.ApiService;
import com.faztrex.customer.utils.AppConstants;
import com.faztrex.customer.utils.NetworkUtils;

import java.util.Objects;

import retrofit2.Call;

import static com.faztrex.customer.utils.AppUtils.getStringValue;
import static com.faztrex.customer.utils.AppUtils.isEmptyString;

public class ForgotPasswordActivity extends BaseActivity implements ApiListener {

    private final String TAG = this.getClass().getSimpleName();

    private final Context mContext = this;
    private String verificationCode = "";
    private String userId = "";
    private ActivityForgotPasswordBinding binding;

    @Override
    public Activity setCurrentActivity() {
        return ForgotPasswordActivity.this;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_forgot_password);
        binding.btnProceed.setAlpha(0.7f);
        disableView(binding.btnProceed);

        Objects.requireNonNull(binding.tnlEmailAddress.getEditText()).addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String emailAddress = getTrimString(binding.tnlEmailAddress);

                if (AppConstants.PATTERN_EMAIL.matcher(emailAddress).matches()) {

                    binding.btnProceed.setAlpha(1f);
                    enableView(binding.btnProceed);
                    visibleView(binding.ivRightIcon);

                } else {

                    binding.btnProceed.setAlpha(0.7f);
                    disableView(binding.btnProceed);
                    hideView(binding.ivRightIcon);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.btnProceed.setOnClickListener(v -> {

            // hide soft keyboard
            hideSoftKeyboard();

            if (NetworkUtils.isConnected(mContext)) {

                // call method to send verification code on email address
                sendVerificationCode();

            } else {
                displayInternetToastMessage(mContext);
            }
        });
    }

    private void sendVerificationCode() {

        try {

            // start progress indicator
            startProgressDialog(currentActivity, false);

            // prepare request body
            AuthenticationRequestModel.ForgotPasswordRequest forgotPasswordRequest = new AuthenticationRequestModel().new ForgotPasswordRequest();

            forgotPasswordRequest.setEmailAddress(getTrimString(binding.tnlEmailAddress));

            ApiService apiService = ApiClient.createService(ApiService.class, "", "");
            Call<CommonResponse<ForgotPassword>> call = apiService.sendVerificationCode(forgotPasswordRequest);

            // call API
            ApiManager.callRetrofit(call, ApiConstant.API_FORGOT_PASSWORD, this, false);
        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }

    }


    @Override
    public void onApiSuccess(String endPointName, Object responseBody) {
        switch (endPointName) {

            case ApiConstant.API_FORGOT_PASSWORD:

                CommonResponse<ForgotPassword> forgotPasswordResponse = (CommonResponse<ForgotPassword>) responseBody;
                processForgotResponse(forgotPasswordResponse);
                break;
        }
    }

    @Override
    public void onApiError(String endPointName, String errorMessage) {

        printErrorLog(TAG, errorMessage);

        // show error toast message
        displayLongToast(mContext, errorMessage);
    }

    @Override
    public void onApiFailure(String endPointName, String failureMessage) {

        printErrorLog(TAG, failureMessage);

        // show error toast message
        displayLongToast(mContext, mContext.getResources().getString(R.string.err_msg_api_response_failure));
    }

    private void processForgotResponse(CommonResponse<ForgotPassword> response) {

        try {

            switch (response.getStatus()) {

                case AppConstants.STATUS_SUCCESS:

                    verificationCode = response.getData().getVerificationCode();
                    userId = response.getData().getUserId();

                    printInfoLog(TAG, "Verification Code is : " + verificationCode);

                    if (!isEmptyString(verificationCode)) {
                        startNavigation();
                    }

                    break;

                case AppConstants.STATUS_FAILURE:

                    showPrompt(getStringValue(response.getMessage()));
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    private void startNavigation() {

        Intent mIntent = new Intent(mContext, VerificationActivity.class);
        mIntent.putExtra(mContext.getResources().getString(R.string.key_user_id), getStringValue(userId));
        mIntent.putExtra(mContext.getResources().getString(R.string.key_email_address), getTrimString(binding.tnlEmailAddress));
        mIntent.putExtra(mContext.getResources().getString(R.string.key_verification_code), getStringValue(verificationCode));
        startActivity(mIntent);
        finish();
    }

    private void showPrompt(String promptMessage) {

        simpleAlertDialog = new SimpleAlertDialog(mContext) {

            @Override
            public boolean setDialogCancelable() {
                return false;
            }

            @Override
            public String setDialogTitle() {
                return "Uh Oh";
            }

            @Override
            public String setDialogMessage() {
                return promptMessage;
            }

            @Override
            public Drawable setDialogIcon() {
                return mContext.getResources().getDrawable(R.mipmap.ic_launcher);
            }

            @Override
            public String setDialogPositiveButtonText() {
                return mContext.getResources().getString(R.string.btn_title_ok);
            }

            @Override
            public DialogInterface.OnClickListener onDialogPositiveButtonClick() {
                return (dialog, which) -> dialog.dismiss();
            }

            @Override
            public String setDialogNegativeButtonText() {
                return null;
            }

            @Override
            public DialogInterface.OnClickListener onDialogNegativeButtonClick() {
                return null;
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

}