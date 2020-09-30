package com.yosa.rankast.controllers;

import com.yosa.rankast.domain.Account;
import com.yosa.rankast.domain.Feedback;
import com.yosa.rankast.domain.Restaurant;
import com.yosa.rankast.dtos.FeedbackDto;
import com.yosa.rankast.dtos.FeedbackPageDto;
import com.yosa.rankast.dtos.FeedbackReadDto;
import com.yosa.rankast.services.AccountService;
import com.yosa.rankast.services.FeedbackService;
import com.yosa.rankast.services.RestaurantService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/feedbacks")
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private ModelMapper modelMapper;

    private Logger logger = LoggerFactory.getLogger(FeedbackController.class);

    @GetMapping("{id}")
    public FeedbackPageDto getFeedbacks(@PathVariable("id") Long restaurant_id, @RequestParam(value = "page", defaultValue = "0") int page) {
        Page<Feedback> feedbacks = feedbackService.getFeedbacks(restaurant_id, page);
        List<FeedbackDto> feedbackList = new ArrayList<FeedbackDto>();
        feedbacks.forEach(e -> feedbackList.add(
                new FeedbackDto(e.getId(), e.getUser().getFullName(), e.getRestaurant().getId(), e.getRate(), e.getPubDate(), e.getText())));
        FeedbackPageDto feedbackPageDto = new FeedbackPageDto(feedbackList, feedbacks.hasNext(), feedbacks.hasPrevious(), feedbacks.getTotalPages(), page);
        logger.info("Returning feedbacks for restaurant id = " + restaurant_id);
        return feedbackPageDto;
    }

    @PostMapping("{id}")
    public FeedbackDto createFeedback(@PathVariable("id") Long restaurant_id, @RequestBody FeedbackReadDto feedback, Principal principal){
        Restaurant restaurant = restaurantService.getOne(restaurant_id);
        Account user = accountService.getByUsername(principal.getName());
        Feedback createdFeedback = new Feedback(feedback.text, feedback.rateType, restaurant, user);
        Feedback resFeedback =  feedbackService.createFeedback(createdFeedback);
        logger.info("Created feedback for restaurant id = " + restaurant_id + " with self id = " + resFeedback.getId());
        return modelMapper.map(resFeedback, FeedbackDto.class);
    }
}
