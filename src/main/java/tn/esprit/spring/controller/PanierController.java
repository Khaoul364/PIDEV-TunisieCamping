package tn.esprit.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entity.Panier;
import tn.esprit.spring.service.EquipementService;
import tn.esprit.spring.service.PanierService;

@CrossOrigin
@RestController
@RequestMapping("paniers")
public class PanierController {
    @Autowired
    private PanierService CS;
    @Autowired
    private EquipementService ES;

    @PostMapping("/add/{idu}")
    public Panier createPanier(@RequestBody Panier cart, @PathVariable("idu") Long IdUser) {
        return CS.createPanier(IdUser);
    }

    @DeleteMapping("/delete/{idc}")
    public String deletePanier(@PathVariable("idc") Long idPanier){
        CS.deletePanier(idPanier);
        return "Deleted Successfully" ;}

    @GetMapping("/find/{idc}")
    Panier findCartById(@PathVariable ("idc") Long idPanier){
        return CS.findByIdPanier(idPanier);

    }
}
