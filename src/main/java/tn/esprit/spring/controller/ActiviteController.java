package tn.esprit.spring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.spring.entity.Activite;
import tn.esprit.spring.service.IActiviteService;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ActiviteController {
    @Autowired
    IActiviteService activiteService;

    @GetMapping("/all-activite")
    public List<Activite> getAllActivite() {
        return activiteService.getAllActivite();
    }

    //@PostMapping("/add-activite")
    //public Activite addActivite(@RequestBody Activite activite) {
       // return activiteService.addActivite(activite);
    //}
    @PostMapping("/add-activite")
    public Activite addActivite(@RequestParam("image") MultipartFile image,
                                @RequestParam("activite") String activiteJson) {
        try {
            Activite activite = new ObjectMapper().readValue(activiteJson, Activite.class);
            if (!image.isEmpty()) {
                byte[] imageBytes = image.getBytes();
                activite.setImage(imageBytes);
            }
            return activiteService.addActivite(activite);
        } catch (IOException e) {
            // Handle the exception
            return null;
        }
    }


    //@PutMapping("/edit-activite/{idActivite}")
    //public Activite editActivite(@RequestBody Activite activite, @PathVariable("idActivite") int idActivite) {
        //return activiteService.editActivite(activite, idActivite);
    //}

    // http://localhost:8081/edit-Product
    @PutMapping("/edit-activite")
    public void editActivite(@RequestBody Activite activite) {
        activiteService.editActivite(activite);
    }

    @DeleteMapping("/delete-activite/{idActivite}")
    public void deleteActivite(@PathVariable("idActivite") int idActivite) {
        activiteService.deleteActivite(idActivite);
    }

    @PostMapping("/assignLieuToActivite/{idLieu}")
    //@ResponseBody
    public void assignLieuToActivite(@RequestBody Activite activite, @PathVariable int idLieu) {
        activiteService.assignLieuToActivite(activite, idLieu);
    }

    @GetMapping("/reservationCount")
    public Map<Integer, Integer> getReservationsCountPerActivity() {
        return activiteService.getReservationsCountPerActivity();
    }

}
