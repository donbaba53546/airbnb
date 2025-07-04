package com.bnb.airbnb.controller;

import com.bnb.airbnb.entity.Property;
import com.bnb.airbnb.repository.PropertyRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/property")
public class PropertyController {

    private final PropertyRepository propertyRepository;

    public PropertyController(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    @GetMapping("/getpropertydetails")
    public List<Property> searchProperty(
    @RequestParam("city") String cityName)
    {
      return   propertyRepository.searchProperty(cityName);

    }
}
