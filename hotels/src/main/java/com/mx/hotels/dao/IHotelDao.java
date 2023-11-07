package com.mx.hotels.dao;

import org.springframework.data.repository.CrudRepository;

import com.mx.hotels.model.Hotel;

public interface IHotelDao extends CrudRepository<Hotel, Long> {


}
