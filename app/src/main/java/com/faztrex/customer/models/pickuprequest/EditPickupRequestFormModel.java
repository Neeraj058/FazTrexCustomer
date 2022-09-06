package com.faztrex.customer.models.pickuprequest;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EditPickupRequestFormModel implements Parcelable {


    public static final Creator<EditPickupRequestFormModel> CREATOR = new Creator<EditPickupRequestFormModel>() {
        @Override
        public EditPickupRequestFormModel createFromParcel(Parcel in) {
            return new EditPickupRequestFormModel(in);
        }

        @Override
        public EditPickupRequestFormModel[] newArray(int size) {
            return new EditPickupRequestFormModel[size];
        }
    };
    @SerializedName(value = "autoNo", alternate = "AutoNo")
    @Expose
    private String autoNo;
    @SerializedName(value = "id", alternate = "Id")
    @Expose
    private String id;
    @SerializedName(value = "no", alternate = "No")
    @Expose
    private String no;
    @SerializedName(value = "preFix", alternate = "PreFix")
    @Expose
    private String preFix;
    @SerializedName(value = "postFix", alternate = "PostFix")
    @Expose
    private String postFix;
    @SerializedName(value = "dATE", alternate = "DATE")
    @Expose
    private String dATE;
    @SerializedName(value = "paymentTypeId", alternate = "PaymentTypeId")
    @Expose
    private String paymentTypeId;
    @SerializedName(value = "bookingBranchId", alternate = "BookingBranchId")
    @Expose
    private String bookingBranchId;
    @SerializedName(value = "bookingBranchName", alternate = "BookingBranchName")
    @Expose
    private String bookingBranchName;
    @SerializedName(value = "bookingStateId", alternate = "BookingStateId")
    @Expose
    private String bookingStateId;
    @SerializedName(value = "bookingStateName", alternate = "BookingStateName")
    @Expose
    private String bookingStateName;
    @SerializedName(value = "bookingCityId", alternate = "BookingCityId")
    @Expose
    private String bookingCityId;
    @SerializedName(value = "bookingCityName", alternate = "BookingCityName")
    @Expose
    private String bookingCityName;
    @SerializedName(value = "bookingPostCodeId", alternate = "BookingPostCodeId")
    @Expose
    private String bookingPostCodeId;
    @SerializedName(value = "bookingPostCode", alternate = "BookingPostCode")
    @Expose
    private String bookingPostCode;
    @SerializedName(value = "destinationStateId", alternate = "DestinationStateId")
    @Expose
    private String destinationStateId;
    @SerializedName(value = "destinationStateName", alternate = "DestinationStateName")
    @Expose
    private String destinationStateName;
    @SerializedName(value = "destinationCityId", alternate = "DestinationCityId")
    @Expose
    private String destinationCityId;
    @SerializedName(value = "destinationCityName", alternate = "DestinationCityName")
    @Expose
    private String destinationCityName;
    @SerializedName(value = "destinationPostCodeId", alternate = "DestinationPostCodeId")
    @Expose
    private String destinationPostCodeId;
    @SerializedName(value = "destinationPostCode", alternate = "DestinationPostCode")
    @Expose
    private String destinationPostCode;
    @SerializedName(value = "destinationPostCodeType", alternate = "DestinationPostCodeType")
    @Expose
    private String destinationPostCodeType;
    @SerializedName(value = "consignorId", alternate = "ConsignorId")
    @Expose
    private String consignorId;
    @SerializedName(value = "consignorCode", alternate = "ConsignorCode")
    @Expose
    private String consignorCode;
    @SerializedName(value = "consignorName", alternate = "ConsignorName")
    @Expose
    private String consignorName;
    @SerializedName(value = "consignorAddress", alternate = "ConsignorAddress")
    @Expose
    private String consignorAddress;
    @SerializedName(value = "consignorPostCode", alternate = "ConsignorPostCode")
    @Expose
    private String consignorPostCode;
    @SerializedName(value = "consignorMobileNo", alternate = "ConsignorMobileNo")
    @Expose
    private String consignorMobileNo;
    @SerializedName(value = "consignorGSTNo", alternate = "ConsignorGSTNo")
    @Expose
    private String consignorGSTNo;
    @SerializedName(value = "consigneeCode", alternate = "ConsigneeCode")
    @Expose
    private String consigneeCode;
    @SerializedName(value = "consigneeName", alternate = "ConsigneeName")
    @Expose
    private String consigneeName;
    @SerializedName(value = "consigneeAddress", alternate = "ConsigneeAddress")
    @Expose
    private String consigneeAddress;
    @SerializedName(value = "consigneePostCode", alternate = "ConsigneePostCode")
    @Expose
    private String consigneePostCode;
    @SerializedName(value = "consigneeMobileNo", alternate = "ConsigneeMobileNo")
    @Expose
    private String consigneeMobileNo;
    @SerializedName(value = "consigneeGSTNo", alternate = "ConsigneeGSTNo")
    @Expose
    private String consigneeGSTNo;
    @SerializedName(value = "verticleTypeId", alternate = "VerticleTypeId")
    @Expose
    private String verticleTypeId;
    @SerializedName(value = "verticleTypeName", alternate = "VerticleTypeName")
    @Expose
    private String verticleTypeName;
    @SerializedName(value = "productName", alternate = "ProductName")
    @Expose
    private String productName;
    @SerializedName(value = "packingTypeId", alternate = "PackingTypeId")
    @Expose
    private String packingTypeId;
    @SerializedName(value = "packingTypeName", alternate = "PackingTypeName")
    @Expose
    private String packingTypeName;
    @SerializedName(value = "noOfPackages", alternate = "NoOfPackages")
    @Expose
    private String noOfPackages;
    @SerializedName(value = "actualWeight", alternate = "ActualWeight")
    @Expose
    private String actualWeight;
    @SerializedName(value = "chargeWeight", alternate = "ChargeWeight")
    @Expose
    private String chargeWeight;
    @SerializedName(value = "volumetricWeight", alternate = "VolumetricWeight")
    @Expose
    private String volumetricWeight;
    @SerializedName(value = "chargeWeightPercentage", alternate = "ChargeWeightPercentage")
    @Expose
    private String chargeWeightPercentage;
    @SerializedName(value = "basicChargeAmount", alternate = "BasicChargeAmount")
    @Expose
    private String basicChargeAmount;
    @SerializedName(value = "netPayable", alternate = "NetPayable")
    @Expose
    private String netPayable;
    @SerializedName(value = "paymentModeId", alternate = "PaymentModeId")
    @Expose
    private String paymentModeId;
    @SerializedName(value = "couponCode", alternate = "CouponCode")
    @Expose
    private String couponCode;
    @SerializedName(value = "otp", alternate = "Otp")
    @Expose
    private String otp;
    @SerializedName(value = "docketCount", alternate = "DocketCount")
    @Expose
    private String docketCount;
    @SerializedName(value = "docketNo", alternate = "DocketNo")
    @Expose
    private String docketNo;

    @SerializedName(value = "pickUpRequestType", alternate = "PickUpRequestType")
    @Expose
    private String pickUpRequestType;

    public String getPickUpRequestType() {
        return pickUpRequestType;
    }

    public void setPickUpRequestType(String pickUpRequestType) {
        this.pickUpRequestType = pickUpRequestType;
    }
/*  @SerializedName(value = "sid", alternate = "Sid")
    @Expose
    private Integer sid;

    @SerializedName(value = "cid", alternate = "Cid")
    @Expose
    private Integer cid;

    @SerializedName(value = "bid", alternate = "Bid")
    @Expose
    private Integer bid;

    @SerializedName(value = "uid", alternate = "Uid")
    @Expose
    private Integer uid;

    @SerializedName(value = "isActive", alternate = "IsActive")
    @Expose
    private Integer isActive;

    @SerializedName(value = "isDelete", alternate = "IsDelete")
    @Expose
    private Integer isDelete;

    @SerializedName(value = "isDefault", alternate = "IsDefault")
    @Expose
    private Integer isDefault;

    @SerializedName(value = "isEnable", alternate = "IsEnable")
    @Expose
    private Integer isEnable;

    @SerializedName(value = "isSync", alternate = "IsSync")
    @Expose
    private Integer isSync;

    @SerializedName(value = "isFrom", alternate = "IsFrom")
    @Expose
    private Integer isFrom;*/



    protected EditPickupRequestFormModel(Parcel in) {
        autoNo = in.readString();
        id = in.readString();
        no = in.readString();
        preFix = in.readString();
        postFix = in.readString();
        dATE = in.readString();
        paymentTypeId = in.readString();
        bookingBranchId = in.readString();
        bookingBranchName = in.readString();
        bookingStateId = in.readString();
        bookingStateName = in.readString();
        bookingCityId = in.readString();
        bookingCityName = in.readString();
        bookingPostCodeId = in.readString();
        bookingPostCode = in.readString();
        destinationStateId = in.readString();
        destinationStateName = in.readString();
        destinationCityId = in.readString();
        destinationCityName = in.readString();
        destinationPostCodeId = in.readString();
        destinationPostCode = in.readString();
        destinationPostCodeType = in.readString();
        consignorId = in.readString();
        consignorCode = in.readString();
        consignorName = in.readString();
        consignorAddress = in.readString();
        consignorPostCode = in.readString();
        consignorMobileNo = in.readString();
        consignorGSTNo = in.readString();
        consigneeCode = in.readString();
        consigneeName = in.readString();
        consigneeAddress = in.readString();
        consigneePostCode = in.readString();
        consigneeMobileNo = in.readString();
        consigneeGSTNo = in.readString();
        verticleTypeId = in.readString();
        verticleTypeName = in.readString();
        productName = in.readString();
        packingTypeId = in.readString();
        packingTypeName = in.readString();
        noOfPackages = in.readString();
        actualWeight = in.readString();
        chargeWeight = in.readString();
        volumetricWeight = in.readString();
        chargeWeightPercentage = in.readString();
        basicChargeAmount = in.readString();
        netPayable = in.readString();
        paymentModeId = in.readString();
        couponCode = in.readString();
        otp = in.readString();
        docketCount = in.readString();
        docketNo = in.readString();
        pickUpRequestType = in.readString();
    }

    public String getAutoNo() {
        return autoNo;
    }

    public void setAutoNo(String autoNo) {
        this.autoNo = autoNo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getPreFix() {
        return preFix;
    }

    public void setPreFix(String preFix) {
        this.preFix = preFix;
    }

    public String getPostFix() {
        return postFix;
    }

    public void setPostFix(String postFix) {
        this.postFix = postFix;
    }

    public String getdATE() {
        return dATE;
    }

    public void setdATE(String dATE) {
        this.dATE = dATE;
    }

    public String getPaymentTypeId() {
        return paymentTypeId;
    }

    public void setPaymentTypeId(String paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    public String getBookingBranchId() {
        return bookingBranchId;
    }

    public void setBookingBranchId(String bookingBranchId) {
        this.bookingBranchId = bookingBranchId;
    }

    public String getBookingBranchName() {
        return bookingBranchName;
    }

    public void setBookingBranchName(String bookingBranchName) {
        this.bookingBranchName = bookingBranchName;
    }

    public String getBookingStateId() {
        return bookingStateId;
    }

    public void setBookingStateId(String bookingStateId) {
        this.bookingStateId = bookingStateId;
    }

    public String getBookingStateName() {
        return bookingStateName;
    }

    public void setBookingStateName(String bookingStateName) {
        this.bookingStateName = bookingStateName;
    }

    public String getBookingCityId() {
        return bookingCityId;
    }

    public void setBookingCityId(String bookingCityId) {
        this.bookingCityId = bookingCityId;
    }

    public String getBookingCityName() {
        return bookingCityName;
    }

    public void setBookingCityName(String bookingCityName) {
        this.bookingCityName = bookingCityName;
    }

    public String getBookingPostCodeId() {
        return bookingPostCodeId;
    }

    public void setBookingPostCodeId(String bookingPostCodeId) {
        this.bookingPostCodeId = bookingPostCodeId;
    }

    public String getBookingPostCode() {
        return bookingPostCode;
    }

    public void setBookingPostCode(String bookingPostCode) {
        this.bookingPostCode = bookingPostCode;
    }

    public String getDestinationStateId() {
        return destinationStateId;
    }

    public void setDestinationStateId(String destinationStateId) {
        this.destinationStateId = destinationStateId;
    }

    public String getDestinationStateName() {
        return destinationStateName;
    }

    public void setDestinationStateName(String destinationStateName) {
        this.destinationStateName = destinationStateName;
    }

    public String getDestinationCityId() {
        return destinationCityId;
    }

    public void setDestinationCityId(String destinationCityId) {
        this.destinationCityId = destinationCityId;
    }

    public String getDestinationCityName() {
        return destinationCityName;
    }

    public void setDestinationCityName(String destinationCityName) {
        this.destinationCityName = destinationCityName;
    }

    public String getDestinationPostCodeId() {
        return destinationPostCodeId;
    }

    public void setDestinationPostCodeId(String destinationPostCodeId) {
        this.destinationPostCodeId = destinationPostCodeId;
    }

    public String getDestinationPostCode() {
        return destinationPostCode;
    }

    public void setDestinationPostCode(String destinationPostCode) {
        this.destinationPostCode = destinationPostCode;
    }

    public String getDestinationPostCodeType() {
        return destinationPostCodeType;
    }

    public void setDestinationPostCodeType(String destinationPostCodeType) {
        this.destinationPostCodeType = destinationPostCodeType;
    }

    public String getConsignorId() {
        return consignorId;
    }

    public void setConsignorId(String consignorId) {
        this.consignorId = consignorId;
    }

    public String getConsignorCode() {
        return consignorCode;
    }

    public void setConsignorCode(String consignorCode) {
        this.consignorCode = consignorCode;
    }

    public String getConsignorName() {
        return consignorName;
    }

    public void setConsignorName(String consignorName) {
        this.consignorName = consignorName;
    }

    public String getConsignorAddress() {
        return consignorAddress;
    }

    public void setConsignorAddress(String consignorAddress) {
        this.consignorAddress = consignorAddress;
    }

    public String getConsignorPostCode() {
        return consignorPostCode;
    }

    public void setConsignorPostCode(String consignorPostCode) {
        this.consignorPostCode = consignorPostCode;
    }

    public String getConsignorMobileNo() {
        return consignorMobileNo;
    }

    public void setConsignorMobileNo(String consignorMobileNo) {
        this.consignorMobileNo = consignorMobileNo;
    }

    public String getConsignorGSTNo() {
        return consignorGSTNo;
    }

    public void setConsignorGSTNo(String consignorGSTNo) {
        this.consignorGSTNo = consignorGSTNo;
    }

    public String getConsigneeCode() {
        return consigneeCode;
    }

    public void setConsigneeCode(String consigneeCode) {
        this.consigneeCode = consigneeCode;
    }

    public String getConsigneeName() {
        return consigneeName;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
    }

    public String getConsigneeAddress() {
        return consigneeAddress;
    }

    public void setConsigneeAddress(String consigneeAddress) {
        this.consigneeAddress = consigneeAddress;
    }

    public String getConsigneePostCode() {
        return consigneePostCode;
    }

    public void setConsigneePostCode(String consigneePostCode) {
        this.consigneePostCode = consigneePostCode;
    }

    public String getConsigneeMobileNo() {
        return consigneeMobileNo;
    }

    public void setConsigneeMobileNo(String consigneeMobileNo) {
        this.consigneeMobileNo = consigneeMobileNo;
    }

    public String getConsigneeGSTNo() {
        return consigneeGSTNo;
    }

    public void setConsigneeGSTNo(String consigneeGSTNo) {
        this.consigneeGSTNo = consigneeGSTNo;
    }

    public String getVerticleTypeId() {
        return verticleTypeId;
    }

    public void setVerticleTypeId(String verticleTypeId) {
        this.verticleTypeId = verticleTypeId;
    }

    public String getVerticleTypeName() {
        return verticleTypeName;
    }

    public void setVerticleTypeName(String verticleTypeName) {
        this.verticleTypeName = verticleTypeName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPackingTypeId() {
        return packingTypeId;
    }

    public void setPackingTypeId(String packingTypeId) {
        this.packingTypeId = packingTypeId;
    }

    public String getPackingTypeName() {
        return packingTypeName;
    }

    public void setPackingTypeName(String packingTypeName) {
        this.packingTypeName = packingTypeName;
    }

    public String getNoOfPackages() {
        return noOfPackages;
    }

    public void setNoOfPackages(String noOfPackages) {
        this.noOfPackages = noOfPackages;
    }

    public String getActualWeight() {
        return actualWeight;
    }

    public void setActualWeight(String actualWeight) {
        this.actualWeight = actualWeight;
    }

    public String getChargeWeight() {
        return chargeWeight;
    }

    public void setChargeWeight(String chargeWeight) {
        this.chargeWeight = chargeWeight;
    }

    public String getVolumetricWeight() {
        return volumetricWeight;
    }

    public void setVolumetricWeight(String volumetricWeight) {
        this.volumetricWeight = volumetricWeight;
    }

    public String getChargeWeightPercentage() {
        return chargeWeightPercentage;
    }

    public void setChargeWeightPercentage(String chargeWeightPercentage) {
        this.chargeWeightPercentage = chargeWeightPercentage;
    }

    public String getBasicChargeAmount() {
        return basicChargeAmount;
    }

    public void setBasicChargeAmount(String basicChargeAmount) {
        this.basicChargeAmount = basicChargeAmount;
    }

    public String getNetPayable() {
        return netPayable;
    }

    public void setNetPayable(String netPayable) {
        this.netPayable = netPayable;
    }

    public String getPaymentModeId() {
        return paymentModeId;
    }

    public void setPaymentModeId(String paymentModeId) {
        this.paymentModeId = paymentModeId;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getDocketCount() {
        return docketCount;
    }

    public void setDocketCount(String docketCount) {
        this.docketCount = docketCount;
    }

    public String getDocketNo() {
        return docketNo;
    }

    public void setDocketNo(String docketNo) {
        this.docketNo = docketNo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(autoNo);
        dest.writeString(id);
        dest.writeString(no);
        dest.writeString(preFix);
        dest.writeString(postFix);
        dest.writeString(dATE);
        dest.writeString(paymentTypeId);
        dest.writeString(bookingBranchId);
        dest.writeString(bookingBranchName);
        dest.writeString(bookingStateId);
        dest.writeString(bookingStateName);
        dest.writeString(bookingCityId);
        dest.writeString(bookingCityName);
        dest.writeString(bookingPostCodeId);
        dest.writeString(bookingPostCode);
        dest.writeString(destinationStateId);
        dest.writeString(destinationStateName);
        dest.writeString(destinationCityId);
        dest.writeString(destinationCityName);
        dest.writeString(destinationPostCodeId);
        dest.writeString(destinationPostCode);
        dest.writeString(destinationPostCodeType);
        dest.writeString(consignorId);
        dest.writeString(consignorCode);
        dest.writeString(consignorName);
        dest.writeString(consignorAddress);
        dest.writeString(consignorPostCode);
        dest.writeString(consignorMobileNo);
        dest.writeString(consignorGSTNo);
        dest.writeString(consigneeCode);
        dest.writeString(consigneeName);
        dest.writeString(consigneeAddress);
        dest.writeString(consigneePostCode);
        dest.writeString(consigneeMobileNo);
        dest.writeString(consigneeGSTNo);
        dest.writeString(verticleTypeId);
        dest.writeString(verticleTypeName);
        dest.writeString(productName);
        dest.writeString(packingTypeId);
        dest.writeString(packingTypeName);
        dest.writeString(noOfPackages);
        dest.writeString(actualWeight);
        dest.writeString(chargeWeight);
        dest.writeString(volumetricWeight);
        dest.writeString(chargeWeightPercentage);
        dest.writeString(basicChargeAmount);
        dest.writeString(netPayable);
        dest.writeString(paymentModeId);
        dest.writeString(couponCode);
        dest.writeString(otp);
        dest.writeString(docketCount);
        dest.writeString(docketNo);
        dest.writeString(pickUpRequestType);
    }
}
