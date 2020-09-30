package com.yosa.rankast.services;

import com.yosa.rankast.domain.Feedback;
import com.yosa.rankast.domain.RateType;
import com.yosa.rankast.domain.Restaurant;
import com.yosa.rankast.exceptions.BadRequestException;
import com.yosa.rankast.exceptions.NotFoundException;
import com.yosa.rankast.repositories.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FeedbackService {
    @Autowired
    private FeedbackRepository feedbackRepository;
    @Autowired
    private RestaurantService restaurantService;

    public Page<Feedback> getFeedbacks(Long restaurant_id, int page){
        return feedbackRepository.findByRestaurantId(restaurant_id, PageRequest.of(page, 20, Sort.by(Sort.Direction.DESC, "id")));
    }

    public Feedback createFeedback(Feedback feedback){
        Optional<Feedback> feedbackOpt = feedbackRepository.findByRestaurantIdAndUserId(feedback.getRestaurant().getId(), feedback.getUser().getId());
        if(feedbackOpt.isPresent())
            throw new BadRequestException("you have already leave feedback");
        Restaurant restaurant = restaurantService.getOne(feedback.getRestaurant().getId());
        restaurant.setRate(restaurant.getRate()+feedback.getRate().getRate());
        return feedbackRepository.save(feedback);
    }

    public void deleteFeedback(Long feedback_id){
        feedbackRepository.delete(
                feedbackRepository.findById(feedback_id)
                        .orElseThrow(() -> new NotFoundException("Feedback with id = " + feedback_id + " not found.")));
    }

    public void updateFeedback(Long feedback_id, String text){
        feedbackRepository.updateFeedback(feedback_id, text);
    }

}