package com.example.myapplication.bigwork.table;

public class Address {
    private Integer addressId;

    private Integer userId;

    private String consignee;

    private String phoneNumber;

    private String provinceAndCityArea;

    private String detailedAddress;

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee == null ? null : consignee.trim();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
    }

    public String getProvinceAndCityArea() {
        return provinceAndCityArea;
    }

    public void setProvinceAndCityArea(String provinceAndCityArea) {
        this.provinceAndCityArea = provinceAndCityArea == null ? null : provinceAndCityArea.trim();
    }

    public String getDetailedAddress() {
        return detailedAddress;
    }

    public void setDetailedAddress(String detailedAddress) {
        this.detailedAddress = detailedAddress == null ? null : detailedAddress.trim();
    }
}