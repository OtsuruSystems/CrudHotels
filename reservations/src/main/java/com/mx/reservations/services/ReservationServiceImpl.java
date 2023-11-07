package com.mx.reservations.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.reservations.dao.IReservationDao;
import com.mx.reservations.model.Reservation;

@Service
public class ReservationServiceImpl implements IReservationService {

	@Autowired
	private IReservationDao reservationDao;
	
	@Override
	public List<Reservation> search() {
		return (List<Reservation>) reservationDao.findAll();
	}

}
