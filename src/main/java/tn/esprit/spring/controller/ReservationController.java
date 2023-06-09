package tn.esprit.spring.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entity.Reservation;
import tn.esprit.spring.service.IReservationService;

import java.util.List;

@RestController
@AllArgsConstructor
public class ReservationController {

    @Autowired
    IReservationService reservationService;

    @GetMapping("/all-reservation")
    @ResponseBody
    public List<Reservation> getAllReservation() {

        return reservationService.getAllReseravations();
    }

    @PostMapping("/add-reservation")
    @ResponseBody
    public Reservation addReservation(@RequestBody Reservation reservation) {
        return reservationService.addReservation(reservation);
    }

    @PutMapping("/edit-reservation/{idReservation}")
    public Reservation editReservation(@RequestBody Reservation reservation, @PathVariable("idReservation") int idReservation) {
        return reservationService.editReservation(reservation,idReservation);
    }

    @DeleteMapping("/delete-reservation/{idReservation}")
    public void deleteReservation(@PathVariable("idReservation") int idReservation) {
        reservationService.deleteReservation(idReservation);
    }

    @PostMapping("/addReservationActivity/{idActivite}")
    @ResponseBody
    public void assignActiviteToReservation(@RequestBody Reservation reservation,@PathVariable int idActivite) {
        reservationService.assignActiviteToReservation(reservation,idActivite);
    }
}
