package com.yosa.rankast.domain;

import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "feedback")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull
    private String text;
    @NonNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date pubDate;
    @NonNull
    private RateType rate;
    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;
    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Account user;

    public Feedback() {
    }

    public Feedback(@NonNull String text, @NonNull RateType rate, @NonNull Restaurant restaurant, @NonNull Account user) {
        this.text = text;
        this.rate = rate;
        this.restaurant = restaurant;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NonNull
    public String getText() {
        return text;
    }

    public void setText(@NonNull String text) {
        this.text = text;
    }

    @NonNull
    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(@NonNull Date pubDate) {
        this.pubDate = pubDate;
    }

    @NonNull
    public RateType getRate() {
        return rate;
    }

    public void setRate(@NonNull RateType rate) {
        this.rate = rate;
    }

    @NonNull
    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(@NonNull Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @NonNull
    public Account getUser() {
        return user;
    }

    public void setUser(@NonNull Account user) {
        this.user = user;
    }
}
