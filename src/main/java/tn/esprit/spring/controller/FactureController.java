package tn.esprit.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entity.Facture;
import tn.esprit.spring.repository.FactureRepository;
import tn.esprit.spring.service.FactureService;

@CrossOrigin
@RestController
@RequestMapping("factures")
public class FactureController {
    @Autowired
    private FactureService IS;
    @Autowired
    private FactureRepository IR;

    @GetMapping("/facture/{idi}")
    public Facture getInvoicebyid(@PathVariable("idi") Long idFacture){return IS.findFactureById(idFacture);}

}
