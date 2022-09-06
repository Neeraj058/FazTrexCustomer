package com.faztrex.customer.models.hyperlocal;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HyperLocalFreight implements Parcelable {
    @SerializedName(value = "Freight", alternate = "freight")
    @Expose
    private String Freight;

    @SerializedName(value = "TotalKM", alternate = "totalKM")
    @Expose
    private String TotalKM;

    protected HyperLocalFreight(Parcel in) {
        Freight = in.readString();
        TotalKM = in.readString();
    }

    public static final Creator<HyperLocalFreight> CREATOR = new Creator<HyperLocalFreight>() {
        @Override
        public HyperLocalFreight createFromParcel(Parcel in) {
            return new HyperLocalFreight(in);
        }

        @Override
        public HyperLocalFreight[] newArray(int size) {
            return new HyperLocalFreight[size];
        }
    };

    public String getFreight() {
        return Freight;
    }

    public void setFreight(String freight) {
        Freight = freight;
    }

    public String getTotalKM() {
        return TotalKM;
    }

    public void setTotalKM(String totalKM) {
        TotalKM = totalKM;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(Freight);
        parcel.writeString(TotalKM);
    }
}
