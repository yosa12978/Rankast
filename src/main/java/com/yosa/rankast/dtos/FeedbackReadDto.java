package com.yosa.rankast.dtos;

import com.yosa.rankast.domain.RateType;

public class FeedbackReadDto {
    public RateType rateType;
    public String text;
    public Long restaurant_id;

    public FeedbackReadDto() {
    }

    public FeedbackReadDto(RateType rateType, String text, Long restaurant_id) {
        this.rateType = rateType;
        this.text = text;
        this.restaurant_id = restaurant_id;
    }
}
