package tn.esprit.spring.service;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.spring.entity.Color;
import tn.esprit.spring.entity.Equipement;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface IEquipementService {
    public List<Equipement> getAllEquipement();

    public Equipement addEquipement(Equipement e,Long idCategorie);

    public Equipement updateEquipment(Equipement equipment, long idEquipement, Long IdCategorie);

    public void deleteEquipement(long idEquipement);

    public Equipement findByIdEquipment(long idEquipement);

    public List<Equipement> retrieveEquipmentById(long idEquipement);
    List<Equipement> searchEquipment(Color color , String categorieName, String marque );

    List<Equipement> featuredEquipment();
    Equipement uploadImage(Long IdEquipment , MultipartFile file) throws IOException;
    String saveFile(MultipartFile file) throws IOException;
    List<Equipement> filterByPriceRange(double minPrice, double maxPrice );
    List<Equipement> AvailableEquipment();
    List<Equipement> PromotionalEquipment();
    Equipement findMostSoldEquipment(String marque);
    Equipement findLeastSoldEquipment(String marque);
    Equipement findMostSoldEquipmentFeatured(boolean featured);
    Equipement findLeastSoldEquipmentFeatured(boolean featured);
    Equipement findMostSoldEquipmentPromotion(boolean promotion);
    Equipement findLeastSoldEquipmentPromotion(boolean promotion);
    float totalEarningsForMostSoldEquip(String marque);
    float totalEarningsForLeastSoldEquip(String marque);

    float totalEarningsForMostSoldEquipFeatured(boolean featured);
    float totalEarningsForLeastSoldEquipFeatured(boolean featured);

    float totalEarningsForMostSoldEquipPromotion(boolean promotion);
    float totalEarningsForLeastSoldEquipPromotion(boolean promotion);
}
