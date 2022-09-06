package com.faztrex.customer.ui.fragments.docket;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;


import com.cittasolutions.cittalibrary.utils.AppConstant;
import com.faztrex.customer.R;
import com.faztrex.customer.baseclasses.BaseFragment;
import com.faztrex.customer.databinding.FragmentDocketChargesBinding;
import com.faztrex.customer.network.response.docket.DocketCalculation;
import com.faztrex.customer.utils.AppConstants;

import java.util.Objects;

import static com.faztrex.customer.utils.AppUtils.castToDouble;
import static com.faztrex.customer.utils.AppUtils.getFormattedString;
import static com.faztrex.customer.utils.AppUtils.getStringValue;


public class DocketChargesFragment extends BaseFragment {

    private final String TAG = this.getClass().getSimpleName();
    public static Context mAddLrContext;

    @SuppressLint("StaticFieldLeak")
    private static FragmentDocketChargesBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_docket_charges, container, false);
        View view = binding.getRoot();

        mAddLrContext = getActivity();
        // hide keyboard
        requireActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        return view;
    }

    /*
     * Method to set docket charges in second fragment
     *
     *
     * */
    static void setDocketCharges(DocketCalculation docketCalculation) {

        try {

            if (docketCalculation != null) {

                binding.tvBasicCharge.setText(getFormattedString(String.valueOf(castToDouble(getStringValue(docketCalculation.getBasicChargeAmount()))), AppConstants.FORMAT_2_F));
                binding.tvValueSurcharge.setText(getFormattedString(String.valueOf(castToDouble(getStringValue(docketCalculation.getValueSurchargeAmount()))), AppConstants.FORMAT_2_F));
                binding.tvDocketCharge.setText(getFormattedString(String.valueOf(castToDouble(getStringValue(docketCalculation.getDocketChargeAmount()))), AppConstants.FORMAT_2_F));
                binding.tvGreenTax.setText(getFormattedString(String.valueOf(castToDouble(getStringValue(docketCalculation.getGreenChargeAmount()))), AppConstants.FORMAT_2_F));
                binding.tvDeliveryCharge.setText(getFormattedString(String.valueOf(castToDouble(getStringValue(docketCalculation.getDeliveryChargeAmount()))), AppConstants.FORMAT_2_F));
                binding.tvOdaCharge.setText(getFormattedString(String.valueOf(castToDouble(getStringValue(docketCalculation.getOdaChargesAmount()))), AppConstants.FORMAT_2_F));
                binding.tvToPayCharges.setText(getFormattedString(String.valueOf(castToDouble(getStringValue(docketCalculation.getToPayChargesAmount()))), AppConstants.FORMAT_2_F));
                binding.tvPackageHandlingCharges.setText(getFormattedString(String.valueOf(castToDouble(getStringValue(docketCalculation.getPkgHandlingChargeAmount()))), AppConstants.FORMAT_2_F));
                binding.tvHardCopyCharges.setText(getFormattedString(String.valueOf(castToDouble(getStringValue(docketCalculation.getHardCopyChargeAmount()))), AppConstants.FORMAT_2_F));
                binding.tvSubtotal.setText(getFormattedString(String.valueOf(castToDouble(getStringValue(docketCalculation.getSubTotalAmount()))), AppConstants.FORMAT_2_F));
                binding.tvSurcharge.setText(getFormattedString(String.valueOf(castToDouble(getStringValue(docketCalculation.getSurchargeAmount()))), AppConstants.FORMAT_2_F));
                binding.tvTotalFreightAmount.setText(getFormattedString(String.valueOf(castToDouble(getStringValue(docketCalculation.getTotalFreightAmount()))), AppConstants.FORMAT_2_F));
                binding.tvSgstPercentage.setText(getFormattedString(String.valueOf(castToDouble(getStringValue(docketCalculation.getSgstPercentage()))), AppConstants.FORMAT_0_F));
                binding.tvSgstAmount.setText(getFormattedString(String.valueOf(castToDouble(getStringValue(docketCalculation.getSgstAmount()))), AppConstants.FORMAT_2_F));
                binding.tvCgstPercentage.setText(getFormattedString(String.valueOf(castToDouble(getStringValue(docketCalculation.getCgstPercentage()))), AppConstants.FORMAT_0_F));
                binding.tvCgstAmount.setText(getFormattedString(String.valueOf(castToDouble(getStringValue(docketCalculation.getCgstAmount()))), AppConstants.FORMAT_2_F));
                binding.tvIgstPercentage.setText(getFormattedString(String.valueOf(castToDouble(getStringValue(docketCalculation.getIgstPercentage()))), AppConstants.FORMAT_0_F));
                binding.tvIgstAmount.setText(getFormattedString(String.valueOf(castToDouble(getStringValue(docketCalculation.getIgstAmount()))), AppConstants.FORMAT_2_F));
                binding.tvSummaryTotalFreightAmount.setText(getFormattedString(String.valueOf(castToDouble(getStringValue(docketCalculation.getTotalFreightAmount()))), AppConstants.FORMAT_2_F));
                binding.tvSummaryTotalTaxAmount.setText(getFormattedString(String.valueOf(castToDouble(getStringValue(docketCalculation.getTotalGstAmount()))), AppConstants.FORMAT_2_F));
                binding.tvGrandTotal.setText(getFormattedString(String.valueOf(castToDouble(getStringValue(docketCalculation.getNetAmount()))), AppConstants.FORMAT_2_F));
            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(DocketChargesFragment.class.getSimpleName(), e.getLocalizedMessage());
        }
    }
}