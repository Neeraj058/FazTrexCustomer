package com.faztrex.customer.ui.activities.notifications;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.faztrex.customer.R;
import com.faztrex.customer.baseclasses.BaseActivity;
import com.faztrex.customer.baseclasses.BaseAdapter;
import com.faztrex.customer.databinding.ActivityPushNotificationsBinding;
import com.faztrex.customer.listeners.general.CommonActionListener;
import com.faztrex.customer.retrofit.ApiListener;

import java.util.ArrayList;

public class PushNotifications extends BaseActivity
        implements View.OnClickListener, ApiListener,
        CommonActionListener{

    ActivityPushNotificationsBinding binding;
    private final Context mContext = this;
    private BaseAdapter baseAdapter;
    ArrayList notificationsListArrayList=new ArrayList();

    @Override
    public Activity setCurrentActivity() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(PushNotifications.this, R.layout.activity_push_notifications);

        binding.toolbar.tvHeaderTitle.setText(R.string.nav_menu_title_notification);

        binding.toolbar.ivBack.setOnClickListener(v -> finish());


        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        binding.rvNotifications.setLayoutManager(mLayoutManager);
        binding.rvNotifications.setItemAnimator(new DefaultItemAnimator());

        baseAdapter = new BaseAdapter(mContext, notificationsListArrayList, R.layout.item_notification, this);
        binding.rvNotifications.setAdapter(baseAdapter);

        getNotificationsList();


    }

    private void getNotificationsList() {
//        TODO: Notification API call is remaining here
        Toast.makeText(mContext, "Call Api to get NotificationsList Here", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onViewClick(int itemPosition, Object object) {

    }

    @Override
    public void onInfoClick(int itemPosition, Object object, View... views) {

    }

    @Override
    public void onEditClick(int itemPosition, Object object) {

    }

    @Override
    public void onDeleteClick(int itemPosition, Object object) {

    }
}