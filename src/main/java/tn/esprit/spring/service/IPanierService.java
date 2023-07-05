package tn.esprit.spring.service;

import tn.esprit.spring.entity.Equipement;
import tn.esprit.spring.entity.Panier;

import java.util.List;

public interface IPanierService {
    List<Panier> fetchPanierList();
    Panier createPanier(Long IdUser);
    void deletePanier(Long id);
    Panier findByIdPanier(Long idc);
    Panier addEquipmentToPanier(Long IdPanier, Long IdEquipment);
    Panier removeEquipmentFromPanier(Long IdPanier, Long IdEquipment);
    Panier findByIdUser(Long idu);
    void updateTotalPrice(Panier panier);
    List<Equipement> fetchEquipmentsinPanier(Long idPanier);

    String clearPanier(Long idPanier);
    Long getIdPanier(Long idu);
}
