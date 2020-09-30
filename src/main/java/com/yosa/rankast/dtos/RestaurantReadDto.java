package com.yosa.rankast.dtos;

public class RestaurantReadDto {
    public String title;
    public String description;
    public String address;
    public Float latitude;
    public Float longitude;

    public RestaurantReadDto() {
    }

    public RestaurantReadDto(String title, String description, String address, Float latitude, Float longitude) {
        this.title = title;
        this.description = description;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
