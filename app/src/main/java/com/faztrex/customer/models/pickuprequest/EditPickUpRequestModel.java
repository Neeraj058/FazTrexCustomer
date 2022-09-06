package com.faztrex.customer.models.pickuprequest;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class EditPickUpRequestModel implements Parcelable {

    public static final Creator<EditPickUpRequestModel> CREATOR = new Creator<EditPickUpRequestModel>() {
        @Override
        public EditPickUpRequestModel createFromParcel(Parcel in) {
            return new EditPickUpRequestModel(in);
        }

        @Override
        public EditPickUpRequestModel[] newArray(int size) {
            return new EditPickUpRequestModel[size];
        }
    };
    @SerializedName(value = "MainDetails", alternate = "mainDetails")
    private EditPickupRequestFormModel editPickupRequestFormModel;
    @SerializedName(value = "SubDetails", alternate = "subDetails")
    private ArrayList<EditDimensionDetailModel> editDimensionDetailModel;

    protected EditPickUpRequestModel(Parcel in) {
        editPickupRequestFormModel = in.readParcelable(EditPickupRequestFormModel.class.getClassLoader());
        editDimensionDetailModel = in.createTypedArrayList(EditDimensionDetailModel.CREATOR);
    }

    public EditPickupRequestFormModel getEditPickupRequestFormModel() {
        return editPickupRequestFormModel;
    }

    public void setEditPickupRequestFormModel(EditPickupRequestFormModel editPickupRequestFormModel) {
        this.editPickupRequestFormModel = editPickupRequestFormModel;
    }

    public ArrayList<EditDimensionDetailModel> getEditDimensionDetailModel() {
        return editDimensionDetailModel;
    }

    public void setEditDimensionDetailModel(ArrayList<EditDimensionDetailModel> editDimensionDetailModel) {
        this.editDimensionDetailModel = editDimensionDetailModel;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(editPickupRequestFormModel, flags);
        dest.writeTypedList(editDimensionDetailModel);
    }
}
