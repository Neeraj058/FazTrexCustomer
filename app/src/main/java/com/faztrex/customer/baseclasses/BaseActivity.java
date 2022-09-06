package com.faztrex.customer.baseclasses;

import static com.faztrex.customer.retrofit.ApiConstant.API_MPESA_PAYMENT_REQUEST;
import static com.faztrex.customer.retrofit.ApiConstant.API_MPESA_PAYMENT_REQUEST_STATUS;
import static com.faztrex.customer.utils.AppUtils.isEmptyString;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.widget.NestedScrollView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.cittasolutions.cittalibrary.utils.AppValidation;
import com.faztrex.customer.R;
import com.faztrex.customer.customviews.CustomProgressDialog;
import com.faztrex.customer.customviews.SimpleAlertDialog;
import com.faztrex.customer.databinding.ToolbarMainBinding;
import com.faztrex.customer.listeners.bottomsheet.MasterBottomSheetActionListener;
import com.faztrex.customer.models.pickuprequest.PickupRequestDetailModel;
import com.faztrex.customer.models.pickuprequest.PickupRequestFormModel;
import com.faztrex.customer.network.model.request.MPesaModel;
import com.faztrex.customer.network.model.request.MasterRequestModel;
import com.faztrex.customer.network.response.CommonListResponse;
import com.faztrex.customer.network.response.CommonResponse;
import com.faztrex.customer.network.response.user.User;
import com.faztrex.customer.retrofit.ApiClient;
import com.faztrex.customer.retrofit.ApiConstant;
import com.faztrex.customer.retrofit.ApiListener;
import com.faztrex.customer.retrofit.ApiManager;
import com.faztrex.customer.retrofit.ApiService;
import com.faztrex.customer.ui.fragments.bottomsheet.MasterBottomSheetFragment;
import com.faztrex.customer.utils.AppConstants;
import com.faztrex.customer.utils.AppPreference;
import com.faztrex.customer.utils.NetworkUtils;
import com.firebase.client.Firebase;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;

public abstract class BaseActivity extends AppCompatActivity implements ApiListener {

    public static Activity currentActivity;

    public static ArrayList<CommonListResponse> stateMasterList = new ArrayList<>();

    public static ArrayList<CommonListResponse> cityMasterList = new ArrayList<>();

    public static ArrayList<CommonListResponse> stateDestinationMasterList = new ArrayList<>();

    public static ArrayList<CommonListResponse> cityDestinationMasterList = new ArrayList<>();

    public static ArrayList<CommonListResponse> postcodeMasterList = new ArrayList<>();

    public static ArrayList<CommonListResponse> destinationPostcodeMasterList = new ArrayList<>();

    public static ArrayList<CommonListResponse> paymentType2MasterList = new ArrayList<>();

    public static ArrayList<CommonListResponse> paymentType3MasterList = new ArrayList<>();
    public static ArrayList<CommonListResponse> paymentType4MasterList = new ArrayList<>();
    public static ArrayList<CommonListResponse> paymentType5MasterList = new ArrayList<>();

    public static ArrayList<CommonListResponse> verticleMasterList = new ArrayList<>();

    public static ArrayList<CommonListResponse> packingTypeMasterList = new ArrayList<>();

    public static ArrayList<CommonListResponse> weightMasterList = new ArrayList<>();

    public static ArrayList<CommonListResponse> addressTypeMasterList = new ArrayList<>();

    public static ArrayList<CommonListResponse> dispatchModeMasterList = new ArrayList<>();

    private static CustomProgressDialog myProgressDialog = null;

    private final List<WeakReference<Fragment>> weakReferenceFragmentList = new ArrayList<>();
    private final String TAG = this.getClass().getSimpleName();
    private final Context mContext = BaseActivity.this;
    private final int parentId = 0;
    public FragmentManager fragmentManager;
    public SimpleAlertDialog simpleAlertDialog = null;
    public User prefUserModel;
    public boolean prefIsLogin;
    public AppValidation appValidation;
    public AppPreference appPreference;
    protected PickupRequestDetailModel pickupRequestDetailModel, pickupRequestDetailModelBottomSheet;
    protected PickupRequestFormModel pickupRequestFormModel;
    private List<Fragment> fragmentsList = new ArrayList<>();
    private FragmentTransaction fragmentTransaction;
    private MasterBottomSheetFragment bottomSheet;
    private String spinnerType;
    private String masterBottonSheetTitle;
    private String edtId;
    private MasterBottomSheetActionListener masterBottomSheetActionListener;

