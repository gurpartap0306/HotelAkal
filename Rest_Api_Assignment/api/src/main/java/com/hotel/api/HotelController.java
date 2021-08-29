package com.hotel.api;

import com.hotel.api.business.domain.Confirmation;
import com.hotel.api.business.domain.HotelList;
import com.hotel.api.business.domain.HotelReservation;
import com.hotel.api.business.service.HotelService;
import com.hotel.api.data.entity.Hotels;
import com.hotel.api.data.entity.Reservation;
import com.hotel.api.data.repository.HotelsRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @RequestMapping("/")
    public String index(){
        Map<String, Object> claims = new HashMap<>();
        String SECRET_KEY = "hotel-api-key";
        return "token for api is: " + Jwts.builder().setClaims(claims).setSubject("Client").signWith(SignatureAlgorithm.HS256,SECRET_KEY).compact();
    }

    @RequestMapping("/getListOfHotels")
    public HotelList getHotels(){
            return hotelService.getHotels();
        }

    @RequestMapping(path="/reservationConfirmation", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Confirmation createReservation(@RequestBody HotelReservation hotelReservation){

        return hotelService.createReservation(hotelReservation);
        }

}
