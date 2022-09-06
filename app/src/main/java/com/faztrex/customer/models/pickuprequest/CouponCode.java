package com.faztrex.customer.models.pickuprequest;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CouponCode implements Parcelable {

    @SerializedName(value = "NetPayable", alternate = "netPayable")
    @Expose
    private String NetPayable;

    @SerializedName(value = "Discount", alternate = "discount")
    @Expose
    private String Discount;

    protected CouponCode(Parcel in) {
        NetPayable = in.readString();
        Discount = in.readString();
    }

    public static final Creator<CouponCode> CREATOR = new Creator<CouponCode>() {
        @Override
        public CouponCode createFromParcel(Parcel in) {
            return new CouponCode(in);
        }

        @Override
        public CouponCode[] newArray(int size) {
            return new CouponCode[size];
        }
    };

    public String getNetPayable() {
        return NetPayable;
    }

    public void setNetPayable(String netPayable) {
        NetPayable = netPayable;
    }

    public String getDiscount() {
        return Discount;
    }

    public void setDiscount(String discount) {
        Discount = discount;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(NetPayable);
        parcel.writeString(Discount);
    }
}
