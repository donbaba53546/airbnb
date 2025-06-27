package com.bnb.airbnb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @Column(name = "no_of_guests")
    private Integer noOfGuests;

    @Column(name = "guest_name", nullable = false)
    private String guestName;

    @Column(name = "mobile", nullable = false, length = 14)
    private String mobile;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "type_of_room", nullable = false)
    private String typeOfRoom;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;

    @ManyToOne
    @JoinColumn(name = "app_user_id")
    private AppUser appUser;

    @Column(name = "total_price", nullable = false)
    private Float total_price;

    @Column(name = "total_nights", nullable = false)
    private Integer totalNights;

    @Column(name = "check_in_date", nullable = false)
    private LocalDate checkInDate;

    @Column(name = "check_out_date", nullable = false)
    private LocalDate checkOutDate;

    @Column(name = "rooms_booked", nullable = false)
    private Integer roomsBooked;

    public Integer getNoOfGuests() {
        return noOfGuests;
    }

    public void setNoOfGuests(Integer noOfGuests) {
        this.noOfGuests = noOfGuests;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getGuestName() {
        return guestName;
    }
    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }
    public String getTypeOfRoom() {
        return typeOfRoom;
    }
    public void setTypeOfRoom(String typeOfRoom) {
        this.typeOfRoom = typeOfRoom;
    }
    public Property getProperty() {
        return property;
    }
    public void setProperty(Property property) {
        this.property = property;
    }
    public AppUser getAppUser() {
        return appUser;
    }
    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }
    public Float getTotal_price() {
        return total_price;
    }
    public void setTotal_price(double total_price) {
        this.total_price = (float) total_price;
    }
    public Integer getTotalNights() {
        return totalNights;
    }
    public void setTotalNights(Integer totalNights) {
        this.totalNights = totalNights;
    }
    public LocalDate getCheckInDate() {
        return checkInDate;
    }
    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }
    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }
    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }
    public Integer getRoomsBooked() {
        return roomsBooked;
    }
    public void setRoomsBooked(Integer roomsBooked) {
        this.roomsBooked = roomsBooked;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }



}