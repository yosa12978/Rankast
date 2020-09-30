package com.yosa.rankast.dtos;

import java.util.List;

public class FeedbackPageDto {
    private List<FeedbackDto> feedbacks;
    private Boolean hasNextPage;
    private Boolean hasPreviousPage;
    private Integer totalPages;
    private Integer pageNum;

    public FeedbackPageDto() {
    }

    public FeedbackPageDto(List<FeedbackDto> feedbacks, Boolean hasNextPage, Boolean hasPreviousPage, Integer totalPages, Integer pageNum) {
        this.feedbacks = feedbacks;
        this.hasNextPage = hasNextPage;
        this.hasPreviousPage = hasPreviousPage;
        this.totalPages = totalPages;
        this.pageNum = pageNum;
    }

    public List<FeedbackDto> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List<FeedbackDto> feedbacks) {
        this.feedbacks = feedbacks;
    }

    public Boolean getHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(Boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public Boolean getHasPreviousPage() {
        return hasPreviousPage;
    }

    public void setHasPreviousPage(Boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }
}
