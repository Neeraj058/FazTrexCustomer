package com.faztrex.customer.models.pickuprequest;

import android.os.Parcel;
import android.os.Parcelable;

public class DimensionDetailModel implements Parcelable {

    private int serialNo;

    private int boxes;

    private double length;

    private double Width;

    private double Height;

    private double actualWeight;

    private double actualWeightPerBox;

    private double volumetricWeight;

    public DimensionDetailModel(int serialNo, int boxes, double length, double width, double height, double actualWeight, double actualWeightPerBox, double volumetricWeight) {
        this.serialNo = serialNo;
        this.boxes = boxes;
        this.length = length;
        Width = width;
        Height = height;
        this.actualWeight = actualWeight;
        this.actualWeightPerBox = actualWeightPerBox;
        this.volumetricWeight = volumetricWeight;
    }

    protected DimensionDetailModel(Parcel in) {
        serialNo = in.readInt();
        boxes = in.readInt();
        length = in.readDouble();
        Width = in.readDouble();
        Height = in.readDouble();
        actualWeight = in.readDouble();
        actualWeightPerBox = in.readDouble();
        volumetricWeight = in.readDouble();
    }

    public static final Creator<DimensionDetailModel> CREATOR = new Creator<DimensionDetailModel>() {
        @Override
        public DimensionDetailModel createFromParcel(Parcel in) {
            return new DimensionDetailModel(in);
        }

        @Override
        public DimensionDetailModel[] newArray(int size) {
            return new DimensionDetailModel[size];
        }
    };

    public int getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(int serialNo) {
        this.serialNo = serialNo;
    }

    public int getBoxes() {
        return boxes;
    }

    public void setBoxes(int boxes) {
        this.boxes = boxes;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return Width;
    }

    public void setWidth(double width) {
        Width = width;
    }

    public double getHeight() {
        return Height;
    }

    public void setHeight(double height) {
        Height = height;
    }

    public double getActualWeight() {
        return actualWeight;
    }

    public void setActualWeight(double actualWeight) {
        this.actualWeight = actualWeight;
    }

    public double getActualWeightPerBox() {
        return actualWeightPerBox;
    }

    public void setActualWeightPerBox(double actualWeightPerBox) {
        this.actualWeightPerBox = actualWeightPerBox;
    }

    public double getVolumetricWeight() {
        return volumetricWeight;
    }

    public void setVolumetricWeight(double volumetricWeight) {
        this.volumetricWeight = volumetricWeight;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(serialNo);
        dest.writeInt(boxes);
        dest.writeDouble(length);
        dest.writeDouble(Width);
        dest.writeDouble(Height);
        dest.writeDouble(actualWeight);
        dest.writeDouble(actualWeightPerBox);
        dest.writeDouble(volumetricWeight);
    }
}
