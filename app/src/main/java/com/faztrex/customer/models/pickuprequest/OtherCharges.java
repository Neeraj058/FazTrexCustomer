package com.faztrex.customer.models.pickuprequest;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OtherCharges implements Parcelable {

    @SerializedName(value = "ChargableWeight", alternate = "chargableWeight")
    @Expose
    private Double chargableWeight;

    @SerializedName(value = "ChargableWeightPer", alternate = "chargableWeightPer")
    @Expose
    private Double chargableWeightPer;

    @SerializedName(value = "BasicChargeAmount", alternate = "basicChargeAmount")
    @Expose
    private Double basicChargeAmount;

    protected OtherCharges(Parcel in) {
        if (in.readByte() == 0) {
            chargableWeight = null;
        } else {
            chargableWeight = in.readDouble();
        }
        if (in.readByte() == 0) {
            chargableWeightPer = null;
        } else {
            chargableWeightPer = in.readDouble();
        }
        if (in.readByte() == 0) {
            basicChargeAmount = null;
        } else {
            basicChargeAmount = in.readDouble();
        }
    }

    public static final Creator<OtherCharges> CREATOR = new Creator<OtherCharges>() {
        @Override
        public OtherCharges createFromParcel(Parcel in) {
            return new OtherCharges(in);
        }

        @Override
        public OtherCharges[] newArray(int size) {
            return new OtherCharges[size];
        }
    };

    public Double getChargableWeight() {
        return chargableWeight;
    }

    public void setChargableWeight(Double chargableWeight) {
        this.chargableWeight = chargableWeight;
    }

    public Double getChargableWeightPer() {
        return chargableWeightPer;
    }

    public void setChargableWeightPer(Double chargableWeightPer) {
        this.chargableWeightPer = chargableWeightPer;
    }

    public Double getBasicChargeAmount() {
        return basicChargeAmount;
    }

    public void setBasicChargeAmount(Double basicChargeAmount) {
        this.basicChargeAmount = basicChargeAmount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (chargableWeight == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(chargableWeight);
        }
        if (chargableWeightPer == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(chargableWeightPer);
        }
        if (basicChargeAmount == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(basicChargeAmount);
        }
    }
}
