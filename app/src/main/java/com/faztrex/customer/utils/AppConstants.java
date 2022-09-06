package com.faztrex.customer.utils;

import java.util.regex.Pattern;

public class AppConstants {

    public static final long SPLASH_TIME = 1000L;

    // Constant for Preference File Name
    public static final String PREFERENCE_FILE_NAME = "FAZTREX_CUSTOMER_PREFERENCE";

    // Operation Type for Access Rights
    public static final int R_VIEW = 1;
    public static final int R_EDIT = 2;
    public static final int R_DELETE = 3;
    public static final int R_ADD = 4;
    public static final int R_PRINT = 5;

    public static final int DIMENSION_DETAIL_VALUE = 1728;

    // Map constants to get common API response
    public static final String KEY_STATE_ID = "KEY_STATE_ID";
    public static final String KEY_CITY_ID = "KEY_CITY_ID";
    // Docket Delivery Status
    public static final String STATUS_DELIVERED = "DELIVERED";
    // Customer Type
    public static final String CUSTOMER_TYPE_CREDIT = "CREDIT";
    public static final String CUSTOMER_TYPE_RETAIL = "RETAIL";
    // Navigation Header Title
    public static final String NAV_TITLE_PICK_UP_REQUEST = "Pick Up Request";
    public static final String NAV_TITLE_DOCKET_BOOKING = "Docket Booking";
    public static final String NAV_TITLE_DOCKET_TRACKING = "Docket Tracking";
    public static final String NAV_TITLE_MIS_REPORT = "MIS Report";
    public static final String NAV_TITLE_BILL = "Bill";
    public static final String NAV_TITLE_HYPER_LOCAL_REQUEST = "Hyper Local Request";
    public static final String NAV_TITLE_Notifications = "Notifications";
    // Payment Type
    public static final String PAY_TYPE_PAID = "PAID";
    public static final String PAY_TYPE_TO_PAY = "TO PAY";
    public static final String PAY_TYPE_TO_BE_BILLED = "TO BE BILLED";
    public static final String PAY_TYPE_COD = "COD";
    // Dispatch Mode
    public static final String MODE_SURFACE = "SURFACE";
    public static final String MODE_AIR = "AIR";
    // Common Status
    public static final String STATUS_ACTIVE = "1";
    public static final String STATUS_DELETE = "0";
    public static final String STATUS_IS_FROM = "2";
    // Constant for API Response
    public static final String STATUS_FAILURE = "0";
    public static final String STATUS_SUCCESS = "1";
    // String Format Pattern
    public static final String FORMAT_0_F = "%.0f";
    public static final String FORMAT_1_F = "%.1f";
    public static final String FORMAT_2_F = "%.2f";
    // Country ID
    public static final String COUNTRY_ID = "2";
    // Spinner Type
    public static final String SP_STATE = "SP_STATE";
    public static final String SP_CITY = "SP_CITY";
    public static final String SP_STATE2 = "SP_STATE2";
    public static final String SP_CITY2 = "SP_CITY2";
    public static final String SP_POSTCODE = "SP_POSTCODE";
    public static final String SP_POSTCODE2 = "SP_POSTCODE2";
    public static final String SP_PAYMENT_TYPE = "SP_PAYMENT_TYPE";
    public static final String SP_PAYMENT_TYPE2 = "SP_PAYMENT_TYPE2";
    public static final String SP_PAYMENT_TYPE3 = "SP_PAYMENT_TYPE3";
    public static final String SP_PAYMENT_TYPE4 = "SP_PAYMENT_TYPE4";
    public static final String SP_PAYMENT_TYPE5 = "SP_PAYMENT_TYPE5";
    public static final String SP_WEIGHT = "SP_WEIGHT";
    public static final String SP_ADDRESS_TYPE = "SP_ADDRESS_TYPE";
    public static final String SP_DISPATCH_MODE = "SP_DISPATCH_MODE";
    public static final String SP_VERTICAL = "SP_VERTICAL";
    public static final String SP_PACKING_TYPE = "SP_PACKING_TYPE";
    public static final String SP_REASON = "SP_REASON";
    public static final String SP_DELIVERY_TYPE = "SP_DELIVERY_TYPE";
    public static final String SP_DELIVERY_STATUS = "SP_DELIVERY_STATUS";
    public static final String SP_CONSIGNOR = "SP_CONSIGNOR";
    public static final String SP_BANK = "SP_BANK";
    // Display Page Record Count
    public static final int DISPLAY_RECORD_COUNT = 10;
    public static final int REQUEST_CODE_GPS_ENABLE = 2001;
    // Date Format
    public static final String API_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String TRACKING_API_DATE_FORMAT = "MM/dd/yyyy hh:mm:ss aa";
    public static final String CALENDAR_DATE_FORMAT = "dd/MM/yyyy";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String DD_MM_YYYY = "dd-MM-yyyy";
    public static final String MM_DD_YYYY = "MM/dd/yyyy";
    public static final String IMAGE_DATE_FORMAT = "yyyyMMddHHmmssSSS";
    public static final String DISPLAY_DATE_FORMAT_1 = "dd/MM/yyyy";
    public static final String DISPLAY_DATE_FORMAT_2 = "EEEE, MMMM dd, yyyy hh:mm aa";
    public static final String DISPLAY_DATE_FORMAT_3 = "MMMM dd, yyyy hh:mm aa";
    // Patterns
    public static final Pattern PATTERN_EMAIL = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern PATTERN_GST = Pattern.compile("\\d{2}[A-Z]{5}\\d{4}[A-Z][A-Z\\d][Z][A-Z\\d]", Pattern.CASE_INSENSITIVE);
    //region Rating Start
    public static final float RATE_ZERO = 0;
    public static final float RATE_ONE = 1;
    public static final float RATE_TWO = 2;
    public static final float RATE_THREE = 3;
    public static final float RATE_FOUR = 4;
    public static final float RATE_FIVE = 5;
    public static String PAGE_DRS = "DRS";
    public static boolean IS_FROM_DESTINATION = false;
    public static boolean CHECKER_ONE = false;
    public static boolean CHECKER_TWO = false;
    public static String PICK_UP_REQUEST_ID = "PICK_UP_REQUEST_ID";
    public static String HYPER_LOCAL_REQUEST_ID = "HYPER_LOCAL_REQUEST_ID";
    public static String MODE = "MODE";
    //endregion Rating End
}
