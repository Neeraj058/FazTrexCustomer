package com.faztrex.customer.ui.fragments.bottomsheet;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.faztrex.customer.BR;
import com.faztrex.customer.R;
import com.faztrex.customer.databinding.ItemMasterButtomSheetDialogBinding;
import com.faztrex.customer.databinding.MasterBottomSheetLayoutBinding;
import com.faztrex.customer.listeners.bottomsheet.MasterBottomSheetActionListener;
import com.faztrex.customer.network.response.CommonListResponse;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

public class MasterBottomSheetFragment extends BottomSheetDialogFragment {

    private ArrayList<?> stringArrayList;
    private Context mContext;
    private MasterBottomSheetActionListener masterBottomSheetActionListener;
    private String masterBottomSheettitle;
    private String editextId;
    MasterButtomSheetDialogAdapter masterButtomSheetDialogAdapter;
    BottomSheetBehavior bottomSheetBehavior;
    MasterBottomSheetLayoutBinding binding;

    public MasterBottomSheetFragment(Context mContext, ArrayList<?> stringArrayList, String masterBottomSheettitle, String editextId, MasterBottomSheetActionListener masterBottomSheetActionListener) {
        this.mContext = mContext;
        this.stringArrayList = stringArrayList;
        this.masterBottomSheettitle = masterBottomSheettitle;
        this.editextId = editextId;
        this.masterBottomSheetActionListener = masterBottomSheetActionListener;
    }

    public MasterBottomSheetFragment(Context mContext, String masterBottomSheettitle, String editextId, MasterBottomSheetActionListener masterBottomSheetActionListener) {
        this.mContext = mContext;
        this.masterBottomSheettitle = masterBottomSheettitle;
        this.editextId = editextId;
        this.masterBottomSheetActionListener = masterBottomSheetActionListener;
    }

    public MasterBottomSheetFragment(ArrayList<?> stringArrayList) {
        this.stringArrayList = stringArrayList;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        BottomSheetDialog bottomSheet = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);

        View view = View.inflate(getContext(), R.layout.master_bottom_sheet_layout, null);
        binding = DataBindingUtil.bind(view);
        bottomSheet.setContentView(view);

        binding.rvBottomSheetItems.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        masterButtomSheetDialogAdapter = new MasterButtomSheetDialogAdapter(getActivity(), stringArrayList, R.layout.item_master_buttom_sheet_dialog, editextId, masterBottomSheetActionListener);
        binding.rvBottomSheetItems.setAdapter(masterButtomSheetDialogAdapter);
        bottomSheetBehavior = BottomSheetBehavior.from((View) (view.getParent()));

        bottomSheetBehavior.setPeekHeight(BottomSheetBehavior.PEEK_HEIGHT_AUTO);
        //setting max height of bottom sheet
        binding.rvBottomSheetItems.setMinimumHeight((Resources.getSystem().getDisplayMetrics().heightPixels) / 2);

        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int i) {
                if (BottomSheetBehavior.STATE_EXPANDED == i) {
                    showView(binding.appBarLayout, getActionBarSize());
                    hideAppBar(binding.tvBottomSheetLabel);

                }
                if (BottomSheetBehavior.STATE_COLLAPSED == i) {
                    hideAppBar(binding.appBarLayout);
                    showView(binding.tvBottomSheetLabel, getActionBarSize());
                }

                if (BottomSheetBehavior.STATE_HIDDEN == i) {
                    dismiss();
                }

            }

            @Override
            public void onSlide(@NonNull View view, float v) {

            }
        });

        hideAppBar(binding.appBarLayout);

        binding.edtSearchBottomSheet.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //after the change calling the method and passing the search input
                filter(editable.toString());
            }
        });

        binding.tvBottomSheetLabel.setText(masterBottomSheettitle);

        return bottomSheet;
    }

    private void filter(String text) {

        ArrayList<Object> filteredNames = new ArrayList<>();

        for (Object s : stringArrayList) {

            CommonListResponse obj = null;
            if (s instanceof CommonListResponse)
                obj = (CommonListResponse) s;

            if (obj != null && obj.getName().toLowerCase().contains((text.toLowerCase()))) {
                filteredNames.add(s);
            }
        }

        masterButtomSheetDialogAdapter.filterList(filteredNames);
    }

    @Override
    public void onStart() {
        super.onStart();

        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }

    private void hideAppBar(View view) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.height = 0;
        view.setLayoutParams(params);

    }

    private void showView(View view, int size) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.height = size;
        view.setLayoutParams(params);
    }

    private int getActionBarSize() {
        final TypedArray array = getContext().getTheme().obtainStyledAttributes(new int[]{android.R.attr.actionBarSize});
        int size = (int) array.getDimension(0, 0);
        return size;
    }

    public class MasterButtomSheetDialogAdapter extends RecyclerView.Adapter<MasterButtomSheetDialogAdapter.MyViewHolder> {

        private final int rawLayoutId;
        private final Context mContext;
        private final boolean isFileListLayout = false;
        private ArrayList<?> itemsArrayList;
        private MasterBottomSheetActionListener masterBottomSheetActionListener;
        private String edtId;

        public MasterButtomSheetDialogAdapter(Context mContext, ArrayList<?> itemsArrayList, int rawLayoutId) {
            this.mContext = mContext;
            this.itemsArrayList = itemsArrayList;
            this.rawLayoutId = rawLayoutId;
        }

        public MasterButtomSheetDialogAdapter(Context mContext, ArrayList<?> itemsArrayList, int rawLayoutId, MasterBottomSheetActionListener masterBottomSheetActionListener) {
            this.mContext = mContext;
            this.itemsArrayList = itemsArrayList;
            this.rawLayoutId = rawLayoutId;
            this.masterBottomSheetActionListener = masterBottomSheetActionListener;
        }

        public MasterButtomSheetDialogAdapter(Context mContext, ArrayList<?> itemsArrayList, int rawLayoutId, String edtID, MasterBottomSheetActionListener masterBottomSheetActionListener) {
            this.mContext = mContext;
            this.itemsArrayList = itemsArrayList;
            this.rawLayoutId = rawLayoutId;
            this.edtId = edtID;
            this.masterBottomSheetActionListener = masterBottomSheetActionListener;
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

                if (binding instanceof ItemMasterButtomSheetDialogBinding) {
                    if (masterBottomSheetActionListener == null)
                        masterBottomSheetActionListener = (MasterBottomSheetActionListener) mContext;
                }
            }

            @SuppressLint({"SimpleDateFormat", "UseCompatLoadingForDrawables"})
            void setBinding(Object obj) {

                binding.setVariable(BR.data, obj);

                if (binding instanceof ItemMasterButtomSheetDialogBinding) {

                    CommonListResponse commonListResponse = (CommonListResponse) obj;
                    ((ItemMasterButtomSheetDialogBinding) binding).setData(commonListResponse);

                    ((ItemMasterButtomSheetDialogBinding) binding).ItemBottomSheetMainLayout.setOnClickListener(v -> {
                        masterBottomSheetActionListener.onMasterBottomSheetViewClick(getAdapterPosition(), commonListResponse, edtId);
                        dismiss();
                    });
                }
            }

        }
    }

}
