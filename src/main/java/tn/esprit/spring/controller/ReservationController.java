package tn.esprit.spring.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entity.Activite;
import tn.esprit.spring.entity.Reservation;
import tn.esprit.spring.service.ActiviteService;
import tn.esprit.spring.service.EmailService;
import tn.esprit.spring.service.IReservationService;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ReservationController {

    @Autowired
    IReservationService reservationService;
    @Autowired
    EmailService emailService;
    @GetMapping("/all-reservation")
    //@ResponseBody
    public List<Reservation> getAllReservation() {

        return reservationService.getAllReseravations();
    }

    @PostMapping("/add-reservation")
    @ResponseBody
    public Reservation addReservation(@RequestBody Reservation reservation) {
        Reservation addedReservation = reservationService.addReservation(reservation);

        String login = addedReservation.getEmail();
        // Send email notification
        String to = login; // Use the login variable as the recipient's email address
        String subject = "Confirmation de réservation Tunisie Camping ";
        String content = "Votre réservation a été bien sauvegardée. Détails: " + addedReservation.toString();
        emailService.sendEmail(to, subject, content);

        return addedReservation;
    }


    @PutMapping("/edit-reservation")
    public void editReservation(@RequestBody Reservation reservation) {

        reservationService.editReservation(reservation);
    }

    //@PutMapping("/edit-reservation/{idReservation}")
    //public Reservation editReservation(@RequestBody Reservation reservation, @PathVariable("idReservation") int idReservation) {
        //return reservationService.editReservation(reservation,idReservation);
    //}

    @DeleteMapping("/delete-reservation/{idReservation}")
    public void deleteReservation(@PathVariable("idReservation") int idReservation) {
        reservationService.deleteReservation(idReservation);
    }

    @PostMapping("/addReservationActivity/{idActivite}")
    //@ResponseBody
    public void assignActiviteToReservation(@RequestBody Reservation reservation,@PathVariable int idActivite) {
        reservationService.assignActiviteToReservation(reservation,idActivite);
    }


}
