package com.yosa.rankast.controllers;

import com.yosa.rankast.domain.Account;
import com.yosa.rankast.domain.Restaurant;
import com.yosa.rankast.dtos.RestaurantDto;
import com.yosa.rankast.dtos.RestaurantPageDto;
import com.yosa.rankast.services.AccountService;
import com.yosa.rankast.services.RestaurantService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private AccountService accountService;

    private Logger logger = LoggerFactory.getLogger(RestaurantController.class);

    @GetMapping("")
    public RestaurantPageDto getRestaurants(@RequestParam(value = "page", defaultValue = "0") int page) {
        Page<Restaurant> restaurants = restaurantService.getAll(page);
        restaurants.getContent().forEach(e -> System.out.println(e.getTitle()));
        List<RestaurantDto> restaurantList = modelMapper.map(restaurants.getContent(), new TypeToken<List<RestaurantDto>>(){}.getType());
        restaurantList.forEach(e -> System.out.println(" __ " + e.getTitle()));

        RestaurantPageDto restaurantPageDto = new RestaurantPageDto(restaurantList, restaurants.hasNext(), restaurants.hasPrevious(), restaurants.getTotalPages(), page);
        logger.info("Returning all restaurants with page " + page);
        return restaurantPageDto;
    }

    @GetMapping("{id}")
    public RestaurantDto getRestaurant(@PathVariable("id") Long id) {
        Restaurant restaurant = restaurantService.getOne(id);
        logger.info("Returning restaurant with id = " + id);
        return modelMapper.map(restaurant, RestaurantDto.class);
    }

    @GetMapping("search")
    public RestaurantPageDto searchRestaurants(@RequestParam(value = "page", defaultValue = "0") int page, @RequestParam("q") String query){
        Page<Restaurant> restaurants = restaurantService.search(query, page);
        List<RestaurantDto> restaurantList = modelMapper.map(restaurants.getContent(), new TypeToken<List<RestaurantDto>>(){}.getType());
        RestaurantPageDto restaurantPageDto = new RestaurantPageDto(restaurantList, restaurants.hasNext(), restaurants.hasPrevious(), restaurants.getTotalPages(), page);
        logger.info("Searching restaurants with query = "  + query);
        return restaurantPageDto;
    }
}