package com.faztrex.customer.network.response.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName(value = "Id", alternate = "id")
    @Expose
    private Integer id;

    @SerializedName(value = "CFT", alternate = "cft")
    @Expose
    private Double CFT;

    @SerializedName(value = "CustomerCode", alternate = "customerCode")
    @Expose
    private String customerCode;

    @SerializedName(value = "CustomerName", alternate = "customerName")
    @Expose
    private String customerName;

    @SerializedName(value = "AccountGroupId", alternate = "accountGroupId")
    @Expose
    private String accountGroupId;

    @SerializedName(value = "ParentCustomerId", alternate = "parentCustomerId")
    @Expose
    private String parentCustomerId;

    @SerializedName(value = "ContactPerson", alternate = "contactPerson")
    @Expose
    private String contactPerson;

    @SerializedName(value = "MobileNo", alternate = "mobileNo")
    @Expose
    private String mobileNo;

    @SerializedName(value = "PhoneNo", alternate = "phoneNo")
    @Expose
    private String phoneNo;

    @SerializedName(value = "Emails", alternate = "emails")
    @Expose
    private String emails;

    @SerializedName(value = "Website", alternate = "website")
    @Expose
    private String website;

    @SerializedName(value = "Username", alternate = "username")
    @Expose
    private String username;

    @SerializedName(value = "Password", alternate = "password")
    @Expose
    private String password;

    @SerializedName(value = "ContractSignedBy", alternate = "contractSignedBy")
    @Expose
    private String contractSignedBy;

    @SerializedName(value = "ContractApproveBy", alternate = "contractApproveBy")
    @Expose
    private String contractApproveBy;

    @SerializedName(value = "ContractOriginId", alternate = "contractOriginId")
    @Expose
    private String contractOriginId;

    @SerializedName(value = "CreditDays", alternate = "creditDays")
    @Expose
    private String creditDays;

    @SerializedName(value = "InterestRate", alternate = "interestRate")
    @Expose
    private String interestRate;

    @SerializedName(value = "IsTDS", alternate = "isTDS")
    @Expose
    private String isTDS;

    @SerializedName(value = "TDSPercentage", alternate = "tDSPercentage")
    @Expose
    private String tDSPercentage;

    @SerializedName(value = "TDSDocument", alternate = "tDSDocument")
    @Expose
    private String tDSDocument;

    @SerializedName(value = "TANNo", alternate = "tANNo")
    @Expose
    private String tANNo;

    @SerializedName(value = "PANNo", alternate = "pANNo")
    @Expose
    private String pANNo;

    @SerializedName(value = "TINNo", alternate = "tINNo")
    @Expose
    private String tINNo;

    @SerializedName(value = "IsBlock", alternate = "isBlock")
    @Expose
    private String isBlock;

    @SerializedName(value = "BlockRemarks", alternate = "blockRemarks")
    @Expose
    private String blockRemarks;

    @SerializedName(value = "CodeOpeningDocument", alternate = "codeOpeningDocument")
    @Expose
    private String codeOpeningDocument;

    @SerializedName(value = "KYCDocument", alternate = "kYCDocument")
    @Expose
    private String kYCDocument;

    @SerializedName(value = "VerticleId", alternate = "verticleId")
    @Expose
    private String verticleId;

    @SerializedName(value = "ProductName", alternate = "productName")
    @Expose
    private String productName;

    @SerializedName(value = "PackingTypeId", alternate = "packingTypeId")
    @Expose
    private String packingTypeId;

    @SerializedName(value = "RiskType", alternate = "riskType")
    @Expose
    private String riskType;

    @SerializedName(value = "BankName", alternate = "bankName")
    @Expose
    private String bankName;

    @SerializedName(value = "BankCode", alternate = "bankCode")
    @Expose
    private String bankCode;

    @SerializedName(value = "IFSCCode", alternate = "iFSCCode")
    @Expose
    private String iFSCCode;

    @SerializedName(value = "MICRCode", alternate = "mICRCode")
    @Expose
    private String mICRCode;

    @SerializedName(value = "ACNo", alternate = "aCNo")
    @Expose
    private String aCNo;

    @SerializedName(value = "BranchName", alternate = "branchName")
    @Expose
    private String branchName;

    @SerializedName(value = "BranchAddress", alternate = "branchAddress")
    @Expose
    private String branchAddress;

    @SerializedName(value = "PostCode", alternate = "postCode")
    @Expose
    private String postCode;

    @SerializedName(value = "InsCompany", alternate = "insCompany")
    @Expose
    private String insCompany;

    @SerializedName(value = "PolicyNo", alternate = "policyNo")
    @Expose
    private String policyNo;

    @SerializedName(value = "PolicyAmount", alternate = "policyAmount")
    @Expose
    private String policyAmount;

    @SerializedName(value = "PolicyStartDate", alternate = "policyStartDate")
    @Expose
    private String policyStartDate;

    @SerializedName(value = "PolicyExpiryDate", alternate = "policyExpiryDate")
    @Expose
    private String policyExpiryDate;

    @SerializedName(value = "Risk", alternate = "risk")
    @Expose
    private String risk;

    @SerializedName(value = "Sid", alternate = "sid")
    @Expose
    private String sid;

    @SerializedName(value = "Cid", alternate = "cid")
    @Expose
    private String cid;

    @SerializedName(value = "Bid", alternate = "bid")
    @Expose
    private String bid;

    @SerializedName(value = "Uid", alternate = "uid")
    @Expose
    private String uid;

    @SerializedName(value = "IsActive", alternate = "isActive")
    @Expose
    private String isActive;

    @SerializedName(value = "IsDelete", alternate = "isDelete")
    @Expose
    private String isDelete;

    @SerializedName(value = "IsDefault", alternate = "isDefault")
    @Expose
    private String isDefault;

    @SerializedName(value = "IsEnable", alternate = "isEnable")
    @Expose
    private String isEnable;

    @SerializedName(value = "Status", alternate = "status")
    @Expose
    private String status;

    @SerializedName(value = "IsSync", alternate = "isSync")
    @Expose
    private String isSync;

    @SerializedName(value = "IsFrom", alternate = "isFrom")
    @Expose
    private String isFrom;

    @SerializedName(value = "WildSearch", alternate = "wildSearch")
    @Expose
    private String wildSearch;

    @SerializedName(value = "Notes", alternate = "notes")
    @Expose
    private String notes;

    @SerializedName(value = "ContractType", alternate = "contractType")
    @Expose
    private String contractType;

    @SerializedName(value = "RateType", alternate = "rateType")
    @Expose
    private String rateType;

    @SerializedName(value = "ContractOriginStateId", alternate = "contractOriginStateId")
    @Expose
    private String contractOriginStateId;

    @SerializedName(value = "BillType", alternate = "billType")
    @Expose
    private String billType;

    @SerializedName(value = "BillToName", alternate = "billToName")
    @Expose
    private String billToName;

    @SerializedName(value = "BillToStateID", alternate = "billToStateID")
    @Expose
    private String billToStateID;

    @SerializedName(value = "BillToCityID", alternate = "billToCityID")
    @Expose
    private String billToCityID;

    @SerializedName(value = "BillToAdd1", alternate = "billToAdd1")
    @Expose
    private String billToAdd1;

    @SerializedName(value = "BillToAdd2", alternate = "billToAdd2")
    @Expose
    private String billToAdd2;

    @SerializedName(value = "BillToAdd3", alternate = "billToAdd3")
    @Expose
    private String billToAdd3;

    @SerializedName(value = "BillToPostCode", alternate = "billToPostCode")
    @Expose
    private String billToPostCode;

    @SerializedName(value = "BillSubmissionType", alternate = "billSubmissionType")
    @Expose
    private String billSubmissionType;

    @SerializedName(value = "ContractDocument", alternate = "contractDocument")
    @Expose
    private String contractDocument;

    @SerializedName(value = "BillGenerationType", alternate = "billGenerationType")
    @Expose
    private String billGenerationType;

    @SerializedName(value = "BillGenName", alternate = "billGenName")
    @Expose
    private String billGenName;

    @SerializedName(value = "BillGenStateID", alternate = "billGenStateID")
    @Expose
    private String billGenStateID;

    @SerializedName(value = "BillGenCityID", alternate = "billGenCityID")
    @Expose
    private String billGenCityID;

    @SerializedName(value = "BillGenAdd1", alternate = "billGenAdd1")
    @Expose
    private String billGenAdd1;

    @SerializedName(value = "BillGenAdd2", alternate = "billGenAdd2")
    @Expose
    private String billGenAdd2;

    @SerializedName(value = "BillGenAdd3", alternate = "billGenAdd3")
    @Expose
    private String billGenAdd3;

    @SerializedName(value = "BillGenPostCode", alternate = "billGenPostCode")
    @Expose
    private String billGenPostCode;

    @SerializedName(value = "PaymentCollectionType", alternate = "paymentCollectionType")
    @Expose
    private String paymentCollectionType;

    @SerializedName(value = "PaymentColName", alternate = "paymentColName")
    @Expose
    private String paymentColName;

    @SerializedName(value = "PaymentColStateID", alternate = "paymentColStateID")
    @Expose
    private String paymentColStateID;

    @SerializedName(value = "PaymentColCityID", alternate = "paymentColCityID")
    @Expose
    private String paymentColCityID;

    @SerializedName(value = "PaymentColAdd1", alternate = "paymentColAdd1")
    @Expose
    private String paymentColAdd1;

    @SerializedName(value = "PaymentColAdd2", alternate = "paymentColAdd2")
    @Expose
    private String paymentColAdd2;

    @SerializedName(value = "PaymentColAdd3", alternate = "paymentColAdd3")
    @Expose
    private String paymentColAdd3;

    @SerializedName(value = "PaymentColPostCode", alternate = "paymentColPostCode")
    @Expose
    private String paymentColPostCode;

    public Double getCFT() {
        return CFT;
    }

    public void setCFT(Double CFT) {
        this.CFT = CFT;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAccountGroupId() {
        return accountGroupId;
    }

    public void setAccountGroupId(String accountGroupId) {
        this.accountGroupId = accountGroupId;
    }

    public String getParentCustomerId() {
        return parentCustomerId;
    }

    public void setParentCustomerId(String parentCustomerId) {
        this.parentCustomerId = parentCustomerId;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmails() {
        return emails;
    }

    public void setEmails(String emails) {
        this.emails = emails;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContractSignedBy() {
        return contractSignedBy;
    }

    public void setContractSignedBy(String contractSignedBy) {
        this.contractSignedBy = contractSignedBy;
    }

    public String getContractApproveBy() {
        return contractApproveBy;
    }

    public void setContractApproveBy(String contractApproveBy) {
        this.contractApproveBy = contractApproveBy;
    }

    public String getContractOriginId() {
        return contractOriginId;
    }

    public void setContractOriginId(String contractOriginId) {
        this.contractOriginId = contractOriginId;
    }

    public String getCreditDays() {
        return creditDays;
    }

    public void setCreditDays(String creditDays) {
        this.creditDays = creditDays;
    }

    public String getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(String interestRate) {
        this.interestRate = interestRate;
    }

    public String getIsTDS() {
        return isTDS;
    }

    public void setIsTDS(String isTDS) {
        this.isTDS = isTDS;
    }

    public String gettDSPercentage() {
        return tDSPercentage;
    }

    public void settDSPercentage(String tDSPercentage) {
        this.tDSPercentage = tDSPercentage;
    }

    public String gettDSDocument() {
        return tDSDocument;
    }

    public void settDSDocument(String tDSDocument) {
        this.tDSDocument = tDSDocument;
    }

    public String gettANNo() {
        return tANNo;
    }

    public void settANNo(String tANNo) {
        this.tANNo = tANNo;
    }

    public String getpANNo() {
        return pANNo;
    }

    public void setpANNo(String pANNo) {
        this.pANNo = pANNo;
    }

    public String gettINNo() {
        return tINNo;
    }

    public void settINNo(String tINNo) {
        this.tINNo = tINNo;
    }

    public String getIsBlock() {
        return isBlock;
    }

    public void setIsBlock(String isBlock) {
        this.isBlock = isBlock;
    }

    public String getBlockRemarks() {
        return blockRemarks;
    }

    public void setBlockRemarks(String blockRemarks) {
        this.blockRemarks = blockRemarks;
    }

    public String getCodeOpeningDocument() {
        return codeOpeningDocument;
    }

    public void setCodeOpeningDocument(String codeOpeningDocument) {
        this.codeOpeningDocument = codeOpeningDocument;
    }

    public String getkYCDocument() {
        return kYCDocument;
    }

    public void setkYCDocument(String kYCDocument) {
        this.kYCDocument = kYCDocument;
    }

    public String getVerticleId() {
        return verticleId;
    }

    public void setVerticleId(String verticleId) {
        this.verticleId = verticleId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPackingTypeId() {
        return packingTypeId;
    }

    public void setPackingTypeId(String packingTypeId) {
        this.packingTypeId = packingTypeId;
    }

    public String getRiskType() {
        return riskType;
    }

    public void setRiskType(String riskType) {
        this.riskType = riskType;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getiFSCCode() {
        return iFSCCode;
    }

    public void setiFSCCode(String iFSCCode) {
        this.iFSCCode = iFSCCode;
    }

    public String getmICRCode() {
        return mICRCode;
    }

    public void setmICRCode(String mICRCode) {
        this.mICRCode = mICRCode;
    }

    public String getaCNo() {
        return aCNo;
    }

    public void setaCNo(String aCNo) {
        this.aCNo = aCNo;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchAddress() {
        return branchAddress;
    }

    public void setBranchAddress(String branchAddress) {
        this.branchAddress = branchAddress;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getInsCompany() {
        return insCompany;
    }

    public void setInsCompany(String insCompany) {
        this.insCompany = insCompany;
    }

    public String getPolicyNo() {
        return policyNo;
    }

    public void setPolicyNo(String policyNo) {
        this.policyNo = policyNo;
    }

    public String getPolicyAmount() {
        return policyAmount;
    }

    public void setPolicyAmount(String policyAmount) {
        this.policyAmount = policyAmount;
    }

    public String getPolicyStartDate() {
        return policyStartDate;
    }

    public void setPolicyStartDate(String policyStartDate) {
        this.policyStartDate = policyStartDate;
    }

    public String getPolicyExpiryDate() {
        return policyExpiryDate;
    }

    public void setPolicyExpiryDate(String policyExpiryDate) {
        this.policyExpiryDate = policyExpiryDate;
    }

    public String getRisk() {
        return risk;
    }

    public void setRisk(String risk) {
        this.risk = risk;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    public String getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(String isEnable) {
        this.isEnable = isEnable;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIsSync() {
        return isSync;
    }

    public void setIsSync(String isSync) {
        this.isSync = isSync;
    }

    public String getIsFrom() {
        return isFrom;
    }

    public void setIsFrom(String isFrom) {
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

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getRateType() {
        return rateType;
    }

    public void setRateType(String rateType) {
        this.rateType = rateType;
    }

    public String getContractOriginStateId() {
        return contractOriginStateId;
    }

    public void setContractOriginStateId(String contractOriginStateId) {
        this.contractOriginStateId = contractOriginStateId;
    }

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    public String getBillToName() {
        return billToName;
    }

    public void setBillToName(String billToName) {
        this.billToName = billToName;
    }

    public String getBillToStateID() {
        return billToStateID;
    }

    public void setBillToStateID(String billToStateID) {
        this.billToStateID = billToStateID;
    }

    public String getBillToCityID() {
        return billToCityID;
    }

    public void setBillToCityID(String billToCityID) {
        this.billToCityID = billToCityID;
    }

    public String getBillToAdd1() {
        return billToAdd1;
    }

    public void setBillToAdd1(String billToAdd1) {
        this.billToAdd1 = billToAdd1;
    }

    public String getBillToAdd2() {
        return billToAdd2;
    }

    public void setBillToAdd2(String billToAdd2) {
        this.billToAdd2 = billToAdd2;
    }

    public String getBillToAdd3() {
        return billToAdd3;
    }

    public void setBillToAdd3(String billToAdd3) {
        this.billToAdd3 = billToAdd3;
    }

    public String getBillToPostCode() {
        return billToPostCode;
    }

    public void setBillToPostCode(String billToPostCode) {
        this.billToPostCode = billToPostCode;
    }

    public String getBillSubmissionType() {
        return billSubmissionType;
    }

    public void setBillSubmissionType(String billSubmissionType) {
        this.billSubmissionType = billSubmissionType;
    }

    public String getContractDocument() {
        return contractDocument;
    }

    public void setContractDocument(String contractDocument) {
        this.contractDocument = contractDocument;
    }

    public String getBillGenerationType() {
        return billGenerationType;
    }

    public void setBillGenerationType(String billGenerationType) {
        this.billGenerationType = billGenerationType;
    }

    public String getBillGenName() {
        return billGenName;
    }

    public void setBillGenName(String billGenName) {
        this.billGenName = billGenName;
    }

    public String getBillGenStateID() {
        return billGenStateID;
    }

    public void setBillGenStateID(String billGenStateID) {
        this.billGenStateID = billGenStateID;
    }

    public String getBillGenCityID() {
        return billGenCityID;
    }

    public void setBillGenCityID(String billGenCityID) {
        this.billGenCityID = billGenCityID;
    }

    public String getBillGenAdd1() {
        return billGenAdd1;
    }

    public void setBillGenAdd1(String billGenAdd1) {
        this.billGenAdd1 = billGenAdd1;
    }

    public String getBillGenAdd2() {
        return billGenAdd2;
    }

    public void setBillGenAdd2(String billGenAdd2) {
        this.billGenAdd2 = billGenAdd2;
    }

    public String getBillGenAdd3() {
        return billGenAdd3;
    }

    public void setBillGenAdd3(String billGenAdd3) {
        this.billGenAdd3 = billGenAdd3;
    }

    public String getBillGenPostCode() {
        return billGenPostCode;
    }

    public void setBillGenPostCode(String billGenPostCode) {
        this.billGenPostCode = billGenPostCode;
    }

    public String getPaymentCollectionType() {
        return paymentCollectionType;
    }

    public void setPaymentCollectionType(String paymentCollectionType) {
        this.paymentCollectionType = paymentCollectionType;
    }

    public String getPaymentColName() {
        return paymentColName;
    }

    public void setPaymentColName(String paymentColName) {
        this.paymentColName = paymentColName;
    }

    public String getPaymentColStateID() {
        return paymentColStateID;
    }

    public void setPaymentColStateID(String paymentColStateID) {
        this.paymentColStateID = paymentColStateID;
    }

    public String getPaymentColCityID() {
        return paymentColCityID;
    }

    public void setPaymentColCityID(String paymentColCityID) {
        this.paymentColCityID = paymentColCityID;
    }

    public String getPaymentColAdd1() {
        return paymentColAdd1;
    }

    public void setPaymentColAdd1(String paymentColAdd1) {
        this.paymentColAdd1 = paymentColAdd1;
    }

    public String getPaymentColAdd2() {
        return paymentColAdd2;
    }

    public void setPaymentColAdd2(String paymentColAdd2) {
        this.paymentColAdd2 = paymentColAdd2;
    }

    public String getPaymentColAdd3() {
        return paymentColAdd3;
    }

    public void setPaymentColAdd3(String paymentColAdd3) {
        this.paymentColAdd3 = paymentColAdd3;
    }

    public String getPaymentColPostCode() {
        return paymentColPostCode;
    }

    public void setPaymentColPostCode(String paymentColPostCode) {
        this.paymentColPostCode = paymentColPostCode;
    }
}
