package tn.esprit.spring.service;

import tn.esprit.spring.entity.Activite;
import tn.esprit.spring.entity.Reservation;

import java.util.List;
import java.util.Optional;

public interface IReservationService {

    public List<Reservation> getAllReseravations();

    public Reservation addReservation(Reservation reservation);

    public void assignActiviteToReservation(Reservation reservation, int idActivite);

    //public Reservation editReservation(Reservation reservation, int idReservation);

    public void editReservation(Reservation reservation);

    public void deleteReservation(int idReservation);

    public Optional<Reservation> getReservationById(int idReservation);
}
