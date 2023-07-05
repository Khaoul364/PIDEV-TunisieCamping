package tn.esprit.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entity.Facture;
import tn.esprit.spring.repository.FactureRepository;
import tn.esprit.spring.service.FactureService;

import java.util.List;

@CrossOrigin(origins = " http://localhost:4200")
@RestController
@RequestMapping("factures")
public class FactureController {
    @Autowired
    private FactureService IS;
    @Autowired
    private FactureRepository IR;

    @GetMapping("/facture/{idi}")
    public Facture getInvoicebyid(@PathVariable("idi") Long idFacture){return IS.findFactureById(idFacture);}
    @PostMapping("add/{idu}/{delivery}/{paiment}/{adr}")
    public Facture createInvoice(@PathVariable("idu") Long IdUser, @PathVariable("delivery") boolean Delivery, @PathVariable("paiment")Facture.PaimentMode paimentMode,@PathVariable("adr") String adresse){

        return IS.generateFacture(IdUser,Delivery,paimentMode,adresse);
    }
    @GetMapping("/totalpriceNumberofEquipmentbyMonthAndYear")
    public List<Object[]> getTotalPriceandNumberOfEquipmentByMonthAndYear(){return  IS.getTotalPriceAndNumberOfEquipmentByMonthAndYEar();}
    @PutMapping("/processpaiment/{idi}")
    public Facture processPaiment(@RequestBody Facture invoice,@PathVariable("idi") Long idInvoice){return IS.ProcessedFacture(invoice,idInvoice);}

    @GetMapping("/getInvsByUser/{idu}")
    public List<Facture> getInvoicesByUser(@PathVariable("idu") Long idUser){return IR.findByUserId(idUser);}

}
