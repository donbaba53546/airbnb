package com.bnb.airbnb.repository;

import com.bnb.airbnb.entity.Property;
import com.bnb.airbnb.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface RoomRepository extends JpaRepository<Room, Long> {


    @Query("SELECT r FROM Room r WHERE r.property.id = :propertyId AND r.type =:type AND r.date=:date")

    Room findByPropertyIdAndTypeAndDate(
            @Param("propertyId") long propertyId,
            @Param("type") String type,
            @Param("date") LocalDate date
    );
}