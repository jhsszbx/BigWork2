package com.example.myapplication.bigwork.table;

public class Order_Details {
    private Integer orderDetailsId;

    private Integer orderNumber;

    private Integer commodityId;

    private Integer quantityOfCommodities;

    public Integer getOrderDetailsId() {
        return orderDetailsId;
    }

    public void setOrderDetailsId(Integer orderDetailsId) {
        this.orderDetailsId = orderDetailsId;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public Integer getQuantityOfCommodities() {
        return quantityOfCommodities;
    }

    public void setQuantityOfCommodities(Integer quantityOfCommodities) {
        this.quantityOfCommodities = quantityOfCommodities;
    }
}