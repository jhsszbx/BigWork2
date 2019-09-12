package com.example.myapplication.bigwork.table;

public class Logistics_Information {
    private String logisticsNumber;

    private String shippingMethod;

    private String logisticsCompany;

    private String waybillNumber;

    private String logisticsDetailed;

    public String getLogisticsNumber() {
        return logisticsNumber;
    }

    public void setLogisticsNumber(String logisticsNumber) {
        this.logisticsNumber = logisticsNumber == null ? null : logisticsNumber.trim();
    }

    public String getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(String shippingMethod) {
        this.shippingMethod = shippingMethod == null ? null : shippingMethod.trim();
    }

    public String getLogisticsCompany() {
        return logisticsCompany;
    }

    public void setLogisticsCompany(String logisticsCompany) {
        this.logisticsCompany = logisticsCompany == null ? null : logisticsCompany.trim();
    }

    public String getWaybillNumber() {
        return waybillNumber;
    }

    public void setWaybillNumber(String waybillNumber) {
        this.waybillNumber = waybillNumber == null ? null : waybillNumber.trim();
    }

    public String getLogisticsDetailed() {
        return logisticsDetailed;
    }

    public void setLogisticsDetailed(String logisticsDetailed) {
        this.logisticsDetailed = logisticsDetailed == null ? null : logisticsDetailed.trim();
    }
}