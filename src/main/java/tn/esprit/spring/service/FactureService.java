package tn.esprit.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.Equipement;
import tn.esprit.spring.entity.Facture;
import tn.esprit.spring.entity.Panier;
import tn.esprit.spring.entity.Utilisateur;
import tn.esprit.spring.repository.FactureRepository;
import tn.esprit.spring.repository.UtilisateurRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service

public class FactureService implements IFactureService{
    @Autowired
    private FactureRepository IR;
    @Autowired
    private UtilisateurRepository UR;
    @Autowired
    private PanierService CS;

    @Override
    public Facture generateFacture(Long idUser, boolean delivery, Facture.PaimentMode paimentMode,String adresse) {
        Utilisateur user= UR.findById(idUser).get();
        Facture facture = new Facture();
        facture.setUtilisateur(user);
        Panier panier = CS.findByIdUser(user.getIdUser());
        if (panier!= null){

            List<Equipement> equipments= CS.fetchEquipmentsinPanier(panier.getIdPanier());

            facture.setTotale(panier.getTotalPrice());
            facture.setTotalNumberEquipPurchased(panier.getNumberOfEquipPurchased());
            if (delivery) {
                float deliveryPrice=10;
                facture.setDeleviryPrice(deliveryPrice);
                facture.setTotale(panier.getFinalPrice()+deliveryPrice);}else {
                facture.setDeleviryPrice(0F);}

            facture.setDate(LocalDate.now());
            user.getFacture().add(facture);
            for(Equipement equipment:equipments){
                equipment.setQuantityInStock(equipment.getQuantityInStock()-equipment.getPurchaseQuantity());
                equipment.setDateModification(LocalDateTime.now());
                equipment.setPurchaseQuantity(equipment.getPurchaseQuantity() + 1);
                if(equipment.getQuantityInStock()==0){equipment.setAvailabilty(false);}

            }
            facture.setTotale(panier.getFinalPrice());
            facture.setModeOfPaiment(paimentMode);
            facture.setAdresse(adresse);
            facture.setPaimentStatus(Facture.PaimentStatus.NOT_PAID);
            CS.clearPanier(panier.getIdPanier());
            return IR.save(facture);
        }

        return  null;
    }

    @Override
    public Facture updateFacture(Long idFacture, Long idUser) {
        return null;
    }

    @Override
    public void deleteFacture(Long IdFacture) {
        Facture facture = IR.findById(IdFacture).get();
        IR.delete(facture);

    }

    @Override
    public List<Facture> getAllFactures() {
        return IR.findAll();
    }

    @Override
    public Facture findFactureById(Long IdFacture) {
        Facture facture = IR.findById(IdFacture).get();
        return facture;    }

    @Override
    public List<Object[]> getTotalPriceAndNumberOfEquipmentByMonthAndYEar() {
        return IR.getTotalPriceAndNumberOfEquipementByMonthAndYear();
    }

    @Override
    public Facture ProcessedFacture(Facture facture, Long idFacture) {
        Facture fact = IR.findById(idFacture).get();
        fact.setPaimentStatus(facture.getPaimentStatus());
        return IR.save(fact);    }
}
