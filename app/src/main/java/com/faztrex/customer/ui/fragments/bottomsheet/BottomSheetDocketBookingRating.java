package com.faztrex.customer.ui.fragments.bottomsheet;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import com.faztrex.customer.R;
import com.faztrex.customer.databinding.ItemDocketBookingRatingBinding;
import com.faztrex.customer.network.response.docket.DocketListResponseModel;
import com.faztrex.customer.utils.AppConstants;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.Objects;

public class BottomSheetDocketBookingRating extends BottomSheetDialogFragment {

    ItemDocketBookingRatingBinding binding;

    private final RatingClick ratingClick;

    private final DocketListResponseModel docket;

    public BottomSheetDocketBookingRating(RatingClick ratingClick, DocketListResponseModel docket) {
        this.ratingClick = ratingClick;
        this.docket = docket;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.AppBottomSheetDialogTheme);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.item_docket_booking_rating, container, false);

        if (docket != null) {

            switch (docket.getDispatchMode()) {

                case AppConstants.MODE_SURFACE:
                    binding.ivDispatchMode.setImageDrawable(requireActivity().getResources().getDrawable(R.mipmap.ic_mode_surface));
                    break;

                case AppConstants.MODE_AIR:
                    binding.ivDispatchMode.setImageDrawable(Objects.requireNonNull(getActivity()).getResources().getDrawable(R.mipmap.ic_mode_air));
                    break;
            }
        }

        if (docket != null) {

            int rating = Integer.parseInt(docket.getRatingValue());

            switch (rating) {

                case 1:
                    binding.ratingBar.setRating(AppConstants.RATE_ONE);
                    break;

                case 2:
                    binding.ratingBar.setRating(AppConstants.RATE_TWO);
                    break;

                case 3:
                    binding.ratingBar.setRating(AppConstants.RATE_THREE);
                    break;

                case 4:
                    binding.ratingBar.setRating(AppConstants.RATE_FOUR);
                    break;

                case 5:
                    binding.ratingBar.setRating(AppConstants.RATE_FIVE);
                    break;

            }
        }

        binding.tvSubmit.setOnClickListener(v -> {
            ratingClick.onRatingClick(String.valueOf(Math.round(binding.ratingBar.getRating())));
            dismiss();
        });

        binding.setData(docket);

        return binding.getRoot();
    }

    public interface RatingClick {
        void onRatingClick(String rating);
    }
}
