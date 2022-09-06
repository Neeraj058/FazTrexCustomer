package com.faztrex.customer.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.cittasolutions.cittalibrary.utils.AppConstant;
import com.faztrex.customer.R;
import com.faztrex.customer.databinding.ItemDocketTrackingTimelineBinding;
import com.faztrex.customer.network.response.docket.tracking.TrackingInfo;

import java.util.ArrayList;

import static com.faztrex.customer.utils.AppUtils.convertDateFormat;
import static com.faztrex.customer.utils.AppUtils.getStringValue;

public class DocketTrackingAdapter extends RecyclerView.Adapter<DocketTrackingAdapter.MyViewHolder> {

    private final Context mContext;
    private final ArrayList<TrackingInfo> trackingInfoList;

    public DocketTrackingAdapter(Context mContext, ArrayList<TrackingInfo> trackingInfoList) {
        this.mContext = mContext;
        this.trackingInfoList = trackingInfoList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater mLayoutInflater = LayoutInflater.from(viewGroup.getContext());
        ItemDocketTrackingTimelineBinding binding = DataBindingUtil.inflate(mLayoutInflater, R.layout.item_docket_tracking_timeline, viewGroup, false);

        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        TrackingInfo trackingInfo = trackingInfoList.get(i);

        myViewHolder.binding.tvTimelineActivityStatus.setText(getStringValue(trackingInfo.getTrackingDetails().get(0).getStatus()));
        myViewHolder.binding.tvTimelineDate.setText(convertDateFormat(AppConstant.API_DATE_FORMAT, AppConstant.DISPLAY_DATE_FORMAT_2, getStringValue(trackingInfo.getTrackingDate())));
        myViewHolder.binding.tvTimelineLocation.setText(getStringValue(trackingInfo.getTrackingDetails().get(0).getLocation()));
    }

    @Override
    public int getItemCount() {
        return trackingInfoList != null ? trackingInfoList.size() : 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final ItemDocketTrackingTimelineBinding binding;

        MyViewHolder(ItemDocketTrackingTimelineBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
