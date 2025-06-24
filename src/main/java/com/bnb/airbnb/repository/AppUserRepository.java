package com.bnb.airbnb.repository;

import com.bnb.airbnb.entity.AppUser;

import com.bnb.airbnb.payload.LoginDto;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByEmail(String email);
    Optional<AppUser>findByName(String name);
//    Optional<AppUser>findByUsername(String name);
}
