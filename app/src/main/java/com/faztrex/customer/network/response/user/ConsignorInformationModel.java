package com.faztrex.customer.network.response.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ConsignorInformationModel {


    @SerializedName(value = "postcode", alternate = "Postcode")
    @Expose
    String postcode;

    @SerializedName(value = "address", alternate = "Address")
    @Expose
    String address;

    @SerializedName(value = "gstNo", alternate = "GstNo")
    @Expose
    String gstNo;

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGstNo() {
        return gstNo;
    }

    public void setGstNo(String gstNo) {
        this.gstNo = gstNo;
    }
}
