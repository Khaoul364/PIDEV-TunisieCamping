package tn.esprit.spring.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.spring.entity.Categorie;
import tn.esprit.spring.entity.Color;
import tn.esprit.spring.entity.Equipement;
import tn.esprit.spring.repository.CategorieRepository;
import tn.esprit.spring.repository.EquipementRepository;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

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
        e.setDateModification(LocalDateTime.now());
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

    @Override
    public List<Equipement> searchEquipment(Color color, String categorieName, String marque) {
        return ER.searchEquipment(color,categorieName,marque);

    }

    @Override
    public List<Equipement> featuredEquipment() {
        return  ER.findEquipmentByFeatured(true) ;
    }

    @Override
    public Equipement uploadImage(Long IdEquipment, MultipartFile file) throws IOException {
        Equipement equipment = ER.findById(IdEquipment).orElseThrow();

        if (equipment != null && !file.isEmpty()) {

            String StorageLocation = saveFile(file);
            equipment.setEquipementImage(StorageLocation);
            return  ER.save(equipment);
        }
        return  null;    }

    @Override
    public String saveFile(MultipartFile file) throws IOException {
        String storageDirectory = "C:/Users/youss/OneDrive/Bureau/pictures/";
        String originalFileName = file.getOriginalFilename();
        String uniqueFileName = UUID.randomUUID().toString() +"-"+originalFileName;
        String storageLocation= storageDirectory + uniqueFileName;
        File destinationFile = new File(storageLocation);
        file.transferTo(destinationFile);
        return storageLocation;    }

    @Override
    public List<Equipement> filterByPriceRange(double minPrice, double maxPrice) {
        return ER.findAll().stream().filter(equipement ->equipement.getEquipementPrice() >= minPrice && equipement.getEquipementPrice()<= maxPrice ).collect(Collectors.toList());
    }

    @Override
    public List<Equipement> AvailableEquipment() {
        return ER.findEquipmentByAvailable(true);
    }

    @Override
    public List<Equipement> PromotionalEquipment() {
        return ER.findEquipmentByPromotion(true);
    }

    @Override
    public Equipement findMostSoldEquipment(String marque) {
        List<Equipement> filteredEquipment=ER.findByFilters(marque);
        Optional<Equipement> mostSoldEquip=filteredEquipment.stream().max(Comparator.comparingInt(Equipement::getPurchaseQuantity));
        return mostSoldEquip.orElse(null);    }

    @Override
    public Equipement findLeastSoldEquipment(String marque) {
        List<Equipement> filteredEquipment=ER.findByFilters(marque);
        Optional<Equipement> leastSoldEquip=filteredEquipment.stream().min(Comparator.comparingInt(Equipement::getPurchaseQuantity));
        return leastSoldEquip.orElse(null);    }

    @Override
    public Equipement findMostSoldEquipmentFeatured(boolean featured) {
        List<Equipement> filteredEquipment=ER.findByFiltersByFeatured(featured);
        Optional<Equipement> mostSoldEquip=filteredEquipment.stream().max(Comparator.comparingInt(Equipement::getPurchaseQuantity));
        return mostSoldEquip.orElse(null);    }

    @Override
    public Equipement findLeastSoldEquipmentFeatured(boolean featured) {
        List<Equipement> filteredEquipment=ER.findByFiltersByFeatured(featured);
        Optional<Equipement> leastSoldEquip=filteredEquipment.stream().min(Comparator.comparingInt(Equipement::getPurchaseQuantity));
        return leastSoldEquip.orElse(null);    }

    @Override
    public Equipement findMostSoldEquipmentPromotion(boolean promotion) {
        List<Equipement> filteredEquipment=ER.findByFiltersByPromotion(promotion);
        Optional<Equipement> mostSoldEquip=filteredEquipment.stream().max(Comparator.comparingInt(Equipement::getPurchaseQuantity));
        return mostSoldEquip.orElse(null);
    }

    @Override
    public Equipement findLeastSoldEquipmentPromotion(boolean promotion) {
        List<Equipement> filteredEquipment=ER.findByFiltersByPromotion(promotion);
        Optional<Equipement> leastSoldEquip=filteredEquipment.stream().min(Comparator.comparingInt(Equipement::getPurchaseQuantity));
        return leastSoldEquip.orElse(null);
    }

    @Override
    public float totalEarningsForMostSoldEquip(String marque) {
        Equipement mostSoldEquip= findMostSoldEquipment(marque);
        float totalEarnings=0;
        if (mostSoldEquip!= null){
            if(mostSoldEquip.isPromotion()==true){
                totalEarnings=(mostSoldEquip.getEquipementPrice()*mostSoldEquip.getPurchaseQuantity())*mostSoldEquip.getDiscount()/100;
            }else

                totalEarnings = mostSoldEquip.getEquipementPrice()*mostSoldEquip.getPurchaseQuantity();
            return  totalEarnings;}else
            return 0;    }

    @Override
    public float totalEarningsForLeastSoldEquip(String marque) {
        Equipement leastSoldEquip= findLeastSoldEquipment(marque);
        float totalEarnings=0;
        if (leastSoldEquip!= null){
            if(leastSoldEquip.isPromotion()==true){
                totalEarnings=(leastSoldEquip.getEquipementPrice()*leastSoldEquip.getPurchaseQuantity())*leastSoldEquip.getDiscount()/100;
            }else

                totalEarnings = leastSoldEquip.getEquipementPrice()*leastSoldEquip.getPurchaseQuantity();
            return  totalEarnings;}else
            return 0;    }

    @Override
    public float totalEarningsForMostSoldEquipFeatured(boolean featured) {
        Equipement mostSoldEquip= findMostSoldEquipmentFeatured(featured);
        float totalEarnings=0;
        if (mostSoldEquip!= null){
            if(mostSoldEquip.isPromotion()==true){
                totalEarnings=(mostSoldEquip.getEquipementPrice()*mostSoldEquip.getPurchaseQuantity())*mostSoldEquip.getDiscount()/100;
            }else

                totalEarnings = mostSoldEquip.getEquipementPrice()*mostSoldEquip.getPurchaseQuantity();
            return  totalEarnings;}else
            return 0;    }

    @Override
    public float totalEarningsForLeastSoldEquipFeatured(boolean featured) {
        Equipement leastSoldEquip= findLeastSoldEquipmentFeatured(featured);
        float totalEarnings=0;
        if (leastSoldEquip!= null){
            if(leastSoldEquip.isPromotion()==true){
                totalEarnings=(leastSoldEquip.getEquipementPrice()*leastSoldEquip.getPurchaseQuantity())*leastSoldEquip.getDiscount()/100;
            }else

                totalEarnings = leastSoldEquip.getEquipementPrice()*leastSoldEquip.getPurchaseQuantity();
            return  totalEarnings;}else
            return 0;    }

    @Override
    public float totalEarningsForMostSoldEquipPromotion(boolean promotion) {
        Equipement mostSoldEquip= findMostSoldEquipmentPromotion(promotion);
        float totalEarnings=0;
        if (mostSoldEquip!= null){
            if(mostSoldEquip.isPromotion()==true){
                totalEarnings=(mostSoldEquip.getEquipementPrice()*mostSoldEquip.getPurchaseQuantity())*mostSoldEquip.getDiscount()/100;
            }else

                totalEarnings = mostSoldEquip.getEquipementPrice()*mostSoldEquip.getPurchaseQuantity();
            return  totalEarnings;}else
            return 0;    }

    @Override
    public float totalEarningsForLeastSoldEquipPromotion(boolean promotion) {
        Equipement leastSoldEquip= findLeastSoldEquipmentPromotion(promotion);
        float totalEarnings=0;
        if (leastSoldEquip!= null){
            if(leastSoldEquip.isPromotion()==true){
                totalEarnings=(leastSoldEquip.getEquipementPrice()*leastSoldEquip.getPurchaseQuantity())*leastSoldEquip.getDiscount()/100;
            }else

                totalEarnings = leastSoldEquip.getEquipementPrice()*leastSoldEquip.getPurchaseQuantity();
            return  totalEarnings;}else
            return 0;    }


}
