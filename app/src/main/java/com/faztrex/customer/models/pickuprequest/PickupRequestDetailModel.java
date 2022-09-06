package com.faztrex.customer.models.pickuprequest;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PickupRequestDetailModel implements Parcelable {

    @SerializedName(value = "id", alternate = "Id")
    @Expose
    private Integer id;
    @SerializedName(value = "pickUpRequestId", alternate = "PickUpRequestId")
    @Expose
    private Integer pickUpRequestId;
    @SerializedName(value = "boxes", alternate = "Boxes")
    @Expose
    private Integer Boxes;
    @SerializedName(value = "length", alternate = "Length")
    @Expose
    private Double length;
    @SerializedName(value = "width", alternate = "Width")
    @Expose
    private Double width;
    @SerializedName(value = "height", alternate = "Height")
    @Expose
    private Double height;
    @SerializedName(value = "actualWeight", alternate = "ActualWeight")
    @Expose
    private Double actualWeight;
    @SerializedName(value = "actualWeightPerBox", alternate = "ActualWeightPerBox")
    @Expose
    private Double actualWeightPerBox;
    @SerializedName(value = "volumetricWeight", alternate = "VolumetricWeight")
    @Expose
    private Double volumetricWeight;
    @SerializedName(value = "sid", alternate = "Sid")
    @Expose
    private Integer sid;
    @SerializedName(value = "cid", alternate = "Cid")
    @Expose
    private Integer cid;
    @SerializedName(value = "bid", alternate = "Bid")
    @Expose
    private Integer bid;
    @SerializedName(value = "uid", alternate = "Uid")
    @Expose
    private Integer uid;
    @SerializedName(value = "isActive", alternate = "IsActive")
    @Expose
    private Integer isActive;
    @SerializedName(value = "isDelete", alternate = "IsDelete")
    @Expose
    private Integer isDelete;
    @SerializedName(value = "entryDate", alternate = "EntryDate")
    @Expose
    private String entryDate;
    @SerializedName(value = "lastModifyDate", alternate = "LastModifyDate")
    @Expose
    private String lastModifyDate;
    @SerializedName(value = "lastModifyBy", alternate = "LastModifyBy")
    @Expose
    private Integer lastModifyBy;
    @SerializedName(value = "isDefault", alternate = "IsDefault")
    @Expose
    private Integer isDefault;
    @SerializedName(value = "isEnable", alternate = "IsEnable")
    @Expose
    private Integer isEnable;
    @SerializedName(value = "status", alternate = "Status")
    @Expose
    private Integer status;
    @SerializedName(value = "isSync", alternate = "IsSync")
    @Expose
    private Integer isSync;
    @SerializedName(value = "isFrom", alternate = "IsFrom")
    @Expose
    private Integer isFrom;
    @SerializedName(value = "wildSearch", alternate = "WildSearch")
    @Expose
    private String wildSearch;
    @SerializedName(value = "notes", alternate = "Notes")
    @Expose
    private String notes;
    @SerializedName(value = "basicChargeAmount", alternate = "BasicChargeAmount")
    @Expose
    private Double basicChargeAmount;

    @SerializedName(value = "chargableWeight", alternate = "ChargableWeight")
    @Expose
    private Double chargableWeight;

    @SerializedName(value = "chargableWeightPer", alternate = "ChargableWeightPer")
    @Expose
    private Double chargableWeightPer;

    public PickupRequestDetailModel() {
    }

    protected PickupRequestDetailModel(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        if (in.readByte() == 0) {
            pickUpRequestId = null;
        } else {
            pickUpRequestId = in.readInt();
        }
        if (in.readByte() == 0) {
            Boxes = null;
        } else {
            Boxes = in.readInt();
        }
        if (in.readByte() == 0) {
            length = null;
        } else {
            length = in.readDouble();
        }
        if (in.readByte() == 0) {
            width = null;
        } else {
            width = in.readDouble();
        }
        if (in.readByte() == 0) {
            height = null;
        } else {
            height = in.readDouble();
        }
        if (in.readByte() == 0) {
            actualWeight = null;
        } else {
            actualWeight = in.readDouble();
        }
        if (in.readByte() == 0) {
            actualWeightPerBox = null;
        } else {
            actualWeightPerBox = in.readDouble();
        }
        if (in.readByte() == 0) {
            volumetricWeight = null;
        } else {
            volumetricWeight = in.readDouble();
        }
        if (in.readByte() == 0) {
            sid = null;
        } else {
            sid = in.readInt();
        }
        if (in.readByte() == 0) {
            cid = null;
        } else {
            cid = in.readInt();
        }
        if (in.readByte() == 0) {
            bid = null;
        } else {
            bid = in.readInt();
        }
        if (in.readByte() == 0) {
            uid = null;
        } else {
            uid = in.readInt();
        }
        if (in.readByte() == 0) {
            isActive = null;
        } else {
            isActive = in.readInt();
        }
        if (in.readByte() == 0) {
            isDelete = null;
        } else {
            isDelete = in.readInt();
        }
        entryDate = in.readString();
        lastModifyDate = in.readString();
        if (in.readByte() == 0) {
            lastModifyBy = null;
        } else {
            lastModifyBy = in.readInt();
        }
        if (in.readByte() == 0) {
            isDefault = null;
        } else {
            isDefault = in.readInt();
        }
        if (in.readByte() == 0) {
            isEnable = null;
        } else {
            isEnable = in.readInt();
        }
        if (in.readByte() == 0) {
            status = null;
        } else {
            status = in.readInt();
        }
        if (in.readByte() == 0) {
            isSync = null;
        } else {
            isSync = in.readInt();
        }
        if (in.readByte() == 0) {
            isFrom = null;
        } else {
            isFrom = in.readInt();
        }
        wildSearch = in.readString();
        notes = in.readString();
        if (in.readByte() == 0) {
            basicChargeAmount = null;
        } else {
            basicChargeAmount = in.readDouble();
        }
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
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        if (pickUpRequestId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(pickUpRequestId);
        }
        if (Boxes == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(Boxes);
        }
        if (length == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(length);
        }
        if (width == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(width);
        }
        if (height == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(height);
        }
        if (actualWeight == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(actualWeight);
        }
        if (actualWeightPerBox == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(actualWeightPerBox);
        }
        if (volumetricWeight == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(volumetricWeight);
        }
        if (sid == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(sid);
        }
        if (cid == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(cid);
        }
        if (bid == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(bid);
        }
        if (uid == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(uid);
        }
        if (isActive == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(isActive);
        }
        if (isDelete == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(isDelete);
        }
        dest.writeString(entryDate);
        dest.writeString(lastModifyDate);
        if (lastModifyBy == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(lastModifyBy);
        }
        if (isDefault == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(isDefault);
        }
        if (isEnable == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(isEnable);
        }
        if (status == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(status);
        }
        if (isSync == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(isSync);
        }
        if (isFrom == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(isFrom);
        }
        dest.writeString(wildSearch);
        dest.writeString(notes);
        if (basicChargeAmount == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(basicChargeAmount);
        }
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
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PickupRequestDetailModel> CREATOR = new Creator<PickupRequestDetailModel>() {
        @Override
        public PickupRequestDetailModel createFromParcel(Parcel in) {
            return new PickupRequestDetailModel(in);
        }

        @Override
        public PickupRequestDetailModel[] newArray(int size) {
            return new PickupRequestDetailModel[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPickUpRequestId() {
        return pickUpRequestId;
    }

    public void setPickUpRequestId(Integer pickUpRequestId) {
        this.pickUpRequestId = pickUpRequestId;
    }

    public Integer getBoxes() {
        return Boxes;
    }

    public void setBoxes(Integer boxes) {
        Boxes = boxes;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getActualWeight() {
        return actualWeight;
    }

    public void setActualWeight(Double actualWeight) {
        this.actualWeight = actualWeight;
    }

    public Double getVolumetricWeight() {
        return volumetricWeight;
    }

    public void setVolumetricWeight(Double volumetricWeight) {
        this.volumetricWeight = volumetricWeight;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public String getLastModifyDate() {
        return lastModifyDate;
    }

    public void setLastModifyDate(String lastModifyDate) {
        this.lastModifyDate = lastModifyDate;
    }

    public Integer getLastModifyBy() {
        return lastModifyBy;
    }

    public void setLastModifyBy(Integer lastModifyBy) {
        this.lastModifyBy = lastModifyBy;
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

    public Integer getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Integer isEnable) {
        this.isEnable = isEnable;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsSync() {
        return isSync;
    }

    public void setIsSync(Integer isSync) {
        this.isSync = isSync;
    }

    public Integer getIsFrom() {
        return isFrom;
    }

    public void setIsFrom(Integer isFrom) {
        this.isFrom = isFrom;
    }

    public String getWildSearch() {
        return wildSearch;
    }

    public void setWildSearch(String wildSearch) {
        this.wildSearch = wildSearch;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Double getActualWeightPerBox() {
        return actualWeightPerBox;
    }

    public void setActualWeightPerBox(Double actualWeightPerBox) {
        this.actualWeightPerBox = actualWeightPerBox;
    }

    public Double getBasicChargeAmount() {
        return basicChargeAmount;
    }

    public void setBasicChargeAmount(Double basicChargeAmount) {
        this.basicChargeAmount = basicChargeAmount;
    }

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

}
