package com.faztrex.customer.network.response.pickuprequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PickupListResponseModel {

    @SerializedName(value = "RowNo", alternate = "rowNo")
    @Expose
    private String rowNo;

    @SerializedName(value = "Id", alternate = "id")
    @Expose
    private String id;

    @SerializedName(value = "No", alternate = "no")
    @Expose
    private String no;

    @SerializedName(value = "DATE", alternate = "date")
    @Expose
    private String date;

    @SerializedName(value = "BookingState", alternate = "bookingState")
    @Expose
    private String bookingState;

    @SerializedName(value = "BookingCity", alternate = "bookingCity")
    @Expose
    private String bookingCity;

    @SerializedName(value = "BookingPostCode", alternate = "bookingPostCode")
    @Expose
    private String bookingPostCode;

    @SerializedName(value = "DestinationState", alternate = "destinationState")
    @Expose
    private String destinationState;

    @SerializedName(value = "DestinationCity", alternate = "destinationCity")
    @Expose
    private String destinationCity;

    @SerializedName(value = "DestinationPostCode", alternate = "destinationPostCode")
    @Expose
    private String destinationPostCode;

    @SerializedName(value = "ConsignorName", alternate = "consignorName")
    @Expose
    private String consignorName;

    @SerializedName(value = "ConsigneeName", alternate = "consigneeName")
    @Expose
    private String consigneeName;

    @SerializedName(value = "NoOfPackages", alternate = "noOfPackages")
    @Expose
    private String noOfPackages;

    @SerializedName(value = "ActualWeight", alternate = "actualWeight")
    @Expose
    private String actualWeight;

    @SerializedName(value = "ChargeWeight", alternate = "chargeWeight")
    @Expose
    private String chargeWeight;

    @SerializedName(value = "BasicChargeAmount", alternate = "basicChargeAmount")
    @Expose
    private String basicChargeAmount;

    @SerializedName(value = "DocketNo", alternate = "docketNo")
    @Expose
    private String docketNo;

    public String getRowNo() {
        return rowNo;
    }

    public void setRowNo(String rowNo) {
        this.rowNo = rowNo;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBookingState() {
        return bookingState;
    }

    public void setBookingState(String bookingState) {
        this.bookingState = bookingState;
    }

    public String getBookingCity() {
        return bookingCity;
    }

    public void setBookingCity(String bookingCity) {
        this.bookingCity = bookingCity;
    }

    public String getBookingPostCode() {
        return bookingPostCode;
    }

    public void setBookingPostCode(String bookingPostCode) {
        this.bookingPostCode = bookingPostCode;
    }

    public String getDestinationState() {
        return destinationState;
    }

    public void setDestinationState(String destinationState) {
        this.destinationState = destinationState;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    public String getDestinationPostCode() {
        return destinationPostCode;
    }

    public void setDestinationPostCode(String destinationPostCode) {
        this.destinationPostCode = destinationPostCode;
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

    public String getBasicChargeAmount() {
        return basicChargeAmount;
    }

    public void setBasicChargeAmount(String basicChargeAmount) {
        this.basicChargeAmount = basicChargeAmount;
    }

    public String getDocketNo() {
        return docketNo;
    }

    public void setDocketNo(String docketNo) {
        this.docketNo = docketNo;
    }
}
