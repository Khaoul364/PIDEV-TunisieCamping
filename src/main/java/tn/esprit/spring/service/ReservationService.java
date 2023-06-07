package tn.esprit.spring.service;

import tn.esprit.spring.entity.Reservation;
import tn.esprit.spring.repository.ReservationRepository;

import java.util.List;
import java.util.Optional;

public class ReservationService implements IReservationService{

    ReservationRepository reservationRepository;

    @Override
    public List<Reservation> getAllReseravations() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation addReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation editReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public void deleteReservation(int idReservation) {
        reservationRepository.deleteById(idReservation);
    }

    @Override
    public Optional<Reservation> getReservationById(int idReservation) {
        return reservationRepository.findById(idReservation);
    }


}
