package tn.esprit.spring.service;

import tn.esprit.spring.entity.Facture;

import java.util.List;

public interface IFactureService {
    Facture updateFacture(Long idFacture, Long idUser);
    void deleteFacture(Long IdFacture);
    List<Facture> getAllFactures();
    Facture findFactureById(Long IdFacture);
}
