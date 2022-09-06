package com.faztrex.customer.ui.fragments.bottomsheet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import com.faztrex.customer.R;
import com.faztrex.customer.databinding.BottomsheetDimensionDetailBinding;
import com.faztrex.customer.models.pickuprequest.PickupRequestDetailModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomSheetDimensionDetail extends BottomSheetDialogFragment {

    private BottomsheetDimensionDetailBinding binding;
    private PickupRequestDetailModel pickupRequestDetailModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.AppBottomSheetDialogTheme);
    }

    public BottomSheetDimensionDetail(PickupRequestDetailModel pickupRequestDetailModel) {
        this.pickupRequestDetailModel = pickupRequestDetailModel;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.bottomsheet_dimension_detail, container, false);

        binding.ivclose.setOnClickListener(v -> dismiss());

        binding.setData(pickupRequestDetailModel);

        return binding.getRoot();
    }
}
