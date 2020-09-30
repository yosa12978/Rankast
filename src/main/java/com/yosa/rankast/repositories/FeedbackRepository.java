package com.yosa.rankast.repositories;

import com.yosa.rankast.domain.Feedback;
import com.yosa.rankast.domain.RateType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    Page<Feedback> findByRestaurantId(Long id, Pageable pageable);
    Optional<Feedback> findByRestaurantIdAndUserId(Long restaurantId, Long userId);

    @Modifying
    @Query("update Feedback f set f.text = :text where f.id = :id")
    void updateFeedback(@Param("id") Long id, @Param("text") String text);
}
