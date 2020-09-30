package com.yosa.rankast.domain;


import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "restaurant")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull
    private String title;
    @NonNull
    private String description;
    @NonNull
    private Long rate = 0L;
    @NonNull
    private Long views = 0L;
    @NonNull
    private Float latitude;
    @NonNull
    private Float longitude;
    @NonNull
    private String address;
    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Account owner;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    private List<Feedback> feedbacks;
    @ElementCollection
    private List<String> images;


    public Restaurant() {
    }

    public Restaurant(@NonNull String title,
                      @NonNull String description,
                      @NonNull Float latitude,
                      @NonNull Float longitude,
                      @NonNull String address,
                      @NonNull Account owner,
                      List<String> images) {
        this.title = title;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.owner = owner;
        this.images = images;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    public void setDescription(@NonNull String description) {
        this.description = description;
    }

    @NonNull
    public Long getRate() {
        return rate;
    }

    public void setRate(@NonNull Long rate) {
        this.rate = rate;
    }

    @NonNull
    public Long getViews() {
        return views;
    }

    public void setViews(@NonNull Long views) {
        this.views = views;
    }

    @NonNull
    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(@NonNull Float latitude) {
        this.latitude = latitude;
    }

    @NonNull
    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(@NonNull Float longitude) {
        this.longitude = longitude;
    }

    @NonNull
    public String getAddress() {
        return address;
    }

    public void setAddress(@NonNull String address) {
        this.address = address;
    }

    @NonNull
    public Account getOwner() {
        return owner;
    }

    public void setOwner(@NonNull Account owner) {
        this.owner = owner;
    }

    public List<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
