package com.bnb.airbnb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "property")
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "number_of_guests", nullable = false)
    private int numberofGuests;

    @Column(name = "number_of_beds", nullable = false)
    private int numberofBeds;

    @Column(name = "number_of_bathrooms", nullable = false)
    private int numberofBathrooms;

    @Column(name = "number_of_bedrooms", nullable = false)
    private int numberofBedrooms;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberofBeds() {
        return numberofBeds;
    }

    public void setNumberofBeds(int numberofBeds) {
        this.numberofBeds = numberofBeds;
    }

    public int getNumberofGuests() {
        return numberofGuests;
    }

    public void setNumberofGuests(int numberofGuests) {
        this.numberofGuests = numberofGuests;
    }
    public int getNumberofBathrooms() {
        return numberofBathrooms;
    }

    public void setNumberofBathrooms(int numberofBathrooms) {
        this.numberofBathrooms = numberofBathrooms;

    }
    public int getNumberofBedrooms() {
        return numberofBedrooms;
    }
    public void setNumberofBedrooms(int numberofBedrooms) {
        this.numberofBedrooms = numberofBedrooms;
    }
    public Country getCountry() {
        return country;
    }
    public void setCountry(Country country) {
        this.country = country;
    }
    public City getCity() {
        return city;
    }
    public void setCity(City city) {
        this.city = city;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }


}