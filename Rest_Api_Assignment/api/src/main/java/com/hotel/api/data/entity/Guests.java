package com.hotel.api.data.entity;

import javax.persistence.*;

@Entity
@Table(name = "GUESTS")
public class Guests {

    @Id
    @Column(name = "guest_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int guest_id;
    @Column(name = "guest_name")
    private String guest_name;
    @Column(name = "gender")
    private String gender;
    @Column(name = "confirmation_number")
    private int confirmation_number;

    public int getGuest_id() {
        return guest_id;
    }

    public void setGuest_id(int guest_id) {
        this.guest_id = guest_id;
    }

    public String getGuest_name() {
        return guest_name;
    }

    public void setGuest_name(String guest_name) {
        this.guest_name = guest_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getConfirmation_number() {
        return confirmation_number;
    }

    public void setConfirmation_number(int confirmation_number) {
        this.confirmation_number = confirmation_number;
    }
}
