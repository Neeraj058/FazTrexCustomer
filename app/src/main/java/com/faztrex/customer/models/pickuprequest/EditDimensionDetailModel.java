package com.faztrex.customer.models.pickuprequest;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EditDimensionDetailModel implements Parcelable {

    @SerializedName(value = "id", alternate = "Id")
    @Expose
    private String id;

    @SerializedName(value = "boxes", alternate = "Boxes")
    @Expose
    private String boxes;

    @SerializedName(value = "length", alternate = "Length")
    @Expose
    private String length;

    @SerializedName(value = "width", alternate = "Width")
    @Expose
    private String width;

    @SerializedName(value = "height", alternate = "Height")
    @Expose
    private String height;

    @SerializedName(value = "actualWeight", alternate = "ActualWeight")
    @Expose
    private String actualWeight;

    @SerializedName(value = "actualWeightPerBox", alternate = "ActualWeightPerBox")
    @Expose
    private String actualWeightPerBox;

    @SerializedName(value = "volumetricWeight", alternate = "VolumetricWeight")
    @Expose
    private String volumetricWeight;

    protected EditDimensionDetailModel(Parcel in) {
        id = in.readString();
        boxes = in.readString();
        length = in.readString();
        width = in.readString();
        height = in.readString();
        actualWeight = in.readString();
        actualWeightPerBox = in.readString();
        volumetricWeight = in.readString();
    }

    public static final Creator<EditDimensionDetailModel> CREATOR = new Creator<EditDimensionDetailModel>() {
        @Override
        public EditDimensionDetailModel createFromParcel(Parcel in) {
            return new EditDimensionDetailModel(in);
        }

        @Override
        public EditDimensionDetailModel[] newArray(int size) {
            return new EditDimensionDetailModel[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBoxes() {
        return boxes;
    }

    public void setBoxes(String boxes) {
        this.boxes = boxes;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getActualWeight() {
        return actualWeight;
    }

    public void setActualWeight(String actualWeight) {
        this.actualWeight = actualWeight;
    }

    public String getActualWeightPerBox() {
        return actualWeightPerBox;
    }

    public void setActualWeightPerBox(String actualWeightPerBox) {
        this.actualWeightPerBox = actualWeightPerBox;
    }

    public String getVolumetricWeight() {
        return volumetricWeight;
    }

    public void setVolumetricWeight(String volumetricWeight) {
        this.volumetricWeight = volumetricWeight;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(boxes);
        dest.writeString(length);
        dest.writeString(width);
        dest.writeString(height);
        dest.writeString(actualWeight);
        dest.writeString(actualWeightPerBox);
        dest.writeString(volumetricWeight);
    }
}
