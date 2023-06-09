package tn.esprit.spring.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.Activite;
import tn.esprit.spring.entity.Reservation;
import tn.esprit.spring.repository.ActiviteRepository;
import tn.esprit.spring.repository.ReservationRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ReservationService implements IReservationService{
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    ActiviteRepository activiteRepository;

    @Override
    public List<Reservation> getAllReseravations() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation addReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public void assignActiviteToReservation(Reservation reservation, int idActivite) {
        Activite act = activiteRepository.findById(idActivite).orElse(null);
        reservation.setActivite(act);
        reservationRepository.save(reservation);
    }

    @Override
    public Reservation editReservation(Reservation reservation, int idReservation) {

        Reservation res = reservationRepository.findById(idReservation).orElse(null);
        res.setNom(reservation.getNom());
        res.setActivite(reservation.getActivite());
        res.setDate_deb(reservation.getDate_deb());
        res.setDate_fin(reservation.getDate_fin());
        res.setTelephone(reservation.getTelephone());
        res.setTransport(reservation.getTransport());
        res.setPrixTotal(reservation.getPrixTotal());
        res.setNbrPersonne(reservation.getNbrPersonne());
        res.setUtilisateur(reservation.getUtilisateur());

        return reservationRepository.save(res);
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
