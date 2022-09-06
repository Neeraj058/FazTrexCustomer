package com.faztrex.customer.models.general;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DashboardCount implements Parcelable {

  /*  @SerializedName(value = "docketBookingCount", alternate = "DocketBookingCount")
    @Expose
    private String docketBookingCount;

    @SerializedName(value = "pickUpRequestCount", alternate = "PickUpRequestCount")
    @Expose
    private String pickUpRequestCount;

    @SerializedName(value = "totalBills", alternate = "TotalBills")
    @Expose
    private String totalBills;

    @SerializedName(value = "outstandingAmount", alternate = "OutstandingAmount")
    @Expose
    private String outstandingAmount;*/
    @SerializedName(value = "docketBooking", alternate = "DocketBooking")
    @Expose
    private String docketBooking;

    @SerializedName(value = "pickupRequest", alternate = "PickupRequest")
    @Expose
    private String pickupRequest;

    @SerializedName(value = "totalBilling", alternate = "TotalBilling")
    @Expose
    private String totalBilling;

    @SerializedName(value = "totalOutstanding", alternate = "TotalOutstanding")
    @Expose
    private String totalOutstanding;

    public DashboardCount() {
    }

    protected DashboardCount(Parcel in) {
        docketBooking = in.readString();
        pickupRequest = in.readString();
        totalBilling = in.readString();
        totalOutstanding = in.readString();
    }

    public static final Creator<DashboardCount> CREATOR = new Creator<DashboardCount>() {
        @Override
        public DashboardCount createFromParcel(Parcel in) {
            return new DashboardCount(in);
        }

        @Override
        public DashboardCount[] newArray(int size) {
            return new DashboardCount[size];
        }
    };

    public String getDocketBookingCount() {
        return docketBooking;
    }

    public void setDocketBookingCount(String docketBooking) {
        this.docketBooking = docketBooking;
    }

    public String getPickUpRequestCount() {
        return pickupRequest;
    }

    public void setPickUpRequestCount(String pickupRequest) {
        this.pickupRequest = pickupRequest;
    }

    public String getTotalBills() {
        return totalBilling;
    }

    public void setTotalBills(String totalBilling) {
        this.totalBilling = totalBilling;
    }

    public String getOutstandingAmount() {
        return totalOutstanding;
    }

    public void setOutstandingAmount(String totalOutstanding) {
        this.totalOutstanding = totalOutstanding;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(docketBooking);
        dest.writeString(pickupRequest);
        dest.writeString(totalBilling);
        dest.writeString(totalOutstanding);
    }
}
