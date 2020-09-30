package com.yosa.rankast.repositories;

import com.yosa.rankast.domain.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    @Modifying
    @Query("update Restaurant r set r.title = :title, r.description = :desc, r.latitude = :latitude, r.longitude = :longitude, r.address = :address where r.id = :id")
    void updateRestaurant(@Param("id") Long id,
                          @Param("title") String title,
                          @Param("desc") String description,
                          @Param("latitude") Float latitude,
                          @Param("longitude") Float longitude,
                          @Param("address") String address);

    List<Restaurant> findByOwnerUsername(String username);
    Page<Restaurant> findByTitleContaining(String title, Pageable pageable);
}
