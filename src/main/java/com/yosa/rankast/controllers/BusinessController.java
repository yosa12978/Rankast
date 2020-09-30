package com.yosa.rankast.controllers;

import com.yosa.rankast.domain.Restaurant;
import com.yosa.rankast.dtos.RestaurantDto;
import com.yosa.rankast.dtos.RestaurantReadDto;
import com.yosa.rankast.services.AccountService;
import com.yosa.rankast.services.RestaurantService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/business")
@PreAuthorize("hasRole('ROLE_OWNER')")
public class BusinessController {
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private AccountService accountService;

    private Logger logger = LoggerFactory.getLogger(BusinessController.class);

    @GetMapping("")
    public List<RestaurantDto> getRestaurants(Principal principal){
        List<Restaurant> restaurants = restaurantService.getOwnerRestaurants(principal.getName());
        logger.info("Returning restaurants for user which username is " + principal.getName());
        return modelMapper.map(restaurants, new TypeToken<List<RestaurantDto>>(){}.getType());
    }

    @PostMapping("register")
    public RestaurantDto createRestaurant(@RequestBody RestaurantReadDto restaurant){
        String principal = SecurityContextHolder.getContext().getAuthentication().getName();
        Restaurant requestedRestaurant = new Restaurant(restaurant.title,
                                                        restaurant.description,
                                                        restaurant.latitude,
                                                        restaurant.longitude,
                                                        restaurant.address,
                                                        accountService.getByUsername(principal),
                                                        new ArrayList<>()); // TODO: refactor
        Restaurant createdRestaurant = restaurantService.create(requestedRestaurant);
        logger.info("Creating restaurant for user = " + principal + " and title = " + restaurant.title);
        return modelMapper.map(createdRestaurant, RestaurantDto.class);
    }
}
