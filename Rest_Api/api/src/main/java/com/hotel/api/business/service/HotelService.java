package com.hotel.api.business.service;

import com.hotel.api.business.domain.Confirmation;
import com.hotel.api.business.domain.HotelList;
import com.hotel.api.business.domain.HotelReservation;
import com.hotel.api.data.entity.Guests;
import com.hotel.api.data.entity.Hotels;
import com.hotel.api.data.entity.Reservation;
import com.hotel.api.data.repository.GuestsRepository;
import com.hotel.api.data.repository.HotelsRepository;
import com.hotel.api.data.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {

    @Autowired
    GuestsRepository guests_repository;
    @Autowired
    HotelsRepository hotels_repository;
    @Autowired
    ReservationRepository reservation_repository;

    public HotelList getHotels(){
        List<Hotels> hotels = (List<Hotels>) hotels_repository.findAll();
        HotelList hotelList=new HotelList();
        hotelList.setHotelsList(hotels);
        return hotelList;
    }

    public Confirmation createReservation(HotelReservation hotelReservation){

        Reservation reservation = new Reservation();
        Confirmation confirmation = new Confirmation();
        List<Hotels> hotel = (List<Hotels>) hotels_repository.findHotelsByHotelName(hotelReservation.getHotel_name());
        if(hotel.isEmpty()){
            //Confirmation confirmation = new Confirmation();
            confirmation.setConfirmation_number("Not available. Hotel does not exist");
            return confirmation;
        }

        if (hotel.get(0).getAvailability() == false){
            //Confirmation confirmation = new Confirmation();
            confirmation.setConfirmation_number("Not available. Room not available.");
            return confirmation;
        }

        int hotel_id = hotel.get(0).id();
        reservation.setHotel_id(hotel_id);
        reservation.setCheckin(hotelReservation.getCheckin());
        reservation.setCheckout(hotelReservation.getCheckout());
        Reservation save = reservation_repository.save(reservation);

        List<Guests> guests =hotelReservation.getGuests_list();
        guests.forEach(guest ->{
            guest.setConfirmation_number(save.getConfirmation_number());
            guests_repository.save(guest);
        });

        //Confirmation confirmation = new Confirmation();
        confirmation.setConfirmation_number(Integer.toString(save.getConfirmation_number()));

        return confirmation;
    }

}
