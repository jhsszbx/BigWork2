package com.example.myapplication.bigwork.table;

public class Product_Pic {
    private Integer picId;

    private Integer commodityId;

    private String productPicUrl;

    public Integer getPicId() {
        return picId;
    }

    public void setPicId(Integer picId) {
        this.picId = picId;
    }

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public String getProductPicUrl() {
        return productPicUrl;
    }

    public void setProductPicUrl(String productPicUrl) {
        this.productPicUrl = productPicUrl == null ? null : productPicUrl.trim();
    }
}