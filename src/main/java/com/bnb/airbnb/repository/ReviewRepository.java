package com.bnb.airbnb.repository;

import com.bnb.airbnb.entity.AppUser;
import com.bnb.airbnb.entity.Property;
import com.bnb.airbnb.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
   //one user can give review once for one property

    @Query("select r from Review r where r.appUser=:user and r.property=:property")
    Review findByUserAndProperty(@Param("user") AppUser user,@Param("property") Property property);
@Query("select r from Review r where r.appUser=:user")
  List<Review> findReviewByUser(
          @Param("user") AppUser user
  );
}