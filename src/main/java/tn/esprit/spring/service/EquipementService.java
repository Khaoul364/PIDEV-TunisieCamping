package tn.esprit.spring.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.Categorie;
import tn.esprit.spring.entity.Equipement;
import tn.esprit.spring.repository.CategorieRepository;
import tn.esprit.spring.repository.EquipementRepository;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service

public class EquipementService implements IEquipementService {
    @Autowired
    private EquipementRepository ER;
    @Autowired
    private CategorieRepository CR;

    @Override
    public List<Equipement> getAllEquipement() {
        return (List<Equipement>)ER.findAll();    }

    @Override
    public Equipement addEquipement(Equipement e, Long idCategorie) {
        Categorie category =CR.findById(idCategorie).orElseThrow(() -> new IllegalArgumentException("Category not found"));
        e.setCategorie(category);
        e.setPurchaseQuantity(0);
        e.setEquipementImage("images.png");
        e.setDateModification(Date.from(Instant.now()));
        e.setAvailabilty(true);
       // if (e.isPromotion()=null && e.isFeatured()=null)
        {e.setPromotion(false); e.setFeatured(false);}
        return ER.save(e);
    }

    @Override
    public Equipement updateEquipment(Equipement equipment, long idEquipement, Long IdCategorie) {
        Equipement e = ER.findById(idEquipement).get();
        e.setEquipementName(equipment.getEquipementName());
        e.setEquipementPrice(equipment.getEquipementPrice());
        e.setDiscount(equipment.getDiscount());
        e.setFeatured(equipment.isFeatured());
        e.setDescription(equipment.getDescription());
        e.setColor(equipment.getColor());
        e.setMarque(equipment.getMarque());
        e.setAvailabilty(equipment.isAvailabilty());
        e.setPromotion(equipment.isPromotion());
        e.setQuantityInStock(equipment.getQuantityInStock());
        e.setPurchaseQuantity(equipment.getPurchaseQuantity());
        e.setEquipementImage(equipment.getEquipementImage());

        Categorie category = CR.findById(IdCategorie).orElseThrow();
        equipment.setCategorie(category);

        return ER.save(e);    }

    @Override
    public void deleteEquipement(long idEquipement) {
        ER.deleteById(idEquipement);

    }

    @Override
    public Equipement findByIdEquipment(long idEquipement) {
        Equipement equipment =  ER.findById(idEquipement).get();
        return  equipment;    }

    @Override
    public List<Equipement> retrieveEquipmentById(long idEquipement) {
        return null;
    }


}
