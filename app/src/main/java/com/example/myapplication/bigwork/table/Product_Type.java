package com.example.myapplication.bigwork.table;

public class Product_Type {
    private Integer productTypeId;

    private String productTypeName;

    private String productTypePicUrl;

    public Integer getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
    }

    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName == null ? null : productTypeName.trim();
    }

    public String getProductTypePicUrl() {
        return productTypePicUrl;
    }

    public void setProductTypePicUrl(String productTypePicUrl) {
        this.productTypePicUrl = productTypePicUrl == null ? null : productTypePicUrl.trim();
    }
}