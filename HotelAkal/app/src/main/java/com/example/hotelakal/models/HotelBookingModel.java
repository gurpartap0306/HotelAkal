package com.example.hotelakal.models;

import java.util.List;

public class HotelBookingModel {

    private String hotel_name;
    private String checkin;
    private String checkout;
    private List<GuestDetails> guests_list;

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    public void setGuests_list(List<GuestDetails> guests_list) {
        this.guests_list = guests_list;
    }

    public String getHotel_name() {
        return hotel_name;
    }

    public String getCheckin() {
        return checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public List<GuestDetails> getGuests_list() {
        return guests_list;
    }
}
