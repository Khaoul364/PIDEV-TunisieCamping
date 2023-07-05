package tn.esprit.spring.service;

import tn.esprit.spring.entity.PanierEquipement;

import java.util.List;

public interface IPanierEquipementService {
    List<PanierEquipement> findPanierEquipByIdPanier(Long idPanier);
}
