package com.example.myapplication.bigwork.table;

public class Brand {
    private Integer brandId;

    private String brandName;

    private String brandPicUrl;

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName == null ? null : brandName.trim();
    }

    public String getBrandPicUrl() {
        return brandPicUrl;
    }

    public void setBrandPicUrl(String brandPicUrl) {
        this.brandPicUrl = brandPicUrl == null ? null : brandPicUrl.trim();
    }
}