package tn.esprit.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entity.Equipement;
import tn.esprit.spring.repository.EquipementRepository;
import tn.esprit.spring.service.CategorieService;
import tn.esprit.spring.service.EquipementService;

import java.util.List;

@RequestMapping("equipments")
@RestController
@CrossOrigin
public class EquipementController {
    @Autowired
    private EquipementRepository ER;
    @Autowired
    private EquipementService ES;
    @Autowired
    private CategorieService CS;

    @PostMapping("/add/{id}")
    public Equipement createEquipment(@RequestBody Equipement equipement, @PathVariable("id") Long idCategorie) {
        return ES.addEquipement(equipement, idCategorie);
    }

    @GetMapping("liste")
    public List<Equipement> fetchEquipmentList() {

        return ES.getAllEquipement();
    }

    @PutMapping("/update/{id}/{idc}")
    public Equipement
    updateEquipment(@RequestBody Equipement equipment,
                    @PathVariable("id") Long IdEquipment, @PathVariable("idc") Long IdCategorie) {
        return ES.updateEquipment(equipment, IdEquipment, IdCategorie);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteEquipment(@PathVariable("id")
                                  Long IdEquipment) {
        ES.deleteEquipement(IdEquipment);

        return "Deleted Successfully";
    }

    @GetMapping("/find/{id}")
    public Equipement findEquipmentById(@PathVariable("id")
                                       Long IdEquipment) {

        return ES.findByIdEquipment(IdEquipment);
    }

}
