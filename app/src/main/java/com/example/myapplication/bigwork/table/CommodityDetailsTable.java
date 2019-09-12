package com.example.myapplication.bigwork.table;

public class CommodityDetailsTable {
    private String firshName;
    private String finalName;

    public CommodityDetailsTable(String firshName, String finalName) {
        this.firshName = firshName;
        this.finalName = finalName;
    }

    public String getFirshName() {
        return firshName;
    }

    public void setFirshName(String firshName) {
        this.firshName = firshName;
    }

    public String getFinalName() {
        return finalName;
    }

    public void setFinalName(String finalName) {
        this.finalName = finalName;
    }
}
