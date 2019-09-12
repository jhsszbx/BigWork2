package com.example.myapplication.bigwork.table;

import java.util.List;

public class Goods {

    /**
     * commodityId : 201812006
     * brandId : 201812021
     * productTypeId : 201812001
     * tradeName : 高腰灯芯绒前排扣迷你裙华为
     * specifications : s/m/l
     * originalPrice : 199
     * discountPrice : 149
     * stockNumber : 100
     * commodityDescription : 优雅的灯芯绒裙子!紧身的设计，适合搭配叠穿。
     采用具有适度凹凸感的灯芯绒面料制成，优雅又不失温暖感。
     前排扣的设计，洋溢着复古气息。
     适合与针织衫搭配，打造具有季节感的休闲造型。
     短裙的设计，适合与连裤袜、紧身裤搭配叠穿。

     [面料组成]棉99%,氨纶1%。
     [洗涤信息]机洗
     * flag : -1
     * picId : 201812026
     * productPicUrl : img\product\201812026.jpg
     */

    private int commodityId;
    private int brandId;
    private int productTypeId;
    private String tradeName;
    private String specifications;
    private int originalPrice;
    private int discountPrice;
    private int stockNumber;
    private String commodityDescription;
    private int flag;
    private int picId;
    private String productPicUrl;

    public int getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public int getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(int productTypeId) {
        this.productTypeId = productTypeId;
    }

    public String getTradeName() {
        return tradeName;
    }

    public void setTradeName(String tradeName) {
        this.tradeName = tradeName;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public int getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(int originalPrice) {
        this.originalPrice = originalPrice;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(int discountPrice) {
        this.discountPrice = discountPrice;
    }

    public int getStockNumber() {
        return stockNumber;
    }

    public void setStockNumber(int stockNumber) {
        this.stockNumber = stockNumber;
    }

    public String getCommodityDescription() {
        return commodityDescription;
    }

    public void setCommodityDescription(String commodityDescription) {
        this.commodityDescription = commodityDescription;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getPicId() {
        return picId;
    }

    public void setPicId(int picId) {
        this.picId = picId;
    }

    public String getProductPicUrl() {
        return productPicUrl;
    }

    public void setProductPicUrl(String productPicUrl) {
        this.productPicUrl = productPicUrl;
    }
}
