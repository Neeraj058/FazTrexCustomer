package com.faztrex.customer.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.faztrex.customer.R;
import com.faztrex.customer.databinding.ItemDimensionDetailForDocketBinding;
import com.faztrex.customer.network.response.docket.Dimension;

import java.util.ArrayList;

public class DocketDimensionAdapter extends RecyclerView.Adapter<DocketDimensionAdapter.MyViewHolder> {

    private final ArrayList<Dimension> dimensionList;
    private final OnDimensionClickListener listener;
    private final boolean isReadOnly;
    private final boolean fmCreated;

    public DocketDimensionAdapter(ArrayList<Dimension> dimensionList, OnDimensionClickListener listener, boolean isReadOnly, boolean fmCreated) {
        this.dimensionList = dimensionList;
        this.listener = listener;
        this.isReadOnly = isReadOnly;
        this.fmCreated = fmCreated;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater mLayoutInflater = LayoutInflater.from(viewGroup.getContext());
        ItemDimensionDetailForDocketBinding binding = DataBindingUtil.inflate(mLayoutInflater, R.layout.item_dimension_detail_for_docket, viewGroup, false);

        return new MyViewHolder(binding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        Dimension dimension = dimensionList.get(i);

        // set data using binding
        myViewHolder.setBinding(dimension);

        // set visibility of the component
        myViewHolder.binding.ivEdit.setVisibility(isReadOnly ? View.GONE : (fmCreated ? View.GONE : View.VISIBLE));
        myViewHolder.binding.ivDelete.setVisibility(isReadOnly ? View.GONE : (fmCreated ? View.GONE : View.VISIBLE));

        myViewHolder.binding.ivEdit.setOnClickListener(v -> listener.onUpdate(myViewHolder.getLayoutPosition(), dimension));

        myViewHolder.binding.ivDelete.setOnClickListener(v -> listener.onDelete(myViewHolder.getLayoutPosition(), dimension));
    }

    @Override
    public int getItemCount() {
        return dimensionList != null ? dimensionList.size() : 0;
    }

    public interface OnDimensionClickListener {

        void onUpdate(int itemPosition, Dimension dimension);

        void onDelete(int itemPosition, Dimension dimension);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final ItemDimensionDetailForDocketBinding binding;

        MyViewHolder(ItemDimensionDetailForDocketBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @SuppressLint("SetTextI18n")
        void setBinding(Dimension dimension) {
            binding.setDimension(dimension);
            binding.executePendingBindings();
            binding.tvSerialNo.setText("#" + (getLayoutPosition() + 1) + ". ");
        }
    }
}
