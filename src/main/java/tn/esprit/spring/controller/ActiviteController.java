package tn.esprit.spring.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entity.Activite;
import tn.esprit.spring.service.IActiviteService;

import java.util.List;

@RestController
@AllArgsConstructor
public class ActiviteController {
    IActiviteService activiteService;

    @GetMapping("/all-activite")
    @ResponseBody
    public List<Activite> getAllActivite() {
        return activiteService.getAllActivite();
    }

    @PostMapping("/add-activite")
    @ResponseBody
    public Activite addActivite(@RequestBody Activite activite) {
        return activiteService.addActivite(activite);
    }

    @PutMapping("/edit-activite/{idActivite}")
    public Activite editActivite(@RequestBody Activite activite, @PathVariable("idActivite") int idActivite) {
        return activiteService.editActivite(activite, idActivite);
    }

    @DeleteMapping("/delete-activite/{idActivite}")
    public void deleteActivite(@PathVariable("idActivite") int idActivite) {
        activiteService.deleteActivite(idActivite);
    }
}
