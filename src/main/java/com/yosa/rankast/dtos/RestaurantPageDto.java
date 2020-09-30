package com.yosa.rankast.dtos;

import java.util.List;

public class RestaurantPageDto {
    private List<RestaurantDto> restaurants;
    private Boolean hasNextPage;
    private Boolean hasPreviousPage;
    private Integer totalPages;
    private Integer pageNum;

    public RestaurantPageDto() {
    }

    public RestaurantPageDto(List<RestaurantDto> restaurants, Boolean hasNextPage, Boolean hasPreviousPage, Integer totalPages, Integer pageNum) {
        this.restaurants = restaurants;
        this.hasNextPage = hasNextPage;
        this.hasPreviousPage = hasPreviousPage;
        this.totalPages = totalPages;
        this.pageNum = pageNum;
    }

    public List<RestaurantDto> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<RestaurantDto> restaurants) {
        this.restaurants = restaurants;
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
