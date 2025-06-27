package com.bnb.airbnb.controller;

import com.bnb.airbnb.entity.AppUser;
import com.bnb.airbnb.entity.Booking;
import com.bnb.airbnb.entity.Property;
import com.bnb.airbnb.entity.Room;
import com.bnb.airbnb.repository.BookingRepository;
import com.bnb.airbnb.repository.PropertyRepository;
import com.bnb.airbnb.repository.RoomRepository;
import com.bnb.airbnb.service.PdfService;
import com.bnb.airbnb.service.SmsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/booking")
public class BookingController {
    private PropertyRepository propertyRepository;
    private RoomRepository roomRepository;
    private BookingRepository bookingRepository;
    private PdfService pdfService;
    private SmsService smsService;

    public BookingController(PropertyRepository propertyRepository, RoomRepository roomRepository, BookingRepository bookingRepository, PdfService pdfService, SmsService smsService) {
        this.propertyRepository = propertyRepository;
        this.roomRepository = roomRepository;
        this.bookingRepository = bookingRepository;
        this.pdfService = pdfService;
        this.smsService = smsService;
    }
    @PostMapping("/createBooking")
    public ResponseEntity<?> createBooking(
            @RequestParam long propertyId,
            @RequestParam String type,
            @RequestBody Booking booking,
            //  @RequestParam int numberOfRooms,
            @AuthenticationPrincipal AppUser user
    ){

        Property property = propertyRepository.findById(propertyId).get();
        List<LocalDate> datesBetween = getDatesBetween(booking.getCheckInDate(), booking.getCheckOutDate());
        List<Room> rooms=new ArrayList<>();
        for (LocalDate date : datesBetween) {
            Room room = roomRepository.findByPropertyIdAndTypeAndDate(propertyId, type,date);
            System.out.println("Fetched room: " + room);

            if (room == null || room.getCount() == 0) {
//                rooms.removeAll((Collection<?>) room);
                return new ResponseEntity<>("No Rooms Available ", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            rooms.add(room);

        }
        double total=0;
        for(Room room:rooms) {
        total=total+room.getPrice();
        }
        System.out.println(total);
        booking.setTotal_price(total);
        booking.setProperty(property);
        booking.setAppUser(user);
        booking.setTypeOfRoom(type);
        Booking save = bookingRepository.save(booking);


        if(save!=null) {
            for (Room room : rooms) {
                Integer count = room.getCount();
                room.setCount(count - 1);
                roomRepository.save(room);
            }
        }
        pdfService.generatePdf(save);
        smsService.sendSms(booking.getMobile(), "your booking is conformed");
        return new ResponseEntity<>(save,HttpStatus.CREATED);
    }
    public List<LocalDate> getDatesBetween(LocalDate startDate, LocalDate endDate) {
        List<LocalDate> dates = new ArrayList<>();
        LocalDate currentDate = startDate;
        while(!currentDate.isAfter(endDate)){
            dates.add(currentDate);
            currentDate = currentDate.plusDays(1);
        }
        return dates;
    }
}
