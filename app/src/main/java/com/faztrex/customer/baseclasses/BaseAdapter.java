package com.faztrex.customer.baseclasses;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.faztrex.customer.BR;
import com.faztrex.customer.R;
import com.faztrex.customer.databinding.ItemDimensionDetailBinding;
import com.faztrex.customer.databinding.ItemDocketListBinding;
import com.faztrex.customer.databinding.ItemHyperLocalRequestBinding;
import com.faztrex.customer.databinding.ItemPickupRequestListBinding;
import com.faztrex.customer.databinding.ItemProductDetailsBinding;
import com.faztrex.customer.listeners.general.CommonActionListener;
import com.faztrex.customer.listeners.general.RatingActionListener;
import com.faztrex.customer.models.hyperlocal.HyperLocalDetailForm;
import com.faztrex.customer.models.hyperlocal.HyperLocalList;
import com.faztrex.customer.models.pickuprequest.PickupRequestDetailModel;
import com.faztrex.customer.network.response.docket.DocketListResponseModel;
import com.faztrex.customer.network.response.pickuprequest.PickupListResponseModel;
import com.faztrex.customer.utils.AppConstants;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class BaseAdapter extends RecyclerView.Adapter<BaseAdapter.MyViewHolder> {

    private final int rawLayoutId;
    private final Context mContext;
    private ArrayList<?> itemsArrayList;
    private CommonActionListener commonActionListener;
    private RatingActionListener ratingActionListener;
    private boolean isView;

    public BaseAdapter(Context mContext, ArrayList<?> itemsArrayList, int rawLayoutId) {
        this.mContext = mContext;
        this.itemsArrayList = itemsArrayList;
        this.rawLayoutId = rawLayoutId;
    }

    public BaseAdapter(Context mContext, ArrayList<?> itemsArrayList, int rawLayoutId, CommonActionListener commonActionListener) {
        this.mContext = mContext;
        this.itemsArrayList = itemsArrayList;
        this.rawLayoutId = rawLayoutId;
        this.commonActionListener = commonActionListener;
    }

    public BaseAdapter(Context mContext, ArrayList<?> itemsArrayList, int rawLayoutId, boolean isView, CommonActionListener commonActionListener) {
        this.mContext = mContext;
        this.itemsArrayList = itemsArrayList;
        this.rawLayoutId = rawLayoutId;
        this.isView = isView;
        this.commonActionListener = commonActionListener;
    }

    public BaseAdapter(Context mContext, ArrayList<?> itemsArrayList, int rawLayoutId, boolean isView, CommonActionListener commonActionListener, String calledFrom) {
        this.mContext = mContext;
        this.itemsArrayList = itemsArrayList;
        this.rawLayoutId = rawLayoutId;
        this.isView = isView;
        this.commonActionListener = commonActionListener;
    }

    public BaseAdapter(Context mContext, ArrayList<?> itemsArrayList, int rawLayoutId, RatingActionListener ratingActionListener) {
        this.mContext = mContext;
        this.itemsArrayList = itemsArrayList;
        this.rawLayoutId = rawLayoutId;
        this.ratingActionListener = ratingActionListener;
    }

    @SuppressLint("NonConstantResourceId")
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), rawLayoutId, parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.setBinding(itemsArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return itemsArrayList != null ? itemsArrayList.size() : 0;
    }

    public void filterList(ArrayList<?> itemsArrayList) {
        this.itemsArrayList = itemsArrayList;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ViewDataBinding binding;

        public MyViewHolder(@NonNull ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            if (commonActionListener == null)
                commonActionListener = (CommonActionListener) mContext;
        }

        @SuppressLint({"SimpleDateFormat", "UseCompatLoadingForDrawables", "SetTextI18n"})
        void setBinding(Object obj) {

            binding.setVariable(BR.data, obj);

            if (binding instanceof ItemDimensionDetailBinding) {

                PickupRequestDetailModel dimensionDetailModel = (PickupRequestDetailModel) obj;
                ((ItemDimensionDetailBinding) binding).setData(dimensionDetailModel);
                ((ItemDimensionDetailBinding) binding).tvSerialNo.setText("#" + (getLayoutPosition() + 1));

                ((ItemDimensionDetailBinding) binding).ivDelete.setOnClickListener(v -> commonActionListener.onDeleteClick(getAdapterPosition(), dimensionDetailModel));
                ((ItemDimensionDetailBinding) binding).ivEdit.setOnClickListener(v -> commonActionListener.onEditClick(getAdapterPosition(), dimensionDetailModel));

            } else if (binding instanceof ItemDocketListBinding) {

                GradientDrawable mGradientDrawable;

                DocketListResponseModel docket = (DocketListResponseModel) obj;

                if (docket.getBookingDate() != null) {

                    try {

                        if (!(docket.getBookingDate().isEmpty())) {

                            SimpleDateFormat sdf = new SimpleDateFormat(AppConstants.TRACKING_API_DATE_FORMAT, Locale.US);
                            SimpleDateFormat output = new SimpleDateFormat(AppConstants.CALENDAR_DATE_FORMAT, Locale.US);
                            Date d2 = sdf.parse(docket.getBookingDate());
                            docket.setBookingDate(output.format(d2));
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        docket.setBookingDate(docket.getBookingDate());
                        //  Log.e(TAG, e.getLocalizedMessage());
                    }
                    /*    String bookingDate = AppUtils.convertDateFormat(AppConstants.TRACKING_API_DATE_FORMAT, AppConstants.CALENDAR_DATE_FORMAT, docket.getBookingDate());
                        docket.setBookingDate(bookingDate);
                   */
                }

                mGradientDrawable = (GradientDrawable) ((ItemDocketListBinding) binding).tvDeliveryStatus.getBackground();

                // set drawable background color
                mGradientDrawable.setColor(mContext.getResources().getColor(R.color.colorThemeRed));

                if (docket.getDeliveryStatus().equalsIgnoreCase(AppConstants.STATUS_DELIVERED)) {
                    ((ItemDocketListBinding) binding).tvDeliveryStatus.setVisibility(View.VISIBLE);
                } else {
                    ((ItemDocketListBinding) binding).tvDeliveryStatus.setVisibility(View.GONE);
                }

                switch (docket.getCustomerType()) {

                    case AppConstants.CUSTOMER_TYPE_CREDIT:
                        ((ItemDocketListBinding) binding).ivCustomerType.setVisibility(View.GONE); //GONE
                        ((ItemDocketListBinding) binding).tvPaymentType.setVisibility(View.VISIBLE);  //GONE
                        break;

                    case AppConstants.CUSTOMER_TYPE_RETAIL:
                        ((ItemDocketListBinding) binding).ivCustomerType.setVisibility(View.VISIBLE);
                        ((ItemDocketListBinding) binding).tvPaymentType.setVisibility(View.VISIBLE);

                        // get drawable background
                        mGradientDrawable = (GradientDrawable) ((ItemDocketListBinding) binding).tvPaymentType.getBackground();

                        switch (docket.getPaymentType()) {

                            case AppConstants.PAY_TYPE_PAID:

                                mGradientDrawable.setColor(mContext.getResources().getColor(R.color.colorGreenShade1));
                                ((ItemDocketListBinding) binding).tvPaymentType.setTextColor(mContext.getResources().getColor(R.color.colorLightWhite));
                                break;

                            case AppConstants.PAY_TYPE_TO_PAY:

                                mGradientDrawable.setColor(mContext.getResources().getColor(R.color.colorGreyShade3));
                                ((ItemDocketListBinding) binding).tvPaymentType.setTextColor(mContext.getResources().getColor(R.color.colorTextPrimary));
                                break;

                            case AppConstants.PAY_TYPE_TO_BE_BILLED:
                                mGradientDrawable.setColor(mContext.getResources().getColor(R.color.colorGreyShade1));
                                ((ItemDocketListBinding) binding).tvPaymentType.setTextColor(mContext.getResources().getColor(R.color.colorLightWhite));
                                break;
                        }
                        break;
                }

                switch (docket.getDispatchMode()) {

                    case AppConstants.MODE_SURFACE:
                        ((ItemDocketListBinding) binding).ivDispatchMode.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.ic_mode_surface));
                        break;

                    case AppConstants.MODE_AIR:
                        ((ItemDocketListBinding) binding).ivDispatchMode.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.ic_mode_air));
                        break;
                }

                ((ItemDocketListBinding) binding).tvDocketNo.setText("#" + (getLayoutPosition() + 1) + ". " + docket.getDocketNo());

                ((ItemDocketListBinding) binding).relativeMain.setOnClickListener(v -> commonActionListener.onViewClick(getLayoutPosition(), null));

                ((ItemDocketListBinding) binding).ivRaing.setOnClickListener(v -> commonActionListener.onEditClick(getLayoutPosition(), docket));

            } else if (binding instanceof ItemPickupRequestListBinding) {

                PickupListResponseModel pickupListResponseModel = (PickupListResponseModel) obj;

                ((ItemPickupRequestListBinding) binding).setData(pickupListResponseModel);

                ((ItemPickupRequestListBinding) binding).ivEdit.setOnClickListener(v -> commonActionListener.onEditClick(getAdapterPosition(), pickupListResponseModel));
                ((ItemPickupRequestListBinding) binding).cvMain.setOnClickListener(v -> commonActionListener.onViewClick(getAdapterPosition(), pickupListResponseModel));
                ((ItemPickupRequestListBinding) binding).ivDelete.setOnClickListener(v -> commonActionListener.onDeleteClick(getAdapterPosition(), pickupListResponseModel));

            } else if (binding instanceof ItemProductDetailsBinding) {

                HyperLocalDetailForm detailForm = (HyperLocalDetailForm) obj;

                ((ItemProductDetailsBinding) binding).setData(detailForm);

                if (isView) {
                    ((ItemProductDetailsBinding) binding).ivDelete.setVisibility(View.GONE);
                }

                ((ItemProductDetailsBinding) binding).ivDelete.setOnClickListener(v -> commonActionListener.onDeleteClick(getAdapterPosition(), detailForm));

            } else if (binding instanceof ItemHyperLocalRequestBinding) {

                HyperLocalList data = (HyperLocalList) obj;

                ((ItemHyperLocalRequestBinding) binding).setData(data);

                if (ratingActionListener == null)
                    ratingActionListener = (RatingActionListener) mContext;

                ((ItemHyperLocalRequestBinding) binding).tvNo.setText("#" + (getLayoutPosition() + 1) + ". " + data.getNo());

                ((ItemHyperLocalRequestBinding) binding).ivTrack.setOnClickListener(v -> commonActionListener.onInfoClick(getLayoutPosition(), null));

                ((ItemHyperLocalRequestBinding) binding).tvStatus.setVisibility(View.VISIBLE);

                ((ItemHyperLocalRequestBinding) binding).ivDelete.setOnClickListener(v -> commonActionListener.onDeleteClick(getLayoutPosition(), obj));
                ((ItemHyperLocalRequestBinding) binding).ivEdit.setOnClickListener(v -> commonActionListener.onEditClick(getLayoutPosition(), obj));
                //((ItemHyperLocalRequestBinding) binding).tvStatus.setOnClickListener(v -> ratingActionListener.onSubmitClick(getLayoutPosition(), obj));

                binding.getRoot().setOnClickListener(v -> commonActionListener.onViewClick(getLayoutPosition(), obj));
            }

            binding.executePendingBindings();
        }
    }
}
