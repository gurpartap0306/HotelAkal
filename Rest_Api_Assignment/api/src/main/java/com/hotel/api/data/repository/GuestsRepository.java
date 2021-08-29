package com.hotel.api.data.repository;

import com.hotel.api.data.entity.Guests;
import org.springframework.data.repository.CrudRepository;

public interface GuestsRepository extends CrudRepository<Guests, Long> {
}
