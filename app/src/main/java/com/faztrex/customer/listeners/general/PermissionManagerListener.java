package com.faztrex.customer.listeners.general;

import java.util.ArrayList;

public interface PermissionManagerListener {

    void onSinglePermissionGranted(String permissionName, String... endPoint);

    void onMultiplePermissionGranted(ArrayList<String> permissionName, String... endPoint);

}
