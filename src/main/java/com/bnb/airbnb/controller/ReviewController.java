package com.bnb.airbnb.controller;

import com.bnb.airbnb.entity.AppUser;
import com.bnb.airbnb.entity.Property;
import com.bnb.airbnb.entity.Review;
import com.bnb.airbnb.repository.PropertyRepository;
import com.bnb.airbnb.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/review")
public class ReviewController {
    private ReviewRepository reviewRepository;
    private PropertyRepository propertyRepository;
@Autowired
    public ReviewController(ReviewRepository reviewRepository, PropertyRepository propertyRepository) {
        this.reviewRepository = reviewRepository;
        this.propertyRepository = propertyRepository;
    }

    @Transactional
    @PostMapping("/createreview")
    public ResponseEntity<?> addReview(@RequestBody Review review,
                                            @AuthenticationPrincipal AppUser user,//after login when calling review with jwt token user details go to this object
                                            @RequestParam long propertyId

    ){
        Optional<Property> byId = propertyRepository.findById(propertyId);
       Property property = byId.get();
        System.out.println(property);
        Review byUserAndProperty = reviewRepository.findByUserAndProperty(user, property);
       if(byUserAndProperty!=null){
          return new ResponseEntity<>("Review Exist",HttpStatus.CREATED);//if exist go back
       }

        review.setAppUser(user);//user details automatically coming here
       review.setProperty(property);//property details automatically coming here
        System.out.println(user.getName());
        System.out.println(property.getName());
        System.out.println(review.getRating());
        System.out.println(review.getDescription());
       // Review save = reviewRepository.save(review);
        return new ResponseEntity<>(reviewRepository.save(review), HttpStatus.CREATED);
    }
    @GetMapping("/userreviews")
    public List<Review> reviewsofUser(@AuthenticationPrincipal AppUser user){
        List<Review> reviewByUser = reviewRepository.findReviewByUser(user);
        return reviewByUser;
    }
}
