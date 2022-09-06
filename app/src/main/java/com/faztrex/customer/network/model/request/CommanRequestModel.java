package com.faztrex.customer.network.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CommanRequestModel {
    @SerializedName(value = "Id", alternate = "id")
    @Expose
    private String Id;

    @SerializedName(value = "cid", alternate = "Cid")
    @Expose
    private Integer cid;

    @SerializedName(value = "bid", alternate = "Bid")
    @Expose
    private Integer bid;

    @SerializedName(value = "sortcol", alternate = "Sortcol")
    @Expose
    private String sortcol;

    @SerializedName(value = "sortdir", alternate = "Sortdir")
    @Expose
    private String sortdir;

    @SerializedName(value = "pageindex", alternate = "Pageindex")
    @Expose
    private String pageindex;

    @SerializedName(value = "pagesize", alternate = "Pagesize")
    @Expose
    private String pagesize;

    @SerializedName(value = "keyword", alternate = "Keyword")
    @Expose
    private String keyword;

    @SerializedName(value = "search", alternate = "Search")
    @Expose
    private String search;

    @SerializedName(value = "fromdate", alternate = "Fromdate")
    @Expose
    private String fromdate;

    @SerializedName(value = "todate", alternate = "Todate")
    @Expose
    private String todate;

    @SerializedName(value = "fyfromdate", alternate = "Fyfromdate")
    @Expose
    private String fyfromdate;

    @SerializedName(value = "fytodate", alternate = "Fytodate")
    @Expose
    private String fytodate;

    @SerializedName(value = "consignorid", alternate = "Consignorid")
    @Expose
    private Integer consignorid;

    @SerializedName(value = "customerid", alternate = "Customerid")
    @Expose
    private Integer customerid;

    @SerializedName(value = "dispatchmode", alternate = "Dispatchmode")
    @Expose
    private Integer dispatchmode;

    @SerializedName(value = "paymenttype", alternate = "Paymenttype")
    @Expose
    private Integer paymenttype;

    @SerializedName(value = "originid", alternate = "Originid")
    @Expose
    private Integer originid;

    @SerializedName(value = "bookingstateid", alternate = "Bookingstateid")
    @Expose
    private Integer bookingstateid;

    @SerializedName(value = "bookingcityid", alternate = "Bookingcityid")
    @Expose
    private Integer bookingcityid;

    @SerializedName(value = "bookingpostcodeid", alternate = "Bookingpostcodeid")
    @Expose
    private Integer bookingpostcodeid;

    @SerializedName(value = "destinationstateid", alternate = "Destinationstateid")
    @Expose
    private Integer destinationstateid;

    @SerializedName(value = "destinationcityid", alternate = "Destinationcityid")
    @Expose
    private Integer destinationcityid;

    @SerializedName(value = "destinationpostcodeid", alternate = "Destinationpostcodeid")
    @Expose
    private Integer destinationpostcodeid;

    @SerializedName(value = "vehicletypeid", alternate = "Vehicletypeid")
    @Expose
    private Integer vehicletypeid;

    @SerializedName(value = "packingtypeid", alternate = "Packingtypeid")
    @Expose
    private Integer packingtypeid;

    @SerializedName(value = "Rating", alternate = "rating")
    @Expose
    private String rating;

    @SerializedName(value = "DocketId", alternate = "docketId")
    @Expose
    private String docketId;

    @SerializedName(value = "PickupPostcode", alternate = "pickupPostcode")
    @Expose
    private String PickupPostcode;

    @SerializedName(value = "DeliveryPostcode", alternate = "deliveryPostcode")
    @Expose
    private String DeliveryPostcode;

    @SerializedName(value = "CouponCode", alternate = "couponCode")
    @Expose
    private String CouponCode;

    public String getCouponCode() {
        return CouponCode;
    }

    public void setCouponCode(String couponCode) {
        CouponCode = couponCode;
    }


    public Double getBasicCharge() {
        return BasicCharge;
    }

    public void setBasicCharge(Double basicCharge) {
        BasicCharge = basicCharge;
    }

    @SerializedName(value = "BasicCharge", alternate = "basicCharge")
    @Expose
    private Double BasicCharge;

    @SerializedName(value = "Otp", alternate = "otp")
    @Expose
    private String Otp;

    public String getOtp() {
        return Otp;
    }

    public void setOtp(String otp) {
        Otp = otp;
    }

    public String getPickupPostcode() {
        return PickupPostcode;
    }

    public void setPickupPostcode(String pickupPostcode) {
        this.PickupPostcode = pickupPostcode;
    }

    public String getDeliveryPostcode() {
        return DeliveryPostcode;
    }

    public void setDeliveryPostcode(String deliveryPostcode) {
        this.DeliveryPostcode = deliveryPostcode;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getSortcol() {
        return sortcol;
    }

    public void setSortcol(String sortcol) {
        this.sortcol = sortcol;
    }

    public String getSortdir() {
        return sortdir;
    }

    public void setSortdir(String sortdir) {
        this.sortdir = sortdir;
    }

    public String getPageindex() {
        return pageindex;
    }

    public void setPageindex(String pageindex) {
        this.pageindex = pageindex;
    }

    public String getPagesize() {
        return pagesize;
    }

    public void setPagesize(String pagesize) {
        this.pagesize = pagesize;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getFromdate() {
        return fromdate;
    }

    public void setFromdate(String fromdate) {
        this.fromdate = fromdate;
    }

    public String getTodate() {
        return todate;
    }

    public void setTodate(String todate) {
        this.todate = todate;
    }

    public String getFyfromdate() {
        return fyfromdate;
    }

    public void setFyfromdate(String fyfromdate) {
        this.fyfromdate = fyfromdate;
    }

    public String getFytodate() {
        return fytodate;
    }

    public void setFytodate(String fytodate) {
        this.fytodate = fytodate;
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

    public Integer getConsignorid() {
        return consignorid;
    }

    public void setConsignorid(Integer consignorid) {
        this.consignorid = consignorid;
    }

    public Integer getBookingstateid() {
        return bookingstateid;
    }

    public void setBookingstateid(Integer bookingstateid) {
        this.bookingstateid = bookingstateid;
    }

    public Integer getBookingcityid() {
        return bookingcityid;
    }

    public void setBookingcityid(Integer bookingcityid) {
        this.bookingcityid = bookingcityid;
    }

    public Integer getBookingpostcodeid() {
        return bookingpostcodeid;
    }

    public void setBookingpostcodeid(Integer bookingpostcodeid) {
        this.bookingpostcodeid = bookingpostcodeid;
    }

    public Integer getDestinationstateid() {
        return destinationstateid;
    }

    public void setDestinationstateid(Integer destinationstateid) {
        this.destinationstateid = destinationstateid;
    }

    public Integer getDestinationcityid() {
        return destinationcityid;
    }

    public void setDestinationcityid(Integer destinationcityid) {
        this.destinationcityid = destinationcityid;
    }

    public Integer getDestinationpostcodeid() {
        return destinationpostcodeid;
    }

    public void setDestinationpostcodeid(Integer destinationpostcodeid) {
        this.destinationpostcodeid = destinationpostcodeid;
    }

    public Integer getVehicletypeid() {
        return vehicletypeid;
    }

    public void setVehicletypeid(Integer vehicletypeid) {
        this.vehicletypeid = vehicletypeid;
    }

    public Integer getPackingtypeid() {
        return packingtypeid;
    }

    public void setPackingtypeid(Integer packingtypeid) {
        this.packingtypeid = packingtypeid;
    }

    public Integer getCustomerid() {
        return customerid;
    }

    public void setCustomerid(Integer customerid) {
        this.customerid = customerid;
    }

    public Integer getDispatchmode() {
        return dispatchmode;
    }

    public void setDispatchmode(Integer dispatchmode) {
        this.dispatchmode = dispatchmode;
    }

    public Integer getPaymenttype() {
        return paymenttype;
    }

    public void setPaymenttype(Integer paymenttype) {
        this.paymenttype = paymenttype;
    }

    public Integer getOriginid() {
        return originid;
    }

    public void setOriginid(Integer originid) {
        this.originid = originid;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDocketId() {
        return docketId;
    }

    public void setDocketId(String docketId) {
        this.docketId = docketId;
    }

}
