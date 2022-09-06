package com.faztrex.customer.network.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VerticalResponse {

    @SerializedName("VerticleName")
    @Expose
    private String VerticleName;

    @SerializedName("PackingTypeName")
    @Expose
    private String PackingTypeName;


    public String getVerticleName() {
        return VerticleName;
    }
    public String getPackingTypeName() {
        return PackingTypeName;
    }

    public void setPackingTypeName(String packingTypeName) {
        PackingTypeName = packingTypeName;
    }

    public void setVerticleName(String verticleName) {
        VerticleName = verticleName;


    }
}
