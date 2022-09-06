package com.faztrex.customer.listeners.general;

import android.view.View;

public interface RatingActionListener {

    void onRatingClick(int itemPosition, Object object);

    void onSubmitClick(int itemPosition, Object object, View... views);

    void onCancelClick(int itemPosition, Object object);
}
