package com.yosa.rankast.dtos;

import com.yosa.rankast.domain.RateType;

import java.util.Date;

public class FeedbackDto {
    private Long id;
    private String userFullName;
    private Long restaurant_id;
    private RateType rateType;
    private Date pubDate;
    private String text;

    public FeedbackDto() {
    }

    public FeedbackDto(Long id, String userFullName, Long restaurant_id, RateType rateType, Date pubDate, String text) {
        this.id = id;
        this.userFullName = userFullName;
        this.restaurant_id = restaurant_id;
        this.rateType = rateType;
        this.pubDate = pubDate;
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public Long getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(Long restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public RateType getRateType() {
        return rateType;
    }

    public void setRateType(RateType rateType) {
        this.rateType = rateType;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
