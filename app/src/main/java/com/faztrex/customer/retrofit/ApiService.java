package com.faztrex.customer.retrofit;

import com.faztrex.customer.models.general.DashboardCount;
import com.faztrex.customer.models.hyperlocal.HyperLocalForm;
import com.faztrex.customer.models.hyperlocal.HyperLocalFreight;
import com.faztrex.customer.models.hyperlocal.HyperLocalList;
import com.faztrex.customer.models.pickuprequest.CouponCode;
import com.faztrex.customer.models.pickuprequest.EditPickUpRequestModel;
import com.faztrex.customer.models.pickuprequest.OtherCharges;
import com.faztrex.customer.models.pickuprequest.PickupRequestFormModel;
import com.faztrex.customer.network.model.request.AuthenticationRequestModel;
import com.faztrex.customer.network.model.request.CommanRequestModel;
import com.faztrex.customer.network.model.request.MPesaModel;
import com.faztrex.customer.network.model.request.MasterRequestModel;
import com.faztrex.customer.network.model.request.docket.DocketRequestModel;
import com.faztrex.customer.network.response.CommonListResponse;
import com.faztrex.customer.network.response.CommonResponse;
import com.faztrex.customer.network.response.docket.DocketDetail;
import com.faztrex.customer.network.response.docket.DocketListResponseModel;
import com.faztrex.customer.network.response.docket.tracking.DocketTracking;
import com.faztrex.customer.network.response.pickuprequest.PickupListResponseModel;
import com.faztrex.customer.network.response.user.ConsignorInformationModel;
import com.faztrex.customer.network.response.user.ForgotPassword;
import com.faztrex.customer.network.response.user.User;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {

    String CONTENT_TYPE_APPLICATION_JSON = "Content-Type: application/json";

    // region Login
    @Headers({CONTENT_TYPE_APPLICATION_JSON, ApiConstant.API_VERSION_1_0})
    @POST(ApiConstant.API_LOGIN)
    Call<CommonResponse<User>> authenticateUser(
            @Body AuthenticationRequestModel.LoginRequest requestBody
    );
    //endregion

    //region Forgot Password
    @Headers({CONTENT_TYPE_APPLICATION_JSON, ApiConstant.API_VERSION_1_0})
    @POST(ApiConstant.API_FORGOT_PASSWORD)
    Call<CommonResponse<ForgotPassword>> sendVerificationCode(
            @Body AuthenticationRequestModel.ForgotPasswordRequest requestBody
    );
    //endregion

    //region Reset Password
    @Headers({CONTENT_TYPE_APPLICATION_JSON, ApiConstant.API_VERSION_1_0})
    @POST(ApiConstant.API_RESET_PASSWORD)
    Call<CommonResponse<String>> resetPassword(
            @Body AuthenticationRequestModel.ResetPasswordRequest requestBody
    );
    //endregion

    //region Change Password
    @Headers({CONTENT_TYPE_APPLICATION_JSON, ApiConstant.API_VERSION_1_0})
    @POST(ApiConstant.API_CHANGE_PASSWORD)
    Call<CommonResponse<String>> changePassword(
            @Body AuthenticationRequestModel.ChangePasswordRequest requestBody
    );
    //endregion

    // region Dashboardcount
    @Headers({CONTENT_TYPE_APPLICATION_JSON, ApiConstant.API_VERSION_1_0})
    @POST(ApiConstant.API_DASHBOARD_COUNT_NEW)
    Call<CommonResponse<DashboardCount>> getDashboardCounts(
            @Body CommanRequestModel requestBody
    );

    //region M Pesa Payment Request
    @Headers({CONTENT_TYPE_APPLICATION_JSON, ApiConstant.API_VERSION_1_0})
    @POST(ApiConstant.API_MPESA_PAYMENT_REQUEST)
    Call<CommonResponse<MPesaModel.MPesaPaymentResponse>> sendMPesaPaymentRequest(@Body MPesaModel.MPesaPaymentRequest paymentRequest);

    @Headers({CONTENT_TYPE_APPLICATION_JSON, ApiConstant.API_VERSION_1_0})
    @POST(ApiConstant.API_MPESA_PAYMENT_REQUEST_STATUS)
    Call<CommonResponse<MPesaModel.MPesaPaymentResponse>> sendMPesaPaymentRequestStatus(@Body MPesaModel.MPesaPaymentResponse paymentResponse);
    //endregion

    //region Pickup List
    @Headers({CONTENT_TYPE_APPLICATION_JSON, ApiConstant.API_VERSION_1_0})
    @POST(ApiConstant.API_PICKUP_LIST)
    Call<CommonResponse<ArrayList<PickupListResponseModel>>> getPickUpList(
            @Body CommanRequestModel requestBody
    );
    //endregion
    //region Docket List
    @Headers({CONTENT_TYPE_APPLICATION_JSON, ApiConstant.API_VERSION_1_0})
    @POST(ApiConstant.API_DOCKET_LIST)
    Call<CommonResponse<ArrayList<DocketListResponseModel>>> getDocketList(
            @Body CommanRequestModel requestBody
    );
    //endregion

    //region Hyper Local
    @Headers({CONTENT_TYPE_APPLICATION_JSON, ApiConstant.API_VERSION_2_0})
    @POST(ApiConstant.API_HYPER_LOCAL_LIST)
    Call<CommonResponse<ArrayList<HyperLocalList>>> getHyperLocalList(
            @Body CommanRequestModel commanRequestModel
    );

    @Headers({CONTENT_TYPE_APPLICATION_JSON, ApiConstant.API_VERSION_1_0})
    @POST(ApiConstant.API_HYPER_LOCAL_MANAGE)
    Call<CommonResponse<String>> manageHyperLocal(
            @Body HyperLocalForm hyperLocalForm
    );

    @Headers({CONTENT_TYPE_APPLICATION_JSON, ApiConstant.API_VERSION_1_0})
    @POST(ApiConstant.API_HYPER_LOCAL_EDIT)
    Call<CommonResponse<HyperLocalForm>> getHyperLocalDetailBtyId(
            @Body CommanRequestModel commanRequestModel
    );


    @Headers({CONTENT_TYPE_APPLICATION_JSON, ApiConstant.API_VERSION_1_0})
    @POST(ApiConstant.API_HYPER_LOCAL_Freight)
    Call<CommonResponse<HyperLocalFreight>> getHyperLocalFreight(
            @Body CommanRequestModel commanRequestModel
    );


    //endregion


    //region Get State
    @Headers({CONTENT_TYPE_APPLICATION_JSON, ApiConstant.API_VERSION_1_0})
    @POST(ApiConstant.API_STATE)
    Call<CommonResponse<ArrayList<CommonListResponse>>> getState(
            @Body MasterRequestModel.GetStateRequest requestBody
    );
    //endregion

    //region Get City
    @Headers({CONTENT_TYPE_APPLICATION_JSON, ApiConstant.API_VERSION_1_0})
    @POST(ApiConstant.API_CITY)
    Call<CommonResponse<ArrayList<CommonListResponse>>> getCity(
            @Body MasterRequestModel.GetCityRequest requestBody
    );
    //endregion

    //region Consignor Information
    @Headers({CONTENT_TYPE_APPLICATION_JSON, ApiConstant.API_VERSION_1_0})
    @POST(ApiConstant.API_CONSIGNOR_INFORMATION)
    Call<CommonResponse<ConsignorInformationModel>> getConsignorInformation(
            @Body CommanRequestModel requestBody
    );
    //endregion

    //region Get Postcode
    @Headers({CONTENT_TYPE_APPLICATION_JSON, ApiConstant.API_VERSION_1_0})
    @POST(ApiConstant.API_POST_CODE)
    Call<CommonResponse<ArrayList<CommonListResponse>>> getPostcode(
            @Body MasterRequestModel.GetPostcodeRequest requestBody
    );
    //endregion

    //region Payment Type
    @Headers({CONTENT_TYPE_APPLICATION_JSON, ApiConstant.API_VERSION_2_0})
    @POST(ApiConstant.API_PAYMENT_TYPE)
    Call<CommonResponse<ArrayList<CommonListResponse>>> getPaymentType();

    @Headers({CONTENT_TYPE_APPLICATION_JSON, ApiConstant.API_VERSION_3_0})
    @POST(ApiConstant.API_PAYMENT_TYPE)
    Call<CommonResponse<ArrayList<CommonListResponse>>> getPaymentType3();

    @Headers({CONTENT_TYPE_APPLICATION_JSON, ApiConstant.API_VERSION_4_0})
    @POST(ApiConstant.API_PAYMENT_TYPE)
    Call<CommonResponse<ArrayList<CommonListResponse>>> getPaymentType4();
    //endregion

    //region Weight
    @Headers({CONTENT_TYPE_APPLICATION_JSON, ApiConstant.API_VERSION_1_0})
    @POST(ApiConstant.API_WEIGHT)
    Call<CommonResponse<ArrayList<CommonListResponse>>> getWeight();
    //endregion

    //region Address Type
    @Headers({CONTENT_TYPE_APPLICATION_JSON, ApiConstant.API_VERSION_1_0})
    @POST(ApiConstant.API_ADDRESS_TYPE)
    Call<CommonResponse<ArrayList<CommonListResponse>>> getAddressType();
    //endregion

    //region Dispatch Mode
    @Headers({CONTENT_TYPE_APPLICATION_JSON, ApiConstant.API_VERSION_1_0})
    @POST(ApiConstant.API_DISPATCH_MODE)
    Call<CommonResponse<ArrayList<CommonListResponse>>> getDispatchMode();
    //endregion

    //region Get Vertical
    @Headers({CONTENT_TYPE_APPLICATION_JSON, ApiConstant.API_VERSION_1_0})
    @POST(ApiConstant.API_VERTICLE)
    Call<CommonResponse<ArrayList<CommonListResponse>>> getVerticle();
    //endregion
    
    //region Get Packing Type
    @Headers({CONTENT_TYPE_APPLICATION_JSON, ApiConstant.API_VERSION_1_0})
    @POST(ApiConstant.API_PACKING_TYPE)
    Call<CommonResponse<ArrayList<CommonListResponse>>> getPackingType();
    //endregion

    //region Created By Jigar
    //region Get Docket Details By Docket ID API
    @Headers({CONTENT_TYPE_APPLICATION_JSON, ApiConstant.API_VERSION_1_0})
    @POST(ApiConstant.API_GET_DOCKET_DETAIL)
    Call<CommonResponse<DocketDetail>> getDocketById(
            @Body CommanRequestModel requestModel
    );
    //endregion

    //region Docket Tracking API
    @Headers({CONTENT_TYPE_APPLICATION_JSON, ApiConstant.API_VERSION_1_0})
    @POST(ApiConstant.API_DOCKET_TRACKING)
    Call<CommonResponse<ArrayList<DocketTracking>>> getDocketTrackingDetail(
            @Body DocketRequestModel.DocketTrackingRequest requestBody
    );
    //endregion
    //endregion

    //region Pickup request manage API
    @Headers({CONTENT_TYPE_APPLICATION_JSON, ApiConstant.API_VERSION_1_0})
    @POST(ApiConstant.API_PICKUP_REQUEST_MANAGE)
    Call<CommonResponse<String>> managePickupRequestForm(@Body PickupRequestFormModel pickupRequestFormModel);
    //endregion3

    //region Pickup request edit API
    @Headers({CONTENT_TYPE_APPLICATION_JSON, ApiConstant.API_VERSION_1_0})
    @POST(ApiConstant.API_PICKUP_REQUEST_EDIT)
    Call<CommonResponse<EditPickUpRequestModel>> editPickUpRequest(@Body CommanRequestModel commanRequestModel);
    //endregion

    // region Pickup request Other Charges API
    @Headers({CONTENT_TYPE_APPLICATION_JSON, ApiConstant.API_VERSION_1_0})
    @POST(ApiConstant.API_PICKUP_REQUEST_OTHER_CHARGES)
    Call<CommonResponse<OtherCharges>> getPickUpRequestOtherCharges(@Body JsonObject jsonObject);
    //endregion

    //region Docket Rating
    @Headers({CONTENT_TYPE_APPLICATION_JSON, ApiConstant.API_VERSION_1_0})
    @POST(ApiConstant.API_DOCKET_RATING)
    Call<CommonResponse<String>> docketRating(
            @Body CommanRequestModel commanRequestModel
    );//endregion

    @Headers({CONTENT_TYPE_APPLICATION_JSON, ApiConstant.API_VERSION_1_0})
    @POST(ApiConstant.API_PICKUP_COUPON)
    Call<CommonResponse<CouponCode>> getPickUpCouponCode(@Body CommanRequestModel commanRequestModel);

    @Headers({CONTENT_TYPE_APPLICATION_JSON, ApiConstant.API_VERSION_1_0})
    @POST(ApiConstant.API_PICKUP_OTP)
    Call<CommonResponse> generateOTP(@Body CommanRequestModel commanRequestModel);

    @Headers({CONTENT_TYPE_APPLICATION_JSON, ApiConstant.API_VERSION_1_0})
    @POST(ApiConstant.API_PICKUP_VERIFYOTP)
    Call<CommonResponse<Boolean>> verifyOTP(@Body CommanRequestModel commanRequestModel);


}