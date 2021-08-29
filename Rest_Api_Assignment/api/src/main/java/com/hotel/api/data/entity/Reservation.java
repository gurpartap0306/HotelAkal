package com.hotel.api.data.entity;

import javax.persistence.*;

@Entity
@Table(name = "RESERVATION")
public class Reservation {

    @Id
    @Column(name = "confirmation_number")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int confirmation_number;
    @Column(name = "hotel_id")
    private int hotel_id ;
    @Column(name = "checkin")
    private String checkin;
    @Column(name = "checkout")
    private String checkout;

    public int getConfirmation_number() {
        return confirmation_number;
    }

    public void setConfirmation_number(int confirmation_number) {
        this.confirmation_number = confirmation_number;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
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
}
