package com.faztrex.customer.models.pickuprequest;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PickupRequestFormModel implements Parcelable {

    @SerializedName(value = "Id", alternate = "id")
    @Expose
    private Integer id;

    @SerializedName(value = "Prefix", alternate = "prefix")
    @Expose
    private String prefix;

    @SerializedName(value = "Suffix", alternate = "suffix")
    @Expose
    private String suffix;

    @SerializedName(value = "AutoNo", alternate = "autoNo")
    @Expose
    private Integer autoNo;

    @SerializedName(value = "No", alternate = "no")
    @Expose
    private String no;

    @SerializedName(value = "Date", alternate = "date")
    @Expose
    private String date;

    @SerializedName(value = "PaymentType", alternate = "paymentType")
    @Expose
    private Integer paymentType;

    @SerializedName(value = "BookingBranchId", alternate = "bookingBranchId")
    @Expose
    private Integer bookingBranchId;

    @SerializedName(value = "BookingStateId", alternate = "bookingStateId")
    @Expose
    private Integer bookingStateId;

    @SerializedName(value = "BookingCityId", alternate = "bookingCityId")
    @Expose
    private Integer bookingCityId;

    @SerializedName(value = "BookingPostCode", alternate = "bookingPostCode")
    @Expose
    private String bookingPostCode;

    @SerializedName(value = "BookingPostCodeId", alternate = "bookingPostCodeId")
    @Expose
    private Integer bookingPostCodeId;

    @SerializedName(value = "DestinationStateId", alternate = "destinationStateId")
    @Expose
    private Integer destinationStateId;

    @SerializedName(value = "DestinationCityId", alternate = "destinationCityId")
    @Expose
    private Integer destinationCityId;

    @SerializedName(value = "DestinationPostCode", alternate = "destinationPostCode")
    @Expose
    private String destinationPostCode;

    @SerializedName(value = "DestinationPostCodeId", alternate = "destinationPostCodeId")
    @Expose
    private Integer destinationPostCodeId;

    @SerializedName(value = "DestinationPostCodeType", alternate = "destinationPostCodeType")
    @Expose
    private String destinationPostCodeType;

    @SerializedName(value = "ConsignorId", alternate = "consignorId")
    @Expose
    private Integer consignorId;

    @SerializedName(value = "ConsignorCode", alternate = "consignorCode")
    @Expose
    private String consignorCode;

    @SerializedName(value = "ConsignorName", alternate = "consignorName")
    @Expose
    private String consignorName;

    @SerializedName(value = "ConsignorAddress", alternate = "consignorAddress")
    @Expose
    private String consignorAddress;

    @SerializedName(value = "ConsignorPostCode", alternate = "consignorPostCode")
    @Expose
    private String consignorPostCode;

    @SerializedName(value = "ConsignorMobileNo", alternate = "consignorMobileNo")
    @Expose
    private String consignorMobileNo;

    @SerializedName(value = "ConsignorGSTNo", alternate = "consignorGSTNo")
    @Expose
    private String consignorGSTNo;

    @SerializedName(value = "ConsigneeCode", alternate = "consigneeCode")
    @Expose
    private String consigneeCode;

    @SerializedName(value = "ConsigneeName", alternate = "consigneeName")
    @Expose
    private String consigneeName;

    @SerializedName(value = "ConsigneeAddress", alternate = "consigneeAddress")
    @Expose
    private String consigneeAddress;

    @SerializedName(value = "ConsigneePostCode", alternate = "consigneePostCode")
    @Expose
    private String consigneePostCode;

    @SerializedName(value = "ConsigneeMobileNo", alternate = "consigneeMobileNo")
    @Expose
    private String consigneeMobileNo;

    @SerializedName(value = "ConsigneeGSTNo", alternate = "consigneeGSTNo")
    @Expose
    private String consigneeGSTNo;

    @SerializedName(value = "DeliveryType", alternate = "deliveryType")
    @Expose
    private Integer deliveryType;

    @SerializedName(value = "VerticleTypeId", alternate = "verticleTypeId")
    @Expose
    private Integer verticleTypeId;

    @SerializedName(value = "ProductName", alternate = "productName")
    @Expose
    private String productName;

    @SerializedName(value = "PackingTypeId", alternate = "packingTypeId")
    @Expose
    private Integer packingTypeId;

    @SerializedName(value = "NoOfPackages", alternate = "noOfPackages")
    @Expose
    private Integer noOfPackages;

    @SerializedName(value = "ActualWeight", alternate = "actualWeight")
    @Expose
    private Double actualWeight;

    @SerializedName(value = "ChargeWeight", alternate = "chargeWeight")
    @Expose
    private Double chargeWeight;

    @SerializedName(value = "ChargeWeightPercentage", alternate = "chargeWeightPercentage")
    @Expose
    private Double chargeWeightPercentage;

    @SerializedName(value = "BasicChargeAmount", alternate = "basicChargeAmount")
    @Expose
    private Double basicChargeAmount;

    @SerializedName(value = "ValueSurchargeAmount", alternate = "valueSurchargeAmount")
    @Expose
    private Double valueSurchargeAmount;

    @SerializedName(value = "GreenChargeAmount", alternate = "greenChargeAmount")
    @Expose
    private Double greenChargeAmount;

    @SerializedName(value = "DeliveryChargeAmount", alternate = "deliveryChargeAmount")
    @Expose
    private Double deliveryChargeAmount;

    @SerializedName(value = "ODAChargesAmount", alternate = "oDAChargesAmount")
    @Expose
    private Double oDAChargesAmount;

    @SerializedName(value = "ToPayChargesAmount", alternate = "toPayChargesAmount")
    @Expose
    private Double toPayChargesAmount;

    @SerializedName(value = "PkgHandlingChargeAmount", alternate = "pkgHandlingChargeAmount")
    @Expose
    private Double pkgHandlingChargeAmount;

    @SerializedName(value = "DocketChargeAmount", alternate = "docketChargeAmount")
    @Expose
    private Double docketChargeAmount;

    @SerializedName(value = "HardCopyChargeAmount", alternate = "hardCopyChargeAmount")
    @Expose
    private Double hardCopyChargeAmount;

    @SerializedName(value = "SubTotalAmount", alternate = "subTotalAmount")
    @Expose
    private Double subTotalAmount;

    @SerializedName(value = "SurchargeAmount", alternate = "surchargeAmount")
    @Expose
    private Double surchargeAmount;

    @SerializedName(value = "TotalFreightAmount", alternate = "totalFreightAmount")
    @Expose
    private Double totalFreightAmount;

    @SerializedName(value = "SGSTPercentage", alternate = "sGSTPercentage")
    @Expose
    private Double sGSTPercentage;

    @SerializedName(value = "SGSTAmount", alternate = "sGSTAmount")
    @Expose
    private Double sGSTAmount;

    @SerializedName(value = "CGSTPercentage", alternate = "cGSTPercentage")
    @Expose
    private Double cGSTPercentage;

    @SerializedName(value = "CGSTAmount", alternate = "cGSTAmount")
    @Expose
    private Double cGSTAmount;

    @SerializedName(value = "IGSTPercentage", alternate = "iGSTPercentage")
    @Expose
    private Double iGSTPercentage;

    @SerializedName(value = "IGSTAmount", alternate = "iGSTAmount")
    @Expose
    private Double iGSTAmount;

    @SerializedName(value = "TotalGSTAmount", alternate = "totalGSTAmount")
    @Expose
    private Double totalGSTAmount;

    @SerializedName(value = "NetAmount", alternate = "netAmount")
    @Expose
    private Double netAmount;

    @SerializedName(value = "FMId", alternate = "fMId")
    @Expose
    private Integer fMId;

    @SerializedName(value = "HMId1", alternate = "hMId1")
    @Expose
    private Integer hMId1;

    @SerializedName(value = "HMId2", alternate = "hMId2")
    @Expose
    private Integer hMId2;

    @SerializedName(value = "HMId3", alternate = "hMId3")
    @Expose
    private Integer hMId3;

    @SerializedName(value = "HMId4", alternate = "hMId4")
    @Expose
    private Integer hMId4;

    @SerializedName(value = "HMId5", alternate = "hMId5")
    @Expose
    private Integer hMId5;

    @SerializedName(value = "DRSId", alternate = "dRSId")
    @Expose
    private Integer dRSId;

    @SerializedName(value = "BillId", alternate = "billId")
    @Expose
    private Integer billId;

    @SerializedName(value = "IsDocketCreated", alternate = "isDocketCreated")
    @Expose
    private Integer isDocketCreated;

    @SerializedName(value = "Sid", alternate = "sid")
    @Expose
    private Integer sid;

    @SerializedName(value = "Cid", alternate = "cid")
    @Expose
    private Integer cid;

    @SerializedName(value = "Bid", alternate = "bid")
    @Expose
    private Integer bid;

    @SerializedName(value = "Uid", alternate = "uid")
    @Expose
    private Integer uid;

    @SerializedName(value = "IsActive", alternate = "isActive")
    @Expose
    private Integer isActive;

    @SerializedName(value = "IsDelete", alternate = "isDelete")
    @Expose
    private Integer isDelete;

    @SerializedName(value = "EntryDate", alternate = "entryDate")
    @Expose
    private String entryDate;

    @SerializedName(value = "LastModifyDate", alternate = "lastModifyDate")
    @Expose
    private String lastModifyDate;

    @SerializedName(value = "LastModifyBy", alternate = "lastModifyBy")
    @Expose
    private Integer lastModifyBy;

    @SerializedName(value = "IsDefault", alternate = "isDefault")
    @Expose
    private Integer isDefault;

    @SerializedName(value = "IsEnable", alternate = "isEnable")
    @Expose
    private Integer isEnable;

    @SerializedName(value = "Status", alternate = "status")
    @Expose
    private Integer status;

    @SerializedName(value = "IsSync", alternate = "isSync")
    @Expose
    private Integer isSync;

    @SerializedName(value = "IsFrom", alternate = "isFrom")
    @Expose
    private Integer isFrom;

    @SerializedName(value = "WildSearch", alternate = "wildSearch")
    @Expose
    private String wildSearch;

    @SerializedName(value = "Notes", alternate = "notes")
    @Expose
    private String notes;

    @SerializedName(value = "ActualDeliveryDate", alternate = "actualDeliveryDate")
    @Expose
    private String actualDeliveryDate;

    @SerializedName(value = "FinalDeliveryDate", alternate = "finalDeliveryDate")
    @Expose
    private String finalDeliveryDate;

    @SerializedName(value = "VolumetricWeight", alternate = "volumetricWeight")
    @Expose
    private Double volumetricWeight;

    @SerializedName(value = "ZoneId", alternate = "zoneId")
    @Expose
    private Integer zoneId;

    @SerializedName(value = "DefaultRate", alternate = "defaultRate")
    @Expose
    private Double defaultRate;

    @SerializedName(value = "AppliedRate", alternate = "appliedRate")
    @Expose
    private Double appliedRate;

    @SerializedName(value = "PickupChallanId", alternate = "pickupChallanId")
    @Expose
    private Integer pickupChallanId;

    @SerializedName(value = "CouponCode", alternate = "couponCode")
    @Expose
    private String couponCode;

    @SerializedName(value = "PaymentModeId", alternate = "paymentModeId")
    @Expose
    private Integer paymentModeId;

    @SerializedName(value = "NetPayable", alternate = "netPayable")
    @Expose
    private Double NetPayable;

    protected PickupRequestFormModel(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        prefix = in.readString();
        suffix = in.readString();
        if (in.readByte() == 0) {
            autoNo = null;
        } else {
            autoNo = in.readInt();
        }
        no = in.readString();
        date = in.readString();
        if (in.readByte() == 0) {
            paymentType = null;
        } else {
            paymentType = in.readInt();
        }
        if (in.readByte() == 0) {
            bookingBranchId = null;
        } else {
            bookingBranchId = in.readInt();
        }
        if (in.readByte() == 0) {
            bookingStateId = null;
        } else {
            bookingStateId = in.readInt();
        }
        if (in.readByte() == 0) {
            bookingCityId = null;
        } else {
            bookingCityId = in.readInt();
        }
        bookingPostCode = in.readString();
        if (in.readByte() == 0) {
            bookingPostCodeId = null;
        } else {
            bookingPostCodeId = in.readInt();
        }
        if (in.readByte() == 0) {
            destinationStateId = null;
        } else {
            destinationStateId = in.readInt();
        }
        if (in.readByte() == 0) {
            destinationCityId = null;
        } else {
            destinationCityId = in.readInt();
        }
        destinationPostCode = in.readString();
        if (in.readByte() == 0) {
            destinationPostCodeId = null;
        } else {
            destinationPostCodeId = in.readInt();
        }
        destinationPostCodeType = in.readString();
        if (in.readByte() == 0) {
            consignorId = null;
        } else {
            consignorId = in.readInt();
        }
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
        if (in.readByte() == 0) {
            deliveryType = null;
        } else {
            deliveryType = in.readInt();
        }
        if (in.readByte() == 0) {
            verticleTypeId = null;
        } else {
            verticleTypeId = in.readInt();
        }
        productName = in.readString();
        if (in.readByte() == 0) {
            packingTypeId = null;
        } else {
            packingTypeId = in.readInt();
        }
        if (in.readByte() == 0) {
            noOfPackages = null;
        } else {
            noOfPackages = in.readInt();
        }
        if (in.readByte() == 0) {
            actualWeight = null;
        } else {
            actualWeight = in.readDouble();
        }
        if (in.readByte() == 0) {
            chargeWeight = null;
        } else {
            chargeWeight = in.readDouble();
        }
        if (in.readByte() == 0) {
            chargeWeightPercentage = null;
        } else {
            chargeWeightPercentage = in.readDouble();
        }
        if (in.readByte() == 0) {
            basicChargeAmount = null;
        } else {
            basicChargeAmount = in.readDouble();
        }
        if (in.readByte() == 0) {
            valueSurchargeAmount = null;
        } else {
            valueSurchargeAmount = in.readDouble();
        }
        if (in.readByte() == 0) {
            greenChargeAmount = null;
        } else {
            greenChargeAmount = in.readDouble();
        }
        if (in.readByte() == 0) {
            deliveryChargeAmount = null;
        } else {
            deliveryChargeAmount = in.readDouble();
        }
        if (in.readByte() == 0) {
            oDAChargesAmount = null;
        } else {
            oDAChargesAmount = in.readDouble();
        }
        if (in.readByte() == 0) {
            toPayChargesAmount = null;
        } else {
            toPayChargesAmount = in.readDouble();
        }
        if (in.readByte() == 0) {
            pkgHandlingChargeAmount = null;
        } else {
            pkgHandlingChargeAmount = in.readDouble();
        }
        if (in.readByte() == 0) {
            docketChargeAmount = null;
        } else {
            docketChargeAmount = in.readDouble();
        }
        if (in.readByte() == 0) {
            hardCopyChargeAmount = null;
        } else {
            hardCopyChargeAmount = in.readDouble();
        }
        if (in.readByte() == 0) {
            subTotalAmount = null;
        } else {
            subTotalAmount = in.readDouble();
        }
        if (in.readByte() == 0) {
            surchargeAmount = null;
        } else {
            surchargeAmount = in.readDouble();
        }
        if (in.readByte() == 0) {
            totalFreightAmount = null;
        } else {
            totalFreightAmount = in.readDouble();
        }
        if (in.readByte() == 0) {
            sGSTPercentage = null;
        } else {
            sGSTPercentage = in.readDouble();
        }
        if (in.readByte() == 0) {
            sGSTAmount = null;
        } else {
            sGSTAmount = in.readDouble();
        }
        if (in.readByte() == 0) {
            cGSTPercentage = null;
        } else {
            cGSTPercentage = in.readDouble();
        }
        if (in.readByte() == 0) {
            cGSTAmount = null;
        } else {
            cGSTAmount = in.readDouble();
        }
        if (in.readByte() == 0) {
            iGSTPercentage = null;
        } else {
            iGSTPercentage = in.readDouble();
        }
        if (in.readByte() == 0) {
            iGSTAmount = null;
        } else {
            iGSTAmount = in.readDouble();
        }
        if (in.readByte() == 0) {
            totalGSTAmount = null;
        } else {
            totalGSTAmount = in.readDouble();
        }
        if (in.readByte() == 0) {
            netAmount = null;
        } else {
            netAmount = in.readDouble();
        }
        if (in.readByte() == 0) {
            fMId = null;
        } else {
            fMId = in.readInt();
        }
        if (in.readByte() == 0) {
            hMId1 = null;
        } else {
            hMId1 = in.readInt();
        }
        if (in.readByte() == 0) {
            hMId2 = null;
        } else {
            hMId2 = in.readInt();
        }
        if (in.readByte() == 0) {
            hMId3 = null;
        } else {
            hMId3 = in.readInt();
        }
        if (in.readByte() == 0) {
            hMId4 = null;
        } else {
            hMId4 = in.readInt();
        }
        if (in.readByte() == 0) {
            hMId5 = null;
        } else {
            hMId5 = in.readInt();
        }
        if (in.readByte() == 0) {
            dRSId = null;
        } else {
            dRSId = in.readInt();
        }
        if (in.readByte() == 0) {
            billId = null;
        } else {
            billId = in.readInt();
        }
        if (in.readByte() == 0) {
            isDocketCreated = null;
        } else {
            isDocketCreated = in.readInt();
        }
        if (in.readByte() == 0) {
            sid = null;
        } else {
            sid = in.readInt();
        }
        if (in.readByte() == 0) {
            cid = null;
        } else {
            cid = in.readInt();
        }
        if (in.readByte() == 0) {
            bid = null;
        } else {
            bid = in.readInt();
        }
        if (in.readByte() == 0) {
            uid = null;
        } else {
            uid = in.readInt();
        }
        if (in.readByte() == 0) {
            isActive = null;
        } else {
            isActive = in.readInt();
        }
        if (in.readByte() == 0) {
            isDelete = null;
        } else {
            isDelete = in.readInt();
        }
        entryDate = in.readString();
        lastModifyDate = in.readString();
        if (in.readByte() == 0) {
            lastModifyBy = null;
        } else {
            lastModifyBy = in.readInt();
        }
        if (in.readByte() == 0) {
            isDefault = null;
        } else {
            isDefault = in.readInt();
        }
        if (in.readByte() == 0) {
            isEnable = null;
        } else {
            isEnable = in.readInt();
        }
        if (in.readByte() == 0) {
            status = null;
        } else {
            status = in.readInt();
        }
        if (in.readByte() == 0) {
            isSync = null;
        } else {
            isSync = in.readInt();
        }
        if (in.readByte() == 0) {
            isFrom = null;
        } else {
            isFrom = in.readInt();
        }
        wildSearch = in.readString();
        notes = in.readString();
        actualDeliveryDate = in.readString();
        finalDeliveryDate = in.readString();
        if (in.readByte() == 0) {
            volumetricWeight = null;
        } else {
            volumetricWeight = in.readDouble();
        }
        if (in.readByte() == 0) {
            zoneId = null;
        } else {
            zoneId = in.readInt();
        }
        if (in.readByte() == 0) {
            defaultRate = null;
        } else {
            defaultRate = in.readDouble();
        }
        if (in.readByte() == 0) {
            appliedRate = null;
        } else {
            appliedRate = in.readDouble();
        }
        if (in.readByte() == 0) {
            pickupChallanId = null;
        } else {
            pickupChallanId = in.readInt();
        }
        couponCode = in.readString();
        if (in.readByte() == 0) {
            paymentModeId = null;
        } else {
            paymentModeId = in.readInt();
        }
        if (in.readByte() == 0) {
            NetPayable = null;
        } else {
            NetPayable = in.readDouble();
        }
        if (in.readByte() == 0) {
            Discount = null;
        } else {
            Discount = in.readDouble();
        }
        otp = in.readString();
        pickUpRequestType = in.readString();
        dimensions = in.createTypedArrayList(PickupRequestDetailModel.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(prefix);
        dest.writeString(suffix);
        if (autoNo == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(autoNo);
        }
        dest.writeString(no);
        dest.writeString(date);
        if (paymentType == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(paymentType);
        }
        if (bookingBranchId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(bookingBranchId);
        }
        if (bookingStateId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(bookingStateId);
        }
        if (bookingCityId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(bookingCityId);
        }
        dest.writeString(bookingPostCode);
        if (bookingPostCodeId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(bookingPostCodeId);
        }
        if (destinationStateId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(destinationStateId);
        }
        if (destinationCityId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(destinationCityId);
        }
        dest.writeString(destinationPostCode);
        if (destinationPostCodeId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(destinationPostCodeId);
        }
        dest.writeString(destinationPostCodeType);
        if (consignorId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(consignorId);
        }
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
        if (deliveryType == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(deliveryType);
        }
        if (verticleTypeId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(verticleTypeId);
        }
        dest.writeString(productName);
        if (packingTypeId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(packingTypeId);
        }
        if (noOfPackages == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(noOfPackages);
        }
        if (actualWeight == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(actualWeight);
        }
        if (chargeWeight == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(chargeWeight);
        }
        if (chargeWeightPercentage == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(chargeWeightPercentage);
        }
        if (basicChargeAmount == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(basicChargeAmount);
        }
        if (valueSurchargeAmount == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(valueSurchargeAmount);
        }
        if (greenChargeAmount == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(greenChargeAmount);
        }
        if (deliveryChargeAmount == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(deliveryChargeAmount);
        }
        if (oDAChargesAmount == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(oDAChargesAmount);
        }
        if (toPayChargesAmount == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(toPayChargesAmount);
        }
        if (pkgHandlingChargeAmount == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(pkgHandlingChargeAmount);
        }
        if (docketChargeAmount == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(docketChargeAmount);
        }
        if (hardCopyChargeAmount == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(hardCopyChargeAmount);
        }
        if (subTotalAmount == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(subTotalAmount);
        }
        if (surchargeAmount == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(surchargeAmount);
        }
        if (totalFreightAmount == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(totalFreightAmount);
        }
        if (sGSTPercentage == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(sGSTPercentage);
        }
        if (sGSTAmount == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(sGSTAmount);
        }
        if (cGSTPercentage == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(cGSTPercentage);
        }
        if (cGSTAmount == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(cGSTAmount);
        }
        if (iGSTPercentage == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(iGSTPercentage);
        }
        if (iGSTAmount == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(iGSTAmount);
        }
        if (totalGSTAmount == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(totalGSTAmount);
        }
        if (netAmount == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(netAmount);
        }
        if (fMId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(fMId);
        }
        if (hMId1 == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(hMId1);
        }
        if (hMId2 == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(hMId2);
        }
        if (hMId3 == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(hMId3);
        }
        if (hMId4 == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(hMId4);
        }
        if (hMId5 == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(hMId5);
        }
        if (dRSId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(dRSId);
        }
        if (billId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(billId);
        }
        if (isDocketCreated == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(isDocketCreated);
        }
        if (sid == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(sid);
        }
        if (cid == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(cid);
        }
        if (bid == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(bid);
        }
        if (uid == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(uid);
        }
        if (isActive == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(isActive);
        }
        if (isDelete == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(isDelete);
        }
        dest.writeString(entryDate);
        dest.writeString(lastModifyDate);
        if (lastModifyBy == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(lastModifyBy);
        }
        if (isDefault == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(isDefault);
        }
        if (isEnable == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(isEnable);
        }
        if (status == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(status);
        }
        if (isSync == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(isSync);
        }
        if (isFrom == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(isFrom);
        }
        dest.writeString(wildSearch);
        dest.writeString(notes);
        dest.writeString(actualDeliveryDate);
        dest.writeString(finalDeliveryDate);
        if (volumetricWeight == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(volumetricWeight);
        }
        if (zoneId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(zoneId);
        }
        if (defaultRate == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(defaultRate);
        }
        if (appliedRate == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(appliedRate);
        }
        if (pickupChallanId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(pickupChallanId);
        }
        dest.writeString(couponCode);
        if (paymentModeId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(paymentModeId);
        }
        if (NetPayable == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(NetPayable);
        }
        if (Discount == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(Discount);
        }
        dest.writeString(otp);
        dest.writeTypedList(dimensions);
        dest.writeString(pickUpRequestType);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PickupRequestFormModel> CREATOR = new Creator<PickupRequestFormModel>() {
        @Override
        public PickupRequestFormModel createFromParcel(Parcel in) {
            return new PickupRequestFormModel(in);
        }

        @Override
        public PickupRequestFormModel[] newArray(int size) {
            return new PickupRequestFormModel[size];
        }
    };

    public Double getDiscount() {
        return Discount;
    }

    public void setDiscount(Double discount) {
        Discount = discount;
    }

    @SerializedName(value = "Discount", alternate = "discount")
    @Expose
    private Double Discount;

    @SerializedName(value = "Otp", alternate = "otp")
    @Expose
    private String otp;

    public String getPickUpRequestType() {
        return pickUpRequestType;
    }

    public void setPickUpRequestType(String pickUpRequestType) {
        this.pickUpRequestType = pickUpRequestType;
    }

    @SerializedName(value = "pickUpRequestType", alternate = "PickUpRequestType")
    @Expose
    private String pickUpRequestType;

    @SerializedName(value = "Dimensions", alternate = "dimensions")
    @Expose
    private ArrayList<PickupRequestDetailModel> dimensions;

    public PickupRequestFormModel() {
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public Integer getAutoNo() {
        return autoNo;
    }

    public void setAutoNo(Integer autoNo) {
        this.autoNo = autoNo;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    public Integer getBookingBranchId() {
        return bookingBranchId;
    }

    public void setBookingBranchId(Integer bookingBranchId) {
        this.bookingBranchId = bookingBranchId;
    }

    public Integer getBookingStateId() {
        return bookingStateId;
    }

    public void setBookingStateId(Integer bookingStateId) {
        this.bookingStateId = bookingStateId;
    }

    public Integer getBookingCityId() {
        return bookingCityId;
    }

    public void setBookingCityId(Integer bookingCityId) {
        this.bookingCityId = bookingCityId;
    }

    public String getBookingPostCode() {
        return bookingPostCode;
    }

    public void setBookingPostCode(String bookingPostCode) {
        this.bookingPostCode = bookingPostCode;
    }

    public Integer getBookingPostCodeId() {
        return bookingPostCodeId;
    }

    public void setBookingPostCodeId(Integer bookingPostCodeId) {
        this.bookingPostCodeId = bookingPostCodeId;
    }

    public Integer getDestinationStateId() {
        return destinationStateId;
    }

    public void setDestinationStateId(Integer destinationStateId) {
        this.destinationStateId = destinationStateId;
    }

    public Integer getDestinationCityId() {
        return destinationCityId;
    }

    public void setDestinationCityId(Integer destinationCityId) {
        this.destinationCityId = destinationCityId;
    }

    public String getDestinationPostCode() {
        return destinationPostCode;
    }

    public void setDestinationPostCode(String destinationPostCode) {
        this.destinationPostCode = destinationPostCode;
    }

    public Integer getDestinationPostCodeId() {
        return destinationPostCodeId;
    }

    public void setDestinationPostCodeId(Integer destinationPostCodeId) {
        this.destinationPostCodeId = destinationPostCodeId;
    }

    public String getDestinationPostCodeType() {
        return destinationPostCodeType;
    }

    public void setDestinationPostCodeType(String destinationPostCodeType) {
        this.destinationPostCodeType = destinationPostCodeType;
    }

    public Integer getConsignorId() {
        return consignorId;
    }

    public void setConsignorId(Integer consignorId) {
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

    public Integer getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(Integer deliveryType) {
        this.deliveryType = deliveryType;
    }

    public Integer getVerticleTypeId() {
        return verticleTypeId;
    }

    public void setVerticleTypeId(Integer verticleTypeId) {
        this.verticleTypeId = verticleTypeId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getPackingTypeId() {
        return packingTypeId;
    }

    public void setPackingTypeId(Integer packingTypeId) {
        this.packingTypeId = packingTypeId;
    }

    public Integer getNoOfPackages() {
        return noOfPackages;
    }

    public void setNoOfPackages(Integer noOfPackages) {
        this.noOfPackages = noOfPackages;
    }

    public Double getActualWeight() {
        return actualWeight;
    }

    public void setActualWeight(Double actualWeight) {
        this.actualWeight = actualWeight;
    }

    public Double getChargeWeight() {
        return chargeWeight;
    }

    public void setChargeWeight(Double chargeWeight) {
        this.chargeWeight = chargeWeight;
    }

    public Double getChargeWeightPercentage() {
        return chargeWeightPercentage;
    }

    public void setChargeWeightPercentage(Double chargeWeightPercentage) {
        this.chargeWeightPercentage = chargeWeightPercentage;
    }

    public Double getBasicChargeAmount() {
        return basicChargeAmount;
    }

    public void setBasicChargeAmount(Double basicChargeAmount) {
        this.basicChargeAmount = basicChargeAmount;
    }

    public Double getValueSurchargeAmount() {
        return valueSurchargeAmount;
    }

    public void setValueSurchargeAmount(Double valueSurchargeAmount) {
        this.valueSurchargeAmount = valueSurchargeAmount;
    }

    public Double getGreenChargeAmount() {
        return greenChargeAmount;
    }

    public void setGreenChargeAmount(Double greenChargeAmount) {
        this.greenChargeAmount = greenChargeAmount;
    }

    public Double getDeliveryChargeAmount() {
        return deliveryChargeAmount;
    }

    public void setDeliveryChargeAmount(Double deliveryChargeAmount) {
        this.deliveryChargeAmount = deliveryChargeAmount;
    }

    public Double getoDAChargesAmount() {
        return oDAChargesAmount;
    }

    public void setoDAChargesAmount(Double oDAChargesAmount) {
        this.oDAChargesAmount = oDAChargesAmount;
    }

    public Double getToPayChargesAmount() {
        return toPayChargesAmount;
    }

    public void setToPayChargesAmount(Double toPayChargesAmount) {
        this.toPayChargesAmount = toPayChargesAmount;
    }

    public Double getPkgHandlingChargeAmount() {
        return pkgHandlingChargeAmount;
    }

    public void setPkgHandlingChargeAmount(Double pkgHandlingChargeAmount) {
        this.pkgHandlingChargeAmount = pkgHandlingChargeAmount;
    }

    public Double getDocketChargeAmount() {
        return docketChargeAmount;
    }

    public void setDocketChargeAmount(Double docketChargeAmount) {
        this.docketChargeAmount = docketChargeAmount;
    }

    public Double getHardCopyChargeAmount() {
        return hardCopyChargeAmount;
    }

    public void setHardCopyChargeAmount(Double hardCopyChargeAmount) {
        this.hardCopyChargeAmount = hardCopyChargeAmount;
    }

    public Double getSubTotalAmount() {
        return subTotalAmount;
    }

    public void setSubTotalAmount(Double subTotalAmount) {
        this.subTotalAmount = subTotalAmount;
    }

    public Double getSurchargeAmount() {
        return surchargeAmount;
    }

    public void setSurchargeAmount(Double surchargeAmount) {
        this.surchargeAmount = surchargeAmount;
    }

    public Double getTotalFreightAmount() {
        return totalFreightAmount;
    }

    public void setTotalFreightAmount(Double totalFreightAmount) {
        this.totalFreightAmount = totalFreightAmount;
    }

    public Double getsGSTPercentage() {
        return sGSTPercentage;
    }

    public void setsGSTPercentage(Double sGSTPercentage) {
        this.sGSTPercentage = sGSTPercentage;
    }

    public Double getsGSTAmount() {
        return sGSTAmount;
    }

    public void setsGSTAmount(Double sGSTAmount) {
        this.sGSTAmount = sGSTAmount;
    }

    public Double getcGSTPercentage() {
        return cGSTPercentage;
    }

    public void setcGSTPercentage(Double cGSTPercentage) {
        this.cGSTPercentage = cGSTPercentage;
    }

    public Double getcGSTAmount() {
        return cGSTAmount;
    }

    public void setcGSTAmount(Double cGSTAmount) {
        this.cGSTAmount = cGSTAmount;
    }

    public Double getiGSTPercentage() {
        return iGSTPercentage;
    }

    public void setiGSTPercentage(Double iGSTPercentage) {
        this.iGSTPercentage = iGSTPercentage;
    }

    public Double getiGSTAmount() {
        return iGSTAmount;
    }

    public void setiGSTAmount(Double iGSTAmount) {
        this.iGSTAmount = iGSTAmount;
    }

    public Double getTotalGSTAmount() {
        return totalGSTAmount;
    }

    public void setTotalGSTAmount(Double totalGSTAmount) {
        this.totalGSTAmount = totalGSTAmount;
    }

    public Double getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(Double netAmount) {
        this.netAmount = netAmount;
    }

    public Integer getfMId() {
        return fMId;
    }

    public void setfMId(Integer fMId) {
        this.fMId = fMId;
    }

    public Integer gethMId1() {
        return hMId1;
    }

    public void sethMId1(Integer hMId1) {
        this.hMId1 = hMId1;
    }

    public Integer gethMId2() {
        return hMId2;
    }

    public void sethMId2(Integer hMId2) {
        this.hMId2 = hMId2;
    }

    public Integer gethMId3() {
        return hMId3;
    }

    public void sethMId3(Integer hMId3) {
        this.hMId3 = hMId3;
    }

    public Integer gethMId4() {
        return hMId4;
    }

    public void sethMId4(Integer hMId4) {
        this.hMId4 = hMId4;
    }

    public Integer gethMId5() {
        return hMId5;
    }

    public void sethMId5(Integer hMId5) {
        this.hMId5 = hMId5;
    }

    public Integer getdRSId() {
        return dRSId;
    }

    public void setdRSId(Integer dRSId) {
        this.dRSId = dRSId;
    }

    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    public Integer getIsDocketCreated() {
        return isDocketCreated;
    }

    public void setIsDocketCreated(Integer isDocketCreated) {
        this.isDocketCreated = isDocketCreated;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public String getLastModifyDate() {
        return lastModifyDate;
    }

    public void setLastModifyDate(String lastModifyDate) {
        this.lastModifyDate = lastModifyDate;
    }

    public Integer getLastModifyBy() {
        return lastModifyBy;
    }

    public void setLastModifyBy(Integer lastModifyBy) {
        this.lastModifyBy = lastModifyBy;
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

    public Integer getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Integer isEnable) {
        this.isEnable = isEnable;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsSync() {
        return isSync;
    }

    public void setIsSync(Integer isSync) {
        this.isSync = isSync;
    }

    public Integer getIsFrom() {
        return isFrom;
    }

    public void setIsFrom(Integer isFrom) {
        this.isFrom = isFrom;
    }

    public String getWildSearch() {
        return wildSearch;
    }

    public void setWildSearch(String wildSearch) {
        this.wildSearch = wildSearch;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getActualDeliveryDate() {
        return actualDeliveryDate;
    }

    public void setActualDeliveryDate(String actualDeliveryDate) {
        this.actualDeliveryDate = actualDeliveryDate;
    }

    public String getFinalDeliveryDate() {
        return finalDeliveryDate;
    }

    public void setFinalDeliveryDate(String finalDeliveryDate) {
        this.finalDeliveryDate = finalDeliveryDate;
    }

    public Double getVolumetricWeight() {
        return volumetricWeight;
    }

    public void setVolumetricWeight(Double volumetricWeight) {
        this.volumetricWeight = volumetricWeight;
    }

    public Integer getZoneId() {
        return zoneId;
    }

    public void setZoneId(Integer zoneId) {
        this.zoneId = zoneId;
    }

    public Double getDefaultRate() {
        return defaultRate;
    }

    public void setDefaultRate(Double defaultRate) {
        this.defaultRate = defaultRate;
    }

    public Double getAppliedRate() {
        return appliedRate;
    }

    public void setAppliedRate(Double appliedRate) {
        this.appliedRate = appliedRate;
    }

    public Integer getPickupChallanId() {
        return pickupChallanId;
    }

    public void setPickupChallanId(Integer pickupChallanId) {
        this.pickupChallanId = pickupChallanId;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public Integer getPaymentModeId() {
        return paymentModeId;
    }

    public void setPaymentModeId(Integer paymentModeId) {
        this.paymentModeId = paymentModeId;
    }

    public Double getNetPayable() {
        return NetPayable;
    }

    public void setNetPayable(Double netPayable) {
        this.NetPayable = netPayable;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public ArrayList<PickupRequestDetailModel> getDimensions() {
        return dimensions;
    }

    public void setDimensions(ArrayList<PickupRequestDetailModel> dimensions) {
        this.dimensions = dimensions;
    }

}
