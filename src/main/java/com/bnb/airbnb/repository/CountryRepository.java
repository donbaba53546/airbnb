package com.bnb.airbnb.repository;

import com.bnb.airbnb.entity.AppUser;
import com.bnb.airbnb.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
}
