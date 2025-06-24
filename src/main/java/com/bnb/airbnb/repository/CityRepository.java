package com.bnb.airbnb.repository;

import com.bnb.airbnb.entity.AppUser;
import com.bnb.airbnb.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long>{
}
