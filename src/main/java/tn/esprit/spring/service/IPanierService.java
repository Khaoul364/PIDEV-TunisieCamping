package tn.esprit.spring.service;

import tn.esprit.spring.entity.Panier;

import java.util.List;

public interface IPanierService {
    List<Panier> fetchPanierList();
    Panier createPanier(Long IdUser);
    void deletePanier(Long id);
    Panier findByIdPanier(Long idc);
}
