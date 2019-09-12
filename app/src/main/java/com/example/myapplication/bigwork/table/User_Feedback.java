package com.example.myapplication.bigwork.table;

public class User_Feedback {
    private Integer userFeedbackId;

    private Integer commodityId;

    private Integer userId;

    private String contactInformation;

    private String feedbackContent;

    private Double starClass;

    public Integer getUserFeedbackId() {
        return userFeedbackId;
    }

    public void setUserFeedbackId(Integer userFeedbackId) {
        this.userFeedbackId = userFeedbackId;
    }

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation == null ? null : contactInformation.trim();
    }

    public String getFeedbackContent() {
        return feedbackContent;
    }

    public void setFeedbackContent(String feedbackContent) {
        this.feedbackContent = feedbackContent == null ? null : feedbackContent.trim();
    }

    public Double getStarClass() {
        return starClass;
    }

    public void setStarClass(Double starClass) {
        this.starClass = starClass;
    }
}