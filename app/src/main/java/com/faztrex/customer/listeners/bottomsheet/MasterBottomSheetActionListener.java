package com.faztrex.customer.listeners.bottomsheet;

import android.view.View;

public interface MasterBottomSheetActionListener {

    void onMasterBottomSheetViewClick(int itemPosition, Object object, String edtID);

    void onMasterBottomSheetInfoClick(int itemPosition, Object object, View... views);


}
