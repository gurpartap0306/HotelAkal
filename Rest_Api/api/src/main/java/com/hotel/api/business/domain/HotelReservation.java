package com.hotel.api.business.domain;

import com.hotel.api.data.entity.Guests;

import java.util.List;

public class HotelReservation {

    String hotel_name;
    String checkin;
    String checkout;
    List<Guests> guests_list;

    public String getHotel_name() {
        return hotel_name;
    }

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    public List<Guests> getGuests_list() {
        return guests_list;
    }

    public void setGuests_list(List<Guests> guests_list) {
        this.guests_list = guests_list;
    }
}
