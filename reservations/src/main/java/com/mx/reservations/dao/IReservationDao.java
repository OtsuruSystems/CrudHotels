package com.mx.reservations.dao;

import org.springframework.data.repository.CrudRepository;

import com.mx.reservations.model.Reservation;

public interface IReservationDao extends CrudRepository<Reservation, Long>{

}
