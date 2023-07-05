package tn.esprit.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.Equipement;
import tn.esprit.spring.entity.Panier;
import tn.esprit.spring.entity.PanierEquipement;
import tn.esprit.spring.entity.Utilisateur;
import tn.esprit.spring.repository.PanierEquipementRepository;
import tn.esprit.spring.repository.PanierRepository;
import tn.esprit.spring.repository.UtilisateurRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.*;


@Service

public class PanierService implements IPanierService{
    @Autowired
    PanierRepository CR;
    @Autowired
    EquipementService ES;
    @Autowired
    UtilisateurRepository UR;
    @Autowired
    PanierEquipementRepository CER;
    @Override
    public List<Panier> fetchPanierList() {
        return null;
    }

    @Override
    public Panier createPanier(Long IdUser) {
        Utilisateur user = UR.findById(IdUser).get();
        Panier panier=new Panier();
        panier.setUtilisateur(user);
        return CR.save(panier);    }

    @Override
    public void deletePanier(Long id) {
        Panier panier = CR.findById(id).orElseThrow();

        panier.setUtilisateur(null);
        CR.delete(panier);
        CR.deleteById(id);

    }

    @Override
    public Panier findByIdPanier(Long idc) {
        Panier panier= CR.findById(idc).get();
        return  panier;    }

    @Override
    public Panier addEquipmentToPanier(Long IdPanier, Long IdEquipment) {
        Panier panier = CR.findById(IdPanier).orElseThrow();
        Equipement equipment = ES.findByIdEquipment(IdEquipment);

        PanierEquipement panierEquipment = CER.findCartEquipmentByCartAndEquipment(IdEquipment, IdPanier);
        if (panierEquipment != null) {
            if (Objects.equals(panierEquipment.getEquipement().getIdEquipement(), IdEquipment) && Objects.equals(panierEquipment.getPanier().getIdPanier(), IdPanier)) {
                int newQuantity = panierEquipment.getQuantity() + 1;
                panierEquipment.setQuantity(newQuantity);
                CER.save(panierEquipment); // Save the updated CartEquipment
                panier.setNumberOfEquipPurchased(panier.getNumberOfEquipPurchased() + 1);
                return CR.save(panier);
            }
        } else {
            if (panier != null && equipment != null) {
                if (equipment.getQuantityInStock() != 0) {
                    PanierEquipement newCartEquipment = new PanierEquipement();
                    newCartEquipment.setPanier(panier); // Set the Cart instance
                    newCartEquipment.setEquipement(equipment); // Set the Equipment instance
                    newCartEquipment.setQuantity(1); // Set the quantity
                    CER.save(newCartEquipment);

                    panier.getPanierEquipements().add(newCartEquipment); // Add the new CartEquipment to the Cart

                    equipment.setDateModification(LocalDateTime.now());


                    panier.setNumberOfEquipPurchased(panier.getNumberOfEquipPurchased() + 1);

                    updateTotalPrice(panier);

                    return CR.save(panier);
                }
            }
        }
        return null;    }

    @Override
    public Panier removeEquipmentFromPanier(Long IdPanier, Long IdEquipment) {
        Panier panier = CR.findById(IdPanier).orElseThrow();
        Equipement equipment = ES.findByIdEquipment(IdEquipment);

        PanierEquipement panierEquipment = CER.findCartEquipmentByCartAndEquipment(IdEquipment, IdPanier);
        if (panierEquipment != null) {
            if (Objects.equals(panierEquipment.getEquipement().getIdEquipement(), IdEquipment) && Objects.equals(panierEquipment.getPanier().getIdPanier(), IdPanier)) {
                int quantity = panierEquipment.getQuantity();
                if (quantity > 1) {
                    panierEquipment.setQuantity(quantity - 1);
                    CER.save(panierEquipment); // Save the updated CartEquipment
                } else {
                    panier.getPanierEquipements().remove(panierEquipment); // Remove the CartEquipment from the Cart
                    CER.delete(panierEquipment); // Delete the CartEquipment from the database
                }

                equipment.setDateModification(LocalDateTime.now());

                if (equipment.isPromotion() == true) {
                    panier.setDiscountedAmount(panier.getDiscountedAmount() - equipment.getEquipementPrice() * equipment.getDiscount() / 100);
                }
                if(panier.getNumberOfEquipPurchased()>0){
                    panier.setNumberOfEquipPurchased(panier.getNumberOfEquipPurchased() - 1);}
                updateTotalPrice(panier);
                return CR.save(panier);
            }
        }
        return null;    }

    @Override
    public Panier findByIdUser(Long idu) {
        Panier panier=CR.findByUserId(idu);
        return panier;    }

    @Override
    public void updateTotalPrice(Panier panier) {
        float totalPrice = 0;
        Set<PanierEquipement> panierEquipments = panier.getPanierEquipements();

        for (PanierEquipement panierEquipment : panierEquipments) {
            Equipement equipment = panierEquipment.getEquipement();
            int quantity = panierEquipment.getQuantity();

            float equipmentPrice = equipment.getEquipementPrice();
            if (equipment.isPromotion() == true) {
                panier.setDiscountedAmount(panier.getDiscountedAmount() + equipment.getEquipementPrice() * equipment.getDiscount() / 100);
            }

            totalPrice = totalPrice + (equipmentPrice * quantity);
        }


        float discountedAmount = panier.getDiscountedAmount();
        panier.setFinalPrice(totalPrice - discountedAmount);

    }

    @Override
    public List<Equipement> fetchEquipmentsinPanier(Long idPanier) {
        Panier panier = CR.findById(idPanier).orElseThrow();
        if (panier != null){return new ArrayList<>(panier.getEquipements());
        }
        return Collections.emptyList();    }

    @Override
    public String clearPanier(Long idPanier) {
        Panier panier = CR.findById(idPanier).orElse(null);
        if (panier != null){

            panier.getEquipements().clear();
            panier.setTotalPrice(0);
            panier.setFinalPrice(0);
            panier.setDiscountedAmount(0);
            panier.setNumberOfEquipPurchased(0);
            CR.save(panier);
            return "CART GOT CLEARED";
        } else return "CART NOT FOUND";    }

    @Override
    public Long getIdPanier(Long idu) {
        Panier panier= CR.findByUserId(idu);
        return panier.getIdPanier();    }
}
