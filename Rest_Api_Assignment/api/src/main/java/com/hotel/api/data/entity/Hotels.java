package com.hotel.api.data.entity;

import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;

@Entity
@Table(name="HOTELS")
public class Hotels {

    @Id
    @Column(name = "hotel_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int hotelId;
    @Column(name = "hotel_name")
    private String hotelName;
    @Column(name = "price")
    private String price;
    @Column(name = "availability")
    private String availability;

    public int id(){
        return this.hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public int getPrice() {
        return Integer.parseInt(price);
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public boolean getAvailability() {
        return Boolean.parseBoolean(availability);
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }
}
