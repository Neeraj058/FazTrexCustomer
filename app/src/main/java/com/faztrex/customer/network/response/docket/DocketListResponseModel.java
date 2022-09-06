package com.faztrex.customer.network.response.docket;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DocketListResponseModel {

    @SerializedName(value = "id", alternate = "Id")
    @Expose
    private String id;

    @SerializedName(value = "docketNo", alternate = "DocketNo")
    @Expose
    private String docketNo;

    @SerializedName(value = "bookingDate", alternate = "BookingDate")
    @Expose
    private String bookingDate;

    @SerializedName(value = "origin", alternate = "Origin")
    @Expose
    private String origin;

    @SerializedName(value = "destination", alternate = "Destination")
    @Expose
    private String destination;

    @SerializedName(value = "consignorName", alternate = "ConsignorName")
    @Expose
    private String consignorName;

    @SerializedName(value = "consigneeName", alternate = "ConsigneeName")
    @Expose
    private String consigneeName;

    @SerializedName(value = "noOfPackages", alternate = "NoOfPackages")
    @Expose
    private String noOfPackages;

    @SerializedName(value = "actualWeight", alternate = "ActualWeight")
    @Expose
    private String actualWeight;

    @SerializedName(value = "chargeWeight", alternate = "ChargeWeight")
    @Expose
    private String chargeWeight;

    @SerializedName(value = "customerType", alternate = "CustomerType")
    @Expose
    private String customerType;

    @SerializedName(value = "dispatchMode", alternate = "DispatchMode")
    @Expose
    private String dispatchMode;

    @SerializedName(value = "paymentType", alternate = "PaymentType")
    @Expose
    private String paymentType;

    @SerializedName(value = "netAmount", alternate = "NetAmount")
    @Expose
    private String netAmount;

    @SerializedName(value = "ewayBillNo", alternate = "EwayBillNo")
    @Expose
    private String ewayBillNo;

    @SerializedName(value = "invoiceValue", alternate = "InvoiceValue")
    @Expose
    private String invoiceValue;

    @SerializedName(value = "deliveryStatus", alternate = "DeliveryStatus")
    @Expose
    private String deliveryStatus;

    @SerializedName(value = "modifiedOn", alternate = "ModifiedOn")
    @Expose
    private String modifiedOn;

    @SerializedName(value = "ratingValue", alternate = "RatingValue")
    @Expose
    private String ratingValue;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDocketNo() {
        return docketNo;
    }

    public void setDocketNo(String docketNo) {
        this.docketNo = docketNo;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getConsignorName() {
        return consignorName;
    }

    public void setConsignorName(String consignorName) {
        this.consignorName = consignorName;
    }

    public String getConsigneeName() {
        return consigneeName;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
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

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getDispatchMode() {
        return dispatchMode;
    }

    public void setDispatchMode(String dispatchMode) {
        this.dispatchMode = dispatchMode;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(String netAmount) {
        this.netAmount = netAmount;
    }

    public String getEwayBillNo() {
        return ewayBillNo;
    }

    public void setEwayBillNo(String ewayBillNo) {
        this.ewayBillNo = ewayBillNo;
    }

    public String getInvoiceValue() {
        return invoiceValue;
    }

    public void setInvoiceValue(String invoiceValue) {
        this.invoiceValue = invoiceValue;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public String getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(String modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public String getRatingValue() {
        return ratingValue;
    }

    public void setRatingValue(String ratingValue) {
        this.ratingValue = ratingValue;
    }
}


