package tn.esprit.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entity.Equipement;
import tn.esprit.spring.entity.Panier;
import tn.esprit.spring.entity.PanierEquipement;
import tn.esprit.spring.service.EquipementService;
import tn.esprit.spring.service.PanierEquipementService;
import tn.esprit.spring.service.PanierService;

import java.util.List;

@CrossOrigin(origins = " http://localhost:4200")
@RestController
@RequestMapping("paniers")
public class PanierController {
    @Autowired
    private PanierEquipementService CES;
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
    @PostMapping("/equipments/{idc}/{ide}")
    public ResponseEntity<String> addEquipmentToPanier(@PathVariable Long idc, @PathVariable Long ide){
        CS.addEquipmentToPanier(idc,ide);

        return ResponseEntity.ok("Equipment added to the cart successfully");
    }


    @DeleteMapping("/equipments/{idc}/{ide}")
    public ResponseEntity<String> deleteEquipmentfromPanier(@PathVariable Long idc,@PathVariable Long ide){
        CS.removeEquipmentFromPanier(idc,ide);

        return ResponseEntity.ok("Equipment removed from the cart successfully");
    }



    @CrossOrigin
    @GetMapping("/inpanier/{idc}")
    public List<Equipement> getAllequipInPanier(@PathVariable ("idc") Long IdCart){return CS.fetchEquipmentsinPanier(IdCart);}


    @GetMapping("/CartsByUser/{idu}")
    public Panier getCartByUser (@PathVariable("idu") Long IdUser){return CS.findByIdUser(IdUser);}


    @DeleteMapping("/clear/{idc}")
    public ResponseEntity<String> clearPanier(@PathVariable("idc") Long IdPanier){CS.clearPanier(IdPanier);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @GetMapping("/getid/{idu}")
    public Long getIdPanier (@PathVariable("idu") Long IdUser){return CS.getIdPanier(IdUser);}
    @GetMapping("/getPanierEquipbyid/{idc}")
    public List<PanierEquipement> findPanierEquipByIdPanier (@PathVariable("idc") Long IdPanier){return CES.findPanierEquipByIdPanier(IdPanier);}
}
