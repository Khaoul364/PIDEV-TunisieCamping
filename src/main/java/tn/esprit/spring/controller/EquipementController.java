package tn.esprit.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.spring.entity.Color;
import tn.esprit.spring.entity.Equipement;
import tn.esprit.spring.repository.EquipementRepository;
import tn.esprit.spring.service.CategorieService;
import tn.esprit.spring.service.EquipementService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@RequestMapping("equipments")
@RestController
@CrossOrigin(origins = " http://localhost:4200")
public class EquipementController {
    @Autowired
    private EquipementRepository ER;
    @Autowired
    private EquipementService ES;
    @Autowired
    private CategorieService CS;
    private final String uploads="uploads/";

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
    @GetMapping("/search")
    public List<Equipement> searchEquipment(
            @RequestParam(required = false) String color,
            @RequestParam(required = false) String categorieName,
            @RequestParam(required = false) String marque

    ) {
        Color colorenum = null;
        if (color != null) {

            colorenum = Color.valueOf(color.toUpperCase());

        }


        return ES.searchEquipment(colorenum, categorieName, marque);

    }



    @GetMapping("/featured")
    public  List<Equipement> featuredEquipment(){return ES.featuredEquipment();}


    // @PutMapping("/uploadImage/{ide}")
    // public Equipment uploadImage(@PathVariable ("ide") Long IdEquipment, @RequestParam("file") MultipartFile file) throws IOException {
    //    return ES.uploadImage(IdEquipment,file);}


    @GetMapping("/equipmentPriceRange/{min}/{max}")
    public List<Equipement> getEquipByPriceRange(@PathVariable("min") double MinPrice,@PathVariable("max") double MaxPrice){
        return ES.filterByPriceRange(MinPrice,MaxPrice);

    }

    @GetMapping("/available")
    public  List<Equipement> AvailableEquipment(){return ES.AvailableEquipment();}

    @GetMapping("/promotional")
    public  List<Equipement> PromotionalEquipment(){return ES.PromotionalEquipment();}
    @GetMapping("/most/{marque}")
    public ResponseEntity<Equipement> getMmostSoldEquip(
            @PathVariable String marque

    ){


        Equipement mostSoldEquip = ES.findMostSoldEquipment(marque);
        if(mostSoldEquip!=null){

            return ResponseEntity.ok(mostSoldEquip);
        }else

            return ResponseEntity.notFound().build();

    }


    @GetMapping("/total/{marque}")
    public ResponseEntity<Float> getTotalEbyMostSoldEquip(
            @PathVariable String marque

    ){

        float totalEarnings= ES.totalEarningsForMostSoldEquip(marque);
        return ResponseEntity.ok(totalEarnings);

    }

    @GetMapping("/least/{marque}")
    public ResponseEntity<Equipement> getLeastSoldEquip(
            @PathVariable String marque

    ){


        Equipement leastSoldEquip = ES.findLeastSoldEquipment(marque);
        if(leastSoldEquip!=null){

            return ResponseEntity.ok(leastSoldEquip);
        }else

            return ResponseEntity.notFound().build();

    }




    @GetMapping("/total2/{marque}")
    public ResponseEntity<Float> getTotalEbyLeastSoldEquip(
            @PathVariable String marque
    ){

        float totalEarnings= ES.totalEarningsForLeastSoldEquip(marque);
        return ResponseEntity.ok(totalEarnings);

    }



    @GetMapping("/mostF/{featured}")
    public ResponseEntity<Equipement> getMmostSoldEquipByFeatured(
            @PathVariable boolean featured

    ){


        Equipement mostSoldEquip = ES.findMostSoldEquipmentFeatured(featured);
        if(mostSoldEquip!=null){

            return ResponseEntity.ok(mostSoldEquip);
        }else

            return ResponseEntity.notFound().build();

    }



    @GetMapping("/leastF/{featured}")
    public ResponseEntity<Equipement> getLeastSoldEquipByFeatured(
            @PathVariable boolean featured

    ){


        Equipement leastSoldEquip = ES.findLeastSoldEquipmentFeatured(featured);
        if(leastSoldEquip!=null){

            return ResponseEntity.ok(leastSoldEquip);
        }else

            return ResponseEntity.notFound().build();

    }

    @GetMapping("/mostP/{promotion}")
    public ResponseEntity<Equipement> getMmostSoldEquipByPromotion(
            @PathVariable boolean promotion

    ){


        Equipement mostSoldEquip = ES.findMostSoldEquipmentPromotion(promotion);
        if(mostSoldEquip!=null){

            return ResponseEntity.ok(mostSoldEquip);
        }else

            return ResponseEntity.notFound().build();

    }

    @GetMapping("/leastP/{promotion}")
    public ResponseEntity<Equipement> getLeastSoldEquipByPromotion(
            @PathVariable boolean promotion

    ){


        Equipement leastSoldEquip = ES.findLeastSoldEquipmentPromotion(promotion);
        if(leastSoldEquip!=null){

            return ResponseEntity.ok(leastSoldEquip);
        }else

            return ResponseEntity.notFound().build();

    }





    @GetMapping("/total3/{featured}")
    public ResponseEntity<Float> getTotalEbyMostSoldEquipByFeatured(
            @PathVariable boolean featured

    ){

        float totalEarnings= ES.totalEarningsForMostSoldEquipFeatured(featured);
        return ResponseEntity.ok(totalEarnings);

    }

    @GetMapping("/total4/{featured}")
    public ResponseEntity<Float> getTotalEbyLeastSoldEquipByFeatured(
            @PathVariable boolean featured

    ){

        float totalEarnings= ES.totalEarningsForLeastSoldEquipFeatured(featured);
        return ResponseEntity.ok(totalEarnings);

    }

    @GetMapping("/total5/{promotion}")
    public ResponseEntity<Float> getTotalEbyLeastSoldEquipByPromotion(
            @PathVariable boolean promotion

    ){

        float totalEarnings= ES.totalEarningsForLeastSoldEquipPromotion(promotion);
        return ResponseEntity.ok(totalEarnings);

    }


    @GetMapping("/total6/{promotion}")
    public ResponseEntity<Float> getTotalEbyMostSoldEquipByPromotion(
            @PathVariable boolean promotion

    ){

        float totalEarnings= ES.totalEarningsForMostSoldEquipPromotion(promotion);
        return ResponseEntity.ok(totalEarnings);

    }

    @PostMapping("/upload/{ide}")
    public ResponseEntity<String> uploadImage(@PathVariable("ide") Long idEquipment,@RequestParam ("file") MultipartFile file) {
        try {
            Equipement equipment = ES.findByIdEquipment(idEquipment);
            String uploadDir = "uploads/";
            File directory = new File(uploadDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            String filename = System.currentTimeMillis() + "-" + file.getOriginalFilename();
            Path filePath = Paths.get(uploads, filename);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            equipment.setEquipementImage(filename);
            ER.save(equipment);
            return ResponseEntity.ok("uploaded successfully");

        } catch (IOException e) {
            return ResponseEntity.status(500).body("Failed");
        }
    }

}
