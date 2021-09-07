package com.hotel.api.business.domain;

import com.hotel.api.data.entity.Hotels;

import java.util.List;

public class HotelList {
    private List<Hotels> hotelsList;

    public List<Hotels> getHotelsList() {
        return hotelsList;
    }

    public void setHotelsList(List<Hotels> hotelsList) {
        this.hotelsList = hotelsList;
    }
}