    public static void visibleView(View view) {
        if (view != null) {
            view.setVisibility(View.VISIBLE);
        }
    }

    public static void hideView(View view) {
        if (view != null) {
            view.setVisibility(View.GONE);
        }
    }

    public static void enableView(View view) {
        if (view != null) {
            view.setEnabled(true);
        }
    }

    public static void disableView(View view) {
        if (view != null) {
            view.setEnabled(false);
        }
    }

    public static void disableMultipleViews(ViewGroup view) {

        try {

            if (view != null) {

                for (int i = 0; i < view.getChildCount(); i++) {

                    View child = view.getChildAt(i);

                    if (child instanceof LinearLayout) {
                        disableMultipleViews((ViewGroup) child);
                    }

                    if (child instanceof LinearLayoutCompat) {
                        disableMultipleViews((ViewGroup) child);
                    }

                    if (child instanceof RelativeLayout) {
                        disableMultipleViews((ViewGroup) child);
                    }

                    if (child instanceof ScrollView) {
                        disableMultipleViews((ViewGroup) child);
                    }

                    if (child instanceof NestedScrollView) {
                        disableMultipleViews((ViewGroup) child);
                    }

                    if (child instanceof MaterialCardView) {
                        disableMultipleViews((ViewGroup) child);
                    }

                    if (child instanceof RadioGroup) {
                        disableMultipleViews((ViewGroup) child);
                    }

                    if (child instanceof MaterialButtonToggleGroup) {
                        disableMultipleViews((ViewGroup) child);
                    }

                    if (child instanceof TextInputLayout) {

                        Objects.requireNonNull(((TextInputLayout) child).getEditText()).setEnabled(false);
                        Objects.requireNonNull(((TextInputLayout) child).getEditText()).setFocusable(false);

                    } else if (child instanceof Button) {

                        child.setEnabled(false);

                    } else if (child instanceof Spinner) {

                        child.setEnabled(false);

                    } else if (child instanceof AppCompatImageView) {

                        child.setEnabled(false);

                    } else {

                        child.setEnabled(false);
                        child.setFocusable(false);

                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void startProgressDialog(Activity activity, boolean cancelable) {

        try {

            if (myProgressDialog == null)
                myProgressDialog = CustomProgressDialog.show(activity, cancelable);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void stopProgressDialog() {

        try {

            if (myProgressDialog != null && myProgressDialog.isShowing()) {
                myProgressDialog.dismiss();
                myProgressDialog = null;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void hideSoftKeyboard() {

        try {

            InputMethodManager imm = (InputMethodManager) currentActivity.getSystemService(Activity.INPUT_METHOD_SERVICE);

            //Find the currently focused view, so we can grab the correct window token from it.
            View view = currentActivity.getCurrentFocus();

            //If no view currently has focus, create a new one, just so we can grab a window token from it
            if (view == null) {
                view = new View(currentActivity);
            }

            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public abstract Activity setCurrentActivity();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentActivity = setCurrentActivity();

        // For FileURIExposedException Handling
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        // To Prevent NetworkOnMainThread Exception
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        // hide keyboardx
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        FirebaseApp.initializeApp(mContext);
        Firebase.setAndroidContext(mContext);
        FirebaseMessaging.getInstance().setAutoInitEnabled(true);

        appPreference = AppPreference.getInstance();

        appValidation = AppValidation.getInstance(mContext);
    }

    private void makeRequestForPayment(String mPesaId, ApiListener listener) {

        try {

            if (NetworkUtils.isConnected(mContext)) {

                startProgressDialog(this, false);

                MPesaModel.MPesaPaymentRequest request = new MPesaModel.MPesaPaymentRequest();
                //request.setToPayAmount(String.valueOf(toPayAmount));
                request.setToPayAmount(String.valueOf(10));
                request.setCustomerMPesaId(String.valueOf(mPesaId));

                String requestString = new Gson().toJson(request);

                ApiService apiService = ApiClient.createService(ApiService.class, "", "");

                Call<CommonResponse<MPesaModel.MPesaPaymentResponse>> call = apiService.sendMPesaPaymentRequest(request);

                ApiManager.callRetrofit(call, API_MPESA_PAYMENT_REQUEST, listener, false);

            } else {

                displayInternetToastMessage(mContext);

            }

        } catch (Exception e) {
            stopProgressDialog();
            e.printStackTrace();
        }
    }

    public void checkStatusOfPayment(String checkoutRequestId, ApiListener listener) {

        try {

            if (NetworkUtils.isConnected(mContext)) {

                startProgressDialog(this, false);

                MPesaModel.MPesaPaymentResponse request = new MPesaModel.MPesaPaymentResponse();
                request.setCheckoutRequestID(checkoutRequestId);

                String requestString = new Gson().toJson(request);

                ApiService apiService = ApiClient.createService(ApiService.class, "", "");

                Call<CommonResponse<MPesaModel.MPesaPaymentResponse>> call = apiService.sendMPesaPaymentRequestStatus(request);

                ApiManager.callRetrofit(call, API_MPESA_PAYMENT_REQUEST_STATUS, listener, false);

            } else displayInternetToastMessage(mContext);

        } catch (Exception e) {
            e.printStackTrace();
        }

        stopProgressDialog();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void displayShortToast(Context context, String displayMessage) {
        Toast.makeText(context, displayMessage, Toast.LENGTH_SHORT).show();
    }

    public void displayShortToast(String displayMessage) {
        Toast.makeText(this, displayMessage, Toast.LENGTH_SHORT).show();
    }

    public void displayLongToast(Context context, String displayMessage) {
        Toast.makeText(context, displayMessage, Toast.LENGTH_LONG).show();
    }

    public void displayLongSnackbar(View view, String displayMessage) {
        if (view != null)
            Snackbar.make(view, displayMessage, Snackbar.LENGTH_LONG).show();
    }

    public void displayInternetToastMessage(Context context) {
        Toast.makeText(context, getResources().getString(R.string.msg_no_internet), Toast.LENGTH_SHORT).show();
    }

    public void initToolbar(ToolbarMainBinding toolbarMainBinding, String headerTitle) {

        try {

            setSupportActionBar(toolbarMainBinding.toolbarMain);

            toolbarMainBinding.tvHeaderTitle.setText(headerTitle);

            toolbarMainBinding.ivBack.setOnClickListener(view -> onBackPressed());

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    public String getTrimString(TextInputLayout inputLayout) {
        return Objects.requireNonNull(inputLayout.getEditText()).getText().toString().trim();
    }

    public void printErrorLog(String tag, String msg) {
        Log.e(tag, msg);
    }

    public void printInfoLog(String tag, String msg) {
        Log.i(tag, msg);
    }

    public void printVerboseLog(String tag, String msg) {
        Log.v(tag, msg);
    }

    public void printDebugLog(String tag, String msg) {
        Log.d(tag, msg);
    }

    @SuppressLint("WrongConstant")
    public void setFragment(Fragment fragment, final String backStateName, int containerId) {

        try {

            boolean isCalled = false;
            fragmentsList = new ArrayList<>();
            fragmentManager = getSupportFragmentManager();
            fragmentsList = getActiveFragments();

            for (Fragment fragments : fragmentsList) {

                if (fragments.getClass().getName().equalsIgnoreCase(backStateName))
                    isCalled = true;
            }

            if (!isCalled) {

                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(containerId, fragment);
                fragmentTransaction.setTransition(R.style.WindowAnimationTransition);
                fragmentTransaction.addToBackStack(backStateName);
                weakReferenceFragmentList.add(new WeakReference(fragment));
                fragmentTransaction.commit();

            }

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    public void replaceFragments(Class fragmentClass, int containerId) {

        try {

            Fragment fragment = (Fragment) fragmentClass.newInstance();
            // Insert the fragment by replacing any existing fragment
            fragmentManager.beginTransaction().replace(containerId, fragment).commit();

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    private void clearBackStack() {

        fragmentManager = getSupportFragmentManager();

        if (fragmentManager.getBackStackEntryCount() > 0) {

            FragmentManager.BackStackEntry first = fragmentManager.getBackStackEntryAt(0);
            fragmentManager.popBackStack(first.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }

    public List<Fragment> getActiveFragments() {

        ArrayList<Fragment> returnFragments = new ArrayList<>();

        for (WeakReference<Fragment> weakReference : weakReferenceFragmentList) {

            Fragment weakFragments = weakReference.get();

            if (weakFragments != null) {
                if (weakFragments.isVisible())
                    returnFragments.add(weakFragments);
            }
        }

        return returnFragments;
    }

    public boolean checkAccessRights(String pageRights, int operationType) {

        try {

            if (!isEmptyString(pageRights)) {

                switch (operationType) {

                    case AppConstants.R_VIEW:
                        return pageRights.contains("V");

                    case AppConstants.R_EDIT:
                        return pageRights.contains("E");

                    case AppConstants.R_DELETE:
                        return pageRights.contains("D");

                    case AppConstants.R_ADD:
                        return pageRights.contains("A");

                    case AppConstants.R_PRINT:
                        return pageRights.contains("P");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    //region methods to check access rights
    public String getAccessRights(String pageName) {

        String appRights = "";

        try {

            /*appRights = prefUserModel.getGroupEmpWebRights();*/

            //rights = "leadevent-V-E-D-A,lead-V-E-D-A,leadsource-V-E-D-A";

            String[] appRightsList = appRights.split(",");

            for (String pageRights : appRightsList) {

                if (pageRights.contains(pageName)) {

                    if (pageRights.contains("-")) {

                        String actualPageName = pageRights.split("-")[0];

                        if (pageName.equals(actualPageName)) {

                            appRights = pageRights.substring(actualPageName.length() + 1);
                            return appRights;
                        }

                    } else {
                        appRights = "";
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }

        return appRights;
    }

    public void hideVisibilityWithAnimation(View view) {
        view.animate()
                .alpha(0f)
                .setDuration(200)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        view.setVisibility(View.GONE);
                    }
                });
    }

    public void getPreferenceData() {

        try {

            // get data from preference
            String userDataJson = AppPreference.getInstance().getStringPreference(mContext, mContext.getResources().getString(R.string.pref_user_data));
            User user = new Gson().fromJson(userDataJson, User.class);

            prefIsLogin = AppPreference.getInstance().getBooleanPreference(mContext, getResources().getString(R.string.pref_is_login));

            if (user != null)
                prefUserModel = user;

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    public void showVisibilityWithAnimation(View view) {
        view.setVisibility(View.VISIBLE);
        view.animate()
                .alpha(1f)
                .setDuration(200)
                .setListener(null);
    }

    @SuppressLint("RtlHardcoded")
    public void openDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(Gravity.RIGHT);
    }

    @SuppressLint("RtlHardcoded")
    public void closeDrawer(DrawerLayout drawerLayout) {
        drawerLayout.closeDrawer(Gravity.RIGHT);
       /* try {

            if (binding.drawerLayout.isDrawerOpen(binding.navigationDrawer)) {

                binding.drawerLayout.closeDrawer(Gravity.RIGHT);
                return false;

            }

        } catch (Exception e) {

            e.printStackTrace();

        }
        return true;*/
    }

    public void showFilterMasterBottomSheet(String masterBottonSheetTitle, MasterBottomSheetActionListener masterBottomSheetActionListener, String spinnerType) {

        this.masterBottonSheetTitle = masterBottonSheetTitle;
        this.edtId = spinnerType;
        this.masterBottomSheetActionListener = masterBottomSheetActionListener;

        switch (spinnerType) {

            case AppConstants.SP_STATE:
                if (stateMasterList != null && stateMasterList.size() > 0) {
                    bottomSheet = new MasterBottomSheetFragment(getApplicationContext(), stateMasterList, masterBottonSheetTitle, edtId, masterBottomSheetActionListener);
                    bottomSheet.show(getSupportFragmentManager(), bottomSheet.getTag());
                } else
                    Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content), "Data not available.", Snackbar.LENGTH_LONG).show();
                break;

            case AppConstants.SP_CITY:
                if (cityMasterList != null && cityMasterList.size() > 0) {
                    bottomSheet = new MasterBottomSheetFragment(getApplicationContext(), cityMasterList, masterBottonSheetTitle, edtId, masterBottomSheetActionListener);
                    bottomSheet.show(getSupportFragmentManager(), bottomSheet.getTag());
                } else
                    Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content), "Data not available.", Snackbar.LENGTH_LONG).show();
                break;

            case AppConstants.SP_STATE2:
                if (stateDestinationMasterList != null && stateDestinationMasterList.size() > 0) {
                    bottomSheet = new MasterBottomSheetFragment(getApplicationContext(), stateDestinationMasterList, masterBottonSheetTitle, edtId, masterBottomSheetActionListener);
                    bottomSheet.show(getSupportFragmentManager(), bottomSheet.getTag());
                } else
                    Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content), "Data not available.", Snackbar.LENGTH_LONG).show();
                break;

            case AppConstants.SP_CITY2:
                if (cityDestinationMasterList != null && cityDestinationMasterList.size() > 0) {
                    bottomSheet = new MasterBottomSheetFragment(getApplicationContext(), cityDestinationMasterList, masterBottonSheetTitle, edtId, masterBottomSheetActionListener);
                    bottomSheet.show(getSupportFragmentManager(), bottomSheet.getTag());
                } else
                    Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content), "Data not available.", Snackbar.LENGTH_LONG).show();
                break;

            case AppConstants.SP_POSTCODE:
                if (postcodeMasterList != null && postcodeMasterList.size() > 0) {
                    bottomSheet = new MasterBottomSheetFragment(getApplicationContext(), postcodeMasterList, masterBottonSheetTitle, edtId, masterBottomSheetActionListener);
                    bottomSheet.show(getSupportFragmentManager(), bottomSheet.getTag());
                } else
                    Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content), "Data not available.", Snackbar.LENGTH_LONG).show();
                break;

            case AppConstants.SP_POSTCODE2:
                if (destinationPostcodeMasterList != null && destinationPostcodeMasterList.size() > 0) {
                    bottomSheet = new MasterBottomSheetFragment(getApplicationContext(), destinationPostcodeMasterList, masterBottonSheetTitle, edtId, masterBottomSheetActionListener);
                    bottomSheet.show(getSupportFragmentManager(), bottomSheet.getTag());
                } else
                    Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content), "Data not available.", Snackbar.LENGTH_LONG).show();
                break;

            case AppConstants.SP_PAYMENT_TYPE2:
                if (paymentType2MasterList != null && paymentType2MasterList.size() > 0) {
                    bottomSheet = new MasterBottomSheetFragment(getApplicationContext(), paymentType2MasterList, masterBottonSheetTitle, edtId, masterBottomSheetActionListener);
                    bottomSheet.show(getSupportFragmentManager(), bottomSheet.getTag());
                } else
                    Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content), "Data not available.", Snackbar.LENGTH_LONG).show();
                break;

            case AppConstants.SP_PAYMENT_TYPE3:
                if (paymentType3MasterList != null && paymentType3MasterList.size() > 0) {
                    List<CommonListResponse> test = new ArrayList<>();
                    //if(paymentType3MasterList.contains("To Be Billed"))

                    test.add(paymentType3MasterList.get(2));

                    bottomSheet = new MasterBottomSheetFragment(getApplicationContext(), (ArrayList<?>) test, masterBottonSheetTitle, edtId, masterBottomSheetActionListener);
                    bottomSheet.show(getSupportFragmentManager(), bottomSheet.getTag());
                } else
                    Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content), "Data not available.", Snackbar.LENGTH_LONG).show();
                break;
            case AppConstants.SP_PAYMENT_TYPE5:
                if (paymentType5MasterList != null && paymentType5MasterList.size() > 0) {
                    bottomSheet = new MasterBottomSheetFragment(getApplicationContext(), (ArrayList<?>) paymentType5MasterList, masterBottonSheetTitle, edtId, masterBottomSheetActionListener);
                    bottomSheet.show(getSupportFragmentManager(), bottomSheet.getTag());
                } else Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content), "Data not available", Snackbar.LENGTH_LONG).show();
                break;
            case AppConstants.SP_PAYMENT_TYPE4:
                if (paymentType4MasterList != null && paymentType4MasterList.size() > 0) {
                    bottomSheet = new MasterBottomSheetFragment(getApplicationContext(), paymentType4MasterList, masterBottonSheetTitle, edtId, masterBottomSheetActionListener);
                    bottomSheet.show(getSupportFragmentManager(), bottomSheet.getTag());
                } else
                    Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content), "Data not available.", Snackbar.LENGTH_LONG).show();
                break;

            case AppConstants.SP_WEIGHT:
                if (weightMasterList != null && weightMasterList.size() > 0) {
                    bottomSheet = new MasterBottomSheetFragment(getApplicationContext(), weightMasterList, masterBottonSheetTitle, edtId, masterBottomSheetActionListener);
                    bottomSheet.show(getSupportFragmentManager(), bottomSheet.getTag());
                } else
                    Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content), "Data not available.", Snackbar.LENGTH_LONG).show();
                break;

            case AppConstants.SP_ADDRESS_TYPE:
                if (addressTypeMasterList != null && addressTypeMasterList.size() > 0) {
                    bottomSheet = new MasterBottomSheetFragment(getApplicationContext(), addressTypeMasterList, masterBottonSheetTitle, edtId, masterBottomSheetActionListener);
                    bottomSheet.show(getSupportFragmentManager(), bottomSheet.getTag());
                } else
                    Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content), "Data not available.", Snackbar.LENGTH_LONG).show();
                break;

            case AppConstants.SP_VERTICAL:
                if (verticleMasterList != null && verticleMasterList.size() > 0) {
                    bottomSheet = new MasterBottomSheetFragment(getApplicationContext(), verticleMasterList, masterBottonSheetTitle, edtId, masterBottomSheetActionListener);
                    bottomSheet.show(getSupportFragmentManager(), bottomSheet.getTag());
                } else
                    Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content), "Data not available.", Snackbar.LENGTH_LONG).show();
                break;

            case AppConstants.SP_PACKING_TYPE:
                if (packingTypeMasterList != null && packingTypeMasterList.size() > 0) {
                    bottomSheet = new MasterBottomSheetFragment(getApplicationContext(), packingTypeMasterList, masterBottonSheetTitle, edtId, masterBottomSheetActionListener);
                    bottomSheet.show(getSupportFragmentManager(), bottomSheet.getTag());
                } else
                    Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content), "Data not available.", Snackbar.LENGTH_LONG).show();
                break;

            case AppConstants.SP_DISPATCH_MODE:
                if (dispatchModeMasterList != null && dispatchModeMasterList.size() > 0) {
                    bottomSheet = new MasterBottomSheetFragment(getApplicationContext(), dispatchModeMasterList, masterBottonSheetTitle, edtId, masterBottomSheetActionListener);
                    bottomSheet.show(getSupportFragmentManager(), bottomSheet.getTag());
                } else
                    Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content), "Data not available.", Snackbar.LENGTH_LONG).show();
                break;
        }
    }

    public void getSpinnerList(String spinnerType, Map<String, Integer> inputParams) {

        try {

            if (NetworkUtils.isConnected(mContext)) {

                this.spinnerType = spinnerType;

                // call method to get preference data
                getPreferenceData();

                // start progress indicator
                startProgressDialog(this, false);

                Call<CommonResponse<ArrayList<CommonListResponse>>> call;

                ApiService apiService = ApiClient.createService(ApiService.class, "", "");

                switch (spinnerType) {

                    case AppConstants.SP_STATE:
                    case AppConstants.SP_STATE2:
                        MasterRequestModel.GetStateRequest stateRequest = new MasterRequestModel().new GetStateRequest();
                        stateRequest.setCountryId(AppConstants.COUNTRY_ID);
                        call = apiService.getState(stateRequest);
                        ApiManager.callRetrofit(call, spinnerType, this, false);
                        break;

                    case AppConstants.SP_CITY:
                    case AppConstants.SP_CITY2:
                        MasterRequestModel.GetCityRequest cityRequest = new MasterRequestModel().new GetCityRequest();
                        cityRequest.setStateId(String.valueOf(inputParams.get(AppConstants.KEY_STATE_ID)));
                        call = apiService.getCity(cityRequest);
                        ApiManager.callRetrofit(call, spinnerType, this, false);
                        break;

                    case AppConstants.SP_POSTCODE:
                    case AppConstants.SP_POSTCODE2:

                        MasterRequestModel.GetPostcodeRequest postcodeRequest = new MasterRequestModel().new GetPostcodeRequest();
                        postcodeRequest.setCityId(String.valueOf(inputParams.get(AppConstants.KEY_CITY_ID)));
                        call = apiService.getPostcode(postcodeRequest);
                        ApiManager.callRetrofit(call, spinnerType, this, false);
                        break;

                    case AppConstants.SP_PAYMENT_TYPE2:
                        call = apiService.getPaymentType();
                        ApiManager.callRetrofit(call, spinnerType, this, false);
                        break;

                    case AppConstants.SP_PAYMENT_TYPE3:
                        call = apiService.getPaymentType3();
                        ApiManager.callRetrofit(call, spinnerType, this, false);
                        break;

                    case AppConstants.SP_PAYMENT_TYPE5:
                        call = apiService.getPaymentType3();
                        ApiManager.callRetrofit(call, spinnerType, this, false);
                        break;

                    case AppConstants.SP_PAYMENT_TYPE4:
                        call = apiService.getPaymentType4();
                        ApiManager.callRetrofit(call, spinnerType, this, false);
                        break;

                    case ApiConstant.API_VERTICLE:
                        call = apiService.getVerticle();
                        ApiManager.callRetrofit(call, ApiConstant.API_VERTICLE, this, false);
                        break;

                    case ApiConstant.API_PACKING_TYPE:
                        call = apiService.getPackingType();
                        ApiManager.callRetrofit(call, ApiConstant.API_PACKING_TYPE, this, false);
                        break;

                    case ApiConstant.API_WEIGHT:
                        call = apiService.getWeight();
                        ApiManager.callRetrofit(call, ApiConstant.API_WEIGHT, this, false);
                        break;

                    case ApiConstant.API_ADDRESS_TYPE:
                        call = apiService.getAddressType();
                        ApiManager.callRetrofit(call, ApiConstant.API_ADDRESS_TYPE, this, false);
                        break;

                    case AppConstants.SP_DISPATCH_MODE:
                        call = apiService.getDispatchMode();
                        ApiManager.callRetrofit(call, AppConstants.SP_DISPATCH_MODE, this, false);
                        break;

                }
            } else {
                displayInternetToastMessage(mContext);
            }
        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    @Override
    public void onApiSuccess(String endPointName, Object responseBody) {

        CommonResponse<ArrayList<CommonListResponse>> commonResponse;

        switch (endPointName) {

            case AppConstants.SP_STATE:
            case AppConstants.SP_STATE2:

            case AppConstants.SP_CITY:
            case AppConstants.SP_CITY2:

            case AppConstants.SP_POSTCODE:
            case AppConstants.SP_POSTCODE2:
            case AppConstants.SP_PAYMENT_TYPE2:
            case AppConstants.SP_PAYMENT_TYPE3:
            case AppConstants.SP_PAYMENT_TYPE5:
            case AppConstants.SP_PAYMENT_TYPE4:

            case AppConstants.SP_DISPATCH_MODE:

            case ApiConstant.API_VERTICLE:

            case ApiConstant.API_PACKING_TYPE:
            case ApiConstant.API_WEIGHT:
            case ApiConstant.API_ADDRESS_TYPE:

                commonResponse = (CommonResponse<ArrayList<CommonListResponse>>) responseBody;
                processResponse(endPointName, commonResponse);
                break;

            case API_MPESA_PAYMENT_REQUEST:
                CommonResponse<MPesaModel.MPesaPaymentResponse> response = (CommonResponse<MPesaModel.MPesaPaymentResponse>) responseBody;
                processMpesaRequest();
                break;

            case API_MPESA_PAYMENT_REQUEST_STATUS:
                CommonResponse<MPesaModel.MPesaPaymentResponse> responseStatus = (CommonResponse<MPesaModel.MPesaPaymentResponse>) responseBody;
                processPaymentRequestResponse(responseStatus);
                break;
        }
    }

    private void processPaymentRequestResponse(CommonResponse<MPesaModel.MPesaPaymentResponse> response) {
        printDebugLog("PaymentRequest", "Here in processPaymentRequestResponse");
    }

    public void processMpesaRequest() {
        printDebugLog("MpesaRequest", "Here in processMpesaRequest");
    }

    private void processResponse(String endPointName, CommonResponse<ArrayList<CommonListResponse>> commonResponse) {
        String s = new Gson().toJson(commonResponse);

        try {

            switch (endPointName) {

                case AppConstants.SP_STATE:
                    stateMasterList = new ArrayList<>();
                    stateMasterList = commonResponse.getData();
                    break;

                case AppConstants.SP_STATE2:
                    stateDestinationMasterList = new ArrayList<>();
                    stateDestinationMasterList = commonResponse.getData();
                    break;

                case AppConstants.SP_CITY:
                    cityMasterList = new ArrayList<>();
                    cityMasterList = commonResponse.getData();
                    break;

                case AppConstants.SP_CITY2:
                    cityDestinationMasterList = new ArrayList<>();
                    cityDestinationMasterList = commonResponse.getData();
                    break;

                case AppConstants.SP_POSTCODE:
                    postcodeMasterList = new ArrayList<>();
                    postcodeMasterList = commonResponse.getData();
                    break;

                case AppConstants.SP_POSTCODE2:
                    destinationPostcodeMasterList = new ArrayList<>();
                    destinationPostcodeMasterList = commonResponse.getData();
                    break;

                case AppConstants.SP_PAYMENT_TYPE2:
                    paymentType2MasterList = new ArrayList<>();
                    paymentType2MasterList = commonResponse.getData();
                    break;

                case AppConstants.SP_PAYMENT_TYPE3:
                    paymentType3MasterList = new ArrayList<>();
                    paymentType3MasterList = commonResponse.getData();
                    break;

                case AppConstants.SP_PAYMENT_TYPE5:
                    paymentType5MasterList = new ArrayList<>();
                    paymentType5MasterList = commonResponse.getData();
                    break;
                case AppConstants.SP_PAYMENT_TYPE4:
                    paymentType4MasterList = new ArrayList<>();
                    paymentType4MasterList = commonResponse.getData();
                    break;

                case ApiConstant.API_VERTICLE:
                    verticleMasterList = new ArrayList<>();
                    verticleMasterList = commonResponse.getData();
                    break;

                case ApiConstant.API_PACKING_TYPE:
                    packingTypeMasterList = new ArrayList<>();
                    packingTypeMasterList = commonResponse.getData();
                    break;

                case ApiConstant.API_WEIGHT:
                    weightMasterList = new ArrayList<>();
                    weightMasterList = commonResponse.getData();
                    break;

                case ApiConstant.API_ADDRESS_TYPE:
                    addressTypeMasterList = new ArrayList<>();
                    addressTypeMasterList = commonResponse.getData();
                    break;

                case AppConstants.SP_DISPATCH_MODE:
                    dispatchModeMasterList = new ArrayList<>();
                    dispatchModeMasterList = commonResponse.getData();
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    @Override
    public void onApiFailure(String endPointName, String failureMessage) {

        printErrorLog(TAG, "Something went wrong in : " + endPointName + " " + failureMessage);

        switch (endPointName) {

            case AppConstants.SP_STATE:
                stateMasterList = new ArrayList<>();
                break;

            case AppConstants.SP_STATE2:
                stateDestinationMasterList = new ArrayList<>();
                break;

            case AppConstants.SP_CITY:
                cityMasterList = new ArrayList<>();
                break;

            case AppConstants.SP_CITY2:
                cityDestinationMasterList = new ArrayList<>();
                break;

            case AppConstants.SP_POSTCODE:
                postcodeMasterList = new ArrayList<>();
                break;

            case AppConstants.SP_POSTCODE2:
                destinationPostcodeMasterList = new ArrayList<>();
                break;

            case AppConstants.SP_PAYMENT_TYPE2:
                paymentType2MasterList = new ArrayList<>();
                break;

            case AppConstants.SP_PAYMENT_TYPE3:
                paymentType3MasterList = new ArrayList<>();
                break;
            case AppConstants.SP_PAYMENT_TYPE5:
                paymentType5MasterList = new ArrayList<>();
                break;
            case AppConstants.SP_PAYMENT_TYPE4:
                paymentType4MasterList = new ArrayList<>();
                break;

            case ApiConstant.API_VERTICLE:
                verticleMasterList = new ArrayList<>();
                break;

            case ApiConstant.API_PACKING_TYPE:
                packingTypeMasterList = new ArrayList<>();
                break;

            case ApiConstant.API_WEIGHT:
                weightMasterList = new ArrayList<>();
                break;

            case ApiConstant.API_ADDRESS_TYPE:
                addressTypeMasterList = new ArrayList<>();
                break;

            case AppConstants.SP_DISPATCH_MODE:
                dispatchModeMasterList = new ArrayList<>();
                break;
        }
    }

    @Override
    public void onApiError(String endPointName, String errorMessage) {

        printErrorLog(TAG, "Something went wrong in : " + endPointName + " " + errorMessage);

        switch (endPointName) {

            case AppConstants.SP_STATE:
                stateMasterList = new ArrayList<>();
                break;

            case AppConstants.SP_STATE2:
                stateDestinationMasterList = new ArrayList<>();
                break;

            case AppConstants.SP_CITY:
                cityMasterList = new ArrayList<>();
                break;

            case AppConstants.SP_CITY2:
                cityDestinationMasterList = new ArrayList<>();
                break;

            case AppConstants.SP_POSTCODE:
                postcodeMasterList = new ArrayList<>();
                break;

            case AppConstants.SP_POSTCODE2:
                destinationPostcodeMasterList = new ArrayList<>();
                break;

            case AppConstants.SP_PAYMENT_TYPE2:
                paymentType2MasterList = new ArrayList<>();
                break;

            case AppConstants.SP_PAYMENT_TYPE3:
                paymentType3MasterList = new ArrayList<>();
                break;

            case AppConstants.SP_PAYMENT_TYPE5:
                paymentType5MasterList = new ArrayList<>();
                break;
            case AppConstants.SP_PAYMENT_TYPE4:
                paymentType4MasterList = new ArrayList<>();
                break;

            case ApiConstant.API_VERTICLE:
                verticleMasterList = new ArrayList<>();
                break;

            case ApiConstant.API_PACKING_TYPE:
                packingTypeMasterList = new ArrayList<>();
                break;

            case ApiConstant.API_WEIGHT:
                weightMasterList = new ArrayList<>();
                break;

            case ApiConstant.API_ADDRESS_TYPE:
                addressTypeMasterList = new ArrayList<>();
                break;

            case AppConstants.SP_DISPATCH_MODE:
                dispatchModeMasterList = new ArrayList<>();
                break;
        }
    }
}


