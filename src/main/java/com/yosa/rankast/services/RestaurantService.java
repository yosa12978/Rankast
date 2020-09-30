package com.yosa.rankast.services;

import com.yosa.rankast.domain.Restaurant;
import com.yosa.rankast.exceptions.NotFoundException;
import com.yosa.rankast.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;

    public Page<Restaurant> getAll(int page){
        return restaurantRepository.findAll(PageRequest.of(page, 20, Sort.by(Sort.Direction.DESC, "id")));
    }

    public List<Restaurant> getOwnerRestaurants(String username){
        return restaurantRepository.findByOwnerUsername(username);
    }

    public Restaurant getOne(long restaurantId){
        return restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new NotFoundException("Restaurant not found."));
    }

    public Page<Restaurant> search(String query, int page){
        return restaurantRepository.findByTitleContaining(query,
                PageRequest.of(page, 20, Sort.by(Sort.Direction.DESC, "id")));
    }

    public Restaurant create(Restaurant restaurant){
        return restaurantRepository.save(restaurant);
    }

    public void putRestaurant(Restaurant restaurant){
        restaurantRepository.updateRestaurant(restaurant.getId(),
                                            restaurant.getTitle(),
                                            restaurant.getDescription(),
                                            restaurant.getLatitude(),
                                            restaurant.getLongitude(),
                                            restaurant.getAddress());
    }

    public void deleteRestaurant(Long restaurant_id){
        restaurantRepository.delete(
                restaurantRepository.findById(restaurant_id)
                        .orElseThrow(() -> new NotFoundException("Restaurant with id = " + restaurant_id +" doesn't exist.")));
    }

}
