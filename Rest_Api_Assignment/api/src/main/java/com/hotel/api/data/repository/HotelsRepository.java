package com.hotel.api.data.repository;

import com.hotel.api.data.entity.Hotels;
import org.springframework.data.repository.CrudRepository;

public interface HotelsRepository extends CrudRepository<Hotels, Long> {
    Iterable<Hotels> findHotelsByHotelName(String name);
}
