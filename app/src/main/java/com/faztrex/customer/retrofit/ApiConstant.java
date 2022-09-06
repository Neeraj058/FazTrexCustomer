package com.faztrex.customer.retrofit;

public class ApiConstant {

    //region Authentication
    public static final String API_CUST = "api/cust/";
    public static final String API_LOGIN = API_CUST + "login";
    public static final String API_FORGOT_PASSWORD = API_CUST + "forgotpassword";
    public static final String API_RESET_PASSWORD = API_CUST + "resetpassword";
    public static final String API_CHANGE_PASSWORD = API_CUST + "changepassword";
    //endregion

    public static final String API_STATE = "api/master/state";
    public static final String API_STATE2 = "api/master/state";
    public static final String API_CITY = "api/master/city";

    public static final String API_CONSIGNOR_INFORMATION = API_CUST + "address";

    //MPESA
    public static final String API_MPESA_PAYMENT_REQUEST = "api/docket/mpesarequest";

    public static final String API_MPESA_PAYMENT_REQUEST_STATUS = "api/docket/mpesarequeststatus";


    public static final String API_POST_CODE = "api/master/postcode";
    public static final String API_VERTICLE = "api/master/verticle";
    public static final String API_PACKING_TYPE = "api/master/packingtype";
    public static final String API_REASON = "api/master/reason";
    public static final String API_PAYMENT_TYPE = "api/master/paymenttype";
    public static final String API_BANK = "api/master/bank";
    public static final String API_WEIGHT = "api/master/weight";
    public static final String API_ADDRESS_TYPE = "api/master/addresstype";
    public static final String API_DISPATCH_MODE = "api/master/dispatchmode";

    public static final String API_DASHBOARD_COUNT = "api/dashboard/counts";
    ///http://122.15.16.29:1002/api/driver/dashboard/customercounts
    public static final String API_DASHBOARD_COUNT_NEW = "api/driver/dashboard/customercounts";

    public static final String API_DIMENSION_CALCULATION = "api/docket/dimensiondetailcalculation";
    //region Docket
    public static final String API_DOCKET_LIST = "api/docket/cust/list";
    public static final String API_GET_DOCKET_DETAIL = "api/docket/docketdetailbyid";
    public static final String API_DOCKET_TRACKING = "api/docket/dockettracking";
    public static final String API_DOCKET_RATING = "api/docket/docketrating";
    //region Hyper Local
    public static final String API_HYPER_LOCAL_LIST = "api/hyperlocal/list";
    public static final String API_HYPER_LOCAL_MANAGE = "api/hyperlocal/manage";
    public static final String API_HYPER_LOCAL_EDIT = "api/hyperlocal/edit";
    public static final String API_HYPER_LOCAL_Freight = "api/hyperlocal/othercharges";
    //endregion
    // API Version
    static final String API_VERSION_1_0 = "api-version: 1.0";
    static final String API_VERSION_2_0 = "api-version: 2.0";
    static final String API_VERSION_3_0 = "api-version: 3.0";
    static final String API_VERSION_4_0 = "api-version: 4.0";
    //endregion
    //region Masters
    static final String API_COUNTRY = "api/master/country";
    //endregion
    static final String API_UPLOAD_POD = "uploadpodforapp/uploaddocs";
    //region Pickup Request
    private static final String API_PICKUP = "api/pickup/";
    public static final String API_PICKUP_LIST = API_PICKUP + "list";
    //endregion
    public static final String API_PICKUP_REQUEST_MANAGE = API_PICKUP + "manage";
    public static final String API_PICKUP_REQUEST_EDIT = API_PICKUP + "edit";
    public static final String API_PICKUP_REQUEST_OTHER_CHARGES = API_PICKUP + "othercharges";
    public static final String API_PICKUP_COUPON = API_PICKUP + "Couponcode";
    public static final String API_PICKUP_OTP = API_PICKUP + "OTP";
    public static final String API_PICKUP_VERIFYOTP = API_PICKUP + "VerifyOTP";
    //endregion
}
