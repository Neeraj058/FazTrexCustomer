package com.faztrex.customer.models.hyperlocal;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HyperLocalList extends BaseObservable {

    @SerializedName(value = "RowNo", alternate = "rowNo")
    @Expose
    public String RowNo;

    @SerializedName(value = "Id", alternate = "id")
    @Expose
    public String Id;

    @SerializedName(value = "No", alternate = "no")
    @Expose
    public String No;

    @SerializedName(value = "BookingDateTime", alternate = "bookingDateTime")
    @Expose
    public String BookingDateTime;

    @SerializedName(value = "PickupPersonName", alternate = "pickupPersonName")
    @Expose
    public String PickupPersonName;

    @SerializedName(value = "PickupContactNo", alternate = "pickupContactNo")
    @Expose
    public String PickupContactNo;

    @SerializedName(value = "DeliveryPersonName", alternate = "deliveryPersonName")
    @Expose
    public String DeliveryPersonName;

    @SerializedName(value = "Weight", alternate = "weight")
    @Expose
    public String Weight;

    @SerializedName(value = "Status", alternate = "status")
    @Expose
    public String Status;

    @SerializedName(value = "TotalAmount", alternate = "totalAmount")
    @Expose
    public String TotalAmount;

    @SerializedName(value = "Product", alternate = "product")
    @Expose
    public String Product;

    @Bindable
    public String getRowNo() {
        return RowNo;
    }

    public void setRowNo(String rowNo) {
        RowNo = rowNo;
        notifyPropertyChanged(BR.rowNo);
    }

    @Bindable
    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
        notifyPropertyChanged(BR.id);
    }

    @Bindable
    public String getNo() {
        return No;
    }

    public void setNo(String no) {
        No = no;
        notifyPropertyChanged(BR.no);
    }

    @Bindable
    public String getBookingDateTime() {
        return BookingDateTime;
    }

    public void setBookingDateTime(String bookingDateTime) {
        BookingDateTime = bookingDateTime;
        notifyPropertyChanged(BR.bookingDateTime);
    }

    @Bindable
    public String getPickupPersonName() {
        return PickupPersonName;
    }

    public void setPickupPersonName(String pickupPersonName) {
        PickupPersonName = pickupPersonName;
        notifyPropertyChanged(BR.pickupPersonName);
    }

    @Bindable
    public String getPickupContactNo() {
        return PickupContactNo;
    }

    public void setPickupContactNo(String pickupContactNo) {
        PickupContactNo = pickupContactNo;
        notifyPropertyChanged(BR.pickupContactNo);
    }

    @Bindable
    public String getDeliveryPersonName() {
        return DeliveryPersonName;
    }

    public void setDeliveryPersonName(String deliveryPersonName) {
        DeliveryPersonName = deliveryPersonName;
        notifyPropertyChanged(BR.deliveryPersonName);
    }

    @Bindable
    public String getWeight() {
        return Weight;
    }

    public void setWeight(String weight) {
        Weight = weight;
        notifyPropertyChanged(BR.weight);
    }

    @Bindable
    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
        notifyPropertyChanged(BR.status);
    }

    @Bindable
    public String getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        TotalAmount = totalAmount;
        notifyPropertyChanged(BR.totalAmount);
    }

    @Bindable
    public String getProduct() {
        return Product;
    }

    public void setProduct(String product) {
        Product = product;
        notifyPropertyChanged(BR.product);
    }
}
